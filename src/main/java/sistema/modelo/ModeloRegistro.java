package sistema.modelo;

import sistema.modelo.cliente.DatosCliente;
import sistema.modelo.cliente.ModeloClienteImpl;
import sistema.modelo.familiar.ModeloFamiliarImpl;

public class ModeloRegistro extends ModeloImpl {
		
	public boolean cargarCliente(DatosCliente nuevoCliente) throws Exception {
		String nombre = nuevoCliente.getNombre();
		String apellido = nuevoCliente.getApellido();
		String telefono = nuevoCliente.getTelefono();
		int dni = nuevoCliente.getNroDocumento();
		String direccion = nuevoCliente.getDireccion();
		String fechaNac = nuevoCliente.getFechaNacimiento();
		String correo = nuevoCliente.getMail();
		String plan = nuevoCliente.getPlan();
		int nro_plan ;
		if (plan == "A") {
			nro_plan = 1;
		}else
			nro_plan = 2;
		String usuario = nuevoCliente.getNombreUsuario();
		String contrasena = nuevoCliente.getContrasena();
		
		String sql = "INSERT INTO Cliente (username, password, apellido,nombre,fecha_nac,direccion,telefono,correo,nro_doc,nro_plan) VALUES ('" +usuario +"', md5('"+contrasena+"'),'"+apellido+"', '"+nombre+"', '"+fechaNac+"','"+direccion+"', "+telefono+", '"+correo+"',"+dni+","+nro_plan+");";
		boolean salida = true;
		this.actualizacion(sql);
		return salida;
	}
	
	public boolean cargarFamiliar(String usuario, ModeloFamiliarImpl nuevoFamiliar) {
		String nombre = nuevoFamiliar.getNombre();
		String apellido = nuevoFamiliar.getApellido();
		String telefono = nuevoFamiliar.getTelefono();
		String direccion = nuevoFamiliar.getDireccion();
		String fechaNac = nuevoFamiliar.getFechaNacimiento();
		ModeloClienteImpl clienteActual = new ModeloClienteImpl(usuario,"");
		System.out.println("USUARIO: "+usuario);
		int id_cliente = clienteActual.getNroCliente();
		String sql = "INSERT INTO FAMILIAR (nro_cliente, apellido,nombre,fecha_nac,direccion,telefono) VALUES ("+id_cliente+",'"+apellido+"', '"+nombre+"', '"+fechaNac+"','"+direccion+"', "+telefono+");";
		boolean salida = true;
		this.actualizacion(sql);
		return salida;
	}

}