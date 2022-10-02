package sistema.vista.login;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import sistema.controlador.ControladorLogin;
import sistema.vista.registro.VentanaRegistro;
import sistema.vista.registro.VentanaRegistroImpl;

import java.awt.Dimension;

public class VentanaLoginImpl extends JFrame implements VentanaLogin, ItemListener {

	private static final long serialVersionUID = 1L;

	public VentanaLoginImpl() {
		setSize(new Dimension(372, 223));
		this.inicializar();
	}

	public void eliminarVentana() {
		this.dispose();
	}

	public void informar(String mensaje) {

		JOptionPane.showMessageDialog(null, mensaje);
	}

	public void mostrarVentana() throws Exception {
		this.setVisible(true);
	}

	public void itemStateChanged(ItemEvent e) {
		String opcion = (String) e.getItem();

		if (opcion.equals("Cliente")) {
			this.loginLayout.show(this.panelLogin, "cliente");
		} else if (opcion.equals("Empleado")) {
			this.loginLayout.show(this.panelLogin, "empleado");
		}
	}

	public void registrarControlador(ControladorLogin controlador) {
		this.controlador = controlador;
	}

	public void poblarComboTipoUsuario() {
		this.getComboTipoUsuario().removeAllItems();
		this.getComboTipoUsuario().addItem("Empleado");
		this.getComboTipoUsuario().addItem("Administrador");
		this.getComboTipoUsuario().addItem("Cliente");
	}

	private String getUsuarioSeleccionado() {
		return (String) this.getComboTipoUsuario().getSelectedItem();
	}

	private String getUserName() {

		String username = null;

		username = (String) this.getCampoAdminUsername().getText();

		return username;
	}

	private char[] getPassword() {

		char[] password = null;

		password = this.getCampoAdminPassword().getPassword();

		return password;
	}

	protected ControladorLogin controlador;

	protected JPanel mainPanel;

	protected JComboBox<String> comboTipoUsuario;
	protected JPanel panelLogin;
	protected CardLayout loginLayout;

	protected JTextField campoAdminUsername;
	protected JPasswordField campoAdminPassword;

	protected JTextField campoClienteUsername;
	protected JPasswordField campoClientePassword;

	protected JTextField campoEmpleadoUsername;
	protected JPasswordField campoEmpleadoPassword;

	protected JButton btnAceptarLogin;
	protected JButton btnCancelarLogin;
	protected JButton btnRegistrarse;

	private void inicializar() {
		this.setType(Type.POPUP);
		this.setTitle("Ingreso al Sistema");
		this.setResizable(false);
		this.setBounds(100, 100, 306, 238);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(318, 150));
		this.mainPanel.setLayout(new BorderLayout());
		this.mainPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		this.mainPanel.add(this.crearPanelTipoUsuario(), BorderLayout.PAGE_START);
		this.mainPanel.add(this.crearPanelLogin(), BorderLayout.CENTER);
		this.mainPanel.add(this.crearPanelButtons(), BorderLayout.PAGE_END);

		this.registrarEventos();

		this.setContentPane(this.mainPanel);
		this.pack();
		this.setVisible(true);
	}

	private JPanel crearPanelButtons() {

		JPanel panelButtons = new JPanel();

		btnAceptarLogin = new JButton("Aceptar");
		panelButtons.add(btnAceptarLogin);

		btnCancelarLogin = new JButton("Cancelar");
		panelButtons.add(btnCancelarLogin);

		return panelButtons;
	}

	private JPanel crearPanelTipoUsuario() {

		JPanel panelTipoLogin = new JPanel();
		((FlowLayout) panelTipoLogin.getLayout()).setHgap(25);

		comboTipoUsuario = new JComboBox<String>();
		panelTipoLogin.add(comboTipoUsuario);
		btnRegistrarse = new JButton("Registrarse");
		panelTipoLogin.add(btnRegistrarse);
		comboTipoUsuario.addItemListener(this);

		return panelTipoLogin;
	}

	private JPanel crearPanelLogin() {

		this.loginLayout = new CardLayout();

		this.panelLogin = new JPanel();
		this.panelLogin.setLayout(this.loginLayout);
		
		this.panelLogin.add(this.crearPanelLoginAdmin(), "admin");

		return this.panelLogin;
	}

	private JPanel crearPanelLoginAdmin() {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JPanel panelFila1 = new JPanel();
		((FlowLayout) panelFila1.getLayout()).setHgap(25);

		JLabel lblUsername = new JLabel("Usuario:");
		this.campoAdminUsername = new JTextField();
		this.campoAdminUsername.setColumns(10);

		panelFila1.add(lblUsername);
		panelFila1.add(this.campoAdminUsername);

		JPanel panelFila2 = new JPanel();

		JLabel lblPasswordLogin = new JLabel("Contraseña:");

		this.campoAdminPassword = new JPasswordField();
		this.campoAdminPassword.setColumns(10);

		panelFila2.add(lblPasswordLogin);
		panelFila2.add(this.campoAdminPassword);

		panel.add(panelFila1);
		panel.add(panelFila2);

		return panel;
	}

	protected JTextField getCampoAdminUsername() {
		return campoAdminUsername;
	}

	protected JPasswordField getCampoAdminPassword() {
		return campoAdminPassword;
	}

	protected JTextField getCampoClienteUsername() {
		return campoClienteUsername;
	}

	protected JPasswordField getCampoClientePassword() {
		return campoClientePassword;
	}

	protected JTextField getCampoEmpleadoUsername() {
		return campoEmpleadoUsername;
	}

	protected JPasswordField getCampoEmpleadoPassword() {
		return campoEmpleadoPassword;
	}

	protected JComboBox<String> getComboTipoUsuario() {
		return comboTipoUsuario;
	}

	protected void setComboTipoUsuario(JComboBox<String> comboTipoUsuario) {
		this.comboTipoUsuario = comboTipoUsuario;
	}

	protected JButton getBtnAceptarLogin() {
		return btnAceptarLogin;
	}

	protected JButton getBtnCancelarLogin() {
		return btnCancelarLogin;
	}

	protected void registrarEventos() {
		this.getBtnAceptarLogin().addActionListener(this.getIngresarListener());
		this.getBtnCancelarLogin().addActionListener(this.getCancelarListener());
		this.btnRegistrarse.addActionListener(this.getRegistrarseListener());
	}

	protected ActionListener getRegistrarseListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegistro registrarse = new VentanaRegistroImpl();
				try {
					registrarse.mostrarVentana();
					eliminarVentana();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		};
	}
	protected ActionListener getIngresarListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getUsuarioSeleccionado().equals("Cliente")) {
					controlador.ingresar(getUserName(), getPassword());
				} else {
					System.out.println("Intenta ingresar con un valor erroneo de usuario");
				}
			}
		};
	}

	protected ActionListener getCancelarListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
	}
}
