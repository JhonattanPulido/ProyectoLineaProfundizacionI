//Paquetes
package com.disquera.controllers;

//Librerias
import com.disquera.logic.LAlbum;
import com.disquera.logic.LArtista;
import com.disquera.logic.LIniciarSesion;
import com.disquera.models.Album;
import com.disquera.models.Artista;
import com.disquera.models.Cancion;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.json.JSONArray;
import org.primefaces.event.CellEditEvent;

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
     * 
     */
    private List<Album> listaAlbumes;
    
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
        listaAlbumes = new LAlbum().leerAlbum();
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
            
            if (new LAlbum().crearAlbum(album, JSONCanciones.toString())) {
            
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Álbum creado correctamente", album.getNombre()));
                album.setNombre(null);
                album.setListaCanciones(null);
                listaAlbumes = new LAlbum().leerAlbum();
                
            } else
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudó crear el álbum, intentelo nuevamente"));
            
        } else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No ha agregado canciones"));
    }        

    public void agregarCancion() {            
        album.getListaCanciones().add(new Cancion(cancion.getNombre(), cancion.getPrecio()));        
        calcularPrecioAlbum();                
        cancion.setNombre(null);
        cancion.setPrecio(0);
    }
    
    /**
     * Calcular el precio del álbum
     */
    private void calcularPrecioAlbum() {
        
        if (album.getListaCanciones().size() == 1)
            album.setPrecio(album.getListaCanciones().get(0).getPrecio());
        else {
            
            album.setPrecio(0);
        
            // Agregando precio inicial del álbum
            for (Cancion cancionAuxiliar: album.getListaCanciones())
                album.setPrecio(album.getPrecio() + cancionAuxiliar.getPrecio());

            double promedio = 0;

            for (Cancion cancionAuxiliar: album.getListaCanciones())
                promedio += cancionAuxiliar.getPrecio();                        

            promedio = promedio / album.getListaCanciones().size();

            album.setPrecio(Math.round(album.getPrecio() - promedio)); // Aqui ya esta el precio final 
        }                       
    }
    
    /**
     * Actualizar álbum
     * @param event Datos actualizados del álbum
     */
    public void onCellEdit(CellEditEvent event) {
    
        Object newValue = event.getNewValue();        
        
        Album albumActualizado = listaAlbumes.get(event.getRowIndex());
        
        switch (event.getColumn().getHeaderText()) {
            
            case "Artista":
                albumActualizado.setArtistaId((short) newValue);
                break;
                
            case "Nombre":
                albumActualizado.setNombre(newValue.toString());
                break;                            
        }
        
        if (new LAlbum().actualizarAlbum(albumActualizado)) {
            listaAlbumes = new LAlbum().leerAlbum();            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Álbum", "Álbum actualizado correctamente"));
        } else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Álbum", "No se pudó actualizar el álbum"));
    }
    
    /**
     * Cerrar sesión del usuario     
     * @throws java.io.IOException
     */
    public void cerrarSesion() throws IOException {
        new LIniciarSesion().cerrarSesion();
        facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("faces/iniciar-sesion.xhtml");
    }
    
    /**
     * Actualizar álbum     
     */
    public void actualizarAlbum(CellEditEvent event) {
    
        
    }
    
    public void redireccionar(short albumId) throws IOException {    
        facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("admin-actualizar-album.xhtml?albumId=" + albumId);
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

    public List<Album> getListaAlbumes() {
        return listaAlbumes;
    }

    public void setListaAlbumes(List<Album> listaAlbumes) {
        this.listaAlbumes = listaAlbumes;
    }        
}
