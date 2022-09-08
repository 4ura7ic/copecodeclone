package ku.cs.app.models;

public class Officer extends UserData{

    public Officer() {
    }

    public Officer(String name, String surname, Password password, String userName) {

        super(name, surname, password, userName,"officer");
    }

    @Override
    public String toString() {
        return "Officer{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password=" + password +
                ", userName='" + userName + '\'' +
                '}';
    }
}
