package test;

import org.junit.Test;

import modelo.SimulacionVariasPersonaRegalo;
import util.ObservadorPorConsola;

public class SimulacionVariasPersonaRegaloTest {

	@Test
	public void testConObservadores() {
		
	SimulacionVariasPersonaRegalo sim = new SimulacionVariasPersonaRegalo(2, 1);
	sim.registrarObservador(new ObservadorPorConsola(sim));
	
	sim.iniciarSimulacion();
	
	}








}