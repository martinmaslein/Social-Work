package sistema.utilidades;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import sistema.utilidades.Conexion;

public class Conexion {
	
    private static String url;    
    private static String driverName;   
    private static Connection con;
    private static String urlstring;

	public static Connection getConnection(String usuario, String password) {
		try { 	
			Class.forName("com.mysql.cj.jdbc.Driver");
	            try {
	            	
	            	
	            	
	            	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema?serverTimezone=America/Argentina/Buenos_Aires",
	                		usuario,
	                		password);
	                
	            } catch (SQLException ex) {	            	
	            	System.out.println("Error al crear la conexion con la base de datos."); 
	            }
		} catch (ClassNotFoundException ex) {
			System.out.println("Driver no encontrado."); 
		}
		
	    return con;
    }	
    
	
	public static void inicializar(String propertyFile)	{
		Properties prop = new Properties();
		
		try{
			
			FileInputStream file=new FileInputStream(propertyFile);			
			prop.load(file);
			
			System.out.println("se cargo exitosamente");
			
			Conexion.setDriverName(prop.getProperty("driverName"));
			
			Conexion.setUrl(prop.getProperty("libreria", "jdbc") + ":" +
					  		prop.getProperty("motor", "mysql") + "://" +
					  		prop.getProperty("servidor", "localhost") + ":" +
					  		prop.getProperty("puerto"));
			
			Conexion.setUrlstring(Conexion.getUrl() + "/" + prop.getProperty("base_de_datos") + prop.getProperty("parametro_aux1"));
		}
		catch(Exception ex){
			System.out.println("Se produjo un error al recuperar el archivo de propiedades de la BD."); 
		}
		return;
	}
	

    public static void closeConnection(Connection conn) {
        try {
        	System.out.println("Se intenta cerrar la conexion activa");
            if (null != conn) {
                conn.close();
                conn = null;
            }
        } catch (SQLException ex) {
        	System.out.println("Error al cerrar la conexion con la base de datos."); 
        }
    }

    public static void closeResultset(ResultSet rs) {
        try {
        	System.out.println("Se intenta cerrar el resultSet");
            if (null != rs) {
                rs.close();
                rs = null;
            }
        } catch (SQLException ex) {
        	System.out.println("Error al cerrar el resultSet."); 
        }
    }

    public static void closePreparedStatement(PreparedStatement pstmt) {
        try {
        	System.out.println("Se intenta cerrar la consulta preparada.");
            if (null != pstmt) {
                pstmt.close();
                pstmt = null;
            }
        } catch (SQLException ex) {
        	System.out.println("Error al cerrar la consulta preparada."); 
        }
    }

    public static void closeStatement(Statement stmt) {
        try {
        	System.out.println("Se intenta cerrar la sentencia.");
            if (null != stmt) {
                stmt.close();
                stmt = null;
            }
        } catch (SQLException ex) {
        	System.out.println("Error al cerrar la sentencia."); 
        }
    }	

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		Conexion.url = url;
	}

	public static String getDriverName() {
		return driverName;
	}

	public static void setDriverName(String driverName) {
		Conexion.driverName = driverName;
	}

	public static String getUrlstring() {
		return urlstring;
	}

	public static void setUrlstring(String urlstring) {
		Conexion.urlstring = urlstring;
	}
}