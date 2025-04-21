package expression.exceptions;

public class InvalidCountOfVarsException extends ExpressionException {
    public InvalidCountOfVarsException(int vars, int values) {
        message = "invalid count of variables: " + vars + ", and values: " + values;
    }

}
