package expression.parser.modes;

public class DoubleMode implements Mode<Double> {
    @Override
    public Double add(Double a, Double b) {
        return a + b;
    }

    @Override
    public Double substract(Double a, Double b) {
        return a - b;
    }

    @Override
    public Double multiply(Double a, Double b) {
        return a * b;
    }

    @Override
    public Double divide(Double a, Double b) {
        return a / b;
    }

    @Override
    public Double parseValue(String a) {
        return Double.parseDouble(a);
    }

    @Override
    public Double negate(Double a) {
        return -a;
    }
}
