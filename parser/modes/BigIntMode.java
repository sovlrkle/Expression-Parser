package expression.parser.modes;

import expression.exceptions.DivideByZeroException;

import java.math.BigInteger;

public class BigIntMode implements Mode<BigInteger> {
    @Override
    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    @Override
    public BigInteger substract(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    @Override
    public BigInteger multiply(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    @Override
    public BigInteger divide(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            throw new DivideByZeroException();
        }
        return a.divide(b);
    }

    @Override
    public BigInteger parseValue(String a) {
        return new BigInteger(a);
    }

    @Override
    public BigInteger negate(BigInteger a) {
        return a.negate();
    }
}
