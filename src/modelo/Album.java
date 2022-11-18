package modelo;

import java.util.LinkedList;

public class Album {

	private int idPropietario;
	private LinkedList<Integer> album;
	private int cantidadFiguritas;
	private int cantidadFiguritaPorPaquete; 
	private LinkedList<Integer> figuritasRepetidas;

	// > Constructor
	public Album(int idPropietario){
		album = new LinkedList<Integer>();
		album.add(0);
		this.idPropietario = idPropietario;
		figuritasRepetidas = new LinkedList<Integer>();
		this.cantidadFiguritas = 638;
		this.cantidadFiguritaPorPaquete = 5;	
	}
	
	
	public Album(int idPropietario, int cantidadFiguritas, int cantidadFiguritasPorPaquete) {
		album = new LinkedList<Integer>();
		album.add(0);
		this.idPropietario = idPropietario;
		figuritasRepetidas = new LinkedList<Integer>();
		this.cantidadFiguritaPorPaquete = cantidadFiguritasPorPaquete;
		this.cantidadFiguritas = cantidadFiguritas;
		
	}


	// > Metodos de clase
	public void ingresarFigurita(int figurita) throws Exception{	

		if(figurita < 1)
			throw new IllegalArgumentException("El numero no pude ser menor a 1. No existen figuritas Negativas.");
		else if(!album.contains(figurita)) {
			album.add(figurita);}
		else 
			figuritasRepetidas.add(figurita);
}

	
	
	
	public boolean existeFiguritaEnAlbum(int numDeFigurita){
	boolean existe = false;
		if(album.contains(numDeFigurita))
			existe = true;
	return existe;
}

	public boolean estaCompletoAlbum()
	{ return album.size() == cantidadFiguritas; }

	// > Getters & Setters

	public LinkedList<Integer> getFiguritasDeAlbum()
	{ return album; }
	
	public LinkedList<Integer> getFiguritasRepetidas()
	{ return figuritasRepetidas; }
	
	public int getCantFiguritasTotal() 
	{ return cantidadFiguritas;}
	
	public int getIdPropietarioAlbum()
	{ return idPropietario; }

	public int getcantidadFiguritaPorPaquete()
	{ return cantidadFiguritaPorPaquete; }

	public void setCantidadFiguritasTotales(int nuevaCantidadFiguritasTotales)
	{ cantidadFiguritas = nuevaCantidadFiguritasTotales; }

	public void setCantidadFiguritasPorPaquete(int nuevaCantidadFiguritasPaquete)
	{ cantidadFiguritaPorPaquete = nuevaCantidadFiguritasPaquete; }

}
