package sistema.modelo.familiar;

import sistema.modelo.Modelo;

public interface ModeloFamiliar extends Modelo {

	public String getApellido();

	public void setApellido(String apellido);

	public void setNombre(String nombre);
	
	public String getNombre();

	public String getTelefono();
	
	public void setTelefono(String telefono);

	public String getFechaNacimiento();

	public void setFechaNacimiento(String fechaNacimiento);

	public String getDireccion();

	public void setDireccion(String direccion);
}

