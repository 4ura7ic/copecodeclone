package ku.cs.app.controllers.howTo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class OfficerHowToController {
    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            com.github.saacsos.FXRouter.goTo("main_officer_form");
        } catch (IOException e) {
            System.err.println("err ไป project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

}
