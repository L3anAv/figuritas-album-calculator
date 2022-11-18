package test;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import interfaces.Generador;
import interfaces.Simulacion;
import modelo.SimulacionVariasPersonaRegalo;
import utilidades.GeneradorPrefijado;

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

	@SuppressWarnings("unchecked")
	@Test(expected = IllegalArgumentException.class)
	public void insertarFiguritaNegativa() throws Exception {
		
		SimulacionVariasPersonaRegalo sim = new SimulacionVariasPersonaRegalo(2, 200, 11, 1);
		LinkedList paquete = new LinkedList<Integer>();
		paquete.add(-1);
		
		sim.rellenarAlbum(paquete, sim.getPersonas().get(0));
			
	}
	
	@Test
	public void insertarFiguritasHastaCompletar() throws Exception {
		
		SimulacionVariasPersonaRegalo sim = new SimulacionVariasPersonaRegalo(2, 200, 11, 5);
		
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
		
		sim.rellenarAlbum(paquete1, sim.getPersonas().get(0));
		sim.rellenarAlbum(paquete2, sim.getPersonas().get(0));
		
		//Se considera que son cuatro paquetes distintos
		sim.rellenarAlbum(paquete1, sim.getPersonas().get(1));
		sim.rellenarAlbum(paquete2, sim.getPersonas().get(1));
		
		assertTrue(sim.satisfactorio());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void generarPaquetesYRegalar() throws Exception {
		
		SimulacionVariasPersonaRegalo sim = new SimulacionVariasPersonaRegalo(2, 100, 100, 5);
		
		LinkedList paqPersona1 = generarPaquetes(1, 50);
		LinkedList paqAux = generarPaquetes(1, 50);
		LinkedList paqPersona2 = generarPaquetes(51,100);
		
		
		//1 Paquete rellena el album y el otro va a la lista de repetidas
		sim.rellenarAlbum(paqPersona1, sim.getPersonas().get(0));
		sim.rellenarAlbum(paqAux, sim.getPersonas().get(0));
//		
		sim.getPersonas().get(0).getAlbum().ingresarFigurita(2);
		System.out.println(sim.getPersonas().get(0).getAlbum().getFiguritasRepetidas());
		
		sim.rellenarAlbum(paqPersona2, sim.getPersonas().get(1));
		sim.rellenarAlbum(paqPersona2, sim.getPersonas().get(1));
		
		sim.compartirRepetidas();
		System.out.println(sim.getPersonas().get(0).getFiguritasRepetidas());
		
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
