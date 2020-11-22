//Paquetes
package com.disquera.logic;

//Librerias
import com.disquera.data.DAlbum;
import com.disquera.models.Album;
import java.io.Serializable;
import java.util.List;

/**
 * Capa lógica de album
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 20/11/2020
 */
public class LAlbum implements Serializable{
    
    // Variables
    
    /**
     * Constructor de la clase
     */
    public LAlbum() {
        
    }
    
    // Métodos
    
    /**
     * Crear album
     * @param album
     * @param cancionesJSON
     * @return true si el artista se creo correctamente
     */
    public boolean crearAlbum(Album album, String cancionesJSON) {
        return new DAlbum().crearAlbum(album, cancionesJSON);
    }
    
    /**
     * Leer todos los albumes almacenados en base de datos
     * @return lista de albumesa
     */
    public List<Album> leerAlbum() {
        return new DAlbum().leerAlbum();
    }
    
    /**
     * Leer album filtrado por id
     * @param id
     * @return datos de un artista
     */
    public Album leerAlbum(short id) {
        return new DAlbum().leerAlbum(id);
    }
    
    /**
     * Actualizar un album
     * @param album
     * @return true si el album se actualizó correctamente
     */
    public boolean actualizarAlbum(Album album) {
        return new DAlbum().actualizarAlbum(album);
    }
}
