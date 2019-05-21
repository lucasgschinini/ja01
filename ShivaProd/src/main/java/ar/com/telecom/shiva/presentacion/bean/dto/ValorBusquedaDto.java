package ar.com.telecom.shiva.presentacion.bean.dto;

import java.util.Date;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;


public class ValorBusquedaDto {
	
	// Datos Generales
	private String id;
	private String idValor;
	private String empresa;
	private String empresasAsociadas;
	private String fechaNtifDisponRetiroVal;
	private String cuitCliente;
	private String segmento;
	private String codCliente;
	private String razonSocialClienteAgrupador;
	private Date fechaValor;
	private String fechaValorFormateado;
	private String tipoValor;
	private String idTipoValor;
	private String importe;
	private String saldoDisponible;
	private String estadoValor;
	private String idEstadoValor;	
	private String nombreAnalista;
	private String idAnalista;
	private String urlFotoAnalista;
	private String mailAnalista;
	private String copropietario;
	private String idCopropietario;
	private String urlFotoCopropietario;
	private String mailCopropietario;
	private String usuarioAutorizacion;
	private String urlFotoUsuarioAutorizacion;
	private String mailUsuarioAutorizacion;
	private String usuarioEjecutivo;
	private String urlFotoUsuarioEjecutivo;
	private String mailUsuarioEjecutivo;
	private String usuarioAsistente;
	private String urlFotoUsuarioAsistente;
	private String mailUsuarioAsistente;
	private String usuarioAnalistaTC;
	private String idAnalistaTC;
	private String urlFotoAnalistaTC;
	private String mailUsuarioAnalistaTC;
	private String usuarioSupervisorTC;
	private String idSupervisorTC;
	private String urlFotoSupervisorTC;
	private String mailusuarioSupervisorTC;
	private String usuarioGerenteRegionalTC;
	private String idGerenteRegionalTC;
	private String mailUsuarioGerenteRegionalTC;
	private String urlFotoGerenteRegionalTC;
	private String origen;
	private String idOrigen;
	private String bancoDeposito;
	private String idAcuerdo;
	private String nroBoleta;
	private String referenciaValor;
	private String bancoOrigen;
	private String tipoRetencion;
	private String provincia;
	private String nroCuitRetencion;
	private String codigoOrganismo;
	private String reciboPreImpreso;
	private String constancia;
	private String nroConstancia;
	private String operacionAsociada;
	private String facturaRelacionada;
	private String documentacionOriginalRecibida;
	private String motivo;
	private String valorPadre;
	private String numeroDocumentoContable;
	private String motivoSuspension;
	private String idLegajoChequeRechazado;
	private String fechaNotificacionRechazo;
	private String fechaRechazo;
	private String archivoValoresAconciliar;
	private String fechaAltaValor;
	private String fechaIngresoRecibo;
	private String fechaEmision;
	private String fechaEmisionCheque;
	private String fechaVencimiento;
	private String fechaTransferencia;
	private String fechaDeposito;
	private String fechaDisponible;
	private String fechaUltimoEstado;
	private String boletaImpresaEstado;
	private String boletaEnviadaMailEstado;
	private String observaciones;
	private String cobroFormateado;
	private boolean esSupervisorEmpresaSegmento;

	public String getFechaAltaFormateado() {
		return Utilidad.formatDateAndTimeFull(this.fechaValor);
	}

	public String getImporteParaComparacion() {
		if (Utilidad.PESOS_CHAR.equalsIgnoreCase(importe.substring(0, 1))) {
			return Utilidad.formatCurrency(Utilidad.stringToBigDecimal(importe.substring(1)), 2);
		}
		return Utilidad.formatCurrency(Utilidad.stringToBigDecimal(importe), 2);
	}

	public boolean getChkDocumentacionOriginalDocumentacion() {
		if (Validaciones.isNullOrEmpty(this.documentacionOriginalRecibida)) {
			return false;
		} else {
			return SiNoEnum.SI.getDescripcion().equalsIgnoreCase(this.documentacionOriginalRecibida);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdValor() {
		return idValor;
	}

	public void setIdValor(String idValor) {
		this.idValor = idValor;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
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
	 * @return the fechaNtifDisponRetiroVal
	 */
	public String getFechaNtifDisponRetiroVal() {
		return fechaNtifDisponRetiroVal;
	}

	/**
	 * @param fechaNtifDisponRetiroVal the fechaNtifDisponRetiroVal to set
	 */
	public void setFechaNtifDisponRetiroVal(String fechaNtifDisponRetiroVal) {
		this.fechaNtifDisponRetiroVal = fechaNtifDisponRetiroVal;
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

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getRazonSocialClienteAgrupador() {
		return razonSocialClienteAgrupador;
	}

	public void setRazonSocialClienteAgrupador(String razonSocialClienteAgrupador) {
		this.razonSocialClienteAgrupador = razonSocialClienteAgrupador;
	}

	public Date getFechaValor() {
		return fechaValor;
	}

	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
	}

	public String getFechaValorFormateado() {
		return fechaValorFormateado;
	}

	public void setFechaValorFormateado(String fechaValorFormateado) {
		this.fechaValorFormateado = fechaValorFormateado;
	}

	public String getTipoValor() {
		return tipoValor;
	}

	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getSaldoDisponible() {
		return saldoDisponible;
	}

	public void setSaldoDisponible(String saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	public String getEstadoValor() {
		return estadoValor;
	}

	public void setEstadoValor(String estadoValor) {
		this.estadoValor = estadoValor;
	}

	public String getNombreAnalista() {
		return nombreAnalista;
	}

	public void setNombreAnalista(String nombreAnalista) {
		this.nombreAnalista = nombreAnalista;
	}

	public String getIdAnalista() {
		return idAnalista;
	}

	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
	}

	public String getMailAnalista() {
		return mailAnalista;
	}

	public void setMailAnalista(String mailAnalista) {
		this.mailAnalista = mailAnalista;
	}

	public String getCopropietario() {
		return copropietario;
	}

	public void setCopropietario(String copropietario) {
		this.copropietario = copropietario;
	}

	public String getIdCopropietario() {
		return idCopropietario;
	}

	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
	}

	public String getMailCopropietario() {
		return mailCopropietario;
	}

	public void setMailCopropietario(String mailCopropietario) {
		this.mailCopropietario = mailCopropietario;
	}

	public String getUsuarioAutorizacion() {
		return usuarioAutorizacion;
	}

	public void setUsuarioAutorizacion(String usuarioAutorizacion) {
		this.usuarioAutorizacion = usuarioAutorizacion;
	}

	public String getMailUsuarioAutorizacion() {
		return mailUsuarioAutorizacion;
	}

	public void setMailUsuarioAutorizacion(String mailUsuarioAutorizacion) {
		this.mailUsuarioAutorizacion = mailUsuarioAutorizacion;
	}

	public String getUsuarioEjecutivo() {
		return usuarioEjecutivo;
	}

	public void setUsuarioEjecutivo(String usuarioEjecutivo) {
		this.usuarioEjecutivo = usuarioEjecutivo;
	}

	public String getMailUsuarioEjecutivo() {
		return mailUsuarioEjecutivo;
	}

	public void setMailUsuarioEjecutivo(String mailUsuarioEjecutivo) {
		this.mailUsuarioEjecutivo = mailUsuarioEjecutivo;
	}

	public String getUsuarioAsistente() {
		return usuarioAsistente;
	}

	public void setUsuarioAsistente(String usuarioAsistente) {
		this.usuarioAsistente = usuarioAsistente;
	}

	public String getMailUsuarioAsistente() {
		return mailUsuarioAsistente;
	}

	public void setMailUsuarioAsistente(String mailUsuarioAsistente) {
		this.mailUsuarioAsistente = mailUsuarioAsistente;
	}

	public String getUsuarioAnalistaTC() {
		return usuarioAnalistaTC;
	}

	public void setUsuarioAnalistaTC(String usuarioAnalistaTC) {
		this.usuarioAnalistaTC = usuarioAnalistaTC;
	}

	public String getMailUsuarioAnalistaTC() {
		return mailUsuarioAnalistaTC;
	}

	public void setMailUsuarioAnalistaTC(String mailUsuarioAnalistaTC) {
		this.mailUsuarioAnalistaTC = mailUsuarioAnalistaTC;
	}

	public String getUsuarioSupervisorTC() {
		return usuarioSupervisorTC;
	}

	public void setUsuarioSupervisorTC(String usuarioSupervisorTC) {
		this.usuarioSupervisorTC = usuarioSupervisorTC;
	}

	public String getMailusuarioSupervisorTC() {
		return mailusuarioSupervisorTC;
	}

	public void setMailusuarioSupervisorTC(String mailusuarioSupervisorTC) {
		this.mailusuarioSupervisorTC = mailusuarioSupervisorTC;
	}

	public String getUsuarioGerenteRegionalTC() {
		return usuarioGerenteRegionalTC;
	}

	public void setUsuarioGerenteRegionalTC(String usuarioGerenteRegionalTC) {
		this.usuarioGerenteRegionalTC = usuarioGerenteRegionalTC;
	}

	public String getMailUsuarioGerenteRegionalTC() {
		return mailUsuarioGerenteRegionalTC;
	}

	public void setMailUsuarioGerenteRegionalTC(String mailUsuarioGerenteRegionalTC) {
		this.mailUsuarioGerenteRegionalTC = mailUsuarioGerenteRegionalTC;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getBancoDeposito() {
		return bancoDeposito;
	}

	public void setBancoDeposito(String bancoDeposito) {
		this.bancoDeposito = bancoDeposito;
	}

	public String getIdAcuerdo() {
		return idAcuerdo;
	}

	public void setIdAcuerdo(String idAcuerdo) {
		this.idAcuerdo = idAcuerdo;
	}

	public String getNroBoleta() {
		return nroBoleta;
	}

	public void setNroBoleta(String nroBoleta) {
		this.nroBoleta = nroBoleta;
	}

	public String getReferenciaValor() {
		return referenciaValor;
	}

	public void setReferenciaValor(String referenciaValor) {
		this.referenciaValor = referenciaValor;
	}

	public String getBancoOrigen() {
		return bancoOrigen;
	}

	public void setBancoOrigen(String bancoOrigen) {
		this.bancoOrigen = bancoOrigen;
	}

	public String getTipoRetencion() {
		return tipoRetencion;
	}

	public void setTipoRetencion(String tipoRetencion) {
		this.tipoRetencion = tipoRetencion;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getNroCuitRetencion() {
		return nroCuitRetencion;
	}

	public void setNroCuitRetencion(String nroCuitRetencion) {
		this.nroCuitRetencion = nroCuitRetencion;
	}

	public String getCodigoOrganismo() {
		return codigoOrganismo;
	}

	public void setCodigoOrganismo(String codigoOrganismo) {
		this.codigoOrganismo = codigoOrganismo;
	}

	public String getReciboPreImpreso() {
		return reciboPreImpreso;
	}

	public void setReciboPreImpreso(String reciboPreImpreso) {
		this.reciboPreImpreso = reciboPreImpreso;
	}

	public String getConstancia() {
		return constancia;
	}

	public void setConstancia(String constancia) {
		this.constancia = constancia;
	}

	public String getNroConstancia() {
		return nroConstancia;
	}

	public void setNroConstancia(String nroConstancia) {
		this.nroConstancia = nroConstancia;
	}

	public String getOperacionAsociada() {
		return operacionAsociada;
	}

	public void setOperacionAsociada(String operacionAsociada) {
		this.operacionAsociada = operacionAsociada;
	}

	public String getFacturaRelacionada() {
		return facturaRelacionada;
	}

	public void setFacturaRelacionada(String facturaRelacionada) {
		this.facturaRelacionada = facturaRelacionada;
	}

	public String getDocumentacionOriginalRecibida() {
		return documentacionOriginalRecibida;
	}

	public void setDocumentacionOriginalRecibida(
			String documentacionOriginalRecibida) {
		this.documentacionOriginalRecibida = documentacionOriginalRecibida;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getValorPadre() {
		return valorPadre;
	}

	public void setValorPadre(String valorPadre) {
		this.valorPadre = valorPadre;
	}

	public String getNumeroDocumentoContable() {
		return numeroDocumentoContable;
	}

	public void setNumeroDocumentoContable(String numeroDocumentoContable) {
		this.numeroDocumentoContable = numeroDocumentoContable;
	}

	public String getMotivoSuspension() {
		return motivoSuspension;
	}

	public void setMotivoSuspension(String motivoSuspension) {
		this.motivoSuspension = motivoSuspension;
	}

	public String getArchivoValoresAconciliar() {
		return archivoValoresAconciliar;
	}

	public void setArchivoValoresAconciliar(String archivoValoresAconciliar) {
		this.archivoValoresAconciliar = archivoValoresAconciliar;
	}

	public String getFechaAltaValor() {
		return fechaAltaValor;
	}

	public void setFechaAltaValor(String fechaAltaValor) {
		this.fechaAltaValor = fechaAltaValor;
	}

	public String getFechaIngresoRecibo() {
		return fechaIngresoRecibo;
	}

	public void setFechaIngresoRecibo(String fechaIngresoRecibo) {
		this.fechaIngresoRecibo = fechaIngresoRecibo;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @return the fechaEmisionCheque
	 */
	public String getFechaEmisionCheque() {
		return fechaEmisionCheque;
	}

	/**
	 * @param fechaEmisionCheque the fechaEmisionCheque to set
	 */
	public void setFechaEmisionCheque(String fechaEmisionCheque) {
		this.fechaEmisionCheque = fechaEmisionCheque;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getFechaTransferencia() {
		return fechaTransferencia;
	}

	public void setFechaTransferencia(String fechaTransferencia) {
		this.fechaTransferencia = fechaTransferencia;
	}

	public String getFechaDeposito() {
		return fechaDeposito;
	}

	public void setFechaDeposito(String fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}

	public String getFechaDisponible() {
		return fechaDisponible;
	}

	public void setFechaDisponible(String fechaDisponible) {
		this.fechaDisponible = fechaDisponible;
	}

	public String getFechaUltimoEstado() {
		return fechaUltimoEstado;
	}

	public void setFechaUltimoEstado(String fechaUltimoEstado) {
		this.fechaUltimoEstado = fechaUltimoEstado;
	}

	public String getBoletaImpresaEstado() {
		return boletaImpresaEstado;
	}

	public void setBoletaImpresaEstado(String boletaImpresaEstado) {
		this.boletaImpresaEstado = boletaImpresaEstado;
	}

	public String getBoletaEnviadaMailEstado() {
		return boletaEnviadaMailEstado;
	}

	public void setBoletaEnviadaMailEstado(String boletaEnviadaMailEstado) {
		this.boletaEnviadaMailEstado = boletaEnviadaMailEstado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getCobroFormateado() {
		return cobroFormateado;
	}

	public void setCobroFormateado(String cobroFormateado) {
		this.cobroFormateado = cobroFormateado;
	}

	public String getUrlFotoAnalista() {
		return urlFotoAnalista;
	}

	public void setUrlFotoAnalista(String urlFotoAnalista) {
		this.urlFotoAnalista = urlFotoAnalista;
	}

	public String getUrlFotoCopropietario() {
		return urlFotoCopropietario;
	}

	public void setUrlFotoCopropietario(String urlFotoCopropietario) {
		this.urlFotoCopropietario = urlFotoCopropietario;
	}

	public String getUrlFotoUsuarioAutorizacion() {
		return urlFotoUsuarioAutorizacion;
	}

	public void setUrlFotoUsuarioAutorizacion(String urlFotoUsuarioAutorizacion) {
		this.urlFotoUsuarioAutorizacion = urlFotoUsuarioAutorizacion;
	}

	public String getUrlFotoUsuarioEjecutivo() {
		return urlFotoUsuarioEjecutivo;
	}

	public void setUrlFotoUsuarioEjecutivo(String urlFotoUsuarioEjecutivo) {
		this.urlFotoUsuarioEjecutivo = urlFotoUsuarioEjecutivo;
	}

	public String getUrlFotoUsuarioAsistente() {
		return urlFotoUsuarioAsistente;
	}

	public void setUrlFotoUsuarioAsistente(String urlFotoUsuarioAsistente) {
		this.urlFotoUsuarioAsistente = urlFotoUsuarioAsistente;
	}

	public String getUrlFotoAnalistaTC() {
		return urlFotoAnalistaTC;
	}

	public void setUrlFotoAnalistaTC(String urlFotoAnalistaTC) {
		this.urlFotoAnalistaTC = urlFotoAnalistaTC;
	}

	public String getIdSupervisorTC() {
		return idSupervisorTC;
	}

	public void setIdSupervisorTC(String idSupervisorTC) {
		this.idSupervisorTC = idSupervisorTC;
	}

	public String getIdGerenteRegionalTC() {
		return idGerenteRegionalTC;
	}

	public void setIdGerenteRegionalTC(String idGerenteRegionalTC) {
		this.idGerenteRegionalTC = idGerenteRegionalTC;
	}

	public String getUrlFotoSupervisorTC() {
		return urlFotoSupervisorTC;
	}

	public void setUrlFotoSupervisorTC(String urlFotoSupervisorTC) {
		this.urlFotoSupervisorTC = urlFotoSupervisorTC;
	}

	public String getUrlFotoGerenteRegionalTC() {
		return urlFotoGerenteRegionalTC;
	}

	public void setUrlFotoGerenteRegionalTC(String urlFotoGerenteRegionalTC) {
		this.urlFotoGerenteRegionalTC = urlFotoGerenteRegionalTC;
	}

	public String getIdEstadoValor() {
		return idEstadoValor;
	}

	public void setIdEstadoValor(String idEstadoValor) {
		this.idEstadoValor = idEstadoValor;
	}

	public String getIdTipoValor() {
		return idTipoValor;
	}

	public void setIdTipoValor(String idTipoValor) {
		this.idTipoValor = idTipoValor;
	}

	public String getIdOrigen() {
		return idOrigen;
	}

	public void setIdOrigen(String idOrigen) {
		this.idOrigen = idOrigen;
	}

	public String getIdAnalistaTC() {
		return idAnalistaTC;
	}

	public void setIdAnalistaTC(String idAnalistaTC) {
		this.idAnalistaTC = idAnalistaTC;
	}

	public boolean isEsSupervisorEmpresaSegmento() {
		return esSupervisorEmpresaSegmento;
	}

	public void setEsSupervisorEmpresaSegmento(boolean esSupervisorEmpresaSegmento) {
		this.esSupervisorEmpresaSegmento = esSupervisorEmpresaSegmento;
	}

	public String getIdLegajoChequeRechazado() {
		return idLegajoChequeRechazado;
	}

	public void setIdLegajoChequeRechazado(String idLegajoChequeRechazado) {
		this.idLegajoChequeRechazado = idLegajoChequeRechazado;
	}

	public String getFechaNotificacionRechazo() {
		return fechaNotificacionRechazo;
	}

	public void setFechaNotificacionRechazo(String fechaNotificacionRechazo) {
		this.fechaNotificacionRechazo = fechaNotificacionRechazo;
	}

	public String getFechaRechazo() {
		return fechaRechazo;
	}

	public void setFechaRechazo(String fechaRechazo) {
		this.fechaRechazo = fechaRechazo;
	}
	
	
	
}