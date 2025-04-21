package expression.exceptions;

public class InvalidLogArgumentException extends ExpressionException {
    public InvalidLogArgumentException(String message) {
        this.message = message;
    }
}
