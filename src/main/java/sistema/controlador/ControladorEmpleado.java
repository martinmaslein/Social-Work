package sistema.controlador;

import java.util.ArrayList;

public interface ControladorEmpleado extends ControladorSistema {

	public void cerrarSesion();

	public void salirAplicacion();
	
	public boolean modificarPlan(String dni, String plan) throws Exception;

	public ArrayList<String> cargarClientesTabla();

}