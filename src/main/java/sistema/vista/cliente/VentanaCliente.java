package sistema.vista.cliente;

import sistema.controlador.ControladorCliente;
import sistema.vista.Ventana;

public interface VentanaCliente extends Ventana {
	
	public void mostrarPanel(String panel);

	void registrarControlador(ControladorCliente controlador);	
}