package co.edu.poli.examen2_Fonseca.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Asegurado {
    private final StringProperty id;
    private final StringProperty nombre;

    public Asegurado(String id, String nombre) {
        this.id = new SimpleStringProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
    }

    public StringProperty idProperty() { return id; }
    public StringProperty nombreProperty() { return nombre; }
    public String getId() { return id.get(); }
    public String getNombre() { return nombre.get(); }
}