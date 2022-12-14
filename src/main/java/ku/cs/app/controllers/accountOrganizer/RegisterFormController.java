package ku.cs.app.controllers.accountOrganizer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.app.models.Password;
import ku.cs.app.models.User;
import ku.cs.app.models.list.UserList;
import ku.cs.app.services.DataSource;
import ku.cs.app.services.UserDataListFileDataSource;
import ku.cs.app.services.ImageDataSource;

import java.io.File;
import java.io.IOException;

public class RegisterFormController {
    //-------------------------------------------- FXML

    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField confirmTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField surnameTextField;
    @FXML private Label errorMessageLabel;
    @FXML private ImageView image;

    //-------------------------------------------- private

    private User userDetail;
    private ImageDataSource getImage;

    //-------------------------------------------- noModifier
    DataSource<UserList> dataSource = new UserDataListFileDataSource("data", "user.csv");
    UserList list = dataSource.readData();
    User tempUserDetail = new User();
    String fs = File.separator ;
    String imageName = "default.jpg";

    //-------------------------------------------- initialize

    @FXML
    public void initialize(){
        errorMessageLabel.setText("");
        System.out.println("initialize MemberCardDetailController");
        image.setImage(new Image(System.getProperty("user.dir") + fs + "data" + fs + "images" + fs + "user" + fs + "default.jpg"));

    }

    //-------------------------------------------- handle

    @FXML
    public void handleBackButton(ActionEvent actionEvent){
        try{
            FXRouter.goTo("login_form");
        } catch (IOException e){
            System.err.println("err ไป project ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
        }
    }

    public void handleCreateID(ActionEvent actionEvent){
        tempUserDetail.setUsername(usernameTextField.getText());
        tempUserDetail.setPassword(passwordTextField.getText());
        String errorMsg = tempUserDetail.getErrorMsg();
        if(list.checkDuplicateUsername(tempUserDetail.getUsername())) {
            if(confirmTextField.getText().equals(passwordTextField.getText())) {
                if (errorMsg == "") {
                    userDetail = new User();
                    userDetail.setUsername(usernameTextField.getText());
                    userDetail.setName(nameTextField.getText());
                    userDetail.setSurname(surnameTextField.getText());
                    userDetail.setPassword(passwordTextField.getText());
                    userDetail.setPhoto(imageName);
                    clearAllTextField();
                    try {
                        list.addUser(new User("user", userDetail.getUsername(), new Password(userDetail.getPassword()), userDetail.getName(), userDetail.getSurname(), userDetail.getPhoto()));

                        dataSource.writeData(list);
                        FXRouter.goTo("login_form");
                    } catch (IOException e) {
                        System.err.println("err ไป project ไม่ได้");
                        System.err.println("ให้ตรวจสอบการกําหนด route");
                    }
                } else {
                    passwordTextField.clear();
                    confirmTextField.clear();
                    errorMessageLabel.setText(errorMsg);
                }
            }else{
                errorMessageLabel.setText("Please insert the password correctly.");
                passwordTextField.clear();
                confirmTextField.clear();
            }
        }else {
            passwordTextField.clear();
            errorMessageLabel.setText("This username has already use.\n"+errorMsg);
        }

    }

    //-------------------------------------------- method

    public void clearAllTextField() {
        usernameTextField.clear();
        passwordTextField.clear();
        nameTextField.clear();
        surnameTextField.clear();
    }

    public void chooseImageButton(ActionEvent event){
        if(usernameTextField.getText()!=""&&list.checkDuplicateUsername(usernameTextField.getText())){
            getImage = new ImageDataSource();
            imageName = getImage.chooseImage(usernameTextField.getText(), "user");
            tempUserDetail.setPhoto(imageName);
            //เซ็ตรูปในFXML
            image.setImage(new Image(System.getProperty("user.dir") + fs + "data" + fs + "images" + fs + "user" + fs + tempUserDetail.getPhoto()));

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


