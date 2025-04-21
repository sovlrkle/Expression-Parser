package expression.parser.operations;

import expression.parser.modes.Mode;


public class CheckedNegate<T> implements AbstractExpression<T> {
    private final AbstractExpression<T> expr;
    private boolean hasBrackets = false;

    public CheckedNegate(AbstractExpression<T> expr) {
        this.expr = expr;
    }

    public String toString() {
        if (hasBrackets) {
            return "-(" + expr.toString() + ")";
        }
        StringBuilder sb = new StringBuilder("-" + expr.toString());
        if (sb.length() >= 2 && sb.charAt(0) == sb.charAt(1) && sb.charAt(0) == '-') {
            sb.delete(0, 2);
        }
        return sb.toString();
    }

    public void setHasBrackets() {
        hasBrackets = true;
    }

    @Override
    public T evaluate(T x, T y, T z, Mode<T> mode) {
        return mode.negate(expr.evaluate(x, y, z, mode));
    }
}
