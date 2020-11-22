// Paquetes
package com.disquera.logic;
import com.disquera.data.DIniciarSesion;

// Modelos
import com.disquera.models.IniciarSesion;
import com.disquera.models.Usuario;

// Librerías
import javax.faces.context.FacesContext;

/**
 * Capa lógica de inicio de sesión
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 19/11/2020
 */
public class LIniciarSesion {    
    
    // Variables
    
    /**
     * Variable auxiliar de usuario
     */
    private Usuario usuario;
    
    /**
     * Faces context
     */
    private FacesContext facesContext;
    
    // Constructor
    public LIniciarSesion() {
        
    }
    
    /**
     * 
     * @param datos
     * @return 
     */
    public boolean iniciarSesion(IniciarSesion datos) {
    
        usuario = new DIniciarSesion().iniciarSesion(datos);
        
        if (usuario != null) {            
            facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().getSessionMap().put("GqyZDngHH2", usuario);
        }
        
        return usuario != null;
    }
    
    /**
     * Cerrar sesión de un usuario     
     */
    public void cerrarSesion() {
        facesContext = FacesContext.getCurrentInstance();        
        facesContext.getExternalContext().invalidateSession();   
    }
}
