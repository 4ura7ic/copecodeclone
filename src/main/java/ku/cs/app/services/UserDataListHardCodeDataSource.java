package ku.cs.app.services;
import ku.cs.app.models.UserData;
import ku.cs.app.models.UserDataList;

public class UserDataListHardCodeDataSource {
    private UserDataList dataList;

    public UserDataListHardCodeDataSource(){
        dataList = new UserDataList();
        readData();
    }

    public void readData(){
        UserData Kevin = new UserData();
        UserData Put = new UserData();
        UserData Third = new UserData();
        UserData Non = new UserData();
        UserData Tester = new UserData();

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

        Kevin.setUserName("Kevin99008");
        Put.setUserName("putzamonkey");
        Third.setUserName("Sirrtrd3");
        Non.setUserName("auratic_uel");
        Tester.setUserName("Tester101");

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

    public UserDataList getDataList(){
        return dataList;
    }
}
