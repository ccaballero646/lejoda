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
public class CategoriaManager {
    
    private static final long serialVersionUID = 1L;
    static Logger logger = Logger.getLogger(CategoriaManager.class);
    private Categoria cat;
    private List categoriaList;
    
    public void crearCategoria(Categoria cat, Connection con) throws ServletException {
        
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("insert into categoria(descripcion) values (?)");
            ps.setString(1, cat.getDescripcion());
            ps.execute();
            logger.info("Usuario registrado= "+cat.getDescripcion());
        }
        catch(SQLException e) {
            logger.error("Problema db");
            throw new ServletException("Problema con la conexion a la base de datos.");
        }
        finally {
            try {
                ps.close();
            }
            catch(SQLException e) {
                logger.error("Problema db");
            }
        }
        
    }
    
    public List<Categoria> listarCategorias (Connection con) throws ServletException {
        categoriaList = new ArrayList<Categoria>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = con.prepareStatement("select * from categoria");
            rs = ps.executeQuery();
            while (rs.next()) {
                Categoria c = new Categoria(rs.getInt("id_categoria"), rs.getString("descripcion"));
                categoriaList.add(c);
            }
        }
        catch (SQLException e) {
            logger.error("Problema db");
            throw new ServletException("Problema con la conexion a la base de datos.");
        }
        finally {
            try {
                ps.close();
                rs.close();
            }
            catch (SQLException e){
                logger.error("Problema db");
            throw new ServletException("Problema con la base de datos");
            }
        }
        return categoriaList;
    }
    
    public void editarCategoria (Categoria cat, Connection con) throws ServletException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("update categoria set descripcion=? where id_categoria=?");
            ps.setString(1, cat.getDescripcion());
            ps.setInt(2, cat.getId_categoria());
            ps.execute();
        }
        catch (SQLException e) {
            logger.error("Problema db");
            throw new ServletException("Problema con la conexion a la base de datos.");
        }
        finally {
            try {
                ps.close();
            }
            catch (SQLException e) {
                logger.error("Problema db");
                throw new ServletException("Problema con la conexion a la base de datos.");
            }
        }
    }
    
    public Categoria getCategoria(int id, Connection con) throws ServletException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("select * from categoria where id_categoria=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                this.cat = new Categoria(rs.getInt("id_categoria"), 
                        rs.getString("descripcion"));
                logger.info("Categoria encontrada: " + cat.getDescripcion());
            }
        }
        catch(SQLException e) {
            logger.error("Problema de conexion DB");
            throw new ServletException("Problema de conexion con DB");
        }
        finally {
            try {
                ps.close();
                rs.close();
            }
            catch(SQLException e) {
                logger.error("Problema con la base de datos");
            }
            
        }
        return cat;
    }
    
    public void eliminarCategoria(int id, Connection con) throws ServletException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("delete from categoria where id_categoria=?");
            ps.setInt(1, id);
            ps.execute();
            logger.info("Categoria eliminada. ID= " + id);
        }
        catch(SQLException e){
            logger.error("Problema con la base de datos: " + e.getMessage());
            throw new ServletException("Problema de conexion con BD");
        }
        finally {
            try {
                ps.close();
            }
            catch(SQLException e) {
                logger.error("Problema con la BD");
            }
        }
    }
    
}
