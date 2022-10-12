package ku.cs.app.services;

import ku.cs.app.models.*;
import java.io.*;
import java.util.ArrayList;

public class InappropriateUserListFileDataSource implements DataSource<InappropriateUserList>{
    private String directoryName;
    private String fileName;


    public InappropriateUserListFileDataSource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkIfFileExisted();
    }
    public InappropriateUserList readData(){
        InappropriateUserList list = new InappropriateUserList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split("\\|");
                InappropriateUser user = new InappropriateUser(data[0].trim(),
                        Integer.parseInt(data[1].trim()));
                String[] inappropriateActions = data[2].split(",");
                ArrayList<String> allInappropriateActions = new ArrayList<>();
                for (String s: inappropriateActions){
                    allInappropriateActions.add(s);
                }
                user.addAllInappropriateActions(allInappropriateActions);
                list.addUser(user);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    };
    public void writeData(InappropriateUserList list) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        Writer writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            for(InappropriateUser user: list.getInappropriateUserList()){
                String line = user.getUsername() + "|"
                        + user.getInappropriateActivityCount() + "|";
                for (String s: user.getInappropriateActions()) {
                    line += s + ",";
                }
                buffer.append(line);
                buffer.newLine();
            }
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void clearData(){
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

}
