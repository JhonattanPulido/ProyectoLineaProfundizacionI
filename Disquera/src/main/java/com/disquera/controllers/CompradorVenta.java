/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disquera.controllers;

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
 * Controlador de comprador venta
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 20/11/2020
 */
@Named(value = "compradorVenta")
@ViewScoped
public class CompradorVenta implements Serializable {
    
    private Album album;
    
    /**
     * Lista de Carrito almacenados en base de datos
     */
    private List<Carrito> listaCarrito;

    public CompradorVenta() {
        
        listaCarrito = new LCarrito().leerCarrito();
        
    }
    
    
    public void redireccionarAlbum(short albumId) throws IOException {    
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("comprador-album-detalle.xhtml?albumId=" + albumId);
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Carrito> getListaCarrito() {
        return listaCarrito;
    }

    public void setListaCarrito(List<Carrito> listaCarrito) {
        this.listaCarrito = listaCarrito;
    }
   
    
        
}
