package modelo;

import java.util.ArrayList;

import interfaces.Generador;
import interfaces.Observador;
import mochila.Instancia;

public class Album {

	//Las figuritas en el album
	private ArrayList<Figurita> figuritas;
	
	//Instancia asociada al album
	//private Instancia instancia;
	
	
	
	//Parametros de album
	private int maxCantFigus = 683;
	
	
	// Estadisticas
	private int iteracion;
		
	// Generador de numeros aleatorios
	private Generador random;
		
	// Observadores registrados
	private ArrayList<Observador> observadores;
		
	
	
	public Album(Instancia instancia, Generador generador){
		
		instancia = instancia;
		random = generador;
		observadores = new ArrayList<Observador>();
		
		
	}
}
