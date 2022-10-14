package ku.cs.app.services;

import ku.cs.app.models.UserSuspension;
import ku.cs.app.models.list.UserSuspensionList;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class UserSuspensionListFileSource implements DataSource<UserSuspensionList>{
    //-------------------------------------------- instance
    private String directoryName;
    private String fileName;

    //-------------------------------------------- constructor

    public UserSuspensionListFileSource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkIfFileExisted();
    }

    //-------------------------------------------- reader

    public UserSuspensionList readData() {
        UserSuspensionList list = new UserSuspensionList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file, StandardCharsets.UTF_8);
            buffer = new BufferedReader(reader);

            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                UserSuspension user = new UserSuspension(
                        data[0].trim(),
                        data[1].trim(),
                        Integer.parseInt(data[2].trim())
                );
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
    }

    //-------------------------------------------- writer

    public void writeData(UserSuspensionList list) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        Writer writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file, StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);

            for (UserSuspension user : list.getAllData()) {
                String line = user.getUsername() + ","
                        + user.getReason() + "," + user.getLoginAttempt();

                buffer.append(line);
                buffer.newLine();
            }

            buffer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //-------------------------------------------- method

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

    public void clearData() {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        Writer writer = null;

        try {
            writer = new FileWriter(file, StandardCharsets.UTF_8);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
