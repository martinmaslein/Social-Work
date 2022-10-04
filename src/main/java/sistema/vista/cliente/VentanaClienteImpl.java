package sistema.vista.cliente;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
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
	JPanel panelPpal, panelPpal2, panelFamiliar;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnModificar_1;
	private JLabel lblNewLabel;
	private JButton btnInscribirFamiliar;

	private JTextField campoNombre;
	private JTextField campoApellido;
	private JTextField campoFechaNac;
	private JTextField campodireccion;
	private JTextField campoTelefono;

	private JTextField textField_2;
	private JLabel lblDireccion;
	private JLabel lblMail;
	private JLabel lblTelefono;
	private JLabel lblFecha;
	private JLabel lblNacimiento;
	private JLabel lblNombreUsuario;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JLabel lblUsuario;
	private JLabel lblNroDoc;
	private JPanel panel_cupones;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton;
	private JLabel lblNacimiento_1;
	
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
		btnInscribirFamiliar.addActionListener(this.listenerFamiliar());
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
		
		/**/
		JButton btnVolver3 = new JButton("");
		btnVolver3.setIcon(new ImageIcon("img\\flechi.png"));
		btnVolver3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					panelPpal.setVisible(true);
					panel_cupones.setVisible(false);	
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnVolver3.setBounds(10, 11, 35, 31);
		panel_cupones.add(btnVolver3);
		
		lblNewLabel_2 = new JLabel("Seleccione tipo de cupon");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(61, 140, 259, 35);
		panel_cupones.add(lblNewLabel_2);
		
		lblNewLabel_1 = new JLabel("Cupones");
		lblNewLabel_1.setBounds(369, 11, 164, 69);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Yu Gothic", Font.BOLD, 32));
		panel_cupones.add(lblNewLabel_1);
		
		JComboBox comboBoxCupones = new JComboBox();
		comboBoxCupones.setForeground(new Color(0, 0, 0));
		comboBoxCupones.setModel(new DefaultComboBoxModel(new String[] {"Mensual", "Semestral", "Anual"}));
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
		panelPpal2.setVisible(false);
		frame.getContentPane().add(panelPpal2);
		
		panelFamiliar = new JPanel();
		panelFamiliar.setBackground(new Color(224, 241, 238));
		panelFamiliar.setLayout(null);
		panelFamiliar.setVisible(false);
		frame.getContentPane().add(panelFamiliar);
		
		campoNombre = new JTextField();
		campoNombre.setBorder(null);
		campoNombre.setBounds(236, 102, 191, 20);
		panelFamiliar.add(campoNombre);
		campoNombre.setColumns(10);
		
		campoApellido = new JTextField();
		campoApellido.setBorder(null);
		campoApellido.setColumns(10);
		campoApellido.setBounds(236, 156, 191, 20);
		panelFamiliar.add(campoApellido);
		
		campoFechaNac = new JTextField();
		campoFechaNac.setBorder(null);
		campoFechaNac.setColumns(10);
		campoFechaNac.setBounds(236, 205, 191, 20);
		panelFamiliar.add(campoFechaNac);
		
		campodireccion = new JTextField();
		campodireccion.setBorder(null);
		campodireccion.setColumns(10);
		campodireccion.setBounds(236, 253, 191, 20);
		panelFamiliar.add(campodireccion);
		
		campoTelefono = new JTextField();
		campoTelefono.setBorder(null);
		campoTelefono.setColumns(10);
		campoTelefono.setBounds(236, 298, 191, 20);
		panelFamiliar.add(campoTelefono);
		
		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		labelNombre.setBounds(137, 105, 69, 17);
		panelFamiliar.add(labelNombre);
		
		JLabel labelApellido = new JLabel("Apellido");
		labelApellido.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		labelApellido.setBounds(137, 149, 69, 33);
		panelFamiliar.add(labelApellido);
		
		JLabel labelFecha = new JLabel("Fecha");
		labelFecha.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		labelFecha.setBounds(137, 192, 89, 33);
		panelFamiliar.add(labelFecha);
		
		JLabel labelDireccion = new JLabel("Direccion");
		labelDireccion.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		labelDireccion.setBounds(137, 254, 69, 14);
		panelFamiliar.add(labelDireccion);
		
		JLabel labelTelefono = new JLabel("Telefono");
		labelTelefono.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		labelTelefono.setBounds(137, 299, 69, 14);
		panelFamiliar.add(labelTelefono);
		
		JButton btnCargarFamiliar = new JButton("CargarFamiliar");
		btnCargarFamiliar.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnCargarFamiliar.setBorder(null);
		btnCargarFamiliar.setForeground(new Color(255, 255, 255));
		btnCargarFamiliar.setBackground(new Color(119, 193, 181));
		btnCargarFamiliar.setBounds(262, 356, 131, 30);
		panelFamiliar.add(btnCargarFamiliar);
		panelPpal2.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(35, 58, 98, 23);
		panelPpal2.add(lblNombre);

		lblNombre.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		panelPpal2.add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(125, 60, 167, 20);
		textField.setBorder(null);
		panelPpal2.add(textField);
		textField.setColumns(10);
		
		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setBounds(35, 167, 98, 23);
		lblContrasena.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		panelPpal2.add(lblContrasena);

		textField_1 = new JTextField();
		textField_1.setBounds(125, 169, 167, 20);
		textField_1.setBorder(null);
		textField_1.setColumns(10);
		panelPpal2.add(textField_1);
		
		btnModificar_1 = new JButton("Modificar");
		btnModificar_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btnModificar_1.setBounds(460, 272, 116, 23);
		btnModificar_1.setBorder(null);
		btnModificar_1.setForeground(new Color(255, 255, 255));
		btnModificar_1.setBackground(new Color(119, 193, 181));
		btnModificar_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnModificar_1.addActionListener(this.modificarDatos());
		panelPpal2.add(btnModificar_1);
		
			
		JButton btnVolver = new JButton("");
		btnVolver.setIcon(new ImageIcon("img\\flechi.png"));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					panelPpal.setVisible(true);
					panelFamiliar.setVisible(false);	
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnVolver.setBounds(10, 11, 35, 31);
		panelFamiliar.add(btnVolver);
		
		lblNacimiento_1 = new JLabel("nacimiento:");
		lblNacimiento_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblNacimiento_1.setBounds(137, 205, 89, 35);
		panelFamiliar.add(lblNacimiento_1);
		panelPpal2.add(btnModificar_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(125, 117, 167, 20);
		textField_2.setBorder(null);
		panelPpal2.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lbApellido = new JLabel("Apellido:");
		lbApellido.setBounds(35, 116, 98, 23);
		lbApellido.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		panelPpal2.add(lbApellido);
		
		lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(35, 217, 98, 23);
		lblDireccion.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		panelPpal2.add(lblDireccion);
		
		lblMail = new JLabel("Mail:");
		lblMail.setBounds(35, 274, 98, 23);
		lblMail.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		panelPpal2.add(lblMail);
		
		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(369, 59, 98, 23);
		lblTelefono.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		panelPpal2.add(lblTelefono);
		
		lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(369, 102, 98, 23);
		lblFecha.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		panelPpal2.add(lblFecha);
		
		lblNacimiento = new JLabel("Nacimiento:");
		lblNacimiento.setBounds(369, 116, 98, 23);
		lblNacimiento.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		panelPpal2.add(lblNacimiento);
		
		lblNombreUsuario = new JLabel("Nombre");
		lblNombreUsuario.setBounds(369, 150, 98, 23);
		lblNombreUsuario.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		panelPpal2.add(lblNombreUsuario);
		
		textField_3 = new JTextField();
		textField_3.setBounds(125, 218, 167, 20);
		textField_3.setBorder(null);
		textField_3.setColumns(10);
		panelPpal2.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(125, 275, 167, 20);
		textField_4.setBorder(null);
		textField_4.setColumns(10);
		panelPpal2.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setBounds(460, 60, 167, 20);
		textField_5.setBorder(null);
		textField_5.setColumns(10);
		panelPpal2.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setBounds(460, 117, 167, 20);
		textField_6.setBorder(null);
		textField_6.setColumns(10);
		panelPpal2.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setBounds(460, 168, 167, 20);
		textField_7.setBorder(null);
		textField_7.setColumns(10);
		panelPpal2.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setBounds(460, 218, 167, 20);
		textField_8.setBorder(null);
		textField_8.setColumns(10);
		panelPpal2.add(textField_8);
		
		lblUsuario = new JLabel("usuario:");
		lblUsuario.setBounds(369, 167, 98, 23);
		lblUsuario.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		panelPpal2.add(lblUsuario);
		
		lblNroDoc = new JLabel("Documento:");
		lblNroDoc.setBounds(369, 217, 98, 23);
		lblNroDoc.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		panelPpal2.add(lblNroDoc);
		
		JButton btnModificarDatos = new JButton("Modificar Datos");
		btnModificarDatos.setBorder(null);
		btnModificarDatos.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnModificarDatos.setForeground(new Color(255, 255, 255));
		btnModificarDatos.setBackground(new Color(119, 193, 181));
		btnModificarDatos.setBounds(320, 76, 174, 23);
		panelPpal.add(btnModificarDatos);
		btnModificarDatos.addActionListener(this.nuevoListener());
		
		/**/
		JButton btnVolver2 = new JButton("");
		btnVolver2.setBounds(10, 11, 35, 31);
		btnVolver2.setIcon(new ImageIcon("img\\flechi.png"));
		btnVolver2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					panelPpal.setVisible(true);
					panelPpal2.setVisible(false);	
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		panelPpal2.add(btnVolver2);
		
		
		return panelPpal;			
	}
	
	private ActionListener listenerCargarFamiliar() {
		// TODO Auto-generated method stub
		return null;
	}

	
	protected ActionListener listenerFamiliar() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPpal.setVisible(false);
				panelFamiliar.setVisible(true);	
			}
		};
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