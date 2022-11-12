package modelo;

import interfaces.Simulacion;
import utilidades.ObservadorPorConsola;
public class test1 {

	public static void main(String[] args) throws Exception {
		
//		Simulacion sim = FabricaDeSimulaciones.getSimulacion(false, "nPersonasRegalo", 5, 1, 250,5);
//		sim.registrarObservador(new ObservadorPorConsola(sim));
//		sim.iniciarSimulacion();
	
	
		Simulacion sim = new SimulacionVariasPersonaRegalo(1, 250, 250, 5);
		sim.registrarObservador(new ObservadorPorConsola(sim));
		sim.iniciarSimulacion();
	}
	
}
