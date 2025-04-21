package expression.parser;

import expression.exceptions.IncorrectBracketsException;
import expression.exceptions.UnexpectedTokenException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface CharSource {
    boolean hasNext();

    char next() throws IncorrectBracketsException, UnexpectedTokenException;

    IllegalArgumentException error(String message);
}
