//Paquetes
package com.disquera.controllers;

//Librerias
import com.disquera.logic.LAlbum;
import com.disquera.logic.LCarrito;
import com.disquera.models.Album;
import com.disquera.models.Carrito;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * Controlador de carrito para comprador
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 20/11/2020
 */
@Named(value = "compradorCarritoController")
@ViewScoped
public class CompradorCarritoController implements Serializable {
    // Variables        
    
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
     * 
     */
    
     /**
     * Constructor
     */
    public CompradorCarritoController() {
     
        carrito = new Carrito();  
        listaCarrito = new LCarrito().leerCarrito();
        estadoCompra = false;
        listaAlbumes = new LAlbum().leerAlbum();
        
    }
    
    /**
     * Crear nuevo carrito     
     */
    public void crearCarrito() {                            
        
        if (new LCarrito().crearCarrito(carrito))
            System.out.println("Creado");
        else
            System.out.println("No creado");
    }  
    
    
    public void redireccionarAlbum(short albumId) throws IOException {    
        facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("comprador-album-detalle.xhtml?albumId=" + albumId);
    }
    //Metodos Set & Get

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
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


    public boolean isEstadoCompra() {
        return estadoCompra;
    }

    public void setEstadoCompra(boolean estadoCompra) {
        this.estadoCompra = estadoCompra;
    }
    
    
    
}
