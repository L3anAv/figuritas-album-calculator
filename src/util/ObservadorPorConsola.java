package util;

import interfaces.Observador;
import interfaces.Simulacion;
import modelo.Persona;

public class ObservadorPorConsola implements Observador {
	
	private Simulacion sim;
	
	public ObservadorPorConsola(Simulacion sim)
	{
		this.sim = sim;
	}
	
	@Override
	public void notificar()
	{
		System.out.print("It: " + sim.getIteracion());
		
		for(Persona p: sim.getPersonas()) {
			System.out.println(p.toString());
		}
	
		
		System.out.println();
	}
	
	
	
	
	
	
	
	
}
