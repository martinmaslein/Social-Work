package sistema.vista.login;

import sistema.controlador.ControladorLogin;
import sistema.vista.Ventana;

public interface VentanaLogin extends Ventana {

	public void registrarControlador(ControladorLogin controlador);
		
	public void poblarComboTipoUsuario();
	
}
