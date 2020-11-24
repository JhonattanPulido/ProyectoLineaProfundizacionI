// Paquetes
package com.disquera.models;

// Librerías
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Sandra Moreno - Jhonattan Pulido
 * @since 20/11/2020
 * @version 1.0.0
 */
public class Artista implements Serializable {
    
    // Variables

    /**
     * Id del artista
     */
    private short id;
    
    /**
     * Nombre del artista
     */
    @Pattern(regexp="[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{2,20}", message = "Nombre: Debe tener mínimo 2 y máximo 20 letras")
    private String nombre;
    
    /**
     * Fecha de nacimiento del artista
     */    
    private Date fechaNacimiento;
    
    /**
     * Nacionalidad del artista
     */    
    private String nacionalidad;
    
    /**
     * Id del género del artista
     */
    private short generoId;
    
    /**
     * Genero del artista
     */
    private Genero genero;  
    
    /**
     * Imagen del artista
     */
    private String imagen;
    
    /**
     * Constructor de la clase
     */
    public Artista() {
        
    }   
    
    // Métodos
    
    // Métodos Set & Get

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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public short getGeneroId() {
        return generoId;
    }

    public void setGeneroId(short generoId) {
        this.generoId = generoId;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }    

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }   
}
