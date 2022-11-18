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

public class SimulacionVariasPersonasIntercambio implements Simulacion {

	private ArrayList<Persona> personas;
	private int precioPorPaquete;
	private int gastoTotal;
	private int cantPaquetesTotal;
	private int cantPersonas;
	private int iteracionesGlobales;
	public StringBuilder sb;
	private Generador random;
	private Observador observador;
	private int cantidadTotalFigusAlbum;
	private int cantidadFigusPaq;

// > Constructor de simulacion

	public SimulacionVariasPersonasIntercambio(int cantPersonas, int precioPorPaquete,  int cantidadFiguritasTotal, int cantidadFiguritasPorPaquete){

	if(cantPersonas<1) 
		throw new IllegalArgumentException("No pueden haber menos de 2 participantes");
	
	if(precioPorPaquete < 0 ) 
		throw new IllegalArgumentException("El Valor del precio no puede ser negativo");
	
	if(cantidadFiguritasPorPaquete < 1) 
		throw new IllegalArgumentException("La cantidad de Figuritas por paquete no puede ser menor a 1");
	
	if(cantidadFiguritasTotal < 1) 
		throw new IllegalArgumentException("La cantidad total de figuritas no puede ser menor a 1");

	this.cantPaquetesTotal = 0;
	this.sb = new StringBuilder();
	this.cantPersonas = cantPersonas;
	this.random = new GeneradorRandom();
	this.precioPorPaquete = precioPorPaquete;
	this.cantidadFigusPaq = cantidadFiguritasPorPaquete;
	this.personas = new ArrayList<Persona>(cantPersonas);
	this.cantidadTotalFigusAlbum = cantidadFiguritasTotal;
	
	generarIndividuos();
	nuevaConfig(personas, cantidadFiguritasTotal, cantidadFiguritasPorPaquete);
}
	
// > Metodos de clase

	@Override
	public int iniciarSimulacion() throws Exception {
		int iteraciones = 0;
		this.iteracionesGlobales = 0;

		while(!satisfactorio()) {
			rellenarAlbumsDeTodos();
			intercambiarRepetidas();
			notificarObservadores();
			iteraciones++;
			this.iteracionesGlobales = iteraciones;
		}
		crearLog();
		gastoTotal = cantPaquetesTotal * precioPorPaquete;
		return gastoTotal;
		
}

	public void generarIndividuos(){	
	for(int i = 0 ; i < cantPersonas; i++){
		Persona persona = new Persona(i+1);
		persona.setAlbum(cantidadTotalFigusAlbum, cantidadFigusPaq);
		personas.add(persona);
	}
}
	
	protected void rellenarAlbumsDeTodos() throws Exception {
	int iteraciones = 0;
	
	for(Persona p : personas) if (!p.albumEstaCompleto() && iteraciones < 15){	
		rellenarAlbum(PaqueteFiguritasNormal.nuevo(cantidadTotalFigusAlbum, cantidadFigusPaq).getPaqueteFiguritas(), p);
		cantPaquetesTotal++;
		iteraciones++;
	}
	
}

	private void rellenarAlbum(LinkedList<Integer> paquete, Persona p) throws Exception{
	for(int i = 0; i < paquete.size();i++) 
		if(!p.albumEstaCompleto()){
			p.insertarFiguritaEnAlbum(paquete.get(i));
		}
}
	
	private void intercambiarRepetidas() throws Exception{
		for(Persona p: personas){
			Persona p2 = personas.get(random.nextIntCExclusion(personas.size(), p.getId()));
			if (p.hayRepetidas() && p2.hayRepetidas()){
				p.intercambiarFiguritas(p2);
			}
	}
}

	public boolean satisfactorio() {
		boolean aux = true;
		for(Persona p: personas) {	
			aux = aux && p.albumEstaCompleto();	
		}
		return aux;
}

	public void nuevaConfig(ArrayList<Persona> personas, int cantidadFiguritasTotal, int cantidadFiguritasPorPaquete){
		for(Persona persona: personas){
			persona.getAlbum().setCantidadFiguritasTotales(cantidadFiguritasTotal);
			persona.getAlbum().setCantidadFiguritasPorPaquete(cantidadFiguritasPorPaquete);
		}
}

// > Metodos aux para Test
	
	public void rellenarAlbumes_Testing(LinkedList<Integer> paquete, Persona p) throws Exception {	
		for(int figu: paquete) {
			p.insertarFiguritaEnAlbum(figu);
		}
}

	public void intercambiarRepetidas_Testing(Persona p, Persona other) throws Exception {
		p.intercambiarFiguritas(other);
}

// > Getters && Setters

	@Override
	public int getIteracion() 
	{ return this.iteracionesGlobales; }

	@Override
	public ArrayList<Persona> getPersonas() 
	{ return personas; }

	@Override
	public int getPaquetesAbiertos() 
	{ return cantPaquetesTotal; }

	private void notificarObservadores() 
	{ observador.notificar(); }
	
	@Override
	public void registrarObservador(Observador obs)
	{ this.observador = obs; }

	@Override
	public double promedioPaquetesXPersona() 
	{ return cantPaquetesTotal/personas.size(); }
	
	@Override
	public void escribirLog() {
		this.sb.append("It: " + getIteracion() + "Paquetes abiertos: " + getPaquetesAbiertos() + "\n");
		for(Persona p: personas) {
			this.sb.append(p.toString()).append("\n");
		}
}

	@Override
	public void crearLog() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt"))){
			writer.write(this.sb.toString());
		}catch (IOException e){
			e.printStackTrace();
	}
}

}