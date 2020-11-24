//Paquetes
package com.disquera.data;

import com.disquera.models.Album;
import com.disquera.models.Artista;
import com.disquera.models.Cancion;
import com.disquera.models.Genero;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author Acer
 */
public class DAlbum implements Serializable {
    
    // Variables
    
    /**
     * Variable de conexión a la base de datos
     */
    private Connection dbContext;
    

    /**
     * Variable auxiliar de artista
     */
    private Artista artista;
    
    /**
     * Variable auxiliar de album
     */
    private Album album;
    
    /**
     * Variable auxiliar de genero
     */
    private Genero genero;
    
     /**
     * Todos los albumes almacenados en la base de datos
     */
    private List<Album> listaAlbumes;

    
    /**
     * Constructor de la clase
     */
    public DAlbum() {
    }
    
    /**
     * Crear album
     * @param album
     * @param cancionesJSON
     * @return true si el artista se creo correctamente
     */
    public boolean crearAlbum(Album album, String cancionesJSON) {
    
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
            
            CallableStatement funcion = dbContext.prepareCall("{ call f_crear_album(?,?,?,?) }");
            funcion.setString(1, album.getNombre());
            funcion.setDouble(2, album.getPrecio());  
            funcion.setShort(3, album.getArtistaId());
            funcion.setString(4, cancionesJSON);
            
            ResultSet respuesta = funcion.executeQuery();                        
            
            return true;
            
        } catch (SQLException ex) { 
            System.out.println("Album error: " + ex);
            return false;
        }
    }
    
    /**
     * Leer todos los albumes almacenados en base de datos
     * @return lista de albumes
     */
    public List<Album> leerAlbum() {
    
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
            
            CallableStatement funcion = dbContext.prepareCall("{ call f_leer_album() }");
            
            ResultSet respuesta = funcion.executeQuery();
            
            listaAlbumes = new ArrayList<>();
            
            while (respuesta.next()) {
            
                album = new Album();                
                album.setId(respuesta.getShort("id"));
                album.setNombre(respuesta.getString("nombre"));
                album.setPrecio(respuesta.getDouble("precio"));           
                album.setArtistaId(respuesta.getShort("artista_id"));  
                
                artista = new Artista();
                artista.setId(respuesta.getShort("artista_id"));
                artista.setNombre(respuesta.getString("artista_nombre"));
                artista.setNacionalidad(respuesta.getString("artista_nacionalidad"));
                artista.setFechaNacimiento(respuesta.getDate("artista_fecha_nacimiento"));
                artista.setImagen(respuesta.getString("artista_imagen"));
                artista.setGeneroId(respuesta.getShort("artista_genero_musical_id"));
                album.setArtista(artista);
                
                genero = new Genero();
                genero.setId(respuesta.getShort("artista_genero_musical_id"));
                genero.setNombre(respuesta.getString("artista_genero_musical_nombre"));
                artista.setGenero(genero);
                
                album.setArtista(artista);
                
                JSONArray JSONCanciones = new JSONArray(respuesta.getString("canciones"));
                
                for (short i = 0; i < JSONCanciones.length(); i++)  
                    album.getListaCanciones().add(new Cancion(JSONCanciones.getJSONObject(i).getString("nombre"), JSONCanciones.getJSONObject(i).getDouble("precio")));                
                
                listaAlbumes.add(album);
            }
            
            return listaAlbumes;
            
        } catch (SQLException ex) {   
            System.out.println("ERROR: " + ex);
            return null;
        }
    }
    
      /**
     * Leer album filtrado por id
     * @param id
     * @return datos de un album
     */
    public Album leerAlbum(short id) {
    
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
            
            CallableStatement funcion = dbContext.prepareCall("{ call f_leer_album(?) }");
            funcion.setShort(1, id);
            
            ResultSet respuesta = funcion.executeQuery(); 
            
            while (respuesta.next()) {
                
                album = new Album();                
                album.setId(respuesta.getShort("id"));
                album.setNombre(respuesta.getString("nombre"));
                album.setPrecio(respuesta.getDouble("precio"));           
                album.setArtistaId(respuesta.getShort("artista_id"));  
                
                artista = new Artista();
                artista.setId(respuesta.getShort("artista_id"));
                artista.setNombre(respuesta.getString("artista_nombre"));
                artista.setNacionalidad(respuesta.getString("artista_nacionalidad"));
                artista.setFechaNacimiento(respuesta.getDate("artista_fecha_nacimiento"));
                artista.setImagen(respuesta.getString("artista_imagen"));
                artista.setGeneroId(respuesta.getShort("artista_genero_musical_id"));     
                
                genero = new Genero();
                genero.setId(respuesta.getShort("artista_genero_musical_id"));
                genero.setNombre(respuesta.getString("artista_genero_musical_nombre"));
                artista.setGenero(genero);
                
                album.setArtista(artista);
                
                JSONArray JSONCanciones = new JSONArray(respuesta.getString("canciones"));
                
                for (short i = 0; i < JSONCanciones.length(); i++)  
                    album.getListaCanciones().add(new Cancion(JSONCanciones.getJSONObject(i).getString("nombre"), JSONCanciones.getJSONObject(i).getDouble("precio")));                
            }                        
            
            return album;
            
        } catch (SQLException ex) {   
            System.out.println("ERROR: " + ex);
            return null;
        }
    }
    
       
    /**
     * Actualizar un álbum
     * @param album
     * @return true si el album se actualizó correctamente
     */
    public boolean actualizarAlbum(Album album) {
    
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
            
            CallableStatement funcion = dbContext.prepareCall("{ call f_actualizar_album(?,?,?) }");
            funcion.setShort(1, album.getId());
            funcion.setString(2, album.getNombre());  
            funcion.setShort(3, album.getArtistaId());
            
            ResultSet respuesta = funcion.executeQuery();                        
            
            return true;
            
        } catch (SQLException ex) {        
            return false;
        }                
    }
    
    /**
     * Actualizar canciones de un álbum
     * @param album
     * @param JSONCanciones
     * @return true si se actualizan correctamente los datos
     */
    public boolean actualizarCancionesAlbum(Album album, String JSONCanciones) {
    
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
            
            CallableStatement funcion = dbContext.prepareCall("{ call f_actualizar_album(?,?,?) }");
            funcion.setShort(1, album.getId());
            funcion.setDouble(2, album.getPrecio());  
            funcion.setString(3, JSONCanciones);
            
            ResultSet respuesta = funcion.executeQuery();                        
            
            return true;
        
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
            return false;
        }
    }
}
