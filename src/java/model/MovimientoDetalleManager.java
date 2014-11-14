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
 * @author HugoEladio
 */
public class MovimientoDetalleManager 
{
    private MovimientoDetalle detalle;

    void crearMDetalle(Producto producto, Connection con, int id_movimiento) throws ServletException 
    {
        PreparedStatement ps = null;
        try {
            ProductoManager pm = new ProductoManager();
            Producto productoBD = pm.getProducto(producto.getId_producto(), con);
            
            //La cantidad extraida del deposito pasa a la cantidad fuera del deposito
            productoBD.setCantidadDeposito(productoBD.getCantidadDeposito() - producto.getCantidad());
            productoBD.setCantidad (productoBD.getCantidad() + producto.getCantidad());
            pm.editarProducto(productoBD, con);
            
            ps = con.prepareStatement("insert into movimiento_detalle (id_movimiento, id_producto, cantidad) values (?,?,?)");
            
            ps.setInt(1, id_movimiento);
            ps.setInt(2, producto.getId_producto());
            ps.setInt(3, producto.getCantidad());            
            
            ps.execute();
            logger.info("Detalle ingresado: " + producto.getDescripcion());
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
