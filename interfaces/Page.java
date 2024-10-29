package src.core.interfaces;

import src.core.Router;
import src.core.layout.Menu;

public interface Page {
    void render(Menu menu, Router router);
}