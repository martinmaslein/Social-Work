package sistema.modelo;

public interface Usuario{
	
	public String getUsername();
	
	public String getPassword();
	
	public String getDisplayname();
	
	public void setUsername(String username);
	
	public void setPassword(String password);
	
	public void setDisplayname(String displayname);
	
	public boolean passwordCoincide(char[] clave);	
}