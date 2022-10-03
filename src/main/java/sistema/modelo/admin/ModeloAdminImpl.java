package sistema.modelo.admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import sistema.modelo.ModeloImpl;
import sistema.modelo.cliente.ModeloUsuario;

public class ModeloAdminImpl extends ModeloImpl implements ModeloUsuario {

	private String apellido;
	private String nombre;
	private int nroDocumento;
	private String direccion;
	private String mail;
	private String telefono;
	private Date fechaNacimiento;
	private String nombreUsuario;
	private String contrasena;
	
	public ModeloAdminImpl() {	
		nombre = getNombre();
		apellido = getApellido();
		nroDocumento = getNroDocumento();
		mail = getMail();
		direccion = getDireccion();
		telefono = getTelefono();
		fechaNacimiento = getFechaNacimiento();
		nombreUsuario = getNombreUsuario();
		contrasena = getContrasena();
	}
	
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(int nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String getMail() {
		return mail;
	}

	@Override
	public void setMail(String mail) {
		this.mail = mail;		
	}

	@Override
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	@Override
	public void setNombreUsuario(String usuario) {
		this.nombreUsuario = usuario;
		
	}

	@Override
	public String getContrasena() {
		return contrasena;
	}

	@Override
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	/*public boolean autenticarUsuarioAplicacion(String usuario, String contrasena) {
		return this.nombreUsuario == usuario && this.contrasena == contrasena;
	}*/
	public boolean autenticarUsuarioAplicacion(String username, char[] password) throws Exception {		
		
		String sql = "SELECT * FROM empleado WHERE username=? AND password= md5(?)"; // ver si ta bien esto

		try {
			PreparedStatement select = conexion.prepareStatement(sql);
			select.setString(1,username);
			select.setString(2,password.toString());
			ResultSet rs = select.executeQuery();
				
			if (rs.next()) {
				this.nombreUsuario = rs.getString("username");
			} else return false;
			
			rs.close();
			select.close();

		}catch (SQLException ex) {
			throw new Exception("Error inesperado al consultar la B.D.");
		}
		catch (NumberFormatException ex2) {
			throw new Exception("El legajo ingresado no tiene formato valido");
		}

		return true;
	}
}