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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

/**
 *
 * @author Cristhian
 */
public class CompraManager 
{
    private Compra compra;
    
    public void crearCompra(Compra c, List<Producto> lista, Connection con) throws ServletException {
        CompraDetalleManager cDetalle = new CompraDetalleManager();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("insert into compra (usuario, total, fecha) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, c.getUsuario());
            ps.setInt(2, c.getTotal());
            ps.setTimestamp(3, new java.sql.Timestamp(c.getFecha().getTime()));
            ps.execute();
            //obtener id insertado
            int id_compra=0;
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id_compra=generatedKeys.getInt(1);
                    c.setIdCompra(id_compra);
                }
            }
            
            for (Producto p:lista)
            {
                cDetalle.crearCDetalle(p, con, id_compra);
            }
            
            //TODO por cada detalle, insertar en la bd
            
        }
        catch(SQLException e) {
            //ALGO
        }
        
    }
}
