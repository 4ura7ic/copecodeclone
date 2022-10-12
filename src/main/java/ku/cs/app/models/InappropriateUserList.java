package ku.cs.app.models;

import java.util.ArrayList;

public class InappropriateUserList {
    private ArrayList<InappropriateUser> list;
    public InappropriateUserList() {
        list = new ArrayList<>();
    }
    public void addUser(InappropriateUser user) {
        list.add(user);
    }
    public void removeUser(InappropriateUser user) {
        list.remove(user);
    }

    public boolean checkIfExist(String s) {
        for (InappropriateUser user : list) {
            if (user.getUsername().equals(s)) {
                return true;
            }
        }
        return false;
    }

    public InappropriateUser returnObject(String s){
        for (InappropriateUser user : list) {
            if (user.getUsername().equals(s)) {
                return user;
            }
        }
        return null;
    }
    public ArrayList<InappropriateUser> getInappropriateUserList() {
        return list;
    }
    public ArrayList<String> returnInappropriateUserActivities(String username) {
        ArrayList<String> tmp = new ArrayList<>();
        for (InappropriateUser user : list) {
            if (username.equals(user.getUsername())) {
                tmp.addAll(user.getInappropriateActions());
            }
        }
        return tmp;
    }

    @Override
    public String toString() {
        return "InappropriateUserList{" +
                "list=" + list +
                '}';
    }
}
