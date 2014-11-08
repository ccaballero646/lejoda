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

/**
 *
 * @author Cristhian
 */
public class CompraManager {
    private Compra compra;
    
    public void crearCompra(Compra c, List<Producto> lista, Connection con) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("insert into compra (usuario, total, fecha) values (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, c.getUsuario());
            ps.setInt(2, c.getTotal());
            ps.setTimestamp(3, new java.sql.Timestamp(c.getFecha().getTime()));
            ps.execute();
            //obtener id insertado
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    c.setIdCompra(generatedKeys.getInt(1));
                }
            }
            
            //TODO por cada detalle, insertar en la bd
            
        }
        catch(SQLException e) {
            //ALGO
        }
        
    }
}
