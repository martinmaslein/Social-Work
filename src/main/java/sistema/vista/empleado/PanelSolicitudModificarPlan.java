package sistema.vista.empleado;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelSolicitudModificarPlan extends JPanel {
	
	private JLabel titulo;
	private JButton btnCambioAprobado;
	private JTable tabla;
	
	public PanelSolicitudModificarPlan() {
		super();
		setBackground(new Color(224, 241, 238));
		setLayout(null);
		
		titulo = new JLabel("Confirmar solicitud de cambio");
		titulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		titulo.setBounds(280, 44, 328, 20);
		add(titulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(224, 241, 238));
		scrollPane.setMinimumSize(new Dimension(27, 27));
		scrollPane.setBounds(219, 89, 421, 219);
		add(scrollPane);
		
		tabla = new JTable();
		tabla.setRowHeight(33);
		scrollPane.setViewportView(tabla);
		tabla.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				
			},
			new String[] {
				"Cliente", "Plan solicitado"
			}
		));
		tabla.getColumnModel().getColumn(0).setMinWidth(3);
		
		btnCambioAprobado = new JButton("Aprobar cambio");
		btnCambioAprobado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO CONFIRMAR CAMBIO??? US 9
				 
			}
		});
		btnCambioAprobado.setBounds(366, 348, 128, 23);
		btnCambioAprobado.setBorder(null);
		btnCambioAprobado.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnCambioAprobado.setForeground(new Color(255, 255, 255));
		btnCambioAprobado.setBackground(new Color(119, 193, 181));
		add(btnCambioAprobado);
	}

}
