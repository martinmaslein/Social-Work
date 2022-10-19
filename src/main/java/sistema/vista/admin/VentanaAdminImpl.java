package sistema.vista.admin;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
import javax.swing.border.EmptyBorder;

import sistema.controlador.ControladorAdmin;
import sistema.modelo.admin.ModeloAdminImpl;
import sistema.utilidades.Pair;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import javax.swing.JScrollPane;

public class VentanaAdminImpl extends JFrame implements VentanaAdmin {

	private static final long serialVersionUID = 1L;

	protected JFrame frame;
	protected CardLayout frameLayout;
	protected JLabel lblCliente;
	protected JMenuItem mntmCerrarSesion;
	protected JMenuItem mntmSalir;
	protected ControladorAdmin controlador;
	private JTextField txtPlanes;
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JTextField txtAcciones;
	private JTable table;
	private JTable tablaPrestaciones;
	protected JPanel panelPpal, panelAdministrarPlanes;
	private JButton btnNewButton_1;
	private JPanel panelNuevoPlan;
	private JTextField txtNuevoPlan;
	private JTextField txtNombrePlan;
	private JTextField txtPrecioPorPersona;
	private JTextField txtPrestaciones;
	private JTextField textFieldNombre;
	private JTextField textFieldPrecio;
	private JTextField textFieldPrestaciones;
	private JButton btnNewButton_2;
	private JPanel panelModificarPlan;
	private JTextField txtModificarPlan;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JButton btnConfirmar;
	private JButton btnModificarPlan;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	private ModeloAdminImpl modeloAdmin;
	private JTable table_1;

	public VentanaAdminImpl() {
		inicializar();
		this.frame.setVisible(true);
	}

	private void inicializar() {

		modeloAdmin = new ModeloAdminImpl();
		
		this.frame = new JFrame();
		this.frame.setTitle("Administrador");
		this.frame.setBounds(100, 100, 852, 575);
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.frameLayout = new CardLayout();
		this.frame.getContentPane().setLayout(this.frameLayout);

		this.lblCliente = new JLabel();
		this.lblCliente.setFont(new Font("Arial", Font.BOLD, 13));
		this.lblCliente.setHorizontalAlignment(SwingConstants.LEFT);

		this.frame.setJMenuBar(this.crearMenuOpciones());

		this.frame.getContentPane().add(this.crearPanelVacio());
		
					
		panelAdministrarPlanes = new JPanel();
		panelAdministrarPlanes.setBackground(new Color(224, 241, 238));
		frame.getContentPane().add(panelAdministrarPlanes, "name_63421316270500");
		panelAdministrarPlanes.setLayout(null);
		
		txtPlanes = new JTextField();
		txtPlanes.setBorder(null);
		txtPlanes.setFocusable(false);
		txtPlanes.setEditable(false);
		txtPlanes.setAutoscrolls(false);
		txtPlanes.setBackground(new Color(173, 218, 209));
		txtPlanes.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
		txtPlanes.setText("Planes");
		txtPlanes.setBounds(47, 25, 128, 60);
		panelAdministrarPlanes.add(txtPlanes);
		txtPlanes.setColumns(10);
		
		JButton btnVolver = new JButton("");
		btnVolver.setBounds(10, 11, 35, 31);
		btnVolver.setIcon(new ImageIcon("img\\flechi.png"));
		btnVolver.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					panelPpal.setVisible(true);
					panelAdministrarPlanes.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		panelAdministrarPlanes.add(btnVolver);
		
		JButton btnAgregarPlan = new JButton("Agregar nuevo Plan");
		btnAgregarPlan.setBackground(new Color(119, 193, 181));
		btnAgregarPlan.setForeground(new Color(255, 255, 255));
		btnAgregarPlan.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		btnAgregarPlan.setBounds(516, 42, 214, 33);
		panelAdministrarPlanes.add(btnAgregarPlan);
		btnAgregarPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					panelAdministrarPlanes.setVisible(false);
					panelNuevoPlan.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		txtNombre = new JTextField();
		txtNombre.setBorder(null);
		txtNombre.setBackground(new Color(224, 241, 238));
		txtNombre.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		txtNombre.setText("Nombre");
		txtNombre.setBounds(144, 150, 115, 33);
		panelAdministrarPlanes.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setBorder(null);
		txtPrecio.setText("Precio");
		txtPrecio.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		txtPrecio.setColumns(10);
		txtPrecio.setBackground(new Color(224, 241, 238));
		txtPrecio.setBounds(364, 150, 115, 33);
		panelAdministrarPlanes.add(txtPrecio);
		
		txtAcciones = new JTextField();
		txtAcciones.setBorder(null);
		txtAcciones.setText("Acciones");
		txtAcciones.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		txtAcciones.setColumns(10);
		txtAcciones.setBackground(new Color(224, 241, 238));
		txtAcciones.setBounds(584, 150, 115, 33);
		panelAdministrarPlanes.add(txtAcciones);
		
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(224, 241, 238));
		scrollPane.setMinimumSize(new Dimension(27, 27));
		scrollPane.setBounds(102, 210, 421, 219);
		panelAdministrarPlanes.add(scrollPane);
		
		List<Pair<String,Integer>> planes = modeloAdmin.obtenerPlanes();
		
		String columna [] = {"Nombre","Reintegro"};
		
		String data[][] = {{"",""},{"",""},{"",""},{"",""},{"",""}};
		
		int i=0;
		for(Pair<String,Integer> plan : planes) {
			
			String nombre = plan.getNombre();
	    	int reintegro = plan.getReintegro();
	    	String reintegro2 = reintegro+"";
	    	
			data[i][0] = nombre;
			data[i][1] = reintegro2;
			
			i = i+1;
		}
				
		DefaultTableModel tableModel = new DefaultTableModel(data,columna);
	    JTable table = new JTable(tableModel);
	    table.setRowHeight(33);
	    scrollPane.setViewportView(table);
		
		btnModificarPlan = new JButton("Modificar Plan");
		btnModificarPlan.setForeground(Color.WHITE);
		btnModificarPlan.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		btnModificarPlan.setBackground(new Color(119, 193, 181));
		btnModificarPlan.setBounds(264, 42, 214, 33);
		panelAdministrarPlanes.add(btnModificarPlan);
		btnModificarPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				try {
					panelAdministrarPlanes.setVisible(false);
					panelModificarPlan.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setBackground(new Color(173, 218, 209));
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setFocusTraversalKeysEnabled(false);
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBounds(0, 0, 838, 114);
		panelAdministrarPlanes.add(btnNewButton_1);
		
		btnEditar = new JButton("Editar");
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		btnEditar.setBorder(null);
		btnEditar.setBackground(new Color(119, 193, 181));
		btnEditar.setBounds(568, 210, 119, 37);
		panelAdministrarPlanes.add(btnEditar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(new Color(119, 193, 181));
		btnEliminar.setBounds(568, 274, 119, 37);
		panelAdministrarPlanes.add(btnEliminar);
		
		panelNuevoPlan = new JPanel();
		panelNuevoPlan.setBackground(new Color(224, 241, 238));
		frame.getContentPane().add(panelNuevoPlan, "name_65742471087900");
		panelNuevoPlan.setLayout(null);
		
		JButton btnVolver2 = new JButton("");
		btnVolver2.setBounds(10, 11, 35, 31);
		btnVolver2.setIcon(new ImageIcon("img\\flechi.png"));
		btnVolver2.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnVolver2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					panelAdministrarPlanes.setVisible(true);
					panelNuevoPlan.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		panelNuevoPlan.add(btnVolver2);
		
		txtNuevoPlan = new JTextField();
		txtNuevoPlan.setBackground(new Color(224, 241, 238));
		txtNuevoPlan.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		txtNuevoPlan.setText("Nuevo Plan");
		txtNuevoPlan.setBorder(null);
		txtNuevoPlan.setBounds(416, 29, 173, 43);
		panelNuevoPlan.add(txtNuevoPlan);
		txtNuevoPlan.setColumns(10);
		
		txtNombrePlan = new JTextField();
		txtNombrePlan.setText("Nombre Plan*");
		txtNombrePlan.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		txtNombrePlan.setColumns(10);
		txtNombrePlan.setBorder(null);
		txtNombrePlan.setBackground(new Color(224, 241, 238));
		txtNombrePlan.setBounds(125, 137, 173, 43);
		panelNuevoPlan.add(txtNombrePlan);
		
		txtPrecioPorPersona = new JTextField();
		txtPrecioPorPersona.setText("Precio por persona *");
		txtPrecioPorPersona.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		txtPrecioPorPersona.setColumns(10);
		txtPrecioPorPersona.setBorder(null);
		txtPrecioPorPersona.setBackground(new Color(224, 241, 238));
		txtPrecioPorPersona.setBounds(125, 213, 198, 43);
		panelNuevoPlan.add(txtPrecioPorPersona);
		
		txtPrestaciones = new JTextField();
		txtPrestaciones.setText("Prestaciones*");
		txtPrestaciones.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		txtPrestaciones.setColumns(10);
		txtPrestaciones.setBorder(null);
		txtPrestaciones.setBackground(new Color(224, 241, 238));
		txtPrestaciones.setBounds(125, 286, 173, 43);
		panelNuevoPlan.add(txtPrestaciones);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBorder(null);
		textFieldNombre.setBounds(419, 145, 214, 37);
		panelNuevoPlan.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setBorder(null);
		textFieldPrecio.setColumns(10);
		textFieldPrecio.setBounds(419, 221, 214, 37);
		panelNuevoPlan.add(textFieldPrecio);
		
		btnNewButton_2 = new JButton("Confirmar");
		btnNewButton_2.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setBackground(new Color(119, 193, 181));
		btnNewButton_2.setBounds(470, 426, 119, 37);
		btnNewButton_2.addActionListener(this.crearPlan());
		panelNuevoPlan.add(btnNewButton_2);
		
		panelModificarPlan = new JPanel();
		panelModificarPlan.setBackground(new Color(224, 241, 238));
		frame.getContentPane().add(panelModificarPlan, "name_66965023197000");
		panelModificarPlan.setLayout(null);
		
		JButton btnVolver3 = new JButton("");
		btnVolver3.setBounds(10, 11, 35, 31);
		btnVolver3.setIcon(new ImageIcon("img\\flechi.png"));
		btnVolver3.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnVolver3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					panelAdministrarPlanes.setVisible(true);
					panelModificarPlan.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		panelModificarPlan.add(btnVolver3);
		
		txtModificarPlan = new JTextField();
		txtModificarPlan.setText("Modificar Plan");
		txtModificarPlan.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		txtModificarPlan.setColumns(10);
		txtModificarPlan.setBorder(null);
		txtModificarPlan.setBackground(new Color(224, 241, 238));
		txtModificarPlan.setBounds(402, 27, 208, 43);
		panelModificarPlan.add(txtModificarPlan);
		
		textField_1 = new JTextField();
		textField_1.setText("Nombre Plan*");
		textField_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		textField_1.setColumns(10);
		textField_1.setBorder(null);
		textField_1.setBackground(new Color(224, 241, 238));
		textField_1.setBounds(108, 119, 173, 43);
		panelModificarPlan.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBorder(null);
		textField_2.setBounds(402, 127, 214, 37);
		panelModificarPlan.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBorder(null);
		textField_3.setBounds(402, 203, 214, 37);
		panelModificarPlan.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setText("Precio por persona *");
		textField_4.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		textField_4.setColumns(10);
		textField_4.setBorder(null);
		textField_4.setBackground(new Color(224, 241, 238));
		textField_4.setBounds(108, 195, 198, 43);
		panelModificarPlan.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setText("Prestaciones*");
		textField_5.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		textField_5.setColumns(10);
		textField_5.setBorder(null);
		textField_5.setBackground(new Color(224, 241, 238));
		textField_5.setBounds(108, 268, 173, 43);
		panelModificarPlan.add(textField_5);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		btnConfirmar.setBorder(null);
		btnConfirmar.setBackground(new Color(119, 193, 181));
		btnConfirmar.setBounds(453, 408, 119, 37);
		panelModificarPlan.add(btnConfirmar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setMinimumSize(new Dimension(27, 27));
		scrollPane_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		scrollPane_1.setBorder(null);
		scrollPane_1.setBackground(new Color(224, 241, 238));
		scrollPane_1.setBounds(351, 251, 323, 124);
		panelModificarPlan.add(scrollPane_1);
		
		table_1 = new JTable(new DefaultTableModel(
			new Object[][] {
				{"asd"},
				{"asd"},
				{"asd"},
				{"asd"},
				{"asd"},
				{"asd"},
				
			},
			new String[] {
				"Prestaciones"
			}
		));
		table_1.setRowHeight(33);
		scrollPane_1.setViewportView(table_1);

		this.registrarEventos();
	}

	private void setClientesBase(JTable table_12) {
		table_12.setValueAt("Jorge Lopez",0,0);
		table_12.setValueAt("5000",0,1);
		
		table_12.setValueAt("Guillermo Perez",1,0);
		table_12.setValueAt("2500",1,1);
		
		 
	}

	private void setClientes(JTable table_12) {
		  //TODO Añadir dinamicamente los clientes que generen un cupon?
		
	}

	private ActionListener crearPlan() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					//boolean crearPlan = modeloAdmin.cargarPlan(textFieldNombre.getText(), textFieldPrecio.getText(), textFieldPrestaciones.getText());
					boolean crearPlan = controlador.cargarPlan(textFieldNombre.getText(), textFieldPrecio.getText(), textFieldPrestaciones.getText());
					if(crearPlan == false)
						JOptionPane.showMessageDialog(null, "No pueden haber campos vacíos o nombre existente");
					else
						JOptionPane.showMessageDialog(null, "Plan creado exitosamente");
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

	private Component crearPanelVacio() {
		panelPpal = new JPanel();
		panelPpal.setBackground(new Color(224, 241, 238));
		panelPpal.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Administrador");
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		lblNewLabel.setBounds(305, 56, 211, 44);
		panelPpal.add(lblNewLabel);
		
		JButton btnAdministrarPlanes = new JButton("Administrar Planes");
		btnAdministrarPlanes.setBackground(new Color(119, 193, 181));
		btnAdministrarPlanes.setForeground(new Color(255, 255, 255));
		btnAdministrarPlanes.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		btnAdministrarPlanes.setBounds(305, 149, 202, 44);
		panelPpal.add(btnAdministrarPlanes);
		
		btnAdministrarPlanes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					panelPpal.setVisible(false);
					panelAdministrarPlanes.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnAdministrarEmpleados = new JButton("Administrar Empleados");
		btnAdministrarEmpleados.setForeground(new Color(255, 255, 255));
		btnAdministrarEmpleados.setBackground(new Color(119, 193, 181));
		btnAdministrarEmpleados.setFont(new Font("Yu Gothic UI", Font.BOLD, 13));
		btnAdministrarEmpleados.setBounds(305, 232, 202, 44);
		panelPpal.add(btnAdministrarEmpleados);
		panelPpal.setVisible(false);

		return panelPpal;
	}

	private JMenuBar crearMenuOpciones() {
		JMenuBar barraDeMenu = new JMenuBar();
		JMenu menuOpciones = new JMenu("Menu");
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

	public void registrarControlador(ControladorAdmin controlador) {
		this.controlador = controlador;
	}
}
