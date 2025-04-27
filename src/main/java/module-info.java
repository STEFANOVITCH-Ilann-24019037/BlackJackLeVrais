module com.example.blac {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.blac to javafx.fxml;
    exports com.example.blac;
}