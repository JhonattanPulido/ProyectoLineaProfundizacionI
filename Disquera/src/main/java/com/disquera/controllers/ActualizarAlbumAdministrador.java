// Paquetes
package com.disquera.controllers;
import com.disquera.logic.LAlbum;

// Modelos
import com.disquera.models.Album;
import com.disquera.models.Cancion;
import java.io.IOException;

// Librerías
import java.io.Serializable;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.json.JSONArray;
import org.primefaces.event.CellEditEvent;

/**
 * Controlador de la página para actualizar canciones de un álbum
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 23/11/2020
 */
@Named(value = "actualizarAlbumAdministrador")
@ViewScoped
public class ActualizarAlbumAdministrador implements Serializable {            
    
    /**
     * Almacena los datos del álbum
     */
    private Album album;
    
    /**
     * Almacena los datos de las canciones que se quieren agregar
     */
    private Cancion cancion;
    
    /**
     * Faces context
     */
    private FacesContext facesContext; 
    
    /**
     * Constructor del controlador
     * @throws java.io.IOException
     */
    public ActualizarAlbumAdministrador() throws IOException {
                        
        Map params = FacesContext. getCurrentInstance().getExternalContext().getRequestParameterMap();                  
        album = new LAlbum().leerAlbum(Short.parseShort(params.get("albumId").toString())); 

        if (album == null) {
            facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().redirect("admin-albumes.xhtml");
        }    
        
        cancion = new Cancion();
    }       

    // Métodos
    
    /**
     * Actualizar álbum
     * @param event Datos actualizados del álbum
     */
    public void onCellEdit(CellEditEvent event) {
    
        Object newValue = event.getNewValue();                         
        
        switch (event.getColumn().getHeaderText()) {
            
            case "Nombre":
                album.getListaCanciones().get(event.getRowIndex()).setNombre(newValue.toString());                
                break;
                
            case "Precio":                
                album.getListaCanciones().get(event.getRowIndex()).setPrecio((double) newValue);
                break;                            
        }
                
        for (short i = 0; i < album.getListaCanciones().size(); i++)
            if (album.getListaCanciones().get(i).getNombre() == null || album.getListaCanciones().get(i).getPrecio() <= 0)
                album.getListaCanciones().remove(i);        
        
        calcularPrecioAlbum();
        
        if (new LAlbum().actualizarCancionesAlbum(album, new JSONArray(album.getListaCanciones()).toString())) {            
            album = new LAlbum().leerAlbum(album.getId()); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Álbum", "Álbum actualizado correctamente"));
        } else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Álbum", "No se pudó actualizar el álbum"));
    }        
    
    /**
     * Agregar una cación a un álbum
     */
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
    
    // Métodos Set & Get
    
    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }  

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }        
}
