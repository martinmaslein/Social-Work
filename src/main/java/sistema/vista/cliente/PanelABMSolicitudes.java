package sistema.vista.cliente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sistema.controlador.ControladorCliente;

public class PanelABMSolicitudes extends JPanel {

	private JLabel lblAcciones;
	private JButton btnVerMas, btnSolicitarReintegro, btnEliminar, btnSolicitarPrestacion;
	private ControladorCliente controlador;
	private JScrollPane scrollPane;
	private ArrayList<String> solicitudSeleccionada;
	private PanelABMSolicitudes panel = this;
	private JFrame frame;

	protected PanelSolicitarReintegro panelSolicitarReintegro;
	protected PanelSolicitarPrestacion panelSolicitarPrestacion;

	public PanelABMSolicitudes(final ControladorCliente controlador, JFrame frame) {
		super();
		this.controlador = controlador;
		setBackground(new Color(224, 241, 238));
		setLayout(null);
		craerBotonesPanelABMSolicitudes();
		cargarABMSolicitudes();

		this.frame = frame;
		inicializarPaneles();

	}

	private void inicializarPaneles() {

		panelSolicitarPrestacion = new PanelSolicitarPrestacion(controlador, frame, this);
		panelSolicitarReintegro = new PanelSolicitarReintegro(controlador, frame, this);
	}

	private void craerBotonesPanelABMSolicitudes() {
		// Labels y Botones
		JLabel lblSolicitudes = new JLabel("Solicitudes");
		lblSolicitudes.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
		lblSolicitudes.setBounds(77, 50, 129, 23);
		add(lblSolicitudes);

		btnVerMas = new JButton("Ver Mas");
		btnVerMas.setBounds(618, 209, 129, 34);
		// btnVerMas_1.addActionListener(this.verMas());
		btnVerMas.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnVerMas.setForeground(new Color(255, 255, 255));
		btnVerMas.setBackground(new Color(119, 193, 181));

		btnVerMas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				boolean toReturn;
				if (solicitudSeleccionada != null) {
					if (solicitudSeleccionada.get(0) != null) {
						String info = controlador.informacionSolicitud(solicitudSeleccionada);
						JOptionPane.showMessageDialog(null, info);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una solicitud para ver más.");
				}

			}
		});
		add(btnVerMas);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(618, 278, 129, 34);
		btnEliminar.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBackground(new Color(119, 193, 181));
		// btnEliminar.addActionListener(this.eliminarFamiliar());
		btnEliminar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				boolean toReturn;
				if (solicitudSeleccionada != null) {
					if (solicitudSeleccionada.get(0) != null) {
						System.out.println("nombre que llega al boton = " + solicitudSeleccionada.get(0)
								+ " solicitud: " + solicitudSeleccionada.get(3));

						int select = JOptionPane.showConfirmDialog(panel, "¿Desea eliminar la solcitud seleccionada?",
								"Eliminar solicitud", JOptionPane.CANCEL_OPTION);
						System.out.println(select);

						if (select == JOptionPane.OK_OPTION) {
							toReturn = controlador.eliminarSolicitud(solicitudSeleccionada);
							if (toReturn) {
								cargarABMSolicitudes();
								JOptionPane.showMessageDialog(null, "Solicitud eliminada correctamente.");
							} else {
								JOptionPane.showMessageDialog(null, "No fue posible eliminar la solicitud.");
							}

						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una solicitud para eliminar.");
				}

			}
		});

		add(btnEliminar);

		btnSolicitarReintegro = new JButton("+ Solicitar reintegro");
		btnSolicitarReintegro.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnSolicitarReintegro.setForeground(new Color(255, 255, 255));
		btnSolicitarReintegro.setBackground(new Color(119, 193, 181));
		btnSolicitarReintegro.setBounds(526, 51, 221, 25);
		btnSolicitarReintegro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				panelSolicitarReintegro.refresh();
				panelSolicitarReintegro.setVisible(true);
			}
		});
		add(btnSolicitarReintegro);

		btnSolicitarPrestacion = new JButton("+ Solicitar prestación");
		btnSolicitarPrestacion.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnSolicitarPrestacion.setForeground(new Color(255, 255, 255));
		btnSolicitarPrestacion.setBackground(new Color(119, 193, 181));
		btnSolicitarPrestacion.setBounds(291, 51, 221, 25);
		btnSolicitarPrestacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				panelSolicitarPrestacion.refresh();
				panelSolicitarPrestacion.setVisible(true);
			}
		});
		add(btnSolicitarPrestacion);

		lblAcciones = new JLabel("Acciones");
		add(lblAcciones);
		lblAcciones.setFont(new Font("Yu Gothic UI", Font.BOLD, 24));
		lblAcciones.setBounds(618, 149, 95, 32);

		// ABM
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(224, 241, 238));
		scrollPane.setMinimumSize(new Dimension(27, 27));
		scrollPane.setBounds(33, 188, 488, 219);
		add(scrollPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(154, 209, 201));
		panel_1.setBounds(64, 0, 882, 115);
		add(panel_1);
	}

	public void cargarABMSolicitudes() {
		final ArrayList<ArrayList<String>> solicitudes = controlador.obtenerSolicitudesABM();

		String columna[] = { "Nombre", "	Tipo" };

		String data[][] = { { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" } };

		int i = 0;
		for (ArrayList<String> solicitud : solicitudes) {

			String nombre = solicitud.get(0);
			System.out.print(nombre);
			String apellido = solicitud.get(1);
			System.out.print(apellido);
			String tipo = solicitud.get(2);
			System.out.print(tipo);

			data[i][0] = nombre + " " + apellido;
			data[i][1] = tipo;

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
				Object selectedObjectTipo = (Object) table.getModel().getValueAt(row, col+1);
				Object tipo = selectedObjectTipo.toString();
				String nomAp = selectedObject.toString();
				String[] partes = nomAp.split(" ");
				String nombre = partes[0];
				System.out.println(nombre);

				for (ArrayList<String> solicitud : solicitudes) {
					if (nombre.equals(solicitud.get(0)) && tipo.equals(solicitud.get(2))) {
						solicitudSeleccionada = solicitud;
						break;
					} else {
						solicitudSeleccionada = null;
					}

				}

			}
		});

	}
}
