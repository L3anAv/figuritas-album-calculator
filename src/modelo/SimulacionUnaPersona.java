package modelo;

import java.util.LinkedList;

public class SimulacionUnaPersona {
	
	private Persona persona;
	private Album albumPersona;
	private int valorFiguritas;
	private int gastoTotal;
	
	public SimulacionUnaPersona(int valorFiguritas) 
	{
	persona = new Persona(1);
	this.valorFiguritas = valorFiguritas;
	albumPersona = new Album(persona.getId());
	}
	
	public int iniciarSimulacion() 
	{
	int cantPaquetes = 1;
	while(!albumPersona.estaCompletoAlbum())
		rellenarAlbum(PaqueteFiguritasNormal.nuevo().getPaqueteFiguritas());
		gastoTotal = cantPaquetes * valorFiguritas;
	return gastoTotal;
	}
	
	public void rellenarAlbum(LinkedList<Integer> paquete) 
	{
	for(int i = 0; i < paquete.size();i++) 
	{
	albumPersona.ingresarFigurita(paquete.get(i));
	}
	}
}
