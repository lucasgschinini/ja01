package ar.com.telecom.shiva.negocio.dto.cobros;

import java.util.List;
import java.math.BigDecimal;
import java.util.Date;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.TipoInvocacionEnum;
import ar.com.telecom.shiva.base.jms.datos.entrada.agrupador.MicDetalleMedioPagoRespuestaEntrada;
import ar.com.telecom.shiva.base.jms.datos.salida.agrupador.MicDetalleCobroFactura;

@SuppressWarnings("serial")
public class MicTransaccionRespuestaDto extends DTO {
	
	private String idOperacionTransaccion;
	private Integer idTransaccion;
	private TipoInvocacionEnum tipoInvocacion;
	private Long idCobranza;
	private String resultadoInvocacion;
	private String codigoError;
	private String descripcionError;
	private BigDecimal interesesGenerados;
	private BigDecimal interesesBonificadosRegulados;
	private BigDecimal interesesBonificadosNoRegulados;
	private Long numeroReferencia;
	private String tipoComprobante;
	private String claseComprobante;
	private String sucursal;
	private String numero;
	private Date fechaVencimiento;
	private MicDetalleCobroFactura detalleCobroFactura;
	private List<MicDetalleMedioPagoRespuestaEntrada> listaDetalleMedioPago;
	
	
	public String toString() {
		String resultado = this.idOperacionTransaccion + "," + this.idTransaccion + "," + this.resultadoInvocacion
				+ "," + this.codigoError + "," + this.descripcionError; 
		return resultado;
	}
	
	
	/*********************************************************************
	 * Getters & Setters
	 *********************************************************************/
  	
	public String getIdOperacionTransaccion() {
		return idOperacionTransaccion;
	}
	public void setIdOperacionTransaccion(String idOperacionTransaccion) {
		this.idOperacionTransaccion = idOperacionTransaccion;
	}

	public TipoInvocacionEnum getTipoInvocacion() {
		return tipoInvocacion;
	}
	public void setTipoInvocacion(TipoInvocacionEnum tipoInvocacion) {
		this.tipoInvocacion = tipoInvocacion;
	}

	public Long getIdCobranza() {
		return idCobranza;
	}
	public void setIdCobranza(Long idCobranza) {
		this.idCobranza = idCobranza;
	}
	
	public String getResultadoInvocacion() {
		return resultadoInvocacion;
	}
	public void setResultadoInvocacion(String resultadoInvocacion) {
		this.resultadoInvocacion = resultadoInvocacion;
	}
	public String getCodigoError() {
		return codigoError;
	}
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}
	public String getDescripcionError() {
		return descripcionError;
	}
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}
	
	public BigDecimal getInteresesGenerados() {
		return interesesGenerados;
	}
	public void setInteresesGenerados(BigDecimal interesesGenerados) {
		this.interesesGenerados = interesesGenerados;
	}
	public BigDecimal getInteresesBonificadosRegulados() {
		return interesesBonificadosRegulados;
	}
	public void setInteresesBonificadosRegulados(
			BigDecimal interesesBonificadosRegulados) {
		this.interesesBonificadosRegulados = interesesBonificadosRegulados;
	}
	public BigDecimal getInteresesBonificadosNoRegulados() {
		return interesesBonificadosNoRegulados;
	}
	public void setInteresesBonificadosNoRegulados(
			BigDecimal interesesBonificadosNoRegulados) {
		this.interesesBonificadosNoRegulados = interesesBonificadosNoRegulados;
	}
	
	public Long getNumeroReferencia() {
		return numeroReferencia;
	}
	public void setNumeroReferencia(Long numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}
	public String getTipoComprobante() {
		return tipoComprobante;
	}
	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}
	public String getClaseComprobante() {
		return claseComprobante;
	}
	public void setClaseComprobante(String claseComprobante) {
		this.claseComprobante = claseComprobante;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public Integer getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(Integer idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	public MicDetalleCobroFactura getDetalleCobroFactura() {
		return detalleCobroFactura;
	}
	public void setDetalleCobroFactura(MicDetalleCobroFactura detalleCobroFactura) {
		this.detalleCobroFactura = detalleCobroFactura;
	}
	public List<MicDetalleMedioPagoRespuestaEntrada> getListaDetalleMedioPago() {
		return listaDetalleMedioPago;
	}
	public void setListaDetalleMedioPago(
			List<MicDetalleMedioPagoRespuestaEntrada> listaDetalleMedioPago) {
		this.listaDetalleMedioPago = listaDetalleMedioPago;
	}
}