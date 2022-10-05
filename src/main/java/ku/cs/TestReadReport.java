package ku.cs;

import ku.cs.app.models.ReportList;
import ku.cs.app.services.ReportListFileDataSource;

public class TestReadReport {
    public static void main(String[] args) {
        ReportListFileDataSource dataSource = new ReportListFileDataSource("data", "testReport.csv");
        ReportList reportList = dataSource.readData();
        System.out.println(reportList.toString());
    }

}
