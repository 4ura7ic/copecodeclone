package ku.cs.app.models;

import ku.cs.app.services.Sorter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ReportList {
    private ArrayList<Report> rpt;

    public ReportList(){
        rpt = new ArrayList<>();
    }

    public void addReport(Report rptIn){
        rpt.add(rptIn);
    }
    public void removeReport(Report rptIn) {rpt.remove(rptIn);}

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
    public ArrayList<Report> returnUserReport(String username) {
        ArrayList<Report> list = new ArrayList<>();
        for (Report rp :rpt) {
            if (username.equals(rp.getAuthorUser())) {
                list.add(rp);
            }
        }
        return list;
    }

    public boolean checkIfExist(String username) {
        for (Report rp:rpt) {
            if (username.equals(rp.getAuthorUser())) {
                return true;
            }
        }
        return false;
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

    public ArrayList<Report> sortTimeReport(String key, ArrayList<Report> reports){
        if(key.equals("Newest")){
            Collections.sort(reports, new Comparator<Report>() {
                @Override
                public int compare(Report o1, Report o2) {
                    return o2.getDate().compareTo(o1.getDate());
                }
            });
        }else if(key.equals("Oldest")){
            Collections.sort(reports, new Comparator<Report>() {
                @Override
                public int compare(Report o1, Report o2) {
                    return o1.getDate().compareTo(o2.getDate());
                }
            });
        } else if (key.equals("Least Vote")) {
            Collections.sort(reports, new Comparator<Report>() {
                @Override
                public int compare(Report o1, Report o2) {
                    if(o1.getVote()>o2.getVote())return 1;
                    if(o1.getVote()<o2.getVote())return -1;
                    return 0;
                }
            });
        } else if (key.equals("Most Vote")){
            Collections.sort(reports, new Comparator<Report>(){
                @Override
                public int compare(Report o1, Report o2){
                    if(o1.getVote()> o2.getVote()) return -1;
                    if(o1.getVote()< o2.getVote()) return 1;
                    return 0;
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
                "rpt=" + rpt +
                '}';
    }
}
