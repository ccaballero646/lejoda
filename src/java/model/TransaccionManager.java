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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import org.apache.log4j.Logger;

/**
 *
 * @author Cristhian
 */
public class TransaccionManager {
    static Logger logger = Logger.getLogger(TransaccionManager.class);
    private Transaccion transaccion;
    
    public void crearTransaccion(Transaccion t, List<Producto> carrito, Connection con) throws ServletException {
        TransaccionDetalleManager tDetalle = new TransaccionDetalleManager();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("insert into transaccion(fecha, usuario, total, direccion_envio, medio_pago, nro_tarjeta, estado) values(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, new java.sql.Date(t.getFecha().getTime()));
            ps.setInt(2, t.getId_usuario());
            ps.setInt(3, (int) t.getTotal());
            ps.setString(4, t.getDireccionEnvio());
            ps.setInt(5, t.getMedioPago());
            if(t.getMedioPago() == 1 ){
                ps.setString(6, t.getNroTarjeta());
            }
            else
                ps.setString(6, "");
            ps.setString(7, "I");
            ps.execute();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    t.setId_transaccion(generatedKeys.getInt(1));
                }
            }
            
            for(Producto p : carrito) {
                tDetalle.crearDetalle(p, t, con);
            }    
            logger.info("Transaccion ingresada con exito");
        }
        catch(SQLException e) {
            rollback(t, con);
            logger.error("Problema al crear transaccion: " + e.getMessage());
            throw new ServletException("Problema al crear transaccion: " + e.getMessage());
        }
        finally {
            try {
                ps.close();
            }
            catch(SQLException e) {
                logger.error("Error al cerrar la transaccion: " + e.getMessage());
            }
        }
        
    }
    
    public void rollback(Transaccion t, Connection con) throws ServletException {
        TransaccionDetalleManager tDetalle = new TransaccionDetalleManager();
        PreparedStatement ps = null;
        tDetalle.eliminarDetalle(t.getId_transaccion(), con);
        try {
            ps = con.prepareStatement("delete transaccion where id_transaccion = ?");
            ps.setInt(1, t.getId_transaccion());
            int resultado = ps.executeUpdate();
        }
        catch(SQLException e) {
            logger.error("Error al realizar rollback: " + e.getMessage());
            throw new ServletException("Error al realizar rollback: " + e.getMessage());
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
    
    public List<Transaccion> listar(int id_usuario, Connection con) throws ServletException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Transaccion> lista = new ArrayList<>();
        try {
            ps = con.prepareStatement("select * from transaccion where usuario = ?");
            ps.setInt(1, id_usuario);
            rs = ps.executeQuery();
            while(rs.next()) {
                Transaccion t = new Transaccion(rs.getInt("id_transaccion"),
                                                rs.getDate("fecha"),
                                                rs.getInt("usuario"),
                                                rs.getDouble("total"),
                                                rs.getString("direccion_envio"),
                                                rs.getInt("medio_pago"),
                                                rs.getString("nro_tarjeta"));
                lista.add(t);
                logger.info("Listado de transacciones exitoso");
            }
        }
        catch(SQLException e) {
            logger.error("Error listando transacciones: " + e.getMessage());
            throw new ServletException("Error listando transacciones: " + e.getMessage());
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
}
