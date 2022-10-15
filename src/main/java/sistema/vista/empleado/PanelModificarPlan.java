package sistema.vista.empleado;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;

import sistema.controlador.ControladorEmpleado;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelModificarPlan extends JPanel {

	private JTextField campoDniCliente;
	private JLabel nuevoPlan;
	private JLabel titulo;
	private JLabel dniCliente;
	private JComboBox<String> comboBox;
	private ControladorEmpleado controlador;

	public PanelModificarPlan(final ControladorEmpleado controlador) {
		super();
		setBackground(new Color(224, 241, 238));
		setLayout(null);
		this.controlador = controlador;

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

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean resultado = controlador.modificarPlan(campoDniCliente.getText(),
							comboBox.getItemAt(comboBox.getSelectedIndex()));
					if (resultado) {
						JOptionPane.showMessageDialog(null, "Plan modificado correctamente");
						dniCliente.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "El DNI ingresado no corresponde a un cliente");
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnConfirmar.setBounds(398, 241, 89, 23);
		btnConfirmar.setBorder(null);
		btnConfirmar.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnConfirmar.setForeground(new Color(255, 255, 255));
		btnConfirmar.setBackground(new Color(119, 193, 181));
		add(btnConfirmar);
	}
}
