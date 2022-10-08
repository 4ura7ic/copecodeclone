package ku.cs.app.models;

import javafx.event.ActionEvent;

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

    public ArrayList<InappropriateUser> getInappropriateUserList() {
        return list;
    }

    @Override
    public String toString() {
        return "InappropriateUserList{" +
                "list=" + list +
                '}';
    }
}
