package ku.cs.app.controllers.mainPage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import ku.cs.app.models.Officer;
import ku.cs.app.models.Report;
import ku.cs.app.models.ReportList;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.ReportListFileDataSource;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class MainOfficerFormController {
    //-------------------------------------------- FXML

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
    @FXML private Label officerLabel;
    @FXML private Button submitSolutionButton;
    @FXML private Button voteButton;
    @FXML private Button editSolutionButton;
    @FXML private TextField solutionTextField;
    @FXML private Pane submitSolutionPane;
    @FXML private Pane officerPane;
    @FXML private ListView<Report> inProgressListView;
    @FXML private ListView<Report> finishReportListView;

    //-------------------------------------------- private

    private DataSource<ReportList> dataSource;
    private ReportList list;
    private Officer user;
    private String[] sortBy = {"Oldest","Newest","Most Vote","Least Vote"};
    private Report rp = new Report();

    //-------------------------------------------- noModifier

    ObservableList<String> sortList = FXCollections
            .observableArrayList(sortBy);

    //-------------------------------------------- initialize

    @FXML
    public void initialize() throws IOException {
        startForm();
        user = (Officer) FXRouter.getData();
        dataSource = new ReportListFileDataSource("data","report.csv");
        sortBox.getItems().addAll(sortList);
        sortBox.setValue("Newest");
        sortBox.setOnAction(this::categorySort);
        list = dataSource.readData();
        showListView();
        handleSelectedListView();
        showUserData();
    }

    private void categorySort(Event event) {
        inProgressListView.getItems().clear();
        inProgressListView.getItems().addAll(list.sortTimeReport((String) sortBox.getValue(),list.sortInProgressReportByCategory(user.getInCharge())));
        finishReportListView.getItems().clear();
        finishReportListView.getItems().addAll(list.sortTimeReport((String) sortBox.getValue(),list.sortFinishedReportByCategory(user.getInCharge())));
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
            FXRouter.goTo("login_form");
        } catch (IOException e){
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
    @FXML
    public void handleSubmitSolutionButton(ActionEvent actionEvent){
        submitSolutionPane.setVisible(true);
    }
    @FXML public void handleSubmitButton(ActionEvent actionEvent){
        rp.setSolution(solutionTextField.getText());
        rp.setService(user.getUsername());
        rp.finishingCheck();
        solutionTextField.clear();
        inProgressListView.getItems().clear();
        inProgressListView.getItems().addAll(list.sortTimeReport((String) sortBox.getValue(),list.sortInProgressReportByCategory(user.getInCharge())));
        finishReportListView.getItems().clear();
        finishReportListView.getItems().addAll(list.sortTimeReport((String) sortBox.getValue(),list.sortFinishedReportByCategory(user.getInCharge())));

        dataSource.writeData(list);
        submitSolutionPane.setVisible(false);
    }

    @FXML public void handleEditSolutionButton(ActionEvent actionEvent){
        submitSolutionPane.setVisible(true);
        solutionTextField.setText(rp.getSolution());
    }

    @FXML public void handleCloseButton(ActionEvent actionEvent){
        submitSolutionPane.setVisible(false);
    }

    //-------------------------------------------- method

    private void showUserData(){
        nameLabel.setText(user.getUsername());
    }

    private void showSelectedReport(Report report){
        if(report!=null) {
            rp = report;
            submitSolutionButton.setVisible(true);
            barOne.setVisible(true);
            barTwo.setVisible(true);
            descriptionPane.setVisible(true);
            voteButton.setVisible(true);
            topicLabel.setText(report.getTopic());
            dateLabel.setText(report.getDate());
            categoryLabel.setText(report.getCategory());
            descriptionLabel.setText(report.getDescription());
            rateLabel.setText("Rate: " + (report.getVote()));
            popUpLabel.setText("");
            if (rp.isCheck()) {
                officerPane.setVisible(true);
                officerLabel.setText(rp.getService());
                editSolutionButton.setVisible(true);
                submitSolutionButton.setVisible(false);
            }
            else {
                officerPane.setVisible(false);
                editSolutionButton.setVisible(false);
            }
        }
    }

    private void showListView(){
        inProgressListView.getItems().addAll(list.sortInProgressReportByCategory(user.getInCharge()));
        inProgressListView.refresh();
        finishReportListView.getItems().addAll(list.sortFinishedReportByCategory(user.getInCharge()));
        finishReportListView.refresh();
    }

    private void startForm(){
        descriptionPane.setVisible(false);
        barOne.setVisible(false);
        barTwo.setVisible(false);
        submitSolutionPane.setVisible(false);
        submitSolutionButton.setVisible(false);
        editSolutionButton.setVisible(false);
        voteButton.setVisible(false);
        officerPane.setVisible(false);
        topicLabel.setText("");
        dateLabel.setText("");
        categoryLabel.setText("");
        descriptionLabel.setText("");
        rateLabel.setText("");
        popUpLabel.setText("Please select reports below to view detail here.");
    }

}
