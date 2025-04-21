package expression.parser.modes;

public interface Mode<T> {
    T add(T a, T b);
    T substract(T a, T b);
    T multiply(T a, T b);
    T divide(T a, T b);
    T parseValue(String a);
    T negate(T a);
}
