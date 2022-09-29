package ku.cs.app.models;

import java.util.ArrayList;

public class UserSuspensionList {
    private ArrayList<UserSuspension> suspendedUser;

    public UserSuspensionList(){
        suspendedUser = new ArrayList<>();
    }
    public void addUser(UserSuspension user){
        suspendedUser.add(user);
    }
    public void removeUser(int index){
        suspendedUser.remove(index);
    }
    public ArrayList<UserSuspension> getAllData(){
        return suspendedUser;
    }
    public int dataListSize(){return suspendedUser.size();}
    public ArrayList<UserSuspension> getAllSuspendedUser(){
        return suspendedUser;
    }

    public boolean checkIfSuspended(String username) {
        for (UserSuspension user : suspendedUser) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public UserSuspension returnSuspendedUser(String username) {
        for (UserSuspension user : suspendedUser) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "UserSuspensionList{" +
                "suspendedUser=" + suspendedUser +
                '}';
    }
}
