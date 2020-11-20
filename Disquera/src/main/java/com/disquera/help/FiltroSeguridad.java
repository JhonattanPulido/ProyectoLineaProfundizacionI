// Paquetes
package com.disquera.help;

// Modelos
import com.disquera.models.Usuario;

// Librerías
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filtro de seguridad para validar el inicio de sesión en las páginas
 * @author Sandra Moreno - Jhonattan Pulido
 * @version 1.0.0
 * @since 20/11/2020
 */
public class FiltroSeguridad implements Filter {

    // Variables 
    
    /**
     * Almacena los datos del usuario que inició sesión
     */
    private Usuario usuario;
    
    /**
     * Url de la página que se esta validando
     */
    private String url;
         
    /**
     * Constructor de la clase
     */
    public FiltroSeguridad() {
        
    }        
    
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;                
        
        usuario = (Usuario) req.getSession().getAttribute("GqyZDngHH2");                                                               
        
        if (usuario != null) {
            
            url = req.getRequestURL().toString();
            if (url.contains("admin") && usuario.getRolId() == 1)
                chain.doFilter(request, response);
            else if (url.contains("comprador") && usuario.getRolId() == 2)
                chain.doFilter(request, response); 
            else
                res.sendRedirect(req.getContextPath() + "/faces/errores/401.xhtml");
            
        } else
            res.sendRedirect(req.getContextPath() + "/iniciar-sesion.xhtml");
    }

    @Override
    public void destroy() {
        
    }        
}
