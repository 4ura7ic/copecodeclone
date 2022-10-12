package ku.cs.app.models;

import java.util.ArrayList;

public class UserList {
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
