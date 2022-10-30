package sistema.vista.cliente;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sistema.controlador.ControladorCliente;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class PanelSolicitarReintegro extends JPanel {
	
	private JTextField tipoTextField;
	private JTextField CBUTextField;
	private JComboBox comboBox;
	
	private ControladorCliente controlador;
	private PanelABMSolicitudes panelABMSolicitudes;

	/**
	 * Create the panel.
	 * @param frame 
	 * @param controlador 
	 * @param panelABMSolicitudes 
	 */
	public PanelSolicitarReintegro(final ControladorCliente controlador, JFrame frame, final PanelABMSolicitudes panelABMSolicitudes) {
		super();
		setLayout(null);
		setBackground(new Color(224, 241, 238));
		
		this.controlador=controlador;
		this.panelABMSolicitudes=panelABMSolicitudes;
		
		JButton btnVolver = new JButton("");
		btnVolver.setBounds(10, 11, 35, 31);
		btnVolver.setIcon(new ImageIcon("img\\flechi.png"));
		btnVolver.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelABMSolicitudes.setVisible(true);
				setVisible(false);
			}
		});
		add(btnVolver);
		
		frame.getContentPane().add(this);
		
		JLabel lblSolicitar = new JLabel("Solicitud de Reintegro");
		lblSolicitar.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		lblSolicitar.setBounds(143, 48, 410, 55);
		add(lblSolicitar);
		
		JLabel labelPersona = new JLabel("Persona que recibió la atención");
		labelPersona.setFont(new Font("Tahoma", Font.PLAIN, 17));
		labelPersona.setBounds(20, 164, 247, 37);
		add(labelPersona);
		
		JLabel labelTipoServicio = new JLabel("Tipo de servicio");
		labelTipoServicio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		labelTipoServicio.setBounds(130, 238, 137, 37);
		add(labelTipoServicio);
		
		JLabel labelCBU = new JLabel("CBU");
		labelCBU.setFont(new Font("Tahoma", Font.PLAIN, 17));
		labelCBU.setBounds(215, 309, 52, 37);
		add(labelCBU);
		
		JLabel labelArchivo = new JLabel("Adjuntar orden");
		labelArchivo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		labelArchivo.setBounds(143, 357, 124, 37);
		add(labelArchivo);
		
		
		Vector<String> vector = new Vector<String>(controlador.obtenerNombreFamiliares());
		vector.add(controlador.obtenerDatosCliente().getNombre());
		DefaultComboBoxModel<String> dcm = new DefaultComboBoxModel<String>(vector);
		final JComboBox<String> comboBox = new JComboBox<String>(dcm);
		comboBox.setBounds(277, 164, 213, 31);
		add(comboBox);
		
		tipoTextField = new JTextField();
		tipoTextField.setBounds(277, 238, 213, 31);
		add(tipoTextField);
		tipoTextField.setColumns(10);
		
		CBUTextField = new JTextField();
		CBUTextField.setBounds(277, 309, 213, 31);
		add(CBUTextField);
		CBUTextField.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnConfirmar.setBackground(new Color(119, 193, 181));
		btnConfirmar.setBounds(165, 404, 221, 25);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validarDatos()) {
					controlador.registrarSolicitudReintegro(comboBox.getSelectedIndex(), tipoTextField.getText(), CBUTextField.getText());
					JOptionPane.showMessageDialog(null, "Solicitud cargada correctamente.");
					volver();
				}			
			}
		});
		add(btnConfirmar);
		
		
		
	}

	private boolean validarDatos() {
		if(!validarCBU()) {
			JOptionPane.showMessageDialog(this, " CBU invalido (debe ser un número de 16 digitos)", "Error en los datos ingresados", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	private boolean validarCBU() {
		String cbu= CBUTextField.getText();
		boolean formatoValido=cbu.length()==16;
		
		int i=0;
		while(formatoValido && i<cbu.length()) {
			formatoValido=cbu.charAt(i)>='0' && cbu.charAt(i)<='9';
			i++;
		}
		return formatoValido;
	}

	private void volver() {
		panelABMSolicitudes.setVisible(true);
		setVisible(false);
	}
	
	public void refresh() {
		Vector<String> vector = new Vector<String>(controlador.obtenerNombreFamiliares());
		vector.add(controlador.obtenerDatosCliente().getNombre());
		DefaultComboBoxModel<String> dcm = new DefaultComboBoxModel<String>(vector);
		
	}
}
