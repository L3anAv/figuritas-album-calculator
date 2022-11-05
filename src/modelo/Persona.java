package modelo;

import java.util.LinkedList;
import java.util.Objects;

public class Persona {

	private int id;
	private Album miAlbum;
	private StringBuilder toStringPersona;
	
	// > Constructor
	public Persona(int id){
		this.id = id;
		miAlbum = new Album(id);
		toStringPersona = new StringBuilder();
}
	
	// > Metodos de clase
	public void insertarFiguritaEnAlbum(int numDeFigurita) throws Exception
	{ miAlbum.ingresarFigurita(numDeFigurita); }
	
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
	
	public void regalarFiguritas(Persona personaParaRegalarFigus) throws Exception{
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
	
	public void intercambiarFiguritas(Persona personaParaIntercambiar) throws Exception{
	
	LinkedList<Integer> figusMiasParaIntercambiar = getFiguritasRepetidas();
	LinkedList<Integer> figusDeOtraPersonaParaIntercambiar = personaParaIntercambiar.getFiguritasRepetidas();
	
	LinkedList<Integer> figuritasMiasIntercambiables = new LinkedList<Integer>();
	LinkedList<Integer> figuritasOtraPersonaOfrecidas = new LinkedList<Integer>();
	
	for(int figuritaPosibleIntercambiable: figusMiasParaIntercambiar)
	{
		if(!personaParaIntercambiar.tieneFigurita(figuritaPosibleIntercambiable)
		&& tieneFiguritaParaIntercambiar(figusDeOtraPersonaParaIntercambiar))
			{
			figuritasMiasIntercambiables.add(figuritaPosibleIntercambiable);
			int figuritaOtraPersonaIntercambio = buscarFiguritaParaIntercambiar(figusDeOtraPersonaParaIntercambiar, figuritasOtraPersonaOfrecidas);
			figuritasOtraPersonaOfrecidas.add(figuritaOtraPersonaIntercambio);
		}
	}
	// Realizo el intercambio de figuritas
	realizarIntercambio(figuritasMiasIntercambiables, figuritasOtraPersonaOfrecidas, personaParaIntercambiar);
	//Elimino de repetidas las figuritas intercambidas
	eliminarDeRepetidasFiguExtraidas(figuritasMiasIntercambiables);
	personaParaIntercambiar.eliminarDeRepetidasFiguExtraidas(figuritasOtraPersonaOfrecidas);
	//Vacio las listas aux usadas en el metodo
	figuritasMiasIntercambiables.clear();
	figuritasOtraPersonaOfrecidas.clear();
}
	
	public void realizarIntercambio(LinkedList<Integer> figuritasMiasPersonaOfrecidas, LinkedList<Integer> figuritasOtraPersonaOfrecidas, Persona otraPersona) throws Exception{
	for(int i = 0; i < figuritasMiasPersonaOfrecidas.size(); i++){
		insertarFiguritaEnAlbum(figuritasOtraPersonaOfrecidas.get(i));
		otraPersona.insertarFiguritaEnAlbum(figuritasMiasPersonaOfrecidas.get(i));
	}
}

	public boolean tieneFiguritaParaIntercambiar(LinkedList<Integer> figusDeOtraPersonaParaIntercambiar){
	int index = 0;
	boolean existeFiguritaParaIntercambio = false;
		while(!existeFiguritaParaIntercambio && index < figusDeOtraPersonaParaIntercambiar.size()){
			if(!tieneFigurita(figusDeOtraPersonaParaIntercambiar.get(index)))
				existeFiguritaParaIntercambio = true;
			index++;
	}
	return existeFiguritaParaIntercambio;
}

	public int buscarFiguritaParaIntercambiar(LinkedList<Integer> figusParaIntercambio, LinkedList<Integer> figuritasOtraPersonaOfrecidas){
	int index = 0;
	int figurita = -1;
		while(figurita == -1 && index < figusParaIntercambio.size()){
			if(figuritasOtraPersonaOfrecidas.size() == 0){
				figurita = figusParaIntercambio.get(index);
			}else if(!figuritasOtraPersonaOfrecidas.contains(figusParaIntercambio.get(index))){
				figurita = figusParaIntercambio.get(index);
			}
		index++;
	}
	return figurita;
}

	public void eliminarDeRepetidasFiguExtraidas(LinkedList<Integer> figuritasParaEliminarDeRepetidas){
		LinkedList<Integer> FigusParaRegalar = getFiguritasRepetidas();
		for(int i = 0; i < figuritasParaEliminarDeRepetidas.size(); i++) {
			if(FigusParaRegalar.contains(figuritasParaEliminarDeRepetidas.get(i)))
				FigusParaRegalar.remove(figuritasParaEliminarDeRepetidas.get(i));
	}
}
	
	public LinkedList<Integer> getFiguritasRepetidas()
	{ return miAlbum.getFiguritasRepetidas(); }
	
	public LinkedList<Integer> getMisFiguritas()
	{ return miAlbum.getFiguritasDeAlbum(); }
	
	public int getId() 
	{ return id; }

	public boolean hayRepetidas()
	{ return getFiguritasRepetidas().size() != 0; }


	@Override
	public String toString(){
		
	toStringPersona.append("Persona [id= ")
		.append(id)
		.append(", Figuritas conseguidas= ")
		.append(miAlbum.getFiguritasDeAlbum().size())
		.append(" de 683 ]");
	
	return  toStringPersona.toString();
}
	
	@Override
	public int hashCode() 
	{ return Objects.hash(id, miAlbum); }

	@Override
	public boolean equals(Object obj){
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
