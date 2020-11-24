/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disquera.controllers;

import com.disquera.logic.LAlbum;
import com.disquera.models.Album;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 *
 * @author Acer
 */
public class CompradorAlbumDetalle {

    private Album album;

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

}
