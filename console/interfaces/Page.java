package console.interfaces;

import console.Router;
import console.layout.Menu;

/**
 * Page interface, pages are passed to the router to be rendered
 */
public interface Page {
    /**
     * Render the page
     * @param menu the menu to be rendered
     * @param router the router to be used
     */
    void render(Menu menu, Router router);
}