package ku.cs.app.controllers;

import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import java.io.IOException;

public class HomeController {

    @FXML
    public void handleRegisterButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("register");
        } catch (IOException e){
            System.err.println("err ไปregisterไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleResetPasswordButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("reset_password_form");
        } catch (IOException e){
            System.err.println("err ไป reset ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    @FXML
    public void handleListButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("user_list");
        } catch (IOException e){
            System.err.println("err ไป list ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace();
        }
    }


}
