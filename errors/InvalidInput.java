package src.console.errors;

public class InvalidInput extends RuntimeException {
    public InvalidInput(String message) {
        super(message);
    }
}