/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.controller.categoria;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Categoria;
import model.CategoriaManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Cristhian
 */
public class CrearCategoria extends HttpServlet {
    static Logger logger = Logger.getLogger(CrearCategoria.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String descripcion = request.getParameter("descripcion");
        String error = null;
        
        if (descripcion == null || descripcion.equals("")) {
            error = "La categoria debe tener un nombre";
        }
        if (error != null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/categoria/crear.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>"+error+"</font>");
            rd.include(request, response);
        }
        else {
            CategoriaManager c = new CategoriaManager();
            Connection con = (Connection) getServletContext().getAttribute("DBConnection");
            Categoria nueva = new Categoria(descripcion);
            c.crearCategoria(nueva, con);
            logger.info("EXITO: crear categoria: " + descripcion);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/categoria/listar");
            PrintWriter out= response.getWriter();
            out.println("<font color=green>" + "La categoria se creo con exito" + "</font>");
            rd.include(request, response);
        }       
    }
}
