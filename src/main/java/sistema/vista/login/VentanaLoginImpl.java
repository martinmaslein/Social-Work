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
import java.awt.Font;
import java.awt.Cursor;

public class VentanaLoginImpl extends JFrame implements VentanaLogin, ItemListener {

	private static final long serialVersionUID = 1L;

	public VentanaLoginImpl() {
		setBackground(new Color(202, 228, 255));
		setSize(new Dimension(700, 700));
		this.inicializar();
	}

	public void eliminarVentana() {
		this.dispose();
	}

	public void informar(String mensaje) {

		JOptionPane.showMessageDialog(null, mensaje);
	}

	public void mostrarVentana(boolean m) throws Exception {
		this.setVisible(m);
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

	protected VentanaLoginImpl este = this;
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
		mainPanel.setPreferredSize(new Dimension(518, 210));
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
		panelButtons.setBackground(new Color(224, 241, 238));

		btnAceptarLogin = new JButton("Aceptar");
		btnAceptarLogin.setBackground(new Color(119, 193, 181));
		btnAceptarLogin.setForeground(new Color(255, 255, 255));
		btnAceptarLogin.setFont(new Font("Segoe UI Variable", Font.BOLD, 15));
		btnAceptarLogin.setBorderPainted(false);
		panelButtons.add(btnAceptarLogin);

		btnCancelarLogin = new JButton("Cancelar");
		btnCancelarLogin.setForeground(new Color(255, 255, 255));
		btnCancelarLogin.setBackground(new Color(119, 193, 181));
		btnCancelarLogin.setFont(new Font("Segoe UI Variable", Font.BOLD, 15));
		btnCancelarLogin.setBorderPainted(false);
		panelButtons.add(btnCancelarLogin);

		return panelButtons;
	}

	private JPanel crearPanelTipoUsuario() {

		JPanel panelTipoLogin = new JPanel();
		panelTipoLogin.setBackground(new Color(224, 241, 238));
		panelTipoLogin.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		comboTipoUsuario = new JComboBox<String>();
		comboTipoUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		comboTipoUsuario.setForeground(new Color(0, 0, 0));
		comboTipoUsuario.setFont(new Font("Segoe UI Variable", Font.BOLD, 15));
		comboTipoUsuario.setBackground(new Color(255, 255, 255));
		comboTipoUsuario.setBorder(null);
		panelTipoLogin.add(comboTipoUsuario);
		comboTipoUsuario.setFocusable(false);
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBackground(new Color(119, 193, 181));
		btnRegistrarse.setForeground(new Color(255, 255, 255));
		btnRegistrarse.setFont(new Font("Segoe UI Variable", Font.BOLD, 15));
		btnRegistrarse.setBorderPainted(false);
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
		panelFila1.setBackground(new Color(224, 241, 238));

		JLabel lblUsername = new JLabel("Usuario:");
		lblUsername.setBounds(96, 34, 91, 15);
		lblUsername.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));
		this.campoAdminUsername = new JTextField();
		campoAdminUsername.setBounds(195, 33, 140, 23);
		this.campoAdminUsername.setColumns(10);
		campoAdminUsername.setBorder(null);
		panelFila1.setLayout(null);

		panelFila1.add(lblUsername);
		panelFila1.add(this.campoAdminUsername);

		JPanel panelFila2 = new JPanel();
		panelFila2.setBackground(new Color(224, 241, 238));

		JLabel lblPasswordLogin = new JLabel("Contraseña:");
		lblPasswordLogin.setBounds(65, 12, 122, 15);
		lblPasswordLogin.setFont(new Font("Segoe UI Variable", Font.BOLD, 16));

		this.campoAdminPassword = new JPasswordField();
		campoAdminPassword.setBounds(197, 11, 140, 23);
		this.campoAdminPassword.setColumns(10);
		campoAdminPassword.setBorder(null);
		panelFila2.setLayout(null);

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
				VentanaRegistro registrarse = new VentanaRegistroImpl(este);
				try {
					registrarse.mostrarVentana(true);
					mostrarVentana(false);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		};
	}
	protected ActionListener getIngresarListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getUsuarioSeleccionado().equals("Cliente")) {
					controlador.ingresar(getUserName(), getPassword(), "Cliente");
				} else if (getUsuarioSeleccionado().equals("Empleado")) {
					controlador.ingresar(getUserName(), getPassword(), "Empleado");
				} else if (getUsuarioSeleccionado().equals("Administrador")) {
					controlador.ingresar(getUserName(), getPassword(), "Administrador");
				}			
				else {
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
