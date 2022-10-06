package ku.cs.app.models;

public class Officer extends User{
    //-------------------------------------------- instance
    private String inCharge;

    //-------------------------------------------- constructor
    public Officer() {
    }

    public Officer(String name, String surname, Password password, String userName) {

        super(name, surname, password, userName,"officer");
    }

}
