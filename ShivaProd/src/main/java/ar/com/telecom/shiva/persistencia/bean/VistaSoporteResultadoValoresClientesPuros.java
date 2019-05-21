package ar.com.telecom.shiva.persistencia.bean;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.telecom.shiva.base.enumeradores.OkNokEnum;

public class VistaSoporteResultadoValoresClientesPuros {

	private Long idValor;
	private Long idClienteLegado;
	private String nroValor;
	private BigDecimal importe;
	private String fechaAlta;
	private Long idMotivo;
	private BigDecimal saldoDisponible;
	private Date fechaIngresoRecibo;
	private Date fechaEmision;
	private Date fechaTransferencia;
	private Date fechaDeposito;
	private Date fechaUltimoEstado;
	private Date fechaVencimiento;
	private Long idTipoRetencion;
	private String nroCuitRetencion;
	private String provinciaRetencion;
	private String referenciaValor;
	private Long   idTipoValor;
	private String idEmpresa;
	private String idSegmento;
	private Date fechaValor;
	private String idEmpresaCliente;
	private String idSegmentoCliente;
	private String razonSocialCliente;
	private String cuitCliente;
	private Long   numHoldingCliente;
	private String descripcionHoldingCliente;
	private Long   idAgenciaNegocioCliente;
	private String descripcionAgenciaNegocioCliente;
	private String idProvinciaCliente;
	private OkNokEnum resultadoProcesamiento;
	private String descripcionError;
	
	public Long getIdValor() {
		return idValor;
	}
	public void setIdValor(Long idValor) {
		this.idValor = idValor;
	}
	public Long getIdClienteLegado() {
		return idClienteLegado;
	}
	public void setIdClienteLegado(Long idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}
	public String getNroValor() {
		return nroValor;
	}
	public void setNroValor(String nroValor) {
		this.nroValor = nroValor;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public String getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public Long getIdMotivo() {
		return idMotivo;
	}
	public void setIdMotivo(Long idMotivo) {
		this.idMotivo = idMotivo;
	}
	public BigDecimal getSaldoDisponible() {
		return saldoDisponible;
	}
	public void setSaldoDisponible(BigDecimal saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}
	public Date getFechaIngresoRecibo() {
		return fechaIngresoRecibo;
	}
	public void setFechaIngresoRecibo(Date fechaIngresoRecibo) {
		this.fechaIngresoRecibo = fechaIngresoRecibo;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Date getFechaTransferencia() {
		return fechaTransferencia;
	}
	public void setFechaTransferencia(Date fechaTransferencia) {
		this.fechaTransferencia = fechaTransferencia;
	}
	public Date getFechaDeposito() {
		return fechaDeposito;
	}
	public void setFechaDeposito(Date fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}
	public Date getFechaUltimoEstado() {
		return fechaUltimoEstado;
	}
	public void setFechaUltimoEstado(Date fechaUltimoEstado) {
		this.fechaUltimoEstado = fechaUltimoEstado;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public Long getIdTipoRetencion() {
		return idTipoRetencion;
	}
	public void setIdTipoRetencion(Long idTipoRetencion) {
		this.idTipoRetencion = idTipoRetencion;
	}
	public String getNroCuitRetencion() {
		return nroCuitRetencion;
	}
	public void setNroCuitRetencion(String nroCuitRetencion) {
		this.nroCuitRetencion = nroCuitRetencion;
	}
	public String getProvinciaRetencion() {
		return provinciaRetencion;
	}
	public void setProvinciaRetencion(String provinciaRetencion) {
		this.provinciaRetencion = provinciaRetencion;
	}
	public String getReferenciaValor() {
		return referenciaValor;
	}
	public void setReferenciaValor(String referenciaValor) {
		this.referenciaValor = referenciaValor;
	}
	public Long getIdTipoValor() {
		return idTipoValor;
	}
	public void setIdTipoValor(Long idTipoValor) {
		this.idTipoValor = idTipoValor;
	}
	public String getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getIdSegmento() {
		return idSegmento;
	}
	public void setIdSegmento(String idSegmento) {
		this.idSegmento = idSegmento;
	}
	public Date getFechaValor() {
		return fechaValor;
	}
	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
	}
	public String getIdEmpresaCliente() {
		return idEmpresaCliente;
	}
	public void setIdEmpresaCliente(String idEmpresaCliente) {
		this.idEmpresaCliente = idEmpresaCliente;
	}
	public String getIdSegmentoCliente() {
		return idSegmentoCliente;
	}
	public void setIdSegmentoCliente(String idSegmentoCliente) {
		this.idSegmentoCliente = idSegmentoCliente;
	}
	public String getRazonSocialCliente() {
		return razonSocialCliente;
	}
	public void setRazonSocialCliente(String razonSocialCliente) {
		this.razonSocialCliente = razonSocialCliente;
	}
	public String getCuitCliente() {
		return cuitCliente;
	}
	public void setCuitCliente(String cuitCliente) {
		this.cuitCliente = cuitCliente;
	}
	public Long getNumHoldingCliente() {
		return numHoldingCliente;
	}
	public void setNumHoldingCliente(Long numHoldingCliente) {
		this.numHoldingCliente = numHoldingCliente;
	}
	public String getDescripcionHoldingCliente() {
		return descripcionHoldingCliente;
	}
	public void setDescripcionHoldingCliente(String descripcionHoldingCliente) {
		this.descripcionHoldingCliente = descripcionHoldingCliente;
	}
	public Long getIdAgenciaNegocioCliente() {
		return idAgenciaNegocioCliente;
	}
	public void setIdAgenciaNegocioCliente(Long idAgenciaNegocioCliente) {
		this.idAgenciaNegocioCliente = idAgenciaNegocioCliente;
	}
	public String getDescripcionAgenciaNegocioCliente() {
		return descripcionAgenciaNegocioCliente;
	}
	public void setDescripcionAgenciaNegocioCliente(
			String descripcionAgenciaNegocioCliente) {
		this.descripcionAgenciaNegocioCliente = descripcionAgenciaNegocioCliente;
	}
	public String getIdProvinciaCliente() {
		return idProvinciaCliente;
	}
	public void setIdProvinciaCliente(String idProvinciaCliente) {
		this.idProvinciaCliente = idProvinciaCliente;
	}
	public OkNokEnum getResultadoProcesamiento() {
		return resultadoProcesamiento;
	}
	public void setResultadoProcesamiento(OkNokEnum resultadoProcesamiento) {
		this.resultadoProcesamiento = resultadoProcesamiento;
	}
	public String getDescripcionError() {
		return descripcionError;
	}
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}
	
}
