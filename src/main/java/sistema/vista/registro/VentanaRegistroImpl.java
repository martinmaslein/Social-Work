package sistema.vista.registro;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import sistema.vista.login.VentanaLogin;
import sistema.vista.login.VentanaLoginImpl;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class VentanaRegistroImpl extends JFrame implements VentanaRegistro {

	public VentanaRegistroImpl(VentanaLoginImpl login) {
		setSize(new Dimension(700, 700));
		this.inicializar();
		this.login = login;
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

	private void inicializar() {
		this.setTitle("Registrarse");
		this.setResizable(false);
		this.setBounds(100, 100, 700, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.PanelRegistro = new JPanel();
		PanelRegistro.setLayout(null);
		this.PanelRegistro.setPreferredSize(new Dimension(550, 500));
		this.PanelRegistro.setVisible(true);

		campoNombre = new JTextField();
		campoNombre.setBounds(146, 40, 295, 26);
		campoNombre.setPreferredSize(new Dimension(70, 50));

		campoApellido = new JTextField();
		campoApellido.setBounds(146, 77, 295, 26);
		campoApellido.setPreferredSize(new Dimension(70, 50));

		campoNombreDeUsuario = new JTextField();
		campoNombreDeUsuario.setBounds(146, 374, 295, 26);
		campoNombreDeUsuario.setPreferredSize(new Dimension(70, 50));

		campoDni = new JTextField();
		campoDni.setBounds(146, 114, 295, 26);
		campoDni.setPreferredSize(new Dimension(70, 50));

		campoDireccion = new JTextField();
		campoDireccion.setBounds(146, 151, 295, 26);
		campoDireccion.setPreferredSize(new Dimension(70, 50));

		campoCorreo = new JTextField();
		campoCorreo.setBounds(146, 225, 295, 26);
		campoCorreo.setPreferredSize(new Dimension(70, 50));

		campoFechaNacimiento = new JTextField();
		campoFechaNacimiento.setBounds(146, 188, 295, 26);
		campoFechaNacimiento.setPreferredSize(new Dimension(70, 50));

		campoTelefono = new JTextField();
		campoTelefono.setBounds(146, 262, 295, 26);
		campoTelefono.setPreferredSize(new Dimension(70, 50));

		campoContrasena = new JTextField();
		campoContrasena.setBounds(146, 411, 295, 26);
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
		textoNombre.setBounds(60, 43, 73, 20);
		PanelRegistro.add(textoNombre);

		JLabel textoApellido = new JLabel("Apellido");
		textoApellido.setBounds(60, 83, 46, 14);
		PanelRegistro.add(textoApellido);

		JLabel textoDni = new JLabel("DNI");
		textoDni.setBounds(60, 120, 46, 14);
		PanelRegistro.add(textoDni);

		JLabel textoDireccion = new JLabel("Direccion");
		textoDireccion.setBounds(60, 157, 59, 14);
		PanelRegistro.add(textoDireccion);

		JLabel textoFechaDeNacimiento = new JLabel("Fecha de nac");
		textoFechaDeNacimiento.setBounds(60, 194, 113, 14);
		PanelRegistro.add(textoFechaDeNacimiento);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(60, 231, 46, 14);
		PanelRegistro.add(lblCorreo);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(60, 268, 73, 14);
		PanelRegistro.add(lblTelefono);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(60, 380, 46, 14);
		PanelRegistro.add(lblUsuario);

		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setBounds(60, 417, 76, 14);
		PanelRegistro.add(lblContrasenia);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(426, 463, 102, 26);
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

		JComboBox<String> comboPlan = new JComboBox();
		comboPlan.setBounds(146, 299, 46, 22);
		PanelRegistro.add(comboPlan);
		comboPlan.addItem("A");
		comboPlan.addItem("B");

		JLabel lblPlan = new JLabel("Plan");
		lblPlan.setBounds(60, 304, 73, 14);
		PanelRegistro.add(lblPlan);
		this.pack();
		this.setVisible(true);
	}
}
