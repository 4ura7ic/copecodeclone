package ku.cs.app.services;

import ku.cs.app.models.UserSuspension;
import ku.cs.app.models.UserSuspensionList;

import java.io.*;

public class UserSuspensionListFileSource implements DataSource<UserSuspensionList>{
    private String directoryName;
    private String fileName;

    public UserSuspensionListFileSource(String directoryName, String fileName) {
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

    public UserSuspensionList readData() {
        UserSuspensionList list = new UserSuspensionList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
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

    public void writeData(UserSuspensionList list) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        Writer writer = null;
        BufferedWriter buffer = null;
        try {
            writer = new FileWriter(file);
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