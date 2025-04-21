package expression.parser;

import expression.exceptions.IncorrectBracketsException;
import expression.exceptions.UnexpectedTokenException;

import java.util.Set;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class StringSource implements CharSource {
    public final String data;
    private int pos;
    private boolean isMod = false;
    private int[] countBrackets = new int[3]; // (, {, [

    private static final Set<Character> NUMBERS = Set.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'x', 'y', 'z');
    private static final Set<Character> OPERATIONS = Set.of('$', '(', ')', '+', '-', '/', '*', '{', '}', '[', ']');
    private static final Set<Character> UNARY = Set.of('p', 'o', 'w', 'l', 'g');


    public StringSource(final String data) {
        this.data = data;
    }


    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public char next() throws IncorrectBracketsException, UnexpectedTokenException {
        char c = data.charAt(pos++);
        isCorrect(c);
        switch (c) {
            case ('(') -> countBrackets[0]++;
            case (')') -> countBrackets[0]--;
            case ('{') -> countBrackets[1]++;
            case ('}') -> countBrackets[1]--;
            case ('[') -> countBrackets[2]++;
            case (']') -> countBrackets[2]--;
        }
        if (pos >= data.length() && (countBrackets[0] > 0 || countBrackets[1] > 0 || countBrackets[2] > 0)) {
            throw new IncorrectBracketsException("Expression has extra open brackets");
        } else if (pos >= data.length() && (countBrackets[0] < 0 || countBrackets[1] < 0 || countBrackets[2] < 0)) {
            throw new IncorrectBracketsException("Expression has extra close brackets");
        }
        return c;
    }

    public char current() {
        return data.charAt(pos);
    }

    private void isCorrect(char c) throws UnexpectedTokenException {
        if (isMod || Character.isWhitespace(c)) {
            return;
        }
        if (!NUMBERS.contains(c) && !OPERATIONS.contains(c) && !UNARY.contains(c)) {
            throw new UnexpectedTokenException("Unexpected token: \"" + c + "\" at position: ", pos);
        }
    }

    @Override
    public IllegalArgumentException error(final String message) {
        return new IllegalArgumentException(pos + ": " + message);
    }

    public int getPosition() {
        return pos;
    }

}
