package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;
import ku.cs.app.services.DefaultImageHardCode;

import java.io.IOException;
import java.net.URISyntaxException;

public class ProjectApplication extends Application{
    public void start(Stage stage) throws IOException{
        DefaultImageHardCode imageHardCode = new DefaultImageHardCode();
        imageHardCode.getDefault();
        FXRouter.bind(this, stage, 800, 600);
        Image icon = new Image("logo.png");
        stage.getIcons().add(icon);
        configRoute();
        FXRouter.goTo("login_form");

    }
    private static void configRoute(){
        String packageStr = "ku/cs/";
        FXRouter.when("user_data_list",packageStr+"userDataListForm.fxml");
        FXRouter.when("register_form", packageStr+"registerForm.fxml");
        FXRouter.when("reset_password_form", packageStr+"resetPasswordForm.fxml");
        FXRouter.when("login_form", packageStr+"loginForm.fxml");
        FXRouter.when("credit", packageStr+"credit.fxml");
        FXRouter.when("main_admin_form", packageStr+"mainAdminForm.fxml");
        FXRouter.when("main_officer_form", packageStr+"mainOfficerForm.fxml");
        FXRouter.when("main_user_form", packageStr+"mainUserForm.fxml");
        FXRouter.when("profile_form", packageStr+"profileForm.fxml");
        FXRouter.when("assign_report_form", packageStr+"assignReportForm.fxml");
        FXRouter.when("request_form",packageStr+"requestForm.fxml");
        FXRouter.when("suspend_form",packageStr+"userSuspensionForm.fxml");
        FXRouter.when("activity_log",packageStr+"activityLog.fxml");
        FXRouter.when("my_report_form",packageStr+"myReportForm.fxml");
        FXRouter.when("add_officer_form",packageStr+"addOfficerForm.fxml");
        FXRouter.when("report_inappropriate_activity_form", packageStr+"reportInappropriateActivityForm.fxml");
        FXRouter.when("suspended_user", packageStr+"suspendedUserForm.fxml");
        FXRouter.when("user_HowTo_form", packageStr+"userHowToForm.fxml");
        FXRouter.when("user_HowTo_Assign_form", packageStr+"userHowToAssignForm.fxml");
        FXRouter.when("howTo_Profile", packageStr+"howToProfile.fxml");
        FXRouter.when("officer_HowTo_form", packageStr+"officerHowToForm.fxml");
        FXRouter.when("admin_HowTo_form", packageStr+"adminHowToForm.fxml");
        FXRouter.when("category_edit_form", packageStr+"categoryEditor.fxml");

    }
    public static void main(String[] args) {
        launch();
    }
}
