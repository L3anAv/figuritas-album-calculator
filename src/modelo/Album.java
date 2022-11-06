package modelo;

import java.util.LinkedList;

public class Album {

	private int idPropietario;
	private LinkedList<Integer> album;
	private int cantidadFiguritas = 638;
	private int cantidadFiguritaPorPaquete = 5;
	private LinkedList<Integer> figuritasRepetidas;

	// > Constructor
	public Album(int idPropietario){
		album = new LinkedList<Integer>();
		this.idPropietario = idPropietario;
		figuritasRepetidas = new LinkedList<Integer>();
}

	// > Metodos de clase
	public void ingresarFigurita(int figurita) throws Exception{	

		if(figurita < 1)
			throw new Exception("El numero no pude ser menor a 1. No existen figuritas Negativas.");
		else if(!album.contains(figurita))
			album.add(figurita);
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
	
	public int getIdPropietarioAlbum()
	{ return idPropietario; }

	public int getcantidadFiguritaPorPaquete()
	{ return cantidadFiguritaPorPaquete; }

	public void setCantidadFiguritasTotales(int nuevaCantidadFiguritasTotales)
	{ cantidadFiguritas = nuevaCantidadFiguritasTotales; }

	public void setCantidadFiguritasPorPaquete(int nuevaCantidadFiguritasPaquete)
	{ cantidadFiguritaPorPaquete = nuevaCantidadFiguritasPaquete; }

}
