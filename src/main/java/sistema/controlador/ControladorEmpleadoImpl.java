package sistema.controlador;

import java.util.List;
import javafx.util.Pair;
import java.util.ArrayList;
import sistema.modelo.ModeloLogin;
import sistema.modelo.ModeloLoginImpl;
import sistema.modelo.cliente.ModeloUsuario;
import sistema.vista.empleado.VentanaEmpleado;
import sistema.vista.login.VentanaLogin;
import sistema.vista.login.VentanaLoginImpl;

public class ControladorEmpleadoImpl implements ControladorEmpleado {
	
	private VentanaEmpleado ventana;
	private ModeloUsuario modelo;
	
	public ControladorEmpleadoImpl(VentanaEmpleado ventana, ModeloUsuario modelo) {		
		this.ventana = ventana;
		this.modelo = modelo;
		this.ventana.registrarControlador(this);		
	}
	
	public void ejecutar() {
		try {
			this.ventana.mostrarVentana(true);
		} catch (Exception e) {
			String s = "Se produjo un error al intentar crear la ventana";
			this.ventana.informar(e.getMessage());
		}
	}
	
	public boolean modificarPlan(String dni, String plan) throws Exception{
		return modelo.modificarPlan(dni,plan);
	
	}

	public void salirAplicacion() {
		this.modelo.desconectar();
		this.ventana.eliminarVentana();
		System.exit(0);
	}

	public void cerrarSesion() {
		this.modelo.desconectar();
		this.ventana.eliminarVentana();

		ModeloLogin modeloLogin = new ModeloLoginImpl();  
		VentanaLogin ventanaLogin = new VentanaLoginImpl();
		@SuppressWarnings("unused")
		ControladorLogin controlador = new ControladorLoginImpl(ventanaLogin, modeloLogin);
	}

	@Override

	public List<Pair<String, String>> obtenerSolicitudesCambioPlan() {
		return modelo.obtenerSolicitudesCambioPlan();
	}
	
	public List<Pair<String, String>> cargarClientesTabla() {
		return modelo.cargarClientesTabla();
		
	}
	

	@Override
	public void aprobarCambioPlan(String nombre, String apellido) throws Exception {
		modelo.aprobarCambioPlan(nombre,apellido);
		
	}

	@Override
	public void aprobarPago(String nombre, String apellido) {
		modelo.aprobarPago(nombre,apellido);
	}

	@Override
	public void desaprobarCambio(String nombre, String apellido) {
		modelo.desaprobarCambioPlan(nombre,apellido);
		
	}

	@Override
	public ArrayList<ArrayList<String>> obtenerSolicitudes() {
		return modelo.obtenerSolicitudesABM();
	}

	@Override
	public void aprobarSolicitud(ArrayList<String> solicitud) {
		modelo.aprobarSolicitud(solicitud);		
	}

	@Override
	public void desaprobarSolicitud(ArrayList<String> solicitud) {
		modelo.desaprobarSolicitud(solicitud);		
	}

	@Override
	public String informacionSolicitud(ArrayList<String> solicitudSeleccionada) {
		return modelo.informacionSolicitud(solicitudSeleccionada);
	}

	@Override
	public ArrayList<ArrayList<String>> obtenerInfoClientes() {
		return modelo.obtenerInfoClientes();
	}

	@Override
	public ArrayList<String> getPlanes() {
		
		return modelo.getPlanesTotales();
	}

	@Override
	public void actualizarPlanCliente(String string,String string2) {
		modelo.actualizarPlanCliente(string, string2);
		
	}

	@Override
	public boolean eliminarCliente(String string) {
		
		return modelo.eliminarCliente(string);
	}

}
