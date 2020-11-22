//Paquetes
package com.disquera.models;

//Librerias
import java.io.Serializable;

/**
 *
 * @author Sandra Moreno - Jhonattan Pulido
 * @since 20/11/2020
 * @version 1.0.0
 */
public class TipoVenta implements Serializable{
    
    //Variables 
    
    /**
     * Id del tipo venta
     */
    private short id;
    
    /**
     * Nombre del tipo venta
     */
    private String nombre;

    //Constructor
    public TipoVenta() {
        
    }

    //Metodos Set & Get
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
