package expression.exceptions;

public class ParserException extends Exception {
    String message;
    int pos = -1;

    public String getMessage() {
        if (pos >= 0) {
            return message + pos;
        }
        return message;
    }
}
