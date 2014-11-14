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
import javax.servlet.ServletException;

/**
 *
 * @author HugoEladio
 */
public class MovimientoManager {

    private Movimiento movimiento;
    
    public void crearMovimiento(Movimiento mov, List<Producto> lista, Connection con) throws ServletException 
    {
        MovimientoDetalleManager mDetalle= new MovimientoDetalleManager();
        PreparedStatement ps = null;
        
         try {
            ps = con.prepareStatement("insert into movimiento (usuario, fecha) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, mov.getUsuario());
            ps.setTimestamp(2, new java.sql.Timestamp(mov.getFecha().getTime()));
            ps.execute();
            //obtener id insertado
            int id_movimiento=0;
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) 
            {
                if (generatedKeys.next()) {
                    id_movimiento=generatedKeys.getInt(1);
                    mov.setId_movimiento(id_movimiento);                    
                }
            }
            
            for (Producto p:lista)
            {
                mDetalle.crearMDetalle(p, con, id_movimiento);
            }            
            //TODO por cada detalle, insertar en la bd            
        }
        catch(SQLException e) {
            //ALGO
        }
    }    
}
