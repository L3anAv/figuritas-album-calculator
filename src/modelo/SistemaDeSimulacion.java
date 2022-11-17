package modelo;

import java.util.LinkedList;
import interfaces.Simulacion;
import utilidades.ObservadorPorConsola;

public class SistemaDeSimulacion {
	
	private Integer iteraciones;
	private Simulacion simulacion;
	private String nombreDeSimulacion;
	private int precioPorPaquete;
	private int cantidadDeFiguritasTotal;
	private int cantidadPorPaquete;
	private boolean simulacionTerminada;
	private int cantPesonasParaSimulacion;
	private LinkedList<Integer> resultadosDeSimulacion;
	
	public SistemaDeSimulacion(
			String nombreDeSimulacion,
			int cantPesonasParaSimulacion,
			int cantidadDeIteraciones,
			int precioPorPaquete,
			int cantidadDeFiguritasTotal,
			int cantidadPorPaquete)
		{
		resultadosDeSimulacion = new LinkedList<Integer>();
		iteraciones = cantidadDeIteraciones;
		this.precioPorPaquete = precioPorPaquete;
		this.nombreDeSimulacion = nombreDeSimulacion;
		this.cantidadPorPaquete = cantidadPorPaquete;
		this.cantidadDeFiguritasTotal = cantidadDeFiguritasTotal;
		this.cantPesonasParaSimulacion = cantPesonasParaSimulacion;
}

	public int iniciarSimulacion() throws Exception{
		int i = 0;

		while(i < iteraciones){
			simulacion = FabricaDeSimulaciones.getSimulacion(
					nombreDeSimulacion,
					cantPesonasParaSimulacion,
					precioPorPaquete,
					cantidadDeFiguritasTotal,
					cantidadPorPaquete);
			simulacion.registrarObservador(new ObservadorPorConsola(simulacion));
			int gastoUnaSimulacion = simulacion.iniciarSimulacion();
			resultadosDeSimulacion.add(gastoUnaSimulacion);
			i++;
		}
		
		int gastoTotalEnPaquetes = sumarGastoTotal();
		System.out.print("\n gastoTotalEnPaquetes: " + gastoTotalEnPaquetes);
		int promedioDeGasto = calcularPromedioDeGasto(gastoTotalEnPaquetes);
		System.out.print("\n promedioDeGasto: " + promedioDeGasto);
		
		return promedioDeGasto;
}

	public int sumarGastoTotal(){
		int gastoTotal = 0;
		for (int resultado: resultadosDeSimulacion){
			gastoTotal += resultado;
		}
		return gastoTotal;
}
	
	public int calcularPromedioDeGasto(int gastoTotalEnPaquetes){
		return gastoTotalEnPaquetes / resultadosDeSimulacion.size();
}
	
	public Simulacion getSimulacion(){
		return simulacion;
}

}
