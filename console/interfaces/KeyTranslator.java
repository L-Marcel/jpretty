package console.interfaces;

public interface KeyTranslator<K> {
    /**
     * Translate an virtual key code to a value
     * @param key the virtual key code
     * @return the translated value
     */
    public K translate(int key);

    /**
     * Get an untranslatable value
     * @return an untranslatable value
     */
    public K untranslatable();
}