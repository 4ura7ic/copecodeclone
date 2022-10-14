package ku.cs.app.services;

import ku.cs.app.models.*;
import ku.cs.app.models.list.UserList;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class UserDataListFileDataSource implements DataSource<UserList>{
    //-------------------------------------------- instance

    private String directoryName;
    private String fileName;

    //-------------------------------------------- constructor

    public UserDataListFileDataSource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkIfFileExisted();
    }

    //-------------------------------------------- reader

    public UserList readData() {
        UserList list = new UserList();
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
                if(data[0].equals("user")) {
                    User user = new User(data[0].trim(),
                            data[1].trim(),
                            new Password(data[2].trim()),
                            data[3].trim(),
                            data[4].trim(),
                            data[5].trim()
                    );
                    list.addUser(user);
                } else if (data[0].equals("officer")) {
                    Officer officer = new Officer(
                            data[0].trim(),
                            data[1].trim(),
                            new Password(data[2].trim()),
                            data[3].trim(),
                            data[4].trim(),
                            data[5].trim(),
                            data[6].trim()
                    );
                    list.addUser(officer);
                } else if(data[0].equals("admin")){
                    Admin admin = new Admin(
                            data[0].trim(),
                            data[1].trim(),
                            new Password(data[2].trim()),
                            data[3].trim(),
                            data[4].trim(),
                            data[5].trim()
                    );
                    list.addUser(admin);
                }
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

    public void writeData(UserList list) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        Writer writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file, StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);

            for (Object obj : list.getAllData()) {
                if(((User)obj).getRole().equals("user")||((User)obj).getRole().equals("admin")){
                    User user = (User)obj;
                    String line = user.getRole() + ","
                        + user.getUsername() + ","
                        + user.getPassword() + ","
                        +user.getName() + ","
                        +user.getSurname() + ","
                        +user.getPhoto();
                    buffer.append(line);
                    buffer.newLine();
                    continue;
                }if (((Officer)obj).getRole().equals("officer")) {
                    Officer officer = (Officer) obj;
                    String line = officer.getRole() + ","
                            + officer.getUsername() + ","
                            + officer.getPassword() + ","
                            + officer.getName() + ","
                            + officer.getSurname() + ","
                            + officer.getPhoto() + ","
                            + officer.getInCharge();
                    buffer.append(line);
                    buffer.newLine();
                    continue;
                }

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