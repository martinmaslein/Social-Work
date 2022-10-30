package sistema.vista.cliente;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
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

public class PanelSolicitarPrestacion extends JPanel {
	
	private JTextField fechaTextField;
	private JTextField profesionalTextField;
	private JComboBox comboBox;
	
	private ControladorCliente controlador;
	private PanelABMSolicitudes panelABMSolicitudes;

	/**
	 * Create the panel.
	 * @param frame 
	 * @param controlador 
	 * @param panelABMSolicitudes 
	 */
	public PanelSolicitarPrestacion(final ControladorCliente controlador, JFrame frame, final PanelABMSolicitudes panelABMSolicitudes) {
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
				volver();
			}
		});
		add(btnVolver);
		
		frame.getContentPane().add(this);
		
		JLabel lblSolicitar = new JLabel("Solicitud de Prestación");
		lblSolicitar.setFont(new Font("Yu Gothic UI", Font.BOLD, 30));
		lblSolicitar.setBounds(143, 48, 410, 55);
		add(lblSolicitar);
		
		JLabel labelPersona = new JLabel("Persona que recibió la atención");
		labelPersona.setFont(new Font("Tahoma", Font.PLAIN, 17));
		labelPersona.setBounds(20, 164, 247, 37);
		add(labelPersona);
		
		JLabel labelFecha = new JLabel("Fecha de realización de servicio");
		labelFecha.setFont(new Font("Tahoma", Font.PLAIN, 17));
		labelFecha.setBounds(20, 238, 247, 37);
		add(labelFecha);
		
		JLabel labelProfesional = new JLabel("Nombre profesional o institución");
		labelProfesional.setFont(new Font("Tahoma", Font.PLAIN, 17));
		labelProfesional.setBounds(20, 309, 247, 37);
		add(labelProfesional);
		
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
		
		fechaTextField = new JTextField();
		fechaTextField.setBounds(277, 238, 213, 31);
		add(fechaTextField);
		fechaTextField.setColumns(10);
		
		profesionalTextField = new JTextField();
		profesionalTextField.setBounds(277, 309, 213, 31);
		add(profesionalTextField);
		profesionalTextField.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		btnConfirmar.setBackground(new Color(119, 193, 181));
		btnConfirmar.setBounds(165, 404, 221, 25);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validarDatos()) {
					controlador.registrarSolicitudPrestacion(comboBox.getSelectedIndex(), fechaTextField.getText(), profesionalTextField.getText());
					JOptionPane.showMessageDialog(null, "Solicitud cargada correctamente.");
					volver();
				}			
			}
		});
		add(btnConfirmar);
		
		
		
	}
	
	private boolean validarDatos() {
		if(!validarFormatoFecha()) {
			JOptionPane.showMessageDialog(this, "La fecha no tiene un formato valido (dd/mm/aaaa)", "Error en los datos ingresados", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(!validarFechaNoAnterior()) {
			JOptionPane.showMessageDialog(this, "La fecha ingresada es anterior a la de hoy", "Error en los datos ingresados", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		 
		return true;
	}
	
	private boolean validarFormatoFecha() {
	
	    try {
	        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	        formatoFecha.setLenient(false);
	        java.util.Date fecha = formatoFecha.parse(fechaTextField.getText());
	        return true;
	    } catch (ParseException e) {
	        return false;
	    }
	}
	private boolean validarFechaNoAnterior() {
	    boolean correcto = true;
	    try {
	        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	        java.util.Date fecha = formatoFecha.parse(fechaTextField.getText());
	        correcto = fecha.after(Date.from(Instant.now())) ||formatoFecha.format(fecha).equals(formatoFecha.format(Date.from(Instant.now())));
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return correcto;
	}
	
	private void volver() {
		panelABMSolicitudes.cargarABMSolicitudes();
		panelABMSolicitudes.setVisible(true);
		setVisible(false);
	}
	
	public void refresh() {
		Vector<String> vector = new Vector<String>(controlador.obtenerNombreFamiliares());
		vector.add(controlador.obtenerDatosCliente().getNombre());
		DefaultComboBoxModel<String> dcm = new DefaultComboBoxModel<String>(vector);
		
	}
}
