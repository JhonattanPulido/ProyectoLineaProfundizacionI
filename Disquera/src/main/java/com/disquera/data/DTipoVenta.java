//Paquetes
package com.disquera.data;

//Librerias
import com.disquera.models.TipoVenta;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Capa de datos de tipo venta
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 20/11/2020
 */
public class DTipoVenta {
    
     // Variables
    
    /**
     * Variable de conexión a la base de datos
     */
    private Connection dbContext;
    
    /**
     * Variable auxiliar de tipo venta
     */
    private TipoVenta tipoVenta;
    
    /**
     * Lista de tipo de venta almacenados en la base de datos
     */
    private List<TipoVenta> listaVenta;
    
    /**
     * Constructor de la clase
     */
    public DTipoVenta() {
        
    }
    
    // Métodos
    
    /**
     * Leer todos los tipo de venta almacenados en base de datos
     * @return lista de tipo venta
     */
    public List<TipoVenta> leerTipoVenta() {
    
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
            
            CallableStatement funcion = dbContext.prepareCall("{ call f_leer_tipo_venta() }");
            
            ResultSet respuesta = funcion.executeQuery();
            
            listaVenta = new ArrayList<>();
            
            while (respuesta.next()) {
                
                tipoVenta = new TipoVenta();
                tipoVenta.setId(respuesta.getShort("id"));
                tipoVenta.setNombre(respuesta.getString("nombre"));                
                
                listaVenta.add(tipoVenta);
            }
            
            return listaVenta;
            
        } catch (SQLException ex) {        
            return null;
        }
    }
}
