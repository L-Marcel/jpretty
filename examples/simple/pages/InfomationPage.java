package simple.pages;

import console.Router;
import console.interfaces.Page;
import console.layout.Menu;
import console.layout.Text;

public class InfomationPage implements Page {
    @Override
    public void render(Menu menu, Router router) {
        menu.header("Informações");
        menu.push("Versão: " + Text.highlight("1.0.0"));
        menu.push("Desenvolvido por: " + Text.highlight("Lucas Marcel"));
        menu.divider();
        menu.pushPageBack();
        router.back();
    }
}
