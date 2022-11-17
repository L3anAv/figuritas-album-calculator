package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

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
	@SuppressWarnings("unchecked")
	@Test(expected = IllegalArgumentException.class)
	public void insertarFiguritaNegativa() throws Exception {
		
		SimulacionUnaPersona sim = new SimulacionUnaPersona(200, 11, 1);
		LinkedList paquete = new LinkedList<Integer>();
		paquete.add(-1);
		
		sim.rellenarAlbum(paquete);
			
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void insertarFiguritasHastaTotal() throws Exception {
		SimulacionUnaPersona sim = new SimulacionUnaPersona(200, 10, 1);
		LinkedList paquete1 = new LinkedList<Integer>();
		LinkedList paquete2 = new LinkedList<Integer>();
		
		paquete1.add(1);
		paquete1.add(2);
		paquete1.add(3);
		paquete1.add(4);
		paquete1.add(5);
		
		paquete2.add(2);
		paquete2.add(6);
		paquete2.add(7);
		paquete2.add(8);
		paquete2.add(9);
		
		sim.rellenarAlbum(paquete1);
		sim.rellenarAlbum(paquete2);
		assertTrue(sim.satisfactorio());
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void retornarGastoPromedio() throws Exception {
		SimulacionUnaPersona sim = new SimulacionUnaPersona(200, 10, 1);
		LinkedList paquete1 = new LinkedList<Integer>();
		LinkedList paquete2 = new LinkedList<Integer>();
		
		paquete1.add(1);
		paquete1.add(2);
		paquete1.add(3);
		paquete1.add(4);
		paquete1.add(5);
		
		paquete2.add(2);
		paquete2.add(6);
		paquete2.add(7);
		paquete2.add(8);
		paquete2.add(9);
		
		sim.rellenarAlbum(paquete1);
		sim.rellenarAlbum(paquete2);
		
		int cuenta = sim.getPaquetesAbiertos()* sim.getPrecioPaquete();
		
		assertEquals(cuenta, 400);
		
		
	}
	
	
	
}
