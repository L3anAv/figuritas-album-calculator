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
		private int valorFiguritas;
		private int gastoTotal;
		private int cantPaquetesTotal;
		private int cantPersonas;
		private int iteracionesGlobales;
		public StringBuilder sb;
		private Generador random;
		private Observador observador;
		
	public SimulacionVariasPersonasIntercambio(int cantPersonas, int valorFiguritas){
		this.valorFiguritas = valorFiguritas;
		this.personas = new ArrayList<Persona>(cantPersonas);
		this.cantPersonas = cantPersonas;
		this.cantPaquetesTotal = 0;
		this.random = new GeneradorRandom();
		this.sb = new StringBuilder();
}

	
	
	@Override
	public void registrarObservador(Observador obs){
			this.observador = obs;
}
	
	public void generarIndividuos(){	
	for(int i = 0 ; i <= cantPersonas; i++){
		Persona persona = new Persona();
 		personas.add(persona);
	}
}
	
	protected void rellenarAlbumsDeTodos() {
		
		int iteraciones = 0;
	for(Persona p : personas) if (!p.albumEstaCompleto() && iteraciones < 15) {	
		
		rellenarAlbum(PaqueteFiguritasNormal.nuevo().getPaqueteFiguritas(), p);
		iteraciones++;
		}
	
	}
	
	private void rellenarAlbum(LinkedList<Integer> paquete, Persona p){
	
		for(int i = 0; i < paquete.size();i++) 
			if(!p.albumEstaCompleto()){
				p.insertarFiguritaEnAlbum(paquete.get(i));
				cantPaquetesTotal++;
			}
	}
	
	private void intercambiarRepetidas() {
		
		
	
	}
	
	public boolean satisfactorio() {
		boolean aux = true;
		
		for(Persona p: personas) {
			
			aux = aux && p.albumEstaCompleto();
			
		}
		
		return aux;
	}
	
	
	
	
	@Override
	public int iniciarSimulacion() {
		
		
		int iteraciones = 0;
		this.iteracionesGlobales = 0;
		generarIndividuos();
		while(!satisfactorio()) {
			
			
			rellenarAlbumsDeTodos();
			intercambiarRepetidas();
			
			
			notificarObservadores();
			
			try {
				Thread.sleep(40);
				escribirLog();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			iteraciones++;
			this.iteracionesGlobales = iteraciones;
		}
		
		crearLog();
		
		
		return iteraciones;
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Getters
	
	@Override
	public int getIteracion() {
		return this.iteracionesGlobales;
	}

	@Override
	public ArrayList<Persona> getPersonas() {
		
		return personas;
	}

	@Override
	public int getPaquetesAbiertos() {
		
		return cantPaquetesTotal;
	}

	
	//Herramientas
	@Override
	public void escribirLog() {
		this.sb.append("It: " + getIteracion() + "Paquetes abiertos: " + getPaquetesAbiertos() + "\n");
		
		for(Persona p: personas) {
			
			this.sb.append(p.toString()).append("\n");
		}
	}

	@Override
	public void crearLog() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt"));
			writer.write(this.sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void notificarObservadores() {
		
		observador.notificar();
		
	}
}
