package Vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

import interfaces.Simulacion;
import modelo.SimulacionUnaPersona;

public class InterfazSettingSimulacion {

	private JFrame frame;
	private JLayeredPane layeredPane;
	private Simulacion sim;
	
	
	private JTextField field_CantTotalFigus;
	private JTextField field_CantFigusPaq;
	private JTextField field_Precio;
	private JTextField field_CantSims;
	
	
	//Labels de Valores default
	private JLabel default_CantFigus;
	private JLabel default_CantFigusPaq;
	private JLabel default_Precio;
	
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
		iniciarTxtFields();
	
	}







	private void iniciarFrame() {
		
		frame = new JFrame();
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 640, 480);
		frame.setResizable(false);
		frame.setBounds(380, 180, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
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

	private void iniciarTxtFields() {
		
		
		field_CantTotalFigus = new JTextField();
		field_CantTotalFigus.setBounds(46, 121, 281, 20);
		field_CantTotalFigus.setColumns(10);
		layeredPane.add(field_CantTotalFigus, Integer.valueOf(1));
		//frame.getContentPane().add(field_CantTotalFigus);
		
		field_CantFigusPaq = new JTextField();
		field_CantFigusPaq.setBounds(46, 190, 281, 20);
		field_CantFigusPaq.setColumns(10);
		layeredPane.add(field_CantFigusPaq, Integer.valueOf(1));
		//frame.getContentPane().add(field_CantPaq);
		
		field_Precio = new JTextField();
		field_Precio.setBounds(46, 259, 281, 20);
		field_Precio.setColumns(10);
		layeredPane.add(field_Precio, Integer.valueOf(1));
		//frame.getContentPane().add(field_Precio);
		
		
		field_CantSims = new JTextField();
		field_CantSims.setBounds(46, 328, 281, 20);
		field_CantSims.setColumns(10);
		layeredPane.add(field_CantSims, Integer.valueOf(1));
		//frame.getContentPane().add(field_CantSims);
		
		iniciarLblsDefault();
		//default_CantFigus.setEnabled(false);
		JCheckBox chkDefaultCantFigus = new JCheckBox("Valor por defecto");
		chkDefaultCantFigus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(chkDefaultCantFigus.isSelected()) {
					
					field_CantTotalFigus.setEnabled(false);	
					default_CantFigus.setVisible(true);	
					frame.revalidate();
					frame.repaint();
					
				}
				else {
					
					field_CantTotalFigus.setEnabled(true);
					default_CantFigus.setVisible(false);
	
					frame.revalidate();
					frame.repaint();
					
				}
			}
		});
		chkDefaultCantFigus.setBounds(365, 120, 185, 23);
		frame.getContentPane().add(chkDefaultCantFigus);
		
		JCheckBox chkDefaultFigusPaq = new JCheckBox("Valor por defecto");
		chkDefaultFigusPaq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			
			if(chkDefaultFigusPaq.isSelected()) {
				
				field_CantFigusPaq.setEnabled(false);
				default_CantFigusPaq.setVisible(true);
				frame.revalidate();
				frame.repaint();
				
			}
			
			else {
				
				field_CantFigusPaq.setEnabled(true);
				default_CantFigusPaq.setVisible(false);
				frame.revalidate();
				frame.repaint();
				
			}
				
			
			
			}
		});
		chkDefaultFigusPaq.setBounds(365, 189, 152, 23);
		frame.getContentPane().add(chkDefaultFigusPaq);
		
		JCheckBox chkDefaultPrecio = new JCheckBox("Valor por defecto");
		chkDefaultPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			if(chkDefaultPrecio.isSelected()) {
				field_Precio.setEnabled(false);
				default_Precio.setVisible(true);
			}
			else {
				field_Precio.setEnabled(true);
				default_Precio.setVisible(false);
				
			}
			
			
			}
		});
		chkDefaultPrecio.setBounds(365, 258, 166, 23);
		frame.getContentPane().add(chkDefaultPrecio);

		
		
		
		
		
		frame.getContentPane().add(layeredPane);
	}

	
	private void iniciarLblsDefault() {
		
		default_CantFigus = new JLabel("630");
		default_CantFigus.setBounds(46,121,281,20);
		default_CantFigus.setVisible(false);
		layeredPane.add(default_CantFigus, Integer.valueOf(2));
		
		default_CantFigusPaq = new JLabel("5");
		default_CantFigusPaq.setBounds(46, 190, 281, 20);
		default_CantFigusPaq.setVisible(false);
		layeredPane.add(default_CantFigusPaq, Integer.valueOf(2));
		
		default_Precio = new JLabel("200");
		default_Precio.setBounds(46, 259, 281, 20);
		default_Precio.setVisible(false);
		layeredPane.add(default_Precio, Integer.valueOf(2));
	}

}

