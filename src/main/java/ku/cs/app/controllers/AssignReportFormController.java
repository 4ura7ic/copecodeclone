package ku.cs.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import ku.cs.app.models.Report;
import ku.cs.app.models.list.DynamicCategory;
import ku.cs.app.models.list.ReportList;
import ku.cs.app.models.User;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.DynamicCategoryFileSource;
import ku.cs.app.services.ReportImageDataSource;
import ku.cs.app.services.ReportListFileDataSource;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AssignReportFormController {
    //-------------------------------------------- FXML

    @FXML private ComboBox<String> categoryBox;
    @FXML private TextField topicTextField;
    @FXML private TextField descriptionTextField;
    @FXML private ImageView image;
    @FXML private Button cancelButton;
    @FXML private Rectangle imagePreviewBar;

    //-------------------------------------------- private

    private DynamicCategoryFileSource dynamicCategoryFileSource = new DynamicCategoryFileSource("data", "category.csv");
    private DynamicCategory dynamicCategory = dynamicCategoryFileSource.readData();
    private User user;
    private LocalDateTime date;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm:ss");
    private ReportImageDataSource getImage;
    private String imageName = "-";
    private String fs = File.separator ;
    //-------------------------------------------- initialize

    public void initialize(){
        user = (User) FXRouter.getData();
        categoryBox.getItems().addAll(dynamicCategory.getAllCategory());
        cancelButton.setVisible(false);
    }

    //-------------------------------------------- handle

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("main_user_form");
        } catch (IOException e) {
            System.err.println("err ไป project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    public void handleAssignButton(ActionEvent actionEvent) {
        if(topicTextField.getText()!="") {
            if(descriptionTextField.getText()!="")
                if(categoryBox.getValue()!="") {
                    try {
                        date = LocalDateTime.now();
                        String formatDate = date.format(formatter);
                        DataSource<ReportList> dataSource = new ReportListFileDataSource("data", "report.csv");
                        ReportList list = dataSource.readData();
                        list.addReport(new Report(user.getUsername(), topicTextField.getText(), formatDate, categoryBox.getValue(), descriptionTextField.getText(),imageName));
                        dataSource.writeData(list);
                        FXRouter.goTo("main_user_form");
                    } catch (IOException e) {
                        System.err.println("err ไป project ไม่ได้");
                        System.err.println("ให้ตรวจสอบการกําหนด route");
                    }
                }
            }
        }
    @FXML
    public void handleHowToButton(ActionEvent actionEvent){
        try {
            FXRouter.goTo("user_HowTo_Assign_form");
        } catch (IOException e) {
            System.err.println("err ไป How to ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace();
        }
    }

    @FXML public void handleCancelButton(ActionEvent actionEvent){
        cancelButton.setVisible(false);
        image.setVisible(false);
        imagePreviewBar.setVisible(true);
        imageName = null;
    }

    public void chooseImageButton(ActionEvent event) throws IOException {
        getImage = new ReportImageDataSource();
        imageName = getImage.chooseImage(topicTextField.getText());
        System.out.println(imageName);
        if(imageName!=null) {
            imagePreviewBar.setVisible(false);
            cancelButton.setVisible(true);
            image.setVisible(true);
            image.setImage(new Image(System.getProperty("user.dir") + fs + "data" + fs + "images" + fs + "reportImage" + fs + imageName));
        }
    }
}
