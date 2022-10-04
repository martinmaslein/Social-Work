package sistema.controlador;

import sistema.modelo.ModeloLogin;
import sistema.modelo.ModeloLoginImpl;
import sistema.modelo.cliente.DatosCliente;
import sistema.modelo.cliente.ModeloClienteImpl;
import sistema.modelo.cliente.ModeloUsuario;
import sistema.vista.cliente.VentanaCliente;
import sistema.vista.login.VentanaLogin;
import sistema.vista.login.VentanaLoginImpl;

public class ControladorClienteImpl implements ControladorCliente {
	
	private VentanaCliente ventana;
	private ModeloClienteImpl modelo;
	
	public ControladorClienteImpl(VentanaCliente ventana, ModeloClienteImpl modelo) {		
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
	public boolean modificarDatos(DatosCliente nuevosDatos) {
		return modelo.modificarDatos(nuevosDatos);
		
	}
	
	public void crearCupon() {
		modelo.generarCupon();
	}
}