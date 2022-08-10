package ku.cs.app.models;

public class Report {
    private String topic;
    private String date;

    public Report(String topic, String date){
        this.topic = topic;this.date=date;
    }
    public String getTopic(){return topic;}
    public String getDate(){return date;}

    @Override
    public String toString() {
        return topic;
    }
}
