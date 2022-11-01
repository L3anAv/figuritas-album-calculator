package modelo;

import java.util.ArrayList;
import java.util.LinkedList;

import interfaces.Simulacion;

public class SimulacionUnaPersona implements Simulacion{
	
	private Persona persona;
	private int valorFiguritas;
	private int gastoTotal;
	
	public SimulacionUnaPersona(int valorFiguritas) 
	{
	persona = new Persona(1);
	this.valorFiguritas = valorFiguritas;
	}
	
	@Override
	public int iniciarSimulacion()
	{
	int cantPaquetes = 1;
	while(!persona.albumEstaCompleto())
	{
		rellenarAlbum(PaqueteFiguritasNormal.nuevo().getPaqueteFiguritas());
		gastoTotal = cantPaquetes * valorFiguritas;
	}
	return gastoTotal;
	}
	
	protected void rellenarAlbum(LinkedList<Integer> paquete)
	{
	for(int i = 0; i < paquete.size();i++) 
		persona.insertarFiguritaEnAlbum(paquete.get(i));
	}

	@Override
	public int getIteracion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Persona> getPersonas() {
		// TODO Auto-generated method stub
		return null;
	}



}
