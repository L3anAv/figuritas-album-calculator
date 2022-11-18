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

	
//public SimulacionVariasPersonasIntercambio(int cantPersonas, 
//int precioPorPaquete){
//	this.precioPorPaquete = precioPorPaquete;
//	this.personas = new ArrayList<Persona>(cantPersonas);
//	this.cantPersonas = cantPersonas;
//	this.cantPaquetesTotal = 0;
//	this.random = new GeneradorRandom();
//	this.sb = new StringBuilder();
//	this.cantidadTotalFigusAlbum = 638;
//	this.cantidadFigusPaq = 5;
//}

public SimulacionVariasPersonasIntercambio(int cantPersonas, 
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

@Override
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

@Override
public int iniciarSimulacion() throws Exception {
	int iteraciones = 0;
	this.iteracionesGlobales = 0;
	//generarIndividuos();
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

public void nuevaConfig(ArrayList<Persona> personas, int cantidadFiguritasTotal, int cantidadFiguritasPorPaquete){
	for(Persona persona: personas){
		persona.getAlbum().setCantidadFiguritasTotales(cantidadFiguritasTotal);
		persona.getAlbum().setCantidadFiguritasPorPaquete(cantidadFiguritasPorPaquete);
	}
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
	try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt"))){
		writer.write(this.sb.toString());
	}catch (IOException e){
		e.printStackTrace();
}
}

@Override
public double promedioPaquetesXPersona() 
{ return cantPaquetesTotal/personas.size(); }


private void notificarObservadores() {
	observador.notificar();
}
}


