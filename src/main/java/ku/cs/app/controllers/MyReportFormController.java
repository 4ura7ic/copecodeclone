package ku.cs.app.controllers;

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
import javafx.scene.control.ScrollPane;
import javafx.scene.shape.Rectangle;
import ku.cs.app.models.Report;
import ku.cs.app.models.ReportList;
import ku.cs.app.models.User;
import com.github.saacsos.FXRouter;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.ReportListFileDataSource;

import java.io.IOException;

public class MyReportFormController {
    //-------------------------------------------- FXML

    @FXML private ComboBox categoryBox;
    @FXML private ComboBox sortBox;
    @FXML private ScrollPane descriptionPane;
    @FXML private Rectangle barOne;
    @FXML private Rectangle barTwo;
    @FXML private Label rateLabel;
    @FXML private Label popUpLabel;
    @FXML private Label topicLabel;
    @FXML private Label dateLabel;
    @FXML private Label statusLabel;
    @FXML private Label categoryLabel;
    @FXML private Label descriptionLabel;
    @FXML private ListView<Report> yourReportListView;
    //-------------------------------------------- private

    private User user;
    private ReportList list;
    private String[] category = {"ALL","Education","Environment","Scholarship","Transportation"};

    private String[] sortBy = {"Oldest","Newest","Most Vote","Least Vote"};

    ObservableList<String> categoryList = FXCollections
            .observableArrayList(category);

    ObservableList<String> sortList = FXCollections
            .observableArrayList(sortBy);

    //-------------------------------------------- initialize

    public void initialize(){
        startForm();
        user = (User) FXRouter.getData();
        categoryBox.getItems().addAll(categoryList);
        sortBox.getItems().addAll(sortList);
        categoryBox.setValue("ALL");
        sortBox.setValue("Oldest");
        categoryBox.setOnAction(this::categorySort);
        sortBox.setOnAction(this::categorySort);
        DataSource<ReportList> dataSource = new ReportListFileDataSource("data","report.csv");
        list = dataSource.readData();
        showListView();
        handleSelectedListView();
    }

    private void categorySort(Event event) {
        yourReportListView.getItems().clear();
        yourReportListView.getItems().addAll(list.sortTimeReport((String) sortBox.getValue(),list.sortYourReport(user.getUsername())));
    }

    //-------------------------------------------- handle

    private void handleSelectedListView(){
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

    @FXML public void handleBackButton(ActionEvent actionEvent){
        try {
            if(user.getRole().equals("admin"))
                FXRouter.goTo("main_admin_form");
            if(user.getRole().equals("officer"))
                FXRouter.goTo("main_officer_form");
            if(user.getRole().equals("user")){
                FXRouter.goTo("main_user_form");
            }
        } catch (IOException e) {
            System.err.println("err ไป project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    //-------------------------------------------- method

    private void showSelectedReport(Report report){
        if(report!=null) {
            barOne.setVisible(true);
            barTwo.setVisible(true);
            descriptionPane.setVisible(true);
            topicLabel.setText(report.getTopic());
            dateLabel.setText(report.getDate());
            categoryLabel.setText(report.getCategory());
            descriptionLabel.setText(report.getDescription());
            if(report.isCheck()) statusLabel.setText("Clear");
            else statusLabel.setText("In progress");
            rateLabel.setText("Rate: " + (report.getVote()));
            popUpLabel.setText("");
        }
    }

    private void showListView(){
        yourReportListView.getItems().addAll(list.sortYourReport(user.getUsername()));
        yourReportListView.refresh();
    }

    private void startForm(){
        descriptionPane.setVisible(false);
        barOne.setVisible(false);
        barTwo.setVisible(false);
        topicLabel.setText("");
        dateLabel.setText("");
        categoryLabel.setText("");
        descriptionLabel.setText("");
        rateLabel.setText("");
        statusLabel.setText("");
        popUpLabel.setText("Please select reports below to view detail here.");
    }


}
