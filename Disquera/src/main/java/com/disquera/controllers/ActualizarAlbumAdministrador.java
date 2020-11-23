/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disquera.controllers;

import com.disquera.logic.LAlbum;
import com.disquera.models.Album;
import java.io.Serializable;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author jhona
 */
@Named(value = "actualizarAlbumAdministrador")
@ViewScoped
public class ActualizarAlbumAdministrador implements Serializable {

    private short albumId;
    
    private Album album;
    
    /**
     * Creates a new instance of ActualizarAlbumAdministrador
     */
    public ActualizarAlbumAdministrador() {
        FacesContext facesContext = FacesContext. getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map params = externalContext.getRequestParameterMap();
        Short albumId =  Short.parseShort(params.get("albumId").toString());        
        this.albumId = albumId;
        album = new LAlbum().leerAlbum(this.albumId);        
    }

    public short getAlbumId() {
        return albumId;
    }

    public void setAlbumId(short albumId) {
        this.albumId = albumId;
    }        
}
