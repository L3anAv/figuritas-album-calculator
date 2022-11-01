package modelo;

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
	
	public int intercambioDeFiguritas(int numDeFiguritaRequerida, int numDeFiguritaNueva) 
	{
	int numDeFiguritaParaDar = -1;
	if(miAlbum.existeFiguritasEnRepetidas(numDeFiguritaRequerida) && !miAlbum.existeFiguritaEnAlbum(numDeFiguritaNueva)) 
	{
		numDeFiguritaParaDar = numDeFiguritaRequerida;
		miAlbum.eliminarFigurita(numDeFiguritaNueva);
		insertarFiguritaEnAlbum(numDeFiguritaNueva);
	}
	return numDeFiguritaParaDar;
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

//	public int existefiguritaParaRegalar(int numDeFiguritaRequerida) 
//	{
//	int figurita = -1;
//	if(miAlbum.existeFiguritasEnRepetidas(numDeFiguritaRequerida))
//		return numDeFiguritaRequerida;
//	return figurita;
//	}

	public void RegalarFiguritas(Persona personaParaRegalarFigus) 
	{
	LinkedList<Integer> FigusParaRegalar = getfiguritasRepetidas();
	LinkedList<Integer> figusDePersona2 = personaParaRegalarFigus.getMisFiguritas();
	
	for(int i = 0; i < FigusParaRegalar.size(); i++)
	{ 
	if(!figusDePersona2.contains(FigusParaRegalar.get(i))) 
		personaParaRegalarFigus.insertarFiguritaEnAlbum(FigusParaRegalar.get(i));
	}
	
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
