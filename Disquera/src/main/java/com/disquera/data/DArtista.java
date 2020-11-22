// Paquetes
package com.disquera.data;

// Modelos
import com.disquera.models.Artista;
import com.disquera.models.Genero;

// Librerías
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de datos de artista
 * @author Sandra Moreno - Jhonattan Pulido
 * @since 20/11/2020
 * @version 1.0.0
 */
public class DArtista implements Serializable {    
    
    // Variables
    
    /**
     * Variable de conexión a la base de datos
     */
    private Connection dbContext;
    
    /**
     * Variable auxiliar de género
     */
    private Genero genero;
    
    /**
     * Variable auxiliar de artista
     */
    private Artista artista;
    
    /**
     * Todos los artistas almacenados en la base de datos
     */
    private List<Artista> listaArtistas;
    
    /**
     * Constructor de la clase
     */
    public DArtista() {
        
    }
    
    // Métodos
    
    /**
     * Crear artista
     * @param artista
     * @return true si el artista se creo correctamente
     */
    public boolean crearArtista(Artista artista) {
    
        try {
            
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }
            
            dbContext = DriverManager.getConnection(
                "jdbc:postgresql://proyecto-turismo-produccion-database.cuw9stbxwsmg.us-east-2.rds.amazonaws.com:5432/disquera",
                "p_turismo_master",
                "LCPzVCxRrZtS2BS"    
            );
            
            CallableStatement funcion = dbContext.prepareCall("{ call f_crear_artista(?,?,?,?) }");
            funcion.setString(1, artista.getNombre());
            funcion.setString(2, artista.getNacionalidad());              
            //funcion.setDate(3, new java.sql.Date(artista.getFechaNacimiento().getTime()));               
            funcion.setShort(3, artista.getGeneroId());
            funcion.setString(4, artista.getImagen());            
            
            ResultSet respuesta = funcion.executeQuery();                        
            
            return true;
            
        } catch (SQLException ex) {   
            System.out.println("ERROR :" + ex);
            return false;
        }
    }
    
    /**
     * Leer todos los artistas almacenados en base de datos
     * @return lista de artistas
     */
    public List<Artista> leerArtistas() {
    
        try {
            
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }
            
            dbContext = DriverManager.getConnection(
                "jdbc:postgresql://proyecto-turismo-produccion-database.cuw9stbxwsmg.us-east-2.rds.amazonaws.com:5432/disquera",
                "p_turismo_master",
                "LCPzVCxRrZtS2BS"    
            );
            
            CallableStatement funcion = dbContext.prepareCall("{ call f_leer_artistas() }");
            
            ResultSet respuesta = funcion.executeQuery();
            
            listaArtistas = new ArrayList<>();
            
            while (respuesta.next()) {
            
                artista = new Artista();                
                artista.setId(respuesta.getShort("id"));
                artista.setImagen(respuesta.getString("imagen"));
                artista.setNombre(respuesta.getString("nombre"));
                artista.setNacionalidad(respuesta.getString("nacionalidad"));
                artista.setFechaNacimiento(respuesta.getDate("fecha_nacimiento"));                
                artista.setGeneroId(respuesta.getShort("genero_musical_id"));     
                
                genero = new Genero();
                genero.setId(respuesta.getShort("genero_musical_id"));
                genero.setNombre(respuesta.getString("genero_musical_nombre"));
                artista.setGenero(genero);
                
                listaArtistas.add(artista);
            }
            
            return listaArtistas;
            
        } catch (SQLException ex) {        
            return null;
        }
    }
    
    /**
     * Leer artista filtrado por id
     * @param id
     * @return datos de un artista
     */
    public Artista leerArtista(short id) {
    
        try {
            
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }
            
            dbContext = DriverManager.getConnection(
                "jdbc:postgresql://proyecto-turismo-produccion-database.cuw9stbxwsmg.us-east-2.rds.amazonaws.com:5432/disquera",
                "p_turismo_master",
                "LCPzVCxRrZtS2BS"    
            );
            
            CallableStatement funcion = dbContext.prepareCall("{ call f_leer_artista(?) }");
            funcion.setShort(1, id);
            
            ResultSet respuesta = funcion.executeQuery(); 
            
            while (respuesta.next()) {
                
                artista = new Artista();                
                artista.setId(respuesta.getShort("id"));
                artista.setImagen(respuesta.getString("imagen"));
                artista.setNombre(respuesta.getString("nombre"));
                artista.setNacionalidad(respuesta.getString("nacionalidad"));
                artista.setFechaNacimiento(respuesta.getDate("fecha_nacimiento"));                
                artista.setGeneroId(respuesta.getShort("genero_musical_id"));  
                
                genero = new Genero();
                genero.setId(respuesta.getShort("genero_musical_id"));
                genero.setNombre(respuesta.getString("genero_musical_nombre"));
                artista.setGenero(genero);
            }                        
            
            return artista;
            
        } catch (SQLException ex) {        
            return null;
        }
    }
    
    /**
     * Actualizar un artista
     * @param artista
     * @return true si el artista se actualizó correctamente
     */
    public boolean actualizarArtista(Artista artista) {
    
        try {
            
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }
            
            dbContext = DriverManager.getConnection(
                "jdbc:postgresql://proyecto-turismo-produccion-database.cuw9stbxwsmg.us-east-2.rds.amazonaws.com:5432/disquera",
                "p_turismo_master",
                "LCPzVCxRrZtS2BS"    
            );
            
            CallableStatement funcion = dbContext.prepareCall("{ call f_actualizar_artista(?,?,?,?,?) }");
            funcion.setShort(1, artista.getId());
            funcion.setString(2, artista.getNombre());
            funcion.setString(3, artista.getNacionalidad());              
            funcion.setShort(4, artista.getGeneroId());
            funcion.setString(5, artista.getImagen());
            
            ResultSet respuesta = funcion.executeQuery();                        
            
            return true;
            
        } catch (SQLException ex) {  
            System.out.println("ERROR: " + ex);
            return false;
        }                
    }
}
