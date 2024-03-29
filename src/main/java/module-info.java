module com.example.synchronization {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.synchronization to javafx.fxml;
    exports com.example.synchronization;
}