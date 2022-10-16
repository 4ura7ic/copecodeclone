package ku.cs.app.services;

import ku.cs.app.models.Report;

public class Sorter {
    public boolean categoryFilter(Report report,String key){
        return report.getCategory().equals(key) || key.equals("ALL");
    }
}
