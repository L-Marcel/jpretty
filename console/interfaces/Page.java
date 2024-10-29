package src.console.interfaces;

import src.console.Router;
import src.console.layout.Menu;

public interface Page {
    void render(Menu menu, Router router);
}