package simple.pages;

import console.Router;
import console.errors.InvalidInput;
import console.interfaces.Page;
import console.layout.Menu;

public class CreateUserPage implements Page {
    @Override
    public void render(Menu menu, Router router) {
        String[] access = {"Usuário", "Administrador"};
        String[] genrers = {"Masculino", "Feminino", "Outro"};

        menu.header("Cadastrar usuário");
        menu.getOption("Acesso: ", access);
        menu.getOption("Gênero: ", genrers);
        menu.getString("Nome: ", null);
        menu.getString("E-mail: ", (t) -> {
            if (!t.contains("@")) throw new InvalidInput("Forneça um e-mail válido.");
        });
        menu.getInt("Idade: ", (i) ->  {
            if (i < 18) throw new InvalidInput("O usuário deve ter pelo menos 18 anos.");
        });
        menu.getFloat("Peso: ", null);
        menu.divider();
        boolean confirmation = menu.getPageConfirmation();
        if(confirmation) {
            menu.cleanup();
            menu.header("Aviso");
            menu.push("Você confirmou que quer criar o usuário.");
            menu.push("Essa função não foi implementada neste exemplo.");
            menu.pushPageBack();
        };
        router.back();
    }
}
