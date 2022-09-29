package ku.cs.app.controllers.accountOrganizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.app.models.Report;
import ku.cs.app.models.ReportList;
import ku.cs.app.services.ReportListHardCodeSource;
import ku.cs.app.services.UserDataListHardCodeDataSource;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.io.IOException;

public class UserDataListController {
    @FXML
    private ListView<UserData> dataListView;
    @FXML
    private ListView<Report> reportListView;
    @FXML private ImageView image;
    @FXML private Label usernameLabel;
    @FXML private Label nameLabel;
    @FXML private Label surnameLabel;
    @FXML private Label passwordLabel;
    @FXML private Label dateLabel;
    @FXML private Label topicLabel;
    String url = getClass().getResource("/ku/cs/images/test.jpg").toExternalForm();

    private UserDataListHardCodeDataSource dataSource;
    private ReportListHardCodeSource rptSource;
    private UserDataList dataList;
    private ReportList rptList;

    @FXML
    public void initialize(){
        image.setImage(new Image(url));
        dataSource = new UserDataListHardCodeDataSource();
        dataList = dataSource.getDataList();
        rptSource = new ReportListHardCodeSource();
        rptList = rptSource.getRptList();
        showListView();
        clearSelectedUserData();
        clearSelectedReport();
        handleSelectedListView();
    }
    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("main_form");
        } catch (IOException e){
            System.err.println("err ไป project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    private void handleSelectedListView(){
        dataListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<UserData>() {
                    @Override
                    public void changed(ObservableValue<? extends UserData>
                                                observable,
                                        UserData oldValue, UserData newValue) {
                        System.out.println(newValue + " is selected");
                        showSelectedUserData(newValue);
                    }
                });
        reportListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Report>() {
                    @Override
                    public void changed(ObservableValue<? extends Report>
                                                observable,
                                        Report oldValue, Report newValue) {
                        System.out.println(newValue + " is selected");
                        showSelectedReport(newValue);
                    }
                });
    }
    private void showSelectedUserData(UserData data){
        nameLabel.setText(data.getName());
        surnameLabel.setText(data.getSurname());
        passwordLabel.setText(data.getPassword());
        usernameLabel.setText(data.getUserName());
    }

    private void clearSelectedUserData(){
        nameLabel.setText("");
        surnameLabel.setText("");
        passwordLabel.setText("");
        usernameLabel.setText("");
    }
    private void showListView(){
        dataListView.getItems().addAll(dataList.getAllData());
        dataListView.refresh();
        reportListView.getItems().addAll((rptList.getAllRpt()));
        reportListView.refresh();
    }

    private void showSelectedReport(Report rpt){
        topicLabel.setText(rpt.getTopic());
        dateLabel.setText(rpt.getDate());
    }
    private void clearSelectedReport(){
        topicLabel.setText("");
        dateLabel.setText("");
    }
}
