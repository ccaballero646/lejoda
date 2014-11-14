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
public class TransaccionDetalleManager {
    static Logger logger = Logger.getLogger(TransaccionDetalleManager.class);
    
    public void crearDetalle (Producto p, Transaccion t, Connection con) throws ServletException {
        PreparedStatement ps = null;
        try {
            ProductoManager pm = new ProductoManager();
            Producto bd = pm.getProducto(p.getId_producto(), con);
            //Los productos vendidos se descuentan del stock fuera del deposito
            bd.setCantidad(bd.getCantidad() - p.getCantidad());
            pm.editarProducto(bd, con);
            
            ps = con.prepareStatement("insert into transaccion_detalle (transaccion, producto, cantidad, subtotal) values (?,?,?,?)");
            
            ps.setInt(1, t.getId_transaccion());
            ps.setInt(2, p.getId_producto());
            ps.setInt(3, p.getCantidad());
            ps.setInt(4, (int)(p.getCantidad() * p.getPrecio()));
            
            ps.execute();
            logger.info("Detalle ingresado: " + p.getDescripcion());
        }
        catch(SQLException e) {
            logger.error("Problema al isertar detalle: " + e.getMessage());
            throw new ServletException("Problema al insertar detalle: " + e.getMessage());
        }
        finally {
            try {
                ps.close();
            }
            catch(SQLException e) {
                logger.error("Problema con la base de datos: " + e.getMessage());
            }
        }
    }
    
    public List<TransaccionDetalle> getDetalles(int  t, Connection con) throws ServletException {
        List<TransaccionDetalle> lista = new ArrayList<>();
        ProductoManager pManager = new ProductoManager();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("select * from transaccion_detalle where transaccion = ?");
            ps.setInt(1, t);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                TransaccionDetalle det = new TransaccionDetalle(rs.getInt(1),
                                                                rs.getInt(2),
                                                                rs.getInt(3),
                                                                rs.getInt(4),
                                                                rs.getInt(5));
                Producto p = pManager.getProducto(det.getId_producto(), con);
                lista.add(det);
            }
            
            logger.info("Detalles obtenidos exitosamente");
        }
        catch(SQLException e) {
            logger.error("Problema al obtener los detalles: " + e.getMessage());
            throw new ServletException("Problema al obtener los detalles: " + e.getMessage());
        }
        finally {
            try {
                ps.close();
                rs.close();
            }
            catch(SQLException e) {
                logger.error("Problema con la base de datos: " + e.getMessage());
            }
        }
        return lista;
    }
    
    public void eliminarDetalle(int idTransaccion, Connection con) throws ServletException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("delete transaccion_detalle where transaccion = ?");
            ps.setInt(1, idTransaccion);
            int resultado = ps.executeUpdate();
        }
        catch(SQLException e) {
            logger.error("Error eliminando detalles: " + e.getMessage());
            throw new ServletException("Error eliminando detalles: " + e.getMessage());
        }
        finally {
            try {
                ps.close();
            }
            catch(SQLException e) {
                logger.error("Problema con la base de datos: " + e.getMessage());
            }
        }
    }
    
}
