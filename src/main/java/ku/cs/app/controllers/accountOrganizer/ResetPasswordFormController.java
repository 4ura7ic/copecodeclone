package ku.cs.app.controllers.accountOrganizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ku.cs.app.models.UserData;
import ku.cs.app.models.UserDataList;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.UserDataListFileDataSource;

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

    private UserDataList list;
    private UserData[] user;

    DataSource<UserDataList> dataSource;


    public void initialize() {
        dataSource = new UserDataListFileDataSource("data", "user.csv");
        list = dataSource.readData();
        user = list.getAllData().toArray(new UserData[0]);
        System.out.println(list);
        for (UserData userData : user) {
            System.out.println("Name: " + userData.getName());
            System.out.println("Surname: " + userData.getSurname());
            System.out.println("Password:" + userData.getPassword());
            System.out.println("Username: " + userData.getUserName());
            System.out.println("Role: " + userData.getRole());
            System.out.println("-------------------------------");
        }
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("main_user_form");
        } catch (IOException e){
            System.err.println("err ไป project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleResetButton(ActionEvent actionEvent) {

        boolean isUserExist = false;
        errorMsgLabel.setText("");

        for (int i = 0; i < list.dataListSize(); i++) {
            if (user[i].getUserName().equals(usernameTextField.getText())) {
                isUserExist = true;
                if (currentPasswordTextField.getText().equals(user[i].getPassword())) {
                    if (!newPasswordTextField.getText().equals("") && newPasswordTextField.getText().equals(confirmPasswordTextField.getText())) {

                        if (newPasswordTextField.getText().equals(user[i].getPassword())) {
                            errorMsgLabel.setText("New password is the same with old password.");
                            System.err.println("New password is the same with old password.");
                            clearPasswordTextField();
                            break;
                        }

                        System.out.println("password before: " + user[i].getPassword()); //password before reset

                        user[i].setPassword(newPasswordTextField.getText());

                        System.out.println("password after: " + user[i].getPassword()); //password after reset

                        if (!user[i].getErrorMsg().equals("")) {
                            errorMsgLabel.setText(user[i].getErrorMsg());
                            clearPasswordTextField();

                        } else {
                            try {
                                com.github.saacsos.FXRouter.goTo("main_user_form");
                            } catch (IOException e) {
                                System.err.println("err ไป project ไม่ได้");
                                System.err.println("ให้ตรวจสอบการกําหนด route");
                            }
                        }
                    } else if (newPasswordTextField.getText().equals("")) {
                        errorMsgLabel.setText("Please enter new password.");
                        System.err.println("Please enter new password.");
                        clearPasswordTextField();

                    }else if (!newPasswordTextField.getText().equals("") && confirmPasswordTextField.getText().equals("")) {
                        errorMsgLabel.setText("Please confirm password.");
                        System.err.println("Please confirm password.");
                        clearPasswordTextField();

                    } else {
                        errorMsgLabel.setText("New password does not match.");
                        System.err.println("New password does not match.");
                        clearPasswordTextField();

                    }

                } else if (currentPasswordTextField.getText().equals("")) {
                    errorMsgLabel.setText("Please enter current password.");
                    System.err.println("Please enter current password.");
                    clearPasswordTextField();
                } else {
                    errorMsgLabel.setText("Incorrect current password.");
                    System.err.println("Incorrect current password.");
                    clearPasswordTextField();

                }
            }
        }
        if (usernameTextField.getText().equals("")) {
            errorMsgLabel.setText("Please enter username.");
            System.err.println("Please enter username.");
        }
        else if (!isUserExist) {
            errorMsgLabel.setText("This user does not exist.");
            System.err.println("This user does not exist.");
            clearAllTextField();
        }

        dataSource.clearData();
        dataSource.writeData(list);
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
