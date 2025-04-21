package expression.parser;


import expression.exceptions.*;
import expression.parser.operations.AbstractExpression;
import expression.parser.operations.Const;
import expression.parser.operations.Variable;
import expression.parser.modes.Mode;
import expression.parser.operations.CheckedNegate;

public class ExpressionParser<T> extends BaseParser {
    boolean firstArgument = true;
    private final Creator creator = new Creator();
    private Mode<T> mode;

    public AbstractExpression<T> parse(String expression, Mode<T> mode) throws ParserException {
        this.mode = mode;
        setSource(new StringSource(expression));
        AbstractExpression<T> result = parseExpression();
        checkHasNext();
        return result;
    }

    private void checkHasNext() throws ParserException {
        if (hasNext()) {
            throw new UnexpectedTokenException("Incorrect format of expression");
        }
    }

    private AbstractExpression<T> parseExpression() throws ParserException {
        return parseOperations(0);
    }

    private AbstractExpression<T> parseOperations(int priority) throws ParserException {
        if (priority == 2) {
            return parseElement(false);
        }
        AbstractExpression<T> left = parseOperations((priority + 1) % 3);
        while (true) {
            skipWhitespace();
            if (top() == '+' || top() == '-' || top() == '*' || top() == '/') {
                left = creator.createOperation(take(), left, parseOperations((priority + 1) % 3));
            } else {
                return left;
            }
        }
    }

    private void checkEmptyExpr() throws ParserException {
        if (top() == ')') {
            throw new EmptyExpressionException();
        }
        firstArgument = true;
    }

    public AbstractExpression<T> parseElement(boolean isMinus) throws ParserException {
        skipWhitespace();
        if (take('(')) {
            checkEmptyExpr();
            AbstractExpression<T> result = parseExpression();
            expect(')');
            return parseBrackets(isMinus, result);
        } else if (take('-')) {
            firstArgument = false;
            AbstractExpression<T> element = parseElement(true);
            if (isMinus) {
                return new CheckedNegate<>(element);
            }
            return element;
        } else if (between('x', 'z')) {
            firstArgument = false;
            return parseVariable(isMinus);
        } else if (between('0', '9')) {
            firstArgument = false;
            return parseConst(isMinus);
        } else {
            if (firstArgument && isOperation()) {
                throw new OperationException("Expression can't start with: \"" + top() + "\"");
            }
            if (!hasNext()) {
                throw new NoArgumentException("No last argument");
            }
            if (isOperation()) {
                throw new OperationException("After operator incorrect token: \"" + top() + "\" at position: ", getPosition());
            }

            throw new NoArgumentException("No argument");
        }
    }

    private AbstractExpression<T> parseBrackets(boolean isMinus, AbstractExpression<T> result) {
        if (isMinus) {
            CheckedNegate<T> minusResult = new CheckedNegate<>(result);
            minusResult.setHasBrackets();
            return minusResult;
        }
        return result;
    }


    private AbstractExpression<T> parseConst(boolean isMinus) throws ParserException {
        StringBuilder sb = new StringBuilder();
        takeIntegerModule(sb);
        try {
            skipWhitespace();
            if (between('0', '9')) {
                throw new InvalidNumberFormatException("Spaces in number");
            }
            if (isMinus) {
                return new Const<>(mode.parseValue("-" + sb));
            }
            return new Const<>(mode.parseValue(sb.toString()));
        } catch (final NumberFormatException e) {
            throw new InvalidNumberFormatException("Invalid number format (overflow): " + sb);
        }
    }

    private AbstractExpression<T> parseVariable(boolean isMinus) throws ParserException {
        if (!between('x', 'z')) {
            throw new UnexpectedTokenException("unexpected token: " + top(), getPosition());
        }
        if (isMinus) {
            return new CheckedNegate<>(new Variable<>(String.valueOf(take())));
        }
        return new Variable<>(String.valueOf(take()));
    }
}