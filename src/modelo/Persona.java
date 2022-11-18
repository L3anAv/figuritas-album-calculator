package modelo;


import java.util.LinkedList;
import java.util.Objects;

public class Persona {

	private int id;
	private Album miAlbum;

	
	// > Constructor
	public Persona(int id){
		this.id = id;
		miAlbum = new Album(id);
}
	
// > Metodos de clase

	public void insertarFiguritaEnAlbum(int numDeFigurita) throws Exception{
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

	if(!figusMiasParaIntercambiar.isEmpty()) {
		for(int figuritaPosibleIntercambiable : figusMiasParaIntercambiar) {
			if(!personaParaIntercambiar.tieneFigurita(figuritaPosibleIntercambiable) && !figusDeOtraPersonaParaIntercambiar.isEmpty() && !figusDeOtraPersonaParaIntercambiar.contains(figuritaPosibleIntercambiable)){
				figuritasMiasIntercambiables.add(figuritaPosibleIntercambiable);
			}
		}

		for(int figuritaPosibleIntercambiable2 : figusDeOtraPersonaParaIntercambiar) {
			if(!tieneFigurita(figuritaPosibleIntercambiable2) && !figuritasMiasIntercambiables.contains(figuritaPosibleIntercambiable2)){
				figuritasOtraPersonaOfrecidas.add(figuritaPosibleIntercambiable2);
			}
		}
	}

	if(!figuritasMiasIntercambiables.isEmpty() && !figuritasOtraPersonaOfrecidas.isEmpty()){
			
		realizarIntercambio(figuritasMiasIntercambiables, figuritasOtraPersonaOfrecidas, personaParaIntercambiar);
			
		//Elimino de repetidas las figuritas intercambidas
		eliminarDeRepetidasFiguExtraidas(figuritasMiasIntercambiables);
		personaParaIntercambiar.eliminarDeRepetidasFiguExtraidas(figuritasOtraPersonaOfrecidas);
			
		//Vacio las listas aux usadas en el metodo
		figuritasMiasIntercambiables.clear();
		figuritasOtraPersonaOfrecidas.clear();
	}
}

	public void realizarIntercambio(LinkedList<Integer> figuritasMiasPersonaOfrecidas, LinkedList<Integer> figuritasOtraPersonaOfrecidas, Persona otraPersona) throws Exception{
	for(int i = 0; i < figuritasOtraPersonaOfrecidas.size(); i++){
		insertarFiguritaEnAlbum(figuritasOtraPersonaOfrecidas.get(i));
	}
	for(int i = 0; i< figuritasMiasPersonaOfrecidas.size(); i++) {
		otraPersona.insertarFiguritaEnAlbum(figuritasMiasPersonaOfrecidas.get(i));
	}
}

	public void eliminarDeRepetidasFiguExtraidas(LinkedList<Integer> figuritasParaEliminarDeRepetidas){
		LinkedList<Integer> FigusParaRegalar = getFiguritasRepetidas();
		for(int i = 0; i < figuritasParaEliminarDeRepetidas.size(); i++) {
			if(FigusParaRegalar.contains(figuritasParaEliminarDeRepetidas.get(i)))
				FigusParaRegalar.remove(figuritasParaEliminarDeRepetidas.get(i));
	}
}

	public boolean hayRepetidas()
	{ return getFiguritasRepetidas().size() != 0; }

	// Getter & Setter

	public int getId() 
	{ return id; }
	
	public Album getAlbum()
	{ return miAlbum; }

	public void setAlbum(int cantidadFiguritas, int cantidadFiguritasPorPaquete) 
	{ this.miAlbum = new Album(id, cantidadFiguritas, cantidadFiguritasPorPaquete); }
	
	public void setAlbum() 
	{ this.miAlbum = new Album(id); }
	
	public LinkedList<Integer> getMisFiguritas()
	{ return miAlbum.getFiguritasDeAlbum(); }

	public LinkedList<Integer> getFiguritasRepetidas()
	{ return miAlbum.getFiguritasRepetidas(); }

	@Override
	public String toString(){
	StringBuilder sb = new StringBuilder();
		
	sb.append("Persona [id= ")
		.append(id)
		.append(", Figuritas conseguidas= ")
		.append(miAlbum.getFiguritasDeAlbum().size())
		.append(" de  ")
		.append(miAlbum.getCantFiguritasTotal())
		.append("]");

	return  sb.toString();
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
