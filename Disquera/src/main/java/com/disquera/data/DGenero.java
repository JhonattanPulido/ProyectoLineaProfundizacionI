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
 * Capa de datos de géneros musicales
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 20/11/2020
 */
public class DGenero implements Serializable {    
    
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
     * Lista de géneros musicales almacenados en la base de datos
     */
    private List<Genero> listaGeneros;
    
    /**
     * Constructor de la clase
     */
    public DGenero() {
        
    }
    
    // Métodos
    
    /**
     * Leer todos los géneros musicales almacenados en base de datos
     * @return lista de artistas
     */
    public List<Genero> leerGenerosMusicales() {
    
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
            
            CallableStatement funcion = dbContext.prepareCall("{ call f_leer_generos_musicales() }");
            
            ResultSet respuesta = funcion.executeQuery();
            
            listaGeneros = new ArrayList<>();
            
            while (respuesta.next()) {
                
                genero = new Genero();
                genero.setId(respuesta.getShort("id"));
                genero.setNombre(respuesta.getString("nombre"));                
                
                listaGeneros.add(genero);
            }
            
            return listaGeneros;
            
        } catch (SQLException ex) {        
            return null;
        }
    }
}
