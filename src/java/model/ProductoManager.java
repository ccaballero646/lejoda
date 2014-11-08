/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import org.apache.log4j.Logger;

/**
 *
 * @author Cristhian
 */
public class ProductoManager {
    static Logger logger = Logger.getLogger(ProductoManager.class);
    private Producto producto;
    private List productoList;
    
    public void crearProducto(Producto p, Connection con) throws ServletException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("insert into producto(nombre, categoria, precio, cantidad) values (?,?,?,?)");
            ps.setString(1, p.getDescripcion());
            ps.setInt(2, p.getCategoria());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getCantidad());
            ps.execute();
            logger.info("Producto insertado: " + p.getDescripcion());
        }
        catch(SQLException e) {
            logger.error("Problema con la conexion a la BD: " + e.getMessage());
            throw new ServletException("Problema con la conexion a la BD");
        }
        finally {
            try {
                ps.close();
            }
            catch(SQLException e) {
                logger.error("Problema al cerrar PreparedStatement");
            }
        }
    }
    
    public List<Producto> listarProductos(Connection con) throws ServletException {
        productoList = new ArrayList<Producto>();
        CategoriaManager catManager = new CategoriaManager();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("select * from producto");
            rs = ps.executeQuery();
            while(rs.next()) {
                Producto p = new Producto(rs.getInt("id_producto"), rs.getString("nombre"), rs.getInt("categoria"), rs.getDouble("precio"), rs.getInt("cantidad"));
                Categoria cat = catManager.getCategoria(p.getCategoria(), con);
                p.setCat(cat);
                productoList.add(p);
            }
        }
        catch(SQLException e) {
            logger.error("Problema al obtener los resultados de la consulta listarProductos: " + e.getMessage());
            throw new ServletException("Problema con la BD");
        }
        finally {
            try {
                ps.close();
                rs.close();
            }
            catch(SQLException e) {
                logger.error("Problema con la BD");
            }
        }
        return productoList;
    }
    
    public void editarProducto(Producto p, Connection con) throws ServletException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("update producto set nombre=?, categoria=?, precio=?, cantidad=? where id_producto=?");
            ps.setString(1, p.getDescripcion());
            ps.setInt(2, p.getCategoria());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getCantidad());
            ps.setInt(5, p.getId_producto());
            ps.execute();
            logger.info("Producto editado con exito, ID= " + p.getId_producto());
        }
        catch(SQLException e) {
            logger.error("Problema con la BD: " + e.getMessage());
            throw new ServletException("Problema con la BD");
        }
        finally {
            try {
                ps.close();
            }
            catch(SQLException e) {
                logger.error("Problema al cerrar PreparedStatement");
            }
        }
    }
    
    public Producto getProducto(int id_producto, Connection con) throws ServletException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        CategoriaManager cManager = new CategoriaManager();
        try {
            ps = con.prepareStatement("select * from producto where id_producto=?");
            ps.setInt(1, id_producto);
            rs = ps.executeQuery();
            if(rs != null && rs.next()) {
                this.producto = new Producto(rs.getInt("id_producto"),
                                             rs.getString("nombre"),
                                             rs.getInt("categoria"),
                                             rs.getDouble("precio"),
                                             rs.getInt("cantidad"));
                
                producto.setCat(cManager.getCategoria(producto.getCategoria(), con));
                logger.info("Producto encontrado: " + producto.getDescripcion());
            }
        }
        catch(SQLException e) {
            logger.error("Problema obteniendo producto: " + e.getMessage());
            throw new ServletException("Problema obteniendo producto: " + e.getMessage());
        }
        finally {
            try {
                ps.close();
                rs.close();
            }
            catch(SQLException e) {
                logger.error("Problema al cerrar la conexion: " + e.getMessage());
            }
        }
        return producto;
    }
    
}
