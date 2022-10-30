package modelo;

import java.util.LinkedList;

public class SimulacionVariasPersonaRegalo implements Simulacion{
	
	private LinkedList<Persona> personas;
	private Album albumPersona;
	private int valorFiguritas;
	private int gastoTotal;
	
	public SimulacionVariasPersonaRegalo(int cantPersonas)
	{
		LinkedList<Persona> personas = new LinkedList<Persona>();
		for(int i = 0; i < cantPersonas; i++) {
			Persona persona = new Persona(i);
			personas.add(persona);
		}
	}
	
	@Override
	public int iniciarSimulacion() {
		// TODO Auto-generated method stub
		return 0;
	}
}
