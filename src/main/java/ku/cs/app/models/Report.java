package ku.cs.app.models;

import java.util.ArrayList;

public class Report {
    //-------------------------------------------- instance

    private String topic;
    private String date;
    private String category;
    private String description;
    private String authorUser;
    private int vote;
    private boolean check;
    private ArrayList<String> votedUser;


    //-------------------------------------------- constructor

    public Report(String authorUser, String topic, String date, String category, String description) {
        this(authorUser,topic,date,category,description,0,false);
    }

    public Report(String authorUser, String topic, String date, String category, String description,int vote,boolean check){
        this.authorUser = authorUser;
        this.topic = topic;
        this.date = date;
        this.category = category;
        this.description = description;
        this.vote = vote;
        this.check = check;
        this.votedUser = new ArrayList<>();
    }

    public Report(String topic, String date, String category, String description, String authorUser, int vote, boolean check, ArrayList<String> votedUser) {
        this.topic = topic;
        this.date = date;
        this.category = category;
        this.description = description;
        this.authorUser = authorUser;
        this.vote = vote;
        this.check = check;
        this.votedUser = votedUser;
    }

    public Report(){
        this("","","","","",0,false);
    }

    //-------------------------------------------- getter
    public String getAuthorUser(){return  authorUser;}
    public String getTopic(){return topic;}
    public String getDate(){return date;}
    public String getDescription() {return description;}
    public String getCategory() {return category;}
    public int getVote() {
        return vote;
    }
    public boolean isCheck(){return check;}
    public void increaseVote() {
        vote += 1;
    }
    public void decreaseVote() {
        vote -= 1;
    }
    public void addVotedUser(String username){
        votedUser.add(username);
    }
    public void removeVotedUser(String username) {
        votedUser.remove(username);
    }
    public void addAllVotedUser(ArrayList list){
        votedUser.addAll(list);
    }
    public ArrayList<String> getVotedUser(){
        return votedUser;
    }

    //-------------------------------------------- method
    @Override
    public String toString() {
        return topic + " [" + category + "]";
    }
}
