module cs.ku {
    requires javafx.controls;
    requires javafx.fxml;

    opens ku.cs to javafx.fxml;
    exports ku.cs;

    opens ku.cs.app.controllers to javafx.fxml;
    exports ku.cs.app.controllers;
    exports ku.cs.app.controllers.mainPage;
    opens ku.cs.app.controllers.mainPage to javafx.fxml;
    exports ku.cs.app.controllers.accountOrganizer;
    opens ku.cs.app.controllers.accountOrganizer to javafx.fxml;
    exports ku.cs.app.controllers.howTo;
    opens ku.cs.app.controllers.howTo to javafx.fxml;
}