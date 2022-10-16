package ku.cs.app.controllers.accountOrganizer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.app.models.*;
import ku.cs.app.models.list.DynamicCategory;
import ku.cs.app.models.list.UserList;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.DynamicCategoryFileSource;
import ku.cs.app.services.UserDataListFileDataSource;
import ku.cs.app.services.ImageDataSource;
import com.github.saacsos.FXRouter;

import java.io.File;
import java.io.IOException;

public class AddOfficerFormController {
    @FXML
    private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private ImageView image;
    @FXML private ComboBox<String> inChargeBox;

    private DynamicCategoryFileSource dynamicCategoryFileSource = new DynamicCategoryFileSource("data", "category.csv");
    private DynamicCategory dynamicCategory = dynamicCategoryFileSource.readData();
    private ImageDataSource getImage;

    private DataSource<UserList> dataSource = new UserDataListFileDataSource("data", "user.csv");

    private UserList list = dataSource.readData();

    private Officer officerDetail;

    private Officer tempOfficer = new Officer();

    private String fs = File.separator ;
    private String imageName = "default.jpg";

    @FXML
    public void initialize() {
        dynamicCategory.removeCategory("ALL");
        System.out.println("initialize AddOfficerFormController");
        image.setImage(new Image(System.getProperty("user.dir") + fs + "data" + fs + "images" + fs + "officer" + fs + "default.jpg"));
        inChargeBox.getItems().addAll(dynamicCategory.getAllCategory());
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
            tempOfficer.setPassword(passwordField.getText());

            String errorMsg = tempOfficer.getErrorMsg();

            if(list.checkDuplicateUsername(tempOfficer.getUsername())){
                if (confirmPasswordField.getText().equals(passwordField.getText())){
                    if (errorMsg == ""){
                        officerDetail = new Officer(usernameTextField.getText(),new Password(passwordField.getText()),nameTextField.getText(),surnameTextField.getText(), inChargeBox.getValue());
                        officerDetail.setPhoto(imageName);
                        clearAllTextField();
                        try {
                            Admin admin = new Admin();
                            admin.createOfficer(officerDetail);
                            FXRouter.goTo("main_admin_form");
                        }catch (IOException e) {
                            System.err.println("err ไป project ไม่ได้");
                            System.err.println("ให้ตรวจสอบการกําหนด route");
                        }
                    } else {
                        passwordField.clear();
                        confirmPasswordField.clear();
                        errorMessageLabel.setText(errorMsg);
                    }
                }else{
                    errorMessageLabel.setText("Please insert the password correctly.");
                    passwordField.clear();
                    confirmPasswordField.clear();
                }

            }else {
                passwordField.clear();
                errorMessageLabel.setText("This username has already use.\n" + errorMsg);
            }

    }

    public void clearAllTextField() {
        usernameTextField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
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
            errorMessageLabel.setText(errorCheck);
        }
    }
}


