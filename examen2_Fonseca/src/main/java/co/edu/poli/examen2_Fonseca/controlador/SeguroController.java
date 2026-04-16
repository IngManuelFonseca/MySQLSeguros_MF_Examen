package co.edu.poli.examen2_Fonseca.controlador;

import co.edu.poli.examen2_Fonseca.modelo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeguroController {

    @FXML private TextField txtNumero, txtFecha, txtIdAsegurado, txtNombreAsegurado;
    @FXML private ComboBox<String> cbEstado;
    @FXML private TableView<Seguro> tablaSeguros;
    @FXML private TableColumn<Seguro, String> colNumero, colFecha, colEstado, colId, colNombre;

    private ObservableList<Seguro> listaSeguros = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cbEstado.setItems(FXCollections.observableArrayList("Activo", "Inactivo", "Pendiente"));

        colNumero.setCellValueFactory(cellData -> cellData.getValue().numeroProperty());
        colFecha.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
        colEstado.setCellValueFactory(cellData -> cellData.getValue().estadoProperty());
        colId.setCellValueFactory(cellData -> cellData.getValue().idAseguradoProperty());
        colNombre.setCellValueFactory(cellData -> cellData.getValue().nombreAseguradoProperty());

        cargarDatosDesdeBD();
        tablaSeguros.setItems(listaSeguros);
    }

    @FXML
    private void guardarVida() {
        if (validarCampos()) {
            Asegurado asig = new Asegurado(txtIdAsegurado.getText(), txtNombreAsegurado.getText());
            SeguroVida sv = new SeguroVida(txtNumero.getText(), txtFecha.getText(), cbEstado.getValue(), asig, "Familiar");
            
            if (insertarEnBD(sv, "Vida")) {
                listaSeguros.add(sv);
                limpiarYNotificar("Vida");
            }
        }
    }

    @FXML
    private void guardarVehiculo() {
        if (validarCampos()) {
            Asegurado asig = new Asegurado(txtIdAsegurado.getText(), txtNombreAsegurado.getText());
            SeguroVehiculo sev = new SeguroVehiculo(txtNumero.getText(), txtFecha.getText(), cbEstado.getValue(), asig, "Marca Genérica");
            
            if (insertarEnBD(sev, "Vehículo")) {
                listaSeguros.add(sev);
                limpiarYNotificar("Vehículo");
            }
        }
    }

    private void cargarDatosDesdeBD() {
        listaSeguros.clear();
        String sql = "SELECT * FROM seguros";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Asegurado asig = new Asegurado(rs.getString("id_asegurado"), rs.getString("nombre_asegurado"));
                // Importante: No podemos instanciar Seguro porque es abstracto. 
                // Usamos SeguroVida como contenedor genérico para la tabla.
                SeguroVida s = new SeguroVida(rs.getString("numero"), rs.getString("fecha"), rs.getString("estado"), asig, "");
                listaSeguros.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean insertarEnBD(Seguro s, String tipo) {
        String sql = "INSERT INTO seguros (numero, fecha, estado, id_asegurado, nombre_asegurado, tipo_seguro) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, s.getNumero());
            pstmt.setString(2, s.getFecha());
            pstmt.setString(3, s.getEstado());
            pstmt.setString(4, s.getIdAsegurado());
            pstmt.setString(5, s.getNombreAsegurado());
            pstmt.setString(6, tipo);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private boolean validarCampos() {
        return !txtNumero.getText().isEmpty() && cbEstado.getValue() != null;
    }

    private void limpiarYNotificar(String tipo) {
        txtNumero.clear(); txtFecha.clear(); txtIdAsegurado.clear(); txtNombreAsegurado.clear();
        cbEstado.getSelectionModel().clearSelection();
    }
}