package ku.cs.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ku.cs.app.models.Admin;
import ku.cs.app.models.Officer;
import ku.cs.app.models.User;
import ku.cs.app.models.UserList;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.ImageDataSource;
import ku.cs.app.services.UserDataListFileDataSource;
import com.github.saacsos.FXRouter;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ProfileFormController {
    //-------------------------------------------- FXML
    @FXML private Label usernameLabel;
    @FXML private Label nameLabel;
    @FXML private Label surnameLabel;
    @FXML private ImageView image;

    //-------------------------------------------- private

    private User user;

    //-------------------------------------------- noModifier

    DataSource<UserList> dataSource = new UserDataListFileDataSource("data", "user.csv");
    UserList userList = dataSource.readData();
    String fs = File.separator ;

    private ImageDataSource getImage;

    //-------------------------------------------- FXML

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
            image.setImage(new Image(System.getProperty("user.dir") + fs + "data" + fs + "images" + fs + "admin" + fs + user.getPhoto()));
        } else if(user.getRole().equals("officer")) {
            usernameLabel.setText(user.getUsername());
            nameLabel.setText(user.getName());
            surnameLabel.setText(user.getSurname());
            image.setImage(new Image(System.getProperty("user.dir") + fs + "data" + fs + "images" + fs + "officer" + fs + user.getPhoto()));
        }
    }

    //-------------------------------------------- handle

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            if(user.getRole().equals("admin"))
                FXRouter.goTo("main_admin_form");
            if(user.getRole().equals("officer"))
                FXRouter.goTo("main_officer_form");
            if(user.getRole().equals("user")){
                FXRouter.goTo("main_user_form");
            }
        } catch (IOException e) {
            System.err.println("err ไป project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    @FXML
    public void handleResetPassword(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("reset_password_form");
        } catch (IOException e) {
            System.err.println("err ไป reset ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    public void chooseImageButton(ActionEvent event){
        getImage = new ImageDataSource();
        getImage.changeImage(user);
        if (user.getRole().equals("user")) {
            image.setImage(new Image(System.getProperty("user.dir") + fs + "data" + fs + "images" + fs + user.getPhoto()));
        } else if (user.getRole().equals("officer")) {
            image.setImage(new Image(System.getProperty("user.dir") + fs + "data" + fs + "images" + fs + "officer" + fs + user.getPhoto()));
        } else if (user.getRole().equals("admin")) {
            image.setImage(new Image(System.getProperty("user.dir") + fs + "data" + fs + "images" + fs + "admin" + fs + user.getPhoto()));
        }

        }
    }

