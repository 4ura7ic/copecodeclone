package ku.cs.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class MainForm {
    @FXML
    public void handleLogOut(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("project");
        } catch (IOException e){
            System.err.println("err ไป project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    @FXML
    public void handleResetPassword(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("reset_password_form");
        } catch (IOException e){
            System.err.println("err ไป project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
    @FXML
    public void handleUserList(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("user_data_list");
        } catch (IOException e){
            System.err.println("err ไป project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
}
