package ku.cs.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.app.models.UserData;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileFormController {
    @FXML private Label usernameLabel;
    @FXML private Label nameLabel;
    @FXML private Label surnameLabel;
    private UserData user;
    @FXML private ImageView image;
    String url = getClass().getResource("/ku/cs/images/rickroll.gif").toExternalForm();

    @FXML
    public void initialize(){
        user = (UserData) com.github.saacsos.FXRouter.getData();
        usernameLabel.setText(user.getUserName());
        nameLabel.setText(user.getName());
        surnameLabel.setText(user.getSurname());
        image.setImage(new Image(url));
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("main_user_form");
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
