package pretty.keys;

/**
 * Enum representing keys in DefaultKeyTranslator
 */
public enum Key {
    UNTRANSLATABLE,
    ENTER,
    BACKSPACE,
    SPACE,
    UP,
    DOWN,
    LEFT,
    RIGHT;

    /**
     * The code of untranslatable keys
     */
    private int code = 0;
    private Key() {};

    /**
     * Get the code of the key. Except for UNTRANSLATABLE, 
     * all others have the value 0 by default.
     * @return the code of the key
     */
    public int getCode() {
        return code;
    };

    /**
     * Create an untranslatable key
     * @param code - the code of the key
     * @return the untranslatable key
     */
    public static Key untranslatable(int code) {
        UNTRANSLATABLE.code = code;
        return UNTRANSLATABLE;
    };

    @Override
    public String toString() {
        if(this.equals(UNTRANSLATABLE)) return super.toString() + "(" + code + ")";
        return super.toString();
    };
};
