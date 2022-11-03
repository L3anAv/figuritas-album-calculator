package modelo;

import java.util.LinkedList;

public class Persona {

	private int id;
	private Album miAlbum;
	
	// > Constructor
	public Persona(){
		id++;
		miAlbum = new Album(id);
}
	
	// > Metodos de clase
	public void insertarFiguritaEnAlbum(int numDeFigurita){
	miAlbum.ingresarFigurita(numDeFigurita);
}
	
	public boolean tieneFigurita(int numDeFigurita){
	boolean existe = false;
	if(miAlbum.existeFiguritaEnAlbum(numDeFigurita))
		existe = true;
	return existe;
}
	
	public boolean albumEstaCompleto(){
	boolean estaCompleto = false;
	if(miAlbum.estaCompletoAlbum())
		estaCompleto = true;
	return estaCompleto;
}

	public boolean existeFiguritaRepetida(int numDeFigurita){
	boolean existe = false;
		if(miAlbum.getFiguritasRepetidas().contains(numDeFigurita))
			existe = true;
	return existe;
}
	
	public void regalarFiguritas(Persona personaParaRegalarFigus){
	LinkedList<Integer> figusParaRegalar = getFiguritasRepetidas();
	LinkedList<Integer> figuritasRegaladas = new LinkedList<Integer>();
	LinkedList<Integer> figusDeOtraPersona = personaParaRegalarFigus.getMisFiguritas();
	
	for(int i = 0; i < figusParaRegalar.size(); i++){
		if(!figusDeOtraPersona.contains(figusParaRegalar.get(i)))
			personaParaRegalarFigus.insertarFiguritaEnAlbum(figusParaRegalar.get(i));
			figuritasRegaladas.add(figusParaRegalar.get(i)); 
	}
		eliminarDeRepetidasFiguExtraidas(figuritasRegaladas);
		figuritasRegaladas.clear();
}
	
	public void intercambiarFiguritas(Persona personaParaIntercambiar){
	
	LinkedList<Integer> figusMiasParaIntercambiar = getFiguritasRepetidas();
	LinkedList<Integer> figuritasMiasIntercambiables = new LinkedList<Integer>();
	LinkedList<Integer> figuritasOtraPersonaOfrecidas = new LinkedList<Integer>();
	LinkedList<Integer> figusDeOtraPersonaParaIntercambiar = personaParaIntercambiar.getFiguritasRepetidas();
	
	for(int figuritaPosibleIntercambiable: figusMiasParaIntercambiar){
		if(!personaParaIntercambiar.tieneFigurita(figuritaPosibleIntercambiable) &&
		   tieneFiguritaParaIntercambiar(figusDeOtraPersonaParaIntercambiar)){
			figuritasMiasIntercambiables.add(figuritaPosibleIntercambiable);
			int figuritaOtraPersonaIntercambio = buscarFiguritaParaIntercambiar(figusDeOtraPersonaParaIntercambiar);
			figuritasOtraPersonaOfrecidas.add(figuritaOtraPersonaIntercambio);
		}
	}
	
	realizarIntercambio(figuritasMiasIntercambiables, figuritasOtraPersonaOfrecidas, personaParaIntercambiar);
//	eliminarDeRepetidasFiguExtraidas(figuritasMiasIntercambiables);
//	personaParaIntercambiar.eliminarDeRepetidasFiguExtraidas(figuritasOtraPersonaOfrecidas);
//	figuritasMiasIntercambiables.clear();
//	figuritasOtraPersonaOfrecidas.clear();
}
	
	public void realizarIntercambio(LinkedList<Integer> figuritasMiasPersonaOfrecidas, LinkedList<Integer> figuritasOtraPersonaOfrecidas, Persona otraPersona){
	System.out.println("Cantidad de figus mias para cambiar: " + figuritasMiasPersonaOfrecidas.size());
	System.out.println("Cantidad de figus Otra para cambiar: " + figuritasOtraPersonaOfrecidas.size());
	for(int i = 0; i < figuritasMiasPersonaOfrecidas.size(); i++){
		insertarFiguritaEnAlbum(figuritasOtraPersonaOfrecidas.get(i));
		otraPersona.insertarFiguritaEnAlbum(figuritasMiasPersonaOfrecidas.get(i));
		System.out.println("---------------");
		System.out.println("Figu Persona 3 para cambiar: " + figuritasMiasPersonaOfrecidas.get(i));
		System.out.println("Figu Persona 4 para cambiar: " + figuritasOtraPersonaOfrecidas.get(i));
		System.out.println("---------------");
	}
}
	
	public boolean tieneFiguritaParaIntercambiar(LinkedList<Integer> figusParaIntercambio){
	int index = 0;
	boolean existeFiguritaParaIntercambio = false;
		while(!existeFiguritaParaIntercambio && index < figusParaIntercambio.size()){
			if(!tieneFigurita(figusParaIntercambio.get(index)))
				existeFiguritaParaIntercambio = true;
			index++;
	}
	return existeFiguritaParaIntercambio;
}

//Aca la figurita no tiene que estar ya en la lista de las figuritas que ofrece para intercambio
	public int buscarFiguritaParaIntercambiar(LinkedList<Integer> figusParaIntercambio){
	int index = 0;
	int figurita = -1;
		while(figurita == -1 && index < figusParaIntercambio.size()){
			if(!tieneFigurita(figusParaIntercambio.get(index))){
				figurita = figusParaIntercambio.get(index);
				index++;
		}
	}
	return figurita;
}
	
	public void eliminarDeRepetidasFiguExtraidas(LinkedList<Integer> figuritasParaEliminarDeRepetidas) 
	{
		LinkedList<Integer> FigusParaRegalar = getFiguritasRepetidas();
		for(int i = 0; i < figuritasParaEliminarDeRepetidas.size(); i++) {
			if(FigusParaRegalar.contains(figuritasParaEliminarDeRepetidas.get(i)))
				FigusParaRegalar.remove(figuritasParaEliminarDeRepetidas.get(i));
		}
	}
	
//	public void eliminarFiguritaDeRepetidas(int numDeFigurita)
//	{ miAlbum.eliminarFiguritaDeRepetidas(numDeFigurita); }
	
	public LinkedList<Integer> getFiguritasRepetidas()
	{ return miAlbum.getFiguritasRepetidas(); }
	
	public LinkedList<Integer> getMisFiguritas()
	{ return miAlbum.getFiguritasDeAlbum(); }
	
	public int getId() 
	{ return id; }

	public boolean hayRepetidas()
	{ return getFiguritasRepetidas().size() != 0; }

	// Esto va con stringbuilder
	@Override
	public String toString(){
		return "Persona [id=" + id + ", Figuritas conseguidas= " + miAlbum.getFiguritasDeAlbum().size() + " de " + "683" + "]";
	}
	
	
	
	
}
