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

import sistema.controlador.ControladorEmpleado;

public class PanelPagoCliente extends JPanel {

	private JLabel titulo;
	private JButton btnPagoAprobado;
	private JTable tabla;
	private ControladorEmpleado controlador;
	
	public PanelPagoCliente(ControladorEmpleado controlador) {
		super();
		this.controlador = controlador;
		setBackground(new Color(224, 241, 238));
		setLayout(null);		
		titulo = new JLabel("Confirmar pago");
		titulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		titulo.setBounds(340, 63, 221, 20);
		add(titulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(224, 241, 238));
		scrollPane.setMinimumSize(new Dimension(27, 27));
		scrollPane.setBounds(199, 112, 421, 219);
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
				"Cliente", "Monto"
			}
		));
		tabla.getColumnModel().getColumn(0).setMinWidth(3);
		
		btnPagoAprobado = new JButton("Pago aprobado");
		btnPagoAprobado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO CONFIRMAR PAGO??? US 8
				 
			}
		});
		btnPagoAprobado.setBounds(364, 362, 128, 23);
		btnPagoAprobado.setBorder(null);
		btnPagoAprobado.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnPagoAprobado.setForeground(new Color(255, 255, 255));
		btnPagoAprobado.setBackground(new Color(119, 193, 181));
		add(btnPagoAprobado);
	}
}
