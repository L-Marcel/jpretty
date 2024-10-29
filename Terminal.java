package src.core;

import scala.tools.jline.console.ConsoleReader;
import src.core.interfaces.KeyTranslator;
import src.core.keys.DefaultKeyTranslator;
import src.core.keys.Key;

public class Terminal<T> {
    private ConsoleReader console;
    private KeyTranslator<T> translator;

    public static Terminal<Key> build() {
        return build(new DefaultKeyTranslator());
    };

    public static <K> Terminal<K> build(KeyTranslator<K> translator) {
        return new Terminal<K>(translator);
    };

    public void start() {
        try {
            this.console = new ConsoleReader();
        } catch (Exception e) {};
    };

    public void end() {
        try {
            if(this.console != null) {
                this.console.getTerminal().restore();
            };
        } catch (Exception e) {}
    };

    public Terminal(KeyTranslator<T> translator) {
        this.translator = translator;
    }

    public void clear() {
        try {
            console.clearScreen();
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    };

    public String nextLine(String terminal) {
        try {
            console.setPrompt(terminal);
            String line = console.readLine().trim();
            console.setPrompt("");
            return line;
        } catch (Exception e) {
            return "";
        }
    };

    public T key() {
        try {
            return translator.translate(console.readVirtualKey());
        } catch (Exception e) {
            return translator.untranslatable();
        }
    };

    public void setTranslator(KeyTranslator<T> translator) {
        this.translator = translator;
    };
}
