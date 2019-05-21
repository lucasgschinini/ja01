package ar.com.telecom.shiva.base.ws.cliente.datos.entrada;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCTAoNotaCredito;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleFactura;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.MedioPagoTralasdo;

@SuppressWarnings("serial")
public class EntradaCalipsoApropiacionWS extends EntradaWS {
	
	protected MensajeServicioEnum tipoApropiacion;
	protected Long idOperacion;
	protected Integer numeroTransaccion;
	protected Integer idTransaccion;
	protected String usuarioCobrador;
	protected SiNoEnum modoOperacion;	
	protected MonedaEnum monedaOperacion;
	protected Date fechaCobranza;
	
	protected DetalleFactura detalleFactura = new DetalleFactura();
	protected List<DetalleCTAoNotaCredito> listaCtaONotaCredito = new ArrayList<DetalleCTAoNotaCredito>();
	protected List<MedioPagoTralasdo> listaMedioPago = new ArrayList<MedioPagoTralasdo>();

	
	/*******************************************************************
	 * Getters & Setters
	 ******************************************************************/

	public Long getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}
	
	public Integer getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(Integer idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	
	public String getUsuarioCobrador() {
		return usuarioCobrador;
	}
	public void setUsuarioCobrador(String usuarioCobrador) {
		this.usuarioCobrador = usuarioCobrador;
	}
	
	public DetalleFactura getDetalleFactura() {
		return detalleFactura;
	}
	public void setDetalleFactura(DetalleFactura detalleFactura) {
		this.detalleFactura = detalleFactura;
	}
	
	public List<DetalleCTAoNotaCredito> getListaCtaONotaCredito() {
		return listaCtaONotaCredito;
	}
	public void setListaCtaONotaCredito(
			List<DetalleCTAoNotaCredito> listaCtaONotaCredito) {
		this.listaCtaONotaCredito = listaCtaONotaCredito;
	}
	
	public List<MedioPagoTralasdo> getListaMedioPago() {
		return listaMedioPago;
	}
	public void setListaMedioPago(List<MedioPagoTralasdo> listaMedioPago) {
		this.listaMedioPago = listaMedioPago;
	}
	public MensajeServicioEnum getTipoApropiacion() {
		return tipoApropiacion;
	}
	public void setTipoApropiacion(MensajeServicioEnum tipoApropiacion) {
		this.tipoApropiacion = tipoApropiacion;
	}
	public Integer getNumeroTransaccion() {
		return numeroTransaccion;
	}
	public void setNumeroTransaccion(Integer numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}
	public SiNoEnum getModoOperacion() {
		return modoOperacion;
	}
	public void setModoOperacion(SiNoEnum modoOperacion) {
		this.modoOperacion = modoOperacion;
	}
	public MonedaEnum getMonedaOperacion() {
		return monedaOperacion;
	}
	public void setMonedaOperacion(MonedaEnum monedaOperacion) {
		this.monedaOperacion = monedaOperacion;
	}
	public Date getFechaCobranza() {
		return fechaCobranza;
	}
	public void setFechaCobranza(Date fechaCobranza) {
		this.fechaCobranza = fechaCobranza;
	}	
	
}
