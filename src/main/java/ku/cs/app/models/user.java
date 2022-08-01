package ku.cs.app.models;

public class user {

    private String name;
    private String surname;
    private String password;
    private String userName;



    public user(){
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println(this.name);
    }

    public void setSurname(String surname) {
        this.surname = surname;
        System.out.println(this.surname);
    }

    public void setPassword(String password) {
        this.password = password;
        System.out.println(this.password);
    }

    public void setUserName(String userName) {
        this.userName = userName;
        System.out.println(this.userName);
    }

}
