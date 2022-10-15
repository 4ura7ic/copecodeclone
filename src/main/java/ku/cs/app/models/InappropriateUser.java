package ku.cs.app.models;

import java.util.ArrayList;

public class InappropriateUser {
    private String username;
    private int inappropriateActivityCount;
    private ArrayList<String> InappropriateActions;

    public InappropriateUser(){
        username = "";
        inappropriateActivityCount = 0;
        InappropriateActions = new ArrayList<>();
    };
    public InappropriateUser(String username, int inappropriateActivityCount){
        this.username = username;
        this.inappropriateActivityCount = inappropriateActivityCount;
        InappropriateActions = new ArrayList<>();
    }
    public void addAllInappropriateActions(ArrayList InappropriateActions){
        this.InappropriateActions.addAll(InappropriateActions);
    }
    public void addInappropriateActivityCount(){
        inappropriateActivityCount += 1;
    }

    public int getInappropriateActivityCount() {
        return inappropriateActivityCount;
    }

    public ArrayList<String> getInappropriateActions() {
        return InappropriateActions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addInappropriateActions(String reason) {
        InappropriateActions.add(reason);
    }

    @Override
    public String toString() {
        return "InappropriateUser{" +
                "username='" + username + '\'' +
                ", InappropriateActivityCount=" + inappropriateActivityCount +
                ", InappropriateActions=" + InappropriateActions +
                '}';
    }
}
