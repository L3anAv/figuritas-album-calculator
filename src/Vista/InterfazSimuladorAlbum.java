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


public class InterfazSimuladorAlbum {

	private JFrame frame;
	private JTextField textField;

	public static void main(String[] args){
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
	{ initialize(); }

	@SuppressWarnings({"rawtypes", "unchecked"})
	private void initialize(){
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
	
	//Campo de texto para personas
	textField = new JTextField();
	textField.setBounds(22, 156, 343, 31);
	textField.setEnabled(false);
	textField.setColumns(10);
	panel.add(textField);
	 
	//Label con indicacion para el textField
	JLabel lblCantidadDePersonas = new JLabel(" Campo no solicitado ");
	lblCantidadDePersonas.setBounds(22, 123, 343, 25);
	panel.add(lblCantidadDePersonas);
	lblCantidadDePersonas.setFont(new Font("Inconsolata", Font.ITALIC, 20));
	lblCantidadDePersonas.setForeground(new Color(224, 27, 36));
	
	JButton botonInicio = new JButton("INICIAR");
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
}
