package ku.cs;

import ku.cs.app.models.InappropriateUser;
import ku.cs.app.models.Report;
import ku.cs.app.models.ReportList;
import ku.cs.app.services.ReportListFileDataSource;

import java.util.ArrayList;

public class TestWriteReport {
    public static void main(String[] args) {
        ReportListFileDataSource dataSource = new ReportListFileDataSource("data","testReport.csv");
        ArrayList<String> list = new ArrayList<>();
        InappropriateUser u1 = new InappropriateUser(1);

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
