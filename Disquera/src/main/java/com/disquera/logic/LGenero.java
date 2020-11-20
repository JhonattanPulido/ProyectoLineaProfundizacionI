// Paquetes
package com.disquera.logic;

// Modelos
import com.disquera.data.DGenero;
import com.disquera.models.Genero;

// Librerías
import java.util.List;

/**
 * Capa lógica de géneros musicales
 * @author Sandra Moreno - Jhonattan Pulido
 * @since 20/11/2020
 * @version 1.0.0
 */
public class LGenero {
    
    // Variables
    
    /**
     * Constructor de la clase
     */
    public LGenero() {
        
    }

    /**
     * Leer todos los géneros musicales almacenados en base de datos
     * @return lista de artistas
     */
    public List<Genero> leerGenerosMusicales() {
        return new DGenero().leerGenerosMusicales();
    }
}
