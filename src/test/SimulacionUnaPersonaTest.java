package test;

import org.junit.Test;

import interfaces.Simulacion;
import modelo.SimulacionUnaPersona;
import utilidades.GeneradorPrefijado;
import utilidades.ObservadorPorConsola;

public class SimulacionUnaPersonaTest {

	@Test
	public void creacionDeSimulacionTest() {
	
		Simulacion sim = new SimulacionUnaPersona(200, 683, 5);
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void precioNegativo() {
		
		Simulacion sim = new SimulacionUnaPersona(-1, 683, 5);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cantidadTotalNegativa() {
		
		
		Simulacion sim = new SimulacionUnaPersona(200, -1, 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cantidadEnPaqNegativa() {
		
		Simulacion sim = new SimulacionUnaPersona(200, 683, -1);
		
		
	}
	
	@Test
	public void testingConGeneradorPrefijado() {
		
		SimulacionUnaPersona sim = new SimulacionUnaPersona(200, 11, 6);
		sim.setGenPrefijado(new GeneradorPrefijado(11));
		sim.registrarObservador(new ObservadorPorConsola(sim));
		
		try {
			sim.iniciarTesting();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
