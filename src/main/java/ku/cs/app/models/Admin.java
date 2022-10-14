package ku.cs.app.models;

import ku.cs.app.models.list.UserList;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.UserDataListFileDataSource;

public class Admin extends User{
    //-------------------------------------------- constructor

    public Admin() {
        this("",new Password(),"","");
    }
    public Admin(String username,Password password,String name, String surname) {
        super("admin", username, password, name, surname,"default.jpg");//fix
    }
    public Admin(String role, String username, Password password, String name, String surname, String photo){
        super(role, username, password, name, surname, photo);//fix
    }

    //-------------------------------------------- setter

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    //-------------------------------------------- method

    public void createOfficer (Officer newOfficer){

        DataSource<UserList> dataSource = new UserDataListFileDataSource("data","user.csv");
        UserList list = dataSource.readData();
        list.addUser(newOfficer);
        dataSource.writeData(list);
    }



}
