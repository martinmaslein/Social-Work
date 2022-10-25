package sistema.vista.cliente;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import sistema.controlador.ControladorCliente;
import sistema.modelo.ModeloRegistro;
import sistema.modelo.cliente.*;
import sistema.modelo.familiar.ModeloFamiliarImpl;
import sistema.utilidades.InvalidFormatException;

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

	protected JPanel panelPpal, panelModificarDatos, panelFamiliar;
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
		this.frame.setBounds(100, 100, 727, 471);
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.frameLayout = new CardLayout();
		this.frame.getContentPane().setLayout(this.frameLayout);

		this.lblCliente = new JLabel();
		this.lblCliente.setFont(new Font("Arial", Font.BOLD, 13));
		this.lblCliente.setHorizontalAlignment(SwingConstants.LEFT);

		this.crearPaneles();

		lblNewLabel = new JLabel("Cliente");
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		lblNewLabel.setBounds(315, 46, 145, 45);
		panelPpal.add(lblNewLabel);

		btnInscribirFamiliar = new JButton("Mi Plan Familiar");
		btnInscribirFamiliar.setBorder(null);
		btnInscribirFamiliar.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnInscribirFamiliar.setForeground(new Color(255, 255, 255));
		btnInscribirFamiliar.setBackground(new Color(119, 193, 181));
		btnInscribirFamiliar.setBounds(290, 191, 174, 23);
		btnInscribirFamiliar.addActionListener(this.listenerFamiliar());
		panelPpal.add(btnInscribirFamiliar);

		JButton btnGenerarCupon = new JButton("Generar Cupon");
		btnGenerarCupon.setForeground(Color.WHITE);
		btnGenerarCupon.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnGenerarCupon.setBorder(null);
		btnGenerarCupon.setBackground(new Color(119, 193, 181));
		btnGenerarCupon.setBounds(290, 291, 174, 23);
		btnGenerarCupon.addActionListener(listenerCupones());
		panelPpal.add(btnGenerarCupon);

		btnObtenerTotalA = new JButton("Obtener Total a Abonar");
		btnObtenerTotalA.setForeground(Color.WHITE);
		btnObtenerTotalA.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		btnObtenerTotalA.setBorder(null);
		btnObtenerTotalA.setBackground(new Color(119, 193, 181));
		btnObtenerTotalA.setBounds(290, 337, 174, 23);
		btnObtenerTotalA.addActionListener(this.listenerAbonar());
		panelPpal.add(btnObtenerTotalA);
		
		JButton btnSolicitudes = new JButton("Mis Solicitudes");
		btnSolicitudes.setForeground(Color.WHITE);
		btnSolicitudes.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnSolicitudes.setBorder(null);
		btnSolicitudes.setBackground(new Color(119, 193, 181));
		btnSolicitudes.setBounds(290, 241, 174, 23);
		panelPpal.add(btnSolicitudes);
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.setForeground(Color.WHITE);
		btnCerrarSesion.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnCerrarSesion.setBorder(null);
		btnCerrarSesion.setBackground(new Color(119, 193, 181));
		btnCerrarSesion.setBounds(584, 11, 119, 23);
		panelPpal.add(btnCerrarSesion);
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.cerrarSesion();
			}
		});

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
		lblNewLabel_1.setBounds(307, 43, 118, 41);
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
		DefaultComboBoxModel dcm = new DefaultComboBoxModel(vector);
		planComboBox = new JComboBox<String>(dcm);
		planComboBox.setBounds(415, 251, 53, 23);
		panelModificarDatos.add(planComboBox);
		
	}

	private ActionListener listenerAbonar() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int plan = controlador.obtenerPlan();
				//int monto = controlador.obtenerTotalAbonar();
				int monto = 0;
				ArrayList<String> nombresFamiliares = controlador.obtenerNombreFamiliares();
				int cant = nombresFamiliares.size();
				String mensaje = "";
				
				if(plan == 1) 
					monto = 5000;
				else if(plan == 2) monto = 2500;
				
				for(int i = 0; i < cant; i++) {
					mensaje += " "+nombresFamiliares.get(i)+ " = $"+monto+"\n";
				}
				mensaje += " Total = "+monto*cant;

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

	private void registrarEventos() {

	}

	private Component crearPaneles() {
		panelPpal = new JPanel();
		panelPpal.setBackground(new Color(224, 241, 238));
		panelPpal.setLayout(null);
		frame.getContentPane().add(panelPpal);

		panelModificarDatos = new JPanel();
		panelModificarDatos.setBackground(new Color(224, 241, 238));
		panelModificarDatos.setLayout(null);
		panelModificarDatos.setVisible(false);
		frame.getContentPane().add(panelModificarDatos);

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
		lblNombre.setBounds(22, 39, 98, 23);
		panelModificarDatos.add(lblNombre);

		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblContrasena.setBounds(22, 147, 98, 23);
		panelModificarDatos.add(lblContrasena);

		btnModificar_1 = new JButton("Modificar");
		btnModificar_1.setBorder(null);
		btnModificar_1.setForeground(new Color(255, 255, 255));
		btnModificar_1.setBackground(new Color(119, 193, 181));
		btnModificar_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnModificar_1.addActionListener(this.modificarDatos());
		btnModificar_1.setBounds(550, 372, 116, 23);
		panelModificarDatos.add(btnModificar_1);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblApellido.setBounds(22, 96, 98, 23);
		panelModificarDatos.add(lblApellido);

		lblDireccion = new JLabel("Direccion:");
		lblDireccion.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblDireccion.setBounds(22, 197, 98, 23);
		panelModificarDatos.add(lblDireccion);

		lblMail = new JLabel("Mail:");
		lblMail.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblMail.setBounds(22, 254, 98, 23);
		panelModificarDatos.add(lblMail);

		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblTelefono.setBounds(360, 39, 98, 23);
		panelModificarDatos.add(lblTelefono);

		lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblFecha.setBounds(360, 82, 98, 23);
		panelModificarDatos.add(lblFecha);

		lblNacimiento = new JLabel("Nacimiento:");
		lblNacimiento.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblNacimiento.setBounds(360, 96, 98, 23);
		panelModificarDatos.add(lblNacimiento);

		lblNombreUsuario = new JLabel("Nombre");
		lblNombreUsuario.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblNombreUsuario.setBounds(360, 130, 98, 23);
		panelModificarDatos.add(lblNombreUsuario);

		lblUsuario = new JLabel("usuario:");
		lblUsuario.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblUsuario.setBounds(360, 147, 98, 23);
		panelModificarDatos.add(lblUsuario);

		lblNroDoc = new JLabel("Documento:");
		lblNroDoc.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblNroDoc.setBounds(360, 197, 98, 23);
		panelModificarDatos.add(lblNroDoc);

		JButton btnModificarDatos = new JButton("Mis Datos");
		btnModificarDatos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btnModificarDatos.setBorder(null);
		btnModificarDatos.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnModificarDatos.setForeground(new Color(255, 255, 255));
		btnModificarDatos.setBackground(new Color(119, 193, 181));
		btnModificarDatos.setBounds(290, 140, 174, 23);
		panelPpal.add(btnModificarDatos);
		btnModificarDatos.addActionListener(this.modificarDatosListener());

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
					panelModificarDatos.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		panelModificarDatos.add(btnVolver2);
		
	
		
		lblPlan = new JLabel("Plan:");
		lblPlan.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPlan.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		lblPlan.setBounds(296, 254, 98, 23);
		panelModificarDatos.add(lblPlan);
		
		JButton btnSolicitudCambio = new JButton("Cambiar plan");
		btnSolicitudCambio.setForeground(Color.WHITE);
		btnSolicitudCambio.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		btnSolicitudCambio.setBorder(null);
		btnSolicitudCambio.setBackground(new Color(32, 178, 170));
		btnSolicitudCambio.setBounds(360, 288, 116, 23);
		btnSolicitudCambio.addActionListener( 
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						String planActual=controlador.obtenerDatosCliente().getPlan();
						if(planActual!=planComboBox.getSelectedItem()) {
							//TODO ARREGLAR PARA QUE CONTROLE QUE SEA DISTINTO AL PLAN ACTUAL, NO ANDA
							int select=JOptionPane.showConfirmDialog(panelModificarDatos,"¿desea enviar una solicitud para cambiarse al plan "+planComboBox.getSelectedItem()+"?","Solicitar cambio de plan" ,JOptionPane.CANCEL_OPTION);
							System.out.println(select);
	
							if(select==JOptionPane.OK_OPTION) {
								if(controlador.solicitarCambioPlan(planComboBox.getSelectedIndex()+1)) {
									JOptionPane.showMessageDialog(panelModificarDatos, "se a enviado su solicitud correctamente. Queda a la espera de que un empleado apruebe el cambio");;
								}
							}
						} else {
							JOptionPane.showMessageDialog(panelModificarDatos, "ya posee el plan seleccionado");
						}
					}
				}
		);
	
				
		panelModificarDatos.add(btnSolicitudCambio);
;
		return panelPpal;
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
				panelFamiliar.setVisible(true);
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

	protected ActionListener modificarDatos() {

		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DatosCliente nuevosDatos = construirNuevosDatos();

				try {
					if (controlador.modificarDatos(nuevosDatos))
						JOptionPane.showMessageDialog(null, "Datos modificados correctamente.");
					else JOptionPane.showMessageDialog(null, 
							"Ya existe otro cliente con alguno de los siguientes datos ingrseados: nombre de usuario, documento, email o telefono", 
							"No se pudo modificar los datos",JOptionPane.ERROR_MESSAGE);
				
				} catch (InvalidFormatException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "No se pudo modificar los datos", JOptionPane.ERROR_MESSAGE);
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
		datos=controlador.obtenerDatosCliente();
		iniciarCamposModificarDatos();
	}
}