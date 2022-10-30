package sistema.vista.empleado;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import sistema.controlador.ControladorEmpleado;
import sistema.vista.registro.VentanaRegistro;
import sistema.vista.registro.VentanaRegistroImpl;

public class VentanaEmpleadoImpl extends JFrame implements VentanaEmpleado {

	private static final long serialVersionUID = 1L;

	protected JFrame frame;
	protected VentanaEmpleadoImpl este = this;
	protected CardLayout frameLayout;
	protected JLabel lblCliente;
	protected ControladorEmpleado controlador;
	protected JPanel panelPpal;
	protected JButton btnAltaCliente;
	protected JButton btnConfirmarPago;
	protected JButton btnModificarDatos;
	protected JButton btnSolicitudModificacionPlan;
	protected JButton btnSolicitudReintegro;

	protected JPanel panelAltaCliente;
	protected JPanel panelPagoCliente;
	protected JPanel panelSolicitudModificacionPlan;
	protected JPanel panelModificarPlan;
	protected JPanel panelSolicitudReintegroPrestacion;
	protected JPanel panelModificarDatos;
	private JButton btnCerrarSesion;

	public VentanaEmpleadoImpl() {
		inicializar();
		this.frame.setVisible(true);
	}

	private void inicializar() {

		this.frame = new JFrame();
		this.frame.setTitle("Empleado");
		this.frame.setBounds(100, 100, 650, 515);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.frameLayout = new CardLayout();
		this.frame.getContentPane().setLayout(this.frameLayout);

		this.lblCliente = new JLabel();
		this.lblCliente.setFont(new Font("Arial", Font.BOLD, 13));
		this.lblCliente.setHorizontalAlignment(SwingConstants.LEFT);

		this.frame.getContentPane().add(this.crearPanelPrincipal());
		
		btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.setForeground(Color.WHITE);
		btnCerrarSesion.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnCerrarSesion.setBorder(null);
		btnCerrarSesion.setBackground(new Color(119, 193, 181));
		btnCerrarSesion.setBounds(489, 11, 137, 23);
		panelPpal.add(btnCerrarSesion);
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.cerrarSesion();
			}
		});

		this.registrarEventos();
	}

	private void registrarEventos() {

	}

	private Component crearPanelPrincipal() {
		panelPpal = new JPanel();
		panelPpal.setBackground(new Color(224, 241, 238));
		panelPpal.setLayout(null);

		JLabel lblNewLabel = new JLabel("Empleado");
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		lblNewLabel.setBounds(247, 44, 165, 40);
		panelPpal.add(lblNewLabel);

		btnAltaCliente = new JButton("Clientes");
		btnAltaCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		btnAltaCliente.setBorder(null);
		btnAltaCliente.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnAltaCliente.setForeground(new Color(255, 255, 255));
		btnAltaCliente.setBackground(new Color(119, 193, 181));
		btnAltaCliente.setBounds(233, 156, 179, 23);
		btnAltaCliente.addActionListener(this.listenerAltaCliente());
		panelPpal.add(btnAltaCliente);

		btnConfirmarPago = new JButton("Confirmar pagos");
		btnConfirmarPago.setBorder(null);
		btnConfirmarPago.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnConfirmarPago.setForeground(new Color(255, 255, 255));
		btnConfirmarPago.setBackground(new Color(119, 193, 181));
		btnConfirmarPago.setBounds(233, 190, 179, 23);
		btnConfirmarPago.addActionListener(this.listenerPagoCliente());
		panelPpal.add(btnConfirmarPago);

		btnSolicitudModificacionPlan = new JButton();
		btnSolicitudModificacionPlan.setText("<html>Confirmar solicitudes<br> de modificacion de plan</html>");
		btnSolicitudModificacionPlan.setBorder(null);
		btnSolicitudModificacionPlan.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		btnSolicitudModificacionPlan.setForeground(new Color(255, 255, 255));
		btnSolicitudModificacionPlan.setBackground(new Color(119, 193, 181));
		btnSolicitudModificacionPlan.setBounds(233, 224, 179, 73);
		btnSolicitudModificacionPlan.addActionListener(this.listenerSolicitudModificacionPlan());
		panelPpal.add(btnSolicitudModificacionPlan);

		btnSolicitudReintegro = new JButton("<html>Solicitudes de<br>reintegro/prestaciones</html>");
		btnSolicitudReintegro.setBorder(null);
		btnSolicitudReintegro.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		btnSolicitudReintegro.setForeground(new Color(255, 255, 255));
		btnSolicitudReintegro.setBackground(new Color(119, 193, 181));
		btnSolicitudReintegro.setBounds(233, 311, 179, 54);
		btnSolicitudReintegro.addActionListener(this.listenerSolicitudReintegro());
		panelPpal.add(btnSolicitudReintegro);

		btnModificarDatos = new JButton("Mis Datos");
		btnModificarDatos.setBorder(null);
		btnModificarDatos.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnModificarDatos.setForeground(new Color(255, 255, 255));
		btnModificarDatos.setBackground(new Color(119, 193, 181));
		btnModificarDatos.setBounds(233, 122, 179, 23);
		btnModificarDatos.addActionListener(this.listenerModificarDatos());
		panelPpal.add(btnModificarDatos);

		panelPpal.setVisible(true);
		return panelPpal;
	}

	protected ActionListener listenerAltaCliente() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaRegistro registrarse = new VentanaRegistroImpl(este);
				try {
					registrarse.eliminarVolver();
					panelPpal.setVisible(false);
					panelAltaCliente = registrarse.getPanelRegistro();				
					frame.getContentPane().add(panelAltaCliente);
					panelAltaCliente.setVisible(true);
					registrarse.mostrarVentana(false);
					
					JButton btnVolver3 = new JButton("");
					btnVolver3.setBounds(10, 11, 35, 31);
					btnVolver3.setIcon(new ImageIcon("img\\flechi.png"));
					btnVolver3.setBorder(new EmptyBorder(0, 0, 0, 0));
					btnVolver3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								panelAltaCliente.setVisible(false);
								panelPpal.setVisible(true);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					});
					
					panelAltaCliente.add(btnVolver3);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}	
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
				panelSolicitudModificacionPlan = new PanelSolicitudModificarPlan(controlador);				
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
				panelSolicitudReintegroPrestacion = new PanelSolicitudReintegroPrestacion(controlador);
				frame.getContentPane().add(panelSolicitudReintegroPrestacion);
				panelSolicitudReintegroPrestacion.setVisible(true);
				
				JButton btnVolver3 = new JButton("");
				btnVolver3.setBounds(10, 11, 35, 31);
				btnVolver3.setIcon(new ImageIcon("img\\flechi.png"));
				btnVolver3.setBorder(new EmptyBorder(0, 0, 0, 0));
				btnVolver3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							panelPpal.setVisible(true);
							panelSolicitudReintegroPrestacion.setVisible(false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				panelSolicitudReintegroPrestacion.add(btnVolver3);
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
