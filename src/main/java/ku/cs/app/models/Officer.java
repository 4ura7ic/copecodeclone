package ku.cs.app.models;

public class Officer extends User{

    public Officer() {
    }

    public Officer(String name, String surname, Password password, String userName) {

        super(name, surname, password, userName,"officer");
    }

}
