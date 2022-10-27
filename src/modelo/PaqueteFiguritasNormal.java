package modelo;

import java.util.LinkedList;
import java.util.Random;

public class PaqueteFiguritasNormal{

	public static PaqueteFiguritasNormal paquete;
	private int cantidadTotalFiguritas;
	private int cantidadFiguritasPaquete;
	private LinkedList<Integer> paqueteFiguritas;
	
	// > Generador de paquetes nuevos de figuritas.
	public static PaqueteFiguritasNormal nuevo()
	{
	paquete = new PaqueteFiguritasNormal(); // Instancia de paquete
	Random numeroFiguritas = new Random(); // Random para numero de figurita
	int cantidadFigus = paquete.getCantiadadFiguritas(); // Cantidad de cada paquete
	int cantidadTotalFigus = paquete.getCantidadTotalFiguritas(); // Cantidad total de figuritas album
	for(int i = 0; i < cantidadFigus ;i++) // Relleno de figuritas el paquete
	{
		int numeroFigurita = numeroFiguritas.nextInt(cantidadTotalFigus);
		paquete.setFigurita(numeroFigurita);
	}
	return paquete; // Retorno el paquete
	}
	
	// > Constructor
	private PaqueteFiguritasNormal() 
	{
	cantidadTotalFiguritas = 683;
	cantidadFiguritasPaquete = 5;
	paqueteFiguritas = new LinkedList<Integer>();  
	}
	
	// > Getters && Setters
	public int getCantiadadFiguritas()
	{
	return cantidadFiguritasPaquete;
	}
	
	public int getCantidadTotalFiguritas()
	{
	return cantidadTotalFiguritas;
	}
	
	public void setFigurita(int numDeFigu) 
	{
	paqueteFiguritas.add(numDeFigu);
	}
}
