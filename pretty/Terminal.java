package pretty;

import pretty.interfaces.KeyTranslator;
import pretty.keys.DefaultKeyTranslator;
import pretty.keys.Key;
import scala.tools.jline.console.ConsoleReader;

/**
 * Manage terminal input and output
 */
public class Terminal<T> {
    private ConsoleReader console;
    private KeyTranslator<T> translator;

    /**
     * Build a terminal with the default key translator
     * @return Terminal instance
     */
    public static Terminal<Key> build() {
        return build(new DefaultKeyTranslator());
    };

    /**
     * Build a terminal with a custom key translator
     * @param translator custom key translator
     * @return Terminal instance
     */
    public static <K> Terminal<K> build(KeyTranslator<K> translator) {
        return new Terminal<K>(translator);
    };

    private Terminal(KeyTranslator<T> translator) {
        this.translator = translator;
    }

    /**
     * Start the terminal, called by default in Menu
     */
    public void start() {
        try {
            this.console = new ConsoleReader();
        } catch (Exception e) {};
    };

    /**
     * End the terminal, called by default in Menu
     */
    public void end() {
        try {
            if(this.console != null) {
                this.console.getTerminal().restore();
            };
        } catch (Exception e) {}
    };

    /**
     * Clear the terminal screen
     */
    public void clear() {
        try {
            console.clearScreen();
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    };

    /**
     * Print a prompt to the terminal and return 
     * the next line as Scanner's method
     * @param prompt prompt to print
     */
    public String nextLine(String prompt) {
        try {
            console.setPrompt(prompt);
            String line = console.readLine().trim();
            console.setPrompt("");
            return line;
        } catch (Exception e) {
            return "";
        }
    };

    /**
     * Return the next key pressed and translated by KeyTranslator
     * @param prompt prompt to print
     */
    public T key() {
        try {
            return translator.translate(console.readVirtualKey());
        } catch (Exception e) {
            return translator.untranslatable();
        }
    };

    /**
     * Set the key translator
     * @param translator key translator
     */
    public void setTranslator(KeyTranslator<T> translator) {
        this.translator = translator;
    };
}
