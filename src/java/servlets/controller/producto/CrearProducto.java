/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.controller.producto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoriaManager;
import model.Producto;
import model.ProductoManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Cristhian
 */
public class CrearProducto extends HttpServlet {
    static Logger logger = Logger.getLogger(CrearProducto.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
        CategoriaManager c = new CategoriaManager();
        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        List catList = c.listarCategorias(con);
        logger.info("Listado de categorias exitoso");
        request.setAttribute("lista", catList);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/producto/crear.jsp");
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
        
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        int cantidadDeposito = Integer.parseInt(request.getParameter("cantidadDeposito"));
        int categoria = Integer.parseInt(request.getParameter("categoria"));
        
        String error = null;
        
        if(descripcion == null || descripcion.equals("")){
            error = "El nombre no puede ser nulo";
        }
        if(precio < 0){
            error = "El precio no puede negativo";
        }
        if(cantidad < 0) {
            error = "La cantidad no puede negativa";
        }
        if(cantidadDeposito < 0) {
            error = "La cantidad no puede negativa";
        }
        
        if(error != null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/producto/crear");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>"+error+"</font>");
            rd.include(request, response);
        }
        
        ProductoManager pManager = new ProductoManager();
        Producto p = new Producto(descripcion, categoria, precio, cantidad, cantidadDeposito);
        Connection con = (Connection)getServletContext().getAttribute("DBConnection");
        pManager.crearProducto(p, con);
        logger.info("Producto creado con exito: " + p.getDescripcion());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/productos/listar");
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
