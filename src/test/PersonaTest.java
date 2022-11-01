package test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import modelo.Persona;

public class PersonaTest {
	
	private Persona persona1;
	private Persona persona2;
	
	@Before
	public void seteo()
	{		
	persona1 = new Persona(1);
	persona2 = new Persona(2);
	
	//Tiene repetida la figurita: 7 y 8
	//No tiene la figurita: 5 y 6
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
	
	@Test
	public void crearPersonaTrue()
	{	
	assertTrue(persona1.getId() == 1);
	}
	
	@Test
	public void crearPersonaFalse() 
	{
	assertFalse(persona2.getId() == 3);
	}
	
	@Test
	public void NoexisteFiguritaRepetidaAntesDeRegalar() 
	{
		assertFalse(persona2.tieneFigurita(7));
	}
	
	@Test //Persona
	public void RegalarFiguritas() 
	{
	persona1.RegalarFiguritas(persona2);
	assertTrue(persona2.tieneFigurita(7));
	}
	
	@Test
	public void RegalarFiguritasNoContengaFiguritaRegalada7() 
	{
	persona1.RegalarFiguritas(persona2);
	assertFalse(persona1.existeFiguritaRepetida(7));
	}
	
	@Test
	public void RegalarFiguritasNoContengaFiguritaRegalada8() 
	{
	persona1.RegalarFiguritas(persona2);
	assertFalse(persona1.existeFiguritaRepetida(8));
	}
	
	
	
}
