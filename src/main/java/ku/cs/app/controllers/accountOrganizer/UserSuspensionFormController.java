package ku.cs.app.controllers.accountOrganizer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import ku.cs.app.models.*;
import ku.cs.app.models.list.*;
import ku.cs.app.services.*;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class UserSuspensionFormController {
    //-------------------------------------------- FXML

    @FXML private ListView<User> dataListView;
    @FXML private ListView<Report> reportListView;
    @FXML private ListView<String> inappropriateActivitiesListView;
    @FXML private Pane infoPane;
    @FXML private Label statusLabel;
    @FXML private TextField reasonTextField;
    @FXML private Label amountLabel;
    @FXML private Label loginAttemptCount;
    @FXML private Label reasonLabel;
    @FXML private Label errorMsgLabel;
    @FXML private Label requestLabel;

    //-------------------------------------------- noModifier

    private User user;
     private DataSource<UserList> userListDataSource;
     private DataSource<ReportList> reportListDataSource;
     private DataSource<InappropriateUserList> inappropriateUserListDataSource;
     private DataSource<UserSuspensionList> userSuspensionListDataSource;
     private DataSource<UserRequestList> userRequestListDataSource;

     private UserList userList;
     private ReportList reportList;
     private InappropriateUserList inappropriateUserList;
     private UserSuspensionList userSuspensionList;
     private UserSuspension soonToBeSuspendedUser;
     private Report soonToBeRemovedReport;
     private UserRequestList userRequestList;
     private UserRequest userRequest;
     private User selectedUser;



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

        userRequestListDataSource = new UserRequestListFileDataSource("data", "userRequest.csv");
        userRequestList = userRequestListDataSource.readData();

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
                        selectedUser = newUser;
                        requestLabel.setText("");
                        errorMsgLabel.setText("");
                        if (inappropriateUserList.checkIfExist(newUser.getUsername())){
                            soonToBeSuspendedUser = new UserSuspension(newUser.getUsername());
                            InappropriateUser user = inappropriateUserList.returnObject(newUser.getUsername());
                            amountLabel.setText(Integer.toString(user.getInappropriateActivityCount()));
                        }
                        else {
                            soonToBeSuspendedUser = null;
                            amountLabel.setText("0");
                        }
                        if (userSuspensionList.checkIfExist(newUser.getUsername())) {
                            UserSuspension user = userSuspensionList.returnObject(newUser.getUsername());
                            if (userRequestList.checkIfExist(newUser.getUsername())) {
                                userRequest = userRequestList.returnObject(newUser.getUsername());
                                requestLabel.setText(userRequest.getConfession());
                            }
                            statusLabel.setText("Suspended");
                            reasonLabel.setText(user.getReason());
                            loginAttemptCount.setText(Integer.toString(user.getLoginAttempt()));
                        }
                        else {
                            statusLabel.setText("Active");
                            reasonLabel.setText("");
                            loginAttemptCount.setText("");
                        }
                        showReportListView(newUser.getUsername());
                        showInappropriateActivitiesListView(newUser.getUsername());
                        infoPane.setVisible(true);
                    }
                }
        );
        reportListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Report>() {
                    @Override
                    public void changed(ObservableValue<? extends Report> observableValue, Report report, Report newReport) {
                        soonToBeRemovedReport = newReport;
                    }
                }
        );

    }

    @FXML
    public void handleSuspendButton(ActionEvent actionEvent){
        if (soonToBeSuspendedUser == null) {
            errorMsgLabel.setText("You can't suspend a user without an inappropriate activities.");
            reasonTextField.clear();
        }
        else if (userSuspensionList.checkIfExist(soonToBeSuspendedUser.getUsername())) {
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
        else if (userSuspensionList.checkIfExist(soonToBeSuspendedUser.getUsername())) {
            UserSuspension user = userSuspensionList.returnObject(soonToBeSuspendedUser.getUsername());
            if (userRequestList.checkIfExist(soonToBeSuspendedUser.getUsername())) {
                userRequest = userRequestList.returnObject(soonToBeSuspendedUser.getUsername());

                userSuspensionList.removeUser(user);
                userRequestList.removeUser(userRequest);
                userRequestListDataSource.writeData(userRequestList);
            }
            else {
                userSuspensionList.removeUser(user);
            }
            userSuspensionListDataSource.writeData(userSuspensionList);
            errorMsgLabel.setText("Restore complete.");

        }
    }

    @FXML
    public void handleRemoveReport(ActionEvent actionEvent) {
        if (soonToBeRemovedReport == null) {
            errorMsgLabel.setText("Please select the report to remove.");
        }
        else {
            reportList.removeReport(soonToBeRemovedReport);
            showReportListView(selectedUser.getUsername());
            reportListDataSource.writeData(reportList);
        }
    }

    //-------------------------------------------- method

    private void showUserListView(){
        userList.removeUser();
        dataListView.getItems().addAll(userList.getAllData());
        dataListView.refresh();
    }
    private void showReportListView(String username){
        reportListView.getItems().clear();
        reportListView.getItems().addAll(reportList.returnObject(username));
        reportListView.refresh();
    }
    private void showInappropriateActivitiesListView(String username) {
        inappropriateActivitiesListView.getItems().clear();
        inappropriateActivitiesListView.getItems().addAll(inappropriateUserList.returnInappropriateUserActivities(username));
        inappropriateActivitiesListView.refresh();
    }

    private void initializeLabel() {
        amountLabel.setText("");
        statusLabel.setText("");
        reasonLabel.setText("");
        loginAttemptCount.setText("");
        errorMsgLabel.setText("");
        requestLabel.setText("");
        infoPane.setVisible(false);
    }

}
