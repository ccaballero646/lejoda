/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;
import model.UsuarioManager;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author ccaballero
 */
 

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    static Logger logger = Logger.getLogger(LoginServlet.class);
     
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login_name = request.getParameter("login_name");
        String password = request.getParameter("password");
        String errorMsg = null;
        if(login_name == null || login_name.equals("")){
            errorMsg ="El nombre de usuario no puede ser nulo o vacio";
        }
        if(password == null || password.equals("")){
            errorMsg = "El Password no puede ser nulo o vacio";
        }
         
        if(errorMsg != null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>"+errorMsg+"</font>");
            rd.include(request, response);
        }else{
         
            UsuarioManager u = new UsuarioManager();
            Connection con = (Connection) getServletContext().getAttribute("DBConnection");
            Usuario user = u.obtenerUsuario(login_name, con);
            String encryptedPassword = DigestUtils.md5Hex(password);
            if (user != null && user.getPassword().equals(encryptedPassword)) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", user);
                String url = (String)session.getAttribute("redirigir");
                if(url != null) {
                    logger.info("redirigir a: " + url);
                    response.sendRedirect(url);
                }
                else
                    response.sendRedirect("index.jsp");
            }
            else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
                PrintWriter out= response.getWriter();
                logger.error("Usuario no encontrado= "+login_name);
                out.println("<font color=red>Usuario o contraseña no validos. Favor reintente.</font>");
                rd.include(request, response);
            }
        }
    }
}
