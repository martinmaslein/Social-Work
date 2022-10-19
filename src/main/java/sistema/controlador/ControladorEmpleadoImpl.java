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

	public List<Pair<String, String>> obtenerSolicitudes() {
		return modelo.obtenerSolicitudes();
	}
	
	public ArrayList<String> cargarClientesTabla() {
		return modelo.cargarClientesTabla();
		
	}

}
