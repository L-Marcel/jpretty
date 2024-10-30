package simple.pages;

import pretty.Router;
import pretty.interfaces.Page;
import pretty.layout.Menu;
import pretty.layout.Text;

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
