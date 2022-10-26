package sistema.vista.cliente;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sistema.controlador.ControladorCliente;
import sistema.modelo.ModeloRegistro;
import sistema.modelo.cliente.*;
import sistema.modelo.familiar.ModeloFamiliarImpl;
import sistema.utilidades.InvalidFormatException;
import sistema.utilidades.Pair;
import sistema.vista.empleado.PanelModificarPlan;

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

	protected DatosCliente datos;

	protected JFrame frame;
	protected CardLayout frameLayout;
	protected JLabel lblCliente;

	protected JPanel panelPpal, panelModificarDatos, panelCargarFamiliar, panelABMfamiliares,panelABMSolicitudes;
	private JTextField tfNombre;
	private JTextField tfContra;
	private JButton btnModificar_1;
	private JLabel lblNewLabel;
	private JButton btnMiPlanFamiliar;
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

	private JTextField campoNombre, campoApellido, campodireccion, campoFechaNac, campoTelefono;
	private JLabel lblUsuario;
	private JLabel lblNroDoc;
	private JTextField tfDocumento;
	private JPanel panel_cupones;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton;

	protected ModeloRegistro modeloRegistro;
	private JButton btnObtenerTotalA;
	private JLabel lblPlan;

	protected JComboBox<String> planComboBox;
	private JButton btnMisSolicitudes;

	JScrollPane scrollPane;
	private JLabel lblAcciones_1;
	private JButton btnVerMas_1;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnAgregarFamiliar;
	
	private ArrayList<String> familiarSeleccionado;
	
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

		this.crearPaneles();
		this.crearBotonesPpal();
		this.crearBotonesCupones();
	}

	private Component crearPaneles() {

		// Creo los 3 paneles
		panelPpal = new JPanel();
		panelPpal.setBackground(new Color(224, 241, 238));
		panelPpal.setLayout(null);
		frame.getContentPane().add(panelPpal);

		panelModificarDatos = new JPanel();
		panelModificarDatos.setBackground(new Color(224, 241, 238));
		panelModificarDatos.setLayout(null);
		panelModificarDatos.setVisible(false);
		frame.getContentPane().add(panelModificarDatos);
		
		panelABMfamiliares = new JPanel();
		panelABMfamiliares.setLayout(null);
		panelABMfamiliares.setBackground(new Color(224, 241, 238));
		frame.getContentPane().add(panelABMfamiliares);
		


		panelCargarFamiliar = new JPanel();
		panelCargarFamiliar.setLayout(null);
		panelCargarFamiliar.setBackground(new Color(224, 241, 238));
		frame.getContentPane().add(panelCargarFamiliar);

		// Creo los botones
		craerBotonesPanelABMfamiliar();
		
		crearBotonesPanelCargarFamiliar();

		crearBotonesModificarDatos();

		CrearBotonesVolver();

		return panelPpal;
	}

	private void craerBotonesPanelABMfamiliar() {
		//Labels y Botones
		JLabel lblFamiliares = new JLabel("Familiares");
		lblFamiliares.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
		lblFamiliares.setBounds(80, 66, 129, 23);
		panelABMfamiliares.add(lblFamiliares);
		
	    lblAcciones_1 = new JLabel("Familiares");
	    lblAcciones_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
	    lblAcciones_1.setBounds(618, 150, 129, 23);
	    panelABMfamiliares.add(lblAcciones_1);
	    
	    btnVerMas_1 = new JButton("Ver Mas");
	    btnVerMas_1.setBounds(618, 209, 129, 34);
	    btnVerMas_1.addActionListener(this.verMas());
	    panelABMfamiliares.add(btnVerMas_1);
	    
	    btnEliminar = new JButton("Eliminar");
	    btnEliminar.setBounds(618, 278, 129, 34);
	    btnEliminar.addActionListener(this.eliminarFamiliar());
	    panelABMfamiliares.add(btnEliminar);
	    
	    btnModificar = new JButton("Modificar");
	    btnModificar.setBounds(618, 345, 129, 34);
	    panelABMfamiliares.add(btnModificar);
	    
	    btnAgregarFamiliar = new JButton("Agregar Familiar");
	    btnAgregarFamiliar.setFont(new Font("Tahoma", Font.BOLD, 14));
	    btnAgregarFamiliar.setBounds(604, 57, 155, 48);
	    btnAgregarFamiliar.addActionListener(this.cargarFamiliar());
	    panelABMfamiliares.add(btnAgregarFamiliar);
		
	    //ABM
		scrollPane = new JScrollPane(); 
		scrollPane.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(224, 241, 238));
		scrollPane.setMinimumSize(new Dimension(27, 27));
		scrollPane.setBounds(33, 188, 488, 219);
		panelABMfamiliares.add(scrollPane);
		

	
	}



	private void CrearBotonesVolver() {
		JButton btnVolver = new JButton("");
		btnVolver.setIcon(new ImageIcon("img\\flechi.png"));
		btnVolver.setBorder(null);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					panelABMfamiliares.setVisible(true);
					panelCargarFamiliar.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnVolver.setBounds(10, 11, 35, 31);
		panelCargarFamiliar.add(btnVolver);

		JButton btnVolver2 = new JButton("");
		btnVolver2.setBounds(10, 11, 35, 31);
		btnVolver2.setIcon(new ImageIcon("img\\flechi.png"));
		btnVolver2.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnVolver2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					panelPpal.setVisible(true);
					panelModificarDatos.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		panelModificarDatos.add(btnVolver2);
		
		JButton btnVolver3 = new JButton("");
		btnVolver3.setIcon(new ImageIcon("img\\flechi.png"));
		btnVolver3.setBorder(null);
		btnVolver3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					panelPpal.setVisible(true);
					panelABMfamiliares.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnVolver3.setBounds(10, 11, 35, 31);
		panelABMfamiliares.add(btnVolver3);

	}

	private void crearBotonesModificarDatos() {
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblNombre.setBounds(36, 35, 98, 23);
		panelModificarDatos.add(lblNombre);

		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblContrasena.setBounds(36, 143, 98, 23);
		panelModificarDatos.add(lblContrasena);

		btnModificar_1 = new JButton("Modificar");
		btnModificar_1.setBorder(null);
		btnModificar_1.setForeground(new Color(255, 255, 255));
		btnModificar_1.setBackground(new Color(119, 193, 181));
		btnModificar_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnModificar_1.addActionListener(this.modificarDatos());
		btnModificar_1.setBounds(126, 323, 116, 23);
		panelModificarDatos.add(btnModificar_1);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblApellido.setBounds(36, 92, 98, 23);
		panelModificarDatos.add(lblApellido);

		lblDireccion = new JLabel("Direccion:");
		lblDireccion.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblDireccion.setBounds(36, 193, 98, 23);
		panelModificarDatos.add(lblDireccion);

		lblMail = new JLabel("Mail:");
		lblMail.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblMail.setBounds(36, 250, 98, 23);
		panelModificarDatos.add(lblMail);

		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblTelefono.setBounds(370, 35, 98, 23);
		panelModificarDatos.add(lblTelefono);

		lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblFecha.setBounds(370, 78, 98, 23);
		panelModificarDatos.add(lblFecha);

		lblNacimiento = new JLabel("Nacimiento:");
		lblNacimiento.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblNacimiento.setBounds(370, 92, 98, 23);
		panelModificarDatos.add(lblNacimiento);

		lblNombreUsuario = new JLabel("Nombre");
		lblNombreUsuario.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblNombreUsuario.setBounds(370, 126, 98, 23);
		panelModificarDatos.add(lblNombreUsuario);

		lblUsuario = new JLabel("usuario:");
		lblUsuario.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblUsuario.setBounds(370, 143, 98, 23);
		panelModificarDatos.add(lblUsuario);

		lblNroDoc = new JLabel("Documento:");
		lblNroDoc.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblNroDoc.setBounds(370, 193, 98, 23);
		panelModificarDatos.add(lblNroDoc);

		lblPlan = new JLabel("Plan:");
		lblPlan.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPlan.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblPlan.setBounds(316, 250, 98, 23);
		panelModificarDatos.add(lblPlan);

		JButton btnSolicitudCambio = new JButton("Cambiar plan");
		btnSolicitudCambio.setForeground(Color.WHITE);
		btnSolicitudCambio.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnSolicitudCambio.setBorder(null);
		btnSolicitudCambio.setBackground(new Color(32, 178, 170));
		btnSolicitudCambio.setBounds(370, 284, 116, 23);
		btnSolicitudCambio.addActionListener(this.solicitudCambiarPlan());

		panelModificarDatos.add(btnSolicitudCambio);

	}

	private void crearBotonesPanelCargarFamiliar() {
		campoNombre = new JTextField();
		campoNombre.setBounds(236, 102, 191, 20);
		panelCargarFamiliar.add(campoNombre);
		campoNombre.setColumns(10);

		campoApellido = new JTextField();
		campoApellido.setColumns(10);
		campoApellido.setBounds(236, 146, 191, 20);
		panelCargarFamiliar.add(campoApellido);

		campoFechaNac = new JTextField();
		campoFechaNac.setColumns(10);
		campoFechaNac.setBounds(236, 190, 191, 20);
		panelCargarFamiliar.add(campoFechaNac);

		campodireccion = new JTextField();
		campodireccion.setColumns(10);
		campodireccion.setBounds(236, 236, 191, 20);
		panelCargarFamiliar.add(campodireccion);

		campoTelefono = new JTextField();
		campoTelefono.setColumns(10);
		campoTelefono.setBounds(236, 281, 191, 20);
		panelCargarFamiliar.add(campoTelefono);

		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(137, 105, 69, 14);
		panelCargarFamiliar.add(labelNombre);

		JLabel labelApellido = new JLabel("Apellido");
		labelApellido.setBounds(137, 149, 69, 14);
		panelCargarFamiliar.add(labelApellido);

		JLabel labelFecha = new JLabel("Fecha nac");
		labelFecha.setBounds(137, 193, 89, 14);
		panelCargarFamiliar.add(labelFecha);

		JLabel labelDireccion = new JLabel("Direccion");
		labelDireccion.setBounds(137, 239, 69, 14);
		panelCargarFamiliar.add(labelDireccion);

		JLabel labelTelefono = new JLabel("Telefono");
		labelTelefono.setBounds(137, 284, 89, 14);
		panelCargarFamiliar.add(labelTelefono);

		JButton btnCargarFamiliar = new JButton("Cargar familiar");
		btnCargarFamiliar.setBounds(236, 340, 131, 30);
		panelCargarFamiliar.add(btnCargarFamiliar);
		btnCargarFamiliar.setBorder(null);
		btnCargarFamiliar.setForeground(new Color(255, 255, 255));
		btnCargarFamiliar.setBackground(new Color(119, 193, 181));
		btnCargarFamiliar.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnCargarFamiliar.addActionListener(this.nuevoFamiliar());

	}

	private void crearBotonesCupones() {

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
		comboBoxCupones.setModel(new DefaultComboBoxModel<String>(new String[] { "Mensual", "Semestral", "Anual" }));
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

	}

	private void crearBotonesPpal() {
		// Boton Cliente
		lblNewLabel = new JLabel("Cliente");
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
		lblNewLabel.setBounds(363, 45, 89, 23);
		panelPpal.add(lblNewLabel);

		// Boton Mi plan Familiar
		btnMiPlanFamiliar = new JButton("Mi plan Familiar");
		btnMiPlanFamiliar.setBorder(null);
		btnMiPlanFamiliar.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnMiPlanFamiliar.setForeground(new Color(255, 255, 255));
		btnMiPlanFamiliar.setBackground(new Color(119, 193, 181));
		btnMiPlanFamiliar.setBounds(320, 168, 174, 23);
		btnMiPlanFamiliar.addActionListener(this.listenerFamiliar());
		panelPpal.add(btnMiPlanFamiliar);

		// Boton Generar Cupon
		JButton btnGenerarCupon = new JButton("Generar Cupon");
		btnGenerarCupon.setForeground(Color.WHITE);
		btnGenerarCupon.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnGenerarCupon.setBorder(null);
		btnGenerarCupon.setBackground(new Color(119, 193, 181));
		btnGenerarCupon.setBounds(319, 265, 174, 23);
		btnGenerarCupon.addActionListener(listenerCupones());
		panelPpal.add(btnGenerarCupon);

		// Boton Obtener Total a Abonar
		btnObtenerTotalA = new JButton("Obtener Total a Abonar");
		btnObtenerTotalA.setForeground(Color.WHITE);
		btnObtenerTotalA.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnObtenerTotalA.setBorder(null);
		btnObtenerTotalA.setBackground(new Color(119, 193, 181));
		btnObtenerTotalA.setBounds(320, 313, 174, 23);
		btnObtenerTotalA.addActionListener(this.listenerAbonar());
		panelPpal.add(btnObtenerTotalA);

		// Boton modificar Datos
		JButton btnMisDatos = new JButton("Mis Datos");
		btnMisDatos.setBorder(null);
		btnMisDatos.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnMisDatos.setForeground(new Color(255, 255, 255));
		btnMisDatos.setBackground(new Color(119, 193, 181));
		btnMisDatos.setBounds(320, 117, 174, 23);
		btnMisDatos.addActionListener(this.modificarDatosListener());
		panelPpal.add(btnMisDatos);
		
		btnMisSolicitudes = new JButton("Mis Solicitudes");
		btnMisSolicitudes.setForeground(Color.WHITE);
		btnMisSolicitudes.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnMisSolicitudes.setBorder(null);
		btnMisSolicitudes.setBackground(new Color(119, 193, 181));
		btnMisSolicitudes.setBounds(320, 214, 174, 23);
		btnMisSolicitudes.addActionListener(this.listenerMisSolicitudes());
		panelPpal.add(btnMisSolicitudes);
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.setForeground(Color.WHITE);
		btnCerrarSesion.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnCerrarSesion.setBorder(null);
		btnCerrarSesion.setBackground(new Color(119, 193, 181));
		btnCerrarSesion.setBounds(681, 11, 147, 23);
		panelPpal.add(btnCerrarSesion);
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.cerrarSesion();
			}
		});
		
	}

	private void iniciarCamposModificarDatos() {

		tfDireccion = new JTextField(datos.getDireccion());
		tfDireccion.setBorder(null);
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(126, 194, 167, 20);
		panelModificarDatos.add(tfDireccion);

		tfMail = new JTextField(datos.getMail());
		tfMail.setBorder(null);
		tfMail.setColumns(10);
		tfMail.setBounds(126, 251, 167, 20);
		panelModificarDatos.add(tfMail);

		tfTelefono = new JTextField(datos.getTelefono());
		tfTelefono.setBorder(null);
		tfTelefono.setColumns(10);
		tfTelefono.setBounds(461, 36, 167, 20);
		panelModificarDatos.add(tfTelefono);

		tfFechaNacimiento = new JTextField(datos.getFechaNacimiento());
		tfFechaNacimiento.setBorder(null);
		tfFechaNacimiento.setColumns(10);
		tfFechaNacimiento.setBounds(461, 93, 167, 20);
		panelModificarDatos.add(tfFechaNacimiento);

		tfNombreUsuario = new JTextField(datos.getNombreUsuario());
		tfNombreUsuario.setBorder(null);
		tfNombreUsuario.setColumns(10);
		tfNombreUsuario.setBounds(461, 144, 167, 20);
		panelModificarDatos.add(tfNombreUsuario);

		tfNombre = new JTextField(datos.getNombre());
		tfNombre.setBorder(null);
		tfNombre.setBounds(126, 36, 167, 20);
		panelModificarDatos.add(tfNombre);
		tfNombre.setColumns(10);

		tfApellido = new JTextField(datos.getApellido());
		tfApellido.setBorder(null);
		tfApellido.setBounds(126, 93, 167, 20);
		panelModificarDatos.add(tfApellido);
		tfApellido.setColumns(10);

		tfContra = new JTextField(contraseña);
		tfContra.setBorder(null);
		tfContra.setColumns(10);
		tfContra.setBounds(126, 144, 167, 20);
		panelModificarDatos.add(tfContra);

		tfDocumento = new JTextField(String.valueOf(datos.getNroDocumento()));
		tfDocumento.setColumns(10);
		tfDocumento.setBorder(null);
		tfDocumento.setBounds(461, 195, 167, 20);
		panelModificarDatos.add(tfDocumento);

		Vector<String> vector = new Vector<String>(controlador.obtenerPlanes());
		DefaultComboBoxModel<String> dcm = new DefaultComboBoxModel<String>(vector);
		planComboBox = new JComboBox<String>(dcm);
		planComboBox.setBounds(415, 251, 53, 23);
		panelModificarDatos.add(planComboBox);

	}

	private ActionListener listenerMisSolicitudes() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPpal.setVisible(false);
				panelABMSolicitudes = new PanelABMSolicitudes(controlador);				
				frame.getContentPane().add(panelABMSolicitudes);
				panelABMSolicitudes.setVisible(true);
				
			    
				JButton btnVolver3 = new JButton("");
				btnVolver3.setBounds(10, 11, 35, 31);
				btnVolver3.setIcon(new ImageIcon("img\\flechi.png"));
				btnVolver3.setBorder(new EmptyBorder(0, 0, 0, 0));
				btnVolver3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							panelPpal.setVisible(true);
							panelABMSolicitudes.setVisible(false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				panelABMSolicitudes.add(btnVolver3);
				

			}
		};
	}
	private ActionListener listenerAbonar() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int plan = controlador.obtenerPlan();
				// int monto = controlador.obtenerTotalAbonar();
				int monto = 0;
				ArrayList<String> nombresFamiliares = controlador.obtenerNombreFamiliares();
				int cant = nombresFamiliares.size();
				String mensaje = "";

				if (plan == 1)
					monto = 5000;
				else if (plan == 2)
					monto = 2500;

				for (int i = 0; i < cant; i++) {
					mensaje += " " + nombresFamiliares.get(i) + " = $" + monto + "\n";
				}
				mensaje += " Total = " + monto * cant;

				JOptionPane.showMessageDialog(null, mensaje);

			}
		};
	}

	private ActionListener generarCupon() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int monto = controlador.obtenerTotalAbonar();

				int familiares = controlador.obtenerCantFamiliares();

				controlador.crearCupon(monto, familiares);
				JOptionPane.showMessageDialog(null, "Cupon creado correctamente.");

			}
		};
	}


	private ActionListener nuevoFamiliar() {
		return (new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					if (!campoNombre.getText().isEmpty() && !campoApellido.getText().isEmpty()
							&& !campoFechaNac.getText().isEmpty() && !campodireccion.getText().isEmpty()
							&& !campoTelefono.getText().isEmpty()) {
						boolean resultado = modeloRegistro.cargarFamiliar(usuario, crearFamiliar());
						if (resultado) {
							JOptionPane.showMessageDialog(null, "Datos cargados correctamente");
							campoNombre.setText("");
							campoApellido.setText("");
							campodireccion.setText("");
							campoTelefono.setText("");
							campoFechaNac.setText("");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
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


	private ActionListener cargarFamiliar() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				panelABMfamiliares.setVisible(false);
				panelCargarFamiliar.setVisible(true);

			}
		};
	}
	
	protected ActionListener modificarDatosListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				panelPpal.setVisible(false);
				panelModificarDatos.setVisible(true);

			}
		};
	}

	protected ActionListener listenerFamiliar() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPpal.setVisible(false);
				panelABMfamiliares.setVisible(true);
			}
		};
	}

	protected ActionListener listenerCupones() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPpal.setVisible(false);
				panelModificarDatos.setVisible(false);
				panel_cupones.setVisible(true);
			}
		};
	}

	private ActionListener solicitudCambiarPlan() {

		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String planActual = controlador.obtenerDatosCliente().getPlan();
				if (planActual != planComboBox.getSelectedItem()) {
					// TODO ARREGLAR PARA QUE CONTROLE QUE SEA DISTINTO AL PLAN ACTUAL, NO ANDA
					int select = JOptionPane.showConfirmDialog(
							panelModificarDatos, "¿desea enviar una solicitud para cambiarse al plan "
									+ planComboBox.getSelectedItem() + "?",
							"Solicitar cambio de plan", JOptionPane.CANCEL_OPTION);
					System.out.println(select);

					if (select == JOptionPane.OK_OPTION) {
						if (controlador.solicitarCambioPlan(planComboBox.getSelectedIndex() + 1)) {
							JOptionPane.showMessageDialog(panelModificarDatos,
									"se a enviado su solicitud correctamente. Queda a la espera de que un empleado apruebe el cambio");
							;
						}
					}
				} else {
					JOptionPane.showMessageDialog(panelModificarDatos, "ya posee el plan seleccionado");
				}
			}
		};
	}

	protected ActionListener modificarDatos() {

		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DatosCliente nuevosDatos = construirNuevosDatos();

				try {
					if (controlador.modificarDatos(nuevosDatos))
						JOptionPane.showMessageDialog(null, "Datos modificados correctamente.");
					else
						JOptionPane.showMessageDialog(null,
								"Ya existe otro cliente con alguno de los siguientes datos ingrseados: nombre de usuario, documento, email o telefono",
								"No se pudo modificar los datos", JOptionPane.ERROR_MESSAGE);

				} catch (InvalidFormatException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "No se pudo modificar los datos",
							JOptionPane.ERROR_MESSAGE);
				}

			}

			private DatosCliente construirNuevosDatos() {
				DatosCliente nuevosDatos = new DatosCliente();
				nuevosDatos.setApellido(tfApellido.getText());
				nuevosDatos.setContrasena(tfContra.getText());
				nuevosDatos.setDireccion(tfDireccion.getText());
				nuevosDatos.setTelefono(tfTelefono.getText());
				nuevosDatos.setNombre(tfNombre.getText());
				nuevosDatos.setNombreUsuario(tfNombreUsuario.getText());
				String aux = tfDocumento.getText();
				if (!aux.isEmpty()) {
					nuevosDatos.setNroDocumento(Integer.parseInt(aux));
				}
				nuevosDatos.setMail(tfMail.getText());
				nuevosDatos.setFechaNacimiento(tfFechaNacimiento.getText());
				return nuevosDatos;
			}
		};
	}
	

	private ActionListener verMas() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String mensaje = "";
			if(familiarSeleccionado != null)
				mensaje += " Nombre: " + familiarSeleccionado.get(0) + " " + familiarSeleccionado.get(1) + "\n" 	
							+ " DNI: "+familiarSeleccionado.get(2)+"\n" + " Plan: "+familiarSeleccionado.get(3)+"\n"
							+ " Fecha_nac: "+familiarSeleccionado.get(4)+"\n"+" Direccion: "+familiarSeleccionado.get(5)+"\n"
							+ " Telefono: "+familiarSeleccionado.get(6);
			else
				mensaje += "Seleccione el nombre de un familiar";
			JOptionPane.showMessageDialog(null, mensaje);
			}
		};
	}
	

	private ActionListener eliminarFamiliar() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String [] botones = {"Confirmar", "Cancelar"};
			int opciones = JOptionPane.showConfirmDialog (null, " Desea eliminar el familiar?", "Eliminar",
					JOptionPane.OK_CANCEL_OPTION);
			
			boolean eliminado = false;
			if(opciones == 0) {
				eliminado = controlador.elimnarFamiliar(familiarSeleccionado.get(0));
				JOptionPane.showMessageDialog(null, "Familiar Eliminado");
				cargarABMFamiliares();
			}
			else if(opciones == 2)
				JOptionPane.showMessageDialog(null, "Proceso cancelado");	
			
			}
		};
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
		datos = controlador.obtenerDatosCliente();
		iniciarCamposModificarDatos();
		cargarABMFamiliares();
	}

	private void cargarABMFamiliares() {
		final ArrayList<ArrayList<String>> familiares = controlador.obtenerInfoFamiliares();
		
		String columna [] = {"Nombre","DNI", "Pan"};
		
		String data[][] = {{"","",""},{"","",""},{"","",""},{"","",""},{"","",""}};
		
		int i=0;
		for(ArrayList<String> familiar : familiares) {
			
			String nombre = familiar.get(0);
			String apellido = familiar.get(1);
			String dni = familiar.get(2);
	    	String plan = familiar.get(3);
	    	
			data[i][0] = nombre + " " + apellido;
			data[i][1] = dni;
			data[i][2] = plan;
			
			i = i+1;
		}
		DefaultTableModel tableModel = new DefaultTableModel(data,columna);
	    final JTable table = new JTable(tableModel);
	    table.setRowHeight(33);
	    scrollPane.setViewportView(table);
		
	    table.addMouseListener(new java.awt.event.MouseAdapter() { 
            public void mouseClicked(java.awt.event.MouseEvent evt) {
        		
        		int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());

                Object selectedObject = (Object) table.getModel().getValueAt(row, col);
                
               String nomAp = selectedObject.toString();
               String[] partes = nomAp.split(" ");
               String nombre = partes[0];
               System.out.println(nombre);
                
                for(ArrayList<String> familiar : familiares) {
                	if(nombre.equals(familiar.get(0))) {
                		familiarSeleccionado = familiar;
                		break;
                	}
               	
                }
       
            }
        });
	    
	    
	}
}