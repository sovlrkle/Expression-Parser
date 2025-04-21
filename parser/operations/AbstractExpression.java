package expression.parser.operations;


import expression.parser.modes.Mode;

public interface AbstractExpression<T> {
    T evaluate(T x, T y, T z, Mode<T> mode);
}
