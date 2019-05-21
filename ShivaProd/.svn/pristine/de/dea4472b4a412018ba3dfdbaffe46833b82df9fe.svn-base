package ar.com.telecom.shiva.presentacion.bean.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ar.com.telecom.shiva.base.enumeradores.EnviarMailBoletaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.ImprimirBoletaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.presentacion.bean.UsuarioSesion;

/**
 * DTO de valores
 * 
 * @author nicolas.i.voget
 * 
 */
public class ValorDto extends GestionDto {

	private static final long serialVersionUID = 1L;

	//Manejo Modificacion
	private String tipoModificacion;
	private boolean modifSupRecha;
	private boolean modifAnaRecha;
	private boolean modifAnaNoRecha;
	private boolean modifAdmRecha;
	private boolean modifAdmNoRecha;
	private boolean modifUltimoMes;
	private MultipartFile fileComprobanteModificacion;
	

	private boolean errorComprobanteVacioModif;
	private boolean comprobanteDescripcionVacioModif;
	private boolean comprobantePathVacioModif;	
	private boolean esSupervisorEmpresaSegmento;
	
	// Datos Generales
	private String empresa;
	private String empresasAsociadas;
	private String segmento;
	private String codCliente;
	private String razonSocialClientePerfil;
	private String codClienteAgrupador;
	
	private String fechaNtifDisponRetiroVal;
	private String cuitCliente;
	private String razonSocialClienteAgrupador;
	private String numeroHolding;
	private String nombreHolding;
	private String email;
	private String nombreAnalista;
	private String mailAnalista;
	private String idAnalista;
	private String idSupervisor;
	private String nombreSupervisor;
	private String mailSupervisorIconoMail;
	private String mailSupervisorIconoChat;
	

	private String copropietario;
	private String idCopropietario;
	private String mailCopropietario;
	private boolean existeDuplicado;
	private boolean existeReciboError;
	private String mensajeDuplicadoError;
	private String mensajeReciboError;

	// Boletas
	private String tipoValor;
	private String idTipoValor;
	private String importe;
	private String motivo;
	private String idMotivo;
	private String operacionAsociada;
	private String origen;
	private String idOrigen;
	private String acuerdo;
	private String idAcuerdo;
	private String bancoDeposito;
	private String idBancoDeposito;
	private String numeroCuenta;
	private String idNroCuenta;
	private String bancoOrigen;
	private String idBancoOrigen;
	private String numeroCheque;
	private String fechaVencimiento;
	private String nroChequeAReemplazar;
	private String idValorAsociadoAlChequeAReemplazar;
	private String reciboPreImpreso;
	private String fechaIngresoRecibo;
	private String chequeReemplazarId;
	private String chequeReemplazarNumero;
	

	private boolean imprimirBoleta = false;
	private boolean enviarBoletaMail = false;


	// Combos
	private boolean comboTipoBoleta = false;
	private boolean comboTipoImpuesto = false;
	private boolean comboOrigen = false;
	private boolean comboAcuerdo = false;
	private boolean comboBanco = false;
	private boolean comboCuenta = false;
	private boolean comboCopropietario = true;
	private boolean comboTipoValor = false;
	private boolean comboBancoOrigen = false;
	private boolean comboChequeReemplazar = false;
	private boolean comboEstadoValor = false;
	private boolean comboMotivoSuspension = false;
	private boolean comboMotivo = false;
	private boolean comboProvincia = false;
	private boolean comboTipoComprobante = false;
	private boolean comboLetraComprobante = false;
	private boolean checkImprimirBoleta;
	private boolean checkEnviarMailBoleta;
	private boolean comboComprobante = true;

	//Registro Avc
	private String idRegistroAvc;// usado en el alta a partir de un registro avc
	private String comprobanteError;
	private String descripcionComprobante;
	private String descripcionUnicidadRegistro;
	private boolean errorUnicidadRegistro;
	private String observacionRegistroAvc; // observacion usada en el alta a partir de un registro avc
	private boolean valorNuevo = true; //para saber si el valor lo estoy dando de alta o lo obtuve de la base
	private boolean adjuntando;
	private String estadoRegistroAvc;
	private boolean estaRechazandoConfirmacion; // Booleano que se usa para la validaciones
	private boolean actualizarSaldoEstado; //indica si se debe llamar al metodo de actualizacion de saldo o dar de alta
	private ShvValValor valorPorReversionActualizar;
	private boolean errorSaldoReversado;
	
	// Observaciones
	private String nuevaObservacion;
	private String observaciones; // observacion propia del valor. Se setea al dar de alta
	private String observacionesConfirmarAlta; // observacion usada en la confirmacion del alta a partir de un registro avc
	private String observacionMail; // utilizado en administrarValores en ValorServicio
	private String observacionConfirmacion; // utilizado en confirmarAvisoDePago y rechazarAvisoDePago
		
	// Aviso
	private String numeroDocumentoContable;
	private String numeroBoleta;
	private String fechaDeposito;
	private String numeroReferencia;
	private String cuit;
	private String cuitIbb;
	private String tipoImpuesto;
	private String idTipoImpuesto;
	private String nroConstancia;
	private String provincia;
	private String idProvincia;
	private String tipoComprobante;
	private String idTipoComprobante;
	private String letraComprobante;
	private String idLetraComprobante;
	private String numeroLegalComprobante;
	private String fechaTransferencia;
	private String fechaEmision;
	private boolean fechaEmisionInicialmenteNulo = false;								// Este campo es True si fechaEmision(Cheque) fue null antes de la Modificacion del Valor
	private String fechaEmisionCheque;
	private Date fechaEmisionAux;
	private String fechaNotificacionDisponibilidadRetiroValor;
	private Date fechaNotificacionDisponibilidadRetiroValorAux;
	

	// Extras
	private String estadoValor;
	private String saldoDisponible;
	private String saldoDisponibleFormateado;
	private String constancia;
	private String fechaConstancia;
	private String idEstadoValor;

	// INTERDEPOSITO
	private String fechaInterdeposito;
	private String numeroInterdepositoCdif;
	private String codOrganismo;

	private String partidaContable;
	private String numeroTramite;
	private String fechaTramiteCMS;

	// Manejo Comprobantes
	private List<ComprobanteDto> listaComprobantes;
	private boolean errorArchivoVacio;

	// Otros

	private String numeroValor;
	private String numeroPartidaContable;
	private String idBoleta;
	private Date fechaValorVto;
	private String fechaUltimoEstado;
	private Date fechaValor;
	private String tipoAvisoPago;
	private String tipo;

	// agregados con el mapeador
	private String fechaAltaValor;
	private String usuarioAutorizacion;
	private String idUsuarioAutorizacion;
	private String mailUsuarioAutorizacion;
	private String motivoSuspension;
	private Integer idMotivoSuspension;
	private String motivoRechazo;
	private String idLegajoChequeRechazado;
	private String fechaNotificacionRechazo;
	private String fechaRechazo;
	private String usuarioEjecutivo;
	private String idUsuarioEjecutivo;
	private String mailUsuarioEjecutivo;
	private String usuarioAsistente;
	private String idUsuarioAsistente;
	private String mailUsuarioAsistente;
	private String fechaDisponible;
	private String valorPadre;
	private String facturaRelacionada;
	private String documentacionOriginalRecibida;
	private String archivoValoresAconciliar;
	private Long idValor;
	private String cobroFormateado;
	
	private String pantallaRegreso;
	private Boolean volverBandeja;
	private String idTarea;
	
	private Boolean documentacionOriginalRecibidaBool;
	
	// campo agregado por Pablo
	private boolean generarValorDispoblePorDefecto = false;
	private boolean generarValorNoDispoblePorDefecto = false;

	/* Flags para saber si hay mas de un elemento para los combos */
	private ImprimirBoletaEstadoEnum boletaImpresaEstado;
	private EnviarMailBoletaEstadoEnum boletaEnviadaMailEstado;

	/* Flags de Rol Cajero Pagador */
	private boolean cajeroPagador;
	
	//Migracion
	private boolean migracion = false;
	private boolean errorMapeo = false;
	
	//Elijo un comprobante para borrar o mostrar un comprobante
	private String idComprobanteSelected;
	private String idRegistroAvcSelected;
	private String valorPorReversion;
	
	/**
	 * @author u573005 - Fabio Giaquinta
	 * Req. 11 Sprint 3
	 */
	private String idAnalistaTeamComercial;
	private String usuarioAnalistaTeamComercial;
	private String mailUsuarioAnalistaTeamComercial;
	private String codigoOrganismo;
	private String nroBoleta;
	private String nroCuitRetencion;
	private String referenciaValor;
	private String idSupervisorTeamComercial;
	private String usuarioSupervisorTeamComercial;
	private String mailUsuarioSupervisorTeamComercial;
	private String idGerenteRegionalTeamComercial;
	private String usuarioGerenteRegionalTeamComercial;
	private String mailUsuarioGerenteRegionalTeamComercial;
	private String idTipoRetencion;
	private String tipoRetencion;
	
	private String nombreApellidoAnalista;
	private String nombreApellidoCopropietario;
	private String nombreApellidoAutorizante;
	private String razonSocial;
	private Long idHolding;
	private String descripcionHolding;
	private String idAgenciaNegocio;
	private String descripcionAgenciaNegocio;
	private String segmentoAgenciaNegocio;
	
	//BoletaSinValor se usa este atributo porque boleta sin valor no setea un objeto cliente
	private boolean esBoletaSinValor = false;
	
	//Cliente
	private ClienteDto cliente;
	
	

//	-----------------------------------------
//	Getters y Setters
//	-----------------------------------------
	
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

	/**
	 * @return the documentacionOriginalRecibidaBool
	 */
	public Boolean getDocumentacionOriginalRecibidaBool() {
		return documentacionOriginalRecibidaBool;
	}

	/**
	 * @param documentacionOriginalRecibidaBool the documentacionOriginalRecibidaBool to set
	 */
	public void setDocumentacionOriginalRecibidaBool(
			Boolean documentacionOriginalRecibidaBool) {
		this.documentacionOriginalRecibidaBool = documentacionOriginalRecibidaBool;
	}
	
	// FORMATEO
	public String getAnalistaFormateado() {
		return this.urlFotoUsuario(idAnalista);
	}

	public String getFechaAltaFormateado() {
		return Utilidad.formatDateAndTimeFull(this.fechaValor);
	}

	/**
	 * 
	 * @return
	 */
	public String getFechaValorFormateado() {
		return Utilidad.formatDatePicker(this.fechaValor);
	}

	public String getImporteParaComparacion() {
		if (Utilidad.PESOS_CHAR.equalsIgnoreCase(importe.substring(0, 1))) {
			return Utilidad.formatCurrency(
					Utilidad.stringToBigDecimal(importe.substring(1)), 2);
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

	public boolean getEsNumeroBoletaObligatorio() {
		return this.modifAnaRecha && (String.valueOf(TipoValorEnum.EFECTIVO.getIdTipoValor()).equalsIgnoreCase(this.idTipoValor));
	}
	
	public boolean getEsNumeroBoletaReadOnly() {
		return !this.modifAnaRecha || 
				String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValor()).equalsIgnoreCase(this.idTipoValor) ||
				String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValor()).equalsIgnoreCase(this.idTipoValor) ||
				String.valueOf(TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoValor()).equalsIgnoreCase(this.idTipoValor);
	}
	
	public boolean getEsInterdeposito() {
		return TipoValorEnum.INTERDEPÓSITO.getIdTipoValorString().equalsIgnoreCase(this.idTipoValor);
	}
	
	public boolean getEsTransferencia() {
		return TipoValorEnum.TRANSFERENCIA.getIdTipoValorString().equalsIgnoreCase(this.idTipoValor);
	}
	
	public boolean getEsRetencionImpuesto() {
		return TipoValorEnum.RETENCIÓN_IMPUESTO.getIdTipoValorString().equalsIgnoreCase(this.idTipoValor);
	}
	
	public boolean getBloqueAcuerdoDeshabilitado() {
		return (this.getEsInterdeposito() || !(this.modifAnaRecha));
	}
	
	
	// FIN FORMATEO
	//

	public String getMensajeReciboError() {
		return mensajeReciboError;
	}

	public void setMensajeReciboError(String mensajeReciboError) {
		this.mensajeReciboError = mensajeReciboError;
	}

	public boolean isExisteReciboError() {
		return existeReciboError;
	}

	public void setExisteReciboError(boolean existeReciboError) {
		this.existeReciboError = existeReciboError;
	}

	public boolean getExisteDuplicado() {
		return existeDuplicado;
	}

	public void setExisteDuplicado(boolean duplicado) {
		this.existeDuplicado = duplicado;
	}

	public String getMensajeDuplicadoError() {
		return mensajeDuplicadoError;
	}

	public void setMensajeDuplicadoError(String mensajeDuplicadoError) {
		this.mensajeDuplicadoError = mensajeDuplicadoError;
	}

	public String getChequeReemplazarId() {
		return chequeReemplazarId;
	}

	public void setChequeReemplazarId(String chequeReemplazarId) {
		this.chequeReemplazarId = chequeReemplazarId;
	}

	public String getChequeReemplazarNumero() {
		return chequeReemplazarNumero;
	}

	public void setChequeReemplazarNumero(String chequeReemplazarNumero) {
		this.chequeReemplazarNumero = chequeReemplazarNumero;
	}

	public ValorDto() {
		this.listaComprobantes = new ArrayList<ComprobanteDto>();
	}

	public ValorDto(Long id) {
		super.setId(id);
		this.listaComprobantes = new ArrayList<ComprobanteDto>();
	}

	//
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

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getRazonSocialClientePerfil() {
		return razonSocialClientePerfil;
	}

	public void setRazonSocialClientePerfil(String razonSocialClientePerfil) {
		this.razonSocialClientePerfil = razonSocialClientePerfil;
	}

	public String getCodClienteAgrupador() {
		return codClienteAgrupador;
	}

	public void setCodClienteAgrupador(String codClienteAgrupador) {
		this.codClienteAgrupador = codClienteAgrupador;
	}

	public String getRazonSocialClienteAgrupador() {
		return razonSocialClienteAgrupador;
	}

	public void setRazonSocialClienteAgrupador(
			String razonSocialClienteAgrupador) {
		this.razonSocialClienteAgrupador = razonSocialClienteAgrupador;
	}

	public String getNumeroHolding() {
		return numeroHolding;
	}

	public void setNumeroHolding(String numeroHolding) {
		this.numeroHolding = numeroHolding;
	}

	public String getNombreHolding() {
		return nombreHolding;
	}

	public void setNombreHolding(String nombreHolding) {
		this.nombreHolding = nombreHolding;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombreAnalista() {
		return nombreAnalista;
	}

	public void setNombreAnalista(String nombreAnalista) {
		this.nombreAnalista = nombreAnalista;
	}

	public String getMailAnalista() {
		return mailAnalista;
	}

	public void setMailAnalista(String mailAnalista) {
		this.mailAnalista = mailAnalista;
	}

	public String getIdAnalista() {
		return idAnalista;
	}

	public void setIdAnalista(String idAnalista) {
		this.idAnalista = idAnalista;
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

	public String getTipoValor() {
		return tipoValor;
	}

	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}

	public String getIdTipoValor() {
		return idTipoValor;
	}

	public void setIdTipoValor(String idTipoValor) {
		this.idTipoValor = idTipoValor;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getIdMotivo() {
		return idMotivo;
	}

	public void setIdMotivo(String idMotivo) {
		this.idMotivo = idMotivo;
	}

	public String getOperacionAsociada() {
		return operacionAsociada;
	}

	public void setOperacionAsociada(String operacionAsociada) {
		this.operacionAsociada = operacionAsociada;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getIdOrigen() {
		return idOrigen;
	}

	public void setIdOrigen(String idOrigen) {
		this.idOrigen = idOrigen;
	}

	public String getAcuerdo() {
		return acuerdo;
	}

	public void setAcuerdo(String acuerdo) {
		this.acuerdo = acuerdo;
	}

	public String getIdAcuerdo() {
		return idAcuerdo;
	}

	public void setIdAcuerdo(String idAcuerdo) {
		this.idAcuerdo = idAcuerdo;
	}

	public String getBancoDeposito() {
		return bancoDeposito;
	}

	public void setBancoDeposito(String bancoDeposito) {
		this.bancoDeposito = bancoDeposito;
	}

	public String getIdBancoDeposito() {
		return idBancoDeposito;
	}

	public void setIdBancoDeposito(String idBancoDeposito) {
		this.idBancoDeposito = idBancoDeposito;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public String getIdNroCuenta() {
		return idNroCuenta;
	}

	public void setIdNroCuenta(String idNroCuenta) {
		this.idNroCuenta = idNroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getBancoOrigen() {
		return bancoOrigen;
	}

	public void setBancoOrigen(String bancoOrigen) {
		this.bancoOrigen = bancoOrigen;
	}

	public String getIdBancoOrigen() {
		return idBancoOrigen;
	}

	public void setIdBancoOrigen(String idBancoOrigen) {
		this.idBancoOrigen = idBancoOrigen;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getNroChequeAReemplazar() {
		return nroChequeAReemplazar;
	}

	public void setNroChequeAReemplazar(String nroChequeAReemplazar) {
		this.nroChequeAReemplazar = nroChequeAReemplazar;
	}

	public String getReciboPreImpreso() {
		return reciboPreImpreso;
	}

	public void setReciboPreImpreso(String reciboPreImpreso) {
		this.reciboPreImpreso = reciboPreImpreso;
	}

	public String getFechaIngresoRecibo() {
		return fechaIngresoRecibo;
	}

	public void setFechaIngresoRecibo(String fechaIngresoRecibo) {
		this.fechaIngresoRecibo = fechaIngresoRecibo;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getObservacionMail() {
		return observacionMail;
	}

	public void setObservacionMail(String observacionMail) {
		this.observacionMail = observacionMail;
	}

	public boolean isImprimirBoleta() {
		return imprimirBoleta;
	}

	public void setImprimirBoleta(boolean imprimirBoleta) {
		this.imprimirBoleta = imprimirBoleta;
	}

	public boolean isEnviarBoletaMail() {
		return enviarBoletaMail;
	}

	public void setEnviarBoletaMail(boolean enviarBoletaMail) {
		this.enviarBoletaMail = enviarBoletaMail;
	}

	public boolean isComboTipoBoleta() {
		return comboTipoBoleta;
	}

	public void setComboTipoBoleta(boolean comboTipoBoleta) {
		this.comboTipoBoleta = comboTipoBoleta;
	}

	public boolean isComboTipoImpuesto() {
		return comboTipoImpuesto;
	}

	public void setComboTipoImpuesto(boolean comboTipoImpuesto) {
		this.comboTipoImpuesto = comboTipoImpuesto;
	}

	public boolean isComboOrigen() {
		return comboOrigen;
	}

	public void setComboOrigen(boolean comboOrigen) {
		this.comboOrigen = comboOrigen;
	}

	public boolean isComboAcuerdo() {
		return comboAcuerdo;
	}

	public void setComboAcuerdo(boolean comboAcuerdo) {
		this.comboAcuerdo = comboAcuerdo;
	}

	public boolean isComboBanco() {
		return comboBanco;
	}

	public void setComboBanco(boolean comboBanco) {
		this.comboBanco = comboBanco;
	}

	public boolean isComboCuenta() {
		return comboCuenta;
	}

	public void setComboCuenta(boolean comboCuenta) {
		this.comboCuenta = comboCuenta;
	}

	public boolean isComboCopropietario() {
		return comboCopropietario;
	}

	public void setComboCopropietario(boolean comboCopropietario) {
		this.comboCopropietario = comboCopropietario;
	}

	public boolean isComboTipoValor() {
		return comboTipoValor;
	}

	public void setComboTipoValor(boolean comboTipoValor) {
		this.comboTipoValor = comboTipoValor;
	}

	public boolean isComboBancoOrigen() {
		return comboBancoOrigen;
	}

	public void setComboBancoOrigen(boolean comboBancoOrigen) {
		this.comboBancoOrigen = comboBancoOrigen;
	}

	public boolean isComboChequeReemplazar() {
		return comboChequeReemplazar;
	}

	public void setComboChequeReemplazar(boolean comboChequeReemplazar) {
		this.comboChequeReemplazar = comboChequeReemplazar;
	}

	public boolean isComboEstadoValor() {
		return comboEstadoValor;
	}

	public void setComboEstadoValor(boolean comboEstadoValor) {
		this.comboEstadoValor = comboEstadoValor;
	}

	public boolean isComboMotivoSuspension() {
		return comboMotivoSuspension;
	}

	public void setComboMotivoSuspension(boolean comboMotivoSuspension) {
		this.comboMotivoSuspension = comboMotivoSuspension;
	}

	public boolean isCheckImprimirBoleta() {
		return checkImprimirBoleta;
	}

	public void setCheckImprimirBoleta(boolean checkImprimirBoleta) {
		this.checkImprimirBoleta = checkImprimirBoleta;
	}

	public boolean isCheckEnviarMailBoleta() {
		return checkEnviarMailBoleta;
	}

	public void setCheckEnviarMailBoleta(boolean checkEnviarMailBoleta) {
		this.checkEnviarMailBoleta = checkEnviarMailBoleta;
	}

	public String getNumeroDocumentoContable() {
		return numeroDocumentoContable;
	}

	public void setNumeroDocumentoContable(String numeroDocumentoContable) {
		this.numeroDocumentoContable = numeroDocumentoContable;
	}

	public String getFechaDeposito() {
		return fechaDeposito;
	}

	public void setFechaDeposito(String fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}

	public String getNumeroReferencia() {
		return numeroReferencia;
	}

	public void setNumeroReferencia(String numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getCuitIbb() {
		return cuitIbb;
	}

	public void setCuitIbb(String cuitIbb) {
		this.cuitIbb = cuitIbb;
	}

	public String getTipoImpuesto() {
		return tipoImpuesto;
	}

	public void setTipoImpuesto(String tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}

	public String getIdTipoImpuesto() {
		return idTipoImpuesto;
	}

	public void setIdTipoImpuesto(String idTipoImpuesto) {
		this.idTipoImpuesto = idTipoImpuesto;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public String getIdTipoComprobante() {
		return idTipoComprobante;
	}

	public void setIdTipoComprobante(String idTipoComprobante) {
		this.idTipoComprobante = idTipoComprobante;
	}

	public String getLetraComprobante() {
		return letraComprobante;
	}

	public void setLetraComprobante(String letraComprobante) {
		this.letraComprobante = letraComprobante;
	}

	public String getIdLetraComprobante() {
		return idLetraComprobante;
	}

	public void setIdLetraComprobante(String idLetraComprobante) {
		this.idLetraComprobante = idLetraComprobante;
	}

	public String getNumeroLegalComprobante() {
		return numeroLegalComprobante;
	}

	public void setNumeroLegalComprobante(String numeroLegalComprobante) {
		this.numeroLegalComprobante = numeroLegalComprobante;
	}

	public String getFechaTransferencia() {
		return fechaTransferencia;
	}

	public void setFechaTransferencia(String fechaTransferencia) {
		this.fechaTransferencia = fechaTransferencia;
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

	public String getSaldoDisponible() {
		return saldoDisponible;
	}

	public void setSaldoDisponible(String saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	public String getNumeroValor() {
		return numeroValor;
	}

	public void setNumeroValor(String numeroValor) {
		this.numeroValor = numeroValor;
	}

	/**
	 * 
	 * @return
	 */
	public String getNumeroValorFormateadoConRetornoCarro() {
		return this.numeroValor.replace("<br>", "<br>&nbsp; &nbsp; &nbsp;");
	}
	

	/**
	 * Settea el campo numero valor agregando tipo valor e importe y omitiendo numero boleta
	 */
	public void formarNumeroValor() {
		switch (TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(this.getIdTipoValor()))) {
		case EFECTIVO:
			this.setNumeroValor("Valor - Tipo Valor: "+TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(this.getIdTipoValor())).getDescripcion() +" | "
					+" Importe: "+this.getImporte());
			break;
		case CHEQUE:
			this.setNumeroValor("Valor - Tipo Valor: "+TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(this.getIdTipoValor())).getDescripcion() +" | "
					+" Importe: "+this.getImporte() +" | "
					+" Bco. Origen: "+ this.getBancoOrigen() +" | "
					+" Nro. cheque: "+ this.getNumeroCheque());
			break;
		case CHEQUE_DIFERIDO:
			this.setNumeroValor("Valor - Tipo Valor: "+TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(this.getIdTipoValor())).getDescripcion() +" | "
					+" Importe: "+this.getImporte() +" | "
					+" Bco. Origen" + this.getBancoOrigen() +" | "
					+ " Nro. Cheque: " + this.getNumeroCheque() +" | "
					+ " Fecha Vto.: " + this.getFechaVencimiento());
			break;
		case RETENCIÓN_IMPUESTO:
			this.setNumeroValor("Valor - Tipo Valor: "+TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(this.getIdTipoValor())).getDescripcion() +" | "
					+" Importe: "+this.getImporte()+" | "
					+" Tipo: " +this.getTipoImpuesto()+" | " 
					+" Nro. Constancia: "+this.getNroConstancia());
			break;
		case TRANSFERENCIA:
			this.setNumeroValor("Valor - Tipo Valor: "+TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(this.getIdTipoValor())).getDescripcion() +" "+"|"
					+" Importe: "+this.getImporte() +" | "
					+" Nro. Referencia: "+ this.getNumeroReferencia());

			break;
		case BOLETA_DEPOSITO_EFECTIVO:
			this.setNumeroValor("Valor - Tipo Valor: "+TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(this.getIdTipoValor())).getDescripcion() +" | "
					+" Importe: "+this.getImporte());
			break;
		case BOLETA_DEPOSITO_CHEQUE:
			this.setNumeroValor("Valor - Tipo Valor: "+TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(this.getIdTipoValor())).getDescripcion() +" | "
					+" Importe: "+this.getImporte() +" | "
					+ " Bco. Origen: "+ this.getBancoOrigen() +" | "
					+ " Nro. Cheque: "+ this.getNumeroCheque());
			break;
		case BOLETA_DEPOSITO_CHEQUE_DIFERIDO:
			this.setNumeroValor("Valor - Tipo Valor: "+TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(this.getIdTipoValor())).getDescripcion() +" | "
					+" Importe: "+this.getImporte() +" | "
					+ " Bco. Origen: "+ this.getBancoOrigen() +" | "
					+ " Nro. Cheque: "+ this.getNumeroCheque() +" | "
					+ " Fecha Vto.: "+ this.getFechaVencimiento());

			break;
		case INTERDEPÓSITO:
			this.setNumeroValor("Valor - Tipo Valor: "+TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(this.getIdTipoValor())).getDescripcion() +" | "
					+" Importe: "+this.getImporte() +" | "
					+" Número Interdepósito: "+this.getNumeroInterdepositoCdif() +" | "
					+" Código Organismo: "+this.getCodOrganismo()
					);
			break;
		default:
			break;
		}
	}
	
	public String getNumeroPartidaContable() {
		return numeroPartidaContable;
	}

	public void setNumeroPartidaContable(String numeroPartidaContable) {
		this.numeroPartidaContable = numeroPartidaContable;
	}

	public String getIdBoleta() {
		return idBoleta;
	}

	public void setIdBoleta(String idBoleta) {
		this.idBoleta = idBoleta;
	}

	public String getNumeroBoleta() {
		return numeroBoleta;
	}

	public void setNumeroBoleta(String numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}

	public Date getFechaValorVto() {
		return fechaValorVto;
	}

	public void setFechaValorVto(Date fechaValorVto) {
		this.fechaValorVto = fechaValorVto;
	}

	public String getFechaUltimoEstado() {
		return fechaUltimoEstado;
	}

	public void setFechaUltimoEstado(String fechaUltimoEstado) {
		this.fechaUltimoEstado = fechaUltimoEstado;
	}

	public Date getFechaValor() {
		return fechaValor;
	}

	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
	}
	
	public String getTipoAvisoPago() {
		return tipoAvisoPago;
	}

	public void setTipoAvisoPago(String tipoAvisoPago) {
		this.tipoAvisoPago = tipoAvisoPago;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNroConstancia() {
		return nroConstancia;
	}

	public void setNroConstancia(String nroConstancia) {
		this.nroConstancia = nroConstancia;
	}

	public ImprimirBoletaEstadoEnum getBoletaImpresaEstado() {
		return boletaImpresaEstado;
	}

	public void setBoletaImpresaEstado(
			ImprimirBoletaEstadoEnum boletaImpresaEstado) {
		this.boletaImpresaEstado = boletaImpresaEstado;
	}

	public EnviarMailBoletaEstadoEnum getBoletaEnviadaMailEstado() {
		return boletaEnviadaMailEstado;
	}

	public void setBoletaEnviadaMailEstado(
			EnviarMailBoletaEstadoEnum boletaEnviadaMailEstado) {
		this.boletaEnviadaMailEstado = boletaEnviadaMailEstado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCodOrganismo() {
		return codOrganismo;
	}

	public void setCodOrganismo(String codOrganismo) {
		this.codOrganismo = codOrganismo;
	}

	public String getEstadoValor() {
		return estadoValor;
	}

	public void setEstadoValor(String estadoValor) {
		this.estadoValor = estadoValor;
	}

	public String getNumeroInterdepositoCdif() {
		return numeroInterdepositoCdif;
	}

	public void setNumeroInterdepositoCdif(String numeroInterdepositoCdif) {
		this.numeroInterdepositoCdif = numeroInterdepositoCdif;
	}

	public String getPartidaContable() {
		return partidaContable;
	}

	public void setPartidaContable(String partidaContable) {
		this.partidaContable = partidaContable;
	}

	public String getNumeroTramite() {
		return numeroTramite;
	}

	public void setNumeroTramite(String numeroTramite) {
		this.numeroTramite = numeroTramite;
	}

	public String getFechaTramiteCMS() {
		return fechaTramiteCMS;
	}

	public void setFechaTramiteCMS(String fechaTramiteCMS) {
		this.fechaTramiteCMS = fechaTramiteCMS;
	}

	public boolean isCajeroPagador() {
		return cajeroPagador;
	}

	public void setCajeroPagador(boolean cajeroPagador) {
		this.cajeroPagador = cajeroPagador;
	}

	public List<ComprobanteDto> getListaComprobantes() {
		return listaComprobantes;
	}

	public void setListaComprobantes(List<ComprobanteDto> listaComprobantes) {
		this.listaComprobantes = listaComprobantes;
	}



	public String getTipoModificacion() {
		return tipoModificacion;
	}

	public void setTipoModificacion(String tipoModificacion) {
		this.tipoModificacion = tipoModificacion;
	}

	public boolean isModifAnaRecha() {
		return modifAnaRecha;
	}

	public void setModifAnaRecha(boolean modifAnaRecha) {
		this.modifAnaRecha = modifAnaRecha;
	}

	public boolean isModifAnaNoRecha() {
		return modifAnaNoRecha;
	}

	public void setModifAnaNoRecha(boolean modifAnaNoRecha) {
		this.modifAnaNoRecha = modifAnaNoRecha;
	}

	public boolean isModifAdmRecha() {
		return modifAdmRecha;
	}

	public void setModifAdmRecha(boolean modifAdmRecha) {
		this.modifAdmRecha = modifAdmRecha;
	}

	public boolean isModifAdmNoRecha() {
		return modifAdmNoRecha;
	}

	public void setModifAdmNoRecha(boolean modifAdmNoRecha) {
		this.modifAdmNoRecha = modifAdmNoRecha;
	}

	public String getUsuarioAutorizacion() {
		return usuarioAutorizacion;
	}

	public void setUsuarioAutorizacion(String usuarioAutorizacion) {
		this.usuarioAutorizacion = usuarioAutorizacion;
	}

	public String getMotivoSuspension() {
		return motivoSuspension;
	}

	public void setMotivoSuspension(String motivoSuspension) {
		this.motivoSuspension = motivoSuspension;
	}

	public String getUsuarioEjecutivo() {
		return usuarioEjecutivo;
	}

	public void setUsuarioEjecutivo(String usuarioEjecutivo) {
		this.usuarioEjecutivo = usuarioEjecutivo;
	}

	public String getUsuarioAsistente() {
		return usuarioAsistente;
	}

	public void setUsuarioAsistente(String usuarioAsistente) {
		this.usuarioAsistente = usuarioAsistente;
	}

	public String getFechaDisponible() {
		return fechaDisponible;
	}

	public void setFechaDisponible(String fechaDisponible) {
		this.fechaDisponible = fechaDisponible;
	}

	public String getValorPadre() {
		return valorPadre;
	}

	public void setValorPadre(String valorPadre) {
		this.valorPadre = valorPadre;
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

	public String getArchivoValoresAconciliar() {
		return archivoValoresAconciliar;
	}

	public void setArchivoValoresAconciliar(String archivoValoresAconciliar) {
		this.archivoValoresAconciliar = archivoValoresAconciliar;
	}

	public String getFechaInterdeposito() {
		return fechaInterdeposito;
	}

	public void setFechaInterdeposito(String fechaInterdeposito) {
		this.fechaInterdeposito = fechaInterdeposito;
	}

	public String getConstancia() {
		return constancia;
	}

	public void setConstancia(String constancia) {
		this.constancia = constancia;
	}

	public String getFechaConstancia() {
		return fechaConstancia;
	}

	public void setFechaConstancia(String fechaConstancia) {
		this.fechaConstancia = fechaConstancia;
	}

	public String getNombreSupervisor() {
		return nombreSupervisor;
	}

	public void setNombreSupervisor(String nombreSupervisor) {
		this.nombreSupervisor = nombreSupervisor;
	}

	public Long getIdValor() {
		return idValor;
	}

	public void setIdValor(Long idValor) {
		this.idValor = idValor;
	}

	/**
	 * @return the fechaAltaValor
	 */
	public String getFechaAltaValor() {
		return fechaAltaValor;
	}

	/**
	 * @param fechaAltaValor the fechaAltaValor to set
	 */
	public void setFechaAltaValor(String fechaAltaValor) {
		this.fechaAltaValor = fechaAltaValor;
	}
	
	/**
	 * @return the idSupervisor
	 */
	public String getIdSupervisor() {
		return idSupervisor;
	}

	/**
	 * @param idSupervisor the idSupervisor to set
	 */
	public void setIdSupervisor(String idSupervisor) {
		this.idSupervisor = idSupervisor;
	}

	/**
	 * @return the generarValorDispoblePorDefecto
	 */
	public boolean isGenerarValorDispoblePorDefecto() {
		return generarValorDispoblePorDefecto;
	}

	/**
	 * @param generarValorDispoblePorDefecto
	 *            the generarValorDispoblePorDefecto to set
	 */
	public void setGenerarValorDispoblePorDefecto(
			boolean generarValorDispoblePorDefecto) {
		this.generarValorDispoblePorDefecto = generarValorDispoblePorDefecto;
	}

	/**
	 * @return the comboMotivo
	 */
	public boolean isComboMotivo() {
		return comboMotivo;
	}

	/**
	 * @param comboMotivo the comboMotivo to set
	 */
	public void setComboMotivo(boolean comboMotivo) {
		this.comboMotivo = comboMotivo;
	}

	/**
	 * @return the comboProvincia
	 */
	public boolean isComboProvincia() {
		return comboProvincia;
	}

	/**
	 * @param comboProvincia the comboProvincia to set
	 */
	public void setComboProvincia(boolean comboProvincia) {
		this.comboProvincia = comboProvincia;
	}

	/**
	 * @return the comboTipoComprobante
	 */
	public boolean isComboTipoComprobante() {
		return comboTipoComprobante;
	}

	/**
	 * @param comboTipoComprobante the comboTipoComprobante to set
	 */
	public void setComboTipoComprobante(boolean comboTipoComprobante) {
		this.comboTipoComprobante = comboTipoComprobante;
	}

	/**
	 * @return the comboLetraComprobante
	 */
	public boolean isComboLetraComprobante() {
		return comboLetraComprobante;
	}

	/**
	 * @param comboLetraComprobante the comboLetraComprobante to set
	 */
	public void setComboLetraComprobante(boolean comboLetraComprobante) {
		this.comboLetraComprobante = comboLetraComprobante;
	}

	/**
	 * @return the comboComprobante
	 */
	public boolean isComboComprobante() {
		return comboComprobante;
	}

	/**
	 * @param comboComprobante the comboComprobante to set
	 */
	public void setComboComprobante(boolean comboComprobante) {
		this.comboComprobante = comboComprobante;
	}

	public MultipartFile getFileComprobanteModificacion() {
		return fileComprobanteModificacion;
	}

	public void setFileComprobanteModificacion(
			MultipartFile fileComprobanteModificacion) {
		this.fileComprobanteModificacion = fileComprobanteModificacion;
	}

	public boolean isModifUltimoMes() {
		return modifUltimoMes;
	}

	public void setModifUltimoMes(boolean modifUltimoMes) {
		this.modifUltimoMes = modifUltimoMes;
	}

	public boolean isErrorComprobanteVacioModif() {
		return errorComprobanteVacioModif;
	}

	public void setErrorComprobanteVacioModif(boolean errorComprobanteVacioModif) {
		this.errorComprobanteVacioModif = errorComprobanteVacioModif;
	}

	public String getMailCopropietario() {
		return mailCopropietario;
	}

	public void setMailCopropietario(String mailCopropietario) {
		this.mailCopropietario = mailCopropietario;
	}

	public String getMailUsuarioAutorizacion() {
		return mailUsuarioAutorizacion;
	}

	public void setMailUsuarioAutorizacion(String mailUsuarioAutorizacion) {
		this.mailUsuarioAutorizacion = mailUsuarioAutorizacion;
	}

	/**
	 * @return the mailSupervisorIconoMail
	 */
	public String getMailSupervisorIconoMail() {
		return mailSupervisorIconoMail;
	}

	/**
	 * @param mailSupervisorIconoMail the mailSupervisorIconoMail to set
	 */
	public void setMailSupervisorIconoMail(String mailSupervisorIconoMail) {
		this.mailSupervisorIconoMail = mailSupervisorIconoMail;
	}

	/**
	 * @return the mailSupervisorIconoChat
	 */
	public String getMailSupervisorIconoChat() {
		return mailSupervisorIconoChat;
	}

	/**
	 * @param mailSupervisorIconoChat the mailSupervisorIconoChat to set
	 */
	public void setMailSupervisorIconoChat(String mailSupervisorIconoChat) {
		this.mailSupervisorIconoChat = mailSupervisorIconoChat;
	}

	public Date getFechaEmisionAux() {
		return fechaEmisionAux;
	}

	public void setFechaEmisionAux(Date fechaEmisionAux) {
		this.fechaEmisionAux = fechaEmisionAux;
	}

	public String getSaldoDisponibleFormateado() {
		return saldoDisponibleFormateado;
	}

	public void setSaldoDisponibleFormateado(String saldoDisponibleFormateado) {
		this.saldoDisponibleFormateado = saldoDisponibleFormateado;
	}


	public boolean isComprobanteDescripcionVacioModif() {
		return comprobanteDescripcionVacioModif;
	}

	public void setComprobanteDescripcionVacioModif(
			boolean comprobanteDescripcionVacioModif) {
		this.comprobanteDescripcionVacioModif = comprobanteDescripcionVacioModif;
	}

	public boolean isComprobantePathVacioModif() {
		return comprobantePathVacioModif;
	}

	public void setComprobantePathVacioModif(boolean comprobantePathVacioModif) {
		this.comprobantePathVacioModif = comprobantePathVacioModif;
	}

	public boolean isErrorArchivoVacio() {
		return errorArchivoVacio;
	}

	public void setErrorArchivoVacio(boolean errorArchivoVacio) {
		this.errorArchivoVacio = errorArchivoVacio;
	}
	
	/**
	 * @return the migracion
	 */
	public boolean isMigracion() {
		return migracion;
	}

	/**
	 * @param migracion the migracion to set
	 */
	public void setMigracion(boolean migracion) {
		this.migracion = migracion;
	}

	/**
	 * @return the observacionesConfirmarAlta
	 */
	public String getObservacionesConfirmarAlta() {
		return observacionesConfirmarAlta;
	}

	/**
	 * @param observacionesConfirmarAlta
	 *            the observacionesConfirmarAlta to set
	 */
	public void setObservacionesConfirmarAlta(String observacionesConfirmarAlta) {
		this.observacionesConfirmarAlta = observacionesConfirmarAlta;
	}
	
	public String getIdRegistroAvc() {
		return idRegistroAvc;
	}

	public void setIdRegistroAvc(String idRegistroAvc) {
		this.idRegistroAvc = idRegistroAvc;
	}

	public boolean isGenerarValorNoDispoblePorDefecto() {
		return generarValorNoDispoblePorDefecto;
	}

	public void setGenerarValorNoDispoblePorDefecto(
			boolean generarValorNoDispoblePorDefecto) {
		this.generarValorNoDispoblePorDefecto = generarValorNoDispoblePorDefecto;
	}

	public String getComprobanteError() {
		return comprobanteError;
	}

	public void setComprobanteError(String comprobanteError) {
		this.comprobanteError = comprobanteError;
	}

	public String getDescripcionComprobante() {
		return descripcionComprobante;
	}

	public void setDescripcionComprobante(String descripcionComprobante) {
		this.descripcionComprobante = descripcionComprobante;
	}

	public String getDescripcionUnicidadRegistro() {
		return descripcionUnicidadRegistro;
	}

	public void setDescripcionUnicidadRegistro(String descripcionUnicidadRegistro) {
		this.descripcionUnicidadRegistro = descripcionUnicidadRegistro;
	}

	public boolean isErrorUnicidadRegistro() {
		return errorUnicidadRegistro;
	}

	public void setErrorUnicidadRegistro(boolean errorUnicidadRegistro) {
		this.errorUnicidadRegistro = errorUnicidadRegistro;
	}

	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}
	public String getObservacionRegistroAvc() {
		return observacionRegistroAvc;
	}

	public void setObservacionRegistroAvc(String observacionRegistroAvc) {
		this.observacionRegistroAvc = observacionRegistroAvc;
	}

	public String getObservacionConfirmacion() {
		return Validaciones.isNullOrEmpty(observacionConfirmacion)?"":observacionConfirmacion.trim();
	}

	public void setObservacionConfirmacion(String observacionConfirmacion) {
		this.observacionConfirmacion = observacionConfirmacion;
	}

	public boolean isValorNuevo() {
		return valorNuevo;
	}

	public void setValorNuevo(boolean valorNuevo) {
		this.valorNuevo = valorNuevo;
	}

	public boolean isAdjuntando() {
		return adjuntando;
	}

	public void setAdjuntando(boolean adjuntando) {
		this.adjuntando = adjuntando;
	}

	public String getEstadoRegistroAvc() {
		return estadoRegistroAvc;
	}

	public void setEstadoRegistroAvc(String estadoRegistroAvc) {
		this.estadoRegistroAvc = estadoRegistroAvc;
	}

	public boolean isEstaRechazandoConfirmacion() {
		return estaRechazandoConfirmacion;
	}

	public void setEstaRechazandoConfirmacion(boolean estaRechazandoConfirmacion) {
		this.estaRechazandoConfirmacion = estaRechazandoConfirmacion;
	}

	public boolean isModifSupRecha() {
		return modifSupRecha;
	}

	public void setModifSupRecha(boolean modifSupRecha) {
		this.modifSupRecha = modifSupRecha;
	}

	public String getIdValorAsociadoAlChequeAReemplazar() {
		return idValorAsociadoAlChequeAReemplazar;
	}

	public void setIdValorAsociadoAlChequeAReemplazar(
			String idValorAsociadoAlChequeAReemplazar) {
		this.idValorAsociadoAlChequeAReemplazar = idValorAsociadoAlChequeAReemplazar;
	}

	public String getIdComprobanteSelected() {
		return idComprobanteSelected;
	}

	public void setIdComprobanteSelected(String idComprobanteSelected) {
		this.idComprobanteSelected = idComprobanteSelected;
	}

	public String getIdRegistroAvcSelected() {
		return idRegistroAvcSelected;
	}

	public void setIdRegistroAvcSelected(String idRegistroAvcSelected) {
		this.idRegistroAvcSelected = idRegistroAvcSelected;
	}

	public String getValorPorReversion() {
		return valorPorReversion;
	}

	public void setValorPorReversion(String valorPorReversion) {
		this.valorPorReversion = valorPorReversion;
	}

	public boolean isErrorMapeo() {
		return errorMapeo;
	}

	public void setErrorMapeo(boolean errorMapeo) {
		this.errorMapeo = errorMapeo;
	}

	/**
	 * @return the actualizarSaldoEstado
	 */
	public boolean isActualizarSaldoEstado() {
		return actualizarSaldoEstado;
	}

	/**
	 * @param actualizarSaldoEstado the actualizarSaldoEstado to set
	 */
	public void setActualizarSaldoEstado(boolean actualizarSaldoEstado) {
		this.actualizarSaldoEstado = actualizarSaldoEstado;
	}

	public ShvValValor getValorPorReversionActualizar() {
		return valorPorReversionActualizar;
	}

	public void setValorPorReversionActualizar(
			ShvValValor valorPorReversionActualizar) {
		this.valorPorReversionActualizar = valorPorReversionActualizar;
	}

	public boolean isErrorSaldoReversado() {
		return errorSaldoReversado;
	}

	public void setErrorSaldoReversado(boolean errorSaldoReversado) {
		this.errorSaldoReversado = errorSaldoReversado;
	}

	public String getNuevaObservacion() {
		return nuevaObservacion;
	}

	public void setNuevaObservacion(String nuevaObservacion) {
		this.nuevaObservacion = nuevaObservacion;
	}

	public String getMailUsuarioEjecutivo() {
		return mailUsuarioEjecutivo;
	}

	public void setMailUsuarioEjecutivo(String mailUsuarioEjecutivo) {
		this.mailUsuarioEjecutivo = mailUsuarioEjecutivo;
	}

	public String getMailUsuarioAsistente() {
		return mailUsuarioAsistente;
	}

	public void setMailUsuarioAsistente(String mailUsuarioAsistente) {
		this.mailUsuarioAsistente = mailUsuarioAsistente;
	}

	public String getIdAnalistaTeamComercial() {
		return idAnalistaTeamComercial;
	}

	public void setIdAnalistaTeamComercial(String idAnalistaTeamComercial) {
		this.idAnalistaTeamComercial = idAnalistaTeamComercial;
	}

	public String getCodigoOrganismo() {
		return codigoOrganismo;
	}

	public void setCodigoOrganismo(String codigoOrganismo) {
		this.codigoOrganismo = codigoOrganismo;
	}

	public String getNroBoleta() {
		return nroBoleta;
	}

	public void setNroBoleta(String nroBoleta) {
		this.nroBoleta = nroBoleta;
	}

	public String getNroCuitRetencion() {
		return nroCuitRetencion;
	}

	public void setNroCuitRetencion(String nroCuitRetencion) {
		this.nroCuitRetencion = nroCuitRetencion;
	}

	public String getReferenciaValor() {
		return referenciaValor;
	}

	public void setReferenciaValor(String referenciaValor) {
		this.referenciaValor = referenciaValor;
	}

	public String getUsuarioAnalistaTeamComercial() {
		return usuarioAnalistaTeamComercial;
	}

	public void setUsuarioAnalistaTeamComercial(String usuarioAnalistaTeamComercial) {
		this.usuarioAnalistaTeamComercial = usuarioAnalistaTeamComercial;
	}

	public String getMailUsuarioAnalistaTeamComercial() {
		return mailUsuarioAnalistaTeamComercial;
	}

	public void setMailUsuarioAnalistaTeamComercial(
			String mailUsuarioAnalistaTeamComercial) {
		this.mailUsuarioAnalistaTeamComercial = mailUsuarioAnalistaTeamComercial;
	}

	public String getIdSupervisorTeamComercial() {
		return idSupervisorTeamComercial;
	}

	public void setIdSupervisorTeamComercial(String idSupervisorTeamComercial) {
		this.idSupervisorTeamComercial = idSupervisorTeamComercial;
	}

	public String getUsuarioSupervisorTeamComercial() {
		return usuarioSupervisorTeamComercial;
	}

	public void setUsuarioSupervisorTeamComercial(
			String usuarioSupervisorTeamComercial) {
		this.usuarioSupervisorTeamComercial = usuarioSupervisorTeamComercial;
	}

	public String getMailUsuarioSupervisorTeamComercial() {
		return mailUsuarioSupervisorTeamComercial;
	}

	public void setMailUsuarioSupervisorTeamComercial(
			String mailUsuarioSupervisorTeamComercial) {
		this.mailUsuarioSupervisorTeamComercial = mailUsuarioSupervisorTeamComercial;
	}

	public String getIdGerenteRegionalTeamComercial() {
		return idGerenteRegionalTeamComercial;
	}

	public void setIdGerenteRegionalTeamComercial(
			String idGerenteRegionalTeamComercial) {
		this.idGerenteRegionalTeamComercial = idGerenteRegionalTeamComercial;
	}

	public String getUsuarioGerenteRegionalTeamComercial() {
		return usuarioGerenteRegionalTeamComercial;
	}

	public void setUsuarioGerenteRegionalTeamComercial(
			String usuarioGerenteRegionalTeamComercial) {
		this.usuarioGerenteRegionalTeamComercial = usuarioGerenteRegionalTeamComercial;
	}

	public String getMailUsuarioGerenteRegionalTeamComercial() {
		return mailUsuarioGerenteRegionalTeamComercial;
	}

	public void setMailUsuarioGerenteRegionalTeamComercial(
			String mailUsuarioGerenteRegionalTeamComercial) {
		this.mailUsuarioGerenteRegionalTeamComercial = mailUsuarioGerenteRegionalTeamComercial;
	}

	public String getTipoRetencion() {
		return tipoRetencion;
	}

	public void setTipoRetencion(String tipoRetencion) {
		this.tipoRetencion = tipoRetencion;
	}

	public String getCobroFormateado() {
		return cobroFormateado;
	}

	public void setCobroFormateado(String cobroFormateado) {
		this.cobroFormateado = cobroFormateado;
	}

	public String getPantallaRegreso() {
		return pantallaRegreso;
	}

	public void setPantallaRegreso(String pantallaRegreso) {
		this.pantallaRegreso = pantallaRegreso;
	}

	public Boolean getVolverBandeja() {
		return volverBandeja;
	}

	public void setVolverBandeja(Boolean volverBandeja) {
		this.volverBandeja = volverBandeja;
	}

	public String getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(String idTarea) {
		this.idTarea = idTarea;
	}
	
	/**
	 * Metodo que se usa para habilitar la edicion de la fecha ingreso recibo
	 * cuando se edita un valor de tipo retencion o de tipo valor con boleta.
	 * @return
	 */
	public Boolean getEditarFechaIngresoRecibo() {
		return (
			TipoValorEnum.RETENCIÓN_IMPUESTO.getIdTipoValorString().equals(getIdTipoValor())
		    && Utilidad.formatCurrencySacarPesos(getImporte()).equals(Utilidad.formatCurrencySacarPesos(getSaldoDisponible()))
			&&	(estadoValor.equals(Estado.VAL_DISPONIBLE.name())
				|| estadoValor.equals(Estado.VAL_AVISO_PAGO_RECHAZADO.name()))
			)
	
			||
	
			(
			(TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValorString().equals(getIdTipoValor())
				||TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValorString().equals(getIdTipoValor())
				||TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoValorString().equals(getIdTipoValor()))
			&& Utilidad.formatCurrencySacarPesos(getImporte()).equals(Utilidad.formatCurrencySacarPesos(getSaldoDisponible()))
			&& (estadoValor.equals(Estado.VAL_DISPONIBLE.name()) 
				|| estadoValor.equals(Estado.VAL_BOLETA_RECHAZADA.name()) 
				||  estadoValor.equals(Estado.VAL_BOLETA_PENDIENTE_CONCILIACION.name()))
			);
	
	}

	public boolean getEsSupervisorEmpresaSegmento() {
		return esSupervisorEmpresaSegmento;
	}

	public void setEsSupervisorEmpresaSegmento(boolean esSupervisorEmpresaSegmento) {
		this.esSupervisorEmpresaSegmento = esSupervisorEmpresaSegmento;
	}

	public void setIdEstadoValor(String idEstadoValor) {
		this.idEstadoValor = idEstadoValor;
	}

	public String getIdEstadoValor() {
		return idEstadoValor;
	}

	public String getIdUsuarioAutorizacion() {
		return idUsuarioAutorizacion;
	}

	public void setIdUsuarioAutorizacion(String idUsuarioAutorizacion) {
		this.idUsuarioAutorizacion = idUsuarioAutorizacion;
	}

	public String getIdUsuarioEjecutivo() {
		return idUsuarioEjecutivo;
	}

	public void setIdUsuarioEjecutivo(String idUsuarioEjecutivo) {
		this.idUsuarioEjecutivo = idUsuarioEjecutivo;
	}

	public String getIdUsuarioAsistente() {
		return idUsuarioAsistente;
	}

	public void setIdUsuarioAsistente(String idUsuarioAsistente) {
		this.idUsuarioAsistente = idUsuarioAsistente;
	}

	public String getIdTipoRetencion() {
		return idTipoRetencion;
	}

	public void setIdTipoRetencion(String idTipoRetencion) {
		this.idTipoRetencion = idTipoRetencion;
	}
	
	public String getNombreApellidoAnalista() {
		return nombreApellidoAnalista;
	}

	public void setNombreApellidoAnalista(String nombreApellidoAnalista) {
		this.nombreApellidoAnalista = nombreApellidoAnalista;
	}

	public String getNombreApellidoCopropietario() {
		return nombreApellidoCopropietario;
	}

	public void setNombreApellidoCopropietario(String nombreApellidoCopropietario) {
		this.nombreApellidoCopropietario = nombreApellidoCopropietario;
	}

	public String getNombreApellidoAutorizante() {
		return nombreApellidoAutorizante;
	}

	public void setNombreApellidoAutorizante(String nombreApellidoAutorizante) {
		this.nombreApellidoAutorizante = nombreApellidoAutorizante;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public Long getIdHolding() {
		return idHolding;
	}

	public void setIdHolding(Long idHolding) {
		this.idHolding = idHolding;
	}

	public String getDescripcionHolding() {
		return descripcionHolding;
	}

	public void setDescripcionHolding(String descripcionHolding) {
		this.descripcionHolding = descripcionHolding;
	}

	public String getIdAgenciaNegocio() {
		return idAgenciaNegocio;
	}

	public void setIdAgenciaNegocio(String idAgenciaNegocio) {
		this.idAgenciaNegocio = idAgenciaNegocio;
	}

	public String getDescripcionAgenciaNegocio() {
		return descripcionAgenciaNegocio;
	}

	public void setDescripcionAgenciaNegocio(String descripcionAgenciaNegocio) {
		this.descripcionAgenciaNegocio = descripcionAgenciaNegocio;
	}

	public String getSegmentoAgenciaNegocio() {
		return segmentoAgenciaNegocio;
	}

	public void setSegmentoAgenciaNegocio(String segmentoAgenciaNegocio) {
		this.segmentoAgenciaNegocio = segmentoAgenciaNegocio;
	}

	/**
	 * Metodo que se usa para habilitar la edicion de la fecha ingreso recibo
	 * cuando se edita un valor de tipo retencion o de tipo valor con boleta.
	 * @return
	 */
	
	public Boolean editarFechaIngresoRecibo(UsuarioSesion userSesion, boolean fechaIngresoRecivo) {
		boolean editar = false;

		if (
			TipoValorEnum.RETENCIÓN_IMPUESTO.getIdTipoValorString().equals(getIdTipoValor()) &&
			Utilidad.formatCurrencySacarPesos(getImporte()).equals(Utilidad.formatCurrencySacarPesos(getSaldoDisponible())) &&
			(
					idEstadoValor.equals(Estado.VAL_DISPONIBLE.name()) ||
				idEstadoValor.equals(Estado.VAL_AVISO_PAGO_RECHAZADO.name())
			)
		) {
			editar = true;
		} else if (
			(
				TipoValorEnum.BOLETA_DEPOSITO_CHEQUE.getIdTipoValorString().equals(getIdTipoValor()) ||
				TipoValorEnum.BOLETA_DEPOSITO_CHEQUE_DIFERIDO.getIdTipoValorString().equals(getIdTipoValor()) ||
				TipoValorEnum.BOLETA_DEPOSITO_EFECTIVO.getIdTipoValorString().equals(getIdTipoValor())
			) &&
				Utilidad.formatCurrencySacarPesos(getImporte()).equals(Utilidad.formatCurrencySacarPesos(getSaldoDisponible())) &&
			(
				idEstadoValor.equals(Estado.VAL_DISPONIBLE.name()) ||
				idEstadoValor.equals(Estado.VAL_BOLETA_RECHAZADA.name()) ||
				idEstadoValor.equals(Estado.VAL_BOLETA_PENDIENTE_CONCILIACION.name())
			)
		) {
			editar = true;
		} else if (
			TipoValorEnum.CHEQUE.getIdTipoValorString().equals(getIdTipoValor()) ||
			TipoValorEnum.CHEQUE_DIFERIDO.getIdTipoValorString().equals(getIdTipoValor()) ||
			TipoValorEnum.EFECTIVO.getIdTipoValorString().equals(getIdTipoValor()) ||
			TipoValorEnum.RETENCIÓN_IMPUESTO.getIdTipoValorString().equals(getIdTipoValor())
		) {
			boolean perfilAnalista = userSesion.esAnalista();
			boolean perfilSupervisor = userSesion.esSupervisor();
			boolean perfilAdministrador = userSesion.esAdminValor();
			boolean perfilCajeroPagador = userSesion.esCajeroPagador();

			if (perfilAnalista || perfilSupervisor || perfilCajeroPagador) {
				if (
					Estado.VAL_ANULADO.name().equals(this.idEstadoValor) ||
					Estado.VAL_DISPONIBLE.name().equals(this.idEstadoValor) ||
					Estado.VAL_SUSPENDIDO.name().equals(this.idEstadoValor)
				) {
					if (fechaIngresoRecivo) {
						if (Utilidad.formatCurrencySacarPesos(getImporte()).equals(Utilidad.formatCurrencySacarPesos(getSaldoDisponible()))) {
							editar = true;
						}
					} else {
						editar = true;
					}
					
				} else if (
					Estado.VAL_BOLETA_PENDIENTE_CONCILIACION.name().equals(this.idEstadoValor) ||
					Estado.VAL_BOLETA_RECHAZADA.name().equals(this.idEstadoValor) ||
					Estado.VAL_AVISO_PAGO_RECHAZADO.name().equals(this.idEstadoValor) ||
					Estado.VAL_USADO.name().equals(this.idEstadoValor)
				) {
					editar = true;
				}
			} else if (perfilAdministrador && Estado.VAL_SUSPENDIDO.name().equals(this.idEstadoValor)) {
				editar = false;
			}
		}
		
		return editar;
	}
	public boolean isEsAvispoDePago() {
		if (
			this.idTipoValor != null &&
			!"".equals(this.idTipoValor) 
		) {
			return TipoValorEnum.esTipoAvisoDePago(
				TipoValorEnum.getEnumByIdTipoValor(
					Long.parseLong(this.idTipoValor)
			));
		}
		return false;
	}
	public boolean isEsBoletaSinValor() {
		if (
			this.idTipoValor != null &&
			!"".equals(this.idTipoValor) 
		) {
			return TipoValorEnum.esTipoBoletaSinValor(
				TipoValorEnum.getEnumByIdTipoValor(
					Long.parseLong(this.idTipoValor)
			));
		}
		return false;
	}
	public boolean isEsBoletaConValor() {
		if (
			this.idTipoValor != null &&
			!"".equals(this.idTipoValor) 
		) {
			return TipoValorEnum.esTipoBoletaConValor(
				TipoValorEnum.getEnumByIdTipoValor(
					Long.parseLong(this.tipoValor)
			));
		}
		return false;
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

	/**
	 * @return the idMotivoSuspension
	 */
	public Integer getIdMotivoSuspension() {
		return idMotivoSuspension;
	}

	/**
	 * @param idMotivoSuspension the idMotivoSuspension to set
	 */
	public void setIdMotivoSuspension(Integer idMotivoSuspension) {
		this.idMotivoSuspension = idMotivoSuspension;
	}

	/**
	 * @return the cliente
	 */
	public ClienteDto getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(ClienteDto cliente) {
		this.cliente = cliente;
	}


	/**
	 * @return the fechaNotificacionDisponibilidadRetiroValor
	 */
	public String getFechaNotificacionDisponibilidadRetiroValor() {
		return fechaNotificacionDisponibilidadRetiroValor;
	}

	/**
	 * @param fechaNotificacionDisponibilidadRetiroValor the fechaNotificacionDisponibilidadRetiroValor to set
	 */
	public void setFechaNotificacionDisponibilidadRetiroValor(String fechaNotificacion) {
		this.fechaNotificacionDisponibilidadRetiroValor = fechaNotificacion;
	}

	/**
	 * @return the fechaNotificacionDisponibilidadRetiroValorAux
	 */
	public Date getFechaNotificacionDisponibilidadRetiroValorAux() {
		return fechaNotificacionDisponibilidadRetiroValorAux;
	}

	/**
	 * @param fechaNotificacionDisponibilidadRetiroValorAux the fechaNotificacionDisponibilidadRetiroValorAux to set
	 */
	public void setFechaNotificacionDisponibilidadRetiroValorAux(
			Date fechaNotificacionDisponibilidadRetiroValorAux) {
		this.fechaNotificacionDisponibilidadRetiroValorAux = fechaNotificacionDisponibilidadRetiroValorAux;
	}
	
	public boolean esBoletaSinValor() {
		return this.esBoletaSinValor;
	}
	
	/**
	 * @param esBoletaSinValor BoletaSinValor se usa este atributo porque boleta sin valor no setea un objeto cliente
	 */
	public void setEsBoletaSinValor(boolean esBoletaSinValor) {
		this.esBoletaSinValor = esBoletaSinValor;
	}

	/**
	 * @return the fechaEmisionInicialmenteNulo
	 */
	public boolean isFechaEmisionInicialmenteNulo() {
		return fechaEmisionInicialmenteNulo;
	}

	/**
	 * @param fechaEmisionInicialmenteNulo the fechaEmisionInicialmenteNulo to set
	 */
	public void setFechaEmisionInicialmenteNulo(boolean fechaEmisionInicialmenteNulo) {
		this.fechaEmisionInicialmenteNulo = fechaEmisionInicialmenteNulo;
	}
	
	

//	/**
//	 * @return the fechaEmisionCheques
//	 */
//	public String getFechaEmisionCheques() {
//		return fechaEmisionCheques;
//	}
//
//	/**
//	 * @param fechaEmisionCheques the fechaEmisionCheques to set
//	 */
//	public void setFechaEmisionCheques(String fechaEmisionCheques) {
//		this.fechaEmisionCheques = fechaEmisionCheques;
//	}
//	
//	
//	
	
}