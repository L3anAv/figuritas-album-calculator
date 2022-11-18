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
	private int precioPorPaquete;
	private Simulacion simulacion;
	private int cantidadPorPaquete;
	private JPanel pantallaLoading;
	private JPanel pantallaResultado;
	private String nombreDeSimulacion;
	private int cantidadDeFiguritasTotal;
	private JTextField resultadoFinalxPersona;
	private Integer cantPesonasParaSimulacion;
	private JTextField resultadoFinalPromedioTotal;
	private LinkedList<Integer> resultadosDeSimulacion;

// > Constructor de sistema de simulacion

	public SistemaDeSimulacion( String nombreDeSimulacion, int cantPesonasParaSimulacion, int cantidadDeIteraciones, int precioPorPaquete, int cantidadDeFiguritasTotal, int cantidadPorPaquete,
			JTextField resultadoFinal,
			JTextField resultadoFinalxPersona,
			JLabel LblResultado,
			JPanel pantallaLoading,
			JPanel pantallaResultado){
		
		iteraciones = cantidadDeIteraciones;
		this.pantallaLoading = pantallaLoading;
		this.precioPorPaquete = precioPorPaquete;
		this.pantallaResultado = pantallaResultado;
		this.nombreDeSimulacion = nombreDeSimulacion;
		this.cantidadPorPaquete = cantidadPorPaquete;
		resultadosDeSimulacion = new LinkedList<Integer>();
		this.cantidadDeFiguritasTotal = cantidadDeFiguritasTotal;
		this.cantPesonasParaSimulacion = cantPesonasParaSimulacion;
		this.resultadoFinalPromedioTotal = resultadoFinal;
		this.resultadoFinalxPersona = resultadoFinalxPersona;
}

// > Metodos de SwingWorker

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
					resultadoFinalPromedioTotal.setText(get().toString());
					int resultado = (get() / cantPesonasParaSimulacion);
					String resultadoString = Integer.toString(resultado);
					resultadoFinalxPersona.setText(resultadoString);
				}
				else{
					pantallaLoading.setVisible(false);
					pantallaResultado.setVisible(true);
					resultadoFinalPromedioTotal.setText(get().toString());
					resultadoFinalxPersona.setText("----");
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
		}
	}
}

// > Metodo de clase

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
