package sistema.vista.empleado;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import sistema.controlador.ControladorEmpleado;

public class VentanaEmpleadoImpl extends JFrame implements VentanaEmpleado {

	private static final long serialVersionUID = 1L;
	
	protected JFrame frame;
	protected CardLayout frameLayout;
	protected JLabel lblCliente;
	protected JMenuItem mntmCerrarSesion;
	protected JMenuItem mntmSalir;
	protected ControladorEmpleado controlador;
	
	public VentanaEmpleadoImpl() {		
		inicializar();
		this.frame.setVisible(true);
	}


	private void inicializar() {
		
		this.frame = new JFrame();
		this.frame.setTitle("Empleado");
		this.frame.setBounds(100, 100, 852, 575);
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.frameLayout = new CardLayout();
		this.frame.getContentPane().setLayout(this.frameLayout);

		this.lblCliente = new JLabel();
		this.lblCliente.setFont(new Font("Arial", Font.BOLD, 13));
		this.lblCliente.setHorizontalAlignment(SwingConstants.LEFT);
		
		this.frame.setJMenuBar(this.crearMenuOpciones());

		this.frame.getContentPane().add(this.crearPanelPrincipal());
		
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
	
	private Component crearPanelPrincipal() {
		JPanel panelPpal = new JPanel();
		panelPpal.setLayout(null);
		panelPpal.setVisible(true);		
		JButton btnInscribirFamiliar = new JButton("Inscribir Familiar");
		btnInscribirFamiliar.setBorder(null);
		btnInscribirFamiliar.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnInscribirFamiliar.setForeground(new Color(255, 255, 255));
		btnInscribirFamiliar.setBackground(new Color(119, 193, 181));
		btnInscribirFamiliar.setBounds(320, 127, 174, 23);
		//btnInscribirFamiliar.addActionListener(this.listenerFamiliar());
		panelPpal.add(btnInscribirFamiliar);
		return panelPpal;			
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


	public void registrarControlador(ControladorEmpleado controlador) {
		this.controlador = controlador;
	}


}
