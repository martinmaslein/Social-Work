package sistema.modelo;

import java.util.List;


public interface ModeloLogin { 
	
	public List<String> obtenerNombresUsuarios(); 

	public boolean validar(String displayname, String usuario, char[] clave);

	public void iniciarConexion() throws Exception;
	
	public Usuario obtenerUsuario(String rol);
}
