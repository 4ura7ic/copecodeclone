package ku.cs.app.models;
import java.util.ArrayList;
import ku.cs.app.models.UserData;

public class UserDataList {
    private ArrayList<UserData> data;
    public UserDataList(){
        data = new ArrayList<>();
    }
    public void addUser(UserData info){
        data.add(info);
    }
    public ArrayList<UserData> getAllData(){
        return data;
    }
    public int dataListSize(){return data.size();}

//    public String getUsername(int index) {
//        return data.get(index).getUserName();
//    }

    @Override
    public String toString() {
        return "userDataList{" +
                "data=" + data +
                '}';
    }
}
