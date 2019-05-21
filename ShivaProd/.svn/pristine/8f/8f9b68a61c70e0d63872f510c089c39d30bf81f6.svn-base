package ar.com.telecom.shiva.negocio.executor.rto.cobros;

import ar.com.telecom.shiva.persistencia.modelo.ShvCobProcHilosCobros;

public class ImputacionCobroRto {
	
	private ShvCobProcHilosCobros cobroAProcesar;
	private int count;
	private int cantidadTransaccionesAProcesarPorHilo;
	private boolean hayQueEjecutarValidacionesDeCorte;
	
	/**
	 * 
	 * @param cobroAProcesar
	 */
	public ImputacionCobroRto(ShvCobProcHilosCobros cobroAProcesar) {
		this.cobroAProcesar = cobroAProcesar;
		this.count = 1;
		this.hayQueEjecutarValidacionesDeCorte = false;
	}

	/**
	 * 
	 * @param cobroAProcesar
	 * @param count
	 * @param cantidadTransaccionesAProcesarPorHilo
	 * @param hayQueEjecutarValidacionesDeCorte
	 */
	public ImputacionCobroRto(ShvCobProcHilosCobros cobroAProcesar, int count, int cantidadTransaccionesAProcesarPorHilo, boolean hayQueEjecutarValidacionesDeCorte) {
		this.cobroAProcesar = cobroAProcesar;
		this.count = count;
		this.cantidadTransaccionesAProcesarPorHilo = cantidadTransaccionesAProcesarPorHilo;
		this.hayQueEjecutarValidacionesDeCorte = hayQueEjecutarValidacionesDeCorte;
	}

	public ShvCobProcHilosCobros getCobroAProcesar() {
		return cobroAProcesar;
	}

	public int getCount() {
		return count;
	}

	public int getCantidadTransaccionesAProcesarPorHilo() {
		return cantidadTransaccionesAProcesarPorHilo;
	}

	/**
	 * @return the ejecutarValidacionesDeCorte
	 */
	public boolean hayQueEjecutarValidacionesDeCorte() {
		return hayQueEjecutarValidacionesDeCorte;
	}

	/**
	 * @param ejecutarValidacionesDeCorte the ejecutarValidacionesDeCorte to set
	 */
	public void setSayQueEjecutarValidacionesDeCorte(boolean hayQueEjecutarValidacionesDeCorte) {
		this.hayQueEjecutarValidacionesDeCorte = hayQueEjecutarValidacionesDeCorte;
	}
	
	
}
