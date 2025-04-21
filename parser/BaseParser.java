package expression.parser;

import expression.exceptions.ParserException;
import expression.exceptions.UnexpectedTokenException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class BaseParser {
    private static final char END = '\0';
    protected StringSource source;
    private char ch = 0xffff;

    protected void setSource(StringSource source) throws ParserException {
        this.source = source;
        take();
    }

    protected char take() throws ParserException {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected boolean isWhitespace() {
        return Character.isWhitespace(ch);
    }

    protected boolean hasNext() {
        return source.hasNext();
    }

    protected char top() {
        return ch;
    }

    protected boolean isOperation() {
        return ch == '+' || ch == '*' || ch == '/';
    }


    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean take(final char expected) throws ParserException {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected void expect(final char expected) throws ParserException {
        if (!take(expected)) {
            throw new UnexpectedTokenException("Expected '" + expected + "', found '" + ch + "'");
        }
    }

    protected void skipWhitespace() throws ParserException {
        while (isWhitespace()) {
            take();
        }
    }

    protected int getPosition() {
        return source.getPosition();
    }

    protected void takeIntegerModule(final StringBuilder sb) throws ParserException {
        while (between('0', '9')) {
            sb.append(take());
        }
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }
}
