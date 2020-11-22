//Paquetes
package com.disquera.controllers;

//Librerias
import com.disquera.logic.LArtista;
import com.disquera.models.Album;
import com.disquera.models.Artista;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * Controlador de albumes para administrador
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 20/11/2020
 */
@Named(value = "albumAdministradorController")
@ViewScoped
public class AlbumAdministradorController implements Serializable {
     /**
     * Almacena los datos de un album nuevo
     */
    private Album album;
        
    /**
     * Faces context
     */
    private FacesContext facesContext;         
    
    /**
     * Lista de artistas
     */
    private List<Artista> listaArtista;

    public AlbumAdministradorController() {
        album = new Album();  
        listaArtista = new LArtista().leerArtistas();
    }
    
    // Métodos

    /**
     * Crear nuevo album     
     */
    public void crearAlbum() {
        System.out.println(album.getNombre());
    }

    // Métodos Set & Get
    
    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Artista> getListaArtista() {
        return listaArtista;
    }

    public void setListaArtista(List<Artista> listaArtista) {
        this.listaArtista = listaArtista;
    }
}
