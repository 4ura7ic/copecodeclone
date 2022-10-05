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


    public ArrayList<Report> sortInProgressReport(){
        ArrayList<Report> tmpReport = new ArrayList<>();
        for(Report report: rpt){
            if(!report.isCheck()){
                tmpReport.add(report);
            }
        }
        return tmpReport;
    }

    public ArrayList<Report> sortInProgressReportByCategory(String key){
        ArrayList<Report> tmpReport = new ArrayList<>();
        Sorter sorter = new Sorter();
        for(Report report: rpt){
            if(!report.isCheck()) {
                if (sorter.categoryFilter(report, key)) {
                    tmpReport.add(report);
                }
            }
        }
        return tmpReport;
    }

    public ArrayList<Report> sortFinishedReport(){
        ArrayList<Report> tmpReport = new ArrayList<>();
        for(Report report: rpt){
            if(report.isCheck()){
                tmpReport.add(report);
            }
        }
        return tmpReport;
    }
    public ArrayList<Report> sortFinishedReportByCategory(String key){
        ArrayList<Report> tmpReport = new ArrayList<>();
        Sorter sorter = new Sorter();
        for(Report report: rpt){
            if(report.isCheck()){
                if(sorter.categoryFilter(report,key))
                    tmpReport.add(report);
            }
        }
        return tmpReport;
    }

    @Override
    public String toString() {
        return "ReportList{" +
                "rpt=" + rpt +
                '}';
    }
}
