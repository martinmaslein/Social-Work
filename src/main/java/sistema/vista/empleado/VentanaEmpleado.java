package sistema.vista.empleado;

import sistema.controlador.ControladorEmpleado;
import sistema.vista.Ventana;

public interface VentanaEmpleado extends Ventana {
	
	public void mostrarPanel(String panel);

	void registrarControlador(ControladorEmpleado controlador);	
}
