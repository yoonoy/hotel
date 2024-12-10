module com.example.demo13 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.example.demo13;

    opens com.example.demo13 to
            javafx.fxml;
}