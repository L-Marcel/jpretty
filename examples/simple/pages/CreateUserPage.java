package simple.pages;

import console.Router;
import console.errors.InvalidInput;
import console.interfaces.Page;
import console.layout.Menu;
import simple.Main;
import simple.User;
import simple.enums.Access;
import simple.enums.Genrer;

public class CreateUserPage implements Page {
    @Override
    public void render(Menu menu, Router router) {
        String[] accesses = {"Usuário", "Administrador"};
        String[] genrers = {"Masculino", "Feminino", "Outro"};

        menu.header("Cadastrar usuário");
        Access access = Access.fromCode(menu.getOption("Acesso: ", accesses));
        Genrer genrer = Genrer.fromCode(menu.getOption("Gênero: ", genrers));
        String name = menu.getString("Nome: ", null);
        String email = menu.getString("E-mail: ", (t) -> {
            if (!t.contains("@")) throw new InvalidInput("Forneça um e-mail válido.");
        });
        int age = menu.getInt("Idade: ", (i) ->  {
            if (i < 18) throw new InvalidInput("O usuário deve ter pelo menos 18 anos.");
        });
        float weight = menu.getFloat("Peso: ", null);
        menu.divider();
        boolean confirmation = menu.getPageConfirmation();
        if(confirmation) {
            Main.users.addFirst(new User(access, genrer, name, email, age, weight));
        };
        router.back();
    }
}
