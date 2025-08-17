module com.example.a7fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.a7fx to javafx.fxml;
    exports com.example.a7fx;
}