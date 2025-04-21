package expression.exceptions;

public class EmptyExpressionException extends ParserException {
    public EmptyExpressionException() {
        this.message = "Empty expression";
    }

    public EmptyExpressionException(String message, int pos) {
        this.message = message;
        this.pos = pos;
    }

}
