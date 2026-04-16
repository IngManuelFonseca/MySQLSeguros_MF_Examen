package co.edu.poli.examen2_Fonseca.test;

import static org.junit.Assert.*;
import org.junit.Test;
import co.edu.poli.examen2_Fonseca.modelo.*;
/**
 * Clase de prueba unitaria para validar la lógica de Seguros y Asegurados.
 * Se enfoca en la composición (Seguro -> Asegurado) y herencia.
 */
public class TestSeguroDAO {

    @Test
    public void testRelacionSeguroAsegurado() {
        // 1. Crear el objeto independiente: Asegurado
        Asegurado asig = new Asegurado("1010", "Fonseca Prueba");

        // 2. Crear el objeto Seguro de Vida (usamos la hija porque Seguro es abstracta)
        // Pasamos el objeto 'asig' como parámetro para cumplir con la composición
        SeguroVida sv = new SeguroVida(
            "POL-001", 
            "2024-10-27", 
            "Activo", 
            asig, 
            "Maria Fonseca (Madre)"
        );

        // 3. Verificaciones (Asserts)
        // Esto es lo que hace que la barra se ponga verde
        assertNotNull("El seguro no debería ser nulo", sv);
        assertNotNull("El asegurado dentro del seguro no debería ser nulo", sv.idAseguradoProperty());
        
        // Validamos que los datos que entraron sean los que salen
        assertEquals("1010", sv.getIdAsegurado());
        assertEquals("Fonseca Prueba", sv.getNombreAsegurado());
        assertEquals("Maria Fonseca (Madre)", sv.getBeneficiario());

        System.out.println("✅ Test de Relación: ÉXITO. El Asegurado está correctamente vinculado al Seguro de Vida.");
    }

    @Test
    public void testSeguroVehiculo() {
        // Prueba rápida para Seguro de Vehículo
        Asegurado asig = new Asegurado("2020", "Juan Perez");
        SeguroVehiculo sev = new SeguroVehiculo("POL-999", "2024-11-15", "Pendiente", asig, "Toyota");

        assertEquals("TOYOTA", sev.getMarca()); // Verifica que el constructor lo pase a mayúsculas
        System.out.println("✅ Test de Vehículo: ÉXITO. La marca se formateó correctamente.");
    }
}