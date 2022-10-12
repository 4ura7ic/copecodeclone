package ku.cs.app.controllers.accountOrganizer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ku.cs.app.models.*;
import ku.cs.app.services.*;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class UserSuspensionFormController {
    //-------------------------------------------- FXML

    @FXML private ListView<User> dataListView;
    @FXML private ListView<Report> reportListView;
    @FXML private ListView<String> inappropriateActivitiesListView;
    @FXML private Label statusLabel;
    @FXML private TextField reasonTextField;
    @FXML private Label amntLabel;
    @FXML private Label loginAttemptCnt;
    @FXML private Label reasonLabel;
    @FXML private Label errorMsgLabel;

    //-------------------------------------------- noModifier

    private User user;
     private DataSource<UserList> userListDataSource;
     private DataSource<ReportList> reportListDataSource;
     private DataSource<InappropriateUserList> inappropriateUserListDataSource;
     private DataSource<UserSuspensionList> userSuspensionListDataSource;

     private UserList userList;
     private ReportList reportList;
     private InappropriateUserList inappropriateUserList;
     private UserSuspensionList userSuspensionList;
     private UserSuspension soonToBeSuspendedUser;



    //-------------------------------------------- initialize

    @FXML
    public void initialize() {
        user = (User) FXRouter.getData();

        userListDataSource = new UserDataListFileDataSource("data","user.csv");
        userList = userListDataSource.readData();

        reportListDataSource = new ReportListFileDataSource("data", "report.csv");
        reportList = reportListDataSource.readData();

        inappropriateUserListDataSource = new InappropriateUserListFileDataSource("data",  "inappropriateUser.csv");
        inappropriateUserList = inappropriateUserListDataSource.readData();

        userSuspensionListDataSource = new UserSuspensionListFileSource("data", "suspendedUser.csv");
        userSuspensionList = userSuspensionListDataSource.readData();

        initializeLabel();
        showUserListView();
        handleSelectedListView();

    }

    //-------------------------------------------- handle

    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("main_admin_form");
        } catch (IOException e){
            System.err.println("err ไป project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleSelectedListView(){
        dataListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<User>() {
                    @Override
                    public void changed(ObservableValue<? extends User> observableValue, User oldUser, User newUser) {
                        errorMsgLabel.setText("");
                        if (inappropriateUserList.checkIfExist(newUser.getUsername())){
                            soonToBeSuspendedUser = new UserSuspension(newUser.getUsername());
                            InappropriateUser user = inappropriateUserList.returnObject(newUser.getUsername());
                            amntLabel.setText(Integer.toString(user.getInappropriateActivityCount()));
                        }
                        else {
                            soonToBeSuspendedUser = null;
                            amntLabel.setText("0");
                        }
                        if (userSuspensionList.checkIfSuspended(newUser.getUsername())) {
                            UserSuspension user = userSuspensionList.returnSuspendedUser(newUser.getUsername());
                            statusLabel.setText("Suspended");
                            reasonLabel.setText(user.getReason());
                            loginAttemptCnt.setText(Integer.toString(user.getLoginAttempt()));
                        }
                        else {
                            statusLabel.setText("Active");
                            reasonLabel.setText("");
                            loginAttemptCnt.setText("");
                        }
                        showReportListView(newUser.getUsername());
                        showInappropriateActivitiesListView(newUser.getUsername());
                        System.out.println(newUser + "is selected");
                    }
                }
        );
    }

    @FXML
    public void handleSuspendButton(ActionEvent actionEvent){
        if (soonToBeSuspendedUser == null) {
            errorMsgLabel.setText("You can't suspend user without inappropriate activities.");
            reasonTextField.clear();
        }
        else if (userSuspensionList.checkIfSuspended(soonToBeSuspendedUser.getUsername())) {
            errorMsgLabel.setText("You can't suspend an already suspended user.");
            reasonTextField.clear();
        }
        else if (reasonTextField.getText().equals("")) {
            errorMsgLabel.setText("Enter reason before you suspend someone.");
        }
        else {
            soonToBeSuspendedUser.setReason(reasonTextField.getText());
            userSuspensionList.addUser(soonToBeSuspendedUser);
            userSuspensionListDataSource.writeData(userSuspensionList);
            reasonTextField.clear();
            errorMsgLabel.setText("Suspension complete.");
        }

    }
    @FXML
    public void handleRestoreButton(ActionEvent actionEvent) {
        if (soonToBeSuspendedUser == null){
            errorMsgLabel.setText("You can't restore an active account.");
        }
        else if (userSuspensionList.checkIfSuspended(soonToBeSuspendedUser.getUsername())) {
            UserSuspension user = userSuspensionList.returnSuspendedUser(soonToBeSuspendedUser.getUsername());
            userSuspensionList.removeUser(user);
            userSuspensionListDataSource.writeData(userSuspensionList);
            errorMsgLabel.setText("Restore complete.");
        }
    }

    //-------------------------------------------- method

    private void showUserListView(){
        userList.removeUser(userList.returnUserObject(user.getUsername()));
        dataListView.getItems().addAll(userList.getAllData());
        dataListView.refresh();
    }
    private void showReportListView(String username){
        reportListView.getItems().clear();
        reportListView.getItems().addAll(reportList.returnUserReport(username));
        reportListView.refresh();
    }
    private void showInappropriateActivitiesListView(String username) {
        inappropriateActivitiesListView.getItems().clear();
        inappropriateActivitiesListView.getItems().addAll(inappropriateUserList.returnInappropriateUserActivities(username));
        inappropriateActivitiesListView.refresh();
    }

    private void initializeLabel() {
        amntLabel.setText("");
        statusLabel.setText("");
        reasonLabel.setText("");
        loginAttemptCnt.setText("");
        errorMsgLabel.setText("");
    }

}
