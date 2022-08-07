package ku.cs.app.models;
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


}
