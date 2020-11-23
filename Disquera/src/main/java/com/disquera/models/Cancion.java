// Paquetes
package com.disquera.models;

// Librerías
import java.io.Serializable;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;

/**
 * Clase canción
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 21/11/2020
 */
public class Cancion implements Serializable {
    
    // Variables
    
    /**
     * Nombre de la canción
     */
    @Pattern(regexp="[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{2,30}", message = "Nombre canción: Debe tener mínimo 2 y máximo 30 letras")
    private String nombre;
    
    /**
     * Precio de la canción
     */
    @DecimalMin(value= "1", message = "Precio canción: Debe ser mayor a 0")
    private double precio;    
    
    /**
     * Constructor de la clase
     */
    public Cancion() {
        
    }      

    public Cancion(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }        
    
    // Métodos Set & Get

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }        
}
