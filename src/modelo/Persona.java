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
	
//	public int intercambioDeFiguritas(int numDeFiguritaRequerida, int numDeFiguritaNueva) 
//	{
//	int numDeFiguritaParaDar = -1;
//	if(miAlbum.existeFiguritasEnRepetidas(numDeFiguritaRequerida) && !miAlbum.existeFiguritaEnAlbum(numDeFiguritaNueva)) 
//	{
//		numDeFiguritaParaDar = numDeFiguritaRequerida;
//		miAlbum.eliminarFigurita(numDeFiguritaNueva);
//		insertarFiguritaEnAlbum(numDeFiguritaNueva);
//	}
//	return numDeFiguritaParaDar;
//	}
	
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

	public void RegalarFiguritas(Persona personaParaRegalarFigus) 
	{
	LinkedList<Integer> FigusParaRegalar = getfiguritasRepetidas();
	LinkedList<Integer> figusDePersona2 = personaParaRegalarFigus.getMisFiguritas();
	LinkedList<Integer> figuritasParaEliminarDeRepetidas = new LinkedList<Integer>();
	
	Iterator<Integer> it = FigusParaRegalar.iterator();
	
	while(it.hasNext()){
		int figurita = (int) it.next();
		if(!figusDePersona2.contains(figurita)) 
			personaParaRegalarFigus.insertarFiguritaEnAlbum(figurita);
			figuritasParaEliminarDeRepetidas.add(figurita);
	}
	eliminarRepetidasRegaladas(figuritasParaEliminarDeRepetidas);
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
