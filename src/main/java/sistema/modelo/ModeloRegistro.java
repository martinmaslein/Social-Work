package sistema.modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sistema.modelo.cliente.DatosCliente;
import sistema.modelo.cliente.ModeloClienteImpl;
import sistema.modelo.familiar.ModeloFamiliarImpl;

public class ModeloRegistro extends ModeloImpl {
		
	public boolean cargarCliente(DatosCliente nuevoCliente) throws Exception {
		boolean salida = false;
		if(datosValidos(nuevoCliente)) {
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
			salida = true;
			this.actualizacion(sql);
		} else {
			salida = false;
		}
		return salida;
	}
	
	public static boolean mailValido(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        if(!m.matches()) {
        	System.out.println("falla : "+ email);
        }
        
        return m.matches();
 }
	
	public static boolean esValido(String cadena, int cant) { 
		cant = cant-2;
		// entre 5 y cant caracteres
        String regex = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,"+cant+"}[a-zA-Z0-9]$"; 
  
        Pattern p = Pattern.compile(regex); 
  
        if (cadena == null) { 
            return false; 
        } 
  
        Matcher m = p.matcher(cadena);
        
        if(!m.matches()) {
        	System.out.println("falla : "+ cadena);
        }
        
        return m.matches(); 
    } 
	
	
	public static boolean datosValidos(DatosCliente nuevoCliente) {
		return mailValido(nuevoCliente.getMail()) && esValido(nuevoCliente.getNombreUsuario(),12) && esValido(nuevoCliente.getContrasena(),20);
	}
	
	public boolean cargarFamiliar(String usuario, ModeloFamiliarImpl nuevoFamiliar) {
		String nombre = nuevoFamiliar.getNombre();
		String apellido = nuevoFamiliar.getApellido();
		String telefono = nuevoFamiliar.getTelefono();
		String direccion = nuevoFamiliar.getDireccion();
		String fechaNac = nuevoFamiliar.getFechaNacimiento();
		ModeloClienteImpl clienteActual = new ModeloClienteImpl(usuario,"");
		
		int id_cliente = clienteActual.getNroCliente();
		
		String sql = "INSERT INTO FAMILIAR (nro_cliente, apellido,nombre,fecha_nac,direccion,telefono) VALUES ("+id_cliente+",'"+apellido+"', '"+nombre+"', '"+fechaNac+"','"+direccion+"', '"+telefono+"');";
		System.out.println(sql);
		this.actualizacion(sql);
		boolean salida = true;
		return salida;
	}

}