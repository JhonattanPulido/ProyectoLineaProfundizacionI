// Paquetes
package com.disquera.models;

// Librerías
import java.io.Serializable;
import javax.validation.constraints.Size;

/**
 * Clase login
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 16/11/2020
 */
public class IniciarSesion implements Serializable {
    
    // Variables
    
    /**
     * Nombre de usuario del usuario
     */       
    @Size(min=8, max=25, message = "Nombre de usuario: Debe tener mínimo 8 y máximo 25 caracteres")
    private String nombreUsuario;
    
    /**
     * Clave del usuario
     */
    @Size(min=8, max=20, message = "Clave: Debe tener mínimo 8 y máximo 20 caracteres")
    private String clave;

    /**
     * Constructor de la clase
     */       
    public IniciarSesion() {
        
    }

    // Métodos Set & Get
    
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
}
