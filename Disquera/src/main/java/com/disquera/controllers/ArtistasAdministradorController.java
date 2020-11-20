// Paquetes
package com.disquera.controllers;

// Modelos
import com.disquera.logic.LGenero;
import com.disquera.models.Artista;
import com.disquera.models.Genero;

// Librerías
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.file.UploadedFile;

/**
 * Controlador de artistas para administrador
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 20/11/2020
 */
@Named(value = "artistasAdministradorController")
@ViewScoped
public class ArtistasAdministradorController implements Serializable {

    // Variables        
    
    /**
     * Almacena los datos de un artista nuevo
     */
    private Artista artista;
        
    /**
     * Faces context
     */
    private FacesContext facesContext;                 
    
    /**
     * 
     */
    private List<Genero> listaGeneros;
    
    /**
     * Imagén del artista
     */
    private UploadedFile imagen;
    
    /**
     * Constructor del controlador
     */
    public ArtistasAdministradorController() {
     
        artista = new Artista();  
        
        listaGeneros = new LGenero().leerGenerosMusicales();
    }
    
    // Métodos
    
    /**
     * Crear nuevo artista     
     */
    public void crearArtista() {
    
        System.out.println(artista.getNacionalidad());
        
        /*if (new LArtista().crearArtista(artista))
            System.out.println("Creado");
        else
            System.out.println("No creado");*/
    }
    
    // Métodos Set & Get

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }     

    public List<Genero> getListaGeneros() {
        return listaGeneros;
    }

    public void setListaGeneros(List<Genero> listaGeneros) {
        this.listaGeneros = listaGeneros;
    }        

    public UploadedFile getImagen() {
        return imagen;
    }

    public void setImagen(UploadedFile imagen) {
        this.imagen = imagen;
    }        
}
