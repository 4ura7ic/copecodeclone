package ku.cs.app.models;

import java.util.ArrayList;

public class InappropriateUser {
    private String username;
    private int InappropriateActivityCount;
    private ArrayList<String> InappropriateActions;

    public InappropriateUser(){
        username = "";
        InappropriateActivityCount = 0;
        InappropriateActions = new ArrayList<>();
    };
    public InappropriateUser(int inappropriateActivityCount){
        InappropriateActivityCount = inappropriateActivityCount;
        InappropriateActions = new ArrayList<>();
    }
    public void addAllInappropriateActions(ArrayList<String> InappropriateActions){
        InappropriateActions.addAll(InappropriateActions);
    }

    public void addInappropriateActivityCount(){
        InappropriateActivityCount += 1;
    }
    public void addInappropriateAction(String s){
        InappropriateActions.add(s);
    }

    public int getInappropriateActivityCount() {
        return InappropriateActivityCount;
    }

    public ArrayList<String> getInappropriateActions() {
        return InappropriateActions;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "InappropriateUser{" +
                "InappropriateActivityCount=" + InappropriateActivityCount +
                ", InappropriateActions=" + InappropriateActions +
                '}';
    }
}
