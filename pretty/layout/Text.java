package pretty.layout;

import com.diogonunes.jcolor.AnsiFormat;
import static com.diogonunes.jcolor.Attribute.*;

/**
 * Text class to format text in the console
 */
public class Text {
    /**
     * Returns the message in red
     * @param message - the message
     * @return the formatted message
     */
    public static String warning(String message) {
        return new AnsiFormat(BRIGHT_RED_TEXT()).format(message);
    };

    /**
     * Returns the message in green
     * @param message - the message
     * @return the formatted message
     */
    public static String success(String message) {
        return new AnsiFormat(BRIGHT_GREEN_TEXT()).format(message);
    };

    /**
     * Returns the message in blue and bold
     * @param message - the message
     * @return the formatted message
     */
    public static String header(String message) {
        return new AnsiFormat(BRIGHT_BLUE_TEXT(), BOLD()).format(message);
    };

    /**
     * Returns the message in gray
     * @param message - the message
     * @return the formatted message
     */
    public static String locked(String message) {
        return new AnsiFormat(DESATURATED(), BLACK_TEXT()).format(message.toString());
    };

    /**
     * Returns the number in blue
     * @param <T> T - the type of the number
     * @param number - the number
     * @return the formatted number as string
     */
    public static <T extends Number> String highlight(T number) {
        return new AnsiFormat(BRIGHT_BLUE_TEXT()).format(number.toString());
    };

    /**
     * Returns the message in blue
     * @param message - the message
     * @return the formatted message
     */
    public static String highlight(String message) {
        return new AnsiFormat(BRIGHT_BLUE_TEXT()).format(message.toString());
    };

    /**
     * Returns the char in blue
     * @param character - the char
     * @return the formatted character as string
     */
    public static String highlight(char character) {
        return new AnsiFormat(BRIGHT_BLUE_TEXT()).format(Character.toString(character));
    };
}
