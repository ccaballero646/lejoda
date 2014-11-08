/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Clase utilitaria que establece la conexion a la base de datos via DataSource
 * 
 * @author ccaballero
 */
public class DBConnectionManager {
    
    private Connection connection;
    Context envContext = null;
	
	public DBConnectionManager() throws SQLException{
            try {
                //buscamos via JNDI el recurso jdbc
                envContext = new InitialContext();
                DataSource ds = (DataSource)envContext.lookup("java:/comp/env/jdbc/paronline");
                connection = ds.getConnection();
            } catch (NamingException ex) {
                ex.printStackTrace();
            }  
	}
	
    public Connection getConnection(){
            return this.connection;
    }
}
