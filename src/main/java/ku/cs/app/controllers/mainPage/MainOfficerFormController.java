package ku.cs.app.controllers.mainPage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;
import ku.cs.app.models.Officer;
import ku.cs.app.models.Report;
import ku.cs.app.models.ReportList;
import ku.cs.app.models.User;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.ReportListFileDataSource;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class MainOfficerFormController {
    //-------------------------------------------- FXML

    @FXML private ComboBox categoryBox;
    @FXML private ComboBox sortBox;
    @FXML private ScrollPane descriptionPane;
    @FXML private Rectangle barOne;
    @FXML private Rectangle barTwo;
    @FXML private Label rateLabel;
    @FXML private Label popUpLabel;
    @FXML private Label nameLabel;
    @FXML private Label topicLabel;
    @FXML private Label dateLabel;
    @FXML private Label categoryLabel;
    @FXML private Label descriptionLabel;
    @FXML private Button reportButton;
    @FXML private Button voteButton;
    @FXML private ListView<Report> inProgressListView;
    @FXML private ListView<Report> finishReportListView;

    //-------------------------------------------- private

    private DataSource<ReportList> dataSource;
    private ReportList list;
    private User user;
    private Object object;
    private Officer officer;
    private String[] category = {"ALL","Education","Environment","Scholarship","Transportation"};
    private String[] sortBy = {"Newest","Oldest","Most Vote","Least Vote"};
    private Report rp = new Report();

    //-------------------------------------------- noModifier

    ObservableList<String> categoryList = FXCollections
            .observableArrayList(category);

    ObservableList<String> sortList = FXCollections
            .observableArrayList(sortBy);

    //-------------------------------------------- initialize

    @FXML
    public void initialize() throws IOException {
        startForm();
        user = (User) FXRouter.getData();
        object = (Object) user;
        Officer officer = (Officer) object;
        dataSource = new ReportListFileDataSource("data","report.csv");
        categoryBox.getItems().addAll(categoryList);
        sortBox.getItems().addAll(sortList);
        categoryBox.setValue("ALL");
        sortBox.setValue("Newest");
        categoryBox.setOnAction(this::categorySort);
        sortBox.setOnAction(this::categorySort);
        list = dataSource.readData();
        showListView();
        handleSelectedListView();
        showUserData();
    }

    private void categorySort(Event event) {
        inProgressListView.getItems().clear();
        inProgressListView.getItems().addAll(list.sortTimeReport((String) sortBox.getValue(),list.sortInProgressReportByCategory(officer.getInCharge())));
        finishReportListView.getItems().clear();
        finishReportListView.getItems().addAll(list.sortTimeReport((String) sortBox.getValue(),(list.sortFinishedReportByCategory((String) categoryBox.getValue()))));
    }

    //-------------------------------------------- handle

    private void handleSelectedListView(){
        inProgressListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Report>() {
                    @Override
                    public void changed(ObservableValue<? extends Report>
                                                observable,
                                        Report oldValue, Report newValue) {
                        System.out.println(newValue + " is selected");
                        showSelectedReport(newValue);
                    }
                });
        finishReportListView.getSelectionModel().selectedItemProperty().addListener(
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
    public void handleLogOut(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("login_form");
        } catch (IOException e){
            System.err.println("err ไป project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    @FXML
    public void handleProfile(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("profile_form",user);
        } catch (IOException e){
            System.err.println("err ไป profile ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace();
        }
    }

    @FXML public void handleVoteButton(ActionEvent actionEvent) {
        if (rp.getVotedUser().contains(user.getUsername())){
            rp.decreaseVote();
            rp.getVotedUser().remove(user.getUsername());
            rateLabel.setText("Rate: " + Integer.toString(rp.getVote()));
            dataSource.writeData(list);
        }
        else {
            rp.increaseVote();
            rp.getVotedUser().add(user.getUsername());
            rateLabel.setText("Rate: " + Integer.toString(rp.getVote()));
            dataSource.writeData(list);
        }
    }

    //-------------------------------------------- method

    private void showUserData(){
        nameLabel.setText(user.getUsername());
    }

    private void showSelectedReport(Report report){
        if(report!=null) {
            rp = report;
            barOne.setVisible(true);
            barTwo.setVisible(true);
            descriptionPane.setVisible(true);
            reportButton.setVisible(true);
            voteButton.setVisible(true);
            topicLabel.setText(report.getTopic());
            dateLabel.setText(report.getDate());
            categoryLabel.setText(report.getCategory());
            descriptionLabel.setText(report.getDescription());
            rateLabel.setText("Rate: " + (report.getVote()));
            popUpLabel.setText("");
        }
    }

    private void showListView(){
        inProgressListView.getItems().addAll(list.sortInProgressReport());
        inProgressListView.refresh();
        finishReportListView.getItems().addAll(list.sortFinishedReport());
        finishReportListView.refresh();
    }

    private void startForm(){
        descriptionPane.setVisible(false);
        barOne.setVisible(false);
        barTwo.setVisible(false);
        reportButton.setVisible(false);
        voteButton.setVisible(false);
        topicLabel.setText("");
        dateLabel.setText("");
        categoryLabel.setText("");
        descriptionLabel.setText("");
        rateLabel.setText("");
        popUpLabel.setText("Please select reports below to view detail here.");
    }

}
