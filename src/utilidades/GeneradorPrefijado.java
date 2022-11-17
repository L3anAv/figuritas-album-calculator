package utilidades;

import interfaces.Generador;

public class GeneradorPrefijado implements Generador {

	private int rango;
	private int indice;
	
	public GeneradorPrefijado(int rango) {
		
		this.indice = 1;
		this.rango = rango;
	}
	
	@Override
	public int nextInt(int rango) {
		if(indice >= this.rango) {
			return this.rango;
		}
		return indice++;
	}

	@Override
	public int nextIntCExclusion(int rango, int num) {
		// TODO Auto-generated method stub
		return 0;
	}

}
