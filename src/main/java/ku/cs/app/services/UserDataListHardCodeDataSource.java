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

        Kevin.setName("Salute");
        Put.setName("Tanapat");
        Third.setName("Sirawit");
        Non.setName("Natchanon");

        Kevin.setSurname("Khumyunn");
        Put.setSurname("Bumrungthaiworakul");
        Third.setSurname("Laomongkhulchaisri");
        Non.setSurname("Kaewmongkol");

        Kevin.setUserName("Kevin99008");
        Put.setUserName("putzamonkey");
        Third.setUserName("Sirrtrd3");
        Non.setUserName("auratic_uel");

        Kevin.setPassword("KevinGG555");
        Put.setPassword("Putza277353");
        Third.setPassword("Tt!1344556");
        Non.setPassword("NonThepZa007");

        dataList.addUser(Kevin);
        dataList.addUser(Put);
        dataList.addUser(Third);
        dataList.addUser(Non);
    }

    public UserDataList getDataList(){
        return dataList;
    }
}
