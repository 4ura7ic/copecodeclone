package ku.cs.app.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ku.cs.app.models.Category;
import ku.cs.app.models.Report;
import ku.cs.app.models.ReportList;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.ReportListFileDataSource;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AssignReportFormController {
    //-------------------------------------------- FXML

    @FXML private ComboBox categoryBox;
    @FXML private TextField topicTextField;
    @FXML private TextField descriptionTextField;

    //-------------------------------------------- private

    private LocalDateTime date;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd MMM yyyy | HH:mm");
    private ArrayList<String> category;

    //-------------------------------------------- initialize

    public void initialize(){
        categoryBox.getItems().addAll(category);
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

    @FXML
    public void handleAssignButton(ActionEvent actionEvent) {
        if(topicTextField.getText()!="") {
            if(descriptionTextField.getText()!="") {
                try {
                    date = LocalDateTime.now();
                    String formatDate = date.format(formatter);
                    DataSource<ReportList> dataSource = new ReportListFileDataSource("data", "report.csv");
                    ReportList list = dataSource.readData();
                    list.addReport(new Report(topicTextField.getText(),categoryBox.getValue().toString(),formatDate,descriptionTextField.getText()));
                    dataSource.writeData(list);
                    FXRouter.goTo("main_user_form");
                } catch (IOException e) {
                    System.err.println("err ไป project ไม่ได้");
                    System.err.println("ให้ตรวจสอบการกําหนด route");
                }
            }
        }
    }

}
