package simple;

import java.util.LinkedList;

import console.Router;
import simple.enums.Access;
import simple.enums.Genrer;
import simple.pages.MainPage;

public class Main {
    public static LinkedList<User> users = new LinkedList<>();
    public static void main(String[] args) {
        users.add(new User(Access.ADMIN, Genrer.MALE, "Lucas Marcel", "fake@gmail.com", 22,  35.4f));
        users.add(new User(Access.ADMIN, Genrer.MALE, "Lucas Silva", "fake1@gmail.com", 23,  35.5f));
        users.add(new User(Access.ADMIN, Genrer.MALE, "Lucas Brito", "fake2@gmail.com", 24,  35.6f));
        users.add(new User(Access.ADMIN, Genrer.MALE, "Lucas João", "fake3@gmail.com", 25,  35.7f));
        users.add(new User(Access.ADMIN, Genrer.MALE, "Lucas Manuel", "fake4@gmail.com", 26,  35.8f));
        users.add(new User(Access.ADMIN, Genrer.MALE, "Lucas Paulo", "fake5@gmail.com", 27,  35.9f));
        users.add(new User(Access.ADMIN, Genrer.MALE, "Lucas Thiago", "fake6@gmail.com", 29,  36f));
        users.add(new User(Access.ADMIN, Genrer.MALE, "Lucas Marcelo", "fake7@gmail.com", 29,  36.1f));
        users.add(new User(Access.ADMIN, Genrer.MALE, "Lucas Marcos", "fake8@gmail.com", 30,  36.2f));
        users.add(new User(Access.ADMIN, Genrer.MALE, "Lucas Eduardo", "fake9@gmail.com", 31,  36.3f));
        Router router = Router.getInstance();
        router.start(new MainPage());
    }
};

                  