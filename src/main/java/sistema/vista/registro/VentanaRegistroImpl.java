package sistema.vista.registro;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sistema.modelo.ModeloRegistro;
import sistema.modelo.cliente.ModeloClienteImpl;
import sistema.vista.login.VentanaLogin;
import sistema.vista.login.VentanaLoginImpl;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;

public class VentanaRegistroImpl extends JFrame implements VentanaRegistro {

	public VentanaRegistroImpl(VentanaLoginImpl login) {
		setSize(new Dimension(700, 700));
		this.inicializar();
		this.login = login;
		modeloRegistro = new ModeloRegistro();
	}

	public void mostrarVentana(boolean m) throws Exception {
		this.setVisible(m);
	}

	public void eliminarVentana() {
		this.dispose();
	}

	public void informar(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}

	protected JPanel PanelRegistro;
	protected VentanaLogin login;
	protected JTextField campoNombre;
	protected JTextField campoApellido;
	protected JTextField campoNombreDeUsuario;
	protected JTextField campoFechaNacimiento;
	protected JTextField campoDni;
	protected JTextField campoDireccion;
	protected JTextField campoCorreo;
	protected JTextField campoTelefono;
	protected JTextField campoContrasena;
	protected JComboBox<String> comboPlan ;
	protected ModeloRegistro modeloRegistro;
	
	private void inicializar() {
		this.setTitle("Registrarse");
		this.setResizable(false);
		this.setBounds(100, 100, 700, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.PanelRegistro = new JPanel();
		PanelRegistro.setBackground(new Color(224, 241, 238));
		PanelRegistro.setLayout(null);
		this.PanelRegistro.setPreferredSize(new Dimension(550, 500));
		this.PanelRegistro.setVisible(true);

		campoNombre = new JTextField();
		campoNombre.setBounds(146, 40, 295, 26);
		campoNombre.setPreferredSize(new Dimension(70, 50));
		campoNombre.setBorder(null);

		campoApellido = new JTextField();
		campoApellido.setBounds(146, 77, 295, 26);
		campoApellido.setPreferredSize(new Dimension(70, 50));
		campoApellido.setBorder(null);

		campoNombreDeUsuario = new JTextField();
		campoNombreDeUsuario.setBounds(146, 374, 295, 26);
		campoNombreDeUsuario.setPreferredSize(new Dimension(70, 50));
		campoNombreDeUsuario.setBorder(null);

		campoDni = new JTextField();
		campoDni.setBounds(146, 114, 295, 26);
		campoDni.setPreferredSize(new Dimension(70, 50));
		campoDni.setBorder(null);

		campoDireccion = new JTextField();
		campoDireccion.setBounds(146, 151, 295, 26);
		campoDireccion.setPreferredSize(new Dimension(70, 50));
		campoDireccion.setBorder(null);

		campoCorreo = new JTextField();
		campoCorreo.setBounds(146, 225, 295, 26);
		campoCorreo.setPreferredSize(new Dimension(70, 50));
		campoCorreo.setBorder(null);

		campoFechaNacimiento = new JTextField();
		campoFechaNacimiento.setBounds(146, 188, 295, 26);
		campoFechaNacimiento.setPreferredSize(new Dimension(70, 50));
		campoFechaNacimiento.setBorder(null);

		campoTelefono = new JTextField();
		campoTelefono.setBounds(146, 262, 295, 26);
		campoTelefono.setPreferredSize(new Dimension(70, 50));
		campoTelefono.setBorder(null);

		campoContrasena = new JTextField();
		campoContrasena.setBounds(146, 411, 295, 26);
		campoContrasena.setPreferredSize(new Dimension(70, 50));
		campoContrasena.setBorder(null);

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
		textoNombre.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		textoNombre.setBounds(60, 43, 73, 20);
		PanelRegistro.add(textoNombre);

		JLabel textoApellido = new JLabel("Apellido");
		textoApellido.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		textoApellido.setBounds(60, 83, 46, 14);
		PanelRegistro.add(textoApellido);

		JLabel textoDni = new JLabel("DNI");
		textoDni.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		textoDni.setBounds(60, 120, 46, 14);
		PanelRegistro.add(textoDni);

		JLabel textoDireccion = new JLabel("Direccion");
		textoDireccion.setFont(new Font("Yu Gothic", Font.BOLD, 11));
		textoDireccion.setBounds(60, 157, 59, 14);
		PanelRegistro.add(textoDireccion);

		JLabel textoFechaDeNacimiento = new JLabel("Fecha de ");
		textoFechaDeNacimiento.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		textoFechaDeNacimiento.setBounds(60, 182, 113, 21);
		PanelRegistro.add(textoFechaDeNacimiento);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		lblCorreo.setBounds(60, 231, 46, 14);
		PanelRegistro.add(lblCorreo);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		lblTelefono.setBounds(60, 268, 73, 14);
		PanelRegistro.add(lblTelefono);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		lblUsuario.setBounds(60, 380, 46, 14);
		PanelRegistro.add(lblUsuario);

		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		lblContrasenia.setBounds(60, 417, 76, 14);
		PanelRegistro.add(lblContrasenia);

		JButton btnConfirmar = new JButton("Confirmar");

		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean resultado = modeloRegistro.cargarCliente(crearCliente());
					if (resultado) {
						JOptionPane.showMessageDialog(null, "Datos cargados correctamente");
						campoNombre.setText("");
						campoApellido.setText("");
						campoDireccion.setText("");
						campoTelefono.setText("");
						campoFechaNacimiento.setText("");
						campoDni.setText("");
						campoCorreo.setText("");
						campoNombreDeUsuario.setText("");
						campoContrasena.setText("");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		btnConfirmar.setForeground(new Color(255, 255, 255));
		btnConfirmar.setBackground(new Color(119, 193, 181));
		btnConfirmar.setBounds(426, 463, 102, 26);
		btnConfirmar.setBorder(null);
		PanelRegistro.add(btnConfirmar);

		JButton btnVolver = new JButton("");
		btnVolver.setIcon(new ImageIcon("img\\flechi.png"));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarVentana();
				try {
					login.mostrarVentana(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnVolver.setBounds(10, 11, 35, 31);
		PanelRegistro.add(btnVolver);
		
		comboPlan = new JComboBox<String>();
		comboPlan.setBackground(new Color(255, 255, 255));
		comboPlan.setForeground(new Color(0, 0, 0));
		comboPlan.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		comboPlan.setBounds(146, 299, 46, 22);
		comboPlan.setFocusable(false);
		PanelRegistro.add(comboPlan);
		comboPlan.addItem("A");
		comboPlan.addItem("B");

		JLabel lblPlan = new JLabel("Plan");
		lblPlan.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		lblPlan.setBounds(60, 304, 73, 14);
		PanelRegistro.add(lblPlan);

		JLabel lblNacimiento = new JLabel("nacimiento");
		lblNacimiento.setFont(new Font("Yu Gothic UI", Font.BOLD, 11));
		lblNacimiento.setBounds(60, 193, 113, 21);
		PanelRegistro.add(lblNacimiento);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 *	protected JTextField campoNombre;--
	protected JTextField campoApellido;--
	protected JTextField campoNombreDeUsuario;
	protected JTextField campoFechaNacimiento;
	protected JTextField campoDni;
	protected JTextField campoDireccion;
	protected JTextField campoCorreo;
	protected JTextField campoTelefono;
	protected JTextField campoContrasena;
	 * @throws Exception 
	 * 
-	 * */

	public ModeloClienteImpl crearCliente() throws Exception {
		ModeloClienteImpl nuevoCliente = new ModeloClienteImpl(campoNombreDeUsuario.getText());
		nuevoCliente.setNombre(campoNombre.getText());
		nuevoCliente.setApellido(campoApellido.getText());
		nuevoCliente.setDireccion(campoDireccion.getText());
		nuevoCliente.setTelefono(campoTelefono.getText());
		nuevoCliente.setFechaNacimiento(campoFechaNacimiento.getText());
		nuevoCliente.setNroDocumento(Integer.parseInt(campoDni.getText()));
		nuevoCliente.setMail(campoCorreo.getText());
		nuevoCliente.setPlan(comboPlan.getItemAt(comboPlan.getSelectedIndex()));

		nuevoCliente.setNombreUsuario(campoNombreDeUsuario.getText());
		nuevoCliente.setContrasena(campoContrasena.getText());
		
		return nuevoCliente;
	}

	public static java.util.Date convertirStringADate(String p_fecha) throws Exception {
		java.util.Date retorno = null;
		if ((p_fecha != null)) {
			System.out.println(p_fecha);
			try {
				retorno = (new SimpleDateFormat("yyyy-MM-dd")).parse(p_fecha);
			} catch (ParseException ex) {
				throw new Exception("Se produjo un error en la conversión del string a fecha.");
			}
		}
		return retorno;
	}
}
