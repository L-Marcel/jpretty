package console.interfaces;

import console.Router;
import console.layout.Menu;

public interface Page {
    void render(Menu menu, Router router);
}