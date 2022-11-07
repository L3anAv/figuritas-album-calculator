package modelo;

import interfaces.Simulacion;

public class FabricaDeSimulaciones {

	public static Simulacion getSimulacion(boolean config,
	 String nombreDeSimulacion, 
	int precioPorPaquete,
	int cantidadFiguritasTotal, 
	int cantidadFiguritasPorPaquete){
	if(nombreDeSimulacion == null){
		throw new NullPointerException("Nombre de solicitud de simulacion no puede ser nulo.");
	}else if(nombreDeSimulacion.equals("unaPersona") && !config){
		Simulacion simulacion = new SimulacionUnaPersona(precioPorPaquete);
		return simulacion;
	}else if(nombreDeSimulacion.equals("unaPersona") && config){
		Simulacion simulacion = new SimulacionUnaPersona(precioPorPaquete, cantidadFiguritasTotal,cantidadFiguritasPorPaquete );
		return simulacion;
	}
	return null;
}

	public static Simulacion getSimulacion(boolean config,
	String nombreDeSimulacion, 
	int cantPersonas,
	int precioPorPaquete, 
	int cantidadFiguritasTotal,
	int cantidadFiguritasPorPaquete){
	if(nombreDeSimulacion == null) {
		throw new NullPointerException("Nombre de solicitud de simulacion no puede ser nulo.");
	} else if(nombreDeSimulacion.equals("nPersonasRegalo") && !config) {
		Simulacion simulacion = new SimulacionVariasPersonaRegalo(precioPorPaquete, cantPersonas);
		return simulacion;
	}else if(nombreDeSimulacion.equals("nPersonasRegalo") && config) {
		Simulacion simulacion = new SimulacionVariasPersonaRegalo(precioPorPaquete, cantPersonas,cantidadFiguritasTotal,cantidadFiguritasPorPaquete);
		return simulacion;
	}else if(nombreDeSimulacion.equals("nPersonasIntercambio")) {
		Simulacion simulacion = new SimulacionVariasPersonasIntercambio(precioPorPaquete, cantPersonas);
		return simulacion;
	}else if(nombreDeSimulacion.equals("nPersonasIntercambio") && config){
		Simulacion simulacion = new SimulacionVariasPersonasIntercambio(precioPorPaquete, cantPersonas,cantidadFiguritasTotal,cantidadFiguritasPorPaquete);
		return simulacion;
	}
		return null;
}

	public String getTodasLasSimulaciones(){
	StringBuilder simulaciones = new StringBuilder();
		simulaciones.append("Las simulaciones son: \n")
		.append("unaPersona \n")
		.append("nPersonasRegalo \n")
		.append("nPersonasIntercambio \n");
	return simulaciones.toString();
}

}
