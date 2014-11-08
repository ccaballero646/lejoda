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
import model.Usuario;
import model.UsuarioManager;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Cristhian
 */

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    static Logger logger = Logger.getLogger(RegisterServlet.class);
     
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String nombre = request.getParameter("nombre");
        String loginName = request.getParameter("login_name");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        int tipoUsuario = Integer.parseInt(request.getParameter("tipo_usuario"));
        
        String errorMsg = null;
        if(loginName == null || loginName.equals("")){
            errorMsg = "El nombre de usuario no puede ser nulo o vacio";
        }
        if(password == null || password.equals("")){
            errorMsg = "La contraseña no puede ser nula o vacia";
        }
        if(nombre == null || nombre.equals("")){
            errorMsg = "El nombre no puede ser nulo o vacio";
        }
        if(!password.equals(password2)) {
            errorMsg = "Las contraseñas son distintas";
        }
        
        if(tipoUsuario == 0 || tipoUsuario == 1){
        } else {
            errorMsg = "Tipo de Usuario debe ser 0 (administrador) o 1 (usuario normal)";
        }
         
        if(errorMsg != null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>"+errorMsg+"</font>");
            rd.include(request, response);
        }else{
         
            Connection con = (Connection) getServletContext().getAttribute("DBConnection");
            UsuarioManager u = new UsuarioManager();
            String encryptedPassword = DigestUtils.md5Hex(password);
            Usuario user = new Usuario(nombre, loginName, encryptedPassword, tipoUsuario);
            u.crearUsuario(user, con);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=green>Registro exitoso. Favor inicie sesion.</font>");
            rd.include(request, response);        
        }
    }
}
