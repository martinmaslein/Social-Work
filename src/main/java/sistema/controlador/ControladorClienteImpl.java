package sistema.controlador;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ComboBoxModel;

import javafx.util.Pair;
import sistema.modelo.ModeloLogin;
import sistema.modelo.ModeloLoginImpl;
import sistema.modelo.cliente.DatosCliente;
import sistema.modelo.cliente.ModeloClienteImpl;
import sistema.modelo.cliente.ModeloUsuario;
import sistema.utilidades.InvalidFormatException;
import sistema.vista.cliente.VentanaCliente;
import sistema.vista.empleado.PanelPagoCliente;
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
		new ControladorLoginImpl(ventanaLogin, modeloLogin);
	}

	@Override
	public boolean modificarDatos(DatosCliente nuevosDatos) throws InvalidFormatException {
		if (modelo.sePuedeModificar(nuevosDatos))
			return modelo.modificarDatos(nuevosDatos);
		else
			return false;
		
	}
	
	public void crearCupon(int monto, int familiares) {
		modelo.generarCupon(monto, familiares);
		
	}

	@Override
	public int obtenerTotalAbonar() {
		return modelo.obtenerTotalAbonar();
		
	}

	@Override
	public DatosCliente obtenerDatosCliente() {
		return modelo.ObtenerDatosCliente();
	}
	
	@Override
	public int obtenerCantFamiliares() {
		return modelo.obtenerCantFamiliares();

	}

	@Override
	public ArrayList<String> obtenerNombreFamiliares() {
		// TODO Auto-generated method stub
		return modelo.obtenerNombreFamiliares();
	}

	@Override
	public int obtenerPlan() {
		// TODO Auto-generated method stub
		return modelo.obtenerPlanCliente();
	}

	
	public boolean solicitarCambioPlan(int plan) {
		return modelo.solicitarCambioPlan(plan);
	}

	@Override
	public LinkedList<String> obtenerPlanes() {
		return modelo.getPlanes();
	}

	@Override
	public ArrayList<ArrayList<String>> obtenerInfoFamiliares() {
		
		return modelo.obtenerInfoFamiliares();
	}

	@Override
	public boolean elimnarFamiliar(String nombre) {
		return modelo.eliminarFamiliar(nombre);
	}

	@Override

	public ArrayList<ArrayList<String>> obtenerSolicitudesABM() {		
		return modelo.obtenerSolicitudesABM();
	}

	public void modificarDatosFamiliar(ArrayList<String> datosNuevos) {
		modelo.modificarDatos(datosNuevos);
		
	}

	@Override
	public boolean eliminarSolicitud(ArrayList<String> solicitud) {
		return modelo.eliminarSolicitud(solicitud);
	}

	@Override
	public String informacionSolicitud(ArrayList<String> solicitud) {
		return modelo.informacionSolicitud(solicitud);
	}
}