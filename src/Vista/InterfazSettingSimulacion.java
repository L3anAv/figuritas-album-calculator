package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.im.InputContext;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

import interfaces.Simulacion;
import modelo.SimulacionUnaPersona;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InterfazSettingSimulacion {

	private JFrame frame;
	private JLayeredPane layeredPane;
	private Simulacion sim;

	private JTextField field_CantTotalFigus;
	private JTextField field_CantFigusPaq;
	private JTextField field_Precio;
	private JTextField field_CantSims;

	//Labels de Valores default
	private JLabel defaultLbl_CantFigus;
	private JLabel defaultLbl_CantFigusPaq;
	private JLabel defaultLbl_Precio;

	//Variables
	private int cantTotalFigus;
	private int cantFigusPaq;
	private int precioPaq;
	private int cantSims;

	//Variables default
	private final int default_CantFigus = 630;
	private final int default_CantFigusPaq = 5;
	private final int default_Precio = 200;

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

	private void initialize(){
		iniciarFrame();
		iniciarLabels();
		iniciarTxtFields();
}

	private void iniciarFrame(){

		frame = new JFrame();
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 640, 480);
		frame.setResizable(false);
		frame.setBounds(380, 180, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnStart = new JButton("Comenzar");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				iniciarSimulacion();
			}
		});

		btnStart.setBounds(311, 386, 89, 23);
		frame.getContentPane().add(btnStart);
		frame.setTitle("Simulacion Album Figuritas 2022");
}


	private void iniciarLabels(){
		
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
	
	JLabel lblCantSimulaciones = new JLabel("Cantidad de simulaciones a realizar (Por Thread):");
	lblCantSimulaciones.setBounds(46, 303, 281, 14);
	lblCantSimulaciones.setFont(new Font("Inconsolata",Font.PLAIN ,14));
	frame.getContentPane().add(lblCantSimulaciones);
}

	private void iniciarTxtFields() {

	//Field cantidad total de figuritas
	field_CantTotalFigus = new JTextField();
	field_CantTotalFigus.addKeyListener(new KeyAdapter(){
		
		@Override
		public void keyTyped(KeyEvent e){

		char c = e.getKeyChar();
		
			if(Character.isDigit(c)){			
			cantTotalFigus = cantTotalFigus + c;
		}else{
			
			JLabel lblErrorIngeso_CantTotal = new JLabel("Ingrese solo numeros!");
			lblErrorIngeso_CantTotal.setBounds(46, 143, 200, 14);
			lblErrorIngeso_CantTotal.setFont(new Font("Inconsolata", Font.ITALIC, 11));
			lblErrorIngeso_CantTotal.setForeground(Color.RED);
			layeredPane.add(lblErrorIngeso_CantTotal);

			}
		}
	});
		
	field_CantTotalFigus.setBounds(46, 121, 281, 20);
	field_CantTotalFigus.setColumns(10);
	layeredPane.add(field_CantTotalFigus, Integer.valueOf(1));

	//Field cantidad de figurita en paquete
	field_CantFigusPaq = new JTextField();
	field_CantFigusPaq.addKeyListener(new KeyAdapter() {
			@Override
		public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();
				
			if(Character.isDigit(c)) {				
				cantFigusPaq = cantFigusPaq + c;
			}else{
				
			JLabel lblErrorIngreso_CantFigusPaq = new JLabel("Ingrese solo numeros!");
			lblErrorIngreso_CantFigusPaq.setBounds(46, 212, 200, 14);
			lblErrorIngreso_CantFigusPaq.setFont(new Font("Inconsolata", Font.ITALIC, 11));
			lblErrorIngreso_CantFigusPaq.setForeground(Color.RED);
			layeredPane.add(lblErrorIngreso_CantFigusPaq);
			}
		}
	});

		field_CantFigusPaq.setBounds(46, 190, 281, 20);
		field_CantFigusPaq.setColumns(10);
		layeredPane.add(field_CantFigusPaq, Integer.valueOf(1));
		
		//Field precio
		field_Precio = new JTextField();
		field_Precio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
		if(Character.isDigit(c)){	
			precioPaq = precioPaq + c;
		}else{
			JLabel lblErrorIngresoPrecio = new JLabel("Ingrese solo numeros!");
			lblErrorIngresoPrecio.setBounds(46, 280, 200, 14);
			lblErrorIngresoPrecio.setFont(new Font("Inconsolata", Font.ITALIC, 11));
			lblErrorIngresoPrecio.setForeground(Color.RED);
			layeredPane.add(lblErrorIngresoPrecio);
			}
		}
	});
	
	field_Precio.setBounds(46, 259, 281, 20);
	field_Precio.setColumns(10);
	layeredPane.add(field_Precio, Integer.valueOf(1));
	
	//Field Simulaciones
	field_CantSims = new JTextField();
	field_CantSims.addKeyListener(new KeyAdapter() {
		@Override
		public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();
			
			if(Character.isDigit(c)) {					
				cantSims = cantSims + c;
			}else{
				JLabel lblErrorIngresoCantSims = new JLabel("Ingrese solo numeros!");
				lblErrorIngresoCantSims.setBounds(46, 350, 200, 14);
				lblErrorIngresoCantSims.setFont(new Font("Inconsolata", Font.ITALIC, 11));
				lblErrorIngresoCantSims.setForeground(Color.RED);
				layeredPane.add(lblErrorIngresoCantSims);
			}
		}
	});

	field_CantSims.setBounds(46, 328, 281, 20);
	field_CantSims.setColumns(10);
	layeredPane.add(field_CantSims, Integer.valueOf(1));
	
	iniciarLblsDefault();
	//default_CantFigus.setEnabled(false);
	JCheckBox chkDefaultCantFigus = new JCheckBox("Valor por defecto");
	chkDefaultCantFigus.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(chkDefaultCantFigus.isSelected()){			
				cantTotalFigus = default_CantFigus;
				field_CantTotalFigus.setEnabled(false);	
				defaultLbl_CantFigus.setVisible(true);	
				field_CantTotalFigus.setText("");
				frame.revalidate();
				frame.repaint();
				}else{
				field_CantTotalFigus.setEnabled(true);
				defaultLbl_CantFigus.setVisible(false);
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
				cantFigusPaq = default_CantFigusPaq;
				field_CantFigusPaq.setEnabled(false);
				field_CantFigusPaq.setText("");
				defaultLbl_CantFigusPaq.setVisible(true);
				
				frame.revalidate();
				frame.repaint();
				
			}
			
			else {
				
				field_CantFigusPaq.setEnabled(true);
				defaultLbl_CantFigusPaq.setVisible(false);
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
				precioPaq = default_Precio;
				field_Precio.setEnabled(false);
				field_Precio.setText("");
				defaultLbl_Precio.setVisible(true);
			}
			else {
				field_Precio.setEnabled(true);
				defaultLbl_Precio.setVisible(false);
				
			}
			
			
			}
		});
		chkDefaultPrecio.setBounds(365, 258, 166, 23);
		frame.getContentPane().add(chkDefaultPrecio);

		
		
		
		
		
		frame.getContentPane().add(layeredPane);
		

		
		
	
	}
	
	private void iniciarSimulacion() {
		
		
	if(this.cantTotalFigus <=0) {
		JOptionPane.showMessageDialog(null, "Ingrese una cantidad total de figuritas valida (Mayor a 0)", "Error", JOptionPane.ERROR_MESSAGE);	
	}
		
		
	if(this.cantFigusPaq <=0) {
		JOptionPane.showMessageDialog(null, "Ingrese una cantidad de figuritas por paquete valida (Mayor a 0)", "Error", JOptionPane.ERROR_MESSAGE);

	}
		
	if(this.precioPaq <= 0) {
		JOptionPane.showMessageDialog(null, "Ingrese un precio valido (Mayor a 0)", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	if(this.cantSims <= 0) {
		JOptionPane.showMessageDialog(null, "Ingrese una cantidad de simulaciones valida (Mayor a 0)", "Error", JOptionPane.ERROR_MESSAGE);
	}		
	
}
	
	
	private void iniciarLblsDefault() {
	
		defaultLbl_CantFigus = new JLabel("630");
		defaultLbl_CantFigus.setBounds(46,121,281,20);
		defaultLbl_CantFigus.setVisible(false);
		layeredPane.add(defaultLbl_CantFigus, Integer.valueOf(2));
		
		defaultLbl_CantFigusPaq = new JLabel("5");
		defaultLbl_CantFigusPaq.setBounds(46, 190, 281, 20);
		defaultLbl_CantFigusPaq.setVisible(false);
		layeredPane.add(defaultLbl_CantFigusPaq, Integer.valueOf(2));
		
		defaultLbl_Precio = new JLabel("200");
		defaultLbl_Precio.setBounds(46, 259, 281, 20);
		defaultLbl_Precio.setVisible(false);
		layeredPane.add(defaultLbl_Precio, Integer.valueOf(2));
	}
}

