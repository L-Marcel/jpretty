package src.core.layout;

import com.diogonunes.jcolor.AnsiFormat;
import static com.diogonunes.jcolor.Attribute.*;

public class Text {
    public static String warning(String message) {
        return new AnsiFormat(BRIGHT_RED_TEXT()).format(message);
    };

    public static String success(String message) {
        return new AnsiFormat(BRIGHT_GREEN_TEXT()).format(message);
    };

    public static String header(String message) {
        return new AnsiFormat(BRIGHT_BLUE_TEXT(), BOLD()).format(message);
    };

    public static String locked(String message) {
        return new AnsiFormat(DESATURATED(), BLACK_TEXT()).format(message.toString());
    };

    public static <T extends Number> String highlight(T message) {
        return new AnsiFormat(BRIGHT_BLUE_TEXT()).format(message.toString());
    };

    public static String highlight(String message) {
        return new AnsiFormat(BRIGHT_BLUE_TEXT()).format(message.toString());
    };

    public static String highlight(char message) {
        return new AnsiFormat(BRIGHT_BLUE_TEXT()).format(Character.toString(message));
    };
}
