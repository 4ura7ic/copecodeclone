package ku.cs.app.models;

//import ku.cs.app.services.OfficerListFileDataSource;

import ku.cs.app.services.DataSource;
import ku.cs.app.services.OfficerListFileDataSource;

public class Admin extends User{
    //-------------------------------------------- constructor

    public Admin() {
        this("",new Password(),"","");
    }
    public Admin(String username,Password password,String name, String surname) {
        super("admin", username, password, name, surname);
    }

    //-------------------------------------------- setter

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    //-------------------------------------------- method

    public void createOfficer (String username, Password password,String name, String surname, String inCharge){

        DataSource<OfficerList> dataSource = new OfficerListFileDataSource("data","officer.csv");
        OfficerList list = dataSource.readData();

        Officer tempOfficer = new Officer(username, password, name, surname, inCharge);
        list.addOfficer(tempOfficer);
        dataSource.writeData(list);
    }



}
