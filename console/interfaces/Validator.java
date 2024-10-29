package console.interfaces;

import console.errors.InvalidInput;

/**
 * Validator interface, used to validate objects
 */
@FunctionalInterface
public interface Validator<T> {
    /**
     * Validate an object
     * @param t the object to be validated
     * @throws InvalidInput if the object is invalid
     */
    void validate(T t) throws InvalidInput;
}