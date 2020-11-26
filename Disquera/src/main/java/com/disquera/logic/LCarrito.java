//Paquetes
package com.disquera.logic;

//Librerias
import com.disquera.data.DCarrito;
import com.disquera.models.Carrito;
import java.io.Serializable;
import java.util.List;

/**
 * Capa lógica de carrito
 * @author Sandra Moreno - Jhonattan Pulido
 * @since 20/11/2020
 * @version 1.0.0
 */
public class LCarrito implements Serializable {
    
    /**
     * Lista que almacena el carrito
     */
    private List<Carrito> listaCarrito;
    
    /**
     * Constructor de la clase
     */
    public LCarrito() {
        
    }
    
    // Métodos
    
    /**
     * Crear carrito
     * @param carrito
     * @return true si el carrito se creo correctamente
     */
    public boolean crearCarrito(Carrito carrito) {
        return new DCarrito().crearCarrito(carrito);
    }
    
    /**
     * Leer todos los carritos almacenados en base de datos
     * @return lista de carrito
     */
    public List<Carrito> leerCarrito() {
        
        listaCarrito = new DCarrito().leerCarrito();
        return listaCarrito;
    }

    /**
     * Crear carrito
     * @return true si el estado se cambio
     */
    public boolean actualizarCarrito() {
        return new DCarrito().actualizarEstado();
    }
    
}
