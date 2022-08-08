package ku.cs.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ku.cs.app.models.UserData;
import ku.cs.app.models.UserDataList;
import ku.cs.app.services.UserDataListHardCodeDataSource;

import java.io.IOException;

public class ResetPasswordFormController {

    @FXML
    private Label errorMsgLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField currentPasswordTextField;
    @FXML
    private TextField newPasswordTextField;
    @FXML
    private TextField confirmPasswordTextField;

    private UserDataList dataList;
    private UserData[] user;

    public void initialize() {
        UserDataListHardCodeDataSource dataSource = new UserDataListHardCodeDataSource();
        dataList = dataSource.getDataList();
        user = new UserData[dataList.dataListSize()];
        user = dataList.getAllData().toArray(new UserData[0]);
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

    @FXML
    public void handleResetButton(ActionEvent actionEvent) {

        boolean isUserExist = false;
        errorMsgLabel.setText("");

        for (int i = 0; i < dataList.dataListSize(); i++) {
            if (user[i].getUserName().equals(usernameTextField.getText())) {
                isUserExist = true;
                if (currentPasswordTextField.getText().equals(user[i].getPassword())) {
                    if (!newPasswordTextField.getText().equals("") && newPasswordTextField.getText().equals(confirmPasswordTextField.getText())) {

                        System.out.println("password before: " + user[i].getPassword()); //password before reset

                        user[i].setPassword(newPasswordTextField.getText());

                        System.out.println("password after: " + user[i].getPassword()); //password after reset

                        if (!user[i].getErrorMsg().equals("")) {
                            errorMsgLabel.setText(user[i].getErrorMsg());
                            clearPasswordTextField();

                        } else {
                            try {
                                com.github.saacsos.FXRouter.goTo("project");
                            } catch (IOException e) {
                                System.err.println("err ไป project ไม่ได้");
                                System.err.println("ให้ตรวจสอบการกําหนด route");
                            }
                        }
                    } else if (newPasswordTextField.getText().equals("")) {
                        errorMsgLabel.setText("Please enter new password.");
                        clearPasswordTextField();

                    } else {
                        errorMsgLabel.setText("New password does not match.");
                        clearPasswordTextField();

                    }

                } else {
                    errorMsgLabel.setText("Incorrect current password.");
                    clearPasswordTextField();

                }
            }
        }
        if (!isUserExist) {
            errorMsgLabel.setText("This user does not exist.");
            clearAllTextField();
        }
    }

    private void clearPasswordTextField() {
        currentPasswordTextField.clear();
        newPasswordTextField.clear();
        confirmPasswordTextField.clear();
    }

    private void clearAllTextField() {
        usernameTextField.clear();
        currentPasswordTextField.clear();
        newPasswordTextField.clear();
        confirmPasswordTextField.clear();
    }

}
