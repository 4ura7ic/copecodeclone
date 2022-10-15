package ku.cs.app.models.list;

import ku.cs.app.models.UserSuspension;

import java.util.ArrayList;

public class UserSuspensionList implements CheckIfExistAndReturnObject<UserSuspension> {
    private ArrayList<UserSuspension> suspendedUsers;

    public UserSuspensionList(){
        suspendedUsers = new ArrayList<>();
    }

    public void addUser(UserSuspension user){
        suspendedUsers.add(user);
    }

    public void removeUser(UserSuspension user){
        suspendedUsers.remove(user);
    }

    public ArrayList<UserSuspension> getAllData(){
        return suspendedUsers;
    }

    public boolean checkIfExist(String username) {
        for (UserSuspension user : suspendedUsers) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public UserSuspension returnObject(String username) {
        for (UserSuspension user : suspendedUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "UserSuspensionList{" +
                "suspendedUser=" + suspendedUsers +
                '}';
    }

}
