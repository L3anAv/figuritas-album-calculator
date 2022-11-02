package modelo;

import java.util.LinkedList;

public class Album {
	
	private int idPropietario;
	private LinkedList<Integer> album;
	private int cantidadFiguritas = 683;
	private LinkedList<Integer> figuritasRepetidas;
	
	// > Constructor
	public Album(int idPropietario){
		album = new LinkedList<Integer>();
		this.idPropietario = idPropietario;
		figuritasRepetidas = new LinkedList<Integer>();
}

	// > Metodos de clase
	public void ingresarFigurita(int figurita){
		if(!album.contains(figurita))
			album.add(figurita);
		else
			figuritasRepetidas.add(figurita);
}
	
	public boolean existeFiguritaEnAlbum(int numDeFigurita) 
	{
	boolean existe = false;
		if(album.contains(numDeFigurita))
			existe = true;
	return existe;
	}
	
	public boolean existeFiguritasEnRepetidas(int numDeFigurita) {
	boolean existe = false;
		if(figuritasRepetidas.contains(numDeFigurita))
			existe = true;
	return existe;
}
	
	public boolean estaCompletoAlbum(){
	return album.size() == cantidadFiguritas;
}

	// > Getters & Setters
	public LinkedList<Integer> getFiguritasDeAlbum() {
	return album;
}
	
	public LinkedList<Integer> getFiguritasRepetidas(){
	return figuritasRepetidas;
}
	
	public void eliminarFiguritaDeRepetidas(int numDeFigurita) {
	//int index = figuritasRepetidas.get(numDeFigurita);	
}
	
	public int getIdPropietarioAlbum() {
	return idPropietario;
}
	
}
