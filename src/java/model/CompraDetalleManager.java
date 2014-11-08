/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    
}
