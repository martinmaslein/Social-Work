package sistema.modelo.empleado;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTable;

import javafx.util.Pair;
import sistema.modelo.ModeloImpl;
import sistema.modelo.cliente.ModeloUsuario;

public class ModeloEmpleadoImpl extends ModeloImpl implements ModeloUsuario {

	private String apellido;
	private String nombre;
	private int nroDocumento;
	private String direccion;
	private String mail;
	private String telefono;
	private String fechaNacimiento;
	private String nombreUsuario;
	private String contrasena;

	public ModeloEmpleadoImpl() {
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
			String sql = "SELECT * FROM empleado WHERE username='" + username + "' AND password= md5('" + quimey + "')"; // ver

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

	public boolean modificarPlan(String dni, String plan) throws Exception {
		boolean salida;
		int nroPlan;
		try {
			String sql = "SELECT * FROM Cliente WHERE nro_doc= " + dni + ";"; // ver si ta bien esto
			ResultSet rs = this.consulta(sql);
			nroPlan = plan == "A" ? 1 : 2;
			System.out.println(sql);

			if (rs.next()) {
				salida = true;
				String query = "UPDATE Cliente SET " + "nro_plan = " + nroPlan + " WHERE nro_doc = " + dni + ";";
				System.out.println(query);
				this.actualizacion(query);

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

	public void generarCupon(int monto, int familiares) {
	}

	public boolean cargarPlan(String text, String text2, String text3) {
		return true;
	}

	@Override
	public boolean modificarPlanAdmin(int planID, String nuevoNombre, double nuevoReintegro, int nuevoPrecio)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public String[] obtenerServicios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pair<String, String>> cargarClientesTabla() {

		List<Pair<String, String>> clientes = new LinkedList<Pair<String, String>>();
		Pair<String, String> cliente = new Pair<String, String>(null, null);
		String sql = "SELECT * FROM CLIENTE;";
		ResultSet rs = this.consulta(sql);
		try {
			while (rs.next()) {
				if (rs.getInt("cupon") == 1) {
					if (rs.getInt("nro_plan") == 1)
						cliente = new Pair<String, String>(rs.getString("apellido") + "," + rs.getString("nombre"),
								"5000");
					else if (rs.getInt("nro_plan") == 2)
						cliente = new Pair<String, String>(rs.getString("apellido") + "," + rs.getString("nombre"),
								"2500");
					clientes.add(cliente);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	// #---Solicitud(id_solicitud,nro_cliente,nro_plan INT UNSIGNED)
	public List<Pair<String, String>> obtenerSolicitudes() {
		List<Pair<String, String>> solicitudes = new ArrayList<Pair<String, String>>();
		Pair<String, String> solicitud;
		String sql = "SELECT * FROM Solicitud"; // obtengo todas las solicitudes
		String sqlNombreCliente, sqlPlan, nombreCliente = "", planCliente = "";
		ResultSet rs = this.consulta(sql);
		ResultSet rsNombreCliente, rsPlan;
		try {
			while (rs.next()) {
				sqlNombreCliente = "SELECT nombre, apellido FROM Cliente WHERE nro_cliente = "
						+ rs.getInt("nro_cliente");
				rsNombreCliente = this.consulta(sqlNombreCliente);
				if (rsNombreCliente.next()) {
					nombreCliente = rsNombreCliente.getString("apellido") + "," + rsNombreCliente.getString("nombre");
				}

				sqlPlan = "SELECT nombre FROM Plan WHERE nro_plan = " + rs.getInt("nro_plan");
				rsPlan = this.consulta(sqlPlan);
				if (rsPlan.next()) {
					planCliente = rsPlan.getString("nombre");
				}

				solicitud = new Pair<String, String>(nombreCliente, planCliente);
				solicitudes.add(solicitud);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return solicitudes;
	}

	@Override
	public void aprobarCambio(String nombre, String apellido) throws Exception {
		String sqlCliente = "SELECT * FROM Cliente WHERE nombre = '" + nombre + "' AND apellido = '" + apellido + "';"; // solicitudes
		String sqlSolicitud, sqlDelete;
		ResultSet rsCliente = this.consulta(sqlCliente);
		ResultSet rsSolicitud;
		try {
			while (rsCliente.next()) {
				sqlSolicitud = "SELECT * FROM Solicitud WHERE nro_cliente = " + rsCliente.getInt("nro_cliente") + ";";
				System.out.println(sqlSolicitud);
				rsSolicitud = this.consulta(sqlSolicitud);

				if (rsSolicitud.next()) {
					String query = "UPDATE Cliente SET " + "nro_plan = " + rsSolicitud.getInt("nro_plan")
							+ " WHERE nro_cliente = " + rsSolicitud.getInt("nro_cliente");
					this.actualizacion(query);
					sqlDelete = "DELETE FROM Solicitud WHERE id_solicitud = " + rsSolicitud.getInt("id_solicitud");
					actualizacion(sqlDelete);
				}

			}

			rsCliente.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void aprobarPago(String nombre, String apellido) {
		String sqlCliente = "SELECT * FROM Cliente WHERE nombre = '" + nombre + "' AND apellido = '" + apellido + "';"; 
		ResultSet rsCliente = this.consulta(sqlCliente);
		try {
			while (rsCliente.next()) {
				String query = "UPDATE Cliente SET " + "cupon = " + 0 + " WHERE nro_cliente = "
						+ rsCliente.getInt("nro_cliente");
				this.actualizacion(query);
			}
			rsCliente.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean eliminarPlan(sistema.utilidades.Pair<String, Integer> planSeleccionado) {
		return true;}
	public ArrayList<ArrayList<String>> obtenerSolicitudesABM() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminarSolicitud(ArrayList<String> solicitud) {
		return false;
	}
	public String informacionPlan(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean modificarPlan(String nombre, String text, String text2, JTable table_1) {
		// TODO Auto-generated method stub
		return false;
	}

}
