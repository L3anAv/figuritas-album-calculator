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

	private int iteracion;
	private Persona persona;
	private StringBuilder sb;
	private int cantPaquetes;
	private int precioPorPaquete;
	private Observador observador;
	private Generador genPrefijado;
	private int cantidadFiguritasTotal;
	private int cantidadFiguritasPorPaquete;

// > Constructor de simulacion

	public SimulacionUnaPersona( int precioPorPaquete, int cantidadFiguritasTotal, int cantidadFiguritasPorPaquete){
	
	if(precioPorPaquete < 0 )
		throw new IllegalArgumentException("El Valor del precio no puede ser negativo");
	
	if(cantidadFiguritasPorPaquete < 1) 
		throw new IllegalArgumentException("La cantidad de Figuritas por paquete no puede ser menor a 1");
	
	if(cantidadFiguritasTotal < 1)
		throw new IllegalArgumentException("La cantidad total de figuritas no puede ser menor a 1");
	
	cantPaquetes = 0;
	persona = new Persona(1);
	this.sb = new StringBuilder();
	this.precioPorPaquete = precioPorPaquete;
	this.cantidadFiguritasTotal = cantidadFiguritasTotal;
	this.cantidadFiguritasPorPaquete = cantidadFiguritasPorPaquete;
	
	persona.getAlbum().setCantidadFiguritasTotales(cantidadFiguritasTotal);
	persona.getAlbum().setCantidadFiguritasPorPaquete(cantidadFiguritasPorPaquete);
}

// > Metodos de clase

	@Override
	public int iniciarSimulacion() throws Exception{

	while(!satisfactorio()){
		rellenarAlbum(PaqueteFiguritasNormal.nuevo(cantidadFiguritasTotal, cantidadFiguritasPorPaquete).getPaqueteFiguritas());
		notificarObservadores();
		iteracion++;
		cantPaquetes++;
	}

	crearLog();
		return (cantPaquetes*precioPorPaquete);
}

	public void rellenarAlbum(LinkedList<Integer> paquete) throws Exception{
	for(int i = 0; i < paquete.size();i++){
		persona.insertarFiguritaEnAlbum(paquete.get(i));
	}
}
	
// > Metodo Test
	public void rellenarAlbum_Test(LinkedList<Integer> paquete) throws Exception{
		cantPaquetes++;
		for(int i = 0; i < paquete.size();i++){
			persona.insertarFiguritaEnAlbum(paquete.get(i));
		}
	}
	
// > Getter & Setter

	private void notificarObservadores()
	{ observador.notificar(); }

	public Generador getGenPrefijado() 
	{ return genPrefijado; }

	public void setGenPrefijado(Generador genPrefijado) 
	{ this.genPrefijado = genPrefijado; }

	public boolean satisfactorio()
	{ return true && persona.albumEstaCompleto(); }
	
	public int getPrecioPaquete() 
	{ return this.precioPorPaquete; }
	
	public Persona getPersona()
    { return persona; }
	
	@Override
	public int getIteracion() 
	{ return iteracion; }

	@Override
	public void registrarObservador(Observador obs)
	{ this.observador = obs; }

	@Override
	public int getPaquetesAbiertos()
	{ return cantPaquetes; }

	@Override
	public ArrayList<Persona> getPersonas(){
		ArrayList<Persona> ret = new ArrayList<Persona>(1);
		ret.add(this.persona);
		return ret;
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
	public double promedioPaquetesXPersona()
	{ return this.getPaquetesAbiertos(); }
	
}
