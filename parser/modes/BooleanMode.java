package expression.parser.modes;

import expression.exceptions.DivideByZeroException;

public class BooleanMode implements Mode<Boolean> {
    @Override
    public Boolean add(Boolean a, Boolean b) {
        return a | b;
    }

    @Override
    public Boolean substract(Boolean a, Boolean b) {
        return a ^ b;
    }

    @Override
    public Boolean multiply(Boolean a, Boolean b) {
        return a & b;
    }

    @Override
    public Boolean divide(Boolean a, Boolean b) {
        if (!b) {
            throw new DivideByZeroException();
        }
        return (a ? 1 : 0) / (b ? 1 : 0) != 0;
    }

    @Override
    public Boolean parseValue(String a) {
        if (Integer.parseInt(a) != 0 || a.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean negate(Boolean a) {
        return a;
    }
}
