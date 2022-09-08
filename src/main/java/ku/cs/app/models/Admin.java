package ku.cs.app.models;

import ku.cs.app.services.DataSource;

public class Admin extends UserData{

    public Admin() {
        this.name = "";
        this.surname = "";
        this.userName = "";
        password = new Password();
    }

    public Admin(String name, String surname, Password password, String userName) {
        super(name, surname, password, userName,"admin");
    }



    @Override
    public void setName(String name) {
        super.setName(name);
    }


    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password=" + password +
                ", userName='" + userName + '\'' +
                '}';
    }
}
