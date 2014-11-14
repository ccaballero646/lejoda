/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.controller.producto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Producto;
import model.ProductoManager;
import model.Usuario;
import org.apache.log4j.Logger;

/**
 *
 * @author Cristhian
 */
public class ProductoDetalle extends HttpServlet {
    static Logger logger = Logger.getLogger(ProductoDetalle.class);

   
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
        int id_producto = Integer.parseInt(request.getParameter("id"));
        ProductoManager pManager = new ProductoManager();
        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        Producto p = pManager.getProducto(id_producto, con);
        request.setAttribute("producto", p);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/producto/detalle.jsp");
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
        int id_producto = Integer.parseInt(request.getParameter("id"));
        String descripcion = request.getParameter("descripcion");
        double precio = Double.parseDouble(request.getParameter("precio"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        int cantidadDeposito = Integer.parseInt(request.getParameter("cantidadDeposito"));
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        
        //si el usuario no es administrador no se pueden modificar los detalles del producto
        if (user==null || user.getTipo_usuario() != 0) {
            String url = "/producto/detalle?id=" + id_producto;
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            PrintWriter out= response.getWriter();
            out.println("<font color=red>"+ "Usted no es un usuario administrador. Favor cierre sesion e ingrese con una cuenta de administrador" +"</font>");
            rd.include(request, response);
        }
        
        ProductoManager pManager = new ProductoManager();
        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        Producto p = pManager.getProducto(id_producto, con);
        p.setDescripcion(descripcion);
        p.setPrecio(precio);
        p.setCantidad(cantidad);
        p.setCategoria(cantidadDeposito);
        pManager.editarProducto(p, con);
        
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
