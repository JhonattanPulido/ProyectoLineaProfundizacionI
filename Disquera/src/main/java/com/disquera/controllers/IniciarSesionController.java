// Paquetes
package com.disquera.controllers;
import com.disquera.models.IniciarSesion;

// Librerías
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 * Controlador de la página de inicio de sesión
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 16/11/2020
 */
@Named(value = "iniciarSesionController")
@SessionScoped
public class IniciarSesionController implements Serializable {

    // Variables
    
    /**
     * Variable que almacena los datos necesarios para iniciar sesión
     */
    private IniciarSesion iniciarSesion;
    
    /**
     * Constructor
     */
    public IniciarSesionController() {
        
        iniciarSesion = new IniciarSesion();
    }
    
    // Métodos
    public void validarUsuario() {
    
        System.out.println(iniciarSesion.getNombreUsuario() + " - " + iniciarSesion.getClave());
    }    
    
    // Métodos Set & Get

    public IniciarSesion getIniciarSesion() {
        return iniciarSesion;
    }

    public void setIniciarSesion(IniciarSesion iniciarSesion) {
        this.iniciarSesion = iniciarSesion;
    }        
}
