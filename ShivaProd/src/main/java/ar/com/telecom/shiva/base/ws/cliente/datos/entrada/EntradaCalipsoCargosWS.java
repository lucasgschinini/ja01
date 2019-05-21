package ar.com.telecom.shiva.base.ws.cliente.datos.entrada;

import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOperacionCargoEnum;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCargoEntradaCargosWs;

/**
 * @author u573005
 * Sprint 5
 */
@SuppressWarnings("serial")
public class EntradaCalipsoCargosWS extends EntradaWS {
	
	protected MensajeServicioEnum tipoMensaje;
	
	protected String idOperacion;
	protected String idOperacionDescobroMensajeria;
	protected String idTransaccion;
	protected String numeroTransaccion;
	
	protected String usuarioCobrador;
	protected TipoOperacionCargoEnum tipoOperacion;
	protected SiNoEnum modoOperacion;	
	protected DetalleCargoEntradaCargosWs detalleCargo = new DetalleCargoEntradaCargosWs();
	
	/*******************************************************************
	 * Getters & Setters
	 ******************************************************************/
	public String getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(String idOperacion) {
		this.idOperacion = idOperacion;
	}	
	public String getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	
	public String getUsuarioCobrador() {
		return usuarioCobrador;
	}
	public void setUsuarioCobrador(String usuarioCobrador) {
		this.usuarioCobrador = usuarioCobrador;
	}
	public TipoOperacionCargoEnum getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(TipoOperacionCargoEnum tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public SiNoEnum getModoOperacion() {
		return modoOperacion;
	}
	public void setModoOperacion(SiNoEnum modoOperacion) {
		this.modoOperacion = modoOperacion;
	}
	public DetalleCargoEntradaCargosWs getDetalleCargo() {
		return detalleCargo;
	}
	public void setDetalleCargo(DetalleCargoEntradaCargosWs detalleCargo) {
		this.detalleCargo = detalleCargo;
	}
	public String getNumeroTransaccion() {
		return numeroTransaccion;
	}
	public void setNumeroTransaccion(String numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}
	public MensajeServicioEnum getTipoMensaje() {
		return tipoMensaje;
	}
	public void setTipoMensaje(MensajeServicioEnum tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}
	public String getIdOperacionDescobroMensajeria() {
		return idOperacionDescobroMensajeria;
	}
	public void setIdOperacionDescobroMensajeria(
			String idOperacionDescobroMensajeria) {
		this.idOperacionDescobroMensajeria = idOperacionDescobroMensajeria;
	}
}
