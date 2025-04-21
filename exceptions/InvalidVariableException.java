package expression.exceptions;

public class InvalidVariableException extends ParserException {
    public InvalidVariableException(String message) {
        this.message = message;
    }

    public InvalidVariableException(String message, int pos) {
        this.message = message;
        this.pos = pos;
    }
}
