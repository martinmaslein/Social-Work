package sistema.modelo.cliente;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import sistema.modelo.ModeloImpl;
import sistema.utilidades.CreatePdf;

public class ModeloClienteImpl extends ModeloImpl implements ModeloUsuario {

	private String apellido;
	private String nombre;
	private int nroDocumento;
	private String direccion;
	private String mail;
	private String telefono;
	private String fechaNacimiento;
	private String nombreUsuario;
	private String contrasena;
	private String plan;

	public ModeloClienteImpl() {
		nombre = getNombre();
		apellido = getApellido();
		nroDocumento = getNroDocumento();
		mail = getMail();
		direccion = getDireccion();
		telefono = getTelefono();
		fechaNacimiento = getFechaNacimiento();
		nombreUsuario = getNombreUsuario();
		contrasena = getContrasena();
		plan = getPlan();
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

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
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
	/*
	 * public boolean autenticarUsuarioAplicacion(String usuario, String contrasena)
	 * { return this.nombreUsuario == usuario && this.contrasena == contrasena; }
	 */

	public boolean autenticarUsuarioAplicacion(String username, char[] password) throws Exception {
		boolean salida;
		try {
			String quimey = String.valueOf(password);
			String sql = "SELECT * FROM CLIENTE WHERE username='" + username + "' AND password= md5('" + quimey + "');"; // ver
																															// si
			System.out.println(sql);
																															// bien
																															// esto
			ResultSet rs = this.consulta(sql);

			if (rs.next()) {
				salida = true;
			} else
				salida = false;

			rs.close();

		} catch (SQLException ex) {
			throw new Exception("Error inesperado al consultar la B.D.");
		} catch (NumberFormatException ex2) {
			throw new Exception("El legajo ingresado no tiene formato valido");
		}

		return salida;
	}

	public boolean modificarDatos(String username, String password, String queryID) {

		System.out.println(queryID);
		ResultSet rs = this.consulta(queryID);
		int id = 0;
		try {
			id = rs.getInt("nro_cliente");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String query = "UPDATE Cliente SET username = '" + username + "', password = '" + password
				+ "' WHERE nro_cliente = " + id + ";";
		this.actualizacion(query);

		return true;

	}

	public void generarCupon() {
		CreatePdf pdf = new CreatePdf();
		try {
			pdf.generarPdf();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}
}