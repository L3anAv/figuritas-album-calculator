package test;

import org.junit.Test;

import modelo.SimulacionVariasPersonaRegalo;
import utilidades.ObservadorPorConsola;

public class SimulacionVariasPersonaRegaloTest {

	@Test
	public void testConObservadores() throws Exception {
		
	SimulacionVariasPersonaRegalo sim = new SimulacionVariasPersonaRegalo(2, 1);
	sim.registrarObservador(new ObservadorPorConsola(sim)); 
	sim.iniciarSimulacion();
	
	}
}
