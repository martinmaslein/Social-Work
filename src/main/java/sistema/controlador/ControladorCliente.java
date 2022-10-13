package sistema.controlador;

import sistema.modelo.cliente.DatosCliente;

public interface ControladorCliente extends ControladorSistema {

	public void cerrarSesion();

	public void salirAplicacion();

	public boolean modificarDatos(DatosCliente nuevosDatos);

	public void crearCupon(int monto, int familiares);
	
	public int obtenerTotalAbonar();

<<<<<<< HEAD
	public DatosCliente obtenerDatosCliente();
=======
	public int obtenerCantFamiliares();
>>>>>>> c1a994ab2688c2726adf900a26f2efe56c933943

}