package ku.cs.app.models;

public class Officer extends User{
    //-------------------------------------------- instance
    private String inCharge;

    //-------------------------------------------- constructor
    public Officer() {
    }

    public Officer(String username, Password password, String name, String surname, String inCharge) {
        super("officer", username, password, name, surname);
        this.role = "officer";
        this.inCharge=inCharge;
        this.photo = "rickroll.gif";
    }


//    public Officer(String role, String username, Password password, String name, String surname, String photo, String inCharge) {
//        super(role, username, password, name, surname, photo);
//        this.inCharge = inCharge;
//    }

    public String getInCharge(){
        return inCharge;
    }
}
