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
	
	if(cantPersonas<1) {
		throw new IllegalArgumentException("No pueden haber menos de 2 participantes");
	}
	if(precioPorPaquete < 0 ) {
		throw new IllegalArgumentException("El Valor del precio no puede ser negativo");
	}
	if(cantidadFiguritasPorPaquete < 1) {
		throw new IllegalArgumentException("La cantidad de Figuritas por paquete no puede ser menor a 1");
	}
	if(cantidadFiguritasTotal < 1) {
		throw new IllegalArgumentException("La cantidad total de figuritas no puede ser menor a 1");
	}
	
	
	
	
	
	
	generarIndividuos();
	nuevaConfig(personas, cantidadFiguritasTotal, cantidadFiguritasPorPaquete);
	PaqueteFiguritasNormal.setCantidadFiguritasTotales(cantidadFiguritasTotal);
	PaqueteFiguritasNormal.setCantidadFiguritasPaquete(cantidadFiguritasPorPaquete);
}
	
	@Override //Deberia retornar la cantidad de paquetes el gasto mejor dicho
	public int iniciarSimulacion() throws Exception{

	int iteraciones = 0;
	this.iteracionesGlobales = 0;

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
	for(int i = 0 ; i < cantPersonas; i++){
		Persona persona = new Persona(i+1);
		persona.setAlbum(cantidadTotalFigusAlbum, cantidadFigusPaq);
		personas.add(persona);

	}
}

	protected void rellenarAlbumsDeTodos() throws Exception{
	int iteraciones = 0;
	for(Persona p : personas){
		if (!p.albumEstaCompleto() && iteraciones < 15){
			rellenarAlbum(PaqueteFiguritasNormal.nuevo(cantidadTotalFigusAlbum, cantidadFigusPaq).getPaqueteFiguritas(), p);
			iteraciones++;
			cantPaquetesTotal++;
		}
	}
}

	public void rellenarAlbum(LinkedList<Integer> paquete, Persona p) throws Exception{
		for(int i = 0; i < paquete.size();i++) {
			if(!p.albumEstaCompleto()){
					p.insertarFiguritaEnAlbum(paquete.get(i)); 

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
	
	public void compartirRepetidas() throws Exception{	
	for(Persona p: personas){
		Persona p2 = personas.get(random.nextIntCExclusion(personas.size(), p.getId()));
		if (p.hayRepetidas()){
			p.regalarFiguritas(p2);
		}
	}
}

	
	public void rellenarAlbumes_Testing(LinkedList<Integer> paquete, Persona p) throws Exception {	
		for(int figu: paquete) {
			p.insertarFiguritaEnAlbum(figu);
		}
	}
	
	public void compartirRepetidas_Testing(Persona p, Persona other) throws Exception {
		
		p.regalarFiguritas(other);
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

	@Override
	public double promedioPaquetesXPersona() 
	{ return cantPaquetesTotal/personas.size(); }

	
	private void notificarObservadores(){
		observador.notificar();
}

}
