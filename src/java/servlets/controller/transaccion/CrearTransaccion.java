/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.controller.transaccion;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Producto;
import model.ProductoManager;
import model.Transaccion;
import model.TransaccionManager;
import model.Usuario;

/**
 *
 * @author Cristhian
 */
public class CrearTransaccion extends HttpServlet {

   
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
        

        List <Producto>carrito = (List) request.getSession().getAttribute("carrito");
        double total = 0;

        for(Producto p : carrito) {
            total = total + (p.getCantidad() * p.getPrecio());    
        }
        request.setAttribute("total", total);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/transaccion/crear.jsp");
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
        
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        List <Producto>carrito = (List) request.getSession().getAttribute("carrito");
        String dirEnvio = request.getParameter("dirEnvio");
        int medioPago = Integer.parseInt(request.getParameter("medioPago"));
        String nroTarjeta = request.getParameter("nroTarjeta");
        double total = Double.parseDouble(request.getParameter("total"));
        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        TransaccionManager tManager = new TransaccionManager();
        
        Date fecha = new Date();
        Transaccion t = new Transaccion(fecha, user.getId_usuario(), total, dirEnvio, medioPago, nroTarjeta);
        tManager.crearTransaccion(t, carrito, con);
        request.getSession().removeAttribute("carrito");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/transaccion/gracias.jsp");
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
