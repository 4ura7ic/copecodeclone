package ku.cs;

import ku.cs.app.models.InappropriateUser;
import ku.cs.app.models.list.InappropriateUserList;
import ku.cs.app.services.InappropriateUserListFileDataSource;

import java.util.ArrayList;

public class TestWriteReport {
    public static void main(String[] args) {
        InappropriateUserListFileDataSource dataSource = new InappropriateUserListFileDataSource("data","testReport.csv");
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        InappropriateUser u1 = new InappropriateUser("auratic_uel",1);
        list1.add("trolling");
        u1.addAllInappropriateActions(list1);
        InappropriateUser u2 = new InappropriateUser("asdf",2);
        list2.add("trolling");
        list2.add("spamming");
        u2.addAllInappropriateActions(list2);

        InappropriateUserList iul = new InappropriateUserList();
        iul.addUser(u1);
        iul.addUser(u2);
        dataSource.writeData(iul);
        System.out.println(iul.toString());
//        ReportList reportList = new ReportList();
//        reportList.addReport(rp1);
//        reportList.addReport(rp2);
//        dataSource.writeData(reportList);
//        System.out.println(reportList.toString());
//        System.out.println(rp1.getVotedUser());
//        for (int i = 0; i < rp1.getVotedUser().toArray().length;i++){
//            String rp[] = rp1.getVotedUser().toArray(new String[0]);
//            System.out.println(rp[i]);
//        }

    }
}
