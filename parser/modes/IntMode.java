package expression.parser.modes;

import expression.exceptions.DivideByZeroException;
import expression.exceptions.OverflowException;

public class IntMode implements Mode<Integer> {
    private final boolean checkOverflow;

    public IntMode(boolean checkOverflow) {
        this.checkOverflow = checkOverflow;
    }

    @Override
    public Integer add(Integer a, Integer b) {
        if (checkOverflow) {
            if (a > 0 && b > Integer.MAX_VALUE - a || a < 0 && b < Integer.MIN_VALUE - a) {
                throw new OverflowException("overflow: a + b");
            }
        }
        return a + b;
    }

    @Override
    public Integer substract(Integer a, Integer b) {
        if (checkOverflow) {
            if (b > 0 && a < b + Integer.MIN_VALUE || b < 0 && a > Integer.MAX_VALUE + b) {
                throw new OverflowException("overflow: a - b");
            }
        }
        return a - b;
    }

    @Override
    public Integer multiply(Integer a, Integer b) {
        if (checkOverflow) {
            if (a == -1 && b == Integer.MIN_VALUE ||
                    b == -1 && a == Integer.MIN_VALUE ||
                    a < -1 && (b > Integer.MIN_VALUE / a || b < Integer.MAX_VALUE / a) ||
                    a > 0 && (b < Integer.MIN_VALUE / a || b > Integer.MAX_VALUE / a)
            ) {
                throw new OverflowException("overflow: a * b");
            }
        }
        return a * b;
    }

    @Override
    public Integer divide(Integer a, Integer b) {
        if (checkOverflow) {
            if (a == Integer.MIN_VALUE && b == -1) {
                throw new OverflowException("overflow: a / b");
            }
        }
        if (b == 0) {
            throw new DivideByZeroException();
        }
        return a / b;
    }

    @Override
    public Integer parseValue(String a) {
        return Integer.parseInt(a);
    }

    @Override
    public Integer negate(Integer a) {
        if (checkOverflow) {
            if (a == Integer.MIN_VALUE) {
                throw new OverflowException("unary minus overflow");
            }
        }
        return -a;
    }
}
