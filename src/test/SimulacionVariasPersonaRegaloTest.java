package test;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import interfaces.Generador;
import interfaces.Simulacion;
import modelo.Persona;
import modelo.SimulacionVariasPersonaRegalo;
import utilidades.GeneradorPrefijado;
import utilidades.ObservadorPorConsola;

public class SimulacionVariasPersonaRegaloTest {

	@Test
	public void testConObservadores() throws Exception {
		
	SimulacionVariasPersonaRegalo sim = new SimulacionVariasPersonaRegalo(2, 1, 200, 5);
	sim.registrarObservador(new ObservadorPorConsola(sim)); 
	sim.iniciarSimulacion();
	
	}

	@Test
	public void creacionDeSimulacionTest() {
	
		@SuppressWarnings("unused")
		Simulacion sim = new SimulacionVariasPersonaRegalo(2, 200, 100, 5);
		
	}

	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void precioNegativo() {
		
		Simulacion sim = new SimulacionVariasPersonaRegalo(2, -200, 100, 5);
		
	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void cantidadTotalNegativa() {
		
		
		Simulacion sim = new SimulacionVariasPersonaRegalo(2, 200, -1, 5);
	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void cantidadEnPaqNegativa() {
		
		Simulacion sim = new SimulacionVariasPersonaRegalo(2, 200, 100, -5);
		
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test(expected = IllegalArgumentException.class)
	public void insertarFiguritaNegativa() throws Exception {
		
		SimulacionVariasPersonaRegalo sim = new SimulacionVariasPersonaRegalo(2, 200, 11, 1);
		LinkedList paquete = new LinkedList<Integer>();
		paquete.add(-1);
		
		sim.rellenarAlbum(paquete, sim.getPersonas().get(0));
			
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void insertarFiguritasHastaCompletar() throws Exception {
		
		SimulacionVariasPersonaRegalo sim = new SimulacionVariasPersonaRegalo(2, 200, 10, 5);
		
		LinkedList paquete1 = new LinkedList<Integer>();
		LinkedList paquete2 = new LinkedList<Integer>();
		Persona p1 = sim.getPersonas().get(0);
		Persona p2 = sim.getPersonas().get(1);
		
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

		sim.rellenarAlbumes_Testing(paquete1, p1);
		sim.rellenarAlbumes_Testing(paquete2, p1);
		sim.rellenarAlbumes_Testing(paquete1, p2);
		sim.rellenarAlbumes_Testing(paquete2, p2);

		
		assertTrue(sim.satisfactorio());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void generarPaquetesYRegalar() throws Exception {
		
		SimulacionVariasPersonaRegalo sim = new SimulacionVariasPersonaRegalo(2, 100, 24, 5);
		
		LinkedList paqPersona1 = generarPaquetes(1, 20);
		LinkedList paqAux = generarPaquetes(21, 23);
		LinkedList paqPersona2 = generarPaquetes(1,20);
		Persona p1 = sim.getPersonas().get(0);
		Persona p2 = sim.getPersonas().get(1);
		
		sim.rellenarAlbumes_Testing(paqPersona1, p1);
		sim.rellenarAlbumes_Testing(paqAux, p1);
		sim.rellenarAlbumes_Testing(paqAux, p1);
		
		sim.rellenarAlbumes_Testing(paqPersona2, p2);

		sim.compartirRepetidas_Testing(p1, p2);
	
		assertTrue(sim.satisfactorio());
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public LinkedList<Integer> generarPaquetes(int rangoIn ,int rangoFin){
		
		LinkedList ret = new LinkedList<Integer>();
		Generador generador = new GeneradorPrefijado(rangoIn, rangoFin);
		
		int cont = rangoIn ;
		
		while(cont <= rangoFin) {
			
			ret.add(generador.nextInt(rangoFin));
			cont++;
			
		}
		return ret;
	}
	
	
	
	
	
}
