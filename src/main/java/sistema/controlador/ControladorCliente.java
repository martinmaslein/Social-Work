package sistema.controlador;

import sistema.modelo.cliente.DatosCliente;

public interface ControladorCliente extends ControladorSistema {

	public void cerrarSesion();

	public void salirAplicacion();

	public boolean modificarDatos(DatosCliente nuevosDatos, String id);

	public void crearCupon();

}