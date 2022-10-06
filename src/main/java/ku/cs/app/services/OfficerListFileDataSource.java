package ku.cs.app.services;

import ku.cs.app.models.Officer;
import ku.cs.app.models.OfficerList;
import ku.cs.app.models.Password;
import ku.cs.app.models.User;

import java.io.*;

public class OfficerListFileDataSource implements  DataSource<OfficerList>{

    private String directoryName;
    private String fileName;

    public OfficerListFileDataSource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkIfFileExisted();
    }

    private void checkIfFileExisted() {
        File file = new File(directoryName);
        if(!file.exists()) {
            file.mkdirs();
        }

        String filePath = directoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public OfficerList readData(){
        OfficerList list  = new OfficerList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while ((line = buffer.readLine()) != null){
                String[] data = line.split(",");
                if (data[0].equals("officer")) {
                    Officer officer = new Officer(
                            data[0].trim(),
                            new Password(data[1].trim()),
                            data[2].trim(),
                            data[3].trim(),
                            data[4].trim()
                    );
                    list.addOfficer(officer);
                } else if (data[0].equals("user")) {
                    User user = new User(
                            data[0].trim(),
                            data[1].trim(),
                            new Password(data[2].trim()),
                            data[3].trim(),
                            data[4].trim(),
                            data[5].trim()
                    );
//                    list.addOfficer(user);
                }


            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    public void writeData(OfficerList list){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        Writer writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            for (Officer officer : list.getAllData()){
                String line = officer.getRole() + ","
                        + officer.getUsername() + ","
                        + officer.getPassword() + ","
                        + officer.getName() + ","
                        + officer.getSurname() + ","
                        + officer.getInCharge() + ","
                        + officer.getPhoto();

                buffer.append(line);
                buffer.newLine();
            }

            buffer.close();

        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }
    public void clearData() {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        Writer writer = null;

        try {
            writer = new FileWriter(file);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
