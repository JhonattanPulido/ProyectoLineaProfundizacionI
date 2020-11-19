// Paquetes
package com.disquera.models;

// Librerías
import java.io.Serializable;

/**
 * Clase usuario
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 19/11/2020
 */
public class Usuario implements Serializable {
    
    // Variables
    
    /**
     * Id del usuario
     */
    private short id;
    
    /**
     * Nombre del usuario
     */
    private String nombre;
    
    /**
     * Dato para iniciar sesión
     */
    private String nombreUsuario;
    
    /**
     * Clave para iniciar sesión
     */
    private String clave;
    
    /**
     * Id del rol del usuario
     */
    private short rolId;

    /**
     * Constructor de la clase usuario
     */
    public Usuario() {
        
    }   

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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public short getRolId() {
        return rolId;
    }

    public void setRolId(short rolId) {
        this.rolId = rolId;
    }        
}
