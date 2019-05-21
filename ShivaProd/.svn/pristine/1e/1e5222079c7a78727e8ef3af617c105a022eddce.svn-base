package ar.com.telecom.shiva.base.ws.cliente.datos.entrada;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.calipso.DetalleCTAoNotaCreditoDescobro;

@SuppressWarnings("serial")
public class EntradaCalipsoDescobroWS extends EntradaWS {
	
	protected MensajeServicioEnum tipoMensaje;
	
	protected Long idOperacion;
	protected Integer numeroTransaccion;
	protected Integer numeroTransaccionFicticio;
	protected Integer idTransaccion;
	protected String usuarioCobrador;
	protected TipoInvocacionEnum tipoOperacion;
	protected SiNoEnum modoOperacion;
	
	protected Long idOperacionDescobroMensajeria;
	
	//Detalle de Factura a revertir (Agrupador)
	protected BigInteger idCobranza;
	
	//Detalle de Cargos  a revertir (Agrupador)
	protected BigInteger idMovMer;
	
	//Lista de CTA o Nota de Crédito a revertir (Lista)
	protected List<DetalleCTAoNotaCreditoDescobro> listaCtaONotaCredito = new ArrayList<DetalleCTAoNotaCreditoDescobro>();
	
	private SiNoEnum facturarContracargoFactura;
	private String acuerdoFacturacionContracargoFactura;
	private SiNoEnum facturarContracargoCargo;
	private String acuerdoFacturacionContracargoCargo;
	
	/*******************************************************************
	 * Getters & Setters
	 ******************************************************************/
	
	public Long getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}
	public Integer getNumeroTransaccion() {
		return numeroTransaccion;
	}
	public void setNumeroTransaccion(Integer numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
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
	public SiNoEnum getModoOperacion() {
		return modoOperacion;
	}
	public void setModoOperacion(SiNoEnum modoOperacion) {
		this.modoOperacion = modoOperacion;
	}
	public List<DetalleCTAoNotaCreditoDescobro> getListaCtaONotaCredito() {
		return listaCtaONotaCredito;
	}
	public void setListaCtaONotaCredito(
			List<DetalleCTAoNotaCreditoDescobro> listaCtaONotaCredito) {
		this.listaCtaONotaCredito = listaCtaONotaCredito;
	}
	public SiNoEnum getFacturarContracargoFactura() {
		return facturarContracargoFactura;
	}
	public void setFacturarContracargoFactura(SiNoEnum facturarContracargoFactura) {
		this.facturarContracargoFactura = facturarContracargoFactura;
	}
	public String getAcuerdoFacturacionContracargoFactura() {
		return acuerdoFacturacionContracargoFactura;
	}
	public void setAcuerdoFacturacionContracargoFactura(
			String acuerdoFacturacionContracargoFactura) {
		this.acuerdoFacturacionContracargoFactura = acuerdoFacturacionContracargoFactura;
	}
	public SiNoEnum getFacturarContracargoCargo() {
		return facturarContracargoCargo;
	}
	public void setFacturarContracargoCargo(SiNoEnum facturarContracargoCargo) {
		this.facturarContracargoCargo = facturarContracargoCargo;
	}
	public String getAcuerdoFacturacionContracargoCargo() {
		return acuerdoFacturacionContracargoCargo;
	}
	public void setAcuerdoFacturacionContracargoCargo(
			String acuerdoFacturacionContracargoCargo) {
		this.acuerdoFacturacionContracargoCargo = acuerdoFacturacionContracargoCargo;
	}
	public TipoInvocacionEnum getTipoOperacion() {
		return tipoOperacion;
	}
	public void setTipoOperacion(TipoInvocacionEnum tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	public BigInteger getIdCobranza() {
		return idCobranza;
	}
	public void setIdCobranza(BigInteger idCobranza) {
		this.idCobranza = idCobranza;
	}
	public BigInteger getIdMovMer() {
		return idMovMer;
	}
	public void setIdMovMer(BigInteger idMovMer) {
		this.idMovMer = idMovMer;
	}
	public MensajeServicioEnum getTipoMensaje() {
		return tipoMensaje;
	}
	public void setTipoMensaje(MensajeServicioEnum tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}
	public Long getIdOperacionDescobroMensajeria() {
		return idOperacionDescobroMensajeria;
	}
	public void setIdOperacionDescobroMensajeria(Long idOperacionDescobroMensajeria) {
		this.idOperacionDescobroMensajeria = idOperacionDescobroMensajeria;
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