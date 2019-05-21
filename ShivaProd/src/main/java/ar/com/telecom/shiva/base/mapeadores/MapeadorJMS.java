package ar.com.telecom.shiva.base.mapeadores;

import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.jms.util.definicion.FormatoMensajeJMS;


public abstract class MapeadorJMS {

	private FormatoMensajeJMS defaultFormatoMensajeJMS;
	
	/**
	 * Verifico que si este mensaje corresponde al servicio 
	 * @param DTO
	 * @return
	 */
	public abstract boolean verificarServicio(String msj, boolean esSincronico) throws NegocioExcepcion;
	
	/**
	 * Verifico si la longitud del mensaje recibida es correcto
	 * @param DTO
	 * @return
	 */
	public abstract boolean verificarLongitudMsjRecibida(String msj, boolean esSincronico) throws NegocioExcepcion;
	
	/**
	 * JMS --> String msg a enviar
	 * @param DTO
	 * @return
	 */
	public abstract String serializar(JMS jms, boolean esSincronico) throws NegocioExcepcion;
	
	
	/**
	 * String --> JMS 
	 * @param msg recibido
	 * @return
	 */
	public abstract JMS deserializar(String msg, boolean camposSeteables, boolean esSincronico) throws NegocioExcepcion;

	
	public FormatoMensajeJMS getDefaultFormatoMensajeJMS() {
		return defaultFormatoMensajeJMS;
	}


	public void setDefaultFormatoMensajeJMS(FormatoMensajeJMS defaultFormatoMensajeJMS) {
		this.defaultFormatoMensajeJMS = defaultFormatoMensajeJMS;
	}

	
	
}
