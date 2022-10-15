package sistema.vista.empleado;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPagoCliente extends JPanel {

	private JLabel titulo;
	private JButton btnPagoAprobado;
	
	public PanelPagoCliente() {
		super();
		setBackground(new Color(224, 241, 238));
		setLayout(null);		
		titulo = new JLabel("Confirmar pago");
		titulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		titulo.setBounds(340, 63, 221, 20);
		add(titulo);
		
		btnPagoAprobado = new JButton("Pago aprobado");
		btnPagoAprobado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO CONFIRMAR PAGO??? US 8
				 
			}
		});
		btnPagoAprobado.setBounds(358, 267, 128, 23);
		btnPagoAprobado.setBorder(null);
		btnPagoAprobado.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnPagoAprobado.setForeground(new Color(255, 255, 255));
		btnPagoAprobado.setBackground(new Color(119, 193, 181));
		add(btnPagoAprobado);
	}
}
