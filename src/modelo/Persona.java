package modelo;

import java.util.Iterator;
import java.util.LinkedList;

public class Persona {

	private int id = 0;
	private Album miAlbum;
	
	// > Constructor
	public Persona()
	{
	id++;
	miAlbum = new Album(id);
	}
	
	// > Metodos de clase
	public void insertarFiguritaEnAlbum(int numDeFigurita) 
	{
	miAlbum.ingresarFigurita(numDeFigurita);
	}
	
	public boolean tieneFigurita(int numDeFigurita) 
	{
	boolean existe = false;
	if(miAlbum.existeFiguritaEnAlbum(numDeFigurita))
		existe = true;
	return existe;
	}
	
	public boolean albumEstaCompleto() 
	{
	boolean estaCompleto = false;
	if(miAlbum.estaCompletoAlbum())
		estaCompleto = true;
	return estaCompleto;
	}

	public boolean existeFiguritaRepetida(int numDeFigurita) 
	{
	boolean existe = false;
	if(miAlbum.getFiguritasRepetidas().contains(numDeFigurita))
		existe = true;
	return existe;
	}
	
	public void RegalarFiguritas(Persona personaParaRegalarFigus) 
	{
	LinkedList<Integer> FigusParaRegalar = getfiguritasRepetidas();
	LinkedList<Integer> figusDePersona2 = personaParaRegalarFigus.getMisFiguritas();
	LinkedList<Integer> figuritasParaEliminarDeRepetidas = new LinkedList<Integer>();
	
	for(int i = 0; i < FigusParaRegalar.size(); i++) 
	{
		if(!figusDePersona2.contains(FigusParaRegalar.get(i)))
			personaParaRegalarFigus.insertarFiguritaEnAlbum(FigusParaRegalar.get(i));
			figuritasParaEliminarDeRepetidas.add(FigusParaRegalar.get(i));
	}
	eliminarRepetidasRegaladas(figuritasParaEliminarDeRepetidas);
	figuritasParaEliminarDeRepetidas.clear();
	}
	
	public void eliminarRepetidasRegaladas(LinkedList<Integer> figuritasParaEliminarDeRepetidas) 
	{
		LinkedList<Integer> FigusParaRegalar = getfiguritasRepetidas();
		for(int i = 0; i < figuritasParaEliminarDeRepetidas.size(); i++) {
			if(FigusParaRegalar.contains(figuritasParaEliminarDeRepetidas.get(i)))
				FigusParaRegalar.remove(figuritasParaEliminarDeRepetidas.get(i));
		}
	}
	
	public void eliminarFiguritasDeRepetidas(int numDeFigurita) 
	{
		miAlbum.eliminarFiguritaDeRepetidas(numDeFigurita);
	}
	
	public LinkedList<Integer> getfiguritasRepetidas()
	{
	return miAlbum.getFiguritasRepetidas();
	}
	
	public LinkedList<Integer> getMisFiguritas()
	{
		return miAlbum.getFiguritasDeAlbum();
	}
	
	public int getId() 
	{
	return id;
	}
}
