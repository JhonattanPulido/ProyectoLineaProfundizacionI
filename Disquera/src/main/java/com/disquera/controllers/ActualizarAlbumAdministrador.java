// Paquetes
package com.disquera.controllers;
import com.disquera.logic.LAlbum;

// Modelos
import com.disquera.models.Album;

// Librerías
import java.io.Serializable;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author jhona
 */
@Named(value = "actualizarAlbumAdministrador")
@ViewScoped
public class ActualizarAlbumAdministrador implements Serializable {        
    
    /**
     * Almacena los datos del álbum
     */
    private Album album;
    
    /**
     * Constructor del controlador
     */
    public ActualizarAlbumAdministrador() {
                        
        Map params = FacesContext. getCurrentInstance().getExternalContext().getRequestParameterMap();                  
        album = new LAlbum().leerAlbum(Short.parseShort(params.get("albumId").toString())); 
        System.out.println("DETALLE: " + album.getArtista().getNombre());
    }       

    // Métodos
    
    // Métodos Set & Get
    
    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }        
}
