package ar.com.telecom.shiva.negocio.servicios;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;

public interface IControlHilosCobrosServicio extends IServicio{
	
	/**
	 * Método que inicia el control de hilos  
	 * @param tiempoLimite
	 * @param estadosCobro
	 * @param destinatario
	 * @param destinatarioCC
	 * @param horaEnvioMailCobrosPendientes
	 * @throws NegocioExcepcion
	 */
	public void iniciarProcesoDeControlDeHilos(Integer tiempoLimite, String estadosCobro, String destinatario, String destinatarioCC, String horaEnvioMailCobrosPendientes) throws NegocioExcepcion;

}
