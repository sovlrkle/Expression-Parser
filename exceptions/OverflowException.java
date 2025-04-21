package expression.exceptions;

public class OverflowException extends ExpressionException {
    public OverflowException() {
        this.message = "overflow";
    }

    public OverflowException(String message) {
        this.message = message;
    }
}
