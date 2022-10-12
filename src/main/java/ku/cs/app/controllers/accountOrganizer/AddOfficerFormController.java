package ku.cs.app.controllers.accountOrganizer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.app.models.*;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.UserDataListFileDataSource;
import ku.cs.app.services.ImageDataSource;
import com.github.saacsos.FXRouter;

import java.io.File;
import java.io.IOException;

public class AddOfficerFormController {
    @FXML
    private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField confirmTextField;
    @FXML private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private Label errorMsgLabel;
    @FXML
    private ImageView image;
    @FXML private ComboBox inChargeBox;

    private String[] charge = {"ALL","Education","Environment","Scholarship","Transportation"};

    private ImageDataSource getImage;

    DataSource<UserList> dataSource = new UserDataListFileDataSource("data", "user.csv");

    UserList list = dataSource.readData();

    Officer officerDetail;

    Officer tempOfficer = new Officer();

    String fs = File.separator ;
    String imageName = "rickroll.gif";

    Admin admin = new Admin();

    ObservableList<String> inChargeList = FXCollections
            .observableArrayList(charge);

    @FXML
    public void initialize() {

        System.out.println("initialize AddOfficerFormController");
        inChargeBox.getItems().addAll(inChargeList);
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("main_admin_form");
        } catch (IOException e){
            System.err.println("err ไป main_admin_form ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace();
        }
    }

    public void handleCreateID(ActionEvent actionEvent){

            tempOfficer.setName(usernameTextField.getText());
            tempOfficer.setPassword(passwordTextField.getText());

            String errorMsg = tempOfficer.getErrorMsg();

            if(list.checkDuplicateUsername(tempOfficer.getUsername())){
                if (confirmTextField.getText().equals(passwordTextField.getText())){
                    if (errorMsg == ""){
                        officerDetail = new Officer(usernameTextField.getText(),new Password(passwordTextField.getText()),nameTextField.getText(),surnameTextField.getText(),(String) inChargeBox.getValue());
                        officerDetail.setPhoto(imageName);
                        clearAllTextField();
                        try {
                            DataSource<UserList> dataSource = new UserDataListFileDataSource("data","user.csv");
                            UserList list = dataSource.readData();
                            list.addUser(officerDetail);
                            dataSource.writeData(list);
//                            admin.createOfficer(newOfficer.getUsername(), new Password(newOfficer.getPassword()), newOfficer.getName(), newOfficer.getSurname(), (String) chargeInBox.getValue());
                            FXRouter.goTo("main_admin_form");
                        }catch (IOException e) {
                            System.err.println("err ไป project ไม่ได้");
                            System.err.println("ให้ตรวจสอบการกําหนด route");
                        }
                    } else {
                        passwordTextField.clear();
                        confirmTextField.clear();
                        errorMsgLabel.setText(errorMsg);
                    }
                }else{
                    errorMsgLabel.setText("Please insert the password correctly.");
                    passwordTextField.clear();
                    confirmTextField.clear();
                }

            }else {
                passwordTextField.clear();
                errorMsgLabel.setText("This username has already use.\n" + errorMsg);
            }

    }

    public void clearAllTextField() {
        usernameTextField.clear();
        passwordTextField.clear();
        confirmTextField.clear();
        nameTextField.clear();
        surnameTextField.clear();
    }

    public void chooseImageButton(ActionEvent event){

        if(usernameTextField.getText()!=""&&list.checkDuplicateUsername(usernameTextField.getText())){

            getImage = new ImageDataSource();
            imageName = getImage.chooseImage(usernameTextField.getText(),"officer");
            tempOfficer.setPhoto(imageName);
            //เซ็ตรูปในFXML
            image.setImage(new Image(System.getProperty("user.dir") + fs + "data" + fs + "images" + fs + "officer" + fs + tempOfficer.getPhoto()));

        }

        else{
            String errorCheck = "";
            if(usernameTextField.getText()=="")
                errorCheck += "Please fill username first.";
            if(!list.checkDuplicateUsername(usernameTextField.getText()))
                errorCheck += "This username has already used.";
            errorMsgLabel.setText(errorCheck);
        }
    }
}


