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

public class InterfazSettingSimulacion {

	private JFrame frame;
	private Simulacion sim;
	private JTextField textField;
	
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
	}







	private void iniciarFrame() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(380, 180, 500, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Simulacion Album Figuritas 2022");
		
		
	}


	private void iniciarTxtFieldCantFiguritas() {
		
		
		
	}








}
