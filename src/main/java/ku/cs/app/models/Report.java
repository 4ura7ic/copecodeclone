package ku.cs.app.models;

import java.util.ArrayList;

public class Report {
    //-------------------------------------------- instance
    private String topic;
    private String date;
    private String category;
    private String description;
    private String authorUser;
    private String solution;
    private String service;
    private int vote;
    private boolean check;
    private ArrayList<String> votedUser;
    private String photo;


    //-------------------------------------------- constructor

    public Report(String authorUser, String topic, String date, String category, String description,String photo) {
        this(authorUser,topic,date,category,description,photo, "","",0,false);
    }

    public Report(String authorUser, String topic, String date, String category, String description,String photo, String solution,String service,int vote,boolean check){
        this.authorUser = authorUser;
        this.topic = topic;
        this.date = date;
        this.category = category;
        this.description = description;
        this.photo = photo;
        this.solution = solution;
        this.vote = vote;
        this.check = check;
        this.service = service;
        this.votedUser = new ArrayList<>();
    }

    public Report(){
        this("","","","","","","","",0,false);
    }

    //-------------------------------------------- getter
    public String getAuthorUser(){return  authorUser;}
    public String getTopic(){return topic;}
    public String getDate(){return date;}
    public String getDescription() {return description;}
    public String getSolution() {return solution;}
    public String getCategory() {return category;}
    public String getService() {return service;}
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    //-------------------------------------------- method
    @Override
    public String toString() {
        return "[" + category + "] " + topic + " " + reportStatus();
    }

    public String reportStatus(){
        if(service!=""){
            if(isCheck()){
                return "[Complete]";
            }
            else
                return "[In progress]";
        }
        else
            return "[Waiting for officer]";
    }

    public void finishingCheck(){ check = true; }

    public void setSolution(String solution){
        this.solution = solution;
    }
    public void setService(String service) { this.service = service;}
}
