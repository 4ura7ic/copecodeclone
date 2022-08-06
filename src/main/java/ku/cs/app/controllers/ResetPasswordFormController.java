package ku.cs.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ku.cs.app.models.Password;

public class ResetPasswordFormController {

    private Password p1;
    @FXML private Label errorMsgLabel;
    @FXML private TextField usernameTextField;
    @FXML private TextField oldPasswordTextField;
    @FXML private TextField newPasswordTextField;
    @FXML private TextField confirmPasswordTextField;


    public void initialize() {
        p1 = new Password();
        p1.setPassword("TestTest123456");
    }

    @FXML
    public void handleResetButton(ActionEvent actionEvent) {
        if (checkOldPassword(oldPasswordTextField.getText())) {
            String newPassword = newPasswordTextField.getText();
            String confirmPassword = confirmPasswordTextField.getText();
            if (newPassword.equals(confirmPassword)) {
                p1.setPassword(newPassword);
                String errorMsg = p1.getErrorMsg();
                errorMsgLabel.setText(errorMsg);
                clearAllTextField();
                System.out.println("Password: " + p1.getPassword()); //debug

            }else {
                errorMsgLabel.setText("New password doesn't match.");
                clearAllTextField();
                System.out.println("Password: " + p1.getPassword());} //debug
        }else {
            errorMsgLabel.setText("Old Password doesn't match current password in the database.");
            clearAllTextField();
            System.out.println("Password: " + p1.getPassword()); //debug
        }



    }

    public boolean checkOldPassword(String password) {
        return password.equals(p1.getPassword());
    }

    public void clearAllTextField() {
        usernameTextField.clear();
        oldPasswordTextField.clear();
        newPasswordTextField.clear();
        confirmPasswordTextField.clear();
    }

}
