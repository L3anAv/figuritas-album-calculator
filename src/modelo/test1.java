package modelo;

import interfaces.Simulacion;
import utilidades.ObservadorPorConsola;
public class test1 {

	public static void main(String[] args) throws Exception {
		
		
		
		
		
		
			Simulacion sim = new SimulacionVariasPersonasIntercambio(3, 1);
			sim.registrarObservador(new ObservadorPorConsola(sim));
			
			FabricaDeSimulaciones.generarSimulacionesThread(sim, 2);
			
			
//		sim.iniciarSimulacion();
	}
	
}
