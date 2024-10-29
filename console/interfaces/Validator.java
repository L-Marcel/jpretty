package src.console.interfaces;

import src.console.errors.InvalidInput;

@FunctionalInterface
public interface Validator<T> {
    void validate(T t) throws InvalidInput;
}