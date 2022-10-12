package ku.cs.app.services;

import ku.cs.app.models.User;
import ku.cs.app.models.UserList;

public class UserDataListHardCodeDataSource {
    public UserList dataList;

    public UserDataListHardCodeDataSource(){
        dataList = new UserList();
        readData();
    }

    public void readData(){
        User Kevin = new User();
        User Put = new User();
        User Third = new User();
        User Non = new User();
        User Tester = new User();

        Kevin.setName("Salute");
        Put.setName("Tanapat");
        Third.setName("Sirawit");
        Non.setName("Natchanon");
        Tester.setName("Name101");

        Kevin.setSurname("Khumyunn");
        Put.setSurname("Bumrungthaiworakul");
        Third.setSurname("Laomongkhulchaisri");
        Non.setSurname("Kaewmongkol");
        Tester.setSurname("Surname101");

        Kevin.setUsername("Kevin99008");
        Put.setUsername("putzamonkey");
        Third.setUsername("Sirrtrd3");
        Non.setUsername("auratic_uel");
        Tester.setUsername("Tester101");

        Kevin.setPassword("KevinGG555");
        Put.setPassword("Putza277353");
        Third.setPassword("Tt!1344556");
        Non.setPassword("NonThepZa007");
        Tester.setPassword("Tester1234");

        dataList.addUser(Kevin);
        dataList.addUser(Put);
        dataList.addUser(Third);
        dataList.addUser(Non);
        dataList.addUser(Tester);
    }

    public UserList getDataList(){
        return dataList;
    }
}
