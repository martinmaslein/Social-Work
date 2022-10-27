package sistema.controlador;

import javax.swing.JTable;

import sistema.utilidades.Pair;

public interface ControladorAdmin extends ControladorSistema {
	
	public void cerrarSesion();

	public void salirAplicacion();

	public boolean cargarPlan(String text, String text2, String text3);

	public String[] obtenerServicios();

	public boolean eliminarPlan(Pair<String, Integer> planSeleccionado);

	public String informacionPlan(String nombre);

	public boolean modificarPlan(String nombre, String text, String text2, JTable table_1);

}