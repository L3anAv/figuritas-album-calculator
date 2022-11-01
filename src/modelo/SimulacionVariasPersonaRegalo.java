package modelo;

import java.util.ArrayList;
import java.util.LinkedList;

import interfaces.Generador;
import interfaces.Observador;
import interfaces.Simulacion;
import util.GeneradorRandom;

public class SimulacionVariasPersonaRegalo implements Simulacion{
	
	//Variables
	private ArrayList<Persona> personas;
	private int valorFiguritas;
	private int gastoTotal;
	private int cantPaquetesTotal;
	private int cantPersonas;
	//Iteraciones
	private int iteracionesGlobales;
	
	
	
	//Generador Random
	private Generador random;
	
	//Observador
	private Observador observador;
	
	public SimulacionVariasPersonaRegalo(int cantPersonas, int valorFiguritas)
	{
		this.valorFiguritas = valorFiguritas;
		
		this.personas = new ArrayList<Persona>(cantPersonas);
		this.cantPersonas = cantPersonas;
		this.cantPaquetesTotal = 0;
		this.random = new GeneradorRandom();
		
		

	}
	
	public void registrarObservador(Observador obs) {
		
		this.observador = obs;
	}
	
	public void generarIndividuos() {
		
		for(int i = 0 ; i <= cantPersonas; i++) {
			
			Persona persona = new Persona(i);
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
	
	public boolean satisfactorio() {
		boolean aux = true;
		
		for(Persona p: personas) {
			
			aux = aux && p.albumEstaCompleto();
			
		}
		
		return aux;
	}
	
	
	private void compartirRepetidas() {
		
		for(Persona p: personas) if (p.hayRepetidas()) {
			p.RegalarFiguritas(personas.get(random.nextInt(personas.size())));
						
		}
		
	}
	
	@Override
	public int iniciarSimulacion() {
		
		int iteraciones = 0;
		this.iteracionesGlobales = 0;
		generarIndividuos();
	
		while(!satisfactorio()) {
			
			
			rellenarAlbumsDeTodos();
			compartirRepetidas();
			
			
			notificarObservadores();

			iteraciones++;
			this.iteracionesGlobales = iteraciones;
		}
		
		
		
		
		return iteraciones;
	}

	private void notificarObservadores() {
		
		observador.notificar();
		
	}
	public int getIteracion() {
		return this.iteracionesGlobales;
	}

	@Override
	public ArrayList<Persona> getPersonas() {
		
		return this.personas;
	}

	@Override
	public int getPaquetesAbiertos() {
		
		return cantPaquetesTotal;
	}
	
	
}
