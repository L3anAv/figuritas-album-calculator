package modelo;

import java.util.LinkedList;

import interfaces.Generador;
import utilidades.GeneradorRandom;

public class PaqueteFiguritasNormal{

	private static int cantidadTotalFiguritas;
	public static int cantidadFiguritasPaquete;
	private LinkedList<Integer> paqueteFiguritas;
	private static PaqueteFiguritasNormal paquete;
	private static Generador generadorPref;

	
	
	
	// > Generador de paquetes nuevos de figuritas.
	public static PaqueteFiguritasNormal nuevo(){
	paquete = new PaqueteFiguritasNormal(); // Instancia de paquete
	GeneradorRandom numeroFiguritas = new GeneradorRandom(); // Random para numero de figurita
	int cantidadFigus = paquete.getCantidadFiguritas(); // Cantidad de cada paquete
	int cantidadTotalFigus = paquete.getCantidadTotalFiguritas(); // Cantidad total de figuritas album
	for(int i = 0; i < cantidadFigus ;i++){ // Relleno de figuritas el paquete
		int numeroFigurita = numeroFiguritas.nextIntCExclusion(cantidadTotalFigus, 0);
		paquete.setFigurita(numeroFigurita);
	}
	return paquete; // Retorno el paquete
}



	public static PaqueteFiguritasNormal nuevo(int cantTotalFigus, int cantFigusPaq){ 
		paquete = new PaqueteFiguritasNormal(); // Instancia de paquete	
		GeneradorRandom numeroFiguritas = new GeneradorRandom(); // Random para numero de figurita
		int cantidadFigus = cantFigusPaq; // Cantidad de cada paquete
		int cantidadTotalFigus = cantTotalFigus; // Cantidad total de figuritas album
		for(int i = 0; i < cantidadFigus ;i++){ // Relleno de figuritas el paquete
			int numeroFigurita = numeroFiguritas.nextIntCExclusion(cantidadTotalFigus, 0);
			paquete.setFigurita(numeroFigurita);
		}
		return paquete; // Retorno el paquete
	}
	//METODO PARA TESTEO
	public static PaqueteFiguritasNormal nuevo(Generador gen){ 
		paquete = new PaqueteFiguritasNormal(); // Instancia de paquete
	//	GeneradorRandom numeroFiguritas = new GeneradorRandom(); // Random para numero de figurita
		int cantidadFigus = paquete.getCantidadFiguritas(); // Cantidad de cada paquete
		for(int i = 0; i < cantidadFigus ;i++){ // Relleno de figuritas el paquete
			int numeroFigurita = gen.nextInt(i);
			paquete.setFigurita(numeroFigurita);
		}
		return paquete; // Retorno el paquete
	}
	

	// > Constructor
	private PaqueteFiguritasNormal(){
	cantidadTotalFiguritas = 632;
	cantidadFiguritasPaquete = 5;
	paqueteFiguritas = new LinkedList<Integer>();
}


	// > Getters && Setters

	public static void setCantidadFiguritasPaquete(int nuevaCantidadPorPaquete)
	{ cantidadTotalFiguritas = nuevaCantidadPorPaquete; }

	public static void setCantidadFiguritasTotales(int nuevaCantidadDeFiguritas)
	{ cantidadFiguritasPaquete = nuevaCantidadDeFiguritas; }

	public LinkedList<Integer> getPaqueteFiguritas()
	{ return paqueteFiguritas; }

	public int getCantidadFiguritas()
	{ return cantidadFiguritasPaquete; }

	public int getCantidadTotalFiguritas()
	{ return cantidadTotalFiguritas; }

	public void setFigurita(int numDeFigu) 
	{ paqueteFiguritas.add(numDeFigu); }

}
