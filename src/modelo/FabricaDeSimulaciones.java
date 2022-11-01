package modelo;

import interfaces.Simulacion;

public class FabricaDeSimulaciones {
	
	private Simulacion simulacion;
	
	public Simulacion getSimulacion(String nombreDeSimulacion, int valorFiguritas) 
	{
	if(nombreDeSimulacion == null)
		throw new NullPointerException("Nombre de solicitud de simulacion no puede ser nulo.");
	
	if(nombreDeSimulacion.equals("unaPersona")) 
	{
	simulacion = new SimulacionUnaPersona(valorFiguritas);
	return simulacion;
	}
		return simulacion;
	}
	
	public Simulacion getSimulacion(String nombreDeSimulacion, int valorFiguritas, int cantPersonas) 
	{
	if(nombreDeSimulacion == null)
		throw new NullPointerException("Nombre de solicitud de simulacion no puede ser nulo.");
	
	if(nombreDeSimulacion.equals("nPersonasRegalo")) 
	{
	simulacion = new SimulacionVariasPersonaRegalo(valorFiguritas, cantPersonas);
	return simulacion;
	}
	else if(nombreDeSimulacion.equals("nPersonasIntercambio"))
	{
	simulacion = new SimulacionVariasPersonaRegalo(valorFiguritas, cantPersonas);
	return simulacion;
	}
		return simulacion;
	}
	
	public String[] getTodasLasSimulaciones()
	{
	String[] simulaciones = {"unaPersona","nPersonasRegalo","nPersonasIntercambio"};
		return simulaciones;
	}
}
