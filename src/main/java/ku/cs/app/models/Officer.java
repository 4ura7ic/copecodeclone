package ku.cs.app.models;

public class Officer extends User{
    //-------------------------------------------- instance
    private String inCharge;

    //-------------------------------------------- constructor
    public Officer() {
    }

    public Officer(String username, Password password, String name, String surname, String inCharge) {
        super("officer", username, password, name, surname);
        this.inCharge=inCharge;
    }

    public String getInCharge(){
        return inCharge;
    }
}
