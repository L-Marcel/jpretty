package src.core.utils;

public class Array {
    public static <T> boolean exists(T[] array, T value) {
        if(array == null) return false;
        for (T i : array) {
            if (i == value) return true;
        }
        return false;
    }
}
