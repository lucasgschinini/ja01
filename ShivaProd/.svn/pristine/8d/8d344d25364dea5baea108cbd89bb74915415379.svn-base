package ar.com.telecom.shiva.negocio.dto.cobros.imputacion;

public class ResultadoImputacion {

	private String idOperacionShiva;
	
	private Long idOperacion;
	private Integer numeroTransaccion;
	
	private RespuestaInvocacion respuestaInvocacion = new RespuestaInvocacion();
	
	/**
	 * Este metodo será sobrecargado por cada medio de pago que participe en 
	 * apropiacion de cobradores, y será utilizado para poder sincronizar la respuesta del cobrador 
	 * dentro de la transaccion, luego de una apropiacion
	 * 
	 * @return
	 */
	public String getIdBusquedaRespuestaCobrador() {
		return null;
	}

	/**
	 * @return the idOperacion
	 */
	public Long getIdOperacion() {
		return this.idOperacion;
	}
	
	/**
	 * @return the numeroTransaccion
	 */
	public Integer getNumeroTransaccion() {
		return numeroTransaccion;
	}

	/**
	 * @return the idOperacionShiva
	 */
	public String getIdOperacionShiva() {
		return idOperacionShiva;
	}

	/**
	 * @param idOperacionShiva the idOperacionShiva to set
	 */
	public void setIdOperacionShiva(String idOperacionShiva) {
		this.idOperacionShiva = idOperacionShiva;
		
		String cadenaNumeroOperacion = idOperacionShiva.substring(0, 7);
		String cadenaNumeroTransaccion = idOperacionShiva.substring(8);
		
		this.idOperacion = new Long(cadenaNumeroOperacion);
		this.numeroTransaccion = new Integer(cadenaNumeroTransaccion);
	}

	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}

	public void setNumeroTransaccion(Integer numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}

	public RespuestaInvocacion getRespuestaInvocacion() {
		return respuestaInvocacion;
	}

	public void setRespuestaInvocacion(RespuestaInvocacion respuestaInvocacion) {
		this.respuestaInvocacion = respuestaInvocacion;
	}
}
