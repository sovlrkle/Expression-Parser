package expression.parser.operations;


import expression.parser.modes.Mode;

public abstract class BinaryOperation<T> implements AbstractExpression<T> {
    protected AbstractExpression<T> left;
    protected AbstractExpression<T> right;

    public BinaryOperation(AbstractExpression<T> left, AbstractExpression<T> right) {
        this.left = left;
        this.right = right;
    }


    public T evaluate(T x, T y, T z, Mode<T> mode) {
        return calc(left.evaluate(x, y, z, mode), right.evaluate(x, y, z, mode), mode);
    }

    protected abstract T calc(T a, T b, Mode<T> mode);

}
