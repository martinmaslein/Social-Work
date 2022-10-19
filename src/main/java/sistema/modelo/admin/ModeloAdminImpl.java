package sistema.modelo.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistema.modelo.ModeloImpl;
import sistema.modelo.cliente.ModeloUsuario;
import sistema.utilidades.Pair;

public class ModeloAdminImpl extends ModeloImpl implements ModeloUsuario {

	private String apellido;
	private String nombre;
	private int nroDocumento;
	private String direccion;
	private String mail;
	private String telefono;
	private String fechaNacimiento;
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
	public boolean autenticarUsuarioAplicacion(String username, char[] password) throws Exception {
		boolean salida;
		try {
			String quimey = String.valueOf(password);
			String sql = "SELECT * FROM administrador WHERE username='"+ username +"' AND password= md5('"+ quimey +"')";
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

	public boolean modificarPlanAdmin(int id, String nuevoNombre, int nuevoPrecio) throws Exception{
		boolean salida;
		
		String sql = "UPDATE plan SET nombre = "+ nuevoNombre +", precio = "+ nuevoPrecio +""; 
		ResultSet rs = this.consulta(sql);
		
		
		return false;
	}

	public List<Pair<String, Integer>> obtenerPlanes() {
		List<Pair<String,Integer>> planes = new ArrayList<Pair<String,Integer>>();
		Pair<String, Integer> plan;
		String sql = "SELECT nombre, reintegro FROM plan"; 
		ResultSet rs = this.consulta(sql);
		try {
			while(rs.next()) {
				plan = new Pair<String,Integer>(rs.getString("Nombre"), rs.getInt("Reintegro"));
				planes.add(plan);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return planes;
	}

	public boolean cargarPlan(String nombre, String precio, String prestaciones) {
		 
		if(nombre == "" || precio == "" || prestaciones == "") {return false; }
		else if(chequearNombre(nombre) == false) {return false;}
		
		else{ 

			String cantPlanes = "SELECT * FROM Plan;"; 
			ResultSet rs = this.consulta(cantPlanes); 
			int cant = 0; 
			try { while (rs.next()) cant++; } catch (SQLException e) { e.printStackTrace();}

			double reintegro = 0 + Math.random() * (98 - 2);			
			cant += 1;
			String sql = "INSERT INTO Plan (nro_plan,nombre,reintegro,precio) VALUES (" + cant + " , '" 
						+ nombre + "' , " + reintegro + " , "+Integer.parseInt(precio)+");";
			;
			
			this.actualizacion(sql);
	
			return true;
		}
	}

	private boolean chequearNombre(String nombre2) {
		
		String consulta = "SELECT * FROM Plan";
		
		ResultSet rs = this.consulta(consulta); 
		try { 
			while (rs.next()) {
				if(rs.getString("nombre").equals(nombre2))
					return false;
				}
			} catch (SQLException e) { e.printStackTrace();}
	
		return true;
	}

	@Override
	public void generarCupon(int monto, int familiares) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean modificarPlan(String dni, String plan) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificarPlanAdmin(int planID, String nuevoNombre, double nuevoReintegro, int nuevoPrecio) throws Exception {
		
		return false;
	public String[] obtenerServicios() {

		String[] servicios = new String[3];
		int cont = 0;
		String sql = "SELECT * FROM Servicio;";
		
		ResultSet rs = this.consulta(sql); 
		try { 
			while (rs.next()) {
				servicios[cont] = rs.getString("nombre");
				cont++;
			}
			} catch (SQLException e) { e.printStackTrace();}
		
		return servicios;
	}

	@Override
	public ArrayList<String> cargarClientesTabla() {
		// TODO Auto-generated method stub
		return null;
	}
}