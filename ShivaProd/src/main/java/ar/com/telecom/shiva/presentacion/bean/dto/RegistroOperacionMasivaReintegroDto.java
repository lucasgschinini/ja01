package ar.com.telecom.shiva.presentacion.bean.dto;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoOrigenEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoReintegroEnum;

public class RegistroOperacionMasivaReintegroDto extends RegistroOperacionMasivaDto {

	private static final long serialVersionUID = 1L;
	
	private Long clienteDuenoCredito;
	private Long cuenta;
	private TipoOrigenEnum tipoRemanente;
	private Long numeroReferenciaNC;
	private String numeroDocumento;
	private SiNoEnum creditoMigrado;
	private BigDecimal importe;	
	private Long tramiteReintegro;
	private Date fechaAltaTramiteReintegro;
	private TipoReintegroEnum tipoReintegro;
	private Long clienteDuenoAcuerdoFacturacion;
	private Long acuerdoFacturacionDestino;
	private Long lineaDestino;
	private SiNoEnum reintegraConInteres;
	
	public Long getClienteDuenoCredito() {
		return clienteDuenoCredito;
	}
	public void setClienteDuenoCredito(Long clienteDuenoCredito) {
		this.clienteDuenoCredito = clienteDuenoCredito;
	}
	public Long getCuenta() {
		return cuenta;
	}
	public void setCuenta(Long cuenta) {
		this.cuenta = cuenta;
	}
	public TipoOrigenEnum getTipoRemanente() {
		return tipoRemanente;
	}
	public void setTipoRemanente(TipoOrigenEnum tipoRemanente) {
		this.tipoRemanente = tipoRemanente;
	}
	public Long getNumeroReferenciaNC() {
		return numeroReferenciaNC;
	}
	public void setNumeroReferenciaNC(Long numeroReferenciaNC) {
		this.numeroReferenciaNC = numeroReferenciaNC;
	}
	public SiNoEnum getCreditoMigrado() {
		return creditoMigrado;
	}
	public void setCreditoMigrado(SiNoEnum creditoMigrado) {
		this.creditoMigrado = creditoMigrado;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public Long getTramiteReintegro() {
		return tramiteReintegro;
	}
	public void setTramiteReintegro(Long tramiteReintegro) {
		this.tramiteReintegro = tramiteReintegro;
	}
	public Date getFechaAltaTramiteReintegro() {
		return fechaAltaTramiteReintegro;
	}
	public void setFechaAltaTramiteReintegro(Date fechaAltaTramiteReintegro) {
		this.fechaAltaTramiteReintegro = fechaAltaTramiteReintegro;
	}
	public TipoReintegroEnum getTipoReintegro() {
		return tipoReintegro;
	}
	public void setTipoReintegro(TipoReintegroEnum tipoReintegro) {
		this.tipoReintegro = tipoReintegro;
	}
	public Long getClienteDuenoAcuerdoFacturacion() {
		return clienteDuenoAcuerdoFacturacion;
	}
	public void setClienteDuenoAcuerdoFacturacion(
			Long clienteDuenoAcuerdoFacturacion) {
		this.clienteDuenoAcuerdoFacturacion = clienteDuenoAcuerdoFacturacion;
	}
	public Long getAcuerdoFacturacionDestino() {
		return acuerdoFacturacionDestino;
	}
	public void setAcuerdoFacturacionDestino(Long acuerdoFacturacionDestino) {
		this.acuerdoFacturacionDestino = acuerdoFacturacionDestino;
	}
	public Long getLineaDestino() {
		return lineaDestino;
	}
	public void setLineaDestino(Long lineaDestino) {
		this.lineaDestino = lineaDestino;
	}
	public SiNoEnum getReintegraConInteres() {
		return reintegraConInteres;
	}
	public void setReintegraConInteres(SiNoEnum reintegraConInteres) {
		this.reintegraConInteres = reintegraConInteres;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	
}
