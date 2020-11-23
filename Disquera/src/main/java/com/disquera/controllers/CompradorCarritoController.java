//Paquetes
package com.disquera.controllers;

//Librerias
import com.disquera.logic.LCarrito;
import com.disquera.logic.LTipoVenta;
import com.disquera.models.Carrito;
import com.disquera.models.TipoVenta;
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
     * Faces context
     */
    private FacesContext facesContext;                 
    
    /**
     * Lista de tipo venta almacenados en base de datos
     */
    private List<TipoVenta> listaVenta;
    
    /**
     * Lista de Carrito almacenados en base de datos
     */
    private List<Carrito> listaCarrito;
    
    /**
     * Lista auxiliar de comprar
     */
    private List<Carrito> listaCompras;
    
     /**
     * Constructor
     */
    public CompradorCarritoController() {
     
        carrito = new Carrito();  
        listaCarrito = new LCarrito().leerCarrito();
        listaVenta = new LTipoVenta().leerTipoVenta();
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
    
    //Metodos Set & Get

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public List<TipoVenta> getListaVenta() {
        return listaVenta;
    }

    public void setListaVenta(List<TipoVenta> listaVenta) {
        this.listaVenta = listaVenta;
    }

    public List<Carrito> getListaCarrito() {
        return listaCarrito;
    }

    public void setListaCarrito(List<Carrito> listaCarrito) {
        this.listaCarrito = listaCarrito;
    }

    public List<Carrito> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(List<Carrito> listaCompras) {
        this.listaCompras = listaCompras;
    }
    
    
    
}
