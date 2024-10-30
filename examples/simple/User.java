package simple;

import simple.enums.Access;
import simple.enums.Genrer;

public class User {
    private Access access;
    private Genrer genrer;
    private String name;
    private String email;
    private int age;
    private float weight;


    public User(Access access, Genrer genrer, String name, String email, int age, float weight) {
        this.access = access;
        this.genrer = genrer;
        this.name = name;
        this.email = email;
        this.age = age;
        this.weight = weight;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public Genrer getGenrer() {
        return genrer;
    }

    public void setGenrer(Genrer genrer) {
        this.genrer = genrer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        String accessText = "Usuário";
        if(access == Access.ADMIN) accessText = "Administrador";
        return name + "\n"
            + "   Acesso: " + accessText + "\n"
            + "   E-mail: " + email;
    }

    public String completeToString() {
        String accessText = "Usuário";
        if(access == Access.ADMIN) accessText = "Administrador";
        String genrerText = "Masculino";
        if(genrer == Genrer.FEMALE) genrerText = "Feminino";
        else if(genrer == Genrer.OTHER) genrerText = "Outro";
        return "Acesso: " + accessText + "\n"
            + "Gênero: " + genrerText + "\n"
            + "E-mail: " + email + "\n"
            + "Idade: " + age + "\n"
            + "Peso: " + weight;
    }
}
