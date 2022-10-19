package sistema.controlador;

import java.util.List;

import javafx.util.Pair;

public interface ControladorEmpleado extends ControladorSistema {

	public void cerrarSesion();

	public void salirAplicacion();
	
	public boolean modificarPlan(String dni, String plan) throws Exception;
	
	public List<Pair<String, String>> obtenerSolicitudes();

}