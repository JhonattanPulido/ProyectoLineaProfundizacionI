/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disquera.data;

import com.disquera.models.Album;
import com.disquera.models.Carrito;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class DCarrito implements Serializable {
    
     /**
     * Variable de conexión a la base de datos
     */
    private Connection dbContext;
    
     /**
     * Variable de auxiliar de carrito
     */
    private Carrito carrito;
    
        /**
     * Variable auxiliar de Album
     */
    private Album album;
    /**
     * Todas los compras almacenados en la base de datos
     */
    private List<Carrito> listaCarrito;

    /**
     * Constructor
     */
    public DCarrito() {
    }
    
     /**
     * Crear Carrito
     * @param carrito
     * @return true si el carrito se creo correctamente
     */
    public boolean crearCarrito(Carrito carrito) {
    
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
            
            CallableStatement funcion = dbContext.prepareCall("{ call f_crear_compra_carrito(?,?,?,?) }");
            funcion.setShort(1, carrito.getAlbumId());                          
            funcion.setString(2, carrito.getCanciones());  
            funcion.setDouble(3, carrito.getPrecio());
            funcion.setBoolean(4, carrito.isEstadoCompra());
            
            ResultSet respuesta = funcion.executeQuery();                        
            
            return true;
            
        } catch (SQLException ex) {   
            System.out.println("ERROR :" + ex);
            return false;
        }
    }
    
    /**
     * Leer todos los articulos comprador almacenados en base de datos
     * @return lista de carrito
     */
    public List<Carrito> leerCarrito() {
    
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
            
            CallableStatement funcion = dbContext.prepareCall("{ call f_leer_carrito() }");
            
            ResultSet respuesta = funcion.executeQuery();
            
            listaCarrito = new ArrayList<>();
            
            while (respuesta.next()) {
            
                carrito = new Carrito();                
                carrito.setId(respuesta.getShort("id"));
                carrito.setAlbumId(respuesta.getShort("album_id"));               
                carrito.setCanciones(respuesta.getString("canciones"));   
                carrito.setPrecio(respuesta.getDouble("precio"));
                carrito.setEstadoCompra(respuesta.getBoolean("estado_compra"));
                
                album = new Album();
                album.setId(respuesta.getShort("album_id"));
                album.setNombre(respuesta.getString("album_nombre"));
                carrito.setAlbum(album);
                
                listaCarrito.add(carrito);
            }
            
            return listaCarrito;
            
        } catch (SQLException ex) {        
            return null;
        }
    }
    
}
