package ku.cs.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ku.cs.app.models.InappropriateUser;
import ku.cs.app.models.InappropriateUserList;
import ku.cs.app.models.User;
import ku.cs.app.services.InappropriateUserListFileDataSource;
import com.github.saacsos.FXRouter;

import java.io.IOException;

public class ReportInappropriateActivityFormController {
    @FXML private TextField reasonTextField;

    private User user;
    private InappropriateUserList list;


    @FXML public void initialize(){
        user = (User) FXRouter.getData();
        InappropriateUserListFileDataSource dataSource = new InappropriateUserListFileDataSource("data", "inappropriateUser.csv");
        list = dataSource.readData();
    }

    public void handleBackButton(ActionEvent actionEvent) {
        if (user.getRole().equals("user")) {
            try {
                FXRouter.goTo("main_user_form", user);
            } catch (IOException e) {
                System.err.println("err ไป project ไม่ได้");
                System.err.println("ให้ตรวจสอบการกําหนด route");
            }
        } else if (user.getRole().equals("officer")) {
            try {
                FXRouter.goTo("main_officer_form", user);
            } catch (IOException e) {
                System.err.println("err ไป project ไม่ได้");
                System.err.println("ให้ตรวจสอบการกําหนด route");
            }
        }
    }
    public void handleReportButton(ActionEvent actionEvent){}
    


}
