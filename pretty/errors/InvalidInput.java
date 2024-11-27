package pretty.errors;

/**
 * Exception to be thrown inside an input Validator
 */
public class InvalidInput extends RuntimeException {
    /**
     * Exception to be thrown inside an Validator
     * @param message - the error message
     */
    public InvalidInput(String message) {
        super(message);
    };

    @Override
    public String toString() {
        return "InvalidInput: " + getMessage();
    };
};