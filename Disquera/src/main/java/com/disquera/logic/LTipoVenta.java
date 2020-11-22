/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disquera.logic;

import com.disquera.data.DTipoVenta;
import com.disquera.models.TipoVenta;
import java.io.Serializable;
import java.util.List;

/**
 * Capa lógica de tipo venta
 * @author Sandra Moreno - Jhonattan Pulido
 * @since 20/11/2020
 * @version 1.0.0
 */
public class LTipoVenta implements Serializable{
        // Variables
    
    /**
     * Constructor de la clase
     */
    public LTipoVenta() {
        
    }

    /**
     * Leer todos los tipos venta almacenados en base de datos
     * @return lista de venta
     */
    public List<TipoVenta> leerTipoVenta() {
        return new DTipoVenta().leerTipoVenta();
    }
}
