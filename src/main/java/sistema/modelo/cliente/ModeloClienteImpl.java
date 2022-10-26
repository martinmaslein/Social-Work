package sistema.modelo.cliente;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import java.util.LinkedList;
import java.util.List;

import javafx.util.Pair;
import sistema.modelo.ModeloImpl;
import sistema.modelo.ModeloRegistro;

import sistema.utilidades.CreatePdf;
import sistema.utilidades.InvalidFormatException;

public class ModeloClienteImpl extends ModeloImpl implements ModeloUsuario {

	private DatosCliente clienteActual;
	private int nroCliente;

	public ModeloClienteImpl(String username, String password) {

		String sql = "SELECT * FROM Cliente WHERE username='" + username + "';";

		ResultSet rs = this.consulta(sql);

		try {
			if (rs.next()) {
				clienteActual = new DatosCliente();
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
			String contra = String.valueOf(password);

			String sql = "SELECT * FROM cliente WHERE username='" + username + "' AND password= md5('" + contra + "')"; // ver
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

	public boolean sePuedeModificar(DatosCliente nuevosDatos) throws InvalidFormatException {
		
		if(!ModeloRegistro.datosValidos(nuevosDatos)) {
			throw new InvalidFormatException("Mail, contraseña o nombre de usuario invalido");
		}

		String query = "SELECT * FROM Cliente WHERE "
				+ "(username = '"+nuevosDatos.getNombreUsuario() + "' "
				+ "OR correo = '"+nuevosDatos.getMail()+"' "
				+ "OR nro_doc = "+nuevosDatos.getNroDocumento()+" "
				+ "OR telefono = '"+nuevosDatos.getTelefono()+"' ) "
				+ "AND nro_cliente != "+clienteActual.getNroCliente()+";";
		ResultSet rs = this.consulta(query);
		try {
			return !rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
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

	public void generarCupon(int monto, int familiares) {
		CreatePdf pdf = new CreatePdf();
		try {
			pdf.getNombre(getNombre());
			pdf.getApellido(getApellido());
			pdf.getPlan(getPlan());
			pdf.generarPdf(monto, familiares);

		} catch (IOException e) {

			e.printStackTrace();
		}
		String query = "UPDATE Cliente SET " + "cupon = "+ 1 + " WHERE nombre = '"+ getNombre() + "';";
		this.actualizacion(query);
		
	}

	public String getPlan() {
		return clienteActual.getPlan();
	}

	public void setPlan(String plan) {
		clienteActual.setPlan(plan);
	}

	public int obtenerCantFamiliares() {

		// Obtengo el id del cliente
		String queryPlan2 = "SELECT nro_cliente FROM Cliente WHERE username='" + clienteActual.getNombreUsuario()
				+ "';";
		ResultSet rs = this.consulta(queryPlan2);
		int id = -1;
		try {
			if (rs.next())
				id = rs.getInt("nro_cliente");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String queryFamiliar = "SELECT * FROM Familiar WHERE nro_cliente = '" + id + "';";
		rs = this.consulta(queryFamiliar);

		int cont = 0;
		try {
			while (rs.next())
				cont++;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cont;

	}

	public int obtenerTotalAbonar() {

		// Obtenngo el plan del cliente
		String queryPlan = "SELECT nro_plan FROM Cliente WHERE username='" + clienteActual.getNombreUsuario() + "';";
		ResultSet rs = this.consulta(queryPlan);
		int monto = -1;
		try {
			if (rs.next())
				monto = rs.getInt("nro_plan");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Obtengo el id del cliente
		String queryPlan2 = "SELECT nro_cliente FROM Cliente WHERE username='" + clienteActual.getNombreUsuario()
				+ "';";
		rs = this.consulta(queryPlan2);
		int id = -1;
		try {
			if (rs.next())
				id = rs.getInt("nro_cliente");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Obtengo los familiares
		String queryFamiliar = "SELECT * FROM Familiar WHERE nro_cliente = '" + id + "';";
		rs = this.consulta(queryFamiliar);

		int cont = 0;
		try {
			while (rs.next())
				cont++;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Monto total
		if (cont == 0) {
			if (monto == 1)
				return 5000;
			else if (monto == 2)
				return 2500;
		} else {
			if (monto == 1)
				return cont * 5000 + 5000;
			else if (monto == 2)
				return cont * 2500 + 2500;
		}
		return 0;
	}

	public DatosCliente ObtenerDatosCliente() {
		return clienteActual;
	}

	public int obtenerPlanCliente() {
		String queryPlan = "SELECT nro_plan FROM Cliente WHERE username='" + clienteActual.getNombreUsuario() + "';";
		ResultSet rs = this.consulta(queryPlan);
		int monto = -1;
		try {
			if (rs.next())
				monto = rs.getInt("nro_plan");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return monto;
	}

	public ArrayList<String> obtenerNombreFamiliares() {

		ArrayList<String> nombreFamiliares = new ArrayList<String>();

		// Obtener ID
		String queryPlan2 = "SELECT nro_cliente FROM Cliente WHERE username='" + clienteActual.getNombreUsuario()
				+ "';";
		ResultSet rs = this.consulta(queryPlan2);
		int id = -1;
		try {
			if (rs.next())
				id = rs.getInt("nro_cliente");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Obtener nombre Familiares
		String queryFamiliar = "SELECT * FROM Familiar WHERE nro_cliente = '" + id + "';";
		rs = this.consulta(queryFamiliar);

		try {
			while (rs.next())
				nombreFamiliares.add(rs.getString("nombre"));
				
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nombreFamiliares;
	}

	@Override
	public boolean modificarPlan(String dni, String plan) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cargarPlan(String text, String text2, String text3) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean solicitarCambioPlan(int nro_plan) {
		String sql = "INSERT INTO Solicitud (nro_cliente,nro_plan) VALUES ("+clienteActual.getNroCliente() +" , "+nro_plan+");";
		this.actualizacion(sql);
		return true;
	}

	@Override
	public boolean modificarPlanAdmin(int planID, String nuevoNombre, double nuevoReintegro, int nuevoPrecio) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	public String[] obtenerServicios() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public LinkedList<String> getPlanes() {
		String sql = "SELECT * FROM Plan ORDER BY nombre ASC;";
		ResultSet rs = this.consulta(sql);
		LinkedList<String> planes= new LinkedList<String>();
		try {
			while(rs.next())
				planes.addLast(rs.getString("nombre"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return  planes;
	}

	@Override
	public List<Pair<String, String>> cargarClientesTabla() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pair<String, String>> obtenerSolicitudes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aprobarCambio(String nombre, String apellido) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aprobarPago(String nombre, String apellido) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<ArrayList<String>> obtenerInfoFamiliares() {

		ArrayList<ArrayList<String>> nombreFamiliares = new ArrayList<ArrayList<String>>();
		String plan = "";

		// Obtener ID
		String queryPlan2 = "SELECT nro_cliente FROM Cliente WHERE username='" + clienteActual.getNombreUsuario()
				+ "';";
		ResultSet rs = this.consulta(queryPlan2);
		int id = -1;
		try {if (rs.next()) id = rs.getInt("nro_cliente");} catch (SQLException e) {e.printStackTrace();}
		
		
		String sqlPlan = "SELECT nombre FROM Plan WHERE nro_plan="+obtenerPlanCliente()+";";
		rs = this.consulta(sqlPlan);
		try {if (rs.next()) plan = rs.getString("nombre");} catch (SQLException e) {e.printStackTrace();}
		
		
		// Obtener nombre Familiares
		String queryFamiliar = "SELECT * FROM Familiar WHERE nro_cliente = '" + id + "';";
		rs = this.consulta(queryFamiliar);

		try {
			while (rs.next()) {
				ArrayList<String> familiar = new ArrayList<String>();
				familiar.add(rs.getString("nombre"));
				familiar.add(rs.getString("apellido"));
				familiar.add(rs.getString("dni"));
				familiar.add(plan);	
				familiar.add(rs.getString("fecha_nac"));
				familiar.add(rs.getString("direccion"));
				familiar.add(rs.getString("telefono"));
				
				nombreFamiliares.add(familiar);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return nombreFamiliares;
	}

	public boolean eliminarFamiliar(String nombre) {

		
		String sql= "DELETE FROM Familiar WHERE nombre='"+nombre+"'";
		this.actualizacion(sql);
		
		
		return true;
		
	}
	public ArrayList<ArrayList<String>> obtenerSolicitudesABM() {
		//TODO 
		ArrayList<ArrayList<String>> solicitudes = new ArrayList<ArrayList<String>>();
		String plan = "";

		// Obtener ID
		String queryPlan2 = "SELECT nro_cliente FROM Solicitud WHERE username='" + clienteActual.getNombreUsuario()
				+ "';";
		ResultSet rs = this.consulta(queryPlan2);
		int id = -1;
		try {if (rs.next()) id = rs.getInt("nro_cliente");} catch (SQLException e) {e.printStackTrace();}
		
		
		String sqlPlan = "SELECT nombre FROM Plan WHERE nro_plan="+obtenerPlanCliente()+";";
		rs = this.consulta(sqlPlan);
		try {if (rs.next()) plan = rs.getString("nombre");} catch (SQLException e) {e.printStackTrace();}
		
		
		// Obtener nombre Familiares
		String queryFamiliar = "SELECT * FROM Familiar WHERE nro_cliente = '" + id + "';";
		rs = this.consulta(queryFamiliar);

		try {
			while (rs.next()) {
				ArrayList<String> solicitud = new ArrayList<String>();
				solicitud.add(rs.getString("id_solicitud"));
				solicitud.add(rs.getString("apellido"));
				solicitud.add(rs.getString("dni"));
				solicitud.add(plan);	
				solicitud.add(rs.getString("fecha_nac"));
				solicitud.add(rs.getString("direccion"));
				solicitud.add(rs.getString("telefono"));
				
				solicitudes.add(solicitud);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return solicitudes;
	}

}