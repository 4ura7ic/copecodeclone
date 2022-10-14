package ku.cs.app.models.list;

import ku.cs.app.models.UserRequest;
import ku.cs.app.models.list.CheckIfExistAndReturnObject;

import java.util.ArrayList;

public class UserRequestList implements CheckIfExistAndReturnObject<UserRequest> {
    private ArrayList<UserRequest> rqList;

    public UserRequestList() {rqList = new ArrayList<>();}

    public void addUser(UserRequest user) {
        rqList.add(user);
    }
    public void  removeUser(UserRequest user) {
        rqList.remove(user);
    }
    public ArrayList<UserRequest> getAllData() {
        return rqList;
    }

    @Override
    public boolean checkIfExist(String username) {
        for (UserRequest user: rqList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public UserRequest returnObject(String username) {
        for (UserRequest user : rqList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

}
