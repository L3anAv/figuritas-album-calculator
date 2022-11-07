package interfaces;

import java.util.ArrayList;

import modelo.Persona;

public interface Simulacion {
	
	//Metodos
	public void registrarObservador(Observador obs);
	public int iniciarSimulacion() throws Exception;
	public int getIteracion();
	public ArrayList<Persona> getPersonas();
	public int getPaquetesAbiertos();
	public double promedioPaquetesXPersona();
	public void escribirLog();
	public void crearLog();
	public int getCostoActual();

}
