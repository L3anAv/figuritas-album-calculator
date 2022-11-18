package utilidades;


import java.util.ArrayList;

import interfaces.Observador;
import interfaces.Simulacion;
import modelo.Persona;

public class ObservadorPorConsola implements Observador {
	
	private Simulacion sim;
	private ArrayList<Persona> lista;
	
	
	public ObservadorPorConsola(Simulacion sim){
		this.sim = sim;
		this.lista = sim.getPersonas();
}
	
	@Override
	public void notificar(){
	System.out.print("It: " + sim.getIteracion() + " Paquetes abiertos: " + sim.getPaquetesAbiertos() + " Promedio paquetes por Persona: "  + sim.promedioPaquetesXPersona() +  "\n");
		
	for(Persona p: lista){	
		System.out.println(p.toString());
	}

		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
