package expression.parser.operations;


import expression.parser.modes.Mode;

public class Variable<T> implements AbstractExpression<T> {
    private final String var;

    public Variable(String var) {
        this.var = var;
    }

    @Override
    public T evaluate(T x, T y, T z, Mode<T> mode) {
        if (var.equals("x")) {
            return x;
        } else if (var.equals("y")) {
            return y;
        } else {
            return z;
        }
    }
}
