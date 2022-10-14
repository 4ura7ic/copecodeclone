package ku.cs.app.services;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ku.cs.app.models.Admin;
import ku.cs.app.models.Officer;
import ku.cs.app.models.User;
import ku.cs.app.models.list.UserList;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ImageDataSource {
    //-------------------------------------------- instance

    private File filePath;
    private String imagePath;
    private File destDir;
    String fs = File.separator ;
    private User user;
    DataSource<UserList> dataSource = new UserDataListFileDataSource("data", "user.csv");
    UserList userList = dataSource.readData();

    //-------------------------------------------- method

    public String chooseImage(String fileName, String key){

        String userDirectoryString = System.getProperty("user.home") + "\\Pictures";
        File userDirectory = new File(userDirectoryString);

        if (!userDirectory.canRead()) {
            userDirectory = new File("c:/");
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(userDirectory);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));
        this.filePath = fileChooser.showOpenDialog(new Stage());

        try {
            if(key.equals("user")) {
                destDir = new File("data/images");
                if (!destDir.exists()) {
                    destDir.mkdirs();
                }
            }if(key.equals("officer")){
                destDir = new File("data/images/officer");
                if (!destDir.exists()) {
                    destDir.mkdirs();
                }
            }if (key.equals("admin")){
                destDir = new File("data/images/admin");
                if (!destDir.exists()) {
                    destDir.mkdirs();
                }
            }

            if(filePath!=null) {
                //Rename
                String[] fileSplit = filePath.getName().split("\\.");
                String filename = fileName + "." + fileSplit[fileSplit.length - 1];
                Path target = FileSystems.getDefault().getPath(destDir.getAbsolutePath() + System.getProperty("file.separator") + filename);

                //ก็อบรูปที่เลือกมาใส่ไว้ในไฟล์
                Files.copy(filePath.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                return filename;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "default.jpg";
    }

    public void changeImage(User user){
        String userDirectoryString = System.getProperty("user.home") + "\\Pictures";
        File userDirectory = new File(userDirectoryString);

        if(!userDirectory.canRead())
        {userDirectory = new File("c:/");}

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(userDirectory);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));
        this.filePath = fileChooser.showOpenDialog(new Stage());

        try {

            if(user.getRole().equals("user")) {
                destDir = new File("data/images");
                if (!destDir.exists()) {
                    destDir.mkdirs();
                }
            }if(user.getRole().equals("officer")){
                destDir = new File("data/images/officer");
                if (!destDir.exists()) {
                    destDir.mkdirs();
                }
            }if (user.getRole().equals("admin")){
                destDir = new File("data/images/admin");
                if (!destDir.exists()) {
                    destDir.mkdirs();
                }
            }

            //Rename
            String[] fileSplit = filePath.getName().split("\\.");


            String filename = user.getUsername() + "." + fileSplit[fileSplit.length - 1];

            Path target = FileSystems.getDefault().getPath(destDir.getAbsolutePath() + System.getProperty("file.separator") + filename);

            Files.copy(filePath.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
            imagePath = target.toString();

            user.setPhoto(filename);

            if (user.getRole().equals("user")) {
                userList.changeImageUser(user);
            } else if (user.getRole().equals("officer")) {
                userList.changeImageOfficer((Officer) user);
            } else if (user.getRole().equals("admin")) {
                userList.changeImageAdmin((Admin) user);
            }


            DataSource<UserList> dataSource = new UserDataListFileDataSource("data", "user.csv");
            dataSource.writeData(userList);


        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
