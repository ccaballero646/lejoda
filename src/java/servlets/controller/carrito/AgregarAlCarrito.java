/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.controller.carrito;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Producto;
import model.ProductoManager;

/**
 *
 * @author Cristhian
 */
public class AgregarAlCarrito extends HttpServlet {

    

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
        int id_producto = Integer.parseInt(request.getParameter("id_carrito"));
        int cantidadPedida = Integer.parseInt(request.getParameter("cantidad_carrito"));
        
        ProductoManager pManager = new ProductoManager();
        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        Producto p = pManager.getProducto(id_producto, con);
        
        if(cantidadPedida > p.getCantidad() || cantidadPedida < 0) {
            String url = "/producto/detalle?id=" + id_producto;
            System.out.println(url);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/producto/detalle?id=" + id_producto);
            PrintWriter out= response.getWriter();
            out.println("<font color=red>"+ "La cantidad pedida supera el stock disponible o la cantidad es negativa" +"</font>");
            rd.include(request, response);
        }
        
        p.setCantidad(cantidadPedida);
        List carrito = (List) request.getSession().getAttribute("carrito");
        if (carrito != null) {
            carrito.add(p);
            request.getSession().setAttribute("carrito", carrito);
        }
        else {
            carrito = new ArrayList<Producto>();
            carrito.add(p);
            request.getSession().setAttribute("carrito", carrito);
        }
        String url = "/carrito/listar";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
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
