package ku.cs.app.models.list;

import ku.cs.app.models.Report;
import ku.cs.app.services.Sorter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ReportList implements CheckIfExistAndReturnObject<ArrayList<Report>> {
    private ArrayList<Report> reports;

    public ReportList(){
        reports = new ArrayList<>();
    }

    public void addReport(Report rptIn){
        reports.add(rptIn);
    }
    public void removeReport(Report rptIn) {
        reports.remove(rptIn);}

    public ArrayList<Report> getAllRpt(){
        return reports;
    }

    public ArrayList<Report> sortInProgressReport(){
        ArrayList<Report> tmpReport = new ArrayList<>();
        for(Report report: reports){
            if(!report.isCheck()){
                tmpReport.add(report);
            }
        }
        return tmpReport;
    }
    @Override
    public boolean checkIfExist(String username) {
        for (Report rp: reports) {
            if (username.equals(rp.getAuthorUser())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Report> returnObject(String username) {
        ArrayList<Report> list = new ArrayList<>();
        for (Report rp : reports) {
            if (username.equals(rp.getAuthorUser())) {
                list.add(rp);
            }
        }
        return list;
    }

    public ArrayList<Report> sortUserReportByCategory(String key, ArrayList<Report> reports){
        ArrayList<Report> tmpReport = new ArrayList<>();
        Sorter sorter = new Sorter();
        for(Report report: reports){
                if (sorter.categoryFilter(report, key)) {
                    tmpReport.add(report);
                }
        }
        return tmpReport;
    }

    public ArrayList<Report> sortInProgressReportByCategory(String key){
        ArrayList<Report> tmpReport = new ArrayList<>();
        Sorter sorter = new Sorter();
        for(Report report: reports){
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
        for(Report report: reports){
            if(report.isCheck()){
                tmpReport.add(report);
            }
        }
        return tmpReport;
    }
    public ArrayList<Report> sortFinishedReportByCategory(String key){
        ArrayList<Report> tmpReport = new ArrayList<>();
        Sorter sorter = new Sorter();
        for(Report report: reports){
            if(report.isCheck()){
                if(sorter.categoryFilter(report,key))
                    tmpReport.add(report);
            }
        }
        return tmpReport;
    }

    public ArrayList<Report> sortTimeReport(String key, ArrayList<Report> reports){
        switch (key) {
            case "Newest" -> Collections.sort(reports, new Comparator<Report>() {
                @Override
                public int compare(Report o1, Report o2) {
                    return o2.getDate().compareTo(o1.getDate());
                }
            });
            case "Oldest" -> Collections.sort(reports, new Comparator<Report>() {
                @Override
                public int compare(Report o1, Report o2) {
                    return o1.getDate().compareTo(o2.getDate());
                }
            });
            case "Least Vote" -> Collections.sort(reports, new Comparator<Report>() {
                @Override
                public int compare(Report o1, Report o2) {
                    return Integer.compare(o1.getVote(), o2.getVote());
                }
            });
            case "Most Vote" -> Collections.sort(reports, new Comparator<Report>() {
                @Override
                public int compare(Report o1, Report o2) {
                    return Integer.compare(o2.getVote(), o1.getVote());
                }
            });
        }
        return reports;
    }

    public ArrayList<Report> sortByVoteOfReport(int amount, ArrayList<Report> reports){
        ArrayList<Report> tmpReport = new ArrayList<>();
        for(Report report: reports){
            if(report.getVote()>amount)
                tmpReport.add(report);
        }
        return tmpReport;
    }

    @Override
    public String toString() {
        return "ReportList{" +
                "rpt=" + reports +
                '}';
    }
}
