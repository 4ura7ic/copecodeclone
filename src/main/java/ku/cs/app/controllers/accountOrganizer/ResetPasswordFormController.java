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
import com.github.saacsos.FXRouter;

public class ResetPasswordFormController {
    //-------------------------------------------- FXML
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

    //-------------------------------------------- private

    private UserList list;
    private DataSource<UserList> dataSource;
    public void initialize() {
        dataSource = new UserDataListFileDataSource("data", "user.csv");
        list = dataSource.readData();
    }

    //-------------------------------------------- handle

    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("login_form");
        } catch (IOException e){
            System.err.println("err ไป project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleResetButton(ActionEvent actionEvent) {
        errorMsgLabel.setText("");
        if (list.checkIfExist(usernameTextField.getText())) {

            User user = list.returnObject(usernameTextField.getText());

            if (user.checkIfInputPasswordCorrect(user, currentPasswordTextField.getText())){

                if (user.checkForDuplicatePassword(newPasswordTextField.getText())) {

                    if (user.checkIfNewPasswordEligible(newPasswordTextField.getText(), confirmPasswordTextField.getText())) {

                        user.setPassword(newPasswordTextField.getText());

                        if (user.getErrorMsg().equals("")) {

                            dataSource.clearData();
                            dataSource.writeData(list);

                            try {
                                FXRouter.goTo("login_form");
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

    //-------------------------------------------- method

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
