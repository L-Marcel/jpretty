package simple.pages;

import console.Router;
import console.interfaces.Page;
import console.layout.Menu;
import simple.Main;
import simple.User;

public class ListUserPage implements Page {
    private int initialPage = 0;

    @Override
    public void render(Menu menu, Router router) {
        if(Main.users.size() > 0) {
            menu.header("Usuários");
            String[] options = new String[Main.users.size()];
            int i = 0;
            for(User user : Main.users) options[i++] = user.toString();
            int option = menu.getPageOption(options, initialPage, 4, 0, false);
            if(option >= 0) {
                initialPage = Math.floorDiv(option, 4);
                menu.cleanup();
                User user = Main.users.get(option);
                menu.header(user.getName());
                menu.push(user.completeToString());
                menu.divider();
                menu.pushPageBack();
                router.replace(this);
            } else {
                router.back();
            };
        } else {
            menu.push("Nenhum usuário cadastrado!");
            menu.divider();
            menu.pushPageBack();
            router.back();
        };
    }
}
