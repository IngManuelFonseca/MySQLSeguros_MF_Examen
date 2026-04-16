package co.edu.poli.examen2_Fonseca.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SeguroVehiculo extends Seguro {
    private final StringProperty marca;

    public SeguroVehiculo(String numero, String fecha, String estado, Asegurado asegurado, String marca) {
        super(numero, fecha, estado, asegurado);
        this.marca = new SimpleStringProperty(
            (marca == null || marca.isEmpty()) ? "Marca no especificada" : marca.toUpperCase()
        );
    }

    // Property para JavaFX
    public StringProperty marcaProperty() { return marca; }

    public String getMarca() { return marca.get(); }

    public void setMarca(String marca) { this.marca.set(marca); }

    @Override
    public String toString() {
        return String.format("POLIZA VEHICULO [#%s] | Asegurado: %s | Marca: %s", 
                getNumero(), asegurado.getNombre(), getMarca());
    }
}