package ku.cs.app.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Activity implements Comparable<Activity> {



    private String dateTime;
    private String activity;
    private String message;

    public Activity() {
        this.dateTime = "";
        this.activity = "";
        this.message = "";
    }
    public Activity(String dateTime, String activity, String message) {
        this.dateTime = dateTime;
        this.activity = activity;
        this.message = message;
    }

    public void setDateTime() {
        LocalDateTime date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        date = LocalDateTime.now();
        dateTime = date.format(formatter);
    }
    public void setActivity(String activity) {
        this.activity = activity;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getDateTime() {
        return dateTime;
    }

    public String getActivity() {
        return activity;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return dateTime + " " + activity + " " + message;
    }

    @Override
    public int compareTo(Activity o) {
        return o.dateTime.compareTo(this.dateTime);
    }
}
