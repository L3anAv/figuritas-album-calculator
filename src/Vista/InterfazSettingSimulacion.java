package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import interfaces.Simulacion;
import modelo.SimulacionUnaPersona;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class InterfazSettingSimulacion {

	private JFrame frame;
	private Simulacion sim;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new FlatDarkLaf());
					InterfazSettingSimulacion window = new InterfazSettingSimulacion(new SimulacionUnaPersona(0));
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
	public InterfazSettingSimulacion(Simulacion sim) {
		
		this.sim = sim;
		
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		iniciarFrame();
		iniciarLabels();
	
	
	}







	private void iniciarFrame() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(380, 180, 760, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(46, 121, 281, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(46, 190, 281, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(46, 259, 281, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		
		textField_4 = new JTextField();
		textField_4.setBounds(46, 328, 281, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JCheckBox chkDefaultCantFigus = new JCheckBox("Valor por defecto");
		chkDefaultCantFigus.setBounds(365, 120, 185, 23);
		frame.getContentPane().add(chkDefaultCantFigus);
		
		JCheckBox chkDefaultFigusPaq = new JCheckBox("Valor por defecto");
		chkDefaultFigusPaq.setBounds(365, 189, 152, 23);
		frame.getContentPane().add(chkDefaultFigusPaq);
		
		JCheckBox chkDefaultPrecio = new JCheckBox("Valor por defecto");
		chkDefaultPrecio.setBounds(365, 258, 166, 23);
		frame.getContentPane().add(chkDefaultPrecio);
		
		JButton btnStart = new JButton("Comenzar");
		btnStart.setBounds(311, 386, 89, 23);
		frame.getContentPane().add(btnStart);
		frame.setTitle("Simulacion Album Figuritas 2022");
		
		
	}


	private void iniciarLabels() {
		
		JLabel lblTitulo = new JLabel("Configuracion de Simulacion");
		lblTitulo.setBounds(46, 25, 281, 43);
		lblTitulo.setFont(new Font("Inconsolata",Font.ITALIC ,20));
		frame.getContentPane().add(lblTitulo);
		
		JLabel lblCantTotalFigus = new JLabel("Cantidad total de figuritas:");
		lblCantTotalFigus.setBounds(46, 96, 232, 14);
		lblCantTotalFigus.setFont(new Font("Inconsolata",Font.PLAIN ,14));
		frame.getContentPane().add(lblCantTotalFigus);
		
		JLabel lblFigusEnPaquete = new JLabel("Cantidad de figuritas por paquete: ");
		lblFigusEnPaquete.setBounds(46, 165, 232, 14);
		lblFigusEnPaquete.setFont(new Font("Inconsolata",Font.PLAIN ,14));
		frame.getContentPane().add(lblFigusEnPaquete);
		
		JLabel lblPrecioPaq = new JLabel("Precio por paquete:");
		lblPrecioPaq.setBounds(46, 234, 174, 14);
		lblPrecioPaq.setFont(new Font("Inconsolata",Font.PLAIN ,14));
		frame.getContentPane().add(lblPrecioPaq);
		
		JLabel lblCantSimulaciones = new JLabel("Cantidad de simulaciones a realizar:");
		lblCantSimulaciones.setBounds(46, 303, 281, 14);
		lblCantSimulaciones.setFont(new Font("Inconsolata",Font.PLAIN ,14));
		frame.getContentPane().add(lblCantSimulaciones);
	}
}

