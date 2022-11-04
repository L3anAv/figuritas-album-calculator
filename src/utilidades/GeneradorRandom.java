package utilidades;

import java.util.Random;

import interfaces.Generador;

public class GeneradorRandom implements Generador {

private Random _random;
	
	public GeneradorRandom()
	{
		_random = new Random();
	}
	
	
	@Override
	public int nextInt(int rango)
	{
		return _random.nextInt(rango);
	}


	@Override
	public int nextIntCExclusion(int rango, int num) {
		
		int r = _random.nextInt(rango);
		
		if(r == num) {
			
			if(r >= rango-1) {
				
				r = r -1;
				
			
			}
			else {
				r++;
			}
		
		}
		
		return r;
		
	}


	

}
