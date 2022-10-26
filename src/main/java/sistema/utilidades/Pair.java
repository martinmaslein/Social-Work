package sistema.utilidades;

@SuppressWarnings("hiding")
public class Pair<String,Integer> {
	
	private String nombre;
	private int precio;
	
	public Pair(String n, int r) {
		nombre = n;
		precio = r;
	}
	
	public void setNombre(String name) {
		nombre = name;
	}
	
	public void setPrecio(int reint) {
		precio = reint;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getPrecio() {
		return precio;
	}
}
