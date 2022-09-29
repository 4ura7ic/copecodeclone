package ku.cs.app.controllers.accountOrganizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import ku.cs.app.models.Activity;
import ku.cs.app.models.ActivityLog;
import ku.cs.app.services.ActivityLogDataSource;
import ku.cs.app.services.DataSource;

import java.io.IOException;
import java.util.Collection;

public class LogDataFormController {
    @FXML private ListView<Activity> logListView;
    DataSource<ActivityLog> logData = new ActivityLogDataSource("data","activityLog.csv");
    ActivityLog logList = logData.readData();
    @FXML
    public void initialize(){
        showListview();
    }

    public void showListview(){
        logListView.getItems().addAll(logList.getLog());
        logListView.refresh();
    }

    @FXML public void handleBackButton(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("user_data_list");
        } catch (IOException e){
            System.err.println("err ไป userList ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
}
