package expression.exceptions;

public class IncorrectBracketsException extends ParserException {
    public IncorrectBracketsException(String message) {
        this.message = message;
    }
}
