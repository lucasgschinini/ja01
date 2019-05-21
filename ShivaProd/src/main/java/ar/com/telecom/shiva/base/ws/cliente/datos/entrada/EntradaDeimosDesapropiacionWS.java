package ar.com.telecom.shiva.base.ws.cliente.datos.entrada;

/**
 * @author u573005
 * Sprint 5
 */
@SuppressWarnings("serial")
public class EntradaDeimosDesapropiacionWS extends EntradaWS {
	
	protected Integer idOperacionShiva;
	protected String usuarioCobrador;
	
	/*************************************************
	 * Getters & Setters
	 *************************************************/
	
	public String getUsuarioCobrador() {
		return usuarioCobrador;
	}
	public void setUsuarioCobrador(String usuarioCobrador) {
		this.usuarioCobrador = usuarioCobrador;
	}
	public Integer getIdOperacionShiva() {
		return idOperacionShiva;
	}
	public void setIdOperacionShiva(Integer idOperacionShiva) {
		this.idOperacionShiva = idOperacionShiva;
	}

}