package modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import interfaces.Generador;
import interfaces.Observador;
import interfaces.Simulacion;

public class SimulacionUnaPersona implements Simulacion{

	private Persona persona;
	private StringBuilder sb;
	private int iteracion;
	private int precioPorPaquete;
	private int cantPaquetes;
	private Observador observador;

	
	private Generador genPrefijado;
	
	
//	// > Constructor con valores default (menos precio por paquete)
//	public SimulacionUnaPersona(int precioPorPaquete){
//	persona = new Persona(1);
//	this.precioPorPaquete = precioPorPaquete;
//}

	// > Constructor con valores dados por el usuario
	public SimulacionUnaPersona(
	int precioPorPaquete, 
	int cantidadFiguritasTotal,
	int cantidadFiguritasPorPaquete){
	persona = new Persona(1);
	this.precioPorPaquete = precioPorPaquete;
	this.sb = new StringBuilder();
	
	if(precioPorPaquete < 0 ) {
		throw new IllegalArgumentException("El Valor del precio no puede ser negativo");
	}
	if(cantidadFiguritasPorPaquete < 1) {
		throw new IllegalArgumentException("La cantidad de Figuritas por paquete no puede ser menor a 1");
	}
	if(cantidadFiguritasTotal < 1) {
		throw new IllegalArgumentException("La cantidad total de figuritas no puede ser menor a 1");
	}
	
	cantPaquetes = 0;
	
	persona.getAlbum().setCantidadFiguritasTotales(cantidadFiguritasTotal);
	persona.getAlbum().setCantidadFiguritasPorPaquete(cantidadFiguritasPorPaquete);
	PaqueteFiguritasNormal.setCantidadFiguritasTotales(cantidadFiguritasTotal);
	PaqueteFiguritasNormal.setCantidadFiguritasPaquete(cantidadFiguritasPorPaquete);
}

	@Override
	public int iniciarSimulacion() throws Exception{
	//cantPaquetes = 1;
	while(!satisfactorio()){
		rellenarAlbum(PaqueteFiguritasNormal.nuevo().getPaqueteFiguritas());
		notificarObservadores();
		iteracion++;
		//cantPaquetes++;
	}
		crearLog();
		System.out.print("\n cantPaquetes*precioPorPaquete: " + cantPaquetes*precioPorPaquete);
		return (cantPaquetes*precioPorPaquete);
}

	@Override
	public void registrarObservador(Observador obs){
		this.observador = obs;
}

	public void rellenarAlbum(LinkedList<Integer> paquete) throws Exception{
	for(int i = 0; i < paquete.size();i++) 
		persona.insertarFiguritaEnAlbum(paquete.get(i));
	
	cantPaquetes++;
	}

	// > Getter & Setter
	@Override
	public int getIteracion() {
	return iteracion;
}

	@Override
	public ArrayList<Persona> getPersonas(){
		ArrayList<Persona> ret = new ArrayList<Persona>(1);
		ret.add(this.persona);
		return ret;
}

	@Override
	public int getPaquetesAbiertos(){
		return cantPaquetes;
}

	@Override
	public void escribirLog(){
	this.sb.append("It: " + getIteracion() + "Paquetes abiertos: " + getPaquetesAbiertos() + "\n");	
	this.sb.append(persona.toString()).append("\n");
}

	@Override
	public void crearLog(){
	try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt"))) {
		writer.write(this.sb.toString());
	}catch(IOException e){
			e.printStackTrace();
	}
}

	@Override
	public double promedioPaquetesXPersona(){
	return this.getPaquetesAbiertos();
}


	private void notificarObservadores(){
		observador.notificar();
}

	public Generador getGenPrefijado() {
		return genPrefijado;
	}

	public void setGenPrefijado(Generador genPrefijado) {
		this.genPrefijado = genPrefijado;
	}

	public boolean satisfactorio(){
		return true && persona.albumEstaCompleto();
	}
	public int getPrecioPaquete() {
		return this.precioPorPaquete;
	}
	
}
