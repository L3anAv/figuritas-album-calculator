package test;

import static org.junit.Assert.*;

import org.junit.Test;

import interfaces.Simulacion;

import modelo.SimulacionVariasPersonasIntercambio;

public class SimulacionVariasPersonasIntercambioTest {

	
	@Test
	public void creacionDeSimulacionTest() {
	
		Simulacion sim = new SimulacionVariasPersonasIntercambio(2, 200, 100, 5);
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void precioNegativo() {
		
		Simulacion sim = new SimulacionVariasPersonasIntercambio(2, -200, 100, 5);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cantidadTotalNegativa() {
		
		
		Simulacion sim = new SimulacionVariasPersonasIntercambio(2, 200, -1, 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cantidadEnPaqNegativa() {
		
		Simulacion sim = new SimulacionVariasPersonasIntercambio(2, 200, 100, -5);
		
		
	}
	
	
	
	
	
	
}
