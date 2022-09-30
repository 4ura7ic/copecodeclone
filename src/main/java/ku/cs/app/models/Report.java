package ku.cs.app.models;

public class Report {
    //-------------------------------------------- instance

    private String topic;
    private String date;
    private String category;
    private String description;


    //-------------------------------------------- constructor

    public Report(String topic, String date, String category, String description){
        this.topic = topic;
        this.date=date;
        this.category=category;
        this.description=description;
    }
    public Report(String topic, String date){
        this(topic,date,"","");
    }
    public Report(){
        this("","","","");
    }

    //-------------------------------------------- getter

    public String getTopic(){return topic;}
    public String getDate(){return date;}
    public String getDescription() {return description;}
    public String getCategory() {return category;}

    //-------------------------------------------- method

    @Override
    public String toString() {
        return topic+" [" + date+ "]";
    }

}
