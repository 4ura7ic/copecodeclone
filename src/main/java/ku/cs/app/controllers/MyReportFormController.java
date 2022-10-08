package ku.cs.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import ku.cs.app.models.User;
import com.github.saacsos.FXRouter;
import java.io.IOException;

public class YourReportFormController {
    User user;

    public void initialize(){
        user = (User) FXRouter.getData();
    }

    @FXML public void handleBackButton(ActionEvent actionEvent){
        try {
            if(user.getRole().equals("admin"))
                com.github.saacsos.FXRouter.goTo("main_admin_form");
            if(user.getRole().equals("officer"))
                com.github.saacsos.FXRouter.goTo("main_officer_form");
            if(user.getRole().equals("user")){
                com.github.saacsos.FXRouter.goTo("main_user_form");
            }
        } catch (IOException e) {
            System.err.println("err ไป project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
}
