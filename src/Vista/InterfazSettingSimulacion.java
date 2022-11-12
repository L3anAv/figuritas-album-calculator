package Vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.im.InputContext;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

import interfaces.Observador;
import interfaces.Simulacion;
import modelo.FabricaDeSimulaciones;
import modelo.SimulacionUnaPersona;
import utilidades.ObservadorPorConsola;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InterfazSettingSimulacion {

	private JFrame frame;
	private Simulacion sim;
	private JLayeredPane layeredPane;
	
	// Text Fields Pantalla
	private JTextField textFieldPersonas;
	private JTextField field_Precio;
	private JTextField field_CantSims;
	private JTextField field_CantFigusPaq;
	private JTextField field_CantTotalFigus;
	
	// Labels de Valores default
	private JLabel defaultLbl_Precio;
	private JLabel erroMsgCantPersonas;
	private JLabel defaultLbl_CantFigus;
	private JLabel defaultLbl_CantFigusPaq;
	
	// Jpanels
	private JPanel pantallaInicial;
	private JPanel pantallaConfiguracion;
	
	// Variables
	private int cantSims;
	private int precioPaq;
	private int cantFigusPaq;
	private int cantTotalFigus;
	private int simulacionElegida;
	private int cantPesonasParaSimulacion;
	
	// Variables default
	
	private final String default_Precio_text = "150";
	private final String default_CantFigus_text = "638";
	private final String default_CantFigusPaq_text = "5";
	private String[] opcionesDeSimulacion = {
			"Sin Seleccion",
			"Simulacion una sola persona", 
			"Simulacion varias personas con regalo",
			"Simulacion de varias personas con intercambio"};
	
// >  Launch the application.
	public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	public void run() {
		try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
			InterfazSettingSimulacion window = new InterfazSettingSimulacion(new SimulacionUnaPersona(0));
			window.frame.setVisible(true);
		}catch(Exception e) {
			e.printStackTrace();
			}
		}
	});
}

// > Create the application.
	public InterfazSettingSimulacion(Simulacion sim) {
		this.sim = sim;
		initialize();
}

// > Inicializacion de todo.
	private void initialize(){
		iniciarFrame();
}
	
// > Metodo que incia el frame principal.
	private void iniciarFrame(){

//JFrame
	frame = new JFrame();
	
// > JPanels
	pantallaInicial = new JPanel();
	pantallaConfiguracion = new JPanel();
	pantallaConfiguracion.setVisible(false);
	
	// JPanel -> PANTALLA DE INICIO:
	
		pantallaInicial.setBounds(120, 90, 500, 500);
		pantallaInicial.setLayout(null);
		
		// Label Inicial: No seleccionaste la simulacion.
		JLabel labelSolicitudDeCantPersonas = new JLabel("No seleccionaste una simulacion.");
		labelSolicitudDeCantPersonas.setFont(new Font("Inconsolata", Font.BOLD, 20));
		labelSolicitudDeCantPersonas.setForeground(new Color(224, 27, 36));
		labelSolicitudDeCantPersonas.setBounds(20, 85, 343, 25);
		
		// Boton para Configuracion -> Agregar a pantalla inicio.
		JButton botonInicio = crearBotonIrConfiguracion(pantallaInicial, pantallaConfiguracion);
		botonInicio.setEnabled(false);
		
		// Text Field para ingreso de cantidad de personas -> Agregar a pantalla inicio.
		textFieldPersonas = crearTextFieldParaCantidadPersona(botonInicio);
		textFieldPersonas.setBounds(20, 120, 343, 35);
		textFieldPersonas.setEnabled(false);
		
		// JLabelErrorMsg
		erroMsgCantPersonas = crearLabelErroCantPersonas();
		erroMsgCantPersonas.setBounds(20, 152, 343, 35);
		erroMsgCantPersonas.setVisible(false);
		
		// JComboBox -> Agregar seleccionador a pantalla de inicio.
		JComboBox SeleccionDeSimulacion = crearSelectorDeSimulacion(botonInicio, textFieldPersonas, labelSolicitudDeCantPersonas);
		
		
		pantallaInicial.add(botonInicio);
		pantallaInicial.add(textFieldPersonas);
		pantallaInicial.add(erroMsgCantPersonas);
		pantallaInicial.add(SeleccionDeSimulacion);
		pantallaInicial.add(labelSolicitudDeCantPersonas);
		frame.getContentPane().add(pantallaInicial);
				
//                --------------
		
	// JPanel -> PANTALLA DE CONFIGURACION:
		pantallaConfiguracion.setBounds(0,0, 640, 500);
		pantallaConfiguracion.setLayout(null);
		
		//Boton para panel -> Agregar boton para volver a atras.
		JButton botonIrDetras = crearBotonIrAtras(pantallaInicial, pantallaConfiguracion);
		botonIrDetras.setBounds(10, 10, 29, 25);
		pantallaConfiguracion.add(botonIrDetras);
		
		//JLlabels para panel -> Agregando a panel config.
		LinkedList<JLabel> labelsParaConfig = crearLabelsParaConfig();
		insertarComponentsEnPanel(labelsParaConfig, pantallaConfiguracion);
		//insertarLabelsEnPanel(labelsParaConfig, pantallaConfiguracion);
		
		//JTextFields para panel -> Agregando a panel config.
		LinkedList<JTextField> TextFieldsParaConfig = crearTextFieldsParaConfig();
		insertarComponentsEnPanel(TextFieldsParaConfig, pantallaConfiguracion);
		//insertarTextFieldsEnPanel(TextFieldsParaConfig, pantallaConfiguracion);
		
		//JCheckBox para panel -> Agregando a panel config.
		LinkedList<JCheckBox> checkBoxParaConfig = crearcheckBoxParaConfig();
		insertarComponentsEnPanel(checkBoxParaConfig, pantallaConfiguracion);
		
		//Boton para panel -> Agregandolo a panel config.
		JButton botonComenzar = crearBotonComenzarSimulacion();
		botonComenzar.setBounds(250, 410, 110, 45);
		pantallaConfiguracion.add(botonComenzar);
		
		frame.getContentPane().add(pantallaConfiguracion);

	frame.setResizable(false);
	frame.setBounds(380, 180, 640, 500);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	frame.setTitle("Simulacion Album Figuritas 2022");
}

// > Metodo que crea un boton para el panel de inicio.
	private JButton crearBotonIrConfiguracion(JPanel pantallaInicial, JPanel pantallaConfig){
		JButton BotonIrConfiguracion = new JButton("Continuar a configuracion >> ");
		BotonIrConfiguracion.setBounds(53, 195, 280, 41);
		BotonIrConfiguracion.setFocusPainted(false);
		BotonIrConfiguracion.setFont(new Font("Inconsolata", Font.PLAIN, 13));
		BotonIrConfiguracion.setForeground(new Color(255, 255, 255));
		BotonIrConfiguracion.setBackground(new Color(36, 31, 49));
		BotonIrConfiguracion.setEnabled(false);
		
		BotonIrConfiguracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(textFieldPersonas.getText().length() != 0) {
					pantallaConfig.setVisible(true);
					pantallaInicial.setVisible(false);
					
				} else if(textFieldPersonas.getText().length() < 0 || textFieldPersonas.getText().length() == 0) {
					erroMsgCantPersonas.setVisible(true);
				}
			}
		});
		
		return BotonIrConfiguracion;
	}
	
// > Metodo que crea un JComboBox para el panel de inicio (Seleccion de tipo de simulacion).
	private JComboBox crearSelectorDeSimulacion(JButton botonInicio, JTextField textFieldCantidadPersonas, JLabel labelSolicitudDeCantPersonas ) {
		
		JComboBox SeleccionDeSimulacion = new JComboBox(opcionesDeSimulacion);
		
		SeleccionDeSimulacion.setBounds(15, 10, 349, 38);
		SeleccionDeSimulacion.setFont(new Font("Inconsolata", Font.PLAIN, 14));
		SeleccionDeSimulacion.setFocusable(false);
	
		SeleccionDeSimulacion.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent arg0){
				if(SeleccionDeSimulacion.getSelectedIndex() == 0){
					botonInicio.setEnabled(false);
					textFieldCantidadPersonas.setEnabled(false);
					labelSolicitudDeCantPersonas.setText("No seleccionaste una simulacion.");
					labelSolicitudDeCantPersonas.setFont(new Font("Inconsolata", Font.BOLD, 20));
					labelSolicitudDeCantPersonas.setForeground(new Color(224, 27, 36));
					simulacionElegida = -1;
				}
				else if(SeleccionDeSimulacion.getSelectedIndex() == 1){
					botonInicio.setEnabled(false);
					textFieldCantidadPersonas.setEnabled(false);
					labelSolicitudDeCantPersonas.setText("Campo no solicitado ");
					labelSolicitudDeCantPersonas.setFont(new Font("Inconsolata", Font.BOLD, 25));
					labelSolicitudDeCantPersonas.setForeground(new Color(224, 27, 36));
					simulacionElegida = 1;
				}
				else{
					botonInicio.setEnabled(false);
					simulacionElegida = SeleccionDeSimulacion.getSelectedIndex();
					textFieldCantidadPersonas.setEnabled(true);
					labelSolicitudDeCantPersonas.setText("Cantidad de participantes: ");
					labelSolicitudDeCantPersonas.setFont(new Font("Inconsolata",Font.PLAIN ,20));
					labelSolicitudDeCantPersonas.setForeground(Color.WHITE);
				}
				
				//System.out.print(simulacionElegida);
		}
	});
		
		return SeleccionDeSimulacion;
	}
	
// > Metodo que crea un boton para el panel de configuracion.
	private JButton crearBotonComenzarSimulacion(){
	JButton btnStart = new JButton("Comenzar");	
	btnStart.setFocusPainted(false);
	btnStart.setFont(new Font("Inconsolata", Font.PLAIN, 14));
	btnStart.setForeground(new Color(255, 255, 255));
	btnStart.setBackground(new Color(36, 31, 49));
	btnStart.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			iniciarSimulacion();
		}
	});
	return btnStart;
}

	private JButton crearBotonIrAtras(JPanel pantallaInicial, JPanel pantallaConfig){
	JButton botonIrAtras = new JButton("<");	
	botonIrAtras.setFocusPainted(false);
	botonIrAtras.setFont(new Font("Inconsolata", Font.PLAIN, 14));
	botonIrAtras.setForeground(new Color(255, 255, 255));
	botonIrAtras.setBackground(new Color(36, 31, 49));
	botonIrAtras.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
		pantallaInicial.setVisible(true);
		pantallaConfig.setVisible(false);
		}
	});
	return botonIrAtras;
		
}
	
// > Metodo que crea los labels que indican que poner en cada textField para la simulacion.
	private LinkedList<JLabel> crearLabelsParaConfig(){

	LinkedList<JLabel> labelsPanelConfig = new LinkedList<JLabel>();
		
	JLabel lblTitulo = new JLabel("Configuraciones para Simulacion");
	lblTitulo.setBounds(46, 25, 350, 43);
	lblTitulo.setFont(new Font("Inconsolata",Font.BOLD ,20));
	labelsPanelConfig.add(lblTitulo);
	
	JLabel lblCantTotalFigus = new JLabel("Cantidad total de figuritas:");
	lblCantTotalFigus.setBounds(46, 80, 232, 14);
	lblCantTotalFigus.setFont(new Font("Inconsolata",Font.PLAIN ,14));
	labelsPanelConfig.add(lblCantTotalFigus);

	JLabel lblFigusEnPaquete = new JLabel("Cantidad de figuritas por paquete: ");
	lblFigusEnPaquete.setBounds(46, 165, 320, 14);
	lblFigusEnPaquete.setFont(new Font("Inconsolata",Font.PLAIN ,14));
	labelsPanelConfig.add(lblFigusEnPaquete);

	JLabel lblPrecioPaq = new JLabel("Precio por paquete:");
	lblPrecioPaq.setBounds(46, 244, 174, 14);
	lblPrecioPaq.setFont(new Font("Inconsolata",Font.PLAIN ,14));
	labelsPanelConfig.add(lblPrecioPaq);

	JLabel lblCantSimulaciones = new JLabel("Cantidad de simulaciones a realizar:");
	lblCantSimulaciones.setBounds(46, 325, 320, 14);
	lblCantSimulaciones.setFont(new Font("Inconsolata",Font.PLAIN ,14));
	labelsPanelConfig.add(lblCantSimulaciones);

	return labelsPanelConfig;
}

// > Metodo que crea los campos de texto para ingresar valores para la simulacion.
	private LinkedList<JTextField> crearTextFieldsParaConfig(){
		
	LinkedList<JTextField> textFieldsParaConfig = new LinkedList<JTextField>();
	
	// > Text Cantidad Total De Figuritas (Tomar el valor cantidad de figuritas).
	field_CantTotalFigus = new JTextField();
	field_CantTotalFigus.addKeyListener(new KeyAdapter(){		
		@Override
		public void keyTyped(KeyEvent e) {
			if(!((int) e.getKeyChar() > 47 && (int) e.getKeyChar() < 58)){
				e.consume();
			}else if(field_CantTotalFigus.getText().length() < 0){
				cantTotalFigus = -1;
			}else{
				String cantTotalFigusText = "" + e.getKeyChar();
				cantTotalFigus = Integer.parseInt(cantTotalFigusText);
				//System.out.print(cantTotalFigus);
			}
		}
	});

	field_CantTotalFigus.setBounds(46, 105, 290, 35);
	field_CantTotalFigus.setColumns(10);
	textFieldsParaConfig.add(field_CantTotalFigus);

	// > Text Cantidad de Figuritas por paquete (Toma el valor de Cantidad de Figuritas por paquete).
	field_CantFigusPaq = new JTextField();
	field_CantFigusPaq.addKeyListener(new KeyAdapter() {
		@Override
		public void keyTyped(KeyEvent e) {

			if(!((int) e.getKeyChar() > 47 && (int) e.getKeyChar() < 58)){
				e.consume();
			}else if(field_CantFigusPaq.getText().length() < 0){
				cantFigusPaq = -1;
			}else{
				String cantFigusPaqText = "" + e.getKeyChar();
				cantFigusPaq = Integer.parseInt(cantFigusPaqText);
				//System.out.print(cantFigusPaqText);
			}
		}
	});

	field_CantFigusPaq.setBounds(46, 190, 290, 35);
	field_CantFigusPaq.setColumns(10);
	//layeredPane.add(field_CantFigusPaq, Integer.valueOf(1));
	textFieldsParaConfig.add(field_CantFigusPaq);
	
	// > Text Precio Por Paquete (Toma el precio por paquete de figuritas).
	field_Precio = new JTextField();
	field_Precio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				if(!((int) e.getKeyChar() > 47 && (int) e.getKeyChar() < 58)){
					e.consume();
				}else if(field_Precio.getText().length() < 0){
					precioPaq = -1;
				}else{
					String precioPaqText = "" + e.getKeyChar();
					precioPaq = Integer.parseInt(precioPaqText);
					//System.out.print(precioPaqText);
			}
		}
	});
	
	field_Precio.setBounds(46, 269, 290, 35);
	field_Precio.setColumns(10);
	textFieldsParaConfig.add(field_Precio);
	
	// > Text Cantidad de simulaciones (Toma la Cantidad de simulaciones).
	field_CantSims = new JTextField();
	field_CantSims.addKeyListener(new KeyAdapter() {
		@Override
		public void keyTyped(KeyEvent e){
			
			if(!((int) e.getKeyChar() > 47 && (int) e.getKeyChar() < 58)){
				e.consume();
			}else if(field_CantSims.getText().length() < 0){
				cantSims = -1;
			}else{
				String cantSimsText = "" + e.getKeyChar();
				cantSims = Integer.parseInt(cantSimsText);
				//System.out.print(cantSimsText);
			}
		}
	});

	field_CantSims.setBounds(46, 345, 290, 35);
	field_CantSims.setColumns(10);
	textFieldsParaConfig.add(field_CantSims);
	
	return textFieldsParaConfig;
}
	
	private JTextField crearTextFieldParaCantidadPersona(JButton botonInicio){
	JTextField textFieldCantidadPersona = new JTextField();

	textFieldCantidadPersona.setColumns(10);
	textFieldCantidadPersona.addKeyListener(new KeyAdapter(){		
		@Override
		public void keyTyped(KeyEvent e) {
			if(!((int) e.getKeyChar() > 47 && (int) e.getKeyChar() < 58)){
				e.consume();
			}else if(textFieldCantidadPersona.getText().length() >= 0){
				String cantPersonasSimulacion = "" + e.getKeyChar();
				cantPesonasParaSimulacion = Integer.parseInt(cantPersonasSimulacion);
				botonInicio.setEnabled(true);
				//System.out.print(cantPesonasParaSimulacion);
			}else if(textFieldCantidadPersona.getText().length() < 0){
				cantPesonasParaSimulacion = -1;
				botonInicio.setEnabled(false);
			}
		}
	});
	
	return textFieldCantidadPersona;
}
	
	private JLabel crearLabelErroCantPersonas() {
		JLabel errorMsg = new JLabel("Error: ingrese una cantidad de personas.");
		errorMsg.setFont(new Font("Inconsolata", Font.BOLD, 12));
		errorMsg.setForeground(new Color(224, 27, 36));
		
		return errorMsg;
}
	
	private LinkedList<JCheckBox> crearcheckBoxParaConfig(){
		LinkedList<JCheckBox> cheackBoxsParaConfig = new LinkedList<JCheckBox>();
		
	// > Checks Boxs para dar valores por default ---
		
		// Check 1: Default Cantidad De Figuritas
		JCheckBox chkDefaultCantFigus = new JCheckBox("Valor por defecto");
		chkDefaultCantFigus.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					
				if(chkDefaultCantFigus.isSelected()){
					cantTotalFigus = Integer.parseInt(default_CantFigus_text);
					field_CantTotalFigus.setEnabled(false);
					field_CantTotalFigus.setText(default_CantFigus_text);
				}
				else{
					field_CantTotalFigus.setEnabled(true);
					field_CantTotalFigus.setText("");
					frame.revalidate();
					frame.repaint();	
			}
		}
	});
		// Posicionamiento y otros.
		chkDefaultCantFigus.setBounds(365, 110, 185, 23);
		cheackBoxsParaConfig.add(chkDefaultCantFigus);
		
		// Check 2: Default Cantidad Figus Paquete.
		JCheckBox chkDefaultFigusPaq = new JCheckBox("Valor por defecto");
		chkDefaultFigusPaq.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			
				if(chkDefaultFigusPaq.isSelected()) {
					cantFigusPaq = Integer.parseInt(default_CantFigusPaq_text);
					field_CantFigusPaq.setEnabled(false);
					field_CantFigusPaq.setText(default_CantFigusPaq_text);
					frame.revalidate();
					frame.repaint();
				}
				else {
					field_CantFigusPaq.setEnabled(true);
					field_CantFigusPaq.setText("");
					frame.revalidate();
					frame.repaint();
			}
		}
	});
		// Posicionamiento y otros.
		chkDefaultFigusPaq.setBounds(365, 198, 152, 23);
		cheackBoxsParaConfig.add(chkDefaultFigusPaq);
		
		// Check 3: Defaul Precio Por Paquete.
		JCheckBox chkDefaultPrecio = new JCheckBox("Valor por defecto");
		chkDefaultPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(chkDefaultPrecio.isSelected()) {
					precioPaq = Integer.parseInt(default_Precio_text);
					field_Precio.setText(default_Precio_text);
					field_Precio.setEnabled(false);
				}
				else {
					field_Precio.setEnabled(true);
					field_Precio.setText("");
			}
		}
	});
		// Posicionamiento y otros.
		chkDefaultPrecio.setBounds(365, 268, 166, 23);
		cheackBoxsParaConfig.add(chkDefaultPrecio);
		
		return cheackBoxsParaConfig;
}
	
	private void insertarComponentsEnPanel(LinkedList<?> listDeComponentsParaPanel, JPanel pantallaConfiguracion){
		for(int i = 0; i < listDeComponentsParaPanel.size(); i++) {
			pantallaConfiguracion.add((Component) listDeComponentsParaPanel.get(i));
		}
	}
	
	
	private void setSimulacion() {
		
		if(this.simulacionElegida == 1) {
			
			this.sim = FabricaDeSimulaciones.getSimulacion(true, "unaPersona", precioPaq, cantTotalFigus, cantFigusPaq);

		}
		if(this.simulacionElegida == 2) {
			System.out.println("esta");
			this.sim = FabricaDeSimulaciones.getSimulacion(true, "nPersonasRegalo", precioPaq,cantPesonasParaSimulacion ,cantTotalFigus, cantFigusPaq);
			
		}
		
		if(this.simulacionElegida == 3) {
			this.sim = FabricaDeSimulaciones.getSimulacion(true, "nPersonasIntercambio" ,cantPesonasParaSimulacion, precioPaq, cantTotalFigus, cantFigusPaq);
			
		}
//		else {
//			this.sim = FabricaDeSimulaciones.getSimulacion(false, "unaPersona", 200, 638, 5);
//		}
		
	}
	
	
	
	
	
	private void iniciarSimulacion() {
	
	//Control de campos antes de iniciar simulaciones
	if(this.cantTotalFigus <= 0 || field_CantTotalFigus.getText().length() == 0) {
		JOptionPane.showMessageDialog(null, "Ingrese una cantidad total de figuritas valida (Mayor a 0)", "Error", JOptionPane.ERROR_MESSAGE);	
	}
	else if(this.cantFigusPaq <= 0 || field_CantFigusPaq.getText().length() == 0) {
		JOptionPane.showMessageDialog(null, "Ingrese una cantidad de figuritas por paquete valida (Mayor a 0)", "Error", JOptionPane.ERROR_MESSAGE);
	}
	else if(this.precioPaq <= 0 || field_Precio.getText().length() == 0) {
		JOptionPane.showMessageDialog(null, "Ingrese un precio valido (Mayor a 0)", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	else if(this.cantSims <= 0 || field_CantSims.getText().length() == 0) {
		JOptionPane.showMessageDialog(null, "Ingrese una cantidad de simulaciones valida (Mayor a 0)", "Error", JOptionPane.ERROR_MESSAGE);
	}
	else {
		setSimulacion();
		
		double cuenta =0;
		
		
		
		for(int i = 0; i < cantSims; i++) {
			
			sim.registrarObservador(new ObservadorPorConsola(sim));
			try {
				cuenta = sim.iniciarSimulacion();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		}
		
		
		System.out.println(cuenta);
	
	
	}
	
	
		
		
		
	
	
}

}

