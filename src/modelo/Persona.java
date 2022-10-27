package modelo;

public class Persona {

	private int id;
	private Album miAlbum;
	
	// > Constructor
	public Persona(int id)
	{
	this.id = id;
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
	
	public int figuritasParaRegalar(int numDeFiguritaRequerida) 
	{
	int figurita = -1;
	if(miAlbum.existeFiguritasEnRepetidas(numDeFiguritaRequerida))
		return numDeFiguritaRequerida;
	return figurita;
	}
	
	public int getId() 
	{
	return id;
	}
}
