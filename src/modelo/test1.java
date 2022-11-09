package modelo;

import interfaces.Simulacion;
import utilidades.ObservadorPorConsola;
public class test1 {

	public static void main(String[] args) throws Exception {
		
		Simulacion sim = FabricaDeSimulaciones.getSimulacion(false, "nPersonasRegalo", 3, 1, 0,0);
		//Simulacion sim = new SimulacionVariasPersonasIntercambio(3, 1);
		sim.registrarObservador(new ObservadorPorConsola(sim));
		sim.iniciarSimulacion();
		//FabricaDeSimulaciones.generarSimulacionesThread(sim, 2);
		
//		sim.iniciarSimulacion();
	}
	
}
