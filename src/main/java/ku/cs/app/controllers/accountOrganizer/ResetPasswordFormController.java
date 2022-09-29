package ku.cs.app.controllers.accountOrganizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ku.cs.app.models.User;
import ku.cs.app.models.UserList;
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
    private UserList list;
    private DataSource<UserList> dataSource;
    public void initialize() {
        dataSource = new UserDataListFileDataSource("data", "user.csv");
        list = dataSource.readData();
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
        errorMsgLabel.setText("");
        if (list.checkIfUserExisted(usernameTextField.getText())) {

            User user = list.returnUserObject(usernameTextField.getText());

            if (user.checkIfInputPasswordCorrect(user, currentPasswordTextField.getText())){

                if (user.checkForDuplicatePassword(newPasswordTextField.getText())) {

                    if (user.checkIfNewPasswordEligible(newPasswordTextField.getText(), confirmPasswordTextField.getText())) {

                        user.setPassword(newPasswordTextField.getText());

                        if (user.getErrorMsg().equals("")) {

                            dataSource.clearData();
                            dataSource.writeData(list);

                            try {
                                com.github.saacsos.FXRouter.goTo("login_form");
                            } catch (IOException e) {
                                System.err.println("err ไป project ไม่ได้");
                                System.err.println("ให้ตรวจสอบการกําหนด route");
                                e.printStackTrace();
                            }
                        } else {
                            errorMsgLabel.setText(user.getErrorMsg());
                        }
                    }
                    clearPasswordTextField();
                    errorMsgLabel.setText(user.getErrorMsg());
                }
                clearPasswordTextField();
                errorMsgLabel.setText(user.getErrorMsg());
            }
            clearPasswordTextField();
            errorMsgLabel.setText(user.getErrorMsg());


        }else {
            clearAllTextField();
            errorMsgLabel.setText("This user does not exist.\n");
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
