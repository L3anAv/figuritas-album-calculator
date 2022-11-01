package interfaces;

import java.util.ArrayList;

import modelo.Persona;

public interface Simulacion {

	public int iniciarSimulacion();

	public int getIteracion();
	public ArrayList<Persona> getPersonas();
	public int getPaquetesAbiertos();


}
