package sistema.controlador;

import java.util.ArrayList;

import sistema.modelo.cliente.DatosCliente;
import sistema.utilidades.InvalidFormatException;

public interface ControladorCliente extends ControladorSistema {

	public void cerrarSesion();

	public void salirAplicacion();

	public boolean modificarDatos(DatosCliente nuevosDatos) throws InvalidFormatException;

	public void crearCupon(int monto, int familiares);
	
	public int obtenerTotalAbonar();

	public DatosCliente obtenerDatosCliente();
	
	public int obtenerCantFamiliares();

	public ArrayList<String> obtenerNombreFamiliares();

	public int obtenerPlan();

	public boolean solicitarCambioPlan(int i);

}