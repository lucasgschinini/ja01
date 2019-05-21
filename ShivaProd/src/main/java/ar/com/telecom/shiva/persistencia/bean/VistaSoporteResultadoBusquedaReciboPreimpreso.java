package ar.com.telecom.shiva.persistencia.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.com.telecom.shiva.persistencia.modelo.ShvTalCompensacion;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;

public class VistaSoporteResultadoBusquedaReciboPreimpreso extends VistaSoporteResultadoBusqueda {
	
	private String idReciboPreimpreso;
	private String numeroRecibo;
	private String idTalonario;
	private String tipo;
	private BigDecimal importeRecibo;
	private Date fechaAnulacion;
	private String usuarioAnulacion;
	private String idWorkflowRecibo;
	private Date fechaIngreso;
	private String idValor;
	private String empresa;
	private String cuitCliente;
	private String segmento;
	private String idClienteLegado;
	private String razonSocialClientePerfil;
	private String tipoValor;
	private String estadoValor;
	private String origen;
	private String acuerdo;
	private String nroValor;
	private BigDecimal importe;
	private String fechaAlta;
	private String operacionAsociada;
	private String facturaRelacionada;
	private String documentacionOrigRecibida;
	private String motivo;
	private String idMotivo;
	private String idBancoDeposito;
	private String bancoDeposito;
	private String cuentaDeposito;
	private String idCopropietario;
	private String usuarioAutorizacion;
	private String numeroDocumentoContable;
	private String motivoSuspension;
	private BigDecimal saldoDisponible;
	private Date fechaIngresoRecibo;
	private String fechaRetencion;
	private Date fechaEmision;
	private String fechaTransferencia;
	private Date fechaDeposito;
	private Date fechaUltimoEstado;
	private Date fechaDisponible;
	private String nroBoleta;
	private String idBancoOrigen;
	private String descripcionBancoOrigen;
	private Date fechaVencimiento;
	private String referenciaValor;
	private String idAnalistaTeamComercial;
	private String idSupervisorTeamComercial;
	private String idGerenteRegTeamComercial;
	private String codigoOrganismo;
	private String observaciones;
	private String idEstadoValor;
	private String idTipoValor;
	private String idOrigen;
	private String idEmpresa;
	private String idSegmento;
	private Date fechaValor;
	private String cobroFormateado;
	private String idBoleta;
	private String fechaBusqueda;
	private String nroCuitTransaccion;
	private String empresasAsociadas;
	private Date fechaNotifDisponRetiroVal;
	private String idCompensacion;
	private String referencia;
	private BigDecimal importeCompensacion;
	private Date fechaUltimaModificacion;
	private String idAnalista;
	private String estado;
	private List<ValorDto> listaValores = new ArrayList<ValorDto>();
	private Set<ShvTalCompensacion> listaCompensaciones = new HashSet<ShvTalCompensacion>();
	private String gestorAsignado;
	/**
	 * @return the idReciboPreimpreso
	 */
	public String getIdReciboPreimpreso() {
		return idReciboPreimpreso;
	}
	/**
	 * @param idReciboPreimpreso the idReciboPreimpreso to set
	 */
	public void setIdReciboPreimpreso(String idReciboPreimpreso) {
		this.idReciboPreimpreso = idReciboPreimpreso;
	}
	/**
	 * @return the numeroRecibo
	 */
	public String getNumeroRecibo() {
		return numeroRecibo;
	}
	/**
	 * @param numeroRecibo the numeroRecibo to set
	 */
	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}
	/**
	 * @return the idTalonario
	 */
	public String getIdTalonario() {
		return idTalonario;
	}
	/**
	 * @param idTalonario the idTalonario to set
	 */
	public void setIdTalonario(String idTalonario) {
		this.idTalonario = idTalonario;
	}
	/**
	 * @return the importeRecibo
	 */
	public BigDecimal getImporteRecibo() {
		return importeRecibo;
	}
	/**
	 * @param importeRecibo the importeRecibo to set
	 */
	public void setImporteRecibo(BigDecimal importeRecibo) {
		this.importeRecibo = importeRecibo;
	}
	/**
	 * @return the fechaAnulacion
	 */
	public Date getFechaAnulacion() {
		return fechaAnulacion;
	}
	/**
	 * @param fechaAnulacion the fechaAnulacion to set
	 */
	public void setFechaAnulacion(Date fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}
	/**
	 * @return the usuarioAnulacion
	 */
	public String getUsuarioAnulacion() {
		return usuarioAnulacion;
	}
	/**
	 * @param usuarioAnulacion the usuarioAnulacion to set
	 */
	public void setUsuarioAnulacion(String usuarioAnulacion) {
		this.usuarioAnulacion = usuarioAnulacion;
	}
	/**
	 * @return the idWorkflowRecibo
	 */
	public String getIdWorkflowRecibo() {
		return idWorkflowRecibo;
	}
	/**
	 * @param idWorkflowRecibo the idWorkflowRecibo to set
	 */
	public void setIdWorkflowRecibo(String idWorkflowRecibo) {
		this.idWorkflowRecibo = idWorkflowRecibo;
	}
	/**
	 * @return the fechaIngreso
	 */
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	/**
	 * @param fechaIngreso the fechaIngreso to set
	 */
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	/**
	 * @return the idValor
	 */
	public String getIdValor() {
		return idValor;
	}
	/**
	 * @param idValor the idValor to set
	 */
	public void setIdValor(String idValor) {
		this.idValor = idValor;
	}
	/**
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}
	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	/**
	 * @return the cuitCliente
	 */
	public String getCuitCliente() {
		return cuitCliente;
	}
	/**
	 * @param cuitCliente the cuitCliente to set
	 */
	public void setCuitCliente(String cuitCliente) {
		this.cuitCliente = cuitCliente;
	}
	/**
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}
	/**
	 * @param segmento the segmento to set
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}
	/**
	 * @return the idClienteLegado
	 */
	public String getIdClienteLegado() {
		return idClienteLegado;
	}
	/**
	 * @param idClienteLegado the idClienteLegado to set
	 */
	public void setIdClienteLegado(String idClienteLegado) {
		this.idClienteLegado = idClienteLegado;
	}
	/**
	 * @return the razonSocialClientePerfil
	 */
	public String getRazonSocialClientePerfil() {
		return razonSocialClientePerfil;
	}
	/**
	 * @param razonSocialClientePerfil the razonSocialClientePerfil to set
	 */
	public void setRazonSocialClientePerfil(String razonSocialClientePerfil) {
		this.razonSocialClientePerfil = razonSocialClientePerfil;
	}
	/**
	 * @return the tipoValor
	 */
	public String getTipoValor() {
		return tipoValor;
	}
	/**
	 * @param tipoValor the tipoValor to set
	 */
	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}
	/**
	 * @return the estadoValor
	 */
	public String getEstadoValor() {
		return estadoValor;
	}
	/**
	 * @param estadoValor the estadoValor to set
	 */
	public void setEstadoValor(String estadoValor) {
		this.estadoValor = estadoValor;
	}
	/**
	 * @return the origen
	 */
	public String getOrigen() {
		return origen;
	}
	/**
	 * @param origen the origen to set
	 */
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	/**
	 * @return the acuerdo
	 */
	public String getAcuerdo() {
		return acuerdo;
	}
	/**
	 * @param acuerdo the acuerdo to set
	 */
	public void setAcuerdo(String acuerdo) {
		this.acuerdo = acuerdo;
	}
	/**
	 * @return the nroValor
	 */
	public String getNroValor() {
		return nroValor;
	}
	/**
	 * @param nroValor the nroValor to set
	 */
	public void setNroValor(String nroValor) {
		this.nroValor = nroValor;
	}
	/**
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}
	/**
	 * @param importe the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	/**
	 * @return the fechaAlta
	 */
	public String getFechaAlta() {
		return fechaAlta;
	}
	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	/**
	 * @return the operacionAsociada
	 */
	public String getOperacionAsociada() {
		return operacionAsociada;
	}
	/**
	 * @param operacionAsociada the operacionAsociada to set
	 */
	public void setOperacionAsociada(String operacionAsociada) {
		this.operacionAsociada = operacionAsociada;
	}
	/**
	 * @return the facturaRelacionada
	 */
	public String getFacturaRelacionada() {
		return facturaRelacionada;
	}
	/**
	 * @param facturaRelacionada the facturaRelacionada to set
	 */
	public void setFacturaRelacionada(String facturaRelacionada) {
		this.facturaRelacionada = facturaRelacionada;
	}
	/**
	 * @return the documentacionOrigRecibida
	 */
	public String getDocumentacionOrigRecibida() {
		return documentacionOrigRecibida;
	}
	/**
	 * @param documentacionOrigRecibida the documentacionOrigRecibida to set
	 */
	public void setDocumentacionOrigRecibida(String documentacionOrigRecibida) {
		this.documentacionOrigRecibida = documentacionOrigRecibida;
	}
	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}
	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	/**
	 * @return the idMotivo
	 */
	public String getIdMotivo() {
		return idMotivo;
	}
	/**
	 * @param idMotivo the idMotivo to set
	 */
	public void setIdMotivo(String idMotivo) {
		this.idMotivo = idMotivo;
	}
	/**
	 * @return the idBancoDeposito
	 */
	public String getIdBancoDeposito() {
		return idBancoDeposito;
	}
	/**
	 * @param idBancoDeposito the idBancoDeposito to set
	 */
	public void setIdBancoDeposito(String idBancoDeposito) {
		this.idBancoDeposito = idBancoDeposito;
	}
	/**
	 * @return the bancoDeposito
	 */
	public String getBancoDeposito() {
		return bancoDeposito;
	}
	/**
	 * @param bancoDeposito the bancoDeposito to set
	 */
	public void setBancoDeposito(String bancoDeposito) {
		this.bancoDeposito = bancoDeposito;
	}
	/**
	 * @return the cuentaDeposito
	 */
	public String getCuentaDeposito() {
		return cuentaDeposito;
	}
	/**
	 * @param cuentaDeposito the cuentaDeposito to set
	 */
	public void setCuentaDeposito(String cuentaDeposito) {
		this.cuentaDeposito = cuentaDeposito;
	}
	/**
	 * @return the idCopropietario
	 */
	public String getIdCopropietario() {
		return idCopropietario;
	}
	/**
	 * @param idCopropietario the idCopropietario to set
	 */
	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
	}
	/**
	 * @return the usuarioAutorizacion
	 */
	public String getUsuarioAutorizacion() {
		return usuarioAutorizacion;
	}
	/**
	 * @param usuarioAutorizacion the usuarioAutorizacion to set
	 */
	public void setUsuarioAutorizacion(String usuarioAutorizacion) {
		this.usuarioAutorizacion = usuarioAutorizacion;
	}
	/**
	 * @return the numeroDocumentoContable
	 */
	public String getNumeroDocumentoContable() {
		return numeroDocumentoContable;
	}
	/**
	 * @param numeroDocumentoContable the numeroDocumentoContable to set
	 */
	public void setNumeroDocumentoContable(String numeroDocumentoContable) {
		this.numeroDocumentoContable = numeroDocumentoContable;
	}
	/**
	 * @return the motivoSuspension
	 */
	public String getMotivoSuspension() {
		return motivoSuspension;
	}
	/**
	 * @param motivoSuspension the motivoSuspension to set
	 */
	public void setMotivoSuspension(String motivoSuspension) {
		this.motivoSuspension = motivoSuspension;
	}
	/**
	 * @return the saldoDisponible
	 */
	public BigDecimal getSaldoDisponible() {
		return saldoDisponible;
	}
	/**
	 * @param saldoDisponible the saldoDisponible to set
	 */
	public void setSaldoDisponible(BigDecimal saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}
	/**
	 * @return the fechaIngresoRecibo
	 */
	public Date getFechaIngresoRecibo() {
		return fechaIngresoRecibo;
	}
	/**
	 * @param fechaIngresoRecibo the fechaIngresoRecibo to set
	 */
	public void setFechaIngresoRecibo(Date fechaIngresoRecibo) {
		this.fechaIngresoRecibo = fechaIngresoRecibo;
	}
	/**
	 * @return the fechaRetencion
	 */
	public String getFechaRetencion() {
		return fechaRetencion;
	}
	/**
	 * @param fechaRetencion the fechaRetencion to set
	 */
	public void setFechaRetencion(String fechaRetencion) {
		this.fechaRetencion = fechaRetencion;
	}
	/**
	 * @return the fechaEmision
	 */
	public Date getFechaEmision() {
		return fechaEmision;
	}
	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	/**
	 * @return the fechaTransferencia
	 */
	public String getFechaTransferencia() {
		return fechaTransferencia;
	}
	/**
	 * @param fechaTransferencia the fechaTransferencia to set
	 */
	public void setFechaTransferencia(String fechaTransferencia) {
		this.fechaTransferencia = fechaTransferencia;
	}
	/**
	 * @return the fechaDeposito
	 */
	public Date getFechaDeposito() {
		return fechaDeposito;
	}
	/**
	 * @param fechaDeposito the fechaDeposito to set
	 */
	public void setFechaDeposito(Date fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}
	/**
	 * @return the fechaUltimoEstado
	 */
	public Date getFechaUltimoEstado() {
		return fechaUltimoEstado;
	}
	/**
	 * @param fechaUltimoEstado the fechaUltimoEstado to set
	 */
	public void setFechaUltimoEstado(Date fechaUltimoEstado) {
		this.fechaUltimoEstado = fechaUltimoEstado;
	}
	/**
	 * @return the fechaDisponible
	 */
	public Date getFechaDisponible() {
		return fechaDisponible;
	}
	/**
	 * @param fechaDisponible the fechaDisponible to set
	 */
	public void setFechaDisponible(Date fechaDisponible) {
		this.fechaDisponible = fechaDisponible;
	}
	/**
	 * @return the nroBoleta
	 */
	public String getNroBoleta() {
		return nroBoleta;
	}
	/**
	 * @param nroBoleta the nroBoleta to set
	 */
	public void setNroBoleta(String nroBoleta) {
		this.nroBoleta = nroBoleta;
	}
	/**
	 * @return the idBancoOrigen
	 */
	public String getIdBancoOrigen() {
		return idBancoOrigen;
	}
	/**
	 * @param idBancoOrigen the idBancoOrigen to set
	 */
	public void setIdBancoOrigen(String idBancoOrigen) {
		this.idBancoOrigen = idBancoOrigen;
	}
	/**
	 * @return the descripcionBancoOrigen
	 */
	public String getDescripcionBancoOrigen() {
		return descripcionBancoOrigen;
	}
	/**
	 * @param descripcionBancoOrigen the descripcionBancoOrigen to set
	 */
	public void setDescripcionBancoOrigen(String descripcionBancoOrigen) {
		this.descripcionBancoOrigen = descripcionBancoOrigen;
	}
	/**
	 * @return the fechaVencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	/**
	 * @param fechaVencimiento the fechaVencimiento to set
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	/**
	 * @return the referenciaValor
	 */
	public String getReferenciaValor() {
		return referenciaValor;
	}
	/**
	 * @param referenciaValor the referenciaValor to set
	 */
	public void setReferenciaValor(String referenciaValor) {
		this.referenciaValor = referenciaValor;
	}
	/**
	 * @return the idAnalistaTeamComercial
	 */
	public String getIdAnalistaTeamComercial() {
		return idAnalistaTeamComercial;
	}
	/**
	 * @param idAnalistaTeamComercial the idAnalistaTeamComercial to set
	 */
	public void setIdAnalistaTeamComercial(String idAnalistaTeamComercial) {
		this.idAnalistaTeamComercial = idAnalistaTeamComercial;
	}
	/**
	 * @return the idSupervisorTeamComercial
	 */
	public String getIdSupervisorTeamComercial() {
		return idSupervisorTeamComercial;
	}
	/**
	 * @param idSupervisorTeamComercial the idSupervisorTeamComercial to set
	 */
	public void setIdSupervisorTeamComercial(String idSupervisorTeamComercial) {
		this.idSupervisorTeamComercial = idSupervisorTeamComercial;
	}
	/**
	 * @return the idGerenteRegTeamComercial
	 */
	public String getIdGerenteRegTeamComercial() {
		return idGerenteRegTeamComercial;
	}
	/**
	 * @param idGerenteRegTeamComercial the idGerenteRegTeamComercial to set
	 */
	public void setIdGerenteRegTeamComercial(String idGerenteRegTeamComercial) {
		this.idGerenteRegTeamComercial = idGerenteRegTeamComercial;
	}
	/**
	 * @return the codigoOrganismo
	 */
	public String getCodigoOrganismo() {
		return codigoOrganismo;
	}
	/**
	 * @param codigoOrganismo the codigoOrganismo to set
	 */
	public void setCodigoOrganismo(String codigoOrganismo) {
		this.codigoOrganismo = codigoOrganismo;
	}
	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	/**
	 * @return the idEstadoValor
	 */
	public String getIdEstadoValor() {
		return idEstadoValor;
	}
	/**
	 * @param idEstadoValor the idEstadoValor to set
	 */
	public void setIdEstadoValor(String idEstadoValor) {
		this.idEstadoValor = idEstadoValor;
	}
	/**
	 * @return the idTipoValor
	 */
	public String getIdTipoValor() {
		return idTipoValor;
	}
	/**
	 * @param idTipoValor the idTipoValor to set
	 */
	public void setIdTipoValor(String idTipoValor) {
		this.idTipoValor = idTipoValor;
	}
	/**
	 * @return the idOrigen
	 */
	public String getIdOrigen() {
		return idOrigen;
	}
	/**
	 * @param idOrigen the idOrigen to set
	 */
	public void setIdOrigen(String idOrigen) {
		this.idOrigen = idOrigen;
	}
	/**
	 * @return the idEmpresa
	 */
	public String getIdEmpresa() {
		return idEmpresa;
	}
	/**
	 * @param idEmpresa the idEmpresa to set
	 */
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	/**
	 * @return the idSegmento
	 */
	public String getIdSegmento() {
		return idSegmento;
	}
	/**
	 * @param idSegmento the idSegmento to set
	 */
	public void setIdSegmento(String idSegmento) {
		this.idSegmento = idSegmento;
	}
	/**
	 * @return the fechaValor
	 */
	public Date getFechaValor() {
		return fechaValor;
	}
	/**
	 * @param fechaValor the fechaValor to set
	 */
	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
	}
	/**
	 * @return the cobroFormateado
	 */
	public String getCobroFormateado() {
		return cobroFormateado;
	}
	/**
	 * @param cobroFormateado the cobroFormateado to set
	 */
	public void setCobroFormateado(String cobroFormateado) {
		this.cobroFormateado = cobroFormateado;
	}
	/**
	 * @return the idBoleta
	 */
	public String getIdBoleta() {
		return idBoleta;
	}
	/**
	 * @param idBoleta the idBoleta to set
	 */
	public void setIdBoleta(String idBoleta) {
		this.idBoleta = idBoleta;
	}
	/**
	 * @return the fechaBusqueda
	 */
	public String getFechaBusqueda() {
		return fechaBusqueda;
	}
	/**
	 * @param fechaBusqueda the fechaBusqueda to set
	 */
	public void setFechaBusqueda(String fechaBusqueda) {
		this.fechaBusqueda = fechaBusqueda;
	}
	/**
	 * @return the nroCuitTransaccion
	 */
	public String getNroCuitTransaccion() {
		return nroCuitTransaccion;
	}
	/**
	 * @param nroCuitTransaccion the nroCuitTransaccion to set
	 */
	public void setNroCuitTransaccion(String nroCuitTransaccion) {
		this.nroCuitTransaccion = nroCuitTransaccion;
	}
	/**
	 * @return the empresasAsociadas
	 */
	public String getEmpresasAsociadas() {
		return empresasAsociadas;
	}
	/**
	 * @param empresasAsociadas the empresasAsociadas to set
	 */
	public void setEmpresasAsociadas(String empresasAsociadas) {
		this.empresasAsociadas = empresasAsociadas;
	}
	/**
	 * @return the fechaNotifDisponRetiroVal
	 */
	public Date getFechaNotifDisponRetiroVal() {
		return fechaNotifDisponRetiroVal;
	}
	/**
	 * @param fechaNotifDisponRetiroVal the fechaNotifDisponRetiroVal to set
	 */
	public void setFechaNotifDisponRetiroVal(Date fechaNotifDisponRetiroVal) {
		this.fechaNotifDisponRetiroVal = fechaNotifDisponRetiroVal;
	}
	/**
	 * @return the idCompensacion
	 */
	public String getIdCompensacion() {
		return idCompensacion;
	}
	/**
	 * @param idCompensacion the idCompensacion to set
	 */
	public void setIdCompensacion(String idCompensacion) {
		this.idCompensacion = idCompensacion;
	}
	/**
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}
	/**
	 * @param referencia the referencia to set
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	/**
	 * @return the importeCompensacion
	 */
	public BigDecimal getImporteCompensacion() {
		return importeCompensacion;
	}
	/**
	 * @param importeCompensacion the importeCompensacion to set
	 */
	public void setImporteCompensacion(BigDecimal importeCompensacion) {
		this.importeCompensacion = importeCompensacion;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}	
	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}
	public String getIdAnalista() {
		return idAnalista;
	}	
	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}
	/**
	 * @return the listaValores
	 */
	public List<ValorDto> getListaValores() {
		return listaValores;
	}
	/**
	 * @param listaValores the listaValores to set
	 */
	public void setListaValores(List<ValorDto> listaValores) {
		this.listaValores = listaValores;
	}
	/**
	 * @return the listaCompensaciones
	 */
	public Set<ShvTalCompensacion> getListaCompensaciones() {
		return listaCompensaciones;
	}
	/**
	 * @param listaCompensaciones the listaCompensaciones to set
	 */
	public void setListaCompensaciones(Set<ShvTalCompensacion> listaCompensaciones) {
		this.listaCompensaciones = listaCompensaciones;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return the gestorAsignado
	 */
	public String getGestorAsignado() {
		return gestorAsignado;
	}
	/**
	 * @param gestorAsignado the gestorAsignado to set
	 */
	public void setGestorAsignado(String gestorAsignado) {
		this.gestorAsignado = gestorAsignado;
	}	
	
}
