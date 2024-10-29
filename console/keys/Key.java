package console.keys;

public enum Key {
    UNTRANSLATABLE,
    ENTER,
    BACKSPACE,
    SPACE,
    UP,
    DOWN,
    LEFT,
    RIGHT;

    private int code = 0;
    private Key() {};

    public int getCode() {
        return code;
    }

    public static Key untranslatable(int code) {
        UNTRANSLATABLE.code = code;
        return UNTRANSLATABLE;
    };

    @Override
    public String toString() {
        if(this.equals(UNTRANSLATABLE)) return super.toString() + "(" + code + ")";
        return super.toString();
    }
}
