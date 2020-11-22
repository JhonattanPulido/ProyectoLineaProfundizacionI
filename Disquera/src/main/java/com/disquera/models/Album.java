//Paquetes
package com.disquera.models;

//Librerias
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Sandra Moreno - Jhonattan Pulido
 * @since 20/11/2020
 * @version 1.0.0
 */
public class Album implements Serializable{
    
    //Variables 
    
    /**
     * Id del album
     */
    private short id;
    
    /**
     * Nombre del album
     */
    @Pattern(regexp="[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{2,20}", message = "Nombre: Debe tener mínimo 2 y máximo 20 letras")
    private String nombre;
    
    /**
     * Precio del album
     */
    private double precio;
    
    /**
     * Id del artista
     */
    private short artistaId;
    
    /**
     * Artista del album
     */    
    private Artista artista;
    
    /**
     * Lista de canciones
     */
    private List<Cancion> listaCanciones;

    /**
     * Contructor del album
     */
    public Album() {       
        precio = 0;
        listaCanciones = new ArrayList<>();        
    }

    //Metodos Set & Get
    
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public short getArtistaId() {
        return artistaId;
    }

    public void setArtistaId(short artistaId) {
        this.artistaId = artistaId;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public List<Cancion> getListaCanciones() {
        return listaCanciones;
    }

    public void setListaCanciones(List<Cancion> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }            
}
