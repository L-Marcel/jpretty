package console.keys;

import console.interfaces.KeyTranslator;

public class DefaultKeyTranslator implements KeyTranslator<Key> {
    private boolean windows = false;

    public DefaultKeyTranslator() {
        windows = System.getProperty("os.name").toLowerCase().startsWith("windows");
    }

    @Override
    public Key translate(int key) {
        if(windows) return translateFromWindows(key);
        return translateFromUnix(key);
    };

    @Override
    public Key untranslatable() {
        return Key.UNTRANSLATABLE;
    };

    private Key translateFromWindows(int key) {
        switch(key) {
            case 16:
                return Key.UP;
            case 14:
                return Key.DOWN;
            case 2:
                return Key.LEFT;
            case 6:
                return Key.RIGHT;
            case 13:
                return Key.ENTER;
            case 8:
                return Key.BACKSPACE;
            case 32:
                return Key.SPACE;
            default:
                return Key.untranslatable(key);
        }
    };

    private Key translateFromUnix(int key) {
        switch(key) {
            case 16:
                return Key.UP;
            case 14:
                return Key.DOWN;
            case 2:
                return Key.LEFT;
            case 6:
                return Key.RIGHT;
            case 10:
                return Key.ENTER;
            case 8:
                return Key.BACKSPACE;
            case 32:
                return Key.SPACE;
            default:
                return Key.untranslatable(key);
        }
    };
}
