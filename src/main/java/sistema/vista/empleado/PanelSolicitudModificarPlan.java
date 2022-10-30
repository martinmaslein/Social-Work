package sistema.vista.empleado;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import sistema.utilidades.Pair;

public class PanelSolicitudModificarPlan extends JPanel {

	private JLabel titulo;
	private JButton btnCambioAprobado;
	private JTable tabla;
	private ControladorEmpleado controlador;
	private JScrollPane scrollPane;
	private javafx.util.Pair<String, String> solicitudSeleccionada;
	private PanelSolicitudModificarPlan panel = this;

	public PanelSolicitudModificarPlan(final ControladorEmpleado controlador) {
		super();
		this.controlador = controlador;
		setBackground(new Color(224, 241, 238));
		setLayout(null);

		titulo = new JLabel("Confirmar solicitud de cambio");
		titulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		titulo.setBounds(190, 49, 328, 20);
		add(titulo);

		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(224, 241, 238));
		scrollPane.setMinimumSize(new Dimension(27, 27));
		scrollPane.setBounds(129, 94, 421, 219);
		add(scrollPane);

		tabla = new JTable();
		tabla.setRowHeight(33);
		scrollPane.setViewportView(tabla);

		cargarSolicitudes();

		btnCambioAprobado = new JButton("Aprobar cambio");
		btnCambioAprobado.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (solicitudSeleccionada != null) {
					if (solicitudSeleccionada.getKey() != null) {

						int select = JOptionPane.showConfirmDialog(panel, "¿Desea aprobar la solcitud seleccionada?",
								"Aprobar solicitud", JOptionPane.CANCEL_OPTION);
						System.out.println(select);

						String nombre[] = new String[2];
						String n = (String) solicitudSeleccionada.getKey();
						nombre = n.split(",");								
						
						if (select == JOptionPane.OK_OPTION) {
							try {
								controlador.aprobarCambio(nombre [1], nombre[0]);
							
								controlador.aprobarCambio(solicitudSeleccionada.getKey(),
										solicitudSeleccionada.getValue());
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

		btnCambioAprobado.setBounds(276, 353, 128, 23);
		btnCambioAprobado.setBorder(null);
		btnCambioAprobado.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnCambioAprobado.setForeground(new Color(255, 255, 255));
		btnCambioAprobado.setBackground(new Color(119, 193, 181));
		add(btnCambioAprobado);
	}

	public void cargarSolicitudes() {
		final List<javafx.util.Pair<String, String>> solicitudes = controlador.obtenerSolicitudes();

		String columna[] = { "Nombre", "Plan solicitado" };

		String data[][] = { { "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" } };

		int i = 0;
		for (javafx.util.Pair<String, String> solicitud : solicitudes) {

			String nombre = solicitud.getKey();
			String plan = solicitud.getValue();

			data[i][0] = nombre;
			data[i][1] = plan;

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

				for (javafx.util.Pair<String, String> solicitud : solicitudes) {
					System.out.println(solicitud.getKey());
					if (nombre.equals(solicitud.getKey())) {
						solicitudSeleccionada = solicitud;
						break;
					}else {
						solicitudSeleccionada = null;
					}

				}

			}
		});

	}

}
