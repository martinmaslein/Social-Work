package sistema.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import sistema.modelo.cliente.ModeloClienteImpl;

public class ModeloRegistro extends ModeloImpl {
	
	ModeloClienteImpl cliente = new ModeloClienteImpl();
	
	public void cargarCliente(ModeloClienteImpl nuevoCliente) throws Exception {
		
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
		boolean salida;
		System.out.println(sql);
		this.actualizacion(sql);
	
	}

}