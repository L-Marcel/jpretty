package simple.pages;

import console.Router;
import console.interfaces.Page;
import console.layout.Menu;

public class MainPage implements Page {
    @Override
    public void render(Menu menu, Router router) {
        Integer[] lockeds = {2};
        String[] options = {"Cadastrar usuário", "Listar usuários", "Configurar permissões", "Informações"};
        
        menu.header("Menu Principal");
        int option = menu.getPageOption(options, lockeds, true);
        menu.divider();

        switch(option) {
            case -1:
                router.back();
                break;
            case 0:
                router.navigate(new CreateUserPage());
                break;
            case 1:
                router.navigate(new ListUserPage());
                break;
            case 3:
                router.navigate(new InfomationPage());
                break;
            default:
                router.replace(this);
                break;
        }
    }
}
