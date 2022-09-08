package ku.cs.app.models;

import ku.cs.app.services.DataSource;
import ku.cs.app.services.OfficerListFileDataSource;

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

    public void createOfficer (String name, String surname, Password password, String userName){

        DataSource<OfficerList> dataSource = new OfficerListFileDataSource("data","officer.csv");
        OfficerList list = dataSource.readData();

        Officer tempOfficer = new Officer(name,surname,password,userName);
        list.addOfficer(tempOfficer);
        dataSource.writeData(list);
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
