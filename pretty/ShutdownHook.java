package pretty;

import scala.tools.jline.Terminal;

/**
 * Shutdown hook to restore terminal settings
 */
public class ShutdownHook extends Thread {
    private Terminal terminal;
    
    public ShutdownHook(Terminal terminal) {
        this.terminal = terminal;
    };

    @Override
    public void run() {
        try {
            this.terminal.restore();
        } catch (Exception _) {};
    };
};
