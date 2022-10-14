package ku.cs.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.github.saacsos.FXRouter;
import ku.cs.app.models.*;
import ku.cs.app.models.list.UserRequestList;
import ku.cs.app.services.UserRequestListFileDataSource;

import java.io.IOException;
import java.util.ArrayList;

public class SuspendedUserController {
    @FXML private Label reasonLabel;
    @FXML private Label loginAttemptCountLabel;
    @FXML private Label errorMsgLabel;
    @FXML private TextField requestTextField;

    private ArrayList<Object> o;
    private User user;
    private UserSuspension suspendedUser;
    private UserRequestListFileDataSource dataSource;
    private UserRequestList list;

    public void initialize() {
        o = (ArrayList<Object>) FXRouter.getData();
        user = (User) o.get(0);
        suspendedUser = (UserSuspension) o.get(1);
        dataSource = new UserRequestListFileDataSource("data", "userRequest.csv");
        list = dataSource.readData();


        reasonLabel.setText(suspendedUser.getReason());
        loginAttemptCountLabel.setText(Integer.toString(suspendedUser.getLoginAttempt()) + "time(s).");
        errorMsgLabel.setText("");

    }

    public void handleBackButton(ActionEvent actionEvent){
        try{
            com.github.saacsos.FXRouter.goTo("login_form");
        } catch (IOException e){
            System.err.println("err ไป project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    public void handleSentRequestButton() {
        if (list.checkIfExist(user.getUsername())) {
            errorMsgLabel.setText("You already sent a request.");
        }else {
            if (requestTextField.getText().equals("")) {errorMsgLabel.setText("Please enter a request.");}
            else {
                UserRequest userRequest = new UserRequest(user.getUsername(), requestTextField.getText());
                list.addUser(userRequest);
                dataSource.writeData(list);
                requestTextField.clear();
                try{
                    com.github.saacsos.FXRouter.goTo("login_form");
                } catch (IOException e){
                    System.err.println("err ไป project ไม่ได้");
                    System.err.println("ให้ตรวจสอบการกําหนด route");
                }
            }
        }
    }

}
