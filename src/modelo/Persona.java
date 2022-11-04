package modelo;

import java.util.LinkedList;
import java.util.Objects;

public class Persona {

	private int id;
	private Album miAlbum;
	
	// > Constructor
	public Persona(){
		id++;
		miAlbum = new Album(id);
}
	public Persona(int id) {
		this.id = id;
		miAlbum = new Album(id);
	}
	// > Metodos de clase
	public void insertarFiguritaEnAlbum(int numDeFigurita){
	//System.out.println("Insertando Figurita" + numDeFigurita);
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
//	LinkedList<Integer> figuritasMiasIntercambiadas = new LinkedList<Integer>();
//	LinkedList<Integer> figuritasOtraPersonaIntercambiadas = new LinkedList<Integer>();
	LinkedList<Integer> figusDeOtraPersonaParaIntercambiar = personaParaIntercambiar.getFiguritasRepetidas();
	
	for(int figuritaIntercambiable: figusMiasParaIntercambiar){
		if(!personaParaIntercambiar.tieneFigurita(figuritaIntercambiable) &&
		   tieneFiguritaParaIntercambiar(figusDeOtraPersonaParaIntercambiar)){
			int figuritaOtraPersonaIntercambio = buscarFiguritaParaIntercambiar(figusDeOtraPersonaParaIntercambiar);
			
			//System.out.println( "Aca intercambio: " + figuritaOtraPersonaIntercambio);
			
			// > Los inserto en una lista de cuales intercambio
			insertarFiguritaEnAlbum(figuritaOtraPersonaIntercambio);
			//System.out.println( "Se hace el intercambio" + figuritaOtraPersonaIntercambio);
			personaParaIntercambiar.insertarFiguritaEnAlbum(figuritaIntercambiable);
			//System.out.println("Se hace el intercambio final");
		}
	
		//System.out.println("Loopea");
	
	}

	//System.out.println("Termina la ejecucion de intercambio");
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

	@Override
	public int hashCode() {
		return Objects.hash(id, miAlbum);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		
		return this.id == other.id;
	}
	
	
	
	
}
