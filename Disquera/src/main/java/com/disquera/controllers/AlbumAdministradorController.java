//Paquetes
package com.disquera.controllers;

//Librerias
import com.disquera.logic.LAlbum;
import com.disquera.logic.LArtista;
import com.disquera.models.Album;
import com.disquera.models.Artista;
import com.disquera.models.Cancion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.json.JSONArray;

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
    
    /**
     * Variable auxiliar de canción
     */
    private Cancion cancion;

    /**
     * Constructor del controlador
     */
    public AlbumAdministradorController() {
        album = new Album();                  
        listaArtista = new LArtista().leerArtistas();    
        cancion = new Cancion();
    }
    
    // Métodos

    /**
     * Crear nuevo album     
     */
    public void crearAlbum() {
        
        if (album.getListaCanciones().size() > 0) {
            
            calcularPrecioAlbum();
            
            JSONArray JSONCanciones = new JSONArray(album.getListaCanciones());
            
            if (new LAlbum().crearAlbum(album, JSONCanciones.toString()))
                System.out.println("Creado correctamente");
            else
                System.out.println("Error creando album");
            
        } else
            System.out.println("No hay canciones");
    }        

    public void agregarCancion() {            
        album.getListaCanciones().add(new Cancion(cancion.getNombre(), cancion.getPrecio()));        
        calcularPrecioAlbum();
    }
    
    // Obtener precio del álbum
    private void calcularPrecioAlbum() {
        
        album.setPrecio(0);
        
        // Agregando precio inicial del álbum
        for (Cancion cancionAuxiliar: album.getListaCanciones())
            album.setPrecio(album.getPrecio() + cancionAuxiliar.getPrecio());

        double promedio = 0;

        for (Cancion cancionAuxiliar: album.getListaCanciones())
            promedio += cancionAuxiliar.getPrecio();                        

        promedio = promedio / album.getListaCanciones().size();

        album.setPrecio(album.getPrecio() - promedio); // Aqui ya esta el precio final        
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

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }        
}
