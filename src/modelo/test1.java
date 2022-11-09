package modelo;

import interfaces.Simulacion;
import utilidades.ObservadorPorConsola;
public class test1 {

	public static void main(String[] args) throws Exception {
		Simulacion sim = new SimulacionVariasPersonasIntercambio(3 ,200, 586, 5);
		sim.registrarObservador(new ObservadorPorConsola(sim));
		sim.iniciarSimulacion();
		//FabricaDeSimulaciones.generarSimulacionesThread(sim, 0);
	
	}
	
}
