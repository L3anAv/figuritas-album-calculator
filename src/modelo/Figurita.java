package modelo;

import interfaces.Generador;



public class Figurita implements Comparable<Figurita> {

	//Represento a la figurita con un arreglo de bits
	private boolean[] bits;


	//Instancia a la que esta asociada la figurita
	//private Instancia instancia;



	// Generador de numeros aleatorios para las figuritas
	private static Generador random;

	// Setter para el generador
	public static void setGenerador(Generador generador)
	{
			random = generador;
	}


	// Factory method para construir una figurita aleatoriamente
	public static Figurita aleatoria(Instancia instancia)
	{
			Figurita ret = new Figurita(instancia);
			
//			for(int i=0; i<instancia.getTamano(); ++i)
//				ret.set(i, random.nextBoolean());
//			
			return ret;
		}

	// Constructor para generar una figurita default
		private Figurita(Instancia instancia)
		{
			instancia = instancia;
		//	bits = new boolean[instancia.getTamano()];
		}








	// Getters y setters 
	boolean get(int i) {
		return this.bits[i];}	
		
	private void set(int i, boolean valor){
			this.bits[i] = valor;
		}









	@Override
	public int compareTo(Figurita o) {
		// TODO Auto-generated method stub
		return 0;
	}


}
