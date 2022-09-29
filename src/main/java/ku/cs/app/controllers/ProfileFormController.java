package ku.cs.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ku.cs.app.models.User;
import ku.cs.app.models.UserList;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.UserDataListFileDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ProfileFormController {
    @FXML private Label usernameLabel;
    @FXML private Label nameLabel;
    @FXML private Label surnameLabel;
    @FXML private ImageView image;
    //--------------------------------------------
    private User user;
    private FileChooser fileChooser;
    private File filePath;
    private String imagePath;
    //--------------------------------------------
    DataSource<UserList> dataSource = new UserDataListFileDataSource("data", "user.csv");
    UserList userList = dataSource.readData();
    String fs = File.separator ;
    //--------------------------------------------
    @FXML
    public void initialize(){
        user = (User) com.github.saacsos.FXRouter.getData();
        if(user.getRole().equals("user")) {
            usernameLabel.setText(user.getUsername());
            nameLabel.setText(user.getName());
            surnameLabel.setText(user.getSurname());
            image.setImage(new Image(System.getProperty("user.dir") + fs + "data" + fs + "images" + fs + user.getPhoto()));
        } else if (user.getRole().equals("admin")) {
            usernameLabel.setText(user.getUsername());
            nameLabel.setText(user.getName());
            surnameLabel.setText(user.getSurname());
        } else if(user.getRole().equals("officer")) {
            usernameLabel.setText(user.getUsername());
            nameLabel.setText(user.getName());
            surnameLabel.setText(user.getSurname());
        }
    }

    public void chooseImageButton(ActionEvent event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        fileChooser = new FileChooser();


        String userDirectoryString = System.getProperty("user.home") + "\\Pictures";
        File userDirectory = new File(userDirectoryString);

        if(!userDirectory.canRead())
        {userDirectory = new File("c:/");}

        fileChooser.setInitialDirectory(userDirectory);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg", "*.jpeg"));

        this.filePath = fileChooser.showOpenDialog(stage);



        try{

            File destDir = new File("data/images");
            if (!destDir.exists()){
                destDir.mkdirs();
            }

//            System.out.println(filePath.getName());
            //Rename
            String[] fileSplit = filePath.getName().split("\\.");
//            System.out.println(fileSplit);
            String filename = user.getUsername() + "." + fileSplit[fileSplit.length -1];
//            System.out.println(filename);
            Path target = FileSystems.getDefault().getPath(destDir.getAbsolutePath() + System.getProperty("file.separator") + filename);

            Files.copy(filePath.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
            imagePath = target.toString();

            user.setPhoto(filename);
//            System.out.println("+++++++" + filename);
            userList.changeImageUser(user);
            image.setImage(new Image(System.getProperty("user.dir") + fs + "data" + fs + "images" + fs + user.getPhoto()));
            DataSource<UserList> dataSource = new UserDataListFileDataSource("data", "user.csv");
            dataSource.writeData(userList);


        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            if(user.getRole().equals("admin"))
                com.github.saacsos.FXRouter.goTo("main_admin_form");
            if(user.getRole().equals("officer"))
                com.github.saacsos.FXRouter.goTo("main_officer_form");
            if(user.getRole().equals("user")){
                com.github.saacsos.FXRouter.goTo("main_user_form");
            }
        } catch (IOException e) {
            System.err.println("err ไป project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    @FXML
    public void handleResetPassword(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("reset_password_form");
        } catch (IOException e) {
            System.err.println("err ไป reset ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
}
