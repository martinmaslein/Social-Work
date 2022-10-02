package sistema.vista.VentanaAdmin;

import sistema.controlador.ControladorAdmin;
import sistema.vista.Ventana;

public interface VentanaAdmin extends Ventana {
	
	public void mostrarPanel(String panel);

	void registrarControlador(ControladorAdmin controlador);	
}
