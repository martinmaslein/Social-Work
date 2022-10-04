package sistema.vista.cliente;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import sistema.controlador.ControladorCliente;
import sistema.modelo.cliente.ModeloClienteImpl;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VentanaClienteImpl extends JFrame implements VentanaCliente {

	private static final long serialVersionUID = 1L;
	
	protected String usuario, contraseña;
	
	protected JFrame frame;
	protected CardLayout frameLayout;
	protected JLabel lblCliente;
	protected JMenuItem mntmCerrarSesion;
	protected JMenuItem mntmSalir;
	protected ControladorCliente controlador;
	JPanel panelPpal, panelPpal2;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnModificar_1;
	private JLabel lblNewLabel;
	private JButton btnInscribirFamiliar;
	private JTextField textField_2;
	private JLabel lblDireccion;
	private JLabel lblMail;
	private JLabel lblTelefono;
	private JLabel lblFecha;
	private JLabel lblNacimiento;
	private JLabel lblNombreUsuario;
	private JLabel lblPlan;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JLabel lblUsuario;
	private JLabel lblNroDoc;
	private JTextField textField_9;
	private JPanel panel_cupones;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton;
	
	public VentanaClienteImpl(String username, String password) {		
		inicializar();
		this.frame.setVisible(true);
		this.contraseña = password;
		this.usuario = username;
	}


	private void inicializar() {
		
		this.frame = new JFrame();
		this.frame.setTitle("Cliente");
		this.frame.setBounds(100, 100, 852, 575);
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.frameLayout = new CardLayout();
		this.frame.getContentPane().setLayout(this.frameLayout);

		this.lblCliente = new JLabel();
		this.lblCliente.setFont(new Font("Arial", Font.BOLD, 13));
		this.lblCliente.setHorizontalAlignment(SwingConstants.LEFT);
		
		this.frame.setJMenuBar(this.crearMenuOpciones());
		
		this.crearPaneles();
		
		lblNewLabel = new JLabel("Cliente");
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
		lblNewLabel.setBounds(365, 30, 89, 23);
		panelPpal.add(lblNewLabel);
		
		btnInscribirFamiliar = new JButton("Inscribir Familiar");
		btnInscribirFamiliar.setBorder(null);
		btnInscribirFamiliar.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnInscribirFamiliar.setForeground(new Color(255, 255, 255));
		btnInscribirFamiliar.setBackground(new Color(119, 193, 181));
		btnInscribirFamiliar.setBounds(320, 127, 174, 23);
		panelPpal.add(btnInscribirFamiliar);
		
		JButton btnGenerarCupon = new JButton("Generar Cupon");
		btnGenerarCupon.setForeground(Color.WHITE);
		btnGenerarCupon.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnGenerarCupon.setBorder(null);
		btnGenerarCupon.setBackground(new Color(119, 193, 181));
		btnGenerarCupon.setBounds(320, 176, 174, 23);
		panelPpal.add(btnGenerarCupon);
		btnGenerarCupon.addActionListener(this.listenerCupones());
		
		panel_cupones = new JPanel();
		panel_cupones.setBackground(new Color(224, 241, 238));
		frame.getContentPane().add(panel_cupones, "name_316681242860700");
		panel_cupones.setLayout(null);
		
		lblNewLabel_2 = new JLabel("Seleccione tipo de cupon");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(61, 140, 259, 35);
		panel_cupones.add(lblNewLabel_2);
		
		lblNewLabel_1 = new JLabel("Cupones");
		lblNewLabel_1.setBounds(369, 11, 118, 41);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 30));
		panel_cupones.add(lblNewLabel_1);
		
		JComboBox comboBoxCupones = new JComboBox();
		comboBoxCupones.setForeground(new Color(0, 0, 0));
		comboBoxCupones.setModel(new DefaultComboBoxModel(new String[] {"Semanal", "Mensual", "Anual"}));
		comboBoxCupones.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		comboBoxCupones.setBounds(307, 141, 129, 35);
		panel_cupones.add(comboBoxCupones);
		
		btnNewButton = new JButton("Aceptar");
		btnNewButton.setBackground(new Color(119, 193, 181));
		btnNewButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(498, 141, 118, 35);
		panel_cupones.add(btnNewButton);
		
		
		this.registrarEventos();	
	}
	
	private AbstractButton getMenuItemCerrarSesion() {
		return this.mntmCerrarSesion;
	}
	
	private AbstractButton getMenuItemSalir() {
		return this.mntmSalir;
	}
	
private void registrarEventos() {
		
		this.getMenuItemCerrarSesion().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.cerrarSesion();
			}
		});
			
		this.getMenuItemSalir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.salirAplicacion();
			}
		});

	}
	
	private Component crearPaneles() {
		panelPpal = new JPanel();
		panelPpal.setBackground(new Color(224, 241, 238));
		panelPpal.setLayout(null);
		frame.getContentPane().add(panelPpal);
		
		panelPpal2 = new JPanel();
		panelPpal2.setBackground(new Color(224, 241, 238));
		panelPpal2.setLayout(null);
		panelPpal2.setVisible(false);
		frame.getContentPane().add(panelPpal2);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblNombre.setBounds(36, 35, 98, 23);
		panelPpal2.add(lblNombre);
		
		textField = new JTextField();
		textField.setBorder(null);
		textField.setBounds(126, 36, 167, 20);
		panelPpal2.add(textField);
		textField.setColumns(10);
		
		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblContrasena.setBounds(36, 143, 98, 23);
		panelPpal2.add(lblContrasena);
		
		textField_1 = new JTextField();
		textField_1.setBorder(null);
		textField_1.setColumns(10);
		textField_1.setBounds(126, 144, 167, 20);
		panelPpal2.add(textField_1);
		
		btnModificar_1 = new JButton("Modificar");
		btnModificar_1.setBorder(null);
		btnModificar_1.setForeground(new Color(255, 255, 255));
		btnModificar_1.setBackground(new Color(119, 193, 181));
		btnModificar_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnModificar_1.addActionListener(this.modificarDatos());
		btnModificar_1.setBounds(126, 323, 116, 23);
		panelPpal2.add(btnModificar_1);
		
		textField_2 = new JTextField();
		textField_2.setBorder(null);
		textField_2.setBounds(126, 93, 167, 20);
		panelPpal2.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblApellido.setBounds(36, 92, 98, 23);
		panelPpal2.add(lblApellido);
		
		lblDireccion = new JLabel("Direccion:");
		lblDireccion.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblDireccion.setBounds(36, 193, 98, 23);
		panelPpal2.add(lblDireccion);
		
		lblMail = new JLabel("Mail:");
		lblMail.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblMail.setBounds(36, 250, 98, 23);
		panelPpal2.add(lblMail);
		
		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblTelefono.setBounds(370, 35, 98, 23);
		panelPpal2.add(lblTelefono);
		
		lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblFecha.setBounds(370, 78, 98, 23);
		panelPpal2.add(lblFecha);
		
		lblNacimiento = new JLabel("Nacimiento:");
		lblNacimiento.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblNacimiento.setBounds(370, 92, 98, 23);
		panelPpal2.add(lblNacimiento);
		
		lblNombreUsuario = new JLabel("Nombre");
		lblNombreUsuario.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblNombreUsuario.setBounds(370, 126, 98, 23);
		panelPpal2.add(lblNombreUsuario);
		
		lblPlan = new JLabel("Plan:");
		lblPlan.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblPlan.setBounds(370, 193, 98, 23);
		panelPpal2.add(lblPlan);
		
		textField_3 = new JTextField();
		textField_3.setBorder(null);
		textField_3.setColumns(10);
		textField_3.setBounds(126, 194, 167, 20);
		panelPpal2.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setBorder(null);
		textField_4.setColumns(10);
		textField_4.setBounds(126, 251, 167, 20);
		panelPpal2.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setBorder(null);
		textField_5.setColumns(10);
		textField_5.setBounds(461, 36, 167, 20);
		panelPpal2.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setBorder(null);
		textField_6.setColumns(10);
		textField_6.setBounds(461, 93, 167, 20);
		panelPpal2.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setBorder(null);
		textField_7.setColumns(10);
		textField_7.setBounds(461, 144, 167, 20);
		panelPpal2.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setBorder(null);
		textField_8.setColumns(10);
		textField_8.setBounds(461, 194, 167, 20);
		panelPpal2.add(textField_8);
		
		lblUsuario = new JLabel("usuario:");
		lblUsuario.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblUsuario.setBounds(370, 143, 98, 23);
		panelPpal2.add(lblUsuario);
		
		lblNroDoc = new JLabel("Documento:");
		lblNroDoc.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblNroDoc.setBounds(370, 250, 98, 23);
		panelPpal2.add(lblNroDoc);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBorder(null);
		textField_9.setBounds(461, 252, 167, 20);
		panelPpal2.add(textField_9);
		
		JButton btnModificarDatos = new JButton("Modificar Datos");
		btnModificarDatos.setBorder(null);
		btnModificarDatos.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnModificarDatos.setForeground(new Color(255, 255, 255));
		btnModificarDatos.setBackground(new Color(119, 193, 181));
		btnModificarDatos.setBounds(320, 76, 174, 23);
		panelPpal.add(btnModificarDatos);
		btnModificarDatos.addActionListener(this.nuevoListener());
		
		return panelPpal;			
	}
	
	protected ActionListener nuevoListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPpal.setVisible(false);
				panelPpal2.setVisible(true);	
			}
		};
	} 
	
	protected ActionListener listenerCupones() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPpal.setVisible(false);
				panelPpal2.setVisible(false);	
				panel_cupones.setVisible(true);	
			}
		};
	} 
	
	protected ActionListener modificarDatos() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				String id = "SELECT nro_cliente FROM Cliente WHERE username='"+usuario+"';";
				ModeloClienteImpl modeloCliente = new ModeloClienteImpl();
				if(modeloCliente.modificarDatos(textField.getText(), textField_1.getText(), id))
					JOptionPane.showMessageDialog(null, "Datos modificados correctamente.");
				
				
			}
		};
	}
	
	
	private JMenuBar crearMenuOpciones() {
		JMenuBar barraDeMenu = new JMenuBar();
		JMenu menuOpciones=new JMenu("Menu");
		menuOpciones.setForeground(new Color(0, 0, 0));
		menuOpciones.setFont(new Font("Segoe UI", Font.BOLD, 17));
		barraDeMenu.add(menuOpciones);

		menuOpciones.add(new JSeparator());
		
		this.mntmCerrarSesion = new JMenuItem("Cerrar Sesion");
		this.mntmCerrarSesion.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuOpciones.add(this.mntmCerrarSesion);

		this.mntmSalir = new JMenuItem("Salir");
		this.mntmSalir.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuOpciones.add(this.mntmSalir);
		
		return barraDeMenu;		
	}


	public void mostrarPanel(String panel) {
		this.frameLayout.show(this.frame.getContentPane(), panel);
	}	
	
	public void mostrarVentana(boolean m) throws Exception {
		if (this.frame != null) {
			this.frame.setVisible(m);
			
		}
		else{
			throw new Exception("Error la ventana no esta disponible");			
		}		
	}

	public void eliminarVentana() {
		this.frame.dispose();
	}


	public void informar(String mensaje) {
		JOptionPane.showMessageDialog(this.frame,mensaje);
	}


	public void registrarControlador(ControladorCliente controlador) {
		this.controlador = controlador;
	}
}