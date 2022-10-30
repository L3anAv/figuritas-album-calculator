package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.border.Border;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class InterfazSimuladorAlbum {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazSimuladorAlbum window = new InterfazSimuladorAlbum();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazSimuladorAlbum() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String[] opcionesDeSimulacion = {"Simulacion una sola persona", "Simulacion varias personas con regalo",
				"Simulacion de varias personas con intercambio"};
		
		frame = new JFrame();
		frame.setBounds(380, 180, 450, 300);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		
		
		JComboBox SeleccionDeSimulacion = new JComboBox(opcionesDeSimulacion);
		
		SeleccionDeSimulacion.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String seleccionado = SeleccionDeSimulacion.getSelectedItem().toString();
				if(seleccionado.equals("Simulacion una sola persona")) {
					// TODO activar Desactivar el textField
				}
			}
		});
		
		SeleccionDeSimulacion.setFont(new Font("Inconsolata", Font.PLAIN, 14));
		SeleccionDeSimulacion.setBackground(new Color(255, 255, 255));
		SeleccionDeSimulacion.setBounds(68, 46, 329, 26);
		SeleccionDeSimulacion.setFocusable(false);
	
		
		JButton botonInicio = new JButton("INICIAR");
		botonInicio.setFocusPainted(false);
		botonInicio.setBorderPainted(false);
		botonInicio.setFont(new Font("Inconsolata", Font.PLAIN, 14));
		botonInicio.setForeground(new Color(255, 255, 255));
		botonInicio.setBackground(new Color(17, 150, 238));
		botonInicio.setBounds(150, 210, 131, 35);
		
		frame.getContentPane().add(botonInicio);
		frame.getContentPane().add(SeleccionDeSimulacion);
		
		textField = new JTextField();
		textField.setBounds(68, 102, 329, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setEnabled(false);
		
		JLabel lblCantidadDePersonas = new JLabel("CANTIDAD DE PERSONAS:");
		lblCantidadDePersonas.setFont(new Font("Inconsolata", Font.ITALIC, 16));
		lblCantidadDePersonas.setBounds(68, 84, 174, 17);
		frame.getContentPane().add(lblCantidadDePersonas);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
