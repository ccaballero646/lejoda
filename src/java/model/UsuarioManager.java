/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
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
public class UsuarioManager {
    
    private static final long serialVersionUID = 1L;
    static Logger logger = Logger.getLogger(UsuarioManager.class);
    private Usuario user;
    private List userList;
    
    public void crearUsuario (Usuario u,Connection con) throws ServletException, IOException {        
        
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("insert into usuario(nombre,login_name,password, tipo_usuario) values (?,?,?,?)");
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getLoginName());
            ps.setString(3, u.getPassword());
            ps.setInt(4, u.getTipo_usuario());
             
            ps.execute();
             
            logger.info("Usuario registrado= "+u.getLoginName());
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Problema db");
            throw new ServletException("Problema con la conexion a la base de datos.");
        }finally{
            try {
                ps.close();
            } catch (SQLException e) {
                logger.error("SQLException al cerrar PreparedStatement");
            }
        }
    }
    
    public Usuario obtenerUsuario (String loginName, Connection con) throws ServletException, IOException {
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("select * from usuario where login_name=? limit 1");
            ps.setString(1, loginName);
            rs = ps.executeQuery();
             
            if(rs != null && rs.next()){
                 
                this.user = new Usuario(rs.getInt("id_usuario"), rs.getString("nombre"),
                        rs.getString("login_name"), rs.getString("password"),
                        rs.getInt("tipo_usuario"));
                logger.info("User found with details= "+user.getLoginName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Database connection problem");
            throw new ServletException("DB Connection problem.");
        }finally{
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                logger.error("SQLException al cerrar PreparedStatement or ResultSet");;
            }    
        }
        return this.user;
    }
    
    public List listarUsuarios(Connection con) throws ServletException {
        userList = new ArrayList<Usuario>();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("select * from usuario");
            rs = ps.executeQuery();
            while(rs.next()) {
                Usuario u = new Usuario(rs.getString("nombre"),
                        rs.getString("login_name"), rs.getString("password"),
                        rs.getInt("tipo_usuario"));
                userList.add(u);
            }
        }
        catch(SQLException e) {
            logger.error("Conexion con BD");
            throw new ServletException("Problema en conexion con DB");
        }
        finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                logger.error("Problema en conexion con DB");
                throw new ServletException("Problema en conexion con DB");
            }  
        }
        return this.userList;
    }
    
    public void editarUsuario (Usuario u, Connection con) throws ServletException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("update usuario set nombre=?, login_name=?, password=?, tipo_usuario=? where id=?");
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getLoginName());
            ps.setString(3, u.getPassword());
            ps.setInt(4, u.getTipo_usuario());
            ps.executeQuery();
        }
        catch(SQLException e) {
            logger.error("Problema en conexion con DB");
            throw new ServletException("Problema en conexion con DB");
        }
        finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                logger.error("Problema en conexion con DB");
                throw new ServletException("Problema en conexion con DB");
            }   
        }
    }
    
}
