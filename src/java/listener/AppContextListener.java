/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package listener;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import util.DBConnectionManager;

/**
 * Listener que prepara la conexion a la base de datos para que pueda ser usada
 * por los servlets
 *
 * @author ccaballero
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        
        //inicializar conexion bd
        try {
            DBConnectionManager db = new DBConnectionManager();
            ctx.setAttribute("DBConnection", db.getConnection());
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        //inicializar log4j
        String log4jConfig = ctx.getInitParameter("log4j-config");
    	if(log4jConfig == null){
                System.err.println("No se ha encontrado el archivo de configuracion, inicializando con valores default...");
			BasicConfigurator.configure();
    	}else {
			String webAppPath = ctx.getRealPath("/");
			String log4jProp = webAppPath + log4jConfig;
			File log4jConfigFile = new File(log4jProp);
			if (log4jConfigFile.exists()) {
				System.out.println("Inicializando log4j con " + log4jProp);
				DOMConfigurator.configure(log4jProp);
			} else {
				System.err.println(log4jProp + " Archivo no encontrado. Iniciando con BasicConfigurator");
				BasicConfigurator.configure();
			}
		}
    	System.out.println("log4j configurado correctamente");
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Connection con = (Connection)sce.getServletContext().getAttribute("DBConnection");
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
