package ku.cs.app.controllers.accountOrganizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RequestFormController {
    @FXML
    private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField requestTextField;

    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("login_form");
        } catch (IOException e){
            System.err.println("err ไป login_form ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleSubmitButton(ActionEvent actionEvent){
        try{
            if(usernameTextField.getText()!=""&&passwordTextField.getText()!=""&&requestTextField.getText()!=""){
                com.github.saacsos.FXRouter.goTo("login_form");
            }
        } catch (IOException e){
            System.err.println("err ไป login_form ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
}
