package expression.exceptions;

public class UnexpectedTokenException extends ParserException {
    public UnexpectedTokenException(String message, int pos) {
        this.message = message;
        this.pos = pos;
    }

    public UnexpectedTokenException(String message) {
        this.message = message;
    }
}
