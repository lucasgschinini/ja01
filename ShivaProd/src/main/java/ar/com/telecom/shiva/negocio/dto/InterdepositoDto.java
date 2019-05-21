package ar.com.telecom.shiva.negocio.dto;

import java.util.Date;

import ar.com.telecom.shiva.base.utils.Utilidad;

public class InterdepositoDto extends RegistroAVCDto {

	private static final long serialVersionUID = 1L;

	private Date fechaValor;
	private Date fechaIngreso;
	private String concepto;
	private String codigoOperacion;
	private String deposito;
	private String comprobante;
	private String codigoOrganismo;
	private String codigoInterdeposito;
	private String sucursal;
	private Long codOpBanco;
	private String pcc;
	
	private String observacion;
	
	private String errorAltaInterdeposito;
	private String fechaErrorFormateado;
	
	public InterdepositoDto() {}
	
	public InterdepositoDto(String acuerdo) {
		setAcuerdo(acuerdo);
	}
	
	public Date getFechaValor() {
		return fechaValor;
	}
	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getCodigoOperacion() {
		return codigoOperacion;
	}
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}
	public String getPcc() {
		return pcc;
	}
	public void setPcc(String pcc) {
		this.pcc = pcc;
	}
	public String getDeposito() {
		return deposito;
	}
	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}
	public String getSucursal() {
		return sucursal;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	public String getComprobante() {
		return comprobante;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public void setCodigoOrganismo(String codigoOrganismo) {
		this.codigoOrganismo = codigoOrganismo;
	}
	public void setCodigoInterdeposito(String codigoInterdeposito) {
		this.codigoInterdeposito = codigoInterdeposito;
	}
	public void setCodOpBanco(Long codOpBanco) {
		this.codOpBanco = codOpBanco;
	}
	public String getCodigoOrganismo() {
		return codigoOrganismo;
	}

	public Long getCodOpBanco() {
		return codOpBanco;
	}

	public String getCodigoInterdeposito() {
		return codigoInterdeposito;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	@Override
	public String getFechaIngresoFormateada(){
		return Utilidad.formatDate(this.fechaIngreso);
	}

	public String getErrorAltaInterdeposito() {
		return errorAltaInterdeposito;
	}

	public void setErrorAltaInterdeposito(String errorAltaInterdeposito) {
		this.errorAltaInterdeposito = errorAltaInterdeposito;
	}

	public String getFechaErrorFormateado() {
		return fechaErrorFormateado;
	}

	public void setFechaErrorFormateado(String fechaErrorFormateado) {
		this.fechaErrorFormateado = fechaErrorFormateado;
	}

}
