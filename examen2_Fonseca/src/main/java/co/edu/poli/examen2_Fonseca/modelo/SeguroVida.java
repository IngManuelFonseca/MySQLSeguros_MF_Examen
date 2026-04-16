package co.edu.poli.examen2_Fonseca.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SeguroVida extends Seguro {
    private final StringProperty beneficiario;

    public SeguroVida(String numero, String fecha, String estado, Asegurado asegurado, String beneficiario) {
        super(numero, fecha, estado, asegurado);
        // Si el beneficiario llega vacío, le ponemos un valor por defecto elegante
        this.beneficiario = new SimpleStringProperty(
            (beneficiario == null || beneficiario.isEmpty()) ? "Beneficiario Legal" : beneficiario
        );
    }

    // Property para JavaFX
    public StringProperty beneficiarioProperty() { return beneficiario; }
    
    public String getBeneficiario() { return beneficiario.get(); }
    
    public void setBeneficiario(String beneficiario) { this.beneficiario.set(beneficiario); }

    @Override
    public String toString() {
        return String.format("POLIZA VIDA [#%s] | Asegurado: %s | Beneficiario: %s", 
                getNumero(), asegurado.getNombre(), getBeneficiario());
    }
}