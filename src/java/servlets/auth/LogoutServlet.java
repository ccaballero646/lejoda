/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Cristhian
 */

public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static Logger logger = Logger.getLogger(LogoutServlet.class);
        
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("JSESSIONID")){
                    logger.info("JSESSIONID="+cookie.getValue());
                    break;
                }
            }
        }
        //Invalidar la sesion si existe
        HttpSession session = request.getSession(false);
        logger.info("Usuario= "+session.getAttribute("usuario"));
        if(session != null){
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }
}
