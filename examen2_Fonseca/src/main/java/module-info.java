module co.edu.poli.examen2_Fonseca {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // Esta es la que activa la conexión a XAMPP

    opens co.edu.poli.examen2_Fonseca.controlador to javafx.fxml;
    opens co.edu.poli.examen2_Fonseca.modelo to javafx.base;
    exports co.edu.poli.examen2_Fonseca;
}