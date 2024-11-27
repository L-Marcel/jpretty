package pretty.utils;

/**
 * Utility functions for arrays
 */
public class Array {
    /**
     * Check if a value exists in an array
     * @param <T> T - type of array
     * @param array - array to check
     * @param value - value to check
     * @return true if value exists in array, false otherwise
     */
    public static <T> boolean exists(T[] array, T value) {
        if(array == null) return false;
        for (T i : array) {
            if (i == value) return true;
        };
        return false;
    };
};
