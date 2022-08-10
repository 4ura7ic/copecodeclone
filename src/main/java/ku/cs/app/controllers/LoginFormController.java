package ku.cs.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.app.models.UserData;
import ku.cs.app.models.UserDataList;
import ku.cs.app.services.UserDataListHardCodeDataSource;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private TextField userName;
    @FXML
    private PasswordField userPassword;
    @FXML
    private Label errorMsgLabel;

    private UserDataList dataList;
    private UserData[] user;


    public void initialize() {
        UserDataListHardCodeDataSource dataSource = new UserDataListHardCodeDataSource();
        dataList = dataSource.getDataList();
        user = new UserData[dataList.dataListSize()];
        user = dataList.getAllData().toArray(new UserData[0]);
    }

    @FXML
    public void handleUserLogin(ActionEvent actionEvent) {
//        boolean isUserExist = false;
//
//        for (int i = 0; i < dataList.dataListSize(); i++) {
//            if (user[i].getUserName().equals(userName.getText())) {
//
//                if (user[i].getPassword().equals(userPassword.getText())) {
//                    try {
//                        com.github.saacsos.FXRouter.goTo("main_form");
//                    } catch (IOException e) {
//                        System.err.println("err ไป user_list ไม่ได้");
//                        System.err.println("ให้ตรวจสอบการกําหนด route");
//                    }
//                } else {
//                    System.out.println("password wrong");
//                }
//            } else {
//                System.out.println("username wrong");
//            }
//            if (!isUserExist) {
//                errorMsgLabel.setText("This user does not exist.");
//                clearAllTextField();
//            }
//        }


        boolean isUserExist = false;
        errorMsgLabel.setText("");

        for (int i = 0; i < dataList.dataListSize(); i++) {
            if (user[i].getUserName().equals(userName.getText())) {
                isUserExist = true;
                if (userPassword.getText().equals(user[i].getPassword())) {
                    try {
                        com.github.saacsos.FXRouter.goTo("main_form");
                    } catch (IOException e) {
                        System.err.println("err ไป user_list ไม่ได้");
                        System.err.println("ให้ตรวจสอบการกําหนด route");
                    }
                } else {
                    System.out.printf("wrong password");
                }
            }
        }
        if (!isUserExist) {
            errorMsgLabel.setText("Incorrect username or password");
            System.out.println("user does not exist");
            clearAllTextField();
        }

    }
    private void clearPasswordTextField() {
        userPassword.clear();
    }
    private void clearAllTextField() {
        userPassword.clear();
        userName.clear();
    }
    @FXML
    public void handleUserRegister(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("register_form");
        } catch (IOException e){
            System.err.println("err ไป register ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
//    @FXML
//    public void handleUserReset(ActionEvent actionEvent){
//        try{
//            com.github.saacsos.FXRouter.goTo("reset_password_form");
//        } catch (IOException e){
//            System.err.println("err ไป reset_password_form ไม่ได้");
//            System.err.println("ให้ตรวจสอบการกําหนด route");
//        }
//    }

    public void handleCredit(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("credit");
        } catch (IOException e){
            System.err.println("err ไป credit ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

}
