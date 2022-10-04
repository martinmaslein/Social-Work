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
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(372, 23, 89, 23);
		panelPpal.add(lblNewLabel);
		
		btnInscribirFamiliar = new JButton("Inscribir Familiar");
		btnInscribirFamiliar.setBounds(320, 127, 174, 23);
		btnInscribirFamiliar.addActionListener(this.listenerFamiliar());
		panelPpal.add(btnInscribirFamiliar);
		
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
		panelPpal.setLayout(null);
		frame.getContentPane().add(panelPpal);
		
		panelPpal2 = new JPanel();
		panelPpal2.setLayout(null);
		panelPpal2.setVisible(false);
		frame.getContentPane().add(panelPpal2);
		
		panelFamiliar = new JPanel();
		panelFamiliar.setLayout(null);
		panelFamiliar.setVisible(false);
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
		labelNombre.setBounds(137, 105, 46, 14);
		panelFamiliar.add(labelNombre);
		
		JLabel labelApellido = new JLabel("Apellido");
		labelApellido.setBounds(137, 149, 46, 14);
		panelFamiliar.add(labelApellido);
		
		JLabel labelFecha = new JLabel("Fecha nac");
		labelFecha.setBounds(137, 193, 69, 14);
		panelFamiliar.add(labelFecha);
		
		JLabel labelDireccion = new JLabel("Direccion");
		labelDireccion.setBounds(137, 239, 46, 14);
		panelFamiliar.add(labelDireccion);
		
		JLabel labelTelefono = new JLabel("Telefono");
		labelTelefono.setBounds(137, 284, 46, 14);
		panelFamiliar.add(labelTelefono);
		
		JButton btnCargarFamiliar = new JButton("CargarFamiliar");
		btnCargarFamiliar.setBounds(236, 340, 131, 30);
		panelFamiliar.add(btnCargarFamiliar);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(119, 35, 98, 23);
		panelPpal2.add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(243, 36, 167, 20);
		panelPpal2.add(textField);
		textField.setColumns(10);
		
		JLabel lblApellido = new JLabel("Contraseña:");
		lblApellido.setBounds(119, 75, 98, 23);
		panelPpal2.add(lblApellido);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(243, 76, 167, 20);
		panelPpal2.add(textField_1);
		
		btnModificar_1 = new JButton("Modificar");
		btnModificar_1.addActionListener(this.modificarDatos());
		btnModificar_1.setBounds(271, 129, 116, 23);
		panelPpal2.add(btnModificar_1);
		
		JButton modificarDatos = new JButton("Modificar Datos");
		modificarDatos.setBounds(320, 76, 174, 23);
		panelPpal.add(modificarDatos);
		modificarDatos.addActionListener(this.nuevoListener());
		
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
		
		
		/**/
		JButton btnVolver2 = new JButton("");
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
		btnVolver2.setBounds(10, 11, 35, 31);
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