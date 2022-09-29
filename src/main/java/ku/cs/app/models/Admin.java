package ku.cs.app.models;

import ku.cs.app.services.DataSource;
import ku.cs.app.services.OfficerListFileDataSource;

public class Admin extends User{
    public Admin() {
        this("",new Password(),"","");
    }
    public Admin(String username,Password password,String name, String surname) {
        super("admin", username, password, name, surname);
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

}
