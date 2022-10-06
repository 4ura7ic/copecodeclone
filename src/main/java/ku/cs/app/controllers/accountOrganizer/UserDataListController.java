package ku.cs.app.controllers.accountOrganizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.app.models.Report;
import ku.cs.app.models.ReportList;
import ku.cs.app.models.User;
import ku.cs.app.models.UserList;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.ReportListHardCodeSource;
import ku.cs.app.services.UserDataListFileDataSource;
import ku.cs.app.services.UserDataListHardCodeDataSource;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.io.File;
import java.io.IOException;

public class UserDataListController {
    //-------------------------------------------- FXML

    @FXML
    private ListView<User> dataListView;
    @FXML private ImageView image;
    @FXML private Label usernameLabel;
    @FXML private Label nameLabel;
    @FXML private Label surnameLabel;
    @FXML private Label passwordLabel;

    //-------------------------------------------- noModifier

    String fs = File.separator ;
    String url = getClass().getResource("/ku/cs/images/default.jpg").toExternalForm();
    DataSource<UserList> dataSource = new UserDataListFileDataSource("data","user.csv");
    UserList list = dataSource.readData();

    //-------------------------------------------- initialize

    @FXML
    public void initialize(){
        image.setImage(new Image(url));
        showListView();
        clearSelectedUserData();
        handleSelectedListView();
    }

    //-------------------------------------------- handle

    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("main_admin_form");
        } catch (IOException e){
            System.err.println("err ไป main_admin_form ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleActivityLog(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("activity_log");
        } catch (IOException e){
            System.err.println("err ไป formData ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    private void handleSelectedListView(){
        dataListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<User>() {
                    @Override
                    public void changed(ObservableValue<? extends User>
                                                observable,
                                        User oldValue, User newValue) {
                        System.out.println(newValue + " is selected");
                        showSelectedUserData(newValue);
                    }
                });
    }

    //-------------------------------------------- method

    private void showSelectedUserData(User data){
        image.setImage(new Image(System.getProperty("user.dir") + fs + "data" + fs + "images" + fs + data.getPhoto()));
        nameLabel.setText(data.getName());
        surnameLabel.setText(data.getSurname());
        passwordLabel.setText(data.getPassword());
        usernameLabel.setText(data.getUsername());
    }

    private void clearSelectedUserData(){
        nameLabel.setText("");
        surnameLabel.setText("");
        passwordLabel.setText("");
        usernameLabel.setText("");
    }
    private void showListView(){
        dataListView.getItems().addAll(list.getAllData());
        dataListView.refresh();
    }

}
