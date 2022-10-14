package ku.cs.app.models.list;

import ku.cs.app.models.Admin;
import ku.cs.app.models.Officer;
import ku.cs.app.models.User;
import ku.cs.app.models.list.CheckIfExistAndReturnObject;

import java.util.ArrayList;

public class UserList implements CheckIfExistAndReturnObject<User> {
    private ArrayList<User> data;
    public UserList(){
        data = new ArrayList<>();
    }
    public void addUser(User user){
        data.add(user);
    }
    public void removeUser(User user){
        data.remove(user);
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

    public void changeImageOfficer(Officer officer){
        for (User temp:data){
            if(temp.getUsername().equals(officer.getUsername())){

                temp.setPhoto(officer.getPhoto());

            }
        }
    }
    public void changeImageAdmin(Admin admin){
        for (User temp:data){
            if(temp.getUsername().equals(admin.getUsername())){

                temp.setPhoto(admin.getPhoto());

            }
        }
    }

    @Override
    public boolean checkIfExist(String username) {
        for (User user : data) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User returnObject(String username) {
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
