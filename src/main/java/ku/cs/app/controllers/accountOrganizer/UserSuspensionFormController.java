package ku.cs.app.controllers.accountOrganizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ku.cs.app.models.User;
import ku.cs.app.models.UserList;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.UserDataListFileDataSource;

import java.io.IOException;

public class UserSuspensionFormController {
    //-------------------------------------------- FXML

    @FXML private ListView<User> dataListView;
    @FXML private Label statusLabel;
    @FXML private TextField reasonTextField;
    @FXML private Label amntLabel;
    @FXML private Label loginAttemptCnt;
    @FXML private Label reasonLabel;
    @FXML private Label errorMsgLabel;

    //-------------------------------------------- noModifier

    DataSource<UserList> dataSource = new UserDataListFileDataSource("data","user.csv");
    UserList list = dataSource.readData();

    //-------------------------------------------- initialize

    @FXML
    public void initialize() {
        showListView();

    }

    //-------------------------------------------- handle

    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("main_admin_form");
        } catch (IOException e){
            System.err.println("err ไป project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleSuspendButton(ActionEvent actionEvent){

    }
    @FXML
    public void handleRestoreButton(ActionEvent actionEvent) {

    }

    //-------------------------------------------- method

    private void showListView(){
        dataListView.getItems().addAll(list.getAllData());
        dataListView.refresh();
    }

}
