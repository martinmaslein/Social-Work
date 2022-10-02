package sistema;

import java.awt.EventQueue;

import sistema.controlador.ControladorLogin;
import sistema.controlador.ControladorLoginImpl;
import sistema.modelo.ModeloLogin;
import sistema.modelo.ModeloLoginImpl;
import sistema.vista.login.VentanaLogin;
import sistema.vista.login.VentanaLoginImpl;

public class Sistema {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {		
					ModeloLogin modelo = new ModeloLoginImpl();  
					VentanaLogin ventana = new VentanaLoginImpl();
					ControladorLogin controlador = new ControladorLoginImpl(ventana, modelo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
