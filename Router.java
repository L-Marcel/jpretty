package src.console;

import java.util.LinkedList;

import src.console.interfaces.Page;
import src.console.layout.Menu;

public class Router {
    private static Router instance;
    private Menu menu = Menu.getInstance();
    private LinkedList<Page> history = new LinkedList<>();

    private Router() {};
    public static Router getInstance() {
        if(instance == null) instance = new Router();
        return instance;
    };

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
        } else {
            menu.end();
        };
    };

    public void navigate(Page route) {
        history.addLast(route);
        update();
    }

    public void back() {
        history.removeLast();
        update();
    }

    public void replace(Page route) {
        history.removeLast();
        history.addLast(route);
        update();
    }
}
