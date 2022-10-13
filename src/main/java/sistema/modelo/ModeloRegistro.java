package sistema.modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sistema.modelo.cliente.DatosCliente;
import sistema.modelo.cliente.ModeloClienteImpl;
import sistema.modelo.familiar.ModeloFamiliarImpl;

public class ModeloRegistro extends ModeloImpl {
		
	public boolean cargarCliente(DatosCliente nuevoCliente) throws Exception {
		boolean salida = false;
		
		if(esValido(nuevoCliente.getNombreUsuario(),12) && esValido(nuevoCliente.getContrasena(),20)) {
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
	
	public static boolean esValido(String cadena, int cant) { 
  
        String regex = "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[@#$%^&+=])"
                       + "(?=\\S+$).{8,20}$"; 
  
        Pattern p = Pattern.compile(regex); 
  
        if ( (cadena == null || (cadena.length() > cant) ) ) { 
            return false; 
        } 
  
        Matcher m = p.matcher(cadena);
        
        return m.matches(); 
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