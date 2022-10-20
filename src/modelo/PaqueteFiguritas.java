package modelo;

import java.util.LinkedList;
import java.util.Random;

public class PaqueteFiguritas{
	
	private int cantidadTotalFiguritas;
	private int cantidadFiguritasPaquete;
	private static PaqueteFiguritas paquete;
	private LinkedList<Integer> paqueteFiguritas;
	
// -->  Metodo con Factory para crear paquetes de figuritas aleatorias
	public static PaqueteFiguritas nuevo() 
{
		
		paquete = new PaqueteFiguritas(); // Instancia de paquete
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

// -->  Constructor
	private PaqueteFiguritas()
{
		cantidadFiguritasPaquete = 5;
		cantidadTotalFiguritas = 683;
		paqueteFiguritas = new LinkedList<Integer>();
}
	
// -->  Metodos de clase
	
	//
	//
	//
	
// -->  Getters & Setters
	public int getCantiadadFiguritas() 
{
		return cantidadFiguritasPaquete;
}
	
	public int getCantidadTotalFiguritas() 
{
		return cantidadTotalFiguritas;
}
	
	public void setFigurita(Integer numeroFigurita) 
{
		paqueteFiguritas.add(numeroFigurita);
}
	
}
