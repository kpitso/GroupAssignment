module com.sesothoapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.sesothoapp to javafx.fxml;
    exports com.sesothoapp;
}