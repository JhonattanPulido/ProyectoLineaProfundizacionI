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
     * Id del album
     */
    private short albumId;
    
    /**
     * Canciones
     */
    private String canciones;
    
    /**
     * precio de la compra
     */
    private double precio;
    
    /**
     * estado de la compra
     */
    private boolean estadoCompra;
    
        /**
     * Genero del artista
     */
    private Album album;  
    

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

    public short getAlbumId() {
        return albumId;
    }

    public void setAlbumId(short albumId) {
        this.albumId = albumId;
    }


    public String getCanciones() {
        return canciones;
    }

    public void setCanciones(String canciones) {
        this.canciones = canciones;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isEstadoCompra() {
        return estadoCompra;
    }

    public void setEstadoCompra(boolean estadoCompra) {
        this.estadoCompra = estadoCompra;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

   
    
}
