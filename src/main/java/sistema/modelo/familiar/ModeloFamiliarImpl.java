package sistema.modelo.familiar;

import java.sql.ResultSet;

public class ModeloFamiliarImpl implements ModeloFamiliar {
	protected String nombre;
	protected String apellido;
	protected String direccion;
	protected String fechaNac;
	protected String telefono;
	
	public ModeloFamiliarImpl() {
		this.nombre = "";
		this.apellido = "";
		this.direccion = "";
		this.fechaNac = "";
		this.telefono = "";
	}
	
	@Override
	public String getApellido() {
		return apellido;
	}

	@Override
	public void setApellido(String apellido) {
		this.apellido = apellido;		
	}

	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;		
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String getTelefono() {
		return telefono;
	}

	@Override
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	
	@Override
	public String getFechaNacimiento() {
		return fechaNac;
	}

	@Override
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNac = fechaNacimiento;
	}

	@Override
	public String getDireccion() {
		return direccion;
	}

	@Override
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Override
	public boolean conectar(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void desconectar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet consulta(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizacion(String sql) {
		// TODO Auto-generated method stub
		
	}

}
