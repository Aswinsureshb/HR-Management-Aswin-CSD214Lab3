module com.example.hr_management_aswins {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.hr_management_aswins to javafx.fxml;
    exports com.example.hr_management_aswins;
}