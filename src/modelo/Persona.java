package modelo;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Iterator;
=======
>>>>>>> 7cb303c (Mis Ultimos Cambios)
import java.util.LinkedList;

public class Persona {

	private int id;
	private Album miAlbum;
	
	// > Constructor
	public Persona(int id){
	this.id = id;
		
	miAlbum = new Album(id);
	}
	
	// > Metodos de clase
	public void insertarFiguritaEnAlbum(int numDeFigurita) 
	{
	miAlbum.ingresarFigurita(numDeFigurita);
	}
	
	public boolean tieneFigurita(int numDeFigurita) {
	boolean existe = false;
	if(miAlbum.existeFiguritaEnAlbum(numDeFigurita))
		existe = true;
	return existe;
	}
	
	public boolean albumEstaCompleto() {
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
	

		
	LinkedList<Integer> FigusParaRegalar = getFiguritasRepetidas();
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
		LinkedList<Integer> FigusParaRegalar = getFiguritasRepetidas();
		for(int i = 0; i < figuritasParaEliminarDeRepetidas.size(); i++) {
			if(FigusParaRegalar.contains(figuritasParaEliminarDeRepetidas.get(i)))
				FigusParaRegalar.remove(figuritasParaEliminarDeRepetidas.get(i));
		}
	}
	
	public void eliminarFiguritasDeRepetidas(int numDeFigurita) 
	{
		miAlbum.eliminarFiguritaDeRepetidas(numDeFigurita);
	}
	
	public LinkedList<Integer> getFiguritasRepetidas()
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

	public boolean hayRepetidas() {
		
	return getFiguritasRepetidas().size() != 0;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", Figuritas conseguidas= " + miAlbum.getFiguritasDeAlbum().size() + " de " + "683" + "]";
	}
	
	
	
	
}
