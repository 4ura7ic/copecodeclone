package ku.cs.app.models;

import java.util.ArrayList;

public class UserRequestList {
    private ArrayList<UserRequest> rqList;

    public UserRequestList() {rqList = new ArrayList<>();}

    public void addUser(UserRequest user) {
        rqList.add(user);
    }
    public void  removeUser(UserSuspension user) {
        rqList.remove(user);
    }
    public ArrayList<UserRequest> getAllData() {
        return rqList;
    }

    public boolean checkIfExist(String username) {
        for (UserRequest user: rqList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public UserRequest returnObject(String username) {
        for (UserRequest user : rqList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

}
