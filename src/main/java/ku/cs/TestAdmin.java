package ku.cs;

import ku.cs.app.models.Admin;
import ku.cs.app.models.Officer;
import ku.cs.app.models.OfficerList;
import ku.cs.app.models.Password;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.OfficerListFileDataSource;
import ku.cs.app.services.OfficerListFileHardCodeDataSource;

public class TestAdmin {
    public static void main(String[] args) {


        Admin Akali = new Admin();
        Akali.setName("Akali");
        Akali.setSurname("Jomen");
        Akali.setPassword("LoveRamen");
        Akali.setUserName("akali");


        Akali.createOfficer("newOfficer","IAMOFFICER", new Password("ThisOver9000"),"StromOfficer");

        Officer officer01 = new Officer();
        officer01.setName("TestNameOfficer");
        officer01.setSurname("TestSurOfficer");
        officer01.setPassword("SuperOfficer666");
        officer01.setUserName("OmegaTestOfficer");

        System.out.println(Akali);
        System.out.println(officer01);

        System.out.println("===============================");


    }
}
