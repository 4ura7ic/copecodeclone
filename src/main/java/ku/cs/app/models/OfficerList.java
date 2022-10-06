package ku.cs.app.models;

import java.util.ArrayList;

public class OfficerList {

    private ArrayList<Officer> data;

    public OfficerList(){data = new ArrayList<>();}

    public void addOfficer(Officer info){
        data.add(info);
    }

    public ArrayList<Officer> getAllData(){
        return data;
    }

    public int dataListSize(){return data.size();}

    public String getUsername(int index) {
        return data.get(index).getUsername();
    }

    @Override
    public String toString() {
        return "OfficerList{" +
                "data=" + data +
                '}';
    }

    public boolean checkDuplicateUsername(String officerIn) {
        for(Officer temp: data){
            if(temp.getUsername().equals(officerIn)){
                return false;
            }
        }
        return true;
    }

    public void changeImageOfficer(Officer officer){
        for (User temp:data){
            if(temp.getUsername().equals(officer.getUsername())){

                temp.setPhoto(officer.getPhoto());

            }
        }
    }

}
