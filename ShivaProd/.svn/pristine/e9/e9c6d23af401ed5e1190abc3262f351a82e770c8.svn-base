/**
 * 
 */
package ar.com.telecom.shiva.negocio.dto.cobros.simulacion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author u564027
 *
 */
public class ResultadoSimulacion {

	private String idOperacionShiva;
	
	private Long idOperacion;
	private Integer numeroTransaccion;
	private Integer numeroTransaccionFicticio;
	private List<RespuestaInvocacion> listaRespuestasInvocacion = new ArrayList<RespuestaInvocacion>();
	
	/**
	 * Este metodo será sobrecargado por cada medio de pago que participe en 
	 * apropiacion de cobradores, y será utilizado para poder sincronizar la respuesta del cobrador 
	 * dentro de la transaccion
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
	 * @return the listaRespuestasInvocacion
	 */
	public List<RespuestaInvocacion> getListaRespuestasInvocacion() {
		return listaRespuestasInvocacion;
	}
	/**
	 * @param listaRespuestasInvocacion the listaRespuestasInvocacion to set
	 */
	public void setListaRespuestasInvocacion(
			List<RespuestaInvocacion> listaRespuestasInvocacion) {
		this.listaRespuestasInvocacion = listaRespuestasInvocacion;
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

	/**
	 * @return the numeroTransaccionFicticio
	 */
	public Integer getNumeroTransaccionFicticio() {
		return numeroTransaccionFicticio;
	}

	/**
	 * @param numeroTransaccionFicticio the numeroTransaccionFicticio to set
	 */
	public void setNumeroTransaccionFicticio(Integer numeroTransaccionFicticio) {
		this.numeroTransaccionFicticio = numeroTransaccionFicticio;
	}

}
