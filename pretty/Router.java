package pretty;

import java.util.LinkedList;

import pretty.interfaces.Page;
import pretty.layout.Menu;

/**
 * Manage navigation between pages
 */
public class Router {
    private static Router instance;
    private Menu menu = Menu.getInstance();
    private LinkedList<Page> history = new LinkedList<>();

    private Router() {};

    /**
     * Get the singleton instance of the Router
     * @return Router instance
     */
    public static Router getInstance() {
        if(instance == null) instance = new Router();
        return instance;
    };

    /**
     * Start the router with a page
     * @param page page to start with
     */
    public void start(Page page) {
        if(history.isEmpty()) {
            menu.start();
            history.addLast(page);
            update();
        };
    };
    
    private void update() {
        if(!history.isEmpty()) {
            Page current = history.getLast();
            menu.cleanup();
            current.render(menu, this);
        };
    };

    /**
     * Navigate to a new page
     * @param route page to navigate to
     */
    public void navigate(Page route) {
        history.addLast(route);
        update();
    }

    /**
     * Navigate back to the previous page
     * @param route page to navigate to
     */
    public void back() {
        history.removeLast();
        update();
    }

    /**
     * Replace the current page with a new page
     * @param route page to replace with
     */
    public void replace(Page route) {
        history.removeLast();
        history.addLast(route);
        update();
    }
}
