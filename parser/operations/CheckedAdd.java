package expression.parser.operations;


import expression.parser.modes.Mode;

public class CheckedAdd<T> extends BinaryOperation<T> implements AbstractExpression<T> {
    public CheckedAdd(AbstractExpression<T> left, AbstractExpression<T> right) {
        super(left, right);
    }

    @Override
    protected T calc(T a, T b, Mode<T> mode) {
        return mode.add(a, b);
    }
}
