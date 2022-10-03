package ku.cs.app.models;

public class Report {
    //-------------------------------------------- instance

    private String topic;
    private String date;
    private String category;
    private String description;
    private String authorUser;
    private int rate;


    //-------------------------------------------- constructor

    public Report(String authorUser, String topic, String date, String category, String description) {
        this(authorUser,topic,date,category,description,0);
    }

    public Report(String authorUser, String topic, String date, String category, String description,int rate){
        this.authorUser = authorUser;
        this.topic = topic;
        this.date=date;
        this.category=category;
        this.description=description;
        this.rate =rate;
    }
    public Report(){
        this("","","","","",0);
    }

    //-------------------------------------------- getter
    public String getAuthorUser(){return  authorUser;}
    public String getTopic(){return topic;}
    public String getDate(){return date;}
    public String getDescription() {return description;}
    public String getCategory() {return category;}

    public int getRate() {
        return rate;
    }
    //-------------------------------------------- method

    @Override
    public String toString() {
        return topic+" [" + category+ "]";
    }

}
