package pretty.interfaces;

/**
 * Formatter interface, used to format objects inputs and outputs
 * @param <T> T - the type of the objects to format
 * @param <O> O - the type of the formatted objects
 */
@FunctionalInterface
public interface Formatter<T, O> {
    /**
     * Format an objects
     * @param t - the objects
     * @return the formatted objects
     */
    O format(T t);
};