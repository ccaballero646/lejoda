/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;
import org.apache.log4j.Logger;

/**
 *
 * @author ccaballero
 */
public class AuthFilter implements Filter {
    
    private Logger logger = Logger.getLogger(AuthFilter.class);
	
	public void init(FilterConfig fConfig) throws ServletException {
		logger.info("AuthenticationFilter initialized");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		logger.info("Requested Resource::"+uri);
		
		HttpSession session = req.getSession(false);
                Usuario user = (Usuario)session.getAttribute("usuario");
		
		/*
                TODO: configurar el filtro para que escuche solo la pagina de confirmar compra
                */
                if(user == null) {
			logger.error("Unauthorized access request");
                        session.setAttribute("redirigir", uri);
			res.sendRedirect("/paronline/login.jsp");
		}else{
			
			chain.doFilter(request, response);
		}
		
		
	}

	public void destroy() {
		
	}

}
