package test;

import org.junit.Test;

import interfaces.Simulacion;
import modelo.SimulacionUnaPersona;
import modelo.SimulacionVariasPersonaRegalo;
import utilidades.ObservadorPorConsola;

public class SimulacionVariasPersonaRegaloTest {

	@Test
	public void testConObservadores() throws Exception {
		
	//SimulacionVariasPersonaRegalo sim = new SimulacionVariasPersonaRegalo(2, 1);
	//sim.registrarObservador(new ObservadorPorConsola(sim)); 
	//sim.iniciarSimulacion();
	
	}

	@Test
	public void creacionDeSimulacionTest() {
	
		Simulacion sim = new SimulacionVariasPersonaRegalo(2, 200, 100, 5);
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void precioNegativo() {
		
		Simulacion sim = new SimulacionVariasPersonaRegalo(2, -200, 100, 5);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cantidadTotalNegativa() {
		
		
		Simulacion sim = new SimulacionVariasPersonaRegalo(2, 200, -1, 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cantidadEnPaqNegativa() {
		
		Simulacion sim = new SimulacionVariasPersonaRegalo(2, 200, 100, -5);
		
		
	}



}
