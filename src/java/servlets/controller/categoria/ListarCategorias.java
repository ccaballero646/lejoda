/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.controller.categoria;

import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
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
@WebServlet(name = "ListarCategorias", urlPatterns = {"/categoria/listar"})
public class ListarCategorias extends HttpServlet {
    
    static Logger logger = Logger.getLogger(ListarCategorias.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoriaManager c = new CategoriaManager();
        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        List catList = c.listarCategorias(con);
        for (Iterator it = catList.iterator(); it.hasNext();) {
            Categoria cat = (Categoria)it.next();
            System.out.println("categoria: "+cat.getId_categoria() + " " + cat.getDescripcion());
        }
        request.setAttribute("lista", catList);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/categoria/listar.jsp");
        logger.info("Listado de categorias exitoso");
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
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
