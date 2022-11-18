package interfaces;

import java.util.ArrayList;

import modelo.Persona;

public interface Simulacion {
	public void registrarObservador(Observador obs);
	public int iniciarSimulacion() throws Exception;
	public int getIteracion();
	public ArrayList<Persona> getPersonas();
	public double promedioPaquetesXPersona();
	public int getPaquetesAbiertos();
	public void escribirLog();
	public void crearLog();
}
