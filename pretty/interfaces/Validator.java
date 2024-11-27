package pretty.interfaces;

import pretty.errors.InvalidInput;

/**
 * Validator interface, used to validate objects
 * @param <T> T - the type of the objects to validate
 */
@FunctionalInterface
public interface Validator<T> {
    /**
     * Validate an object
     * @param t - the object to be validated
     * @throws InvalidInput if the object is invalid
     */
    void validate(T t) throws InvalidInput;
};