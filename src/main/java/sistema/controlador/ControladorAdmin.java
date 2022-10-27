package sistema.controlador;

import sistema.utilidades.Pair;

public interface ControladorAdmin extends ControladorSistema {
	
	public void cerrarSesion();

	public void salirAplicacion();

	public boolean cargarPlan(String text, String text2, String text3);

	public String[] obtenerServicios();

	public boolean eliminarPlan(Pair<String, Integer> planSeleccionado);

}