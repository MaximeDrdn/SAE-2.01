module com.example.logicielchevalblancdemerde {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.spaceraceinc.logicielchevalblancdemerde to javafx.fxml;
    exports com.spaceraceinc.logicielchevalblancdemerde;
}