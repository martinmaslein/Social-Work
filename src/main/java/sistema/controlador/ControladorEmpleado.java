package sistema.controlador;


import java.util.List;

import javafx.util.Pair;

import java.util.ArrayList;

public interface ControladorEmpleado extends ControladorSistema {

	public void cerrarSesion();

	public void salirAplicacion();
	
	public boolean modificarPlan(String dni, String plan) throws Exception;
	
	public List<Pair<String, String>> obtenerSolicitudesCambioPlan();

	public List<Pair<String, String>> cargarClientesTabla();
	
	public void aprobarCambioPlan(String nombre, String apellido) throws Exception;

	public void aprobarPago(String nombre, String apellido);

	public void desaprobarCambio(String string, String string2);
	
	public ArrayList<ArrayList<String>> obtenerSolicitudes();

	public void aprobarSolicitud(ArrayList<String> solicitudSeleccionada);

	public void desaprobarSolicitud(ArrayList<String> solicitud);

	public String informacionSolicitud(ArrayList<String> solicitudSeleccionada);

	public ArrayList<ArrayList<String>> obtenerInfoClientes();

	public ArrayList<String> getPlanes();

	public void actualizarPlanCliente(String string, String string2);

	public boolean eliminarCliente(String string);

}