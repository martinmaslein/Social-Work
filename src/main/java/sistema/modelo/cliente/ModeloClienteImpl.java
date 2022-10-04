package sistema.modelo.cliente;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import sistema.modelo.ModeloImpl;
import sistema.utilidades.CreatePdf;

public class ModeloClienteImpl extends ModeloImpl implements ModeloUsuario {

	private DatosCliente clienteActual;
	private int nroCliente;
	
	
	public ModeloClienteImpl(String username, String password) {
		String sql="SELECT * FROM Cliente WHERE username='" + username + "';";

		ResultSet rs = this.consulta(sql);

		try{
			if(rs.next()) {
				clienteActual= new DatosCliente();
				clienteActual.setNombreUsuario(rs.getString("username"));
				clienteActual.setContrasena(password);
				clienteActual.setApellido(rs.getString("apellido"));
				clienteActual.setNombre(rs.getString("nombre"));
				clienteActual.setFechaNacimiento(rs.getString("fecha_nac"));
				clienteActual.setDireccion(rs.getString("direccion"));
				clienteActual.setTelefono(rs.getString("telefono"));
				clienteActual.setMail(rs.getString("correo"));
				clienteActual.setNroDocumento(rs.getInt("nro_doc"));
				clienteActual.setNroCliente(rs.getInt("nro_cliente"));
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getApellido() {
		return clienteActual.getApellido();
	}

	public void setApellido(String apellido) {
		clienteActual.setApellido(apellido);
	}

	public String getNombre() {
		return clienteActual.getNombre();
	}

	public void setNombre(String nombre) {
		clienteActual.setNombre(nombre);
	}

	public int getNroDocumento() {
		return clienteActual.getNroDocumento();
	}

	public void setNroDocumento(int nroDocumento) {
		clienteActual.setNroDocumento(nroDocumento);
	}

	public String getDireccion() {
		return clienteActual.getDireccion();
	}

	public void setDireccion(String direccion) {
		clienteActual.setDireccion(direccion);
	}

	public String getTelefono() {
		return clienteActual.getTelefono();
	}

	public void setTelefono(String telefono) {
		clienteActual.setTelefono(telefono);
	}

	public String getFechaNacimiento() {
		return clienteActual.getFechaNacimiento();
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		clienteActual.setFechaNacimiento(fechaNacimiento);
	}

	@Override
	public String getMail() {
		return clienteActual.getMail();
	}

	@Override
	public void setMail(String mail) {
		clienteActual.setMail(mail);
	}

	@Override
	public String getNombreUsuario() {
		return clienteActual.getNombreUsuario();
	}

	@Override
	public void setNombreUsuario(String usuario) {
		clienteActual.setNombreUsuario(usuario);

	}

	@Override
	public String getContrasena() {
		return clienteActual.getContrasena();
	}

	@Override
	public void setContrasena(String contrasena) {
		clienteActual.setContrasena(contrasena);
	}

	public int getNroCliente() {
		return clienteActual.getNroCliente();
	}
	
	public void setNroCliente(int nroCliente) {
		clienteActual.setNroCliente(nroCliente);
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

			String sql = "SELECT * FROM cliente WHERE username='" + username + "' AND password= md5('" + quimey + "')"; // ver
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

	public boolean modificarDatos(DatosCliente nuevosDatos) {
		String queryID = "SELECT nro_cliente FROM Cliente WHERE username='" + clienteActual.getNombreUsuario() + "';";
		ResultSet rs = this.consulta(queryID);
		int id = 0;
		try {
			if (rs.next())
				id = rs.getInt("nro_cliente");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String newNombreUsuario = nuevosDatos.getNombreUsuario();
		String newPassword = nuevosDatos.getContrasena();
		String newApellido = nuevosDatos.getApellido();
		String newNombre = nuevosDatos.getNombre();
		String newFechaNac = nuevosDatos.getFechaNacimiento();
		String newDireccion = nuevosDatos.getDireccion();
		String newTelefono = nuevosDatos.getTelefono();
		String newMail = nuevosDatos.getMail();
		Integer newNroDoc = nuevosDatos.getNroDocumento();

		if (newNombreUsuario != null && !newNombreUsuario.isBlank())
			clienteActual.setNombreUsuario(newNombreUsuario);
		if (newPassword != null && !newPassword.isBlank())
			clienteActual.setContrasena(newPassword);
		if (newApellido != null && !newApellido.isBlank())
			clienteActual.setApellido(newApellido);
		if (newNombre != null && !newNombre.isBlank())
			clienteActual.setNombre(newNombre);
		if (newFechaNac != null && !newFechaNac.isBlank())
			clienteActual.setFechaNacimiento(newFechaNac);
		if (newDireccion != null && !newDireccion.isBlank())
			clienteActual.setDireccion(newDireccion);
		if (newTelefono != null && !newTelefono.isBlank())
			clienteActual.setTelefono(newTelefono);
		if (newMail != null && !newMail.isBlank())
			clienteActual.setMail(newMail);
		if (newNroDoc != null)
			clienteActual.setNroDocumento(newNroDoc);

		String query = "UPDATE Cliente SET " + "username = '" + clienteActual.getNombreUsuario() + "', password = md5('"
				+ clienteActual.getContrasena() + "')" + ", apellido ='" + clienteActual.getApellido() + "', nombre = '"
				+ clienteActual.getNombre() + "', fecha_nac = '" + clienteActual.getFechaNacimiento()
				+ "', direccion = '" + clienteActual.getDireccion() + "', telefono = '" + clienteActual.getTelefono()
				+ "', correo = '" + clienteActual.getMail() + "', nro_doc = " + clienteActual.getNroDocumento()
				+ " WHERE nro_cliente = " + id + ";";

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
		return clienteActual.getPlan();
	}

	public void setPlan(String plan) {
		clienteActual.setPlan(plan);
	}

	public int obtenerTotalAbonar() {

		String queryPlan = "SELECT nro_plan FROM Cliente WHERE username='" + clienteActual.getNombreUsuario() + "';";
		ResultSet rs = this.consulta(queryPlan);
		int monto = -1;
		try {
			if (rs.next())
				monto = rs.getInt("nro_plan");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (monto == 1)
			return 5000;
		else if (monto == 2)
			return 2500;

		return 0;
	}
}