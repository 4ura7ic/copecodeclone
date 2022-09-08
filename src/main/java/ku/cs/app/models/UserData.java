package ku.cs.app.models;

public class UserData {

    protected String name;
    protected String surname;

    protected Password password;
    protected String userName;

    protected String role;

    public UserData() {
        this.name = "";
        this.surname = "";
        this.userName = "";
        password = new Password();
    }

    public UserData(String name, String surname, Password password, String userName,String role) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.userName = userName;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() { return password.getPassword(); }
    public String getRole(){return role;}

    public void setName(String name) {
        this.name = name;
        System.out.println(this.name);
    }

    public void setSurname(String surname) {
        this.surname = surname;
        System.out.println(this.surname);
    }


    public void setUserName(String userName) {
        this.userName = userName;
        System.out.println(this.userName);
    }

    public void setPassword(String password){
        this.password.setPassword(password);
    }

    public String getErrorMsg() {
        return password.getErrorMsg();
    }

    @Override
    public String toString() {
        return userName;
    }
}
