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
import javax.swing.border.EmptyBorder;

import sistema.controlador.ControladorCliente;
import sistema.modelo.ModeloRegistro;
import sistema.modelo.cliente.*;
import sistema.modelo.familiar.ModeloFamiliarImpl;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class VentanaClienteImpl extends JFrame implements VentanaCliente {

	private static final long serialVersionUID = 1L;

	protected ControladorCliente controlador;

	protected String usuario, contraseña;

	protected JFrame frame;
	protected CardLayout frameLayout;
	protected JLabel lblCliente;
	protected JMenuItem mntmCerrarSesion;
	protected JMenuItem mntmSalir;

	protected JPanel panelPpal, panelPpal2, panelFamiliar;
	private JTextField tfNombre;
	private JTextField tfContra;
	private JButton btnModificar_1;
	private JLabel lblNewLabel;
	private JButton btnInscribirFamiliar;
	private JTextField tfApellido;
	private JLabel lblDireccion;
	private JLabel lblMail;
	private JLabel lblTelefono;
	private JLabel lblFecha;
	private JLabel lblNacimiento;
	private JLabel lblNombreUsuario;
	private JTextField tfDireccion;
	private JTextField tfMail;
	private JTextField tfTelefono;
	private JTextField tfFechaNacimiento;
	private JTextField tfNombreUsuario;

	private JTextField campoNombre, campoApellido, campodireccion, campoFechaNac, campoTelefono, campoMail;
	private JLabel lblUsuario;
	private JLabel lblNroDoc;
	private JTextField tfDocumento;
	private JPanel panel_cupones;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton;

	protected ModeloRegistro modeloRegistro;

	public VentanaClienteImpl(String username, String password) {
		inicializar();
		this.frame.setVisible(true);
		this.contraseña = password;
		this.usuario = username;
		modeloRegistro = new ModeloRegistro();
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

		JButton btnVolver3 = new JButton("");
		btnVolver3.setBounds(10, 11, 35, 31);
		btnVolver3.setIcon(new ImageIcon("img\\flechi.png"));
		btnVolver3.setBorder(new EmptyBorder(0, 0, 0, 0));
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
		panel_cupones.add(btnVolver3);

		lblNewLabel_2 = new JLabel("Seleccione tipo de cupon");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(61, 140, 259, 35);
		panel_cupones.add(lblNewLabel_2);

		lblNewLabel_1 = new JLabel("Cupones");
		lblNewLabel_1.setBounds(369, 11, 118, 41);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 30));
		panel_cupones.add(lblNewLabel_1);

		JComboBox<String> comboBoxCupones = new JComboBox<String>();
		comboBoxCupones.setForeground(new Color(0, 0, 0));
		comboBoxCupones.setModel(new DefaultComboBoxModel(new String[] { "Mensual", "Semestral", "Anual" }));
		comboBoxCupones.setFont(new Font("Yu Gothic UI", Font.BOLD, 12));
		comboBoxCupones.setBounds(307, 141, 129, 35);
		panel_cupones.add(comboBoxCupones);

		btnNewButton = new JButton("Aceptar");
		btnNewButton.setBackground(new Color(119, 193, 181));
		btnNewButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(498, 141, 118, 35);
		panel_cupones.add(btnNewButton);
		btnNewButton.addActionListener(this.generarCupon());

		this.registrarEventos();
	}

	private ActionListener generarCupon() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controlador.crearCupon();
				JOptionPane.showMessageDialog(null, "Cupon creado correctamente.");

			}
		};
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

		panelFamiliar = new JPanel();
		panelFamiliar.setLayout(null);
		panelFamiliar.setBackground(new Color(224, 241, 238));
		frame.getContentPane().add(panelFamiliar);

		campoNombre = new JTextField();
		campoNombre.setBounds(236, 102, 191, 20);
		panelFamiliar.add(campoNombre);
		campoNombre.setColumns(10);

		campoApellido = new JTextField();
		campoApellido.setColumns(10);
		campoApellido.setBounds(236, 146, 191, 20);
		panelFamiliar.add(campoApellido);

		campoFechaNac = new JTextField();
		campoFechaNac.setColumns(10);
		campoFechaNac.setBounds(236, 190, 191, 20);
		panelFamiliar.add(campoFechaNac);

		campodireccion = new JTextField();
		campodireccion.setColumns(10);
		campodireccion.setBounds(236, 236, 191, 20);
		panelFamiliar.add(campodireccion);

		campoTelefono = new JTextField();
		campoTelefono.setColumns(10);
		campoTelefono.setBounds(236, 281, 191, 20);
		panelFamiliar.add(campoTelefono);

		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(137, 105, 69, 14);
		panelFamiliar.add(labelNombre);

		JLabel labelApellido = new JLabel("Apellido");
		labelApellido.setBounds(137, 149, 69, 14);
		panelFamiliar.add(labelApellido);

		JLabel labelFecha = new JLabel("Fecha nac");
		labelFecha.setBounds(137, 193, 89, 14);
		panelFamiliar.add(labelFecha);

		JLabel labelDireccion = new JLabel("Direccion");
		labelDireccion.setBounds(137, 239, 69, 14);
		panelFamiliar.add(labelDireccion);

		JLabel labelTelefono = new JLabel("Telefono");
		labelTelefono.setBounds(137, 284, 89, 14);
		panelFamiliar.add(labelTelefono);

		JButton btnCargarFamiliar = new JButton("Cargar familiar");
		btnCargarFamiliar.setBounds(236, 340, 131, 30);
		panelFamiliar.add(btnCargarFamiliar);
		btnCargarFamiliar.setBorder(null);
		btnCargarFamiliar.setForeground(new Color(255, 255, 255));
		btnCargarFamiliar.setBackground(new Color(119, 193, 181));
		btnCargarFamiliar.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnCargarFamiliar.addActionListener(this.nuevoFamiliar());

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblNombre.setBounds(36, 35, 98, 23);
		panelPpal2.add(lblNombre);

		tfNombre = new JTextField();
		tfNombre.setBorder(null);
		tfNombre.setBounds(126, 36, 167, 20);
		panelPpal2.add(tfNombre);
		tfNombre.setColumns(10);

		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblContrasena.setBounds(36, 143, 98, 23);
		panelPpal2.add(lblContrasena);

		tfContra = new JTextField();
		tfContra.setBorder(null);
		tfContra.setColumns(10);
		tfContra.setBounds(126, 144, 167, 20);
		panelPpal2.add(tfContra);

		btnModificar_1 = new JButton("Modificar");
		btnModificar_1.setBorder(null);
		btnModificar_1.setForeground(new Color(255, 255, 255));
		btnModificar_1.setBackground(new Color(119, 193, 181));
		btnModificar_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnModificar_1.addActionListener(this.modificarDatos());
		btnModificar_1.setBounds(126, 323, 116, 23);
		panelPpal2.add(btnModificar_1);

		tfApellido = new JTextField();
		tfApellido.setBorder(null);
		tfApellido.setBounds(126, 93, 167, 20);
		panelPpal2.add(tfApellido);
		tfApellido.setColumns(10);

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

		tfDireccion = new JTextField();
		tfDireccion.setBorder(null);
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(126, 194, 167, 20);
		panelPpal2.add(tfDireccion);

		tfMail = new JTextField();
		tfMail.setBorder(null);
		tfMail.setColumns(10);
		tfMail.setBounds(126, 251, 167, 20);
		panelPpal2.add(tfMail);

		tfTelefono = new JTextField();
		tfTelefono.setBorder(null);
		tfTelefono.setColumns(10);
		tfTelefono.setBounds(461, 36, 167, 20);
		panelPpal2.add(tfTelefono);

		tfFechaNacimiento = new JTextField();
		tfFechaNacimiento.setBorder(null);
		tfFechaNacimiento.setColumns(10);
		tfFechaNacimiento.setBounds(461, 93, 167, 20);
		panelPpal2.add(tfFechaNacimiento);

		tfNombreUsuario = new JTextField();
		tfNombreUsuario.setBorder(null);
		tfNombreUsuario.setColumns(10);
		tfNombreUsuario.setBounds(461, 144, 167, 20);
		panelPpal2.add(tfNombreUsuario);

		lblUsuario = new JLabel("usuario:");
		lblUsuario.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblUsuario.setBounds(370, 143, 98, 23);
		panelPpal2.add(lblUsuario);

		lblNroDoc = new JLabel("Documento:");
		lblNroDoc.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblNroDoc.setBounds(370, 193, 98, 23);
		panelPpal2.add(lblNroDoc);

		tfDocumento = new JTextField();
		tfDocumento.setColumns(10);
		tfDocumento.setBorder(null);
		tfDocumento.setBounds(461, 195, 167, 20);
		panelPpal2.add(tfDocumento);

		JButton btnModificarDatos = new JButton("Modificar Datos");
		btnModificarDatos.setBorder(null);
		btnModificarDatos.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnModificarDatos.setForeground(new Color(255, 255, 255));
		btnModificarDatos.setBackground(new Color(119, 193, 181));
		btnModificarDatos.setBounds(320, 76, 174, 23);
		panelPpal.add(btnModificarDatos);
		btnModificarDatos.addActionListener(this.nuevoListener());

		JButton btnVolver = new JButton("");
		btnVolver.setIcon(new ImageIcon("img\\flechi.png"));
		btnVolver.setBorder(null);
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

		JButton btnVolver2 = new JButton("");
		btnVolver2.setBounds(10, 11, 35, 31);
		btnVolver2.setIcon(new ImageIcon("img\\flechi.png"));
		btnVolver2.setBorder(new EmptyBorder(0, 0, 0, 0));
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

	private ActionListener nuevoFamiliar() {
		return (new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					boolean resultado = modeloRegistro.cargarFamiliar(usuario, crearFamiliar());
					if (resultado) {
						JOptionPane.showMessageDialog(null, "Datos cargados correctamente");
						campoNombre.setText("");
						campoApellido.setText("");
						campodireccion.setText("");
						campoTelefono.setText("");
						campoFechaNac.setText("");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
	}

	private ModeloFamiliarImpl crearFamiliar() {
		ModeloFamiliarImpl nuevoFamiliar = new ModeloFamiliarImpl();
		nuevoFamiliar.setNombre(campoNombre.getText());
		nuevoFamiliar.setApellido(campoApellido.getText());
		nuevoFamiliar.setFechaNacimiento(campoFechaNac.getText());
		nuevoFamiliar.setDireccion(campodireccion.getText());
		nuevoFamiliar.setTelefono(campoTelefono.getText());
		return nuevoFamiliar;
	}

	protected ActionListener nuevoListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPpal.setVisible(false);
				panelPpal2.setVisible(true);
			}
		};
	}

	protected ActionListener listenerFamiliar() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPpal.setVisible(false);
				panelFamiliar.setVisible(true);

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
				DatosCliente nuevosDatos =construirDatos();
				
				if(controlador.modificarDatos(nuevosDatos))
					JOptionPane.showMessageDialog(null, "Datos modificados correctamente.");

			}

			private DatosCliente construirDatos() {
				DatosCliente nuevosDatos = new DatosCliente();
				nuevosDatos.setApellido(tfApellido.getText());
				nuevosDatos.setContrasena(tfContra.getText());
				nuevosDatos.setDireccion(tfDireccion.getText());
				nuevosDatos.setTelefono(tfTelefono.getText());
				nuevosDatos.setNombre(tfNombre.getText());
				nuevosDatos.setNombreUsuario(tfNombreUsuario.getText());
				String aux= tfDocumento.getText();
				if(!aux.isEmpty()) {
					nuevosDatos.setNroDocumento(Integer.parseInt(aux));
				}
				nuevosDatos.setMail(tfMail.getText());
				nuevosDatos.setFechaNacimiento(tfFechaNacimiento.getText());
				return nuevosDatos;
			}
		};
	}

	private JMenuBar crearMenuOpciones() {
		JMenuBar barraDeMenu = new JMenuBar();
		JMenu menuOpciones = new JMenu("Menu");
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

		} else {
			throw new Exception("Error la ventana no esta disponible");
		}
	}

	public void eliminarVentana() {
		this.frame.dispose();
	}

	public void informar(String mensaje) {
		JOptionPane.showMessageDialog(this.frame, mensaje);
	}

	public void registrarControlador(ControladorCliente controlador) {
		
		this.controlador = controlador;
	}
}