package expression.exceptions;

public class InvalidNumberFormatException extends ParserException {
    public InvalidNumberFormatException(String message) {
        this.message = message;
    }
}
