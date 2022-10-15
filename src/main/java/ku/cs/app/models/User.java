package ku.cs.app.models;
public class User {
    //-------------------------------------------- instance

    protected String name;
    protected String surname;
    protected Password password;
    protected String username;
    protected String role;
    protected String photo;

    //-------------------------------------------- constructor

    public User() {
        this("", "", new Password(), "","","");
    }
    public User(String role, String username, Password password, String name, String surname) {
        this("","",new Password(),"","","default.jpg");
    }
    public User(String role, String username, Password password, String name, String surname, String photo) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.username = username;
        this.role = role;
        this.photo = photo;
    }

    //-------------------------------------------- getter

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() { return password.getPassword(); }

    public String getRole(){return role;}

    public String getPhoto() {
        return photo;
    }

    public String getErrorMsg() {
        return password.getErrorMessage();
    }

    //-------------------------------------------- setter

    public String setPhoto(String newPhoto) {
        this.photo = newPhoto;
        return photo;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println(this.name);
    }

    public void setSurname(String surname) {
        this.surname = surname;
        System.out.println(this.surname);
    }

    public void setUsername(String username) {
        this.username = username;
        System.out.println(this.username);
    }

    public void setPassword(String password){
        this.password.setPassword(password);
    }

    public void setErrorMsg(String msg) { this.password.setErrorMessage(msg); }

    //-------------------------------------------- method

    public String dataFormat(){
        String line = role + "," + username + "," + password
                + "," + name + "," + surname + "," + photo;
        return line;
    }
    public boolean checkIfInputPasswordCorrect (User user, String pwdInput) {
        if (pwdInput.equals("")) {
            user.password.setErrorMessage("Please enter current password.\n");
            return false;
        }
        else if (!user.getPassword().equals(pwdInput)) {
            user.password.setErrorMessage("Incorrect current password.\n");
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkIfNewPasswordEligible(String newPwd, String cfmPwd){
        if (newPwd.equals("")) {
            setErrorMsg("Please enter new password.\n");
            return false;
        }
        else if (!newPwd.equals("") && cfmPwd.equals("")) {
            setErrorMsg("Please confirm password.\n");
            return false;
        }
        else if (!newPwd.equals(cfmPwd)) {
            setErrorMsg("New password does not match.\n");
            return false;
        }
        else {
            return true;
        }
    }
    public boolean checkForDuplicatePassword(String newPwd){
        if (getPassword().equals(newPwd)) {
            setErrorMsg("Duplicate password.\n");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + role + "] " + username;
    }
}
