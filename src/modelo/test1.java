package modelo;

import interfaces.Generador;
import interfaces.Simulacion;
import utilidades.GeneradorPrefijado;
import utilidades.ObservadorPorConsola;
public class test1 {

	public static void main(String[] args) throws Exception {
	//		Simulacion sim = FabricaDeSimulaciones.getSimulacion(true, "nPersonasRegalo", 5, 1, 250,5);
		//sim.registrarObservador(new ObservadorPorConsola(sim));
		//sim.iniciarSimulacion();
	
	
		Simulacion sim = new SimulacionVariasPersonasIntercambio(1, 250, 150, 7);
		sim.registrarObservador(new ObservadorPorConsola(sim));

		sim.iniciarSimulacion();
	

	
}
}