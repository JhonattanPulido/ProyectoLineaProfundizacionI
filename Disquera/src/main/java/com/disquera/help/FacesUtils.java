// Paquetes
package com.disquera.help;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 * Clase con funciones necesarias para subir imágenes
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 21/11/2020
 */
public class FacesUtils {
    
    // Variables
    
    /**
     * Constructor de la clase
     */
    public FacesUtils() {
    
    }
    
    // Métodos
    
    /**
     * Obtener dirección del proyecto
     * @return 
     */
    public static String getPath() {            
        try {        
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            return ctx.getRealPath("/");            
        } catch (Exception ex) {
            return null;
        }
    }
}
