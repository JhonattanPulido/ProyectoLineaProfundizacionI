// Paquetes
package com.disquera.controllers;

// Modelos
import com.disquera.help.Constantes;
import com.disquera.help.FacesUtils;
import com.disquera.logic.LArtista;
import com.disquera.logic.LGenero;
import com.disquera.logic.LIniciarSesion;
import com.disquera.models.Artista;
import com.disquera.models.Genero;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// Librerías
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.CellEditEvent;
import org.primefaces.model.file.UploadedFile;

/**
 * Controlador de artistas para administrador
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 20/11/2020
 */
@Named(value = "artistasAdministradorController")
@ViewScoped
public class ArtistasAdministradorController implements Serializable {

    // Variables        
    
    /**
     * Almacena los datos de un artista nuevo
     */
    private Artista artista;
        
    /**
     * Faces context
     */
    private FacesContext facesContext;                 
    
    /**
     * Lista de géneros musicales almacenados en base de datos
     */
    private List<Genero> listaGeneros;
    
    /**
     * Lista de artistas almacenados en base de datos
     */
    private List<Artista> listaArtistas;
    
    /**
     * Lista de nacionalidades
     */
    private List<String> listaNacionalidades;
    
    /**
     * Imagén del artista
     */
    private UploadedFile imagen;
    
    /**
     * Almacena la ruta en la que se guardará la imagen
     */
    private String rutaImagen;       
    
    /**
     * Constructor del controlador
     */
    public ArtistasAdministradorController() {
     
        artista = new Artista();  
        listaArtistas = new LArtista().leerArtistas();
        listaGeneros = new LGenero().leerGenerosMusicales();
        
        listaNacionalidades = new ArrayList<>();
        listaNacionalidades.add("Colombiana");
        listaNacionalidades.add("Mexicana");
        listaNacionalidades.add("Argentina");
        listaNacionalidades.add("Estadounidense");
        listaNacionalidades.add("Francesa");
        listaNacionalidades.add("Española");
    }
    
    // Métodos
    
    /**
     * Crear nuevo artista     
     */
    public void crearArtista() {            
        
        if (imagen.getSize() > 0) {                                
            if (nuevaImagen()) {
                if (new LArtista().crearArtista(artista)) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Artista", "Artista añadido correctamente"));
                    listaArtistas = new LArtista().leerArtistas();
                } else
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Artista", "El artista no se ha podido crear correctamente"));
            } else
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Imagen", "Error al cargar la imagen, intentelo nuevamente"));
        } else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Imagen", "Debe añadir una imagen del artista"));                        
    }        
    
    /**
     * Subir imágen al servidor
     * @return verdadero si la imagen se cargó correctamente
     */
    public boolean nuevaImagen() {
        
        if (guardarImagen()) {            
            artista.setImagen(Constantes.SEPARADOR + rutaImagen);
            rutaImagen = "";
            return true;
        } else
            return false;
    }
    
    /**
     * Genera la ruta en la cual se guardará la imagen
     * @return true si la imágen se guarda correctamente
     */
    public boolean guardarImagen() {
    
        try {
        
            rutaImagen = Constantes.URL;
            File folder = new File(FacesUtils.getPath() + rutaImagen);
            
            if (!folder.exists())
                folder.mkdirs();
                                    
            copyFile(artista.getNombre() + "_" + artista.getNacionalidad() + ".jpg", imagen.getInputStream());
            
            return true;
            
        } catch (IOException ex) {
            
            System.out.println("Error: " + ex);
            return false;
        }                
    }
    
    /**
     * Guarda la imagen en el servidor
     * @param fileName - Nombre del archivo
     * @param in - Guarda los datos de la imagen
     */
    public void copyFile(String fileName, InputStream in) {
    
        try {
        
            rutaImagen = rutaImagen + Constantes.SEPARADOR + fileName;
            System.out.println("RUTA DE IMAGEN: " + rutaImagen);
            OutputStream out = new FileOutputStream(new File(FacesUtils.getPath() + rutaImagen));
            
            int read = 0;
            byte[] bytes = new byte[1024];
            
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            
            in.close();
            out.flush();
            out.close();
            
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }
        
    /**
     * Actualizar artista
     * @param event Datos actualizados del artista
     */
    public void onCellEdit(CellEditEvent event) {
        
        //Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();        
        
        Artista artistaActualizado = listaArtistas.get(event.getRowIndex());        
        
        switch (event.getColumn().getHeaderText()) {
            
            case "Nombre":
                artistaActualizado.setNombre(newValue.toString());
                break;
                
            case "Género musical":
                artistaActualizado.setGeneroId((short) newValue);
                break;
                
            case "Nacionalidad":
                artistaActualizado.setNacionalidad(newValue.toString());
                break;                                                            
        }                 
        
        if (new LArtista().actualizarArtista(artistaActualizado)) {
            listaArtistas = new LArtista().leerArtistas();            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Artista", "Artista actualizado correctamente"));
        } else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Artista", "No se pudó actualizar el artista"));
    }          
    
    /**
     * Cerrar sesión del usuario     
     * @throws java.io.IOException
     */
    public void cerrarSesion() throws IOException {
        new LIniciarSesion().cerrarSesion();
        facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().redirect("faces/iniciar-sesion.xhtml");
    }
    
    // Métodos Set & Get

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }     

    public List<Genero> getListaGeneros() {
        return listaGeneros;
    }

    public void setListaGeneros(List<Genero> listaGeneros) {
        this.listaGeneros = listaGeneros;
    }        

    public UploadedFile getImagen() {
        return imagen;
    }

    public void setImagen(UploadedFile imagen) {
        this.imagen = imagen;
    }        

    public List<Artista> getListaArtistas() {
        return listaArtistas;
    }

    public void setListaArtistas(List<Artista> listaArtistas) {
        this.listaArtistas = listaArtistas;
    }        

    public List<String> getListaNacionalidades() {
        return listaNacionalidades;
    }

    public void setListaNacionalidades(List<String> listaNacionalidades) {
        this.listaNacionalidades = listaNacionalidades;
    }        
}
