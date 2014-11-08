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
import javax.servlet.annotation.WebServlet;
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
@WebServlet(name = "EditarCategoria", urlPatterns = {"/categoria/editar"})
public class EditarCategoria extends HttpServlet {
    static Logger logger = Logger.getLogger(EditarCategoria.class);


    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoriaManager cat = new CategoriaManager();
        int id = Integer.parseInt(request.getParameter("id"));
        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        Categoria c = cat.getCategoria(id, con);
        request.setAttribute("id", c.getId_categoria());
        request.setAttribute("descripcion", c.getDescripcion());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/categoria/editar.jsp");
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoriaManager cat = new CategoriaManager();
        int id = Integer.parseInt(request.getParameter("id"));
        String descripcion = request.getParameter("descripcion");
        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        Categoria c = cat.getCategoria(id, con);
        c.setDescripcion(descripcion);
        cat.editarCategoria(c, con);
        logger.info("Categoria actualizada: " + c.getDescripcion());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/categoria/listar");
        rd.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
