/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disquera.controllers;

import com.disquera.logic.LAlbum;
import com.disquera.models.Album;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
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

    public CompradorVenta() {
        
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
        
        
}
