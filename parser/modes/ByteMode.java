package expression.parser.modes;

import expression.exceptions.DivideByZeroException;

public class ByteMode implements Mode<Byte> {
    @Override
    public Byte add(Byte a, Byte b) {
        return (byte) (a + b);
    }

    @Override
    public Byte substract(Byte a, Byte b) {
        return (byte) (a - b);
    }

    @Override
    public Byte multiply(Byte a, Byte b) {
        return (byte) (a * b);
    }

    @Override
    public Byte divide(Byte a, Byte b) {
        if (b == 0) {
            throw new DivideByZeroException();
        }
        return (byte) (a / b);
    }

    @Override
    public Byte parseValue(String a) {
        return (byte) Integer.parseInt(a);
    }

    @Override
    public Byte negate(Byte a) {
        return (byte) -a;
    }
}
