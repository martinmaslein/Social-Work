package sistema.vista.empleado;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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
	
	protected JPanel panelABMCliente;
	protected JPanel panelAltaCliente;
	protected JPanel panelPagoCliente;
	protected JPanel panelSolicitudModificacionPlan;
	protected JPanel panelModificarPlan;
	protected JPanel panelSolicitudReintegroPrestacion;
	protected JPanel panelModificarDatos;
	private JButton btnCerrarSesion;
	
	private ArrayList<String> clienteSeleccionado;
	private JLabel lblAcciones_1;
	private JButton btnVerMas_1;
	private JButton btnEliminar;
	private Component btnModificar;
	private JScrollPane scrollPane;
	private JButton btnAgregarCliente;

	public VentanaEmpleadoImpl() {
		inicializar();
		this.frame.setVisible(true);
	}

	private void inicializar() {

		this.frame = new JFrame();
		this.frame.setTitle("Empleado");
		this.frame.setBounds(100, 100, 852, 575);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.frameLayout = new CardLayout();
		this.frame.getContentPane().setLayout(this.frameLayout);

		this.lblCliente = new JLabel();
		this.lblCliente.setFont(new Font("Arial", Font.BOLD, 13));
		this.lblCliente.setHorizontalAlignment(SwingConstants.LEFT);

		this.frame.getContentPane().add(this.crearPanelPrincipal());
		
		//Creo el panel abm
		this.crearPanelABM();

		
		btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.setForeground(Color.WHITE);
		btnCerrarSesion.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnCerrarSesion.setBorder(null);
		btnCerrarSesion.setBackground(new Color(119, 193, 181));
		btnCerrarSesion.setBounds(569, 11, 137, 23);
		panelPpal.add(btnCerrarSesion);
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.cerrarSesion();
			}
		});

		this.registrarEventos();
	}

	private Component crearPanelABM() {
		panelABMCliente = new JPanel();
		panelABMCliente.setBackground(new Color(224, 241, 238));
		panelABMCliente.setLayout(null);
		frame.getContentPane().add(panelABMCliente);
		
		// Labels y Botones
		JLabel lblFamiliares = new JLabel("Clientes");
		lblFamiliares.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
		lblFamiliares.setBounds(80, 66, 129, 23);
		panelABMCliente.add(lblFamiliares);

		lblAcciones_1 = new JLabel("Acciones");
		lblAcciones_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
		lblAcciones_1.setBounds(618, 150, 129, 23);
		panelABMCliente.add(lblAcciones_1);

		btnVerMas_1 = new JButton("Ver Mas");
		btnVerMas_1.setBounds(618, 209, 129, 34);
		btnVerMas_1.addActionListener(this.verMas());
		panelABMCliente.add(btnVerMas_1);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(618, 278, 129, 34);
	//	btnEliminar.addActionListener(this.eliminarFamiliar());
		panelABMCliente.add(btnEliminar);

		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(618, 345, 129, 34);
	//	btnModificar.addActionListener(this.modifcarFamiliar());
		panelABMCliente.add(btnModificar);
		
		btnAgregarCliente = new JButton("Agregar Cliente");
		btnAgregarCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAgregarCliente.setBounds(604, 57, 155, 48);
	//	btnAgregarCliente.addActionListener(this.cargarFamiliar());
		panelABMCliente.add(btnAgregarCliente);

		// ABM
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(224, 241, 238));
		scrollPane.setMinimumSize(new Dimension(27, 27));
		scrollPane.setBounds(33, 188, 488, 219);
		panelABMCliente.add(scrollPane);
		
		JButton btnVolver = new JButton("");
		btnVolver.setIcon(new ImageIcon("img\\flechi.png"));
		btnVolver.setBorder(null);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					panelPpal.setVisible(true);
					panelABMCliente.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnVolver.setBounds(10, 11, 35, 31);
		panelABMCliente.add(btnVolver);
		
		return panelABMCliente;
		
	}

	private ActionListener verMas() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensaje = "";
				if (clienteSeleccionado != null)
					mensaje += " Nombre: " + clienteSeleccionado.get(0) + " " + clienteSeleccionado.get(1) + "\n"
							+ " DNI: " + clienteSeleccionado.get(2) + "\n" + " Plan: " + clienteSeleccionado.get(3)
							+ "\n" + " Fecha_nac: " + clienteSeleccionado.get(4) + "\n" + " Direccion: "
							+ clienteSeleccionado.get(5) + "\n" + " Telefono: " + clienteSeleccionado.get(6)
							+ "\n" +" Email: "+clienteSeleccionado.get(7);
				else
					mensaje += "Seleccione el nombre de un familiar";
				JOptionPane.showMessageDialog(null, mensaje);
			}
		};
	}

	private void registrarEventos() {

	}

	private Component crearPanelPrincipal() {
		panelPpal = new JPanel();
		panelPpal.setBackground(new Color(224, 241, 238));
		panelPpal.setLayout(null);

		JLabel lblNewLabel = new JLabel("Empleado");
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		lblNewLabel.setBounds(327, 44, 165, 40);
		panelPpal.add(lblNewLabel);

		btnAltaCliente = new JButton("Clientes");
		btnAltaCliente.setBorder(null);
		btnAltaCliente.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnAltaCliente.setForeground(new Color(255, 255, 255));
		btnAltaCliente.setBackground(new Color(119, 193, 181));
		btnAltaCliente.setBounds(313, 156, 179, 23);
		btnAltaCliente.addActionListener(this.verABMCliente());
		panelPpal.add(btnAltaCliente);

		btnConfirmarPago = new JButton("Confirmar pagos");
		btnConfirmarPago.setBorder(null);
		btnConfirmarPago.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnConfirmarPago.setForeground(new Color(255, 255, 255));
		btnConfirmarPago.setBackground(new Color(119, 193, 181));
		btnConfirmarPago.setBounds(313, 190, 179, 23);
		btnConfirmarPago.addActionListener(this.listenerPagoCliente());
		panelPpal.add(btnConfirmarPago);

		btnSolicitudModificacionPlan = new JButton();
		btnSolicitudModificacionPlan.setText("<html>Confirmar solicitudes<br> de modificacion de plan</html>");
		btnSolicitudModificacionPlan.setBorder(null);
		btnSolicitudModificacionPlan.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		btnSolicitudModificacionPlan.setForeground(new Color(255, 255, 255));
		btnSolicitudModificacionPlan.setBackground(new Color(119, 193, 181));
		btnSolicitudModificacionPlan.setBounds(313, 224, 179, 73);
		btnSolicitudModificacionPlan.addActionListener(this.listenerSolicitudModificacionPlan());
		panelPpal.add(btnSolicitudModificacionPlan);

		btnSolicitudReintegro = new JButton("<html>Solicitudes de<br>reintegro/prestaciones</html>");
		btnSolicitudReintegro.setBorder(null);
		btnSolicitudReintegro.setFont(new Font("Yu Gothic UI", Font.BOLD, 17));
		btnSolicitudReintegro.setForeground(new Color(255, 255, 255));
		btnSolicitudReintegro.setBackground(new Color(119, 193, 181));
		btnSolicitudReintegro.setBounds(313, 311, 179, 54);
		btnSolicitudReintegro.addActionListener(this.listenerSolicitudReintegro());
		panelPpal.add(btnSolicitudReintegro);

		btnModificarDatos = new JButton("Mis Datos");
		btnModificarDatos.setBorder(null);
		btnModificarDatos.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnModificarDatos.setForeground(new Color(255, 255, 255));
		btnModificarDatos.setBackground(new Color(119, 193, 181));
		btnModificarDatos.setBounds(313, 122, 179, 23);
		btnModificarDatos.addActionListener(this.listenerModificarDatos());
		panelPpal.add(btnModificarDatos);

		panelPpal.setVisible(true);
		return panelPpal;
	}

	private ActionListener verABMCliente() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPpal.setVisible(false);
				panelABMCliente.setVisible(true);	
			}
		};
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
		
		this.cargarABMClientes();
	}

	private void cargarABMClientes() {
		final ArrayList<ArrayList<String>> clientes = controlador.obtenerInfoClientes();

		String columna[] = { "Nombre", "DNI", "Plan" };

		String data[][] = { { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" } };

		int i = 0;
		for (ArrayList<String> cliente : clientes) {

			String nombre = cliente.get(0);
			String apellido = cliente.get(1);
			String dni = cliente.get(2);
			String plan = cliente.get(3);
			System.out.println("asad"+plan);

			data[i][0] = nombre + " " + apellido;
			data[i][1] = dni;
			data[i][2] = plan;

			i = i + 1;
		}
		DefaultTableModel tableModel = new DefaultTableModel(data, columna);
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

				for (ArrayList<String> cliente : clientes) {
					if (nombre.equals(cliente.get(0))) {
						clienteSeleccionado = cliente;
						break;
					}
				}

			}
		});
		
	}

}
