package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import modelo.Album;

public class AlbumTest {

	private Album miAlbum;

	@Before
	public void inicializacionDeAlbum(){
		miAlbum = new Album(1);
}

	@Test /* Testeo de metodo que comprueba si existe una figurita en el album False*/ 
	public void existeFiguritaFalse() throws Exception
	{ 
		miAlbum.ingresarFigurita(2);
		miAlbum.ingresarFigurita(3);
		assertFalse(miAlbum.existeFiguritaEnAlbum(1)); 
	}

	@Test /* Testeo de metodo que insgresa figurita en el album */
	public void ingresandoFiguritaEnAlbum(){

	try { miAlbum.ingresarFigurita(1); } 
	catch (Exception e) { e.printStackTrace();}
	
	assertTrue(miAlbum.existeFiguritaEnAlbum(1));
}

	@Test /* Testeo de metodo que comprubea que el album de figurita esta completo True */
	public void albumEstaCompletoTrue() throws Exception{
		
		for(int i = 1; i < 638; i++) {
			miAlbum.ingresarFigurita(i);
		}

	assertTrue(miAlbum.estaCompletoAlbum());
}

	@Test /* Testeo de metodo que comprubea que el album de figurita esta completo False */
	public void albumEstaCompletoFalse() throws Exception
	{
		for(int i = 1; i <= 200; i++)
			miAlbum.ingresarFigurita(i);
		
		assertFalse(miAlbum.estaCompletoAlbum()); 
	}

	@Test(expected = Exception.class)
	public void albumIngresoValorInvalido() throws Exception{
		miAlbum.ingresarFigurita(0);
}
}
