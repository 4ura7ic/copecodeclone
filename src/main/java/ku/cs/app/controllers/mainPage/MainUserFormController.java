package ku.cs.app.controllers.mainPage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import ku.cs.app.models.Report;
import ku.cs.app.models.ReportList;
import ku.cs.app.models.User;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.ReportListFileDataSource;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class MainUserFormController {
    //-------------------------------------------- FXML

    @FXML private ComboBox categoryBox;
    @FXML private ComboBox timeBox;
    @FXML private Label nameLabel;
    @FXML private Label topicLabel;
    @FXML private Label dateLabel;
    @FXML private Label categoryLabel;
    @FXML private Label descriptionLabel;
    @FXML private ListView<Report> reportListView;

    //-------------------------------------------- private

    private ReportList list;
    private User user;

    //-------------------------------------------- noModifier

    ObservableList<String> categoryList = FXCollections
            .observableArrayList("Environment","Scholarship","Other","Default");

    ObservableList<String> timeList = FXCollections
            .observableArrayList("Descending","Ascending","Default");

    //-------------------------------------------- initialize

    @FXML
    public void initialize() throws IOException {
        DataSource<ReportList> dataSource = new ReportListFileDataSource("data","report.csv");
        list = dataSource.readData();
        showListView();
        handleSelectedListView();

        categoryBox.setValue("Environment");
        categoryBox.setItems(categoryList);

        categoryBox.setValue("Scholarship");
        categoryBox.setItems(categoryList);

        categoryBox.setValue("Other");
        categoryBox.setItems(categoryList);

        categoryBox.setValue("Default");
        categoryBox.setItems(categoryList);

        timeBox.setValue("Descending");
        timeBox.setItems(timeList);

        timeBox.setValue("Ascending");
        timeBox.setItems(timeList);

        timeBox.setValue("Default");
        timeBox.setItems(timeList);

        user = (User) FXRouter.getData();
        showUserData();
    }

    //-------------------------------------------- handle

    private void handleSelectedListView(){
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
    @FXML
    public void handleLogOut(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("login_form");
        } catch (IOException e) {
            System.err.println("err ไป project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleProfile(ActionEvent actionEvent){
        try{
            FXRouter.goTo("profile_form",user);
        } catch (IOException e){
            System.err.println("err ไป profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    @FXML
    public void handleAssignReportButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("assign_report_form");
        } catch (IOException e) {
            System.err.println("err ไป assign ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    //-------------------------------------------- method

    private void showUserData(){
        nameLabel.setText(user.getUsername());
    }
    private void showSelectedReport(Report report){
        topicLabel.setText(report.getTopic());
        dateLabel.setText(report.getDate());
        categoryLabel.setText("none");
        descriptionLabel.setText(report.getDescription());
    }

    private void showListView(){
        reportListView.getItems().addAll(list.getAllRpt());
        reportListView.refresh();
    }

}
