//Paquetes
package com.disquera.models;

//Librerias
import java.io.Serializable;

/**
 *
 * @author Sandra Moreno - Jhonattan Pulido
 * @since 20/11/2020
 * @version 1.0.0
 */
public class Carrito implements Serializable{
    
    // Variables
    
    /**
     * Id del carrito
     */
    private short id;
    
    
    /**
     * Precio del carrito
     */
    private double precio;
    
    /**
     * Id del Tipo de venta
     */
    private short tipoVentaId;
    
    /**
     * Descripcion de la compra
     */
    private String descripcion;
    
    /**
     * Tipo de venta del carrito
     */
    private TipoVenta tipoVenta;

    //Constructor
    public Carrito() {
    }

    //Metodos Set & Get
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public short getTipoVentaId() {
        return tipoVentaId;
    }

    public void setTipoVentaId(short tipoVentaId) {
        this.tipoVentaId = tipoVentaId;
    }

    public TipoVenta getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(TipoVenta tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
