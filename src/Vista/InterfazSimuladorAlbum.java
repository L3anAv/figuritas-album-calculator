package Vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.JLabel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InterfazSimuladorAlbum {

	private JFrame frame;
	private JTextField textField;
	private JLabel lblErrorIngresoCantSims;
	private String seleccionado;
	private int cantPersonas;
	public static void main(String[] args) 
	{
	EventQueue.invokeLater(new Runnable(){
	public void run(){
	try 
	{
	UIManager.setLookAndFeel(new FlatDarkLaf());
	InterfazSimuladorAlbum window = new InterfazSimuladorAlbum();
	window.frame.setVisible(true);
	}
	catch (Exception e)
	{
	e.printStackTrace();
	}}});
	}

	public InterfazSimuladorAlbum()
	{initialize();}

	@SuppressWarnings({"rawtypes", "unchecked"})
	private void initialize()
	{
	String[] opcionesDeSimulacion = {"Simulacion una sola persona", "Simulacion varias personas con regalo",
	"Simulacion de varias personas con intercambio"};
	
	//Ventana
	frame = new JFrame();
	frame.setResizable(false);
	frame.setBounds(380, 180, 500, 350);
	frame.getContentPane().setLayout(null);
	frame.setTitle("Simulacion Album Figuritas 2022");
	JPanel panel = new JPanel();
	panel.setLayout(null);
	panel.setBounds(60,12, 407, 299);
	crearCartelErrorIngreso();
	//Campo de texto para personas
	textField = new JTextField();
	frame.revalidate();
	frame.repaint();
	
	
	textField.addKeyListener(new KeyAdapter() {
		@Override
		public void keyTyped(KeyEvent e) {
		
			char c = e.getKeyChar();
			
			if(Character.isDigit(c)) {
				cantPersonas = cantPersonas + c;
			}
			else {
				lblErrorIngresoCantSims.setVisible(true);
			}

		}
	});
	textField.setBounds(22, 156, 343, 31);
	textField.setEnabled(false);
	textField.setColumns(10);
	panel.add(textField);
	
	//Label con indicacion para el textField
	JLabel lblCantidadDePersonas = new JLabel(" Campo no solicitado ");
	lblCantidadDePersonas.setBounds(12, 119, 343, 25);
	panel.add(lblCantidadDePersonas);
	lblCantidadDePersonas.setFont(new Font("Inconsolata", Font.ITALIC, 20));
	lblCantidadDePersonas.setForeground(new Color(224, 27, 36));
	
	JButton botonInicio = new JButton("INICIAR");
	botonInicio.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
			iniciarPantallaSettings();
		}
	});
	botonInicio.setBounds(145, 246, 106, 41);
	panel.add(botonInicio);
	botonInicio.setFocusPainted(false);
	botonInicio.setFont(new Font("Inconsolata", Font.PLAIN, 14));
	botonInicio.setForeground(new Color(255, 255, 255));
	botonInicio.setBackground(new Color(36, 31, 49));

	//Opciones de simulacion
	JComboBox SeleccionDeSimulacion = new JComboBox(opcionesDeSimulacion);
	SeleccionDeSimulacion.setBounds(22, 47, 343, 38);

	SeleccionDeSimulacion.setFont(new Font("Inconsolata", Font.PLAIN, 14));
	SeleccionDeSimulacion.setFocusable(false);
	
	SeleccionDeSimulacion.addItemListener(new ItemListener() {
	public void itemStateChanged(ItemEvent arg0){
	String seleccionado = SeleccionDeSimulacion.getSelectedItem().toString();
	setSeleccion(seleccionado);
	frame.setTitle(seleccionado);
	if(seleccionado.equals("Simulacion una sola persona")){
	textField.setEnabled(false);
	lblCantidadDePersonas.setText(" Campo no solicitado ");
	lblCantidadDePersonas.setFont(new Font("Inconsolata",Font.ITALIC ,20));
	lblCantidadDePersonas.setForeground(new Color(224, 27, 36));
	} else {
	textField.setEnabled(true);
	lblCantidadDePersonas.setText("Cantidad de participantes: ");
	lblCantidadDePersonas.setFont(new Font("Inconsolata",Font.ITALIC ,20));
	lblCantidadDePersonas.setForeground(Color.WHITE);
	}}});

	panel.add(SeleccionDeSimulacion);
	frame.getContentPane().add(panel);
	
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setSeleccion(String seleccion) {
		this.seleccionado = seleccion;
	}

	private void crearCartelErrorIngreso() {
		
		lblErrorIngresoCantSims = new JLabel("Ingrese solo numeros!");
		lblErrorIngresoCantSims.setBounds(80, 205, 200, 19);
		lblErrorIngresoCantSims.setFont(new Font("Inconsolata", Font.ITALIC, 11));
		lblErrorIngresoCantSims.setForeground(Color.RED);
		lblErrorIngresoCantSims.setVisible(false);
		frame.getContentPane().add(lblErrorIngresoCantSims);
		
		frame.revalidate();
		frame.repaint();
		
		
		
		
	}
	
	private void iniciarPantallaSettings() {
		
		
		
		
		
		InterfazSettingSimulacion avanzar = new InterfazSettingSimulacion();
		avanzar.visualizarPantalla();
		
		
	}


}


