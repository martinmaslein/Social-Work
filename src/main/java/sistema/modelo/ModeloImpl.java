package sistema.modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sistema.utilidades.Conexion;


public class ModeloImpl implements Modelo {
	

	protected Connection conexion = null;

	public boolean conectar(String username, String password) {
		System.out.println(username);
		System.out.println(password);
        this.conexion = Conexion.getConnection(username, password);        
    	return (this.conexion != null);	
	}

	public void desconectar() {
		System.out.println("Se desconecta la conexion a la BD.");
		Conexion.closeConnection(this.conexion);		
	}

	public ResultSet consulta(String sql){
		try{
			Statement stmt = conexion.createStatement();			
			ResultSet rs = stmt.executeQuery(sql);
			
			return rs;
		}
		catch (SQLException ex){
			System.out.println("Error al realizar la consulta.");
		}
		return null;
	}	
	
	public void actualizacion (String sql){
		try{
			Statement stmt = conexion.createStatement();
			stmt.executeUpdate(sql);
			
			stmt.close();
		}
		catch (SQLException ex) {
			System.out.println("Error al intentar actualizar.");
		}
	}	
}