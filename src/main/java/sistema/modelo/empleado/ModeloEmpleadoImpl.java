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
	public List<Pair<String, String>> obtenerSolicitudesCambioPlan() {
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
	public void aprobarCambioPlan(String nombre, String apellido) throws Exception {
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
		return true;
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

	@Override
	public String informacionSolicitud(ArrayList<String> solicitud) {
		// nombre(0), apellido(1), dni(2), "reintegro"(3),id_reintegro(4), cbu(5)
		// nombre(0), apellido(1), dni(2), "prestacion"(3),id_prestacion(4), fecha(5)
		String nombre = solicitud.get(0);
		String apellido = solicitud.get(1);
		String tipoSolicitud = solicitud.get(3).toUpperCase();
		String idSolicitud = solicitud.get(4);
		String servicio = "";
		String toReturn = "";
		String extra = "";
		String tabla = "Solicitud_" + solicitud.get(3);

		if (tipoSolicitud.compareTo("REINTEGRO") == 0) {
			String sqlSolicitud = "SELECT * FROM Solicitud_reintegro WHERE id_reintegro =" + idSolicitud + ";";
			ResultSet rs = this.consulta(sqlSolicitud);
			try {
				if (rs.next()) {
					servicio = "Servicio: " + rs.getString("tipo_servicio");

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			extra = "CBU " + solicitud.get(5);
		} else {// PRESTACION
			String sqlSolicitud = "SELECT profesional FROM Solicitud_prestacion WHERE id_prestacion =" + idSolicitud
					+ ";";
			ResultSet rs = this.consulta(sqlSolicitud);
			try {
				if (rs.next()) {
					servicio = "Profesional: " + rs.getString("profesional");

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			extra = "Fecha: " + solicitud.get(5);

		}

		toReturn = tipoSolicitud + "\nCliente: " + nombre + " " + apellido + "\n" + "" + servicio + "\n" + "" + extra
				+ "\n";

		return toReturn;
	}

	@Override
	public void desaprobarCambioPlan(String nombre, String apellido) {
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
	public ArrayList<ArrayList<String>> obtenerSolicitudesABM() {
		ArrayList<ArrayList<String>> solicitudes = new ArrayList<ArrayList<String>>();
		ResultSet rsACargo, rsFamiliar, rsCliente;

		// OBTENGO TODOS LOS REINTEGROS
		String sqlACargo = "SELECT * FROM Solicitud_reintegro ;";
		System.out.println(sqlACargo);
		rsACargo = this.consulta(sqlACargo);

		try {
			while (rsACargo.next()) {
				ArrayList<String> solicitud = new ArrayList<String>();
				if (rsACargo.getInt("nro_familiar") > 0) {// SI ES PARA UN FAMILIAR
					String sqlFamiliar = "SELECT * FROM Familiar WHERE nro_familiar=" + rsACargo.getInt("nro_familiar")
							+ ";";
					rsFamiliar = this.consulta(sqlFamiliar);
					if (rsFamiliar.next()) {
						try {
							solicitud.add(rsFamiliar.getString("nombre"));
							solicitud.add(rsFamiliar.getString("apellido"));
							solicitud.add(rsFamiliar.getString("dni"));

						} catch (SQLException e) {
							e.printStackTrace();
						}

					}
				} else {
					String sqlCliente = "SELECT * FROM Cliente WHERE nro_cliente=" + rsACargo.getInt("nro_cliente")
							+ ";";
					rsCliente = this.consulta(sqlCliente);
					if (rsCliente.next()) {
						try {
							solicitud.add(rsCliente.getString("nombre"));
							solicitud.add(rsCliente.getString("apellido"));
							solicitud.add(rsCliente.getString("nro_doc"));

						} catch (SQLException e) {
							e.printStackTrace();
						}

					}

				}

				solicitud.add("reintegro");
				solicitud.add("" + rsACargo.getInt("id_reintegro"));
				solicitud.add(rsACargo.getString("nro_cbu"));
				solicitudes.add(solicitud);
				// nombre(0), apellido(1), dni(2), "reintegro"(3),id_reintegro(4), cbu(5)
			}

		} catch (

		SQLException e) {
			e.printStackTrace();
		}

		// OBTENGO TODOS LAS PRESTACIONES
		sqlACargo = "SELECT * FROM Solicitud_prestacion;";
		System.out.println(sqlACargo);
		rsACargo = this.consulta(sqlACargo);

		try {
			while (rsACargo.next()) {
				ArrayList<String> solicitud = new ArrayList<String>();
				if (rsACargo.getInt("nro_familiar") > 0) {// SI ES PARA UN FAMILIAR
					String sqlFamiliar = "SELECT * FROM Familiar WHERE nro_familiar=" + rsACargo.getInt("nro_familiar")
							+ ";";
					rsFamiliar = this.consulta(sqlFamiliar);
					if (rsFamiliar.next()) {
						try {
							solicitud.add(rsFamiliar.getString("nombre"));
							solicitud.add(rsFamiliar.getString("apellido"));
							solicitud.add(rsFamiliar.getString("dni"));
						} catch (SQLException e) {
							e.printStackTrace();
						}

					}
				} else {// ES QUIEN ESTA A CARGO
					String sqlCliente = "SELECT * FROM Cliente WHERE nro_cliente=" + rsACargo.getInt("nro_cliente")
							+ ";";
					rsCliente = this.consulta(sqlCliente);
					if (rsCliente.next()) {
						try {
							solicitud.add(rsCliente.getString("nombre"));
							solicitud.add(rsCliente.getString("apellido"));
							solicitud.add(rsCliente.getString("nro_doc"));

						} catch (SQLException e) {
							e.printStackTrace();
						}

					}
				}

				solicitud.add("prestacion");
				solicitud.add("" + rsACargo.getInt("id_prestacion"));
				solicitud.add(rsACargo.getString("fecha"));
				solicitudes.add(solicitud);
				// nombre(0), apellido(1), dni(2), "prestacion"(3),id_prestacion(4), fecha(5)
			}

		} catch (

		SQLException e) {
			e.printStackTrace();
		}

		return solicitudes;
	}

	@Override
	public void aprobarSolicitud(ArrayList<String> solicitud) {
		// nombre(0), apellido(1), dni(2), "reintegro"(3),id_reintegro(4), cbu(5)
		// nombre(0), apellido(1), dni(2), "prestacion"(3),id_prestacion(4), fecha(5)
		String tipo = solicitud.get(3);
		String tabla = "Solicitud_" + tipo;
		String id = solicitud.get(4);

		String sqlDelete;
		sqlDelete = "DELETE FROM " + tabla + " WHERE id_" + tipo + " = " + id;
		actualizacion(sqlDelete);
	}

	@Override
	public void desaprobarSolicitud(ArrayList<String> solicitud) {
		// nombre(0), apellido(1), dni(2), "reintegro"(3),id_reintegro(4), cbu(5)
		// nombre(0), apellido(1), dni(2), "prestacion"(3),id_prestacion(4), fecha(5)
		String tipo = solicitud.get(3);
		String tabla = "Solicitud_" + tipo;
		String id = solicitud.get(4);

		String sqlDelete;
		sqlDelete = "DELETE FROM " + tabla + " WHERE id_" + tipo + " = " + id;
		actualizacion(sqlDelete);

	}

	@Override
	public ArrayList<ArrayList<String>> obtenerInfoClientes() {
		ArrayList<ArrayList<String>> nombreClientes = new ArrayList<ArrayList<String>>();
		String plan = "";

		// Obtener nombre Familiares
		String sql = "SELECT * FROM Cliente;";
		ResultSet rs = this.consulta(sql);

		try {
			while (rs.next()) {
				ArrayList<String> cliente = new ArrayList<String>();
				cliente.add(rs.getString("nombre"));
				cliente.add(rs.getString("apellido"));
				cliente.add(rs.getString("nro_doc"));
				cliente.add(obtenerPlan(rs.getInt("nro_plan")));
				cliente.add(rs.getString("fecha_nac"));
				cliente.add(rs.getString("direccion"));
				cliente.add(rs.getString("telefono"));
				cliente.add(rs.getString("correo"));

				nombreClientes.add(cliente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nombreClientes;
	}

	private String obtenerPlan(int nroDoc) {
		
		String sqlPlan = "SELECT nombre FROM Plan WHERE nro_plan=" + nroDoc + ";";
		ResultSet rs = this.consulta(sqlPlan);
		String plan="";
		try {
			if (rs.next())
				plan = rs.getString("nombre");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return plan;
	}

	@Override
	public ArrayList<String> getPlanesTotales() {

		String sql = "SELECT * FROM PLAN;";
		ResultSet rs = this.consulta(sql);
		ArrayList<String> plan= new ArrayList<String>();
		try {
			while (rs.next())
				plan.add(rs.getString("nombre"));
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return plan;
	}

	@Override
	public void actualizarPlanCliente(String string, String string2) {
		
		String sql = "SELECT * FROM Plan WHERE nombre='"+string2+"';";
		ResultSet rs = this.consulta(sql);
		int nroPlan = 0;
		try {
			if (rs.next())
				nroPlan = rs.getInt("nro_plan");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		String query = "UPDATE Cliente SET " + "nro_plan = " + nroPlan + " WHERE nombre= '" + string + "';";
		this.actualizacion(query);
		
	}

	@Override
	public boolean eliminarCliente(String nombre) {
		String sql = "DELETE FROM Cliente WHERE nombre='" + nombre + "'";
		this.actualizacion(sql);

		return true;
	}

}
