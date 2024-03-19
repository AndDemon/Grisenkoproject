module com.rx.javajxpr {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.rx.javajxpr to javafx.fxml;
    exports com.rx.javajxpr;
}