package ku.cs;

import ku.cs.app.models.Report;
import ku.cs.app.models.ReportList;
import ku.cs.app.services.ReportListFileDataSource;

import java.util.ArrayList;

public class TestWriteReport {
    public static void main(String[] args) {
        ReportListFileDataSource dataSource = new ReportListFileDataSource("data","testReport.csv");
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Report rp1 = new Report("topic1", "03 10 2022 / 14:29", "catag1", "desc1",
                "auratic_uel", 3, false, list);
        Report rp2 = new Report("topic2", "03 10 2022 / 14:30", "catag2", "desc2",
                "auratic", 3, false, list);

        ReportList reportList = new ReportList();
        reportList.addReport(rp1);
        reportList.addReport(rp2);
        dataSource.writeData(reportList);
        System.out.println(reportList.toString());
        System.out.println(rp1.getVotedUser());
        for (int i = 0; i < rp1.getVotedUser().toArray().length;i++){
            String rp[] = rp1.getVotedUser().toArray(new String[0]);
            System.out.println(rp[i]);
        }

    }
}
