// Paquetes
package com.disquera.controllers;
import com.disquera.logic.LIniciarSesion;

// Modelos
import com.disquera.models.IniciarSesion;
import com.disquera.models.Usuario;
import java.io.IOException;

// Librerías
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * Controlador de la página de inicio de sesión
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 16/11/2020
 */
@Named(value = "iniciarSesionController")
@ViewScoped
public class IniciarSesionController implements Serializable {

    // Variables
    
    /**
     * Variable que almacena los datos necesarios para iniciar sesión
     */
    private IniciarSesion iniciarSesion;     
    
    /**
     * Almacena los datos de el usuario que inició sesión
     */
    private Usuario usuario;
    
    /**
     * Faces context
     */
    private FacesContext facesContext;
    
    /**
     * Constructor
     */
    public IniciarSesionController() {
        
        iniciarSesion = new IniciarSesion();        
    }
    
    // Métodos
    
    /**
     * Validar inicio de sesión de un usuario
     * @throws IOException 
     */
    public void validarUsuario() throws IOException {
    
        facesContext = FacesContext.getCurrentInstance();
        
        if (new LIniciarSesion().iniciarSesion(iniciarSesion) == true) {
                                    
            iniciarSesion.setClave(null); // Seguridad
            
            usuario = (Usuario) facesContext.getExternalContext()
                        .getSessionMap().get("GqyZDngHH2");                        
            
            if (usuario.getRolId() == 1)
                facesContext.getExternalContext().redirect("faces/security/administrador/admin-artistas.xhtml");
            else
                facesContext.getExternalContext().redirect("faces/security/comprador/albumes.xhtml");
            
        } else
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Nombre de usuario y / o clave incorrectos"));
    }
        
    // Métodos Set & Get

    public IniciarSesion getIniciarSesion() {
        return iniciarSesion;
    }

    public void setIniciarSesion(IniciarSesion iniciarSesion) {
        this.iniciarSesion = iniciarSesion;
    }        
}
