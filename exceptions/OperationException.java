package expression.exceptions;

public class OperationException extends ParserException {
    public OperationException(String message) {
        this.message = message;
    }

    public OperationException(String message, int pos) {
        this.message = message;
        this.pos = pos;
    }
}
