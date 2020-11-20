// Paquetes
package com.disquera.data;

// Modelos
import com.disquera.models.IniciarSesion;
import com.disquera.models.Usuario;
import java.sql.CallableStatement;

// Librerías
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Capa de datos para iniciar sesión
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 19/11/2020
 */
public class DIniciarSesion {   
    
    // Variables
    
    /**
     * Variable de conexión a la base de datos
     */
    private Connection dbContext;
    
    /**
     * Variable auxiliar de usuario
     */
    private Usuario usuario;
    
    /**
     * Constructor de la clase
     */
    public DIniciarSesion() {
                
    }
    
    // Métodos
    
    /**
     * Validar inicio de sesión contra base de datos
     * @param datos
     * @return los datos de un usuario
     */
    public Usuario iniciarSesion(IniciarSesion datos) {
    
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
            
            CallableStatement funcion = dbContext.prepareCall("{ call f_iniciar_sesion(?,?) }");
            funcion.setString(1, datos.getNombreUsuario());
            funcion.setString(2, datos.getClave());            
            
            ResultSet respuesta = funcion.executeQuery();                        
            
            while (respuesta.next()) {
            
                usuario = new Usuario();
                usuario.setId(respuesta.getShort("id"));
                usuario.setNombre(respuesta.getString("nombre"));
                usuario.setNombreUsuario(respuesta.getString("nombre_usuario"));
                usuario.setRolId(respuesta.getShort("rol_id"));
            }
            
            return usuario;
            
        } catch (SQLException ex) {                  
            return null;
        }
    }
}
