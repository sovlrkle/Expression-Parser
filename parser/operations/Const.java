package expression.parser.operations;


import expression.parser.modes.Mode;

public class Const<T> implements AbstractExpression<T> {
    private final T c;

    public Const(T c) {
        this.c = c;
    }

    @Override
    public T evaluate(T x, T y, T z, Mode<T> mode) {
        return c;
    }
}
