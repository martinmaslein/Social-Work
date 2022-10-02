package sistema.vista.registro;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sistema.controlador.ControladorCliente;
import sistema.vista.cliente.VentanaCliente;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaRegistroImpl extends JFrame implements VentanaRegistro {

	public VentanaRegistroImpl() {
		setSize (new Dimension(700,700));
		this.inicializar();
	}
	
	public void mostrarVentana() throws Exception {
		this.setVisible(true);
	}

	public void eliminarVentana() {
		this.dispose();
	}
		
	public void informar(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
		
	}
	
	protected JPanel PanelRegistro;
	
	protected JTextField campoNombre;
	protected JTextField campoApellido;
	protected JTextField campoNombreDeUsuario;
	protected JTextField campoFechaNacimiento;
	protected JTextField campoDni;
	protected JTextField campoDireccion;
	protected JTextField campoCorreo;
	protected JTextField campoTelefono;

	protected JTextField campoContrasena;
	
	
	
	private void inicializar() {
		this.setTitle("Registrarse");
		this.setResizable(false);
		this.setBounds(100, 100, 700,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.PanelRegistro = new JPanel();
		PanelRegistro.setLayout(null);
		this.PanelRegistro.setPreferredSize(new Dimension(550, 500));
		this.PanelRegistro.setVisible(true);
		
			
		campoNombre = new JTextField();
		campoNombre.setBounds(146, 57, 295, 26);
		campoNombre.setPreferredSize(new Dimension(70, 50));
		
		campoApellido = new JTextField();
		campoApellido.setBounds(146, 94, 295, 26);
		campoApellido.setPreferredSize(new Dimension(70, 50));
		
		campoNombreDeUsuario = new JTextField();
		campoNombreDeUsuario.setBounds(146, 354, 295, 26);
		campoNombreDeUsuario.setPreferredSize(new Dimension(70, 50));
		
		campoDni = new JTextField();
		campoDni.setBounds(146, 131, 295, 26);
		campoDni.setPreferredSize(new Dimension(70, 50));
		
		campoDireccion = new JTextField();
		campoDireccion.setBounds(146, 168, 295, 26);
		campoDireccion.setPreferredSize(new Dimension(70, 50));
		
		campoCorreo = new JTextField();
		campoCorreo.setBounds(146, 242, 295, 26);
		campoCorreo.setPreferredSize(new Dimension(70, 50));
		
		campoFechaNacimiento = new JTextField();
		campoFechaNacimiento.setBounds(146, 205, 295, 26);
		campoFechaNacimiento.setPreferredSize(new Dimension(70, 50));
		
		campoTelefono = new JTextField();
		campoTelefono.setBounds(146, 279, 295, 26);
		campoTelefono.setPreferredSize(new Dimension(70, 50));
		
		campoContrasena = new JTextField();
		campoContrasena.setBounds(146, 391, 295, 26);
		campoContrasena.setPreferredSize(new Dimension(70, 50));
		
		this.PanelRegistro.add(campoNombre);
		this.PanelRegistro.add(campoApellido);
		this.PanelRegistro.add(campoDni);
		this.PanelRegistro.add(campoFechaNacimiento);
		this.PanelRegistro.add(campoDireccion);
		this.PanelRegistro.add(campoCorreo);
		this.PanelRegistro.add(campoNombreDeUsuario);
		this.PanelRegistro.add(campoTelefono);
		this.PanelRegistro.add(campoContrasena);
		
		this.setContentPane(this.PanelRegistro);
		
		JLabel textoNombre = new JLabel("Nombre");
		textoNombre.setBounds(60, 60, 73, 20);
		PanelRegistro.add(textoNombre);
		
		JLabel textoApellido = new JLabel("Apellido");
		textoApellido.setBounds(60, 100, 46, 14);
		PanelRegistro.add(textoApellido);
		
		JLabel textoDni = new JLabel("DNI");
		textoDni.setBounds(60, 135, 46, 14);
		PanelRegistro.add(textoDni);
		
		JLabel textoDireccion = new JLabel("Direccion");
		textoDireccion.setBounds(60, 172, 59, 14);
		PanelRegistro.add(textoDireccion);
		
		JLabel textoFechaDeNacimiento = new JLabel("Fecha de nac");
		textoFechaDeNacimiento.setBounds(60, 211, 113, 14);
		PanelRegistro.add(textoFechaDeNacimiento);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(60, 246, 46, 14);
		PanelRegistro.add(lblCorreo);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(60, 283, 73, 14);
		PanelRegistro.add(lblTelefono);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(60, 360, 46, 14);
		PanelRegistro.add(lblUsuario);
		
		JLabel lblContrasenia = new JLabel("Contrase\u00F1a");
		lblContrasenia.setBounds(60, 397, 76, 14);
		PanelRegistro.add(lblContrasenia);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(223, 446, 102, 26);
		PanelRegistro.add(btnConfirmar);
		this.pack();
		this.setVisible(true);
	}
}
