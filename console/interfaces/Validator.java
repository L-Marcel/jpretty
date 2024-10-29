package console.interfaces;

import console.errors.InvalidInput;

@FunctionalInterface
public interface Validator<T> {
    void validate(T t) throws InvalidInput;
}