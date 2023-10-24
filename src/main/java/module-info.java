module pl.ksr {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;

    opens pl.ksr.view to javafx.fxml;
    exports pl.ksr.view;
}