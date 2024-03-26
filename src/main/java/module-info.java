module com.mycompany.database_grouppro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    opens com.mycompany.database_grouppro to javafx.fxml;
    exports com.mycompany.database_grouppro;
}
