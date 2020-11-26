/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disquera.controllers;

import com.disquera.logic.LCarrito;
import com.disquera.logic.LIniciarSesion;
import com.disquera.models.Carrito;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * Controlador de carrito
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 20/11/2020
 */
@Named(value = "compradorCarrito")
@ViewScoped
public class CompradorCarrito implements Serializable {
    
    private Carrito carrito;

    
    public CompradorCarrito() {
    }

    
    public boolean actualizarCarrito(){
        if (new LCarrito().actualizarCarrito())
                System.out.println("Se actualizo");
            else
                System.out.println("No actualizado");
        return false;
    }
    
    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }
    
        /**
     * Cerrar sesión del usuario     
     * @throws java.io.IOException
     */
    public void cerrarSesion() throws IOException {
        new LIniciarSesion().cerrarSesion();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("faces/iniciar-sesion.xhtml");
    } 

    
}
