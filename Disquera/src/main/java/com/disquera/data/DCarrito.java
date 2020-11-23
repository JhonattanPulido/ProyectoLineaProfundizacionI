/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.disquera.data;

import com.disquera.models.Carrito;
import com.disquera.models.TipoVenta;
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
     * Variable de auxiliar de tipo de venta
     */
    private TipoVenta tipoVenta;
    
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
            
            CallableStatement funcion = dbContext.prepareCall("{ call f_crear_compra_carrito(?,?,?) }");
            funcion.setDouble(1, carrito.getPrecio());                          
            funcion.setShort(2, carrito.getTipoVentaId());  
            funcion.setString(3, carrito.getDescripcion());
            
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
            
            CallableStatement funcion = dbContext.prepareCall("{ call f_leer_carrito_compras() }");
            
            ResultSet respuesta = funcion.executeQuery();
            
            listaCarrito = new ArrayList<>();
            
            while (respuesta.next()) {
            
                carrito = new Carrito();                
                carrito.setId(respuesta.getShort("id"));
                carrito.setPrecio(respuesta.getDouble("precio"));               
                carrito.setTipoVentaId(respuesta.getShort("tipo_venta_id"));   
                carrito.setDescripcion(respuesta.getString("descripcion"));
                
                tipoVenta = new TipoVenta();
                tipoVenta.setId(respuesta.getShort("tipo_venta_id"));
                tipoVenta.setNombre(respuesta.getString("tipo_venta_nombre"));
                carrito.setTipoVenta(tipoVenta);
                
                listaCarrito.add(carrito);
            }
            
            return listaCarrito;
            
        } catch (SQLException ex) {        
            return null;
        }
    }
}
