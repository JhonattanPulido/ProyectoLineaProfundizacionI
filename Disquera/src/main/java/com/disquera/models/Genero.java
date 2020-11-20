// Modelos
package com.disquera.models;

// Librerías
import java.io.Serializable;

/**
 *
 * @author Sandra Moreno - Jhonattan Pulido
 * @since 20/11/2020
 * @version 1.0.0
 */
public class Genero implements Serializable {    
    
    // Variables
    
    /**
     * Id del género musical
     */
    private short id;
    
    /**
     * Nombre del género musical
     */
    private String nombre;
    
    /**
     * Constructor de la clase
     */
    public Genero() {
        
    }
    
    // Métodos
    
    // Métodos Set & Get

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }        
}
