package ku.cs.app.controllers;

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
import com.github.saacsos.FXRouter;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.DynamicCategoryFileSource;
import ku.cs.app.services.ReportListFileDataSource;

import java.io.File;
import java.io.IOException;

public class MyReportFormController {
    //-------------------------------------------- FXML

    @FXML private ComboBox<String> categoryBox;
    @FXML private ComboBox<String> sortBox;
    @FXML private ScrollPane descriptionPane;
    @FXML private Pane solutionPane;
    @FXML private Pane imagePane;
    @FXML private Rectangle barOne;
    @FXML private Rectangle barTwo;
    @FXML private Label rateLabel;
    @FXML private Label popUpLabel;
    @FXML private Label topicLabel;
    @FXML private Label dateLabel;
    @FXML private Label statusLabel;
    @FXML private Label categoryLabel;
    @FXML private Label solutionLabel;
    @FXML private Label descriptionLabel;
    @FXML private ImageView reportImage;
    @FXML private Button viewImageButton;
    @FXML private Button viewSolutionButton;
    @FXML private ListView<Report> yourReportListView;
    //-------------------------------------------- private
    private DynamicCategoryFileSource dynamicCategoryFileSource = new DynamicCategoryFileSource("data", "category.csv");
    private DynamicCategory dynamicCategory = dynamicCategoryFileSource.readData();
    private String fs = File.separator ;
    private User user;
    private ReportList list;
    private Report report = new Report();
    private String[] sortBy = {"Oldest","Newest","Most Vote","Least Vote"};

    private ObservableList<String> categoryList = FXCollections
            .observableArrayList(dynamicCategory.getAllCategory());

    private ObservableList<String> sortList = FXCollections
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
        yourReportListView.getItems().addAll(list.sortTimeReport(sortBox.getValue(),list.sortUserReportByCategory(categoryBox.getValue(),list.returnObject(user.getUsername()))));
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

    @FXML public void handleViewSolutionButton(ActionEvent actionEvent){
        solutionPane.setVisible(true);
        solutionLabel.setText(report.getSolution());
    }

    @FXML public void handleViewImageButton(){
        System.out.printf(report.getPhoto());
        imagePane.setVisible(true);
        reportImage.setImage(new Image(System.getProperty("user.dir")+fs+"data"+fs+"images"+fs+"reportImage"+fs+report.getPhoto()));
    }

    @FXML public void handleOKButton(ActionEvent actionEvent){
        solutionPane.setVisible(false);
        imagePane.setVisible(false);
    }

    //-------------------------------------------- method

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
            this.report = report;
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
        yourReportListView.getItems().addAll(list.returnObject(user.getUsername()));
        yourReportListView.refresh();
    }

    private void startForm(){
        imagePane.setVisible(false);
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
