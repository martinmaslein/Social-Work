package sistema.utilidades;

@SuppressWarnings("hiding")
public class Pair<String,Integer> {
	
	private String nombre;
	private int reintegro;
	
	public Pair(String n, int r) {
		nombre = n;
		reintegro = r;
	}
	
	public void setNombre(String name) {
		nombre = name;
	}
	
	public void setReintegro(int reint) {
		reintegro = reint;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getReintegro() {
		return reintegro;
	}
}
