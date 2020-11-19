// Paquetes
package com.disquera.logic;
import com.disquera.data.DIniciarSesion;

// Modelos
import com.disquera.models.IniciarSesion;
import com.disquera.models.Usuario;

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
        
        // Guardar datos de usuario en el mapa
        
        return usuario != null;
    }
}
