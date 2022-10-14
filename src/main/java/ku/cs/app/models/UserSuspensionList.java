package ku.cs.app.models;

import java.util.ArrayList;

public class UserSuspensionList implements CheckIfExistAndReturnObject<UserSuspension> {
    private ArrayList<UserSuspension> suspendedUser;

    public UserSuspensionList(){
        suspendedUser = new ArrayList<>();
    }

    public void addUser(UserSuspension user){
        suspendedUser.add(user);
    }

    public void removeUser(UserSuspension user){
        suspendedUser.remove(user);
    }

    public ArrayList<UserSuspension> getAllData(){
        return suspendedUser;
    }

    public boolean checkIfExist(String username) {
        for (UserSuspension user : suspendedUser) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public UserSuspension returnObject(String username) {
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
