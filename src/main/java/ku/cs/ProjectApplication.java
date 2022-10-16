package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;
import java.io.IOException;

public class ProjectApplication extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage, 800, 600);
        Image icon = new Image("logo.png");
        stage.getIcons().add(icon);
        configRoute();
        FXRouter.goTo("login_form");
    }
    private static void configRoute(){
        String packageStr = "ku/cs/";
        FXRouter.when("user_data_list",packageStr+"user_data_list_form.fxml");
        FXRouter.when("register_form", packageStr+"register_form.fxml");
        FXRouter.when("reset_password_form", packageStr+"reset_password_form.fxml");
        FXRouter.when("login_form", packageStr+"login_form.fxml");
        FXRouter.when("credit", packageStr+"credit.fxml");
        FXRouter.when("main_admin_form", packageStr+"main_admin_form.fxml");
        FXRouter.when("main_officer_form", packageStr+"main_officer_form.fxml");
        FXRouter.when("main_user_form", packageStr+"main_user_form.fxml");
        FXRouter.when("profile_form", packageStr+"profile_form.fxml");
        FXRouter.when("assign_report_form", packageStr+"assign_report_form.fxml");
        FXRouter.when("request_form",packageStr+"request_form.fxml");
        FXRouter.when("suspend_form",packageStr+"user_suspension_form.fxml");
        FXRouter.when("activity_log",packageStr+"activity_log.fxml");
        FXRouter.when("my_report_form",packageStr+"my_report_form.fxml");
        FXRouter.when("add_officer_form",packageStr+"add_officer_form.fxml");
        FXRouter.when("report_inappropriate_activity_form", packageStr+"report_inappropriate_activity_form.fxml");
        FXRouter.when("suspended_user", packageStr+"suspended_user_form.fxml");
        FXRouter.when("user_HowTo_form", packageStr+"user_how_to_form.fxml");
        FXRouter.when("user_HowTo_Assign_form", packageStr+"user_how_to_assign_form.fxml");
        FXRouter.when("howTo_Profile", packageStr+"how_to_profile.fxml");
        FXRouter.when("officer_HowTo_form", packageStr+"officer_how_to_form.fxml");
        FXRouter.when("admin_HowTo_form", packageStr+"admin_HowTo_form.fxml");
        FXRouter.when("category_edit_form", packageStr+"category_editor.fxml");

    }
    public static void main(String[] args) {
        launch();
    }
}
