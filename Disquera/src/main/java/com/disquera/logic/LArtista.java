// Paquetes
package com.disquera.logic;
        
// Librerías
import com.disquera.data.DArtista;
import com.disquera.models.Artista;
import java.io.Serializable;
import java.util.List;

/**
 * Capa lógica de artista
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 20/11/2020
 */
public class LArtista implements Serializable {
    
    // Variables
    
    /**
     * Lista que almacena los artistas
     */
    private List<Artista> listaArtistas;
    
    /**
     * Constructor de la clase
     */
    public LArtista() {
        
    }
    
    // Métodos
    
    /**
     * Crear artista
     * @param artista
     * @return true si el artista se creo correctamente
     */
    public boolean crearArtista(Artista artista) {
        return new DArtista().crearArtista(artista);
    }
    
    /**
     * Leer todos los artistas almacenados en base de datos
     * @return lista de artistas
     */
    public List<Artista> leerArtistas() {
        
        listaArtistas = new DArtista().leerArtistas();
        
        for (Artista artista: listaArtistas) {
        
        }
        
        return listaArtistas;
    }
    
    /**
     * Leer artista filtrado por id
     * @param id
     * @return datos de un artista
     */
    public Artista leerArtista(short id) {
        return new DArtista().leerArtista(id);
    }
    
    /**
     * Actualizar un artista
     * @param artista
     * @return true si el artista se actualizó correctamente
     */
    public boolean actualizarArtista(Artista artista) {
        return new DArtista().actualizarArtista(artista);
    }
}
