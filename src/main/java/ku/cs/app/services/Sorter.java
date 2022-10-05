package ku.cs.app.services;

import ku.cs.app.models.Report;

public class Sorter {
    public boolean categoryFilter(Report report,String key){
        if(report.getCategory().equals(key)||key.equals("ALL"))
            return true;
        return false;
    }
}
