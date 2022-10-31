package modelo;

import java.util.LinkedList;

public class SimulacionUnaPersona implements Simulacion{
	
	private Persona persona;
	private int valorFiguritas;
	private int gastoTotal;
	
	public SimulacionUnaPersona(int valorFiguritas) 
	{
	persona = new Persona();
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
}
