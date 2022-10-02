package sistema.modelo;

import java.util.Arrays;

public class UsuarioImpl implements Usuario {
	
	private String username;
	private String displayname;
	private String password;
	
	public UsuarioImpl() {
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public String getDisplayname() {
		return this.displayname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	
	public boolean passwordCoincide(char[] clave) {
		
		boolean esCorrecto = false; 
		
		char[] passCorrecto = this.getPassword().toCharArray(); 

		if (clave.length == passCorrecto.length) {
			esCorrecto = Arrays.equals (clave, passCorrecto);
		}			

		Arrays.fill(passCorrecto,'0');
		
		return esCorrecto;
	}
}