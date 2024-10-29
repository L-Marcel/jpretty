package console.interfaces;

public interface KeyTranslator<K> {
    public K translate(int key);
    public K untranslatable();
}