package modelo;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import interfaces.Simulacion;
import utilidades.ObservadorPorConsola;

public class SistemaDeSimulacion extends SwingWorker<Integer, Integer>{

	private Integer iteraciones;
	private Simulacion simulacion;
	private String nombreDeSimulacion;
	private int precioPorPaquete;
	private int cantidadDeFiguritasTotal;
	private int cantidadPorPaquete;
	private int cantPesonasParaSimulacion;
	private JTextField resultadoFinal;
	private JPanel pantallaLoading;
	private JPanel pantallaResultado;

	private LinkedList<Integer> resultadosDeSimulacion;
	
	public SistemaDeSimulacion(
			String nombreDeSimulacion,
			int cantPesonasParaSimulacion,
			int cantidadDeIteraciones,
			int precioPorPaquete,
			int cantidadDeFiguritasTotal,
			int cantidadPorPaquete,
			JTextField resultadoFinal,
			JLabel LblResultado,
			JPanel pantallaLoading,
			JPanel pantallaResultado)
		{
		resultadosDeSimulacion = new LinkedList<Integer>();
		iteraciones = cantidadDeIteraciones;
		this.precioPorPaquete = precioPorPaquete;
		this.nombreDeSimulacion = nombreDeSimulacion;
		this.cantidadPorPaquete = cantidadPorPaquete;
		this.cantidadDeFiguritasTotal = cantidadDeFiguritasTotal;
		this.cantPesonasParaSimulacion = cantPesonasParaSimulacion;
		this.resultadoFinal = resultadoFinal;
		this.pantallaLoading = pantallaLoading;
		this.pantallaResultado = pantallaResultado;
}

	@Override
	protected Integer doInBackground() throws Exception {
		int promedio = iniciarSimulacion();
		return promedio;
	}
	
	@Override
	public void done(){
		
		if(this.isCancelled() == false){
			try {
				if(cantPesonasParaSimulacion != 1) {
					pantallaLoading.setVisible(false);
					pantallaResultado.setVisible(true);
					resultadoFinal.setText(get().toString());
					System.out.print("Promedio obtenido desde el get: " + get() / cantPesonasParaSimulacion + " \n");
					
				}
				else{
					pantallaLoading.setVisible(false);
					pantallaResultado.setVisible(true);
					resultadoFinal.setText(get().toString());
					System.out.print("Promedio obtenido desde el get: " + get().toString() + " \n");
					
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
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
		int promedioDeGasto = calcularPromedioDeGasto(gastoTotalEnPaquetes);
		
		return promedioDeGasto;
}

	public int sumarGastoTotal(){
		int gastoTotal = 0;
		for (int resultado: resultadosDeSimulacion){
			gastoTotal = gastoTotal + resultado;
		}
		return gastoTotal;
}

	public int calcularPromedioDeGasto(int gastoTotalEnPaquetes){
		return (gastoTotalEnPaquetes / resultadosDeSimulacion.size());
}
	
}
