package ku.cs.app.controllers.mainPage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import ku.cs.app.models.Officer;
import ku.cs.app.models.Report;
import ku.cs.app.models.list.ReportList;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.ReportListFileDataSource;
import com.github.saacsos.FXRouter;

import java.io.File;
import java.io.IOException;

public class MainOfficerFormController {
    //-------------------------------------------- FXML

    @FXML private ComboBox<String> sortBox;
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
    @FXML private Label inChargeLabel;
    @FXML private Button submitSolutionButton;
    @FXML private Button voteButton;
    @FXML private Button editSolutionButton;
    @FXML private Button resetSortButton;
    @FXML private Button enrollButton;
    @FXML private TextField solutionTextField;
    @FXML private Pane submitSolutionPane;
    @FXML private Pane officerPane;
    @FXML private Pane imagePane;
    @FXML private Button viewImageButton;
    @FXML private ImageView reportImage;
    @FXML private ListView<Report> inProgressListView;
    @FXML private ListView<Report> finishReportListView;

    //-------------------------------------------- private

    private DataSource<ReportList> dataSource;
    private ReportList list;
    private Officer user;
    private String fs = File.separator ;
    private String[] sortBy = {"Oldest","Newest","Most Vote","Least Vote"};
    private Report report = new Report();
    private ObservableList<String> sortList = FXCollections
            .observableArrayList(sortBy);

    //-------------------------------------------- initialize

    @FXML
    public void initialize() throws IOException {
        clearForm();
        user = (Officer) FXRouter.getData();
        dataSource = new ReportListFileDataSource("data","report.csv");
        sortBox.getItems().addAll(sortList);
        sortBox.setValue("Oldest");
        sortBox.setOnAction(this::categorySort);
        resetSortButton.setVisible(false);
        list = dataSource.readData();
        inChargeLabel.setText(user.getInCharge());
        showListView();
        handleSelectedListView();
        showUserData();
    }

    private void categorySort(Event event) {
        clearListView();
        resetSortButton.setVisible(true);
        clearForm();
        inProgressListView.getItems().addAll(list.sortTimeReport(sortBox.getValue(),list.sortInProgressReportByCategory(user.getInCharge())));
        finishReportListView.getItems().addAll(list.sortTimeReport(sortBox.getValue(),list.sortFinishedReportByCategory(user.getInCharge())));
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
    @FXML public void handleOKButton(ActionEvent actionEvent){
        imagePane.setVisible(false);
    }

    @FXML public void handleVoteButton(ActionEvent actionEvent) {
        if (report.getVotedUser().contains(user.getUsername())){
            report.decreaseVote();
            report.getVotedUser().remove(user.getUsername());
        }
        else {
            report.increaseVote();
            report.getVotedUser().add(user.getUsername());
        }
        rateLabel.setText("Rate: " + Integer.toString(report.getVote()));
        dataSource.writeData(list);
    }
    @FXML
    public void handleSubmitSolutionButton(ActionEvent actionEvent){
        submitSolutionPane.setVisible(true);
    }
    @FXML public void handleSubmitButton(ActionEvent actionEvent){
        report.setSolution(solutionTextField.getText());
        report.setService(user.getUsername());
        report.finishingCheck();
        solutionTextField.clear();
        clearListView();
        inProgressListView.getItems().addAll(list.sortTimeReport(sortBox.getValue(),list.sortInProgressReportByCategory(user.getInCharge())));
        finishReportListView.getItems().addAll(list.sortTimeReport(sortBox.getValue(),list.sortFinishedReportByCategory(user.getInCharge())));
        dataSource.writeData(list);
        submitSolutionPane.setVisible(false);
    }

    @FXML public void handleEditSolutionButton(ActionEvent actionEvent){
        submitSolutionPane.setVisible(true);
        solutionTextField.setText(report.getSolution());
    }

    @FXML public void handleCloseButton(ActionEvent actionEvent){
        submitSolutionPane.setVisible(false);
    }

    @FXML public void handleResetSortButton(ActionEvent actionEvent){
        clearForm();
        clearListView();
        showListView();
        resetSortButton.setVisible(false);
    }
    @FXML
    public void handleHowToButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("officer_HowTo_form");
        } catch (IOException e) {
            System.err.println("err ไป How to ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace();
        }
    }
    @FXML public void handleViewImageButton(){
        System.out.printf(report.getPhoto());
        imagePane.setVisible(true);
        reportImage.setImage(new Image(System.getProperty("user.dir")+fs+"data"+fs+"images"+fs+"reportImage"+fs+ report.getPhoto()));
    }

    @FXML public void handleEnrollButton(){
        report.setService(user.getUsername());
        officerFunctionUpdate();
    }
    //-------------------------------------------- method

    private void showUserData(){
        nameLabel.setText(user.getUsername());
    }

    private void clearListView(){
        inProgressListView.getItems().clear();
        finishReportListView.getItems().clear();
    }

    private void showSelectedReport(Report report){
        if(report!=null) {
            this.report = report;
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
            officerFunctionUpdate();
            if(!report.getPhoto().equals("null"))
                viewImageButton.setVisible(true);
            else
                viewImageButton.setVisible(false);
        }
    }

    private void officerFunctionUpdate(){
        if(this.report.getService()!=null){
            enrollButton.setVisible(true);
        }
            else
        enrollButton.setVisible(false);
        if (this.report.getService()!="") {
            if(this.report.isCheck()){
                editSolutionButton.setVisible(true);
                submitSolutionButton.setVisible(false);
            }
            enrollButton.setVisible(false);
            submitSolutionButton.setVisible(true);
            officerPane.setVisible(true);
            officerLabel.setText(this.report.getService());
        }
        else {
            submitSolutionButton.setVisible(false);
            officerPane.setVisible(false);
            editSolutionButton.setVisible(false);
        }
    }

    private void showListView(){
        inProgressListView.getItems().addAll(list.sortInProgressReportByCategory(user.getInCharge()));
        inProgressListView.refresh();
        finishReportListView.getItems().addAll(list.sortFinishedReportByCategory(user.getInCharge()));
        finishReportListView.refresh();
    }

    private void clearForm(){
        enrollButton.setVisible(false);
        descriptionPane.setVisible(false);
        barOne.setVisible(false);
        barTwo.setVisible(false);
        submitSolutionPane.setVisible(false);
        submitSolutionButton.setVisible(false);
        editSolutionButton.setVisible(false);
        voteButton.setVisible(false);
        officerPane.setVisible(false);
        viewImageButton.setVisible(false);
        topicLabel.setText("");
        dateLabel.setText("");
        categoryLabel.setText("");
        descriptionLabel.setText("");
        rateLabel.setText("");
        popUpLabel.setText("Please select reports below to view detail here.");
    }

}
