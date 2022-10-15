package sistema.vista.empleado;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelSolicitudModificarPlan extends JPanel {
	
	private JLabel titulo;
	private JButton btnCambioAprobado;
	
	public PanelSolicitudModificarPlan() {
		super();
		setBackground(new Color(224, 241, 238));
		setLayout(null);
		
		titulo = new JLabel("Confirmar solicitud de cambio");
		titulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		titulo.setBounds(280, 44, 328, 20);
		add(titulo);
		
		btnCambioAprobado = new JButton("Aprobar cambio");
		btnCambioAprobado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO CONFIRMAR CAMBIO??? US 9
				 
			}
		});
		btnCambioAprobado.setBounds(358, 267, 128, 23);
		btnCambioAprobado.setBorder(null);
		btnCambioAprobado.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnCambioAprobado.setForeground(new Color(255, 255, 255));
		btnCambioAprobado.setBackground(new Color(119, 193, 181));
		add(btnCambioAprobado);
	}

}
