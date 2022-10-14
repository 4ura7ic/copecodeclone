package ku.cs.app.models.list;

import ku.cs.app.models.Activity;

import java.util.Collection;
import java.util.TreeSet;

public class ActivityLog {
    private Collection<Activity> log;

    public ActivityLog(){
        log = new TreeSet<>();
    }

    public void addLog(Activity activity) {
        log.add(activity);
    }

    public Collection<Activity> getLog() {
        return log;
    }

    @Override
    public String toString() {
        return "ActivityLog{" +
                "log=" + log +
                '}';
    }
}
