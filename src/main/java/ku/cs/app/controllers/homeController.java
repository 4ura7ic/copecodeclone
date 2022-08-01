package ku.cs.app.controllers;

import javafx.fxml.FXML;
import ku.cs.app.models.user;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import java.io.IOException;

public class homeController {

    @FXML
    public void handleRegisterButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("register");
        } catch (IOException e){
            System.err.println("err ไปregisterไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
}
