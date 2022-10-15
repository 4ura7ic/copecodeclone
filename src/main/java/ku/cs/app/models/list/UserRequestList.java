package ku.cs.app.models.list;

import ku.cs.app.models.UserRequest;

import java.util.ArrayList;

public class UserRequestList implements CheckIfExistAndReturnObject<UserRequest> {
    private ArrayList<UserRequest> userRequests;

    public UserRequestList() {
        userRequests = new ArrayList<>();}

    public void addUser(UserRequest user) {
        userRequests.add(user);
    }
    public void  removeUser(UserRequest user) {
        userRequests.remove(user);
    }
    public ArrayList<UserRequest> getAllData() {
        return userRequests;
    }

    @Override
    public boolean checkIfExist(String username) {
        for (UserRequest user: userRequests) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public UserRequest returnObject(String username) {
        for (UserRequest user : userRequests) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

}
