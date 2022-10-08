package ku.cs;

import ku.cs.app.models.InappropriateUserList;
import ku.cs.app.models.Report;
import ku.cs.app.models.ReportList;
import ku.cs.app.services.InappropriateUserListFileDataSource;
import ku.cs.app.services.ReportListFileDataSource;

public class TestReadReport {
    public static void main(String[] args) {
        InappropriateUserListFileDataSource dataSource = new InappropriateUserListFileDataSource("data", "testReport.csv");
        InappropriateUserList list = dataSource.readData();
        System.out.println(list.toString());
//        ReportList reportList = dataSource.readData();
//        System.out.println(reportList.toString());
//        Report r[] = reportList.getAllRpt().toArray(new Report[0]);
//        System.out.println(r[1].getVotedUser().size());
    }

}
