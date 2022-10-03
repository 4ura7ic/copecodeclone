package ku.cs.app.models;
import ku.cs.app.services.Sorter;

import java.util.ArrayList;

public class ReportList {
    private ArrayList<Report> rpt;

    public ReportList(){ rpt = new ArrayList<>();}

    public void addReport(Report rptIn){
        rpt.add(rptIn);
    }

    public ArrayList<Report> getAllRpt(){
        return rpt;
    }

    public ArrayList<Report> sortByCategory(String key){
        ArrayList<Report> tmpReport = new ArrayList<>();
        Sorter sorter = new Sorter();
        for(Report report: rpt){
            if(sorter.categoryFilter(report,key)){
                tmpReport.add(report);
            }
        }
        return tmpReport;
    }

    public ArrayList<Report> sortByUser(String key){
        ArrayList<Report> tmpReport = new ArrayList<>();
        for(Report report: rpt){
            if(report.getAuthorUser().equals(key)){
                tmpReport.add(report);
            }
        }
        return tmpReport;
    }
    public ArrayList<Report> sortByUserAndCategory(String key1,String key2){
        ArrayList<Report> tmpReport = new ArrayList<>();
        Sorter sorter = new Sorter();
        for(Report report: rpt){
            if(report.getAuthorUser().equals(key1)){
                if(sorter.categoryFilter(report,key2))
                tmpReport.add(report);
            }
        }
        return tmpReport;
    }
}
