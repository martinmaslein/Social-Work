package sistema.controlador;

import sistema.modelo.Usuario;
import sistema.modelo.admin.ModeloAdminImpl;
import sistema.modelo.ModeloLogin;
import sistema.modelo.cliente.ModeloUsuario;
import sistema.modelo.empleado.ModeloEmpleadoImpl;
import sistema.modelo.cliente.ModeloClienteImpl;
import sistema.vista.VentanaAdmin.VentanaAdmin;
import sistema.vista.VentanaAdmin.VentanaAdminImpl;
import sistema.vista.cliente.VentanaCliente;
import sistema.vista.cliente.VentanaClienteImpl;
import sistema.vista.empleado.VentanaEmpleado;
import sistema.vista.empleado.VentanaEmpleadoImpl;
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

			this.ventana.mostrarVentana(true);

			this.ventana.poblarComboTipoUsuario(); 
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void ingresar(String username, char [] password, String rol) {
		
		System.out.println(username);
		System.out.println(password);
		
		Usuario usuario = this.modelo.obtenerUsuario(rol);
		ModeloUsuario usuarioRol;

		if (usuario != null) {
			if(rol.equals("Cliente")) 		{
				usuarioRol = new ModeloClienteImpl(username);
				}
			else if(rol.equals("Empleado")) {
				usuarioRol = new ModeloEmpleadoImpl();}
			else {
				usuarioRol = new ModeloAdminImpl();}

			if (usuarioRol.conectar(usuario.getUsername(), usuario.getPassword())) {

				try {
					if (usuarioRol.autenticarUsuarioAplicacion(username, password) ){

						if(rol.equals("Cliente")) {
							VentanaCliente ventanaCliente = new VentanaClienteImpl(username, password.toString());
							ControladorCliente controladorCliente = new ControladorClienteImpl(ventanaCliente, (ModeloClienteImpl)usuarioRol);

							controladorCliente.ejecutar();
						}
						else if(rol.equals("Empleado")) {
							VentanaEmpleado ventanaEmpleado = new VentanaEmpleadoImpl();
							ControladorEmpleado controladorEmp = new ControladorEmpleadoImpl(ventanaEmpleado, usuarioRol);

							controladorEmp.ejecutar();
						}
						else {
							VentanaAdmin ventanaAdmin = new VentanaAdminImpl();
							ControladorAdmin controladorAdmin = new ControladorAdminImpl(ventanaAdmin, usuarioRol);

							controladorAdmin.ejecutar();
						}

						this.ventana.eliminarVentana(); 

					} else {

						this.ventana.informar("El usuario o contraseña ingresados son incorrectos.");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				this.ventana.informar("Error en la conexion con la BD.");
			}
		} else {
			this.ventana.informar("Error en el acceso a la informacion del usuario.");
		}
	}
}