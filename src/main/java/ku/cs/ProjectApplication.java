package ku.cs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.github.saacsos.FXRouter;
import java.io.IOException;

import java.io.IOException;

public class ProjectApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage, 800, 600);
        configRoute();
        FXRouter.goTo("project");
    }
    private static void configRoute(){
        String packageStr = "ku/cs/";
        FXRouter.when("user_data_list",packageStr+"user_data_list.fxml");
        FXRouter.when("register_form", packageStr+"register_form.fxml");
        FXRouter.when("project", packageStr+"project.fxml");
        FXRouter.when("reset_password_form", packageStr+"reset_password_form.fxml");
        FXRouter.when("login_form", packageStr+"login_form.fxml");
        FXRouter.when("credit", packageStr+"credit.fxml");


    }
    public static void main(String[] args) {
        launch();
    }
}
