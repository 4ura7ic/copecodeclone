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
import ku.cs.app.models.Report;
import ku.cs.app.models.list.DynamicCategory;
import ku.cs.app.models.list.ReportList;
import ku.cs.app.models.User;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.DynamicCategoryFileSource;
import ku.cs.app.services.ReportListFileDataSource;
import com.github.saacsos.FXRouter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainUserFormController {
    //-------------------------------------------- FXML

    @FXML private ComboBox<String> categoryBox;
    @FXML private ComboBox<String> sortBox;
    @FXML private Pane solutionPane;
    @FXML private Pane imagePane;
    @FXML private ScrollPane descriptionPane;
    @FXML private Rectangle barOne;
    @FXML private Rectangle barTwo;
    @FXML private Label rateLabel;
    @FXML private Label popUpLabel;
    @FXML private Label nameLabel;
    @FXML private Label topicLabel;
    @FXML private Label dateLabel;
    @FXML private Label solutionLabel;
    @FXML private Label categoryLabel;
    @FXML private Label descriptionLabel;
    @FXML private Label errorMessageLabel;
    @FXML private Button viewSolutionButton;
    @FXML private Button reportButton;
    @FXML private Button voteButton;
    @FXML private Button resetSortButton;
    @FXML private Button viewImageButton;
    @FXML private TextField amountVoteField;
    @FXML private ImageView reportImage;
    @FXML private ListView<Report> inProgressListView;
    @FXML private ListView<Report> finishReportListView;

    //-------------------------------------------- private
    private DynamicCategoryFileSource dynamicCategoryFileSource = new DynamicCategoryFileSource("data", "category.csv");
    private DynamicCategory dynamicCategory = dynamicCategoryFileSource.readData();
    private DataSource<ReportList> dataSource;
    private ReportList list;
    private User user;
    private String fs = File.separator ;
    private String[] sortBy = {"Oldest","Newest","Most Vote","Least Vote"};
    private Report rp = new Report();
    private ObservableList<String> categoryList = FXCollections
            .observableArrayList(dynamicCategory.getAllCategory());
    private ObservableList<String> sortList = FXCollections
            .observableArrayList(sortBy);

    //-------------------------------------------- initialize

    @FXML
    public void initialize() throws IOException {
        clearForm();
        user = (User) FXRouter.getData();
        dataSource = new ReportListFileDataSource("data","report.csv");
        categoryBox.getItems().addAll(categoryList);
        sortBox.getItems().addAll(sortList);
        categoryBox.setValue("ALL");
        sortBox.setValue("Oldest");
        categoryBox.setOnAction(this::categorySort);
        sortBox.setOnAction(this::categorySort);
        resetSortButton.setVisible(false);
        DataSource<ReportList> dataSource = new ReportListFileDataSource("data","report.csv");
        list = dataSource.readData();
        showListView();
        handleSelectedInProgressListView();
        handleSelectedFinishedListView();
        showUserData();
    }


    private void categorySort(Event event) {
        updateListView();
    }

    //-------------------------------------------- handle

    private void handleSelectedInProgressListView(){
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
    }
    private void handleSelectedFinishedListView(){
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

    @FXML
    public void handleYourReport(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("my_report_form", user);
        } catch (IOException e) {
            System.err.println("err ไป assign ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleHowToButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("user_HowTo_form");
        } catch (IOException e) {
            System.err.println("err ไป How to ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace();
        }
    }

    @FXML public void handleVoteButton(ActionEvent actionEvent) {
        if (rp.getVotedUser().contains(user.getUsername())){
            rp.decreaseVote();
            rp.getVotedUser().remove(user.getUsername());
        }
        else {
            rp.increaseVote();
            rp.getVotedUser().add(user.getUsername());
        }
        rateLabel.setText("Rate: " + Integer.toString(rp.getVote()));
        dataSource.writeData(list);
    }

    @FXML public void handleReportButton(ActionEvent actionEvent){
        ArrayList<Object> o = new ArrayList<>();
        o.add(user);
        o.add(rp);
        try {
            FXRouter.goTo("report_inappropriate_activity_form", o);
        } catch (IOException e) {
            System.err.println("err ไป assign ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace();
        }
    }

    @FXML public void handleSortVote(ActionEvent actionEvent){
        String  checkVoteSort = (amountVoteField.getText()!="")?amountVoteField.getText():"";
        if(checkVoteSort == "")
            errorMessageLabel.setText("Put your number first");
        else if(Integer.parseInt(checkVoteSort)>=0) {
            updateListView();
        }
        else
            errorMessageLabel.setText("Invalid Number");
    }

    @FXML public void handleViewSolutionButton(ActionEvent actionEvent){
        solutionPane.setVisible(true);
        solutionLabel.setText(rp.getSolution());
    }

    @FXML public void handleOKButton(ActionEvent actionEvent){
        solutionPane.setVisible(false);
        imagePane.setVisible(false);
    }

    @FXML public void handleResetSortButton(ActionEvent actionEvent){
        clearForm();
        clearListView();
        showListView();
        resetSortButton.setVisible(false);
        amountVoteField.clear();
    }

    @FXML public void handleViewImageButton(){
        System.out.printf(rp.getPhoto());
        imagePane.setVisible(true);
        reportImage.setImage(new Image(System.getProperty("user.dir")+fs+"data"+fs+"images"+fs+"reportImage"+fs+rp.getPhoto()));
    }

    //-------------------------------------------- method

    private void updateListView(){
        String  checkVoteSort = (amountVoteField.getText()!="")?amountVoteField.getText():"-1";
        clearListView();
        inProgressListView.getItems().addAll(list.sortByVoteOfReport(Integer.parseInt(checkVoteSort), list.sortTimeReport(sortBox.getValue(), list.sortInProgressReportByCategory(categoryBox.getValue()))));
        finishReportListView.getItems().addAll(list.sortByVoteOfReport(Integer.parseInt(checkVoteSort), list.sortTimeReport(sortBox.getValue(), list.sortFinishedReportByCategory(categoryBox.getValue()))));
        resetSortButton.setVisible(true);
        clearForm();
    }

    private void clearListView(){
        inProgressListView.getItems().clear();
        finishReportListView.getItems().clear();
    }

    private void showUserData(){
        nameLabel.setText(user.getUsername());
    }
    private void showSelectedReport(Report report){
        if(report!=null) {
            if(report.isCheck())
                viewSolutionButton.setVisible(true);
            else
                viewSolutionButton.setVisible(false);
            if(!report.getPhoto().equals("null"))
                viewImageButton.setVisible(true);
            else
                viewImageButton.setVisible(false);
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
            finishReportListView.refresh();
            inProgressListView.refresh();
        }
    }

    private void showListView(){
        inProgressListView.getItems().addAll(list.sortInProgressReport());
        inProgressListView.refresh();
        finishReportListView.getItems().addAll(list.sortFinishedReport());
        finishReportListView.refresh();
    }

    private void clearForm(){
        imagePane.setVisible(false);
        descriptionPane.setVisible(false);
        barOne.setVisible(false);
        barTwo.setVisible(false);
        reportButton.setVisible(false);
        voteButton.setVisible(false);
        viewSolutionButton.setVisible(false);
        solutionPane.setVisible(false);
        viewImageButton.setVisible(false);
        topicLabel.setText("");
        dateLabel.setText("");
        categoryLabel.setText("");
        descriptionLabel.setText("");
        rateLabel.setText("");
        errorMessageLabel.setText("");
        popUpLabel.setText("Please select reports below to view detail here.");
    }

}
