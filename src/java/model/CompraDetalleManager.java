/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import static model.TransaccionDetalleManager.logger;

/**
 *
 * @author Cristhian
 */
public class CompraDetalleManager {
    private CompraDetalle detalle;
    
    public void crearDetalle (CompraDetalle cd, Connection con) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(null);
        }
        catch(SQLException e) {
            
        }
    }

    
    void crearCDetalle(Producto p, Connection con, int id_compra) throws ServletException 
    {
        PreparedStatement ps = null;
        try {
            ProductoManager pm = new ProductoManager();
            Producto bd = pm.getProducto(p.getId_producto(), con);
            bd.setCantidad(bd.getCantidad() + p.getCantidad());
            pm.editarProducto(bd, con);
            
            ps = con.prepareStatement("insert into compra_detalle (id_compra, producto, cantidad, subtotal) values (?,?,?,?)");
            
            ps.setInt(1, id_compra);
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
    
}
