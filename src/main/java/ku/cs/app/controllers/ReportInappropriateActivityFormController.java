package ku.cs.app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ku.cs.app.models.InappropriateUser;
import ku.cs.app.models.list.InappropriateUserList;
import ku.cs.app.models.Report;
import ku.cs.app.models.User;
import ku.cs.app.services.InappropriateUserListFileDataSource;
import com.github.saacsos.FXRouter;

import java.io.IOException;
import java.util.ArrayList;

public class ReportInappropriateActivityFormController {
    @FXML private TextField reasonTextField;
    @FXML private Label errorMessageLabel;

    InappropriateUserListFileDataSource dataSource;
    private ArrayList<Object> o;
    private User user;
    private Report report;
    private InappropriateUserList list;


    @FXML public void initialize(){
        errorMessageLabel.setText("");
        o = (ArrayList<Object>) FXRouter.getData();
        user = (User) o.get(0);
        report = (Report) o.get(1);
        System.out.println(user);
        System.out.println(report);
        dataSource = new InappropriateUserListFileDataSource("data", "inappropriateUser.csv");
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
    public void handleReportButton(ActionEvent actionEvent){
        if (reasonTextField.getText().equals("")) {
            errorMessageLabel.setText("Please enter reason.");
        }
        else {
            if (list.checkIfExist(report.getAuthorUser())) {
                InappropriateUser u = list.returnObject(report.getAuthorUser());
                u.addInappropriateActivityCount();
                u.addInappropriateActions(reasonTextField.getText());
                dataSource.writeData(list);
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
            else {
                InappropriateUser u = new InappropriateUser();
                u.setUsername(report.getAuthorUser());
                u.addInappropriateActivityCount();
                u.addInappropriateActions(reasonTextField.getText());
                list.addUser(u);
                dataSource.writeData(list);
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


        }
    }
}
