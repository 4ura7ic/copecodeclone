module cs.ku {
    requires javafx.controls;
    requires javafx.fxml;


    opens ku.cs to javafx.fxml;
    exports ku.cs;

    exports ku.cs.app.controllers;
    opens ku.cs.app.controllers to javafx.fxml;

}