package ar.com.telecom.shiva.base.jms.datos.entrada;

import ar.com.telecom.shiva.base.dto.JMS;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicDetalleGeneracionCargos;

@SuppressWarnings("serial")
public class MicGeneracionCargoEntrada extends JMS {
	
	//Para guardar los mensajes
	private MensajeServicioEnum tipoMensaje;
	private Long idTransaccion;
	
	//Para enviar los mensajes
	private TipoInvocacionEnum tipoInvocacion;
	private SiNoEnum modoEjecucion;
	private Integer idOperacion;
	private Integer idOperacionDescobroMensajeria;
	private Integer idSecuencia;
	private String usuarioCobrador;
	
	private MicDetalleGeneracionCargos detalleGeneracionCargos = new MicDetalleGeneracionCargos();
	
	/*********************************************************************
	 * Getters & Setters
	 *********************************************************************/
	
	public TipoInvocacionEnum getTipoInvocacion() {
		return tipoInvocacion;
	}

	public void setTipoInvocacion(TipoInvocacionEnum tipoInvocacion) {
		this.tipoInvocacion = tipoInvocacion;
	}

	public SiNoEnum getModoEjecucion() {
		return modoEjecucion;
	}

	public void setModoEjecucion(SiNoEnum modoEjecucion) {
		this.modoEjecucion = modoEjecucion;
	}

	public Integer getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Integer idOperacion) {
		this.idOperacion = idOperacion;
	}

	public Integer getIdSecuencia() {
		return idSecuencia;
	}

	public void setIdSecuencia(Integer idSecuencia) {
		this.idSecuencia = idSecuencia;
	}

	public String getUsuarioCobrador() {
		return usuarioCobrador;
	}

	public void setUsuarioCobrador(String usuarioCobrador) {
		this.usuarioCobrador = usuarioCobrador;
	}

	public MicDetalleGeneracionCargos getDetalleGeneracionCargos() {
		return detalleGeneracionCargos;
	}

	public void setDetalleGeneracionCargos(
			MicDetalleGeneracionCargos detalleGeneracionCargos) {
		this.detalleGeneracionCargos = detalleGeneracionCargos;
	}

	public MensajeServicioEnum getTipoMensaje() {
		return tipoMensaje;
	}

	public void setTipoMensaje(MensajeServicioEnum tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}

	public Long getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(Long idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public Integer getIdOperacionDescobroMensajeria() {
		return idOperacionDescobroMensajeria;
	}

	public void setIdOperacionDescobroMensajeria(
			Integer idOperacionDescobroMensajeria) {
		this.idOperacionDescobroMensajeria = idOperacionDescobroMensajeria;
	}
	
}
