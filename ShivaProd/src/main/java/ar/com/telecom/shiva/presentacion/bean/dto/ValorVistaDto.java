package ar.com.telecom.shiva.presentacion.bean.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO para la vista de valores
 * 
 *
 * 
 */
public class ValorVistaDto extends GestionDto {

	private static final long serialVersionUID = 1L;
	 
	private Long   idValor;
    private Long   numeroCheque;
    private String numeroConstancia;
    private String descripcionProvincia;
 	private String numeroReferencia;	
 	private String facturaRelacionada;
 	private Date   fechaVencimiento;
 	private Date   fechaTransferencia;
	private String descripcionBancoOrigen;
	private String numeroAcuerdo;
	private String empresa;
	private String segmento;
	private Long   codigoClienteLegado;
	private String ejecutivo;
	private String asistente;
	private Date   fechaIngresoRecibo;
	private String recibo;
	private String numeroConstanciaDeRetencion;
	private String numeroChequeAReemplazar;
	private String archivoDeValoresAConciliar;
	private Date   fechaDeposito;
	private Date   fechaUltimoEstado;
	private Date   fechaDisponible;
	private String razonSocialClienteAgrupador;
	private String estadoValor;
	private String numeroValor;
	private String dbImpresa;
	private String dbEnviadaMail;
	private String fechaDeAlta;
	private String analista;
	private String numeroRecibo;
	private String documentacionOriginalRecibida;
	private String bancoDeposito;
	private String copropietario;
	private String motivoSuspension;
	private Long   numeroDocumentoContable;
	private String usuarioAutorizacion;
	private String tipoValor;
	private BigDecimal importe;
	private BigDecimal saldoDisponible;
	private Date   fechaValor;
	private String motivo;
	private Long   operacionAsociada;
	private String observaciones;
	private String fechaEmision;
	private String cuit;
	private String tipoComprobante;
	private String letraComprobante;
	private String numeroLegalComprobante;
	private String origen;
	private String numeroBoleta;
	private String numeroInterdeposito;
	private String codigoOrganismo;
	private Long   numeroConstanciaDeRecepcion;
	
	private String idEstadoValor;
	private Long   idTipoRentencionImpuesto;
	private Long   idTipoValor;
	
	
	public Long getIdValor() {
		return idValor;
	}
	public void setIdValor(Long idValor) {
		this.idValor = idValor;
	}
	public Long getNumeroCheque() {
		return numeroCheque;
	}
	public void setNumeroCheque(Long numeroCheque) {
		this.numeroCheque = numeroCheque;
	}
	public String getNumeroConstancia() {
		return numeroConstancia;
	}
	public void setNumeroConstancia(String numeroConstancia) {
		this.numeroConstancia = numeroConstancia;
	}
	public String getDescripcionProvincia() {
		return descripcionProvincia;
	}
	public void setDescripcionProvincia(String descripcionProvincia) {
		this.descripcionProvincia = descripcionProvincia;
	}
	public String getNumeroReferencia() {
		return numeroReferencia;
	}
	public void setNumeroReferencia(String numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}
	public String getFacturaRelacionada() {
		return facturaRelacionada;
	}
	public void setFacturaRelacionada(String facturaRelacionada) {
		this.facturaRelacionada = facturaRelacionada;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
 
	public Date getFechaTransferencia() {
		return fechaTransferencia;
	}
	public void setFechaTransferencia(Date fechaTransferencia) {
		this.fechaTransferencia = fechaTransferencia;
	}
	public String getDescripcionBancoOrigen() {
		return descripcionBancoOrigen;
	}
	public void setDescripcionBancoOrigen(String descripcionBancoOrigen) {
		this.descripcionBancoOrigen = descripcionBancoOrigen;
	}
 
	public String getNumeroAcuerdo() {
		return numeroAcuerdo;
	}
	public void setNumeroAcuerdo(String numeroAcuerdo) {
		this.numeroAcuerdo = numeroAcuerdo;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getSegmento() {
		return segmento;
	}
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}
	public Long getCodigoClienteLegado() {
		return codigoClienteLegado;
	}
	public void setCodigoClienteLegado(Long codigoClienteLegado) {
		this.codigoClienteLegado = codigoClienteLegado;
	}
	public String getEjecutivo() {
		return ejecutivo;
	}
	public void setEjecutivo(String ejecutivo) {
		this.ejecutivo = ejecutivo;
	}
	public String getAsistente() {
		return asistente;
	}
	public void setAsistente(String asistente) {
		this.asistente = asistente;
	}
	public Date getFechaIngresoRecibo() {
		return fechaIngresoRecibo;
	}
	public void setFechaIngresoRecibo(Date fechaIngresoRecibo) {
		this.fechaIngresoRecibo = fechaIngresoRecibo;
	}
	public String getRecibo() {
		return recibo;
	}
	public void setRecibo(String recibo) {
		this.recibo = recibo;
	}
	public String getNumeroConstanciaDeRetencion() {
		return numeroConstanciaDeRetencion;
	}
	public void setNumeroConstanciaDeRetencion(String numeroConstanciaDeRetencion) {
		this.numeroConstanciaDeRetencion = numeroConstanciaDeRetencion;
	}
	public String getNumeroChequeAReemplazar() {
		return numeroChequeAReemplazar;
	}
	public void setNumeroChequeAReemplazar(String numeroChequeAReemplazar) {
		this.numeroChequeAReemplazar = numeroChequeAReemplazar;
	}
	public String getArchivoDeValoresAConciliar() {
		return archivoDeValoresAConciliar;
	}
	public void setArchivoDeValoresAConciliar(String archivoDeValoresAConciliar) {
		this.archivoDeValoresAConciliar = archivoDeValoresAConciliar;
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
	public Date getFechaDisponible() {
		return fechaDisponible;
	}
	public void setFechaDisponible(Date fechaDisponible) {
		this.fechaDisponible = fechaDisponible;
	}
	public String getRazonSocialClienteAgrupador() {
		return razonSocialClienteAgrupador;
	}
	public void setRazonSocialClienteAgrupador(String razonSocialClienteAgrupador) {
		this.razonSocialClienteAgrupador = razonSocialClienteAgrupador;
	}
	public String getEstadoValor() {
		return estadoValor;
	}
	public void setEstadoValor(String estadoValor) {
		this.estadoValor = estadoValor;
	}
	public String getNumeroValor() {
		return numeroValor;
	}
	public void setNumeroValor(String numeroValor) {
		this.numeroValor = numeroValor;
	}
	public String getDbImpresa() {
		return dbImpresa;
	}
	public void setDbImpresa(String dbImpresa) {
		this.dbImpresa = dbImpresa;
	}
	public String getDbEnviadaMail() {
		return dbEnviadaMail;
	}
	public void setDbEnviadaMail(String dbEnviadaMail) {
		this.dbEnviadaMail = dbEnviadaMail;
	}
	public String getAnalista() {
		return analista;
	}
	public void setAnalista(String analista) {
		this.analista = analista;
	}
	public String getNumeroRecibo() {
		return numeroRecibo;
	}
	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}
	public Long getNumeroConstanciaDeRecepcion() {
		return numeroConstanciaDeRecepcion;
	}
	public void setNumeroConstanciaDeRecepcion(Long numeroConstanciaDeRecepcion) {
		this.numeroConstanciaDeRecepcion = numeroConstanciaDeRecepcion;
	}
	public String getDocumentacionOriginalRecibida() {
		return documentacionOriginalRecibida;
	}
	public void setDocumentacionOriginalRecibida(
			String documentacionOriginalRecibida) {
		this.documentacionOriginalRecibida = documentacionOriginalRecibida;
	}
	public String getBancoDeposito() {
		return bancoDeposito;
	}
	public void setBancoDeposito(String bancoDeposito) {
		this.bancoDeposito = bancoDeposito;
	}
	public String getCopropietario() {
		return copropietario;
	}
	public void setCopropietario(String copropietario) {
		this.copropietario = copropietario;
	}
	public String getMotivoSuspension() {
		return motivoSuspension;
	}
	public void setMotivoSuspension(String motivoSuspension) {
		this.motivoSuspension = motivoSuspension;
	}
	public Long getNumeroDocumentoContable() {
		return numeroDocumentoContable;
	}
	public void setNumeroDocumentoContable(Long numeroDocumentoContable) {
		this.numeroDocumentoContable = numeroDocumentoContable;
	}
	public String getUsuarioAutorizacion() {
		return usuarioAutorizacion;
	}
	public void setUsuarioAutorizacion(String usuarioAutorizacion) {
		this.usuarioAutorizacion = usuarioAutorizacion;
	}
	public String getTipoValor() {
		return tipoValor;
	}
	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public BigDecimal getSaldoDisponible() {
		return saldoDisponible;
	}
	public void setSaldoDisponible(BigDecimal saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}
	public Date getFechaValor() {
		return fechaValor;
	}
	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public Long getOperacionAsociada() {
		return operacionAsociada;
	}
	public void setOperacionAsociada(Long operacionAsociada) {
		this.operacionAsociada = operacionAsociada;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
 
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getTipoComprobante() {
		return tipoComprobante;
	}
	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}
	public String getLetraComprobante() {
		return letraComprobante;
	}
	public void setLetraComprobante(String letraComprobante) {
		this.letraComprobante = letraComprobante;
	}
	public String getNumeroLegalComprobante() {
		return numeroLegalComprobante;
	}
	public void setNumeroLegalComprobante(String numeroLegalComprobante) {
		this.numeroLegalComprobante = numeroLegalComprobante;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getNumeroBoleta() {
		return numeroBoleta;
	}
	public void setNumeroBoleta(String numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}
	public String getNumeroInterdeposito() {
		return numeroInterdeposito;
	}
	public void setNumeroInterdeposito(String numeroInterdeposito) {
		this.numeroInterdeposito = numeroInterdeposito;
	}
	public String getCodigoOrganismo() {
		return codigoOrganismo;
	}
	public void setCodigoOrganismo(String codigoOrganismo) {
		this.codigoOrganismo = codigoOrganismo;
	}
	public String getIdEstadoValor() {
		return idEstadoValor;
	}
	public void setIdEstadoValor(String idEstadoValor) {
		this.idEstadoValor = idEstadoValor;
	}
	public Long getIdTipoRentencionImpuesto() {
		return idTipoRentencionImpuesto;
	}
	public void setIdTipoRentencionImpuesto(Long idTipoRentencionImpuesto) {
		this.idTipoRentencionImpuesto = idTipoRentencionImpuesto;
	}
	public Long getIdTipoValor() {
		return idTipoValor;
	}
	public void setIdTipoValor(Long idTipoValor) {
		this.idTipoValor = idTipoValor;
	}
	public String getFechaDeAlta() {
		return fechaDeAlta;
	}
	public void setFechaDeAlta(String fechaDeAlta) {
		this.fechaDeAlta = fechaDeAlta;
	}

}

	
	
	
