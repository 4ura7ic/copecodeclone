package ku.cs.app.models;
import java.util.ArrayList;

public class UserList {
    private ArrayList<User> data;
    public UserList(){
        data = new ArrayList<>();
    }
    public void addUser(User info){
        data.add(info);
    }
    public void removeUser(int index){
        data.remove(index);
    }
    public ArrayList<User> getAllData(){
        return data;
    }
    public int dataListSize(){return data.size();}

    public void changeImageUser(User user){
        for (User temp:data){
            if(temp.getUsername().equals(user.getUsername())){
                temp.setPhoto(user.getPhoto());
            }
        }
    }

    public boolean checkIfUserExisted(String username) {
        for (User user : data) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public User returnUserObject(String username) {
        for (User user : data) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean checkDuplicateUsername(String usernameIn){
        for(User temp: data){
            if(temp.getUsername().equals(usernameIn)){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "userDataList{" +
                "data=" + data +
                '}';
    }
}
