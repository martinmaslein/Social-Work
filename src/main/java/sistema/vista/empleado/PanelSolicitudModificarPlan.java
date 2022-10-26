package sistema.vista.empleado;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	public PanelSolicitudModificarPlan(final ControladorEmpleado controlador) {
		super();
		this.controlador = controlador;
		setBackground(new Color(224, 241, 238));
		setLayout(null);
		
		titulo = new JLabel("Confirmar solicitud de cambio");
		titulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		titulo.setBounds(190, 49, 328, 20);
		add(titulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(224, 241, 238));
		scrollPane.setMinimumSize(new Dimension(27, 27));
		scrollPane.setBounds(129, 94, 421, 219);
		add(scrollPane);
		
		tabla = new JTable();
		tabla.setRowHeight(33);
		scrollPane.setViewportView(tabla);
		
		List<javafx.util.Pair<String, String>> solicitudes = controlador.obtenerSolicitudes();
		
		String columna [] = {"Nombre","Plan solicitado"};
		
		String data[][] = {{"",""},{"",""},{"",""},{"",""},{"",""}};
		
		int i=0;
		for(javafx.util.Pair<String, String> solicitud : solicitudes) {
			
			String nombre = solicitud.getKey();
	    	String plan = solicitud.getValue();
	    	
			data[i][0] = nombre;
			data[i][1] = plan;
			
			i = i+1;
		}
		
		
		
		DefaultTableModel tableModel = new DefaultTableModel(data,columna);
		final JTable table = new JTable(tableModel);
		table.setRowHeight(33);
		scrollPane.setViewportView(table);
		
		btnCambioAprobado = new JButton("Aprobar cambio");
		btnCambioAprobado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO CONFIRMAR CAMBIO??? US 9
				final Object selectedObject = (Object) table.getModel().getValueAt(table.getSelectedRow(), 
						table.getSelectedColumn()); 
				
				String nombre[] = new String[2];
				String n = (String) table.getModel().getValueAt(table.getSelectedRow(),0);
				nombre = n.split(",");
				if(selectedObject != null) {
					try {
						controlador.aprobarCambio(nombre [1], nombre[0]);
					
						JScrollPane scrollPane = new JScrollPane();
						scrollPane.setFont(new Font("Yu Gothic UI", Font.BOLD, 15));
						scrollPane.setBorder(null);
						scrollPane.setBackground(new Color(224, 241, 238));
						scrollPane.setMinimumSize(new Dimension(27, 27));
						scrollPane.setBounds(219, 89, 421, 219);
						add(scrollPane);
						
						List<javafx.util.Pair<String, String>> solicitudes = controlador.obtenerSolicitudes();
						
						String columna [] = {"Nombre","Plan solicitado"};
						
						String data[][] = {{"",""},{"",""},{"",""},{"",""},{"",""}};
						
						int i=0;
						for(javafx.util.Pair<String, String> solicitud : solicitudes) {
							
							String nom = solicitud.getKey();
					    	String plan = solicitud.getValue();
					    	
							data[i][0] = nom;
							data[i][1] = plan;
							
							i = i+1;
						}
						DefaultTableModel tableModel = new DefaultTableModel(data,columna);
						final JTable table = new JTable(tableModel);
						table.setRowHeight(33);
						scrollPane.setViewportView(table);
						
						JOptionPane.showMessageDialog(null, "Cambio de plan realizado correctamente");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
		//TODO jeje
	}

}
