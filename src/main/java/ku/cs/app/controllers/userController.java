package ku.cs.app.controllers;

import javafx.fxml.FXML;
import ku.cs.app.models.user;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;

public class userController {
    private user userDetail;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField surnameTextField;

    @FXML
    public void initialize(){
        System.out.println("initialize MemberCardDetailController");
        userDetail = new user();
    }

    public void handleCreateID(ActionEvent actionEvent){

        userDetail.setUserName(usernameTextField.getText());
        userDetail.setPassword(passwordTextField.getText());
        userDetail.setName(nameTextField.getText());
        userDetail.setSurname(surnameTextField.getText());

        usernameTextField.clear();
        passwordTextField.clear();
        nameTextField.clear();
        surnameTextField.clear();

    }
}


