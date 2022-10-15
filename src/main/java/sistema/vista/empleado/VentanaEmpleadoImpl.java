package sistema.vista.empleado;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
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
import javax.swing.border.EmptyBorder;

import sistema.controlador.ControladorEmpleado;

public class VentanaEmpleadoImpl extends JFrame implements VentanaEmpleado {

	private static final long serialVersionUID = 1L;

	protected JFrame frame;
	protected CardLayout frameLayout;
	protected JLabel lblCliente;
	protected JMenuItem mntmCerrarSesion;
	protected JMenuItem mntmSalir;
	protected ControladorEmpleado controlador;
	protected JPanel panelPpal;
	protected JButton btnAltaCliente;
	protected JButton btnConfirmarPago;
	protected JButton btnModificarDatos;
	protected JButton btnSolicitudModificacionPlan;
	protected JButton btnModificarPlanCliente;
	protected JButton btnSolicitudReintegro;

	protected JPanel panelAltaCliente;
	protected JPanel panelPagoCliente;
	protected JPanel panelSolicitudModificacionPlan;
	protected JPanel panelModificarPlan;
	protected JPanel panelSolicitudReintegro;
	protected JPanel panelModificarDatos;

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
		panelPpal = new JPanel();
		panelPpal.setBackground(new Color(224, 241, 238));
		panelPpal.setLayout(null);

		JLabel lblNewLabel = new JLabel("Empleado");
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
		lblNewLabel.setBounds(351, 50, 134, 40);
		panelPpal.add(lblNewLabel);

		btnAltaCliente = new JButton("Dar de alta cliente");
		btnAltaCliente.setBorder(null);
		btnAltaCliente.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnAltaCliente.setForeground(new Color(255, 255, 255));
		btnAltaCliente.setBackground(new Color(119, 193, 181));
		btnAltaCliente.setBounds(320, 129, 174, 23);
		btnAltaCliente.addActionListener(this.listenerAltaCliente());
		panelPpal.add(btnAltaCliente);

		btnConfirmarPago = new JButton("Confirmar pago cliente");
		btnConfirmarPago.setBorder(null);
		btnConfirmarPago.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnConfirmarPago.setForeground(new Color(255, 255, 255));
		btnConfirmarPago.setBackground(new Color(119, 193, 181));
		btnConfirmarPago.setBounds(320, 163, 174, 23);
		btnConfirmarPago.addActionListener(this.listenerPagoCliente());
		panelPpal.add(btnConfirmarPago);

		btnSolicitudModificacionPlan = new JButton();
		btnSolicitudModificacionPlan.setText("<html>" + "" + "Confirmar solicitud<br>de modificacion de plan</html>");
		btnSolicitudModificacionPlan.setBorder(null);
		btnSolicitudModificacionPlan.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnSolicitudModificacionPlan.setForeground(new Color(255, 255, 255));
		btnSolicitudModificacionPlan.setBackground(new Color(119, 193, 181));
		btnSolicitudModificacionPlan.setBounds(320, 231, 174, 40);
		btnSolicitudModificacionPlan.addActionListener(this.listenerSolicitudModificacionPlan());
		panelPpal.add(btnSolicitudModificacionPlan);

		btnModificarPlanCliente = new JButton("Modificar plan cliente");
		btnModificarPlanCliente.setBorder(null);
		btnModificarPlanCliente.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnModificarPlanCliente.setForeground(new Color(255, 255, 255));
		btnModificarPlanCliente.setBackground(new Color(119, 193, 181));
		btnModificarPlanCliente.setBounds(320, 197, 174, 23);
		btnModificarPlanCliente.addActionListener(this.listenermodificarPlanCliente());
		panelPpal.add(btnModificarPlanCliente);

		btnSolicitudReintegro = new JButton("Solicitudes de reintegro");
		btnSolicitudReintegro.setBorder(null);
		btnSolicitudReintegro.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnSolicitudReintegro.setForeground(new Color(255, 255, 255));
		btnSolicitudReintegro.setBackground(new Color(119, 193, 181));
		btnSolicitudReintegro.setBounds(320, 282, 174, 23);
		btnSolicitudReintegro.addActionListener(this.listenerSolicitudReintegro());
		panelPpal.add(btnSolicitudReintegro);

		btnModificarDatos = new JButton("Modificar datos");
		btnModificarDatos.setBorder(null);
		btnModificarDatos.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnModificarDatos.setForeground(new Color(255, 255, 255));
		btnModificarDatos.setBackground(new Color(119, 193, 181));
		btnModificarDatos.setBounds(320, 316, 174, 23);
		btnModificarDatos.addActionListener(this.listenerModificarDatos());
		panelPpal.add(btnModificarDatos);

		panelPpal.setVisible(true);
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

	protected ActionListener listenerAltaCliente() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPpal.setVisible(false);
				panelAltaCliente = new PanelAltaCliente();				
				frame.getContentPane().add(panelAltaCliente);
				panelAltaCliente.setVisible(true);

				JButton btnVolver3 = new JButton("");
				btnVolver3.setBounds(10, 11, 35, 31);
				btnVolver3.setIcon(new ImageIcon("img\\flechi.png"));
				btnVolver3.setBorder(new EmptyBorder(0, 0, 0, 0));
				btnVolver3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							panelPpal.setVisible(true);
							
							panelAltaCliente.setVisible(false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				panelAltaCliente.add(btnVolver3);
			}
		};
	}

	protected ActionListener listenerPagoCliente() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPpal.setVisible(false);
				panelPagoCliente = new PanelPagoCliente(controlador);
				frame.getContentPane().add(panelPagoCliente);
				panelPagoCliente.setVisible(true);
				JButton btnVolver3 = new JButton("");
				btnVolver3.setBounds(10, 11, 35, 31);
				btnVolver3.setIcon(new ImageIcon("img\\flechi.png"));
				btnVolver3.setBorder(new EmptyBorder(0, 0, 0, 0));
				btnVolver3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							panelPpal.setVisible(true);
							
							panelPagoCliente.setVisible(false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				panelPagoCliente.add(btnVolver3);

			}
		};
	}

	protected ActionListener listenerSolicitudModificacionPlan() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPpal.setVisible(false);
				panelSolicitudModificacionPlan = new PanelSolicitudModificarPlan();				
				frame.getContentPane().add(panelSolicitudModificacionPlan);
				panelSolicitudModificacionPlan.setVisible(true);

				JButton btnVolver3 = new JButton("");
				btnVolver3.setBounds(10, 11, 35, 31);
				btnVolver3.setIcon(new ImageIcon("img\\flechi.png"));
				btnVolver3.setBorder(new EmptyBorder(0, 0, 0, 0));
				btnVolver3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							panelPpal.setVisible(true);
							panelSolicitudModificacionPlan.setVisible(false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				panelSolicitudModificacionPlan.add(btnVolver3);
			}
		};
	}

	protected ActionListener listenermodificarPlanCliente() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPpal.setVisible(false);
				panelModificarPlan = new PanelModificarPlan(controlador);				
				frame.getContentPane().add(panelModificarPlan);
				panelModificarPlan.setVisible(true);
				
				JButton btnVolver3 = new JButton("");
				btnVolver3.setBounds(10, 11, 35, 31);
				btnVolver3.setIcon(new ImageIcon("img\\flechi.png"));
				btnVolver3.setBorder(new EmptyBorder(0, 0, 0, 0));
				btnVolver3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							panelPpal.setVisible(true);
							panelModificarPlan.setVisible(false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				panelModificarPlan.add(btnVolver3);
			}
		};
	}

	protected ActionListener listenerSolicitudReintegro() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPpal.setVisible(false);
				panelSolicitudReintegro = new JPanel();
				panelSolicitudReintegro.setBackground(new Color(224, 241, 238));
				panelSolicitudReintegro.setLayout(null);
				panelSolicitudReintegro.setVisible(false);
				frame.getContentPane().add(panelSolicitudReintegro);
				panelSolicitudReintegro.setVisible(true);

				JButton btnVolver3 = new JButton("");
				btnVolver3.setBounds(10, 11, 35, 31);
				btnVolver3.setIcon(new ImageIcon("img\\flechi.png"));
				btnVolver3.setBorder(new EmptyBorder(0, 0, 0, 0));
				btnVolver3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							panelPpal.setVisible(true);
							panelSolicitudReintegro.setVisible(false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				panelSolicitudReintegro.add(btnVolver3);
			}
		};
	}

	protected ActionListener listenerModificarDatos() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPpal.setVisible(false);
				panelModificarDatos = new JPanel();
				panelModificarDatos.setBackground(new Color(224, 241, 238));
				panelModificarDatos.setLayout(null);
				panelModificarDatos.setVisible(false);
				frame.getContentPane().add(panelModificarDatos);
				panelModificarDatos.setVisible(true);

				JButton btnVolver3 = new JButton("");
				btnVolver3.setBounds(10, 11, 35, 31);
				btnVolver3.setIcon(new ImageIcon("img\\flechi.png"));
				btnVolver3.setBorder(new EmptyBorder(0, 0, 0, 0));
				btnVolver3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							panelPpal.setVisible(true);
							panelModificarDatos.setVisible(false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				panelModificarDatos.add(btnVolver3);
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

	public void registrarControlador(ControladorEmpleado controlador) {
		this.controlador = controlador;
	}

}
