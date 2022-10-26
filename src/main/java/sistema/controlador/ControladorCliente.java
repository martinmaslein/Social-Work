package sistema.controlador;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ComboBoxModel;

import javafx.util.Pair;
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

	public LinkedList<String> obtenerPlanes();

	public ArrayList<ArrayList<String>> obtenerInfoFamiliares();

	public boolean elimnarFamiliar(String nombre);
	
	public ArrayList<ArrayList<String>> obtenerSolicitudesABM();

	public void modificarDatosFamiliar(ArrayList<String> datosNuevos);

}