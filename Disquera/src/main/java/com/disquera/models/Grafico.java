// Paquetes
package com.disquera.models;

import java.io.Serializable;

/**
 * Módelo que almacena datos para generar un gráfico
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 26/11/2020
 */
public class Grafico implements Serializable {
    
    // Variables
    
    /**
     * Categoría de un dato
     */
    private String categoria;
    
    /**
     * Cantidad de veces que se repite un dato
     */
    private short cantidad;
    
    /**
     * Constructor de la clase
     * @param categoria
     * @param cantidad
     */        
    public Grafico(String categoria, short cantidad) {
        this.categoria = categoria;
        this.cantidad = cantidad;
    }        

    // Métodos Set & Get
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public short getCantidad() {
        return cantidad;
    }

    public void setCantidad(short cantidad) {
        this.cantidad = cantidad;
    }
}
