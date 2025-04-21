package expression.parser;

import expression.exceptions.ParserException;
import expression.exceptions.UnexpectedTokenException;
import expression.parser.operations.AbstractExpression;
import expression.parser.modes.*;
import expression.parser.operations.CheckedAdd;
import expression.parser.operations.CheckedDivide;
import expression.parser.operations.CheckedMultiply;
import expression.parser.operations.CheckedSubtract;

public class Creator {
    public <T> AbstractExpression<T> createOperation(char operator, AbstractExpression<T> left, AbstractExpression<T> right) throws ParserException {
        return switch (operator) {
            case '*' -> new CheckedMultiply<>(left, right);
            case '/' -> new CheckedDivide<>(left, right);
            case '+' -> new CheckedAdd<>(left, right);
            case '-' -> new CheckedSubtract<>(left, right);
            default -> throw new UnexpectedTokenException(String.valueOf(operator));
        };
    }

    public static Mode<?> createMode(String mode) {
        return switch (mode) {
            case "i" -> new IntMode(true);
            case "d" -> new DoubleMode();
            case "bi" -> new BigIntMode();
            case "b" -> new ByteMode();
            case "u" -> new IntMode(false);
            case "bool" -> new BooleanMode();
            default -> throw new IllegalArgumentException("Invalid mode");
        };
    }
}