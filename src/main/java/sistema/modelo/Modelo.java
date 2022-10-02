package sistema.modelo;

import java.sql.ResultSet;

public interface Modelo {

	
	public boolean conectar(String username, String password);
	
	public void desconectar();

	public ResultSet consulta(String sql);
	
	public void actualizacion (String sql);
}