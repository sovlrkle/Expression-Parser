package expression.parser.operations;

import expression.parser.modes.Mode;

public class CheckedDivide<T> extends BinaryOperation<T> implements AbstractExpression<T> {
    public CheckedDivide(AbstractExpression<T> left, AbstractExpression<T> right) {
        super(left, right);
    }


    @Override
    protected T calc(T a, T b, Mode<T> mode) {
        return mode.divide(a, b);
    }
}
