package ku.cs.app.models;

public class Report {
    private String topic;
    private String date;
    private String description;

    public Report(String topic, String date, String description){
        this.topic = topic;
        this.date=date;
        this.description=description;
    }
    public Report(String topic, String date){
        this(topic,date,"");
    }
    public Report(){
        this("","","");
    }
    public String getTopic(){return topic;}
    public String getDate(){return date;}
    public String getDescription() {return description;}

    @Override
    public String toString() {
        return topic+" [" + date+ "]";
    }
}
