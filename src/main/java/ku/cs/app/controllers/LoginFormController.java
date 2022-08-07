package ku.cs.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class LoginFormController {
    @FXML
    public void handleUserLogin(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("user_list");
        } catch (IOException e){
            System.err.println("err ไป user_list ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    @FXML
    public void handleCredit(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("credit");
        } catch (IOException e){
            System.err.println("err ไป user_list ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    @FXML
    public void handleUserRegister(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("register");
        } catch (IOException e){
            System.err.println("err ไป register ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    @FXML
    public void handleUserReset(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("reset_password_form");
        } catch (IOException e){
            System.err.println("err ไป reset_password_form ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

}
