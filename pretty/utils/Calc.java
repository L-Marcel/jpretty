package pretty.utils;

/**
 * Math utility functions
 */
public class Calc {
    /**
     * Modulo operation that works with negative numbers
     * @param a - dividend
     * @param b - divisor
     * @return remainder of a divided by b
     */
    public static int mod(int a, int b) {
        return ((a % b) + b) % b;
    };
};
