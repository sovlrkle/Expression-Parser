package expression.exceptions;

public class NoArgumentException extends ParserException {
    public NoArgumentException(String message) {
        this.message = message;
    }
}
