package modelo;

import interfaces.Simulacion;

public class FabricaDeSimulaciones {

	public static Simulacion getSimulacion(String nombreDeSimulacion, int valorFiguritas){
	if(nombreDeSimulacion == null) {
		throw new NullPointerException("Nombre de solicitud de simulacion no puede ser nulo.");
	} else if(nombreDeSimulacion.equals("unaPersona")) {
		Simulacion simulacion = new SimulacionUnaPersona(valorFiguritas);
		return simulacion;
	}
	return null;
}

	public static Simulacion getSimulacion(String nombreDeSimulacion, 
	int valorFiguritas, 
	int cantPersonas){
	if(nombreDeSimulacion == null) {
		throw new NullPointerException("Nombre de solicitud de simulacion no puede ser nulo.");
	} else if(nombreDeSimulacion.equals("nPersonasRegalo")) {
			Simulacion simulacion = new SimulacionVariasPersonaRegalo(valorFiguritas, cantPersonas);
			return simulacion;
	} else if(nombreDeSimulacion.equals("nPersonasIntercambio")) {
			Simulacion simulacion = new SimulacionVariasPersonaRegalo(valorFiguritas, cantPersonas);
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
