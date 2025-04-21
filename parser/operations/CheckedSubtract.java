package expression.parser.operations;

import expression.parser.modes.Mode;


public class CheckedSubtract<T> extends BinaryOperation<T> implements AbstractExpression<T> {
    public CheckedSubtract(AbstractExpression<T> left, AbstractExpression<T> right) {
        super(left, right);
    }

    @Override
    protected T calc(T a, T b, Mode<T> mode) {
        return mode.substract(a, b);
    }
}
