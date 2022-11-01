package modelo;

import util.ObservadorPorConsola;

public class test1 {

	public static void main(String[] args) {
		SimulacionVariasPersonaRegalo sim = new SimulacionVariasPersonaRegalo(1, 1);
		sim.registrarObservador(new ObservadorPorConsola(sim));
		
		sim.iniciarSimulacion();
	}

	
}
