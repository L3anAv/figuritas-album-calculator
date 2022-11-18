package test;

import static org.junit.Assert.*;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import modelo.Persona;

public class PersonaTest {

	private Persona persona1;
	private Persona persona2;

	@Before /* > Creacion de personas para testear metodos */
	public void seteo(){		
	persona1 = new Persona(1);
	persona2 = new Persona(2);

		//Tiene repetida la figurita: 7 y 8
		//No tiene la figurita: 5 y 6
		try{
			persona1.insertarFiguritaEnAlbum(1);
			persona1.insertarFiguritaEnAlbum(2);
			persona1.insertarFiguritaEnAlbum(3);
			persona1.insertarFiguritaEnAlbum(4);
			persona1.insertarFiguritaEnAlbum(7);
			persona1.insertarFiguritaEnAlbum(7);
			persona1.insertarFiguritaEnAlbum(8);
			persona1.insertarFiguritaEnAlbum(8);
			
			//Tiene repetida la figurita: 5 y 6
			//No Tiene la figurita: 7 y 8
			persona2.insertarFiguritaEnAlbum(1);
			persona2.insertarFiguritaEnAlbum(2);
			persona2.insertarFiguritaEnAlbum(3);
			persona2.insertarFiguritaEnAlbum(4);
			persona2.insertarFiguritaEnAlbum(5);
			persona2.insertarFiguritaEnAlbum(5);
			persona2.insertarFiguritaEnAlbum(6);
			persona2.insertarFiguritaEnAlbum(6);
		} 
		catch (Exception e) 
			{ e.printStackTrace(); }
		
		
		
}
	
	@Test /* > Testo de que la persona 1 se crean con su ID correcto */
	public void crearPersonaTrue()
	{	assertTrue(persona1.getId() == 1); }
	
	@Test /* > Testo de que la persona 2 no tiene un ID incorrecto */
	public void crearPersonaFalse() 
	{ assertFalse(persona2.getId() == 3); }
	
	@Test /* > Testo del metodo que controla si una persona tiene una figurita en su album: True */
	public void tieneFiguritaTrue() 
	{ assertTrue(persona1.tieneFigurita(1)); }
	
	@Test /* > Testo del metodo que controla si una persona tiene una figurita en su album: False */
	public void tieneFiguritaFalse() 
	{ assertFalse(persona1.tieneFigurita(5)); }
	
	@Test /* > Testeo de que la persona no tenga en su album una figurita antes de realizar un regalo. */
	public void NoexisteFiguritaRepetidaAntesDeRegalar() 
	{ assertFalse(persona2.tieneFigurita(7)); }
	
	@Test /* Realizo regalo de figuritas repetidas de la persona 1 a la persona 2,
	ademas de testear que el regalo sea efectuado correctamente */
	public void RegalarFiguritas() throws Exception{
	persona1.regalarFiguritas(persona2);
	assertTrue(persona2.tieneFigurita(7));
}

/* > Testeos de que la persona 1 ya no tenga las figuritas repetidas que ya regalo. */
	@Test 
	public void personaNoContengaFiguritaRegalada7() throws Exception {
	persona1.regalarFiguritas(persona2);
	assertFalse(persona1.existeFiguritaRepetida(7));
}

	@Test 
	public void personaNoContengaFiguritaRegalada8() throws Exception{
	persona1.regalarFiguritas(persona2);
	assertFalse(persona1.existeFiguritaRepetida(8));
}

}
