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
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(36, 35, 98, 23);
		panelPpal2.add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(101, 36, 167, 20);
		panelPpal2.add(textField);
		textField.setColumns(10);
		
		JLabel lblApellido = new JLabel("Contraseña:");
		lblApellido.setBounds(36, 75, 98, 23);
		panelPpal2.add(lblApellido);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(101, 76, 167, 20);
		panelPpal2.add(textField_1);
		
		btnModificar_1 = new JButton("Modificar");
		btnModificar_1.addActionListener(this.modificarDatos());
		btnModificar_1.setBounds(101, 126, 116, 23);
		panelPpal2.add(btnModificar_1);
		
		JButton btnNewButton = new JButton("Modificar Datos");
		btnNewButton.setBounds(320, 76, 174, 23);
		panelPpal.add(btnNewButton);
		btnNewButton.addActionListener(this.nuevoListener());
		
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