package modelo;

import interfaces.Simulacion;

public class FabricaDeSimulaciones{

	public static Simulacion getSimulacion( String nombreDeSimulacion, int cantPersonas, int precioPorPaquete,  int cantidadFiguritasTotal, int cantidadFiguritasPorPaquete){

	if(nombreDeSimulacion == null){
		throw new NullPointerException("El nombre de simulacion solictada no puede ser nulo.");
	}else if(nombreDeSimulacion.equals("unaPersona") && cantPersonas == 1){
		Simulacion simulacion = new SimulacionUnaPersona(precioPorPaquete, cantidadFiguritasTotal,cantidadFiguritasPorPaquete );
		return simulacion;
	}else if(nombreDeSimulacion.equals("nPersonasRegalo")){
		Simulacion simulacion = new SimulacionVariasPersonaRegalo(cantPersonas, precioPorPaquete,cantidadFiguritasTotal,cantidadFiguritasPorPaquete);
		return simulacion;
	}else if(nombreDeSimulacion.equals("nPersonasIntercambio")){
		Simulacion simulacion = new SimulacionVariasPersonasIntercambio(cantPersonas, precioPorPaquete, cantidadFiguritasTotal,cantidadFiguritasPorPaquete);
		return simulacion;
	}

	return null;
}

}
