package ku.cs.app.controllers.accountOrganizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import ku.cs.app.models.Activity;
import ku.cs.app.models.ActivityLog;
import ku.cs.app.services.ActivityLogDataSource;
import ku.cs.app.services.DataSource;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class LogDataFormController {
    //-------------------------------------------- FXML

    @FXML private ListView<Activity> logListView;

    //-------------------------------------------- noModifier

    DataSource<ActivityLog> logData = new ActivityLogDataSource("data","activityLog.csv");
    ActivityLog logList = logData.readData();

    //-------------------------------------------- initialize

    @FXML
    public void initialize(){
        showListview();
    }

    //-------------------------------------------- handle

    @FXML public void handleBackButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("user_data_list");
        } catch (IOException e){
            System.err.println("err ไป userList ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    //-------------------------------------------- method

    public void showListview(){
        logListView.getItems().addAll(logList.getLog());
        logListView.refresh();
    }
}
