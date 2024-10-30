package pretty.layout;

import java.util.LinkedList;

import pretty.Terminal;
import pretty.errors.InvalidInput;
import pretty.interfaces.Validator;
import pretty.keys.Key;
import pretty.utils.Array;
import pretty.utils.Calc;

/**
 * Used to render menus and get input from the user by terminal
 */
public class Menu {
    private static Menu instance;

    private final int WIDTH = 40;
    private int temporary = 0;

    private Terminal<Key> terminal = Terminal.build();
    private LinkedList<String> lines = new LinkedList<String>();

    private Menu() {};

    /**
     * Get the singleton instance of Menu
     * @return the instance of Menu
     */
    public static Menu getInstance() {
        if(instance == null) instance = new Menu();
        return instance;
    };

    /**
     * Start the menu, called by the router by default
     */
    public void start() {
        terminal.start();
    };

    /**
     * End the menu, called by the router by default
     */
    public void end() {
        terminal.end();
    };

    //#region Control
    /**
     * Push a line to the menu
     * @param line the line to be pushed
     */
    public void push(String line) {
        lines.addLast(line);
        print(true);
    }

    /**
     * Push a line to the menu
     * @param line the line to be pushed
     * @param newLine if the line should be end with a new line
     */
    public void push(String line, boolean newLine) {
        lines.addLast(line);
        print(newLine);
    }

    /**
     * Temporarily push a line to the menu. 
     * The line will be removed after the next rollback.
     * @param line the line to be temporarily pushed
     */
    public void temporarilyPush(String line) {
        temporary++;
        push(line);
    }

    /**
     * Push a line to the menu without rendering it
     * @param line the line to be pushed
     * @param newLine if the line should be end with a new line
     */
    public void phantomPush(String line) {
        lines.addLast(line);
    }

    // Rollback the last line and temporary lines
    public void rollback() {
        rollback(temporary + 1);
        temporary = 0;
    }

    // Rollback a number of lines, not including temporary lines
    public void rollback(int count) {
        while(count > 0) {
            lines.removeLast();
            count--;
        }
        print(true);
    }

    // Rollback the last line, keeping temporary lines
    public void rollbackKeepingTemporary() {
        lines.removeLast();
        print(true);
    }

    // Rollback all lines and temporary lines
    public void cleanup() {
        lines.clear();
    };
    //#endregion

    //#region Private
    private void clear() {
        terminal.clear();
    };

    private void print(boolean newLine) {
        clear();
        for (int i = 0; i < lines.size(); i++) {
            if(i == lines.size() - 1 && !newLine) {
                System.out.print(lines.get(i));
            } else {
                System.out.println(lines.get(i));
            }
        }
    }
    //#endregion

    //#region Special
    /**
     * Rollback the last line and temporary lines 
     * and print an warning message
     * @param message the message to be printed
     */
    public void warning(String message) {
        warning(message, 1);
    };

    /**
     * Rollback a number of lines and temporary lines 
     * and print an warning message
     * @param message the message to be printed
     * @param rollbacks the number of lines to rollback
     */
    public void warning(String message, int rollbacks) {
        rollback(temporary + rollbacks);
        temporary = 0;
        temporarilyPush("");
        temporarilyPush(Text.warning(">> " + message));
        temporarilyPush("");
    };

    /**
     * Push a header to the menu
     * @param title the title
     */
    public void header(String title) {
        int length = title.length() + 2;
        int space = (WIDTH - 6) - length;
        int left = Math.floorDiv(space, 2);
        int right = space - left;
        push("##" + "=".repeat(left) + "# " + Text.header(title) + " #" + "=".repeat(right) + "##");
    }

    /**
     * Push a divider to the menu
     */
    public void divider() {
        int space = WIDTH - 4;
        push("##" + "=".repeat(space) + "##");
    }
    //#endregion

    //#region Input
    /**
     * Get an integer from the user
     * @param prompt the prompt to be shown
     * @param validator the validator to be used
     * @return the integer input
     */
    public int getInt(String prompt, Validator<Integer> validator) {
        try {
            phantomPush("- " + prompt);
            String line = terminal.nextLine("- " + prompt);
            if(line.isEmpty()) throw new Exception();
            int input = Integer.parseInt(line);
            if(validator != null) validator.validate(input);
            rollback();
            push(Text.success("+ ") + prompt + Text.highlight(input));
            return input;
        } catch (NumberFormatException e) {
            warning("Por favor, forneça um número inteiro.");
            return getInt(prompt, validator);
        } catch (InvalidInput e) {
            warning(e.getMessage());
            return getInt(prompt, validator);
        } catch (Exception e) {
            rollbackKeepingTemporary();
            return getInt(prompt, validator);
        }
    }

    /**
     * Get a double from the user
     * @param prompt the prompt to be shown
     * @param validator the validator to be used
     * @return the double input
     */
    public double getDouble(String prompt, Validator<Double> validator) {
        try {
            phantomPush("- " + prompt);
            String line = terminal.nextLine("- " + prompt);
            if(line.isEmpty()) throw new Exception();
            double input = Double.parseDouble(line);
            if(validator != null) validator.validate(input);
            rollback();
            push(Text.success("+ ") + prompt + Text.highlight(input));
            return input;
        } catch (NumberFormatException e) {
            warning("Por favor, forneça um número.");
            return getDouble(prompt, validator);
        } catch (InvalidInput e) {
            warning(e.getMessage());
            return getDouble(prompt, validator);
        } catch (Exception e) {
            rollbackKeepingTemporary();
            return getDouble(prompt, validator);
        }
    }

    /**
     * Get a float from the user
     * @param prompt the prompt to be shown
     * @param validator the validator to be used
     * @return the float input
     */
    public float getFloat(String prompt, Validator<Float> validator) {
        try {
            phantomPush("- " + prompt);
            String line = terminal.nextLine("- " + prompt);
            if(line.isEmpty()) throw new Exception();
            float input = Float.parseFloat(line);
            if(validator != null) validator.validate(input);
            rollback();
            push(Text.success("+ ") + prompt + Text.highlight(input));
            return input;
        } catch (NumberFormatException e) {
            warning("Por favor, forneça um número.");
            return getFloat(prompt, validator);
        } catch (InvalidInput e) {
            warning(e.getMessage());
            return getFloat(prompt, validator);
        } catch (Exception e) {
            rollbackKeepingTemporary();
            return getFloat(prompt, validator);
        }
    }

    /**
     * Get a char from the user
     * @param prompt the prompt to be shown
     * @param validator the validator to be used
     * @return the char input
     */
    public char getChar(String prompt, Validator<Character> validator) {
        try {
            phantomPush("- " + prompt);
            char input = terminal.nextLine("- " + prompt).charAt(0);
            if(validator != null) validator.validate(input);
            rollback();
            push(Text.success("+ ") + prompt + Text.highlight(input));
            return input;
        } catch (InvalidInput e) {
            warning(e.getMessage());
            return getChar(prompt, validator);
        } catch (Exception e) {
            rollbackKeepingTemporary();
            return getChar(prompt, validator);
        }
    }

    /**
     * Get a string from the user
     * @param prompt the prompt to be shown
     * @param validator the validator to be used
     * @return the string input
     */
    public String getString(String prompt, Validator<String> validator) {
        try {
            phantomPush("- " + prompt);
            String input = terminal.nextLine("- " + prompt);
            if(input.isEmpty()) throw new Exception();
            else if(validator != null) validator.validate(input);
            
            rollback();
            push(Text.success("+ ") + prompt + Text.highlight(input));
            return input;
        } catch (InvalidInput e) {
            warning(e.getMessage());
            return getString(prompt, validator);
        } catch (Exception e) {
            rollbackKeepingTemporary();
            return getString(prompt, validator);
        }
    }
    
    /**
     * Show options to the user and get the selected option
     * @param prompt the prompt to be shown
     * @param options the options to be shown
     * @return the selected option
     */
    public int getOption(String prompt, String[] options) {
        return getOption(prompt, options, 0);
    };

    /**
     * Show options to the user and get the selected option
     * @param prompt the prompt to be shown
     * @param options the options to be shown
     * @param selected the default selected option
     * @return the selected option, or -1 if no options is passed
     */
    public int getOption(String prompt, String[] options, int selected) {
        if(options == null || options.length == -1) return -1;
        try {
            push("- " + prompt);
            for (int i = 0; i < options.length; i++) {
                if(selected == i) {
                    push(Text.highlight(" > " + options[i]), i == options.length - 1);
                } else {
                    push("   " + options[i], i == options.length - 1);
                }
            }
            divider();
            push("[" + Text.highlight("UP") + "/" + Text.highlight("DOWN") + "] Escolher");
            push("[" + Text.highlight("ENTER") + "] Confirmar");

            Key key = terminal.key();
            rollback(options.length + 4);
            switch (key) {
                case ENTER:
                    push(Text.success("+ ") + prompt + Text.highlight(options[selected]));
                    return selected;
                case DOWN:
                    if(selected == options.length - 1) return getOption(prompt, options, 0);
                    return getOption(prompt, options, selected + 1);
                case UP:
                    if(selected == 0) return getOption(prompt, options, options.length - 1);
                    return getOption(prompt, options, selected - 1);
                default:
                    return getOption(prompt, options, selected);
            }
        } catch (Exception e) {
            warning(e.getMessage());
            return getOption(prompt, options);
        }
    }
    //#endregion

    //#region Page
    /**
     * Push a footer to the menu with the only option to go back
     */
    public void pushPageBack() {
        push("[" + Text.highlight("BACKSPACE") + "] Voltar");
        if(terminal.key() != Key.BACKSPACE) {
            rollback(1);
            pushPageBack();
        }
    };

    /**
     * Get a confirmation from the user
     * @return true, if the user confirms, false otherwise
     */
    public boolean getPageConfirmation() {
        return getPageConfirmation(false);
    };

    /**
     * Get a confirmation from the user
     * @param exit if the option last option is exit or back
     * @return true, if the user confirms, false otherwise
     */
    public boolean getPageConfirmation(boolean exit) {
        push("[" + Text.highlight("ENTER") + "] Confirmar");
        if(exit) push("[" + Text.highlight("BACKSPACE") + "] Sair");
        else push("[" + Text.highlight("BACKSPACE") + "] Voltar");

        Key key = terminal.key();
        switch (key) {
            case ENTER:
                return true;
            case BACKSPACE:
                return false;
            default:
                rollback(2);
                return getPageConfirmation(exit);
        }
    };

    /**
     * Get an option from the user
     * @param options the options to be shown
     * @return the selected option or -1 if no options are selected
     */
    public int getPageOption(String[] options) {
        return getPageOption(options, null, 0, false);
    };

    /**
     * Get an option from the user
     * @param options the options to be shown
     * @param exit if the option last option is exit or back
     * @return the selected option or -1 if no options are selected
     */
    public int getPageOption(String[] options, boolean exit) {
        return getPageOption(options, null, 0, exit);
    };

    /**
     * Get an option from the user
     * @param options the options to be shown
     * @param lockeds the locked options
     * @return the selected option or -1 if no options are selected
     */
    public int getPageOption(String[] options, Integer[] lockeds) {
        return getPageOption(options, lockeds, 0, false);
    };

    /**
     * Get an option from the user
     * @param options the options to be shown
     * @param lockeds the locked options
     * @param exit if the option last option is exit or back
     * @return the selected option or -1 if no options are selected
     */
    public int getPageOption(String[] options, Integer[] lockeds, boolean exit) {
        return getPageOption(options, lockeds, 0, exit);
    };

    /**
     * Get an option from the user
     * @param options the options to be shown
     * @param lockeds the locked options
     * @param selected the default selected option
     * @param exit if the option last option is exit or back
     * @return the selected option or -1 if no options are selected
     */
    public int getPageOption(String[] options, Integer[] lockeds, int selected, boolean exit) {
        return getPageOption(options, lockeds, 8, selected, exit);
    }

    /**
     * Get an option from the user
     * @param options the options to be shown
     * @param optionsPerPage the max number of options per page
     * @param selected the default selected option
     * @param exit if the option last option is exit or back
     * @return the selected option or -1 if no options are selected
     */
    public int getPageOption(String[] options, int page, int optionsPerPage, int selected, boolean exit) {
        return getPageOption(options, null, optionsPerPage, selected, exit);
    };

    /**
     * Get an option from the user
     * @param options the options to be shown
     * @param lockeds the locked options
     * @param optionsPerPage the max number of options per page
     * @param selected the default selected option
     * @param exit if the option last option is exit or back
     * @return the selected option or -1 if no options are selected
     */
    public int getPageOption(String[] options, Integer[] lockeds, int optionsPerPage, int selected, boolean exit) {
        if(options == null || options.length == 0) {
            boolean confirmation = getPageConfirmation(exit);
            if(confirmation) return 0;
            else return -1;
        }
        else if(lockeds != null && lockeds.length >= options.length) selected = -1;

        try {
            int i = 0;
            int page = 0;
            int pages = 0;

            if(optionsPerPage > 0) {
                page = Math.floorDiv(selected, optionsPerPage);
                pages = Math.ceilDiv(options.length, optionsPerPage);
            };

            int limit = options.length;
            int rollbacks = limit;

            if(page >= 0 && selected >= 0 && optionsPerPage > 0) {
                i = page * optionsPerPage;
                limit = Math.min(i + optionsPerPage, limit);
                rollbacks = limit - i;
            };
            
            for (; i < limit; i++) {
                if(Array.exists(lockeds, i)) {
                    push(Text.locked("- " + options[i]), i == options.length - 1);
                } else if(selected == i) {
                    push(Text.highlight("> " + options[i]), i == options.length - 1);
                } else {
                    push("- " + options[i], i == options.length - 1);
                }
            }
            
            if(page >= 0 && optionsPerPage > 0 && limit > 0 && options.length > optionsPerPage) {
                rollbacks += 2;
                header((page + 1) + " / " + Math.ceilDiv(options.length, optionsPerPage));
                push("[" + Text.highlight("LEFT") + "/" + Text.highlight("RIGHT") + "] Mudar página");
            } else if(limit <= 0) {
                rollbacks++;
                push("Não há nada aqui...");
            };
            divider();
            push("[" + Text.highlight("UP") + "/" + Text.highlight("DOWN") + "] Escolher");
            push("[" + Text.highlight("ENTER") + "] Confirmar");
            if(exit) push("[" + Text.highlight("BACKSPACE") + "] Sair");
            else push("[" + Text.highlight("BACKSPACE") + "] Voltar");

            Key key = terminal.key();
            switch (key) {
                case ENTER:
                    return selected;
                case BACKSPACE:
                    return -1;
                case LEFT:
                    rollback(4 + rollbacks);
                    if(optionsPerPage > 0) {
                        limit = options.length;
                        int index = Calc.mod(selected, optionsPerPage);
                        int previous = Calc.mod(page - 1, pages) * optionsPerPage + index;
                        previous = Math.min(previous, limit - 1);
                        while(Array.exists(lockeds, previous)) previous = Calc.mod(previous - 1, limit);
                        return getPageOption(options, lockeds, optionsPerPage, previous, exit);
                    }
                    else return getPageOption(options, lockeds, optionsPerPage, selected, exit);
                case RIGHT:
                    rollback(4 + rollbacks);    
                    if(optionsPerPage > 0) {
                        limit = options.length;
                        int index = Calc.mod(selected, optionsPerPage);
                        int next = Calc.mod(page + 1, pages) * optionsPerPage + index;
                        next = Math.min(next, limit - 1);
                        while(Array.exists(lockeds, next)) next = Calc.mod(next + 1, limit);
                        return getPageOption(options, lockeds, optionsPerPage, next, exit);
                    }
                    else return getPageOption(options, lockeds, optionsPerPage, selected, exit);
                case DOWN:
                    rollback(4 + rollbacks);
                    if(selected >= 0) {
                        limit = options.length;
                        int next = Calc.mod(selected + 1, limit);
                        while(Array.exists(lockeds, next)) next = Calc.mod(next + 1, limit);
                        return getPageOption(options, lockeds, optionsPerPage, next, exit);
                    } else {
                        return getPageOption(options, lockeds, optionsPerPage, -1, exit);
                    }
                case UP:
                    rollback(4 + rollbacks);
                    if(selected >= 0) {
                        limit = options.length;
                        int previous = Calc.mod(selected - 1, limit);
                        while(Array.exists(lockeds, previous)) previous = Calc.mod(previous - 1, limit);
                        return getPageOption(options, lockeds, optionsPerPage, previous, exit);
                    } else {
                        return getPageOption(options, lockeds, optionsPerPage, -1, exit);
                    }
                default:
                    rollback(4 + rollbacks);
                    return getPageOption(options, lockeds, optionsPerPage, selected, exit);
            }
        } catch (Exception e) {
            return getPageOption(options, lockeds, optionsPerPage, selected, exit);
        }
    }
    //#endregion
}
