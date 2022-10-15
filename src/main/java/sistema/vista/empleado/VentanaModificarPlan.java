package sistema.vista.empleado;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class VentanaModificarPlan extends JPanel {
	private JTextField campoDniCliente;
	private JLabel nuevoPlan;
	private JLabel titulo;
	private JLabel dniCliente;
	private JComboBox<String> comboBox;
	
	public VentanaModificarPlan() {
		super();
		setBackground(new Color(224, 241, 238));
		setLayout(null);
		setVisible(false);
		
		campoDniCliente = new JTextField();
		campoDniCliente.setBounds(267, 129, 108, 20);
		add(campoDniCliente);
		campoDniCliente.setColumns(10);
		
		nuevoPlan = new JLabel("Plan nuevo");
		nuevoPlan.setBounds(166, 183, 70, 14);
		add(nuevoPlan);
		
		titulo = new JLabel("Modificar plan cliente");
		titulo.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		titulo.setBounds(340, 63, 221, 20);
		add(titulo);
		
		dniCliente = new JLabel("DNI cliente *");
		dniCliente.setBounds(166, 132, 79, 14);
		add(dniCliente);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(267, 175, 43, 22);
		comboBox.addItem("A");
		comboBox.addItem("B");
		add(comboBox);
	}
}
