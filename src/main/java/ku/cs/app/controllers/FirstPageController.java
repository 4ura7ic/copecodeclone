package ku.cs.app.controllers;

import javafx.fxml.FXML;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;

import java.io.IOException;

public class FirstPageController {

    @FXML
    public  void handleGetIn(ActionEvent actionEvent){
        try {
            FXRouter.goTo("login_form");
        }catch (IOException e){
            System.err.println("err ไป login ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }
//    @FXML
//    public void handleRegisterButton(ActionEvent actionEvent){
//        try{
//            FXRouter.goTo("register_form");
//        } catch (IOException e){
//            System.err.println("err ไปregisterไม่ได้");
//            System.err.println("ให้ตรวจสอบการกําหนด route");
//        }
//    }
//
//    @FXML
//    public void handleResetPasswordButton(ActionEvent actionEvent){
//        try{
//            FXRouter.goTo("reset_password_form");
//        } catch (IOException e){
//            System.err.println("err ไป reset ไม่ได้");
//            System.err.println("ให้ตรวจสอบการกําหนด route");
//        }
//    }
//
//    @FXML
//    public void handleListButton(ActionEvent actionEvent){
//        try{
//            FXRouter.goTo("user_data_list");
//        } catch (IOException e){
//            System.err.println("err ไป list ไม่ได้");
//            System.err.println("ให้ตรวจสอบการกําหนด route");
//            e.printStackTrace();
//        }
//    }
//    @FXML
//    public void handleCredit(ActionEvent actionEvent){
//        try{
//            com.github.saacsos.FXRouter.goTo("credit");
//        } catch (IOException e){
//            System.err.println("err ไป credit ไม่ได้");
//            System.err.println("ให้ตรวจสอบการกําหนด route");
//        }
//    }
//    @FXML
//    public void handleLogin(ActionEvent actionEvent){
//        try{
//            com.github.saacsos.FXRouter.goTo("login_form");
//        } catch (IOException e){
//            System.err.println("err ไป login ไม่ได้");
//            System.err.println("ให้ตรวจสอบการกําหนด route");
//        }
//    }

}
