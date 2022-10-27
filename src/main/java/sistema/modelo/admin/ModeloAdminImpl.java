package sistema.modelo.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

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

	public List<Pair<String, Integer>> obtenerPlanes() {
		List<Pair<String,Integer>> planes = new ArrayList<Pair<String,Integer>>();
		Pair<String, Integer> plan;
		String sql = "SELECT nombre, precio FROM plan"; 
		ResultSet rs = this.consulta(sql);
		try {
			while(rs.next()) {
				plan = new Pair<String,Integer>(rs.getString("Nombre"), rs.getInt("Precio"));
				planes.add(plan);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return planes;
	}
	
	public boolean eliminarPlan(Pair<String, Integer> plan){
		boolean salida = false;
		String sql = "SELECT nro_plan FROM plan WHERE nombre='"+plan.getNombre()+"';";
		ResultSet rs = this.consulta(sql);
		int id = 500;
		try {
			if (rs.next()) {
				id = rs.getInt("nro_plan");
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String sql1 = "SELECT * FROM cliente WHERE nro_plan="+id+";";
		ResultSet rs1 = this.consulta(sql1);
		try {
			if (rs1.next()) {
				salida = true;
			} else
				salida = false;
			rs1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(!salida) {
			String sql2= "DELETE FROM servicio_plan WHERE nro_plan="+id+";";
			String sql3= "DELETE FROM plan WHERE nro_plan="+id+";";
			this.actualizacion(sql2);
			this.actualizacion(sql3);
			salida = true;
		} else {
			salida = false;
		}
		
		return salida;
	}
	
	public Pair<String, Integer> obtenerPlan(String id) {
		Pair<String, Integer> plan = null;
		
		String sql = "SELECT nombre, precio FROM plan WHERE nombre='"+id+"';";
		ResultSet rs = this.consulta(sql);
		
		try {
			if(rs.next()) {
				String n = rs.getString("nombre");
				int p = rs.getInt("precio");
				plan = new Pair<String,Integer>(n,p);
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return plan;
	}
	
	@Override
	public String informacionPlan(String nombre) {
		String sql = "SELECT * FROM plan WHERE nombre='"+nombre+"';";
		ResultSet rs = this.consulta(sql);
		int id = 0;
		int precio = 0;
		String [] prestaciones;
		String toReturn = "";
		try {
			if(rs.next()) {
				id = rs.getInt("nro_plan");
				nombre = rs.getString("nombre");
				precio = rs.getInt("precio");
				rs.close();
			}
		
		String sql1 = "SELECT nro_servicio FROM servicio_plan WHERE nro_plan='"+id+"';";
		int [] servicios = new int[5];
		ResultSet rs1 = this.consulta(sql1);
		int i=0;
		while(rs1.next()) {
			servicios[i] = rs1.getInt("nro_servicio");
			i++;
		}
		rs1.close();
	
		
		String serviciosConcatenados = "";
		ResultSet rs2 = null;
		String serv = "";
		int j=0;
		for(int s : servicios) {
			String sql2 = "SELECT nombre FROM servicio WHERE nro_servicio='"+servicios[j]+"';";
			rs2 = this.consulta(sql2);
			if(rs2.next()) {
				serv = rs2.getString("nombre");
				serviciosConcatenados += "-  "+serv+"\n";
			}
			j++;
		}
		rs2.close();
		
		toReturn = "Plan : "+nombre+"\n"
										+"Precio : "+precio+"\n"
										+"Prestaciones : \n"+serviciosConcatenados+"";
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return toReturn;
		
	}
	
	public String [][] obtenerPrestaciones(String string) {
		int id = 0;
		String sql1 = "SELECT nro_plan FROM plan WHERE nombre='"+string+"'";
		ResultSet rs1 = this.consulta(sql1);
		try {
			if(rs1.next()) {
				id = rs1.getInt("nro_plan");
				rs1.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<Integer> ids = new ArrayList<Integer>();
		String sql2 = "SELECT nro_servicio FROM servicio_plan WHERE nro_plan='"+id+"'";
		ResultSet rs2 = this.consulta(sql2);
		try {
			while(rs2.next()) {
				ids.add(rs2.getInt("nro_servicio"));
			}
			rs2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int j=0;
		String data [][] = {{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""},{"",""}};
		for(int i : ids) {
			String sql3 = "SELECT nombre FROM servicio WHERE nro_servicio='"+i+"'";
			ResultSet rs3 = this.consulta(sql3);
			try {
				if(rs3.next()) {
					String nombre = rs3.getString("nombre");
					data[j][0] = nombre;
					j++;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return data;
	}

	public boolean cargarPlan(String nombre, String precio, String prestaciones) {
		 
		if(nombre == "" || precio == "" || prestaciones == "") {return false; }
		else if(chequearNombre(nombre) == false) {return false;}
		
		else{ 
			// Cargar Plan
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
			
			int cantServicio = cargarServicio(prestaciones);
			
			cargarServicioPlan(cant, cantServicio);
	
			return true;
		}
	}

	private void cargarServicioPlan(int cantPlan, int cantServicio) {
		
		// Obtengo el id_servicio_plan inicial --> cant
		String cantServicioPlan = "SELECT * FROM Servicio_Plan;"; 
		ResultSet rs = this.consulta(cantServicioPlan); 
		int cant = 0; 
		try { while (rs.next()) cant++; } catch (SQLException e) { e.printStackTrace();}
		cant += 1;		
		
		// Obtengo la cantidad de Servicios totales (para restarle el inicial)
		String cantServicios = "SELECT * FROM Servicio;";
		rs = this.consulta(cantServicios); 
		int i = 0; 
		try { while (rs.next()) i++; } catch (SQLException e) { e.printStackTrace();}
		i += 1;
		
		for(int j = cantServicio; j < i; j++) {
			
			String sql = "INSERT INTO Servicio_plan (id_servicio_plan,nro_servicio, nro_plan) VALUES (" + j + " , " 
					+ j + " , " + cantPlan +");";
		
			this.actualizacion(sql);
		}
		
	}

	private int cargarServicio(String prestaciones) {
		
		String lineas[] = prestaciones.split("\\r?\\n");
		int salida = lineas.length;
		
		String cantServicios = "SELECT * FROM Servicio;"; 
		ResultSet rs = this.consulta(cantServicios); 
		int cant = 0; 
		try { while (rs.next()) cant++; } catch (SQLException e) { e.printStackTrace();}
			
		int aux = cant+1;
		
		for(int i = 0; i < lineas.length; i++) {
			String sql = "INSERT INTO Servicio (nro_servicio,nombre) VALUES (" + aux + " , '" 
						+ lineas[i] + "');";
			
			this.actualizacion(sql);
			aux++;
		}
		return salida;
	}
	
	@Override
	public boolean modificarPlan(String nombre, String text, String text2, JTable table_1) {
		int precio = Integer.parseInt(text2);
		char nombreNuevo = text.charAt(0);
		String sql = "UPDATE plan SET nombre = '"+nombreNuevo+"', precio = "+precio+" WHERE nombre='"+nombre+"';"; 
		this.actualizacion(sql);
		String servicios = "";
		String [] prestaciones = new String[5];
		for(int i=0;i<5;i++) {
			prestaciones[i] = (String) table_1.getValueAt(i, 0);
			servicios += table_1.getValueAt(i, 0)+"\n";
		}
		
		int cant = cargarServicio(servicios);
		System.out.println("cantidad prestaciones = "+cant);
		
		String sql1 = "SELECT * FROM plan WHERE nombre = '"+nombreNuevo+"';";
		ResultSet rs1 = this.consulta(sql1);
		int id = 0;
		try {
			if(rs1.next()) {
				id = rs1.getInt("nro_plan");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql4 = "DELETE FROM Servicio_plan WHERE nro_plan="+id+";";
		this.actualizacion(sql4);
		
		
		try {
			int idServicio = 0;
			for(int j=0;j<cant;j++) {
				String sql2 = "SELECT nro_servicio FROM servicio WHERE nombre = '"+prestaciones[j]+"';";
				System.out.println(sql2);
				ResultSet rs2 = this.consulta(sql2);
				if(rs2.next()) {
					idServicio = rs2.getInt("nro_servicio");
				}
				
				String sql3= "INSERT INTO Servicio_plan (id_servicio_plan, nro_servicio, nro_plan) VALUES (NULL,"+idServicio+","+id+");";
				System.out.println(sql3);
				this.actualizacion(sql3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		return false;
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
	}
	
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
	public List<javafx.util.Pair<String, String>> obtenerSolicitudes() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<javafx.util.Pair<String, String>> cargarClientesTabla() {
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

	@Override
	public ArrayList<ArrayList<String>> obtenerSolicitudesABM() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminarSolicitud(ArrayList<String> solicitud) {
		// TODO Auto-generated method stub
		return false;
	}
}