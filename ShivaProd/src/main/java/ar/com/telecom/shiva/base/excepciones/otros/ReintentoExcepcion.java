package ar.com.telecom.shiva.base.excepciones.otros;

import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;

/**
 * Excepcion del Reintento
 *
 */
public class ReintentoExcepcion extends Throwable {

	private static final long serialVersionUID = 1L;
	
	private Long idOperacion;
	private Integer idTransaccion;
	private SistemaEnum cobrador; 
	private TipoInvocacionEnum tipoInvocacion;
	private MensajeServicioEnum servicio;
	
	/**
	 * 
	 * @param idOperacion
	 * @param idTransaccion
	 * @param message
	 */
	public ReintentoExcepcion(Long idOperacion, Integer idTransaccion, String message) {
		super(message);
		this.idOperacion = idOperacion;
		this.idTransaccion = idTransaccion;
	}
	
	/**
	 * 
	 * @param idOperacion
	 * @param idTransaccion
	 * @param cobrador
	 * @param tipoInvocacion
	 * @param message
	 */
	public ReintentoExcepcion(Long idOperacion, Integer idTransaccion, SistemaEnum cobrador, TipoInvocacionEnum tipoInvocacion, String message) {
		super(message);
		this.idOperacion = idOperacion;
		this.idTransaccion = idTransaccion;
		this.cobrador = cobrador;
		this.tipoInvocacion = tipoInvocacion;
	}

	/**
	 * 
	 * @param idOperacion
	 * @param idTransaccion
	 * @param message
	 * @param cobrador
	 * @param servicio
	 */
	public ReintentoExcepcion(Long idOperacion, Integer idTransaccion, String message, SistemaEnum cobrador, MensajeServicioEnum servicio) {
		super(message);
		this.idOperacion = idOperacion;
		this.idTransaccion = idTransaccion;
		this.cobrador = cobrador;
		this.servicio = servicio;
	}

	/**
	 * 
	 * @return
	 */
	public Long getIdOperacion() {
		return idOperacion;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * 
	 * @return
	 */
	public SistemaEnum getCobrador() {
		return cobrador;
	}

	/**
	 * 
	 * @return
	 */
	public TipoInvocacionEnum getTipoInvocacion() {
		return tipoInvocacion;
	}

	public MensajeServicioEnum getServicio() {
		return servicio;
	}

	public void setServicio(MensajeServicioEnum servicio) {
		this.servicio = servicio;
	}
	
}
