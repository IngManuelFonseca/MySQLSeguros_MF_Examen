package co.edu.poli.examen2_Fonseca.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
	private static final String URL = "jdbc:mysql://localhost:3306/examen2_fonseca";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // En XAMPP por defecto viene vacío

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}