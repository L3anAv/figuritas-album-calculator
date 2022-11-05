package modelo;

import interfaces.Simulacion;
import utilidades.ObservadorPorConsola;
public class test1 {

	public static void main(String[] args) {
		Simulacion sim = new SimulacionVariasPersonasIntercambio(3, 1);
		sim.registrarObservador(new ObservadorPorConsola(sim));
		sim.iniciarSimulacion();
	}
	
}
