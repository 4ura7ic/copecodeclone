package ku.cs.app.services;

import ku.cs.app.models.Report;
import ku.cs.app.models.ReportList;

public class ReportListHardCodeSource {
    private ReportList rptList;
    public ReportListHardCodeSource(){
        rptList = new ReportList();
        readReport();
    }
    public void readReport(){
        Report Kevin = new Report("Malware in 708", "7 August 2022");
        Report Put = new Report("Lost wallet in Library", "8 August 2022");
        Report Third = new Report("Fire at building", "8 August 2022");
        Report Non = new Report("Loss car at park", "9 August 2022");
        rptList.addReport(Kevin);
    }
    public ReportList getRptList(){return rptList;}
}
