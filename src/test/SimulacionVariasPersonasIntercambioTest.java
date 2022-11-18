package test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import interfaces.Generador;
import interfaces.Simulacion;
import modelo.Persona;
import modelo.SimulacionVariasPersonaRegalo;
import modelo.SimulacionVariasPersonasIntercambio;
import utilidades.GeneradorPrefijado;

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
	
	@SuppressWarnings("unchecked")
	@Test(expected = IllegalArgumentException.class)
	public void insertarFiguritaNegativa() throws Exception {
		SimulacionVariasPersonasIntercambio sim = new SimulacionVariasPersonasIntercambio(2, 200, 11, 1);
		LinkedList paquete = new LinkedList<Integer>();
		paquete.add(-1);	
		sim.rellenarAlbumes_Testing(paquete, sim.getPersonas().get(0));	
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void insertarFiguritasHastaCompletar() throws Exception {
		
		SimulacionVariasPersonasIntercambio sim = new SimulacionVariasPersonasIntercambio(1, 200, 10, 5);
		
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
	
	
	
	@Test
	public void generarPaquetesEIntercambiar() throws Exception {
		
		SimulacionVariasPersonasIntercambio sim = new SimulacionVariasPersonasIntercambio(1, 200, 30, 5);
		
		
		LinkedList paqPersona1 = generarPaquetes(1, 20);
		LinkedList paqAux1 = generarPaquetes(21, 25);
		LinkedList paqPersona2 = generarPaquetes(1,20);
		LinkedList paqAux2 = generarPaquetes(26, 29);
		Persona p1 = sim.getPersonas().get(0);
		Persona p2 = sim.getPersonas().get(1);
		
		sim.rellenarAlbumes_Testing(paqPersona1, p1);
		sim.rellenarAlbumes_Testing(paqAux1, p1);
		sim.rellenarAlbumes_Testing(paqAux1, p1);
		
		sim.rellenarAlbumes_Testing(paqPersona2, p2);
		sim.rellenarAlbumes_Testing(paqAux2, p2);
		sim.rellenarAlbumes_Testing(paqAux2, p2);
		
		
		sim.intercambiarRepetidas_Testing(p1, p2);

		assertTrue(sim.satisfactorio());
		
	}
	
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
