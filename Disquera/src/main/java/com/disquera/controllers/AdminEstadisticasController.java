// Paquetes
package com.disquera.controllers;

// Librerías
import com.disquera.logic.LCarrito;
import com.disquera.models.Album;
import com.disquera.models.Carrito;
import com.disquera.models.Grafico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.chart.PieChartModel;

/**
 * Controlador de la página estadísticas
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 26/11/2020
 */
@Named(value = "adminEstadisticasController")
@ViewScoped
public class AdminEstadisticasController implements Serializable {

    // Variables
    
    /**
     * Lista de compras realizadas por usuarios
     */
    private List<Carrito> listaCompras;
    
    /**
     * Lista de datos para mostrar en la gráfica
     */
    private List<Grafico> listaDatos;
    
    /**
     * Lista de artistas que se mostrarán en la gráfica
     */
    private List<Album> listaAlbumes;
    
    /**
     * Modelo del gráfico
     */
    private PieChartModel torta;
    
    /**
     * Constructor del controlador
     */
    public AdminEstadisticasController() {
        
        listaCompras = new LCarrito().leerTodoCarrito();
        procesarDatos();
    }
    
    // Métodos
    
    /**
     * Procesar datos para mostrarlos en el gráfico de torta
     */
    public void procesarDatos() {
    
        listaDatos = new ArrayList<>();
        listaAlbumes = new ArrayList<>();
        
        boolean guardarAlbum;
        
        for (Carrito compra: listaCompras) {
            
            guardarAlbum = true;
            
            for (Album album: listaAlbumes)
                if (compra.getAlbumId() == album.getId())
                    guardarAlbum = false;
            
            if (guardarAlbum)
                listaAlbumes.add(compra.getAlbum());
        }
        
        for (Album album: listaAlbumes)
            listaDatos.add(new Grafico(album.getNombre(), calcularCantidadAlbumes(album)));
        
        generarGraficoTorta();
    }
    
    /**
     * Calcular la cantidad de veces que se compró un álbum
     * @param album - Datos del álbum que se quiere procesar
     * @return cantidad de veces que se compro un álbum
     */
    public short calcularCantidadAlbumes(Album album) {
    
        short cantidad = 0;
        
        for (Carrito compra: listaCompras)
            if (compra.getAlbumId() == album.getId())
                cantidad++;
                
        return cantidad;
    }
    
    /**
     * Generar gráfico de torta     
     */
    public void generarGraficoTorta() {
        
        torta = new PieChartModel();
        
        for (Grafico dato: listaDatos)
            torta.set(dato.getCategoria(), dato.getCantidad());
        
        torta.setTitle("Porcentaje de venta por álbum");
        torta.setLegendPosition("e");
        torta.setFill(true);
        torta.setShowDataLabels(true);
        torta.setDiameter(150);
    }
    
    // Métodos Set & Get

    public List<Carrito> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(List<Carrito> listaCompras) {
        this.listaCompras = listaCompras;
    }

    public PieChartModel getTorta() {
        return torta;
    }

    public void setTorta(PieChartModel torta) {
        this.torta = torta;
    }        
}
