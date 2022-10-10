package ku.cs.app.models;

public class Officer extends User{
    //-------------------------------------------- instance
    private String inCharge;

    //-------------------------------------------- constructor
    public Officer() {
        super("", "", new Password(), "","","");
    }

    public Officer(String username, Password password, String name, String surname, String inCharge) {
        super("officer", username, password, name, surname,"rickroll.gif");
        this.inCharge=inCharge;
    }

    public Officer(String role, String username, Password password, String name, String surname, String photo, String inCharge) {
        super(role, username, password, name, surname, photo);
        this.inCharge = inCharge;
    }

    public void setInCharge(String inCharge) {
        this.inCharge = inCharge;
    }

    public String getInCharge(){
        return inCharge;
    }

    @Override
    public String toString() {
        return "[" + role + "] " + username;
    }
}
