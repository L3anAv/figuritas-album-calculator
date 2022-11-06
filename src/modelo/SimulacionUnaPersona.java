package modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import interfaces.Observador;
import interfaces.Simulacion;

public class SimulacionUnaPersona implements Simulacion{

	private int gastoTotal;
	private Persona persona;
	private StringBuilder sb;
	private int precioPorPaquete;
	private Observador observador;

	// > Constructor con valores default (menos precio por paquete)
	public SimulacionUnaPersona(int precioPorPaquete){
	persona = new Persona(1);
	this.precioPorPaquete = precioPorPaquete;
}

	// > Constructor con valores dados por el usuario
	public SimulacionUnaPersona(int precioPorPaquete, 
	int cantidadFiguritasTotal, 
	int cantidadFiguritasPorPaquete){
	persona = new Persona(1);
	this.precioPorPaquete = precioPorPaquete;
	persona.getAlbum().setCantidadFiguritasTotales(cantidadFiguritasTotal);
	persona.getAlbum().setCantidadFiguritasPorPaquete(cantidadFiguritasPorPaquete);
	PaqueteFiguritasNormal.setCantidadFiguritasTotales(cantidadFiguritasTotal);
	PaqueteFiguritasNormal.setCantidadFiguritasPaquete(cantidadFiguritasPorPaquete);
}

	@Override
	public int iniciarSimulacion(){
	int cantPaquetes = 1;
	while(!persona.albumEstaCompleto()){
		
		try { rellenarAlbum( PaqueteFiguritasNormal.nuevo().getPaqueteFiguritas()); } 
		catch (Exception e) { e.printStackTrace();}

		notificarObservadores();
		gastoTotal = cantPaquetes * precioPorPaquete;
	}
		return gastoTotal;
}

	protected void rellenarAlbum(LinkedList<Integer> paquete) throws Exception{
	for(int i = 0; i < paquete.size();i++) 
		persona.insertarFiguritaEnAlbum(paquete.get(i));
}

	// > Getter & Setter

	@Override
	public int getIteracion() {
	return 0;
}

	@Override
	public ArrayList<Persona> getPersonas(){
		ArrayList<Persona> ret = new ArrayList<Persona>(1);
		ret.add(this.persona);
		return ret;
}

	@Override
	public int getPaquetesAbiertos(){
		return 0;
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
 
	private void notificarObservadores(){
		observador.notificar();
}

	@Override
	public void registrarObservador(Observador obs){
		this.observador = obs;
}

	@Override
	public double promedioPaquetesXPersona(){
	return this.getPaquetesAbiertos();
}

}
