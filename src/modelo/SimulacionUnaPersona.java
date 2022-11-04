package modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import interfaces.Observador;
import interfaces.Simulacion;

public class SimulacionUnaPersona implements Simulacion{
	
	private Persona persona;
	private int valorFiguritas;
	private int gastoTotal;
	private StringBuilder sb;
	private Observador observador;
	

	public SimulacionUnaPersona(int valorFiguritas) {
	persona = new Persona();
	this.valorFiguritas = valorFiguritas;
}
	
	@Override
	public int iniciarSimulacion(){
	int cantPaquetes = 1;
	while(!persona.albumEstaCompleto()){
		rellenarAlbum(PaqueteFiguritasNormal.nuevo().getPaqueteFiguritas());
		gastoTotal = cantPaquetes * valorFiguritas;
	}
	return gastoTotal;
}
	
	protected void rellenarAlbum(LinkedList<Integer> paquete){
	for(int i = 0; i < paquete.size();i++) 
		persona.insertarFiguritaEnAlbum(paquete.get(i));
}

	@Override
	public int getIteracion() {
	return 0;
}
	
	@Override
	public ArrayList<Persona> getPersonas() {
		ArrayList<Persona> ret = new ArrayList<Persona>(1);
		ret.add(this.persona);
		return ret;
}

	@Override
	public int getPaquetesAbiertos() {
		return 0;
}

	@Override
	public void escribirLog() {
	this.sb.append("It: " + getIteracion() + "Paquetes abiertos: " + getPaquetesAbiertos() + "\n");	
	this.sb.append(persona.toString()).append("\n");		
}
	
	@Override
	public void crearLog() {
	try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt"))) {
		writer.write(this.sb.toString());
	}catch(IOException e){
			e.printStackTrace();
	}
}

	@Override
	public void registrarObservador(Observador obs) {
		this.observador = obs;	
}

	@Override
	public double promedioPaquetesXPersona() {
		
		return this.getPaquetesAbiertos();
	}


}
