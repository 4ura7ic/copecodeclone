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

public class MainAdminFormController {
    //-------------------------------------------- FXML

    @FXML private ComboBox<String> categoryBox;
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
    @FXML private Label solutionLabel;
    @FXML private Label errorMessageLabel;
    @FXML private Button resetSortButton;
    @FXML private TextField amountVoteField;
    @FXML private ImageView reportImage;
    @FXML private ListView<Report> inProgressListView;
    @FXML private ListView<Report> finishReportListView;
    @FXML private Pane imagePane;
    @FXML private Pane solutionPane;
    @FXML private Button viewImageButton;
    @FXML private Button voteButton;
    @FXML private Button viewSolutionButton;

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
        list = dataSource.readData();
        resetSortButton.setVisible(false);
        showListView();
        handleSelectedListView();
        showUserData();
    }

    private void categorySort(Event event) {
        updateListView();
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
    public void handleUserList(ActionEvent actionEvent){
        try{
            FXRouter.goTo("user_data_list");
        } catch (IOException e){
            System.err.println("err ไป project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace();
        }
    }
    @FXML
    public void handleProfile(ActionEvent actionEvent){
        try{
            FXRouter.goTo("profile_form",user);
        } catch (IOException e){
            System.err.println("err ไป project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace();
        }
    }
    @FXML
    public void handleSuspendManagerButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("suspend_form", user);
        } catch (IOException e) {
            System.err.println("err ไป suspendManager ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAddNewOfficerButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("add_officer_form");
        } catch (IOException e) {
            System.err.println("err ไป AddNewOfficer ไม่ได้");
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
    @FXML public void handleSortVote(ActionEvent actionEvent){
        if(!(amountVoteField.getText().matches("[a-zA-Z]+"))) {
            String checkVoteSort = (amountVoteField.getText() != "") ? amountVoteField.getText() : "";
            if (checkVoteSort == "")
                errorMessageLabel.setText("Put your number first");
            else if (Integer.parseInt(checkVoteSort) >= 0) {
                updateListView();
            } else
                errorMessageLabel.setText("Invalid Number");
        }else
            errorMessageLabel.setText("Invalid Input");
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
        clearListView();
        clearForm();
        showListView();
        resetSortButton.setVisible(false);
        amountVoteField.clear();
    }
    @FXML
    public void handleHowToButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("admin_HowTo_form");
        } catch (IOException e) {
            System.err.println("err ไป How to ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleEditButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("category_edit_form", user);
        } catch (IOException e) {
            System.err.println("err ไป How to ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace();
        }
    }
    @FXML public void handleViewImageButton(){
        System.out.printf(rp.getPhoto());
        imagePane.setVisible(true);
        reportImage.setImage(new Image(System.getProperty("user.dir")+fs+"data"+fs+"images"+fs+"reportImage"+fs+rp.getPhoto()));
    }

    @FXML public void handleRefreshButton(){
        updateListView();
        resetSortButton.setVisible(false);
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
        descriptionPane.setVisible(false);
        barOne.setVisible(false);
        barTwo.setVisible(false);
        voteButton.setVisible(false);
        viewSolutionButton.setVisible(false);
        solutionPane.setVisible(false);
        viewImageButton.setVisible(false);
        imagePane.setVisible(false);
        topicLabel.setText("");
        dateLabel.setText("");
        categoryLabel.setText("");
        descriptionLabel.setText("");
        rateLabel.setText("");
        errorMessageLabel.setText("");
        popUpLabel.setText("Please select reports below to view detail here.");
    }

}
