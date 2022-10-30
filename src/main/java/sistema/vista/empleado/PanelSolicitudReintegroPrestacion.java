package sistema.vista.empleado;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sistema.controlador.ControladorEmpleado;

public class PanelSolicitudReintegroPrestacion extends JPanel {

	private JLabel titulo;
	private JButton btnAprobar;
	private JTable tabla;
	private ControladorEmpleado controlador;
	private JScrollPane scrollPane;
	private ArrayList<String> solicitudSeleccionada;
	private PanelSolicitudReintegroPrestacion panel = this;

	public PanelSolicitudReintegroPrestacion(final ControladorEmpleado controlador) {
		super();
		setBackground(new Color(224, 241, 238));
		setLayout(null);
		this.controlador = controlador;

		titulo = new JLabel("Solicitudes");
		titulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		titulo.setBounds(70, 32, 328, 20);
		add(titulo);

		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(224, 241, 238));
		scrollPane.setMinimumSize(new Dimension(27, 27));
		scrollPane.setBounds(56, 95, 421, 219);
		add(scrollPane);

		tabla = new JTable();
		tabla.setRowHeight(33);
		scrollPane.setViewportView(tabla);

		cargarSolicitudes();

		btnAprobar = new JButton("Aprobar");
		btnAprobar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (solicitudSeleccionada != null) {
					if (solicitudSeleccionada.get(1) != null) {

						int select = JOptionPane.showConfirmDialog(panel, "¿Desea aprobar la solcitud seleccionada?",
								"Aprobar solicitud", JOptionPane.CANCEL_OPTION);
						System.out.println(select);

						if (select == JOptionPane.OK_OPTION) {
							try {
								controlador.aprobarCambio(solicitudSeleccionada.get(1), solicitudSeleccionada.get(0));
								JOptionPane.showMessageDialog(null, "Solicitud aprobada correctamente.");
								cargarSolicitudes();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una solicitud para aprobar.");
				}

			}
		});

		btnAprobar.setBounds(504, 188, 100, 23);
		btnAprobar.setBorder(null);
		btnAprobar.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnAprobar.setForeground(new Color(255, 255, 255));
		btnAprobar.setBackground(new Color(119, 193, 181));
		add(btnAprobar);

		JButton btnDesaprobar = new JButton("Desaprobar");
		btnDesaprobar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (solicitudSeleccionada != null) {
					if (solicitudSeleccionada.get(1) != null) {

						int select = JOptionPane.showConfirmDialog(panel, "¿Desea desaprobar la solcitud seleccionada?",
								"Desaprobar solicitud", JOptionPane.CANCEL_OPTION);
						System.out.println(select);

						if (select == JOptionPane.OK_OPTION) {
							try {
								controlador.desaprobarCambio(solicitudSeleccionada.get(1),
										solicitudSeleccionada.get(0));
								JOptionPane.showMessageDialog(null, "Solicitud desaprobada correctamente.");
								cargarSolicitudes();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar una solicitud para desaprobar.");
				}

			}
		});
		btnDesaprobar.setForeground(Color.WHITE);
		btnDesaprobar.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnDesaprobar.setBorder(null);
		btnDesaprobar.setBackground(new Color(119, 193, 181));
		btnDesaprobar.setBounds(504, 222, 100, 23);
		add(btnDesaprobar);
		
		JButton btnVerMas = new JButton("Ver mas");
		btnVerMas.setForeground(Color.WHITE);
		btnVerMas.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnVerMas.setBorder(null);
		btnVerMas.setBackground(new Color(119, 193, 181));
		btnVerMas.setBounds(504, 154, 100, 23);
		add(btnVerMas);
	}

	public void cargarSolicitudes() {
		final ArrayList<ArrayList<String>> solicitudes = controlador.obtenerSolicitudes();
		String columna[] = { "Nombre", "DNI", "Tipo solicitud" };

		String data[][] = { { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" } };

		int i = 0;
		for (ArrayList<String> solicitud : solicitudes) {
			// nombre(0), apellido(1), dni(2), "prestacion"(3),id_prestacion(4), fecha(5)
			String nombre = solicitud.get(0);
			String apellido = solicitud.get(1);
			String dni = solicitud.get(2);
			String tipo_solicitud = solicitud.get(3);

			data[i][0] = nombre + ", " + apellido;
			data[i][1] = dni;
			data[i][2] = tipo_solicitud;

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
				System.out.println(nombre);

				for (ArrayList<String> solicitud : solicitudes) {
					if (nombre.equals(solicitud.get(1) + "," + solicitud.get(1))) {
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
