// Paquetes
package com.disquera.controllers;
import com.disquera.logic.LIniciarSesion;
import com.disquera.models.IniciarSesion;

// Librerías
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Controlador de la página de inicio de sesión
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 16/11/2020
 */
@Named(value = "iniciarSesionController")
@RequestScoped
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
    
        if (new LIniciarSesion().iniciarSesion(iniciarSesion) == true) {
            System.out.println("Correcto");
        } else {
            System.out.println("Incorrecto");
        }
    }    
    
    // Métodos Set & Get

    public IniciarSesion getIniciarSesion() {
        return iniciarSesion;
    }

    public void setIniciarSesion(IniciarSesion iniciarSesion) {
        this.iniciarSesion = iniciarSesion;
    }        
}
