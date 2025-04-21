package expression.exceptions;

public class ExpressionException extends RuntimeException {
    protected String message;

    public String getMessage() {
        return message;
    }
}
