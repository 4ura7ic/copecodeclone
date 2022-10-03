package ku.cs.app.controllers.mainPage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainUserFormController {
    //-------------------------------------------- FXML

    @FXML private ComboBox categoryBox;
    @FXML private ComboBox timeBox;
    @FXML private Label rateLabel;
    @FXML private Label popUpLabel;
    @FXML private Label nameLabel;
    @FXML private Label topicLabel;
    @FXML private Label dateLabel;
    @FXML private Label categoryLabel;
    @FXML private Label descriptionLabel;
    @FXML private ListView<Report> reportListView;
    @FXML private ListView<Report> yourReportListView;

    //-------------------------------------------- private

    private ReportList list;
    private ReportList yourList;
    private User user;
    private String[] category = {"ALL","Education","Environment","Scholarship","Transportation"};
    private String[] sortBy = {"Descending","Ascending"};

    //-------------------------------------------- noModifier

    ObservableList<String> categoryList = FXCollections
            .observableArrayList(category);

    ObservableList<String> timeList = FXCollections
            .observableArrayList(sortBy);

    //-------------------------------------------- initialize

    @FXML
    public void initialize() throws IOException {
        startForm();
        user = (User) FXRouter.getData();
        categoryBox.getItems().addAll(categoryList);
        timeBox.getItems().addAll(timeList);
        categoryBox.setOnAction(this::categorySort);
        DataSource<ReportList> dataSource = new ReportListFileDataSource("data","report.csv");
        list = dataSource.readData();
        yourList = list;
        showListView();
        handleSelectedListView();
        showUserData();
    }

    private void categorySort(Event event) {
        reportListView.getItems().clear();
        reportListView.getItems().addAll(list.sortByCategory((String) categoryBox.getValue()));
        yourReportListView.getItems().clear();
        yourReportListView.getItems().addAll((list.sortByUserAndCategory(user.getUsername(),(String) categoryBox.getValue())));
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
        yourReportListView.getSelectionModel().selectedItemProperty().addListener(
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
            FXRouter.goTo("assign_report_form",user);
        } catch (IOException e) {
            System.err.println("err ไป assign ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace();
        }
    }

    //-------------------------------------------- method

    private void showUserData(){
        nameLabel.setText(user.getUsername());
    }
    private void showSelectedReport(Report report){
        if(report!=null) {
            topicLabel.setText(report.getTopic());
            dateLabel.setText(report.getDate());
            categoryLabel.setText(report.getCategory());
            descriptionLabel.setText(report.getDescription());
            rateLabel.setText("Rate: " + Integer.toString(report.getRate()));
            popUpLabel.setText("");
        }
    }

    private void showListView(){
        reportListView.getItems().addAll(list.getAllRpt());
        reportListView.refresh();
        yourReportListView.getItems().addAll(list.sortByUser(user.getUsername()));
        yourReportListView.refresh();
    }

    private void startForm(){
        topicLabel.setText("");
        dateLabel.setText("");
        categoryLabel.setText("");
        descriptionLabel.setText("");
        rateLabel.setText("");
        popUpLabel.setText("Please select reports below to view detail here.");
    }

}
