module com.example.logicielchevalblancdemerde {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.logicielchevalblancdemerde to javafx.fxml;
    exports com.example.logicielchevalblancdemerde;
}