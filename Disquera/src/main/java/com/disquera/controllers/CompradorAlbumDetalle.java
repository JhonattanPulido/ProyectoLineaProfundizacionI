//Paquetes
package com.disquera.controllers;

//Librerias
import com.disquera.logic.LAlbum;
import com.disquera.models.Album;
import com.disquera.models.Carrito;
import java.io.Serializable;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * Controlador de album detalle para comprador
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 20/11/2020
 */
@Named(value = "compradorAlbumDetalle")
@ViewScoped
public class CompradorAlbumDetalle implements Serializable {

    private Album album;
    
    private Carrito carrito;

    public CompradorAlbumDetalle() {
        Map params = FacesContext. getCurrentInstance().getExternalContext().getRequestParameterMap();                  
        album = new LAlbum().leerAlbum(Short.parseShort(params.get("albumId").toString())); 
   }

    
    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }
    
    

}
