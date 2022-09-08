package ku.cs.app.controllers.accountOrganizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.app.models.UserData;
import ku.cs.app.models.UserDataList;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.UserDataListFileDataSource;
import ku.cs.app.services.UserDataListHardCodeDataSource;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private TextField userName;
    @FXML
    private PasswordField userPassword;
    @FXML
    private Label errorMsgLabel;
    private UserData[] user;

    public int userIndex;


    public void initialize() {
//        UserDataListHardCodeDataSource dataSource = new UserDataListHardCodeDataSource();
//        dataList = dataSource.getDataList();
//        user = new UserData[dataList.dataListSize()];
//        user = dataList.getAllData().toArray(new UserData[0]);
    }

    @FXML
    public void handleUserLogin(ActionEvent actionEvent) {
        DataSource<UserDataList> dataSource = new UserDataListFileDataSource("data","user.csv");
        UserDataList list = dataSource.readData();
        UserData[] user = list.getAllData().toArray(new UserData[0]);
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
        boolean correctPassword = false;
        errorMsgLabel.setText("");

        for (int i = 0; i < list.dataListSize(); i++) {
            if (user[i].getUserName().equals(userName.getText())) {
                isUserExist = true;
                if (userPassword.getText().equals(user[i].getPassword())) {
                    correctPassword = true;
                    if(user[i].getRole().equals("user")) {
                        try {
                            FXRouter.goTo("main_user_form",user[i]);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (user[i].getRole().equals("officer")) {
                        try {
                            FXRouter.goTo("main_agent_form",user[i]);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (user[i].getRole().equals("admin")){
                        try {
                            FXRouter.goTo("main_admin_user",user[i]);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
        if (!isUserExist||!correctPassword) {
            errorMsgLabel.setText("Incorrect username or password");
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
            FXRouter.goTo("register_form");
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

    public int getUserIndex(){
        return userIndex;
    }

}
