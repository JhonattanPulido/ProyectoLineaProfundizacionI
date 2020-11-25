//Paquetes
package com.disquera.controllers;

//Librerias
import com.disquera.logic.LAlbum;
import com.disquera.logic.LCarrito;
import com.disquera.models.Album;
import com.disquera.models.Cancion;
import com.disquera.models.Carrito;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.json.JSONArray;

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
    

    /**
     * Lista de canciones seleccionadas por el usuario
     */
    private List<Cancion> listaCanciones;
    
     /**
     * Almacena los datos de un carrito nuevo
     */
     private Carrito carrito;
    
    
    /**
     * estado de la compra
     */
    private boolean estadoCompra;
        
    /**
     * Faces context
     */
    private FacesContext facesContext;      
    
    /**
     * Lista de Carrito almacenados en base de datos
     */
    private List<Carrito> listaCarrito;
    
    /**
     * Lista auxiliar de comprar
     */
    private List<Album> listaAlbumes;
    
    
     /**
     * Constructor
     */
    public CompradorAlbumDetalle() {
        Map params = FacesContext. getCurrentInstance().getExternalContext().getRequestParameterMap();                  
        album = new LAlbum().leerAlbum(Short.parseShort(params.get("albumId").toString())); 
        carrito = new Carrito();  
        listaCanciones = new ArrayList<>();
        //listaCarrito = new LCarrito().leerCarrito();
        //estadoCompra = false;
        //listaAlbumes = new LAlbum().leerAlbum();
    }
   

     /**
     * Crear nuevo carrito   
     */
    public void crearCarrito() {       
        
        if (listaCanciones.size() > 0) {
            
            if (listaCanciones.size() == album.getListaCanciones().size())
                carrito.setPrecio(album.getPrecio());
            else
                for (Cancion cancion: listaCanciones)
                    carrito.setPrecio(carrito.getPrecio() + cancion.getPrecio());
            
            JSONArray JSONCanciones = new JSONArray(listaCanciones);
                        
            carrito.setAlbumId(album.getId());
            carrito.setCanciones(JSONCanciones.toString());
            
            if (new LCarrito().crearCarrito(carrito))
                System.out.println("Creado");
            else
                System.out.println("No creado");
        }                    
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

    public boolean isEstadoCompra() {
        return estadoCompra;
    }

    public void setEstadoCompra(boolean estadoCompra) {
        this.estadoCompra = estadoCompra;
    }

    public List<Carrito> getListaCarrito() {
        return listaCarrito;
    }

    public void setListaCarrito(List<Carrito> listaCarrito) {
        this.listaCarrito = listaCarrito;
    }

    public List<Album> getListaAlbumes() {
        return listaAlbumes;
    }

    public void setListaAlbumes(List<Album> listaAlbumes) {
        this.listaAlbumes = listaAlbumes;
    }

    public List<Cancion> getListaCanciones() {
        return listaCanciones;
    }

    public void setListaCanciones(List<Cancion> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }        
}
