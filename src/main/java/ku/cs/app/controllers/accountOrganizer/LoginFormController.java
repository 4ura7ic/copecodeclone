package ku.cs.app.controllers.accountOrganizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ku.cs.app.models.*;
import ku.cs.app.models.list.ActivityLog;
import ku.cs.app.models.list.UserList;
import ku.cs.app.models.list.UserSuspensionList;
import ku.cs.app.services.ActivityLogDataSource;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.UserDataListFileDataSource;
import com.github.saacsos.FXRouter;
import ku.cs.app.services.UserSuspensionListFileSource;

import java.io.IOException;
import java.util.ArrayList;

public class LoginFormController {
    //-------------------------------------------- FXML

    @FXML
    private TextField userName;
    @FXML
    private PasswordField userPassword;
    @FXML
    private Label errorMessageLabel;

    //-------------------------------------------- private

    private DataSource<UserList> dataSource;
    private UserList list;
    private DataSource<ActivityLog> activityLog;
    private ActivityLog log;
    private DataSource<UserSuspensionList> susSource;
    private UserSuspensionList susList;

    //-------------------------------------------- initialize

    public void initialize() {

        dataSource = new UserDataListFileDataSource("data","user.csv");
        list = dataSource.readData();

        activityLog = new ActivityLogDataSource("data" ,"activityLog.csv");
        log = activityLog.readData();

        susSource = new UserSuspensionListFileSource("data", "suspendedUser.csv");
        susList = susSource.readData();

    }

    //-------------------------------------------- handle

    @FXML
    public void handleUserLogin(ActionEvent actionEvent){

        Activity act;

        act = new Activity();
        act.setDateTime();
        act.setActivity("| Login attempt from user: " + userName.getText() + " | ");

        if (list.checkIfExist(userName.getText())){
            User user = list.returnObject(userName.getText());
            if(user.getUsername().equals(userName.getText())) {
                if (user.checkIfInputPasswordCorrect(user, userPassword.getText())) {

                    if (susList.checkIfExist(user.getUsername())) {
                        try {
                            ArrayList<Object> o = new ArrayList<>();
                            UserSuspension suspendedUser = susList.returnObject(user.getUsername());
                            suspendedUser.addLoginAttempt();
                            act.setMessage("Failed - User suspended. | Login attempt: " + suspendedUser.getLoginAttempt() + "time(s).");
                            log.addLog(act);
                            o.add(user);
                            o.add(suspendedUser);
                            FXRouter.goTo("suspended_user", o);
                        }catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    } else {
                        if (user.getRole().equals("user")) {
                            try {
                                act.setMessage("Success.");
                                log.addLog(act);
                                FXRouter.goTo("main_user_form", user);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else if (user.getRole().equals("officer")) {
                            try {
                                act.setMessage("Success.");
                                log.addLog(act);
                                activityLog.clearData();
                                FXRouter.goTo("main_officer_form", user);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else if (user.getRole().equals("admin")) {
                            try {
                                act.setMessage("Success.");
                                log.addLog(act);
                                FXRouter.goTo("main_admin_form", user);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
                else {
                    userPassword.clear();
                    act.setMessage("Failed - Wrong password.");
                    log.addLog(act);
                    errorMessageLabel.setText("Wrong password.");
                }
            }
        }
        else {
            clearAllTextField();
            act.setMessage("Failed - user not exist.");
            log.addLog(act);
            errorMessageLabel.setText("User not exist.");
        }
        activityLog.writeData(log);
        susSource.writeData(susList);
    }
    @FXML
    public void handleUserRegister(ActionEvent actionEvent){
        try{
            FXRouter.goTo("register_form");
        } catch (IOException e){
            System.err.println("err ไป register ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace();
        }
    }
    public void handleCredit(ActionEvent actionEvent){
        try{
            FXRouter.goTo("credit");
        } catch (IOException e){
            System.err.println("err ไป credit ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    //-------------------------------------------- method

    private void clearAllTextField() {
        userPassword.clear();
        userName.clear();
    }

}
