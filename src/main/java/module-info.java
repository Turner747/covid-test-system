module controller {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive java.sql;
    requires transitive javafx.graphics;

    opens controller to javafx.fxml;
    exports controller;
}
