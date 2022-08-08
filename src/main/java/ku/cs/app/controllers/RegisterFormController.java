package ku.cs.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ku.cs.app.models.UserData;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegisterFormController {
    private UserData userDetail;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField surnameTextField;
    @FXML private Label errorMsgLabel;

    @FXML
    public void initialize(){
        System.out.println("initialize MemberCardDetailController");
        userDetail = new UserData();
    }
    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("project");
        } catch (IOException e){
            System.err.println("err ไป project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    public void handleCreateID(ActionEvent actionEvent){

        userDetail.setUserName(usernameTextField.getText());
        userDetail.setName(nameTextField.getText());
        userDetail.setSurname(surnameTextField.getText());
        userDetail.setPassword(passwordTextField.getText());
        String errorMsg = userDetail.getErrorMsg();
        if (errorMsg == "") {

            clearAllTextField();
            try{
                FXRouter.goTo("login");
            } catch (IOException e){
                System.err.println("err ไป project ไม่ได้");
                System.err.println("ให้ตรวจสอบการกําหนด route");
            }

        }
        else {
            passwordTextField.clear();
            errorMsgLabel.setText(errorMsg);
        }



    }

    public void clearAllTextField() {
        usernameTextField.clear();
        passwordTextField.clear();
        nameTextField.clear();
        surnameTextField.clear();
    }
}


