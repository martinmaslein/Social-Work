package sistema.controlador;

import sistema.modelo.Usuario;
import sistema.modelo.ModeloLogin;
import sistema.modelo.cliente.ModeloUsuario;
import sistema.modelo.cliente.ModeloClienteImpl;
import sistema.vista.cliente.VentanaCliente;
import sistema.vista.cliente.VentanaClienteImpl;
import sistema.vista.login.VentanaLogin;
 
public class ControladorLoginImpl implements ControladorLogin { 
	
	
	private VentanaLogin ventana; 
	private ModeloLogin modelo;	
	
	public ControladorLoginImpl(VentanaLogin ventana, ModeloLogin theModel) {
		this.ventana = ventana;	
		this.modelo = theModel;
		
		try {
			this.modelo.iniciarConexion();
			
			this.ventana.registrarControlador(this);
			
			this.ventana.mostrarVentana();
			
			this.ventana.poblarComboTipoUsuario();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void ingresar(String username, char[] password) {

		Usuario usuario = this.modelo.obtenerUsuario("Cliente");

		if (usuario != null) {
			
			ModeloUsuario cliente = new ModeloClienteImpl();
			
			if (cliente.conectar(usuario.getUsername(), usuario.getPassword())) {
			
				if (cliente.autenticarUsuarioAplicacion(cliente.getNombreUsuario(), cliente.getContrasena())) {

					VentanaCliente ventanaCliente = new VentanaClienteImpl();
					ControladorCliente controladorCliente = new ControladorClienteImpl(ventanaCliente,cliente);
				
					controladorCliente.ejecutar();			
					
					this.ventana.eliminarVentana();
					
				}
				else{
					this.ventana.informar("El usuario o contraseña ingresados son incorrectos.");
				}
			}
			else{
				this.ventana.informar("Error en la conexion con la BD.");
			}			
		}
		else{
			this.ventana.informar("Error en el acceso a la informacion del usuario.");
		}			
		
	}
}