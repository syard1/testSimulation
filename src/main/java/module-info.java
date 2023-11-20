module com.punimi {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.punimi to javafx.fxml;
    exports com.punimi;
}
