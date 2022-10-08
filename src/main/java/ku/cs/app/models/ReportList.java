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

    public ArrayList<Report> sortTimeReport(String key, ArrayList<Report> reports){
        ArrayList<Report> tmpReport = new ArrayList<>();
        if(key.equals("Newest")){
            Collections.sort(reports, new Comparator<Report>() {
                @Override
                public int compare(Report o1, Report o2) {
                    String[] string1 = o1.getDate().split("/");String[] string2 = o2.getDate().split("/");
                    String s1 = string1[0]+string1[1];
                    String s2 = string2[0]+string2[1];
                    if(s1.compareTo(s2)==1) return -1;
                    if(s1.compareTo(s2)==-1) return 1;
                    return 0;
                }
            });
        }else if(key.equals("Oldest")){
            Collections.sort(reports, new Comparator<Report>() {
                @Override
                public int compare(Report o1, Report o2) {
                    String[] string1 = o1.getDate().split("/");String[] string2 = o2.getDate().split("/");
                    String s1 = string1[0]+string1[1];
                    String s2 = string2[0]+string2[1];
                    if(s1.compareTo(s2)==1) return 1;
                    if(s1.compareTo(s2)==-1) return -1;
                    return 0;
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

    @Override
    public String toString() {
        return "ReportList{" +
                "rpt=" + rpt +
                '}';
    }
}
