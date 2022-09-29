package ku.cs.app.services;

import ku.cs.app.models.Officer;
import ku.cs.app.models.OfficerList;

public class OfficerListFileHardCodeDataSource {
    private OfficerList officerList;

    public OfficerListFileHardCodeDataSource(){
        officerList = new OfficerList();
        readOfficerList();

    }

    public void readOfficerList(){

        Officer alphaOfficer = new Officer();
        alphaOfficer.setName("AlphaOfficer");
        alphaOfficer.setSurname("SurnameAlphaOfficer");
        alphaOfficer.setPassword("AlphaOfficer1150");
        alphaOfficer.setUsername("AlphaTestOfficer");

        Officer betaOfficer = new Officer();
        betaOfficer.setName("BetaOfficer");
        betaOfficer.setSurname("SurnameBetaOfficer");
        betaOfficer.setPassword("BetaOfficer1150");
        betaOfficer.setUsername("BetaTestOfficer");

        officerList.addOfficer(alphaOfficer);
        officerList.addOfficer(betaOfficer);

    }
}
