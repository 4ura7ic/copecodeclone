package ku.cs.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.scene.control.TextField;
import ku.cs.app.models.Report;
import ku.cs.app.models.ReportList;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.ReportListFileDataSource;

import java.io.IOException;

public class AssignReportFormController {
    @FXML private TextField topicTextField;
    @FXML private TextField descriptionTextField;

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
                    DataSource<ReportList> dataSource = new ReportListFileDataSource("data", "report.csv");
                    ReportList list = dataSource.readData();
                    list.addReport(new Report(topicTextField.getText(),"time set is maintenance",descriptionTextField.getText()));
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
