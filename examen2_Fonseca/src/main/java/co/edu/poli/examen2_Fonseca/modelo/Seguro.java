package co.edu.poli.examen2_Fonseca.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Seguro {
    protected StringProperty numero;
    protected StringProperty fecha;
    protected StringProperty estado;
    protected Asegurado asegurado; // Relación de agregación/composición

    public Seguro(String numero, String fecha, String estado, Asegurado asegurado) {
        this.numero = new SimpleStringProperty(numero);
        this.fecha = new SimpleStringProperty(fecha);
        this.estado = new SimpleStringProperty(estado);
        this.asegurado = asegurado;
    }

    // Getters para la tabla de JavaFX
    public StringProperty numeroProperty() { return numero; }
    public StringProperty fechaProperty() { return fecha; }
    public StringProperty estadoProperty() { return estado; }
    
    // Estos getters acceden a los datos DENTRO del objeto asegurado
    public StringProperty idAseguradoProperty() { return asegurado.idProperty(); }
    public StringProperty nombreAseguradoProperty() { return asegurado.nombreProperty(); }

    public String getNumero() { return numero.get(); }
    public String getFecha() { return fecha.get(); }
    public String getEstado() { return estado.get(); }
    public String getIdAsegurado() { return asegurado.getId(); }
    public String getNombreAsegurado() { return asegurado.getNombre(); }
}