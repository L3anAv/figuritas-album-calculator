package modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import interfaces.Generador;
import interfaces.Observador;
import interfaces.Simulacion;
import utilidades.GeneradorRandom;


public class SimulacionVariasPersonaRegalo implements Simulacion{
	
	private int gastoTotal;
	public StringBuilder sb;
	private int cantPersonas;
	private Generador random;
	private int precioPorPaquete;
	private int cantPaquetesTotal;
	private Observador observador;
	private int iteracionesGlobales;
	private ArrayList<Persona> personas;
	
	private int cantidadTotalFigusAlbum;
	private int cantidadFigusPaq;

	
	
	//Constructor con valores default(menos participantes y precio paquetes)
	public SimulacionVariasPersonaRegalo(int cantPersonas, int precioPorPaquete){
	this.cantPaquetesTotal = 0;
	this.sb = new StringBuilder();
	this.cantPersonas = cantPersonas;
	this.random = new GeneradorRandom();
	this.precioPorPaquete = precioPorPaquete;
	this.personas = new ArrayList<Persona>(cantPersonas);
	this.cantidadTotalFigusAlbum = 638;
	this.cantidadFigusPaq = 5;

	
	}	
	//Constructor con valores seteados por usuario
	public SimulacionVariasPersonaRegalo(int cantPersonas,
	int precioPorPaquete,  
	int cantidadFiguritasTotal, 
	int cantidadFiguritasPorPaquete){
	this.cantPaquetesTotal = 0;
	this.sb = new StringBuilder();
	this.cantPersonas = cantPersonas;
	this.random = new GeneradorRandom();
	this.precioPorPaquete = precioPorPaquete;
	this.personas = new ArrayList<Persona>(cantPersonas);
	
	this.cantidadFigusPaq = cantidadFiguritasPorPaquete;
	this.cantidadTotalFigusAlbum = cantidadFiguritasTotal;
	

//	nuevaConfig(personas, cantidadFiguritasTotal, cantidadFiguritasPorPaquete);
//	PaqueteFiguritasNormal.setCantidadFiguritasTotales(cantidadFiguritasTotal);
//	PaqueteFiguritasNormal.setCantidadFiguritasPaquete(cantidadFiguritasPorPaquete);
}
	
	@Override //Deberia retornar la cantidad de paquetes el gasto mejor dicho
	public int iniciarSimulacion() throws Exception{

	int iteraciones = 0;
	this.iteracionesGlobales = 0;
	generarIndividuos();

	while(!satisfactorio()){
		rellenarAlbumsDeTodos();
		compartirRepetidas();
		notificarObservadores();
		iteraciones++;
		this.iteracionesGlobales = iteraciones;
	}
	crearLog();
	gastoTotal = cantPaquetesTotal * precioPorPaquete;
	return gastoTotal;
}
	
	public void registrarObservador(Observador obs){
		this.observador = obs;
}
	
	public void generarIndividuos(){
	for(int i = 0 ; i <= cantPersonas; i++){
		Persona persona = new Persona(i+1);
		persona.setAlbum(cantidadTotalFigusAlbum, cantidadFigusPaq);
		personas.add(persona);

	}
}

	protected void rellenarAlbumsDeTodos(){
	int iteraciones = 0;
	for(Persona p : personas){
		if (!p.albumEstaCompleto() && iteraciones < 15){
			rellenarAlbum(PaqueteFiguritasNormal.nuevo(cantidadTotalFigusAlbum, cantidadFigusPaq).getPaqueteFiguritas(), p);
			iteraciones++;
			cantPaquetesTotal++;
		}
	}
}

	private void rellenarAlbum(LinkedList<Integer> paquete, Persona p){
		for(int i = 0; i < paquete.size();i++) {
			if(!p.albumEstaCompleto()){
				try { p.insertarFiguritaEnAlbum(paquete.get(i)); }
				catch ( Exception e) {e.printStackTrace(); }
		}
	}
}
	
	public boolean satisfactorio(){
	boolean aux = true;
	for(Persona p: personas){	
		aux = aux && p.albumEstaCompleto();
	}
	return aux;
}
	
	private void compartirRepetidas() throws Exception{	
	for(Persona p: personas){
		Persona p2 = personas.get(random.nextIntCExclusion(personas.size(), p.getId()));
		if (p.hayRepetidas()){
			p.regalarFiguritas(p2);
		}
	}
}

	public void nuevaConfig(ArrayList<Persona> personas, int cantidadFiguritasTotal, int cantidadFiguritasPorPaquete){
	for(Persona persona: personas){
		persona.getAlbum().setCantidadFiguritasTotales(cantidadFiguritasTotal);
		persona.getAlbum().setCantidadFiguritasPorPaquete(cantidadFiguritasPorPaquete);
	}
}

	//Getters
	public int getIteracion() {
		return this.iteracionesGlobales;
}

	@Override
	public ArrayList<Persona> getPersonas(){
		return this.personas;
}

	@Override
	public int getPaquetesAbiertos(){
		return cantPaquetesTotal;
}

	@Override
	public void escribirLog(){	

	this.sb.append("It: ").append(getIteracion())
	.append("Paquetes abiertos: ").append(getPaquetesAbiertos())
	.append(" \n");

	for(Persona p: personas)
	{ this.sb.append(p.toString()).append(" \n"); }
}

	@Override
	public void crearLog(){
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt"))){
			writer.write(this.sb.toString());
		}catch (IOException e){
			e.printStackTrace();
	}
}

	private void notificarObservadores(){
		observador.notificar();
}

	@Override
	public double promedioPaquetesXPersona() 
	{ return cantPaquetesTotal/personas.size(); }

}
