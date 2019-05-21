package ar.com.telecom.shiva.presentacion.bean.dto;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoAccionEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoPerfilEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoTareaGestionaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.mail.Adjunto;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;

/**
 * DTO de tareas
 */
@JsonIgnoreProperties({"adjuntosMail"})
public class TareaDto extends DTO {

	private static final long serialVersionUID = 1L;
	
	private Long idTarea;
	private Integer idWorkflow;
	private TipoTareaEnum tipoTarea;
	private Date fechaCreacion;
	private String usuarioCreacion;
	private String perfilAsignacion;
	private String usuarioAsignacion;
	private Date fechaEjecucion;
	private String usuarioEjecucion;
	private String nombreUsuarioEjecucion;
	private TipoTareaGestionaEnum gestionaSobre;
	private TipoTareaEstadoEnum estado;
	
	private String nroCliente;
	private String razonSocial;
	private String importe;
	
	private Long idItem; //Puede ser un valor, talonario, valor por reversion o registro AVC
	private Long idValor;
	private Long idTalonario;
	private Long idRegistroAVC;
	private Long idOperacionMasiva;
	private Long idValorPorReversion;
	private Long idCobro;
	private Long idDescobro;
	
	private Long idOperacion;
	
	private String empresa;
	private String idEmpresa;
	private String segmento;
	
	//
	// INFORMACION ADICIONAL - CAMPOS SEPARADOS
	//
	private String nroBoleta;
	private String descBanco;
	private String nroCheque;
	private String vencimiento;
	private String codInterdeposito;
	private String referencia;
	
	private String refBandeja;
	
	private String tipo;
	private String nroConstancia;
	private String cuit;
	private String provincia;
	
	private String codOrganismo;
	
	private String rango;
	//
	// FIN INFORMACION ADICIONAL - CAMPOS SEPARADOS
	//
	
	private String informacionAdicional;
	
	//Extras
	private boolean enviarMail = true;
	private String asuntoMail;
	private String cuerpoMail = Utilidad.EMPTY_STRING;
	// Este atributo no se convertida en Json
	private List<Adjunto> adjuntosMail = null;

	private String idUsuarioAsignado;
	private String nombreUsuarioAsignado;
	private String mailUsuarioAsignado;
	private String chatUsuarioAsignado;
	
	private String idUsuarioCreacion;
	private String nombreUsuarioCreacion;
	private String mailUsuarioCreacion;
	private String chatUsuarioCreacion;
	
	private int delegado;
	private String marcaDescripcion = Utilidad.EMPTY_STRING;
	
	private String monedaImporte;

	private boolean permitirTomarTarea;
	private boolean permitirLiberarTarea;
	
	private SistemaEnum sistema;
	private SociedadEnum sociedad;
	
	//Se usan en la Bandeja de Entrada
	@SuppressWarnings("unused")
	private String nameTarea;
	@SuppressWarnings("unused")
	private String sociedadPantalla;
	@SuppressWarnings("unused")
	private String sistemaPantalla;
	
	
	private String observacion;
	private TipoAccionEnum tipoAccion;
	
	
	/**
	 * @return the nombreUsuarioEjecucion
	 */
	public String getNombreUsuarioEjecucion() {
		return nombreUsuarioEjecucion;
	}


	/**
	 * @param nombreUsuarioEjecucion the nombreUsuarioEjecucion to set
	 */
	public void setNombreUsuarioEjecucion(String nombreUsuarioEjecucion) {
		this.nombreUsuarioEjecucion = nombreUsuarioEjecucion;
	}


	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}


	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}


	/**
	 * @return the tipoAccion
	 */
	public TipoAccionEnum getTipoAccion() {
		return tipoAccion;
	}


	/**
	 * @param tipoAccion the tipoAccion to set
	 */
	public void setTipoAccion(TipoAccionEnum tipoAccion) {
		this.tipoAccion = tipoAccion;
	}


	public SociedadEnum getSociedad() {
		return sociedad;
	}


	public String getSociedadPantalla() {
		if(!Validaciones.isObjectNull(this.sociedad)) {
			return this.sociedad.name();
		} else {
			return "";
		}
	}


	public void setSociedadPantalla(String sociedadPantalla) {
		this.sociedadPantalla = sociedadPantalla;
	}



	public String getSistemaPantalla() {
		if(!Validaciones.isObjectNull(this.sistema)) {
			return this.sistema.name();
		} else {
			return "";
		}
	}


	public void setSistemaPantalla(String sistemaPantalla) {
		this.sistemaPantalla = sistemaPantalla;
	}



	public void setSociedad(SociedadEnum sociedad) {
		this.sociedad = sociedad;
	}



	public SistemaEnum getSistema() {
		return sistema;
	}



	public void setSistema(SistemaEnum sistema) {
		this.sistema = sistema;
	}



	public TareaDto() {}

	
	
	/**
	 * Se usa para los Correos de tareas de Reversiones
	 * @param idWorkflow
	 * @param tipoTarea
	 * @param fechaCreacion
	 * @param usuarioCreacion
	 * @param usuarioAsignacion
	 * @param gestionaSobre
	 * @param estado
	 * @param nroCliente
	 * @param razonSocial
	 * @param importe
	 * @param idItem
	 * @param idDescobro
	 * @param idOperacion
	 * @param enviarMail
	 * @param monedaImporte
	 */
	public TareaDto(Integer idWorkflow, TipoTareaEnum tipoTarea,
			Date fechaCreacion, String usuarioCreacion,
			String usuarioAsignacion, TipoTareaGestionaEnum gestionaSobre,
			TipoTareaEstadoEnum estado, String nroCliente, String razonSocial,
			String importe, Long idItem, Long idDescobro, Long idOperacion,
			boolean enviarMail, String monedaImporte) {
		super();
		this.idWorkflow = idWorkflow;
		this.tipoTarea = tipoTarea;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCreacion = usuarioCreacion;
		this.usuarioAsignacion = usuarioAsignacion;
		this.gestionaSobre = gestionaSobre;
		this.estado = estado;
		this.nroCliente = nroCliente;
		this.razonSocial = razonSocial;
		this.importe = importe;
		this.idItem = idItem;
		this.idDescobro = idDescobro;
		this.idOperacion = idOperacion;
		this.enviarMail = enviarMail;
		this.monedaImporte = monedaImporte;
	}




	public String getTipoTareaDescripcion() {
		
		if (!Validaciones.isObjectNull(this.sociedad) && (!Validaciones.isObjectNull(this.sistema))){
			return this.tipoTarea.descripcion() +" ("+ this.sociedad.getDescripcion() + "/" + this.sistema.getDescripcion() + ")";
		} else if (Validaciones.isObjectNull(this.sociedad) && (!Validaciones.isObjectNull(this.sistema))){
			return this.tipoTarea.descripcion() +" ("+ this.sistema.getDescripcion() +")";
		} else if (!Validaciones.isObjectNull(this.sociedad) && (Validaciones.isObjectNull(this.sistema))){
			return this.tipoTarea.descripcion() + " ("+ this.sociedad.getDescripcion() +")";
		}
		
		return this.tipoTarea.descripcion();
	}
	
	public String getFechaCreacionFormateado(){
		return Utilidad.formatDateAndTimeFull(this.fechaCreacion);
	}
	
	public String getGrupoAsignado(){
		return TipoPerfilEnum.getEnumByDescripcion(this.getPerfilAsignacion()).grupo();
	}
	
	public String getEstadoFormateado() {
		return this.estado.descripcion();
	}
	
	public String getGestionaSobreFormateado() {
		return this.gestionaSobre.descripcion();
	}
	
	public String getEstadoPendienteDescripcion() {
		if (Validaciones.isNullEmptyOrDash(this.getMarcaDescripcion())) {
			return this.tipoTarea.estadoPendienteDescripcion();
		}
		return this.tipoTarea.estadoPendienteDescripcion() + " - " + this.getMarcaDescripcion();
	}
	
	public TipoTareaGestionaEnum getTipoTareaGestionaPorIdTipoValor(Long idTipoValor) {
		switch (TipoValorEnum.getEnumByIdTipoValor(Long.valueOf(idTipoValor))) {
			case BOLETA_DEPOSITO_CHEQUE:
				return TipoTareaGestionaEnum.VALOR_BOLETA_DEP_CHQ;
			case BOLETA_DEPOSITO_CHEQUE_DIFERIDO:
				return TipoTareaGestionaEnum.VALOR_BOLETA_DEP_CHQ_DIF;
			case BOLETA_DEPOSITO_EFECTIVO:	
				return TipoTareaGestionaEnum.VALOR_BOLETA_DEP_EFE;
			case EFECTIVO:
				return TipoTareaGestionaEnum.VALOR_EFECTIVO;
			case CHEQUE:
				return TipoTareaGestionaEnum.VALOR_CHEQUE;
			case CHEQUE_DIFERIDO:
				return TipoTareaGestionaEnum.VALOR_CHEQUE_DIFERIDO;
			case TRANSFERENCIA:
				return TipoTareaGestionaEnum.VALOR_TRANSF;
			case INTERDEPÓSITO:
				return TipoTareaGestionaEnum.VALOR_INTERDEPOSITO;
			case RETENCIÓN_IMPUESTO:
				return TipoTareaGestionaEnum.VALOR_RETENCION_IMPUESTO;
			default:
				return null;
		}
	}
	
	public boolean getEsTareaDeSupervisorTalonarios() {
		if (TipoTareaEnum.ASIG_GESTOR_TAL.equals(this.tipoTarea)
				|| TipoTareaEnum.REV_REND_TAL.equals(this.tipoTarea)) {
			return true;
		}
		return false;
	}
	
	public boolean getEsTareaDeAdminTalonarios() {
		if (TipoTareaEnum.AUTR_REND_TAL.equals(this.tipoTarea)
				|| TipoTareaEnum.REV_TAL.equals(this.tipoTarea)) {
			return true;
		}
		return false;
	}
	
	public boolean getEsTareaDeAdminValores() {
		if (TipoTareaEnum.CONF_AVISO_PAGO.equals(this.tipoTarea) 
				|| TipoTareaEnum.CONF_ALTA_V_AVC.equals(this.tipoTarea)
				|| TipoTareaEnum.CONF_ALTA_V_REV.equals(this.tipoTarea)
				|| TipoTareaEnum.REV_ANUL_R_AVC.equals(this.tipoTarea)) {
			return true;
		}
		return false;
	}
	
	public boolean getEsTareaDeSupervisor() {
		if (TipoTareaEnum.AUTR_VALOR.equals(this.tipoTarea)) {
			return true;
		}
		return false;
	}
	
	public boolean getEsReferenteCobranza() {
		if (TipoTareaEnum.COB_AUTR_COB.equals(this.tipoTarea) ||
				TipoTareaEnum.APROBAR_OP_MAS.equals(this.tipoTarea)) {
			return true;
			
			
		}
		return false;
	}
	
	public boolean getEsAnalistaCobranza() {
		if (TipoTareaEnum.COB_AUTR_COB.equals(this.tipoTarea)) {
			return true;
		}
		return false;
	}
	
	public boolean getEsSupervisorOperacionesEspeciales() {
		if (TipoTareaEnum.COB_AUTR_COB_SUP_OP_ESP.equals(this.tipoTarea)) {
			return true;
		}
		return false;
	}
	
	public boolean getEsAnalistaOperacionMasiva() {
		if (TipoTareaEnum.APROBAR_OP_MAS.equals(this.tipoTarea)) {
			return true;
		}
		return false;
	}
	
	public boolean getEsTareaDeAnalista() {
		if (TipoTareaEnum.REV_ALTA_V_AVC.equals(this.tipoTarea)
				|| TipoTareaEnum.REV_AVISO_PAGO.equals(this.tipoTarea)
				|| TipoTareaEnum.REV_VALOR.equals(this.tipoTarea)
				|| TipoTareaEnum.COB_REV_COB_CON.equals(this.tipoTarea)
				|| TipoTareaEnum.COB_REV_COB_DES.equals(this.tipoTarea)
				|| TipoTareaEnum.COB_REV_COB_ERR.equals(this.tipoTarea)
				|| TipoTareaEnum.COB_REV_COB_APR.equals(this.tipoTarea)
				|| TipoTareaEnum.COB_ERR_CONF_APLIC_MANUAL.equals(this.tipoTarea)
				|| TipoTareaEnum.REV_ALTA_V_REV.equals(this.tipoTarea)
				|| TipoTareaEnum.DES_RES_SIM_ERR.equals(this.tipoTarea)
				|| TipoTareaEnum.DES_RES_SIM_OK.equals(this.tipoTarea)
				|| TipoTareaEnum.DES_IMP_1ER_ERR.equals(this.tipoTarea)
				|| TipoTareaEnum.DES_IMP_ERR.equals(this.tipoTarea)
				|| TipoTareaEnum.OP_MAS_ERROR.equals(this.tipoTarea)
				|| TipoTareaEnum.OP_MAS_PARCIAL.equals(this.tipoTarea)) {
			return true;
		}
		return false;	
	}
	
	public boolean getSePuedeEliminar() {
		if (TipoTareaEnum.REV_ALTA_V_AVC.equals(this.tipoTarea)
				|| TipoTareaEnum.REV_AVISO_PAGO.equals(this.tipoTarea)
				|| TipoTareaEnum.REV_VALOR.equals(this.tipoTarea)
				|| TipoTareaEnum.REV_TAL.equals(this.tipoTarea)
				|| TipoTareaEnum.REV_ANUL_R_AVC.equals(this.tipoTarea)
				/**@author u573005, Sprint 3, defecto 90
				 * Se agrega esta condicion para que se permita borrar la tarea de revision
				 * de valor pre-shiva
				 */
				|| TipoTareaEnum.REV_ALTA_V_REV.equals(this.tipoTarea)
				|| TipoTareaEnum.COB_REV_RECH.equals(this.tipoTarea)
				|| TipoTareaEnum.REV_OP_MAS_RECH.equals(this.tipoTarea)
				|| TipoTareaEnum.COB_REV_COB_CON.equals(this.tipoTarea)
				|| TipoTareaEnum.COB_REV_COB_ERR.equals(this.tipoTarea)
				|| TipoTareaEnum.COB_REV_COB_APR.equals(this.tipoTarea)
				|| TipoTareaEnum.COB_ERR_CONF_APLIC_MANUAL.equals(this.tipoTarea)
				|| TipoTareaEnum.COB_REV_ERR_APLIC_MANUAL.equals(this.tipoTarea)
				|| TipoTareaEnum.COB_AUTR_COB.equals(this.tipoTarea)
				|| TipoTareaEnum.DES_RES_SIM_ERR.equals(this.tipoTarea)
				|| TipoTareaEnum.DES_IMP_1ER_ERR.equals(this.tipoTarea)
				|| TipoTareaEnum.OP_MAS_ERROR.equals(this.tipoTarea)
				|| TipoTareaEnum.OP_MAS_PARCIAL.equals(this.tipoTarea)
				|| TipoTareaEnum.DES_RES_SIM_OK.equals(this.tipoTarea)) {
			return true;
		}
		return false;
	}
	public boolean getSePuedeVerDetalle() {
		if (TipoTareaEnum.COB_REV_COB_DES.equals(this.tipoTarea) || TipoTareaEnum.DES_IMP_ERR.equals(this.tipoTarea) || TipoTareaEnum.OP_MAS_ERROR.equals(this.tipoTarea) || TipoTareaEnum.OP_MAS_PARCIAL.equals(this.tipoTarea) || TipoTareaEnum.DES_COMP_PEND.equals(this.tipoTarea) || TipoTareaEnum.COB_REV_COB_ERR.equals(this.tipoTarea) || TipoTareaEnum.COB_REV_COB_APR.equals(this.tipoTarea) || TipoTareaEnum.COB_ERR_CONF_APLIC_MANUAL.equals(this.tipoTarea) || TipoTareaEnum.COB_REV_ERR_APLIC_MANUAL.equals(this.tipoTarea)) {
			return true;
		}
		return false;
	}
	
	public boolean getEsTareaDeSupervisorAdminValor() {
		return TipoTareaEnum.CONF_ANUL_R_AVC.equals(this.tipoTarea);
	}
	
	public boolean getEsTareaDesapropiacionCobro() {
		if (TipoTareaEnum.COB_REV_COB_DES.equals(this.tipoTarea)) {
			return true;
		}
		return false;
	}
	
	public boolean getEsTareaErrorConfirmacionCobro() {
		if (TipoTareaEnum.COB_REV_COB_CON.equals(this.tipoTarea)) {
			return true;
		}
		return false;
	}
	
	public boolean getEsTareaErrorPrimerDescobro() {
		if (!TipoTareaEnum.DES_IMP_1ER_ERR.equals(this.tipoTarea)) {
			return false;
		}
		return true;
	}
	
	public boolean getEsTareaDescobroConfirmarAplicacionManual() {
		if (
			TipoTareaEnum.DES_CONFIRMA_APL_MAN.equals(this.tipoTarea)
		) {
			return true;
		}
		return false;
	}
	
	public boolean getEsTareaCobroConfirmarAplicacionManual() {

		if (TipoTareaEnum.COB_CONF_APLIC_MANUAL.equals(this.tipoTarea)) {
			return true;
		}
		return false;
	}
	
	public boolean getEsTareaCobroConfirmarAplicacionManualRechazada() {
		if (
			TipoTareaEnum.COB_ERR_CONF_APLIC_MANUAL.equals(this.tipoTarea)) {
			return true;
		}
		return false;
	}

	public boolean getEsTareaDesapropiacionAplicacionManual() {
		if (TipoTareaEnum.COB_DESAPRO_APLI_MANUAL.equals(this.tipoTarea)) {
			return true;
		}
		return false;
	}
	/**
	 * @return the titleTarea
	 */
	public String getTitleTarea() {
		
		if (TipoTareaEnum.COB_REV_COB_DES.equals(this.tipoTarea)) {
			return Propiedades.MENSAJES_PROPIEDADES.getString("desapropiacionDetalleTarea.mensaje");
		}
		return this.getTipoTareaDescripcion();
	}
	
	
	
	/*********************************************************************************************
	 * Getters & Setters
	 *********************************************************************************************/
	public Integer getIdWorkflow() {
		return idWorkflow;
	}
	public void setIdWorkflow(Integer idWorkflow) {
		this.idWorkflow = idWorkflow;
	}
	
	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}
	
	/**
	 * @return the titleTarea
	 */
	public String getTitleTareaVerDetalle() {
		
		String tipoTarea = "";
		
		if (TipoTareaEnum.COB_REV_COB_DES.equals(this.tipoTarea)) {
			tipoTarea = Propiedades.MENSAJES_PROPIEDADES.getString("tarea.ver.detalle.cobro.mensaje");
		}else if(TipoTareaEnum.OP_MAS_ERROR.equals(this.tipoTarea) || TipoTareaEnum.OP_MAS_PARCIAL.equals(this.tipoTarea)){
			tipoTarea = Propiedades.MENSAJES_PROPIEDADES.getString("tarea.ver.detalle.operacionMasiva.mensaje");
		}else if(TipoTareaEnum.DES_IMP_ERR.equals(this.tipoTarea)){
			tipoTarea = Propiedades.MENSAJES_PROPIEDADES.getString("tarea.ver.detalle.descobro.mensaje");
		}
		
		return tipoTarea;
	}

	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}
	public String getUsuarioEjecucion() {
		return usuarioEjecucion;
	}
	public void setUsuarioEjecucion(String usuarioEjecucion) {
		this.usuarioEjecucion = usuarioEjecucion;
	}
	public TipoTareaGestionaEnum getGestionaSobre() {
		return gestionaSobre;
	}
	public void setGestionaSobre(TipoTareaGestionaEnum gestionaSobre) {
		this.gestionaSobre = gestionaSobre;
	}
	public TipoTareaEstadoEnum getEstado() {
		return estado;
	}
	public void setEstado(TipoTareaEstadoEnum estado) {
		this.estado = estado;
	}
	public Long getIdValor() {
		return idValor;
	}
	public void setIdValor(Long idValor) {
		this.idValor = idValor;
	}
	public Long getIdTalonario() {
		return idTalonario;
	}
	public void setIdTalonario(Long idTalonario) {
		this.idTalonario = idTalonario;
	}
	public Long getIdRegistroAVC() {
		return idRegistroAVC;
	}
	public void setIdRegistroAVC(Long idRegistroAVC) {
		this.idRegistroAVC = idRegistroAVC;
	}
	public TipoTareaEnum getTipoTarea() {
		return tipoTarea;
	}
	public void setTipoTarea(TipoTareaEnum tipoTarea) {
		this.tipoTarea = tipoTarea;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public String getPerfilAsignacion() {
		return perfilAsignacion;
	}
	public void setPerfilAsignacion(String perfilAsignacion) {
		this.perfilAsignacion = perfilAsignacion;
	}
	public String getAsuntoMail() {
		return asuntoMail;
	}
	public void setAsuntoMail(String asuntoMail) {
		this.asuntoMail = asuntoMail;
	}
	public String getCuerpoMail() {
		return cuerpoMail;
	}
	public void setCuerpoMail(String cuerpoMail) {
		this.cuerpoMail = cuerpoMail;
	}

	public String getIdUsuarioAsignado() {
		return idUsuarioAsignado;
	}

	public void setIdUsuarioAsignado(String idUsuarioAsignado) {
		this.idUsuarioAsignado = idUsuarioAsignado;
	}

	public String getNombreUsuarioAsignado() {
		return nombreUsuarioAsignado;
	}

	public void setNombreUsuarioAsignado(String nombreUsuarioAsignado) {
		this.nombreUsuarioAsignado = nombreUsuarioAsignado;
	}

	public String getMailUsuarioAsignado() {
		return mailUsuarioAsignado;
	}

	public void setMailUsuarioAsignado(String mailUsuarioAsignado) {
		this.mailUsuarioAsignado = mailUsuarioAsignado;
	}

	public String getChatUsuarioAsignado() {
		return chatUsuarioAsignado;
	}

	public void setChatUsuarioAsignado(String chatUsuarioAsignado) {
		this.chatUsuarioAsignado = chatUsuarioAsignado;
	}

	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
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

	public Long getIdOperacionMasiva() {
		return idOperacionMasiva;
	}

	public void setIdOperacionMasiva(Long idOperacionMasiva) {
		this.idOperacionMasiva = idOperacionMasiva;
	}

	public Long getIdValorPorReversion() {
		return idValorPorReversion;
	}

	public void setIdValorPorReversion(Long idValorPorReversion) {
		this.idValorPorReversion = idValorPorReversion;
	}

	public String getUsuarioAsignacion() {
		return usuarioAsignacion;
	}

	public void setUsuarioAsignacion(String usuarioAsignacion) {
		this.usuarioAsignacion = usuarioAsignacion;
	}

	public String getNroCliente() {
		return nroCliente;
	}

	public void setNroCliente(String nroCliente) {
		this.nroCliente = nroCliente;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public boolean getEnviarMail() {
		return enviarMail;
	}

	public void setEnviarMail(boolean enviarMail) {
		this.enviarMail = enviarMail;
	}

	public String getNroBoleta() {
		return nroBoleta;
	}

	public void setNroBoleta(String nroBoleta) {
		this.nroBoleta = nroBoleta;
	}

	public String getDescBanco() {
		return descBanco;
	}

	public void setDescBanco(String descBanco) {
		this.descBanco = descBanco;
	}

	public String getNroCheque() {
		return nroCheque;
	}

	public void setNroCheque(String nroCheque) {
		this.nroCheque = nroCheque;
	}

	public String getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}

	public String getCodInterdeposito() {
		return codInterdeposito;
	}

	public void setCodInterdeposito(String codInterdeposito) {
		this.codInterdeposito = codInterdeposito;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
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

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCodOrganismo() {
		return codOrganismo;
	}

	public void setCodOrganismo(String codOrganismo) {
		this.codOrganismo = codOrganismo;
	}

	public String getRango() {
		return rango;
	}

	public void setRango(String rango) {
		this.rango = rango;
	}

	public int getDelegado() {
		return delegado;
	}

	public void setDelegado(int delegado) {
		this.delegado = delegado;
	}

	public String getRefBandeja() {
		return refBandeja;
	}

	public void setRefBandeja(String refBandeja) {
		this.refBandeja = refBandeja;
	}

	public Long getIdCobro() {
		return idCobro;
	}

	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}

	public Long getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}

	/**
	 * @return the marcaDescripcion
	 */
	public String getMarcaDescripcion() {
		return marcaDescripcion;
	}

	/**
	 * @param marcaDescripcion the marcaDescripcion to set
	 */
	public void setMarcaDescripcion(String marcaDescripcion) {
		this.marcaDescripcion = marcaDescripcion;
	}
	
	public Long getIdDescobro() {
		return idDescobro;
	}

	public void setIdDescobro(Long idDescobro) {
		this.idDescobro = idDescobro;
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

	
	public String getInformacionAdicional() {
		return informacionAdicional;
	}

	public void setInformacionAdicional(String informacionAdicional) {
		this.informacionAdicional = informacionAdicional;
	}

	public String getMonedaImporte() {
		return monedaImporte;
	}

	public void setMonedaImporte(String monedaImporte) {
		this.monedaImporte = monedaImporte;
	}



	/**
	 * No se convertira en JSON
	 * @return the adjuntosMail
	 */
	public List<Adjunto> getAdjuntosMail() {
		return adjuntosMail;
	}



	/**
	 * @param adjuntosMail the adjuntosMail to set
	 */
	public void setAdjuntosMail(List<Adjunto> adjuntosMail) {
		this.adjuntosMail = adjuntosMail;
	}



	/**
	 * @return the idUsuarioCreacion
	 */
	public String getIdUsuarioCreacion() {
		return idUsuarioCreacion;
	}



	/**
	 * @param idUsuarioCreacion the idUsuarioCreacion to set
	 */
	public void setIdUsuarioCreacion(String idUsuarioCreacion) {
		this.idUsuarioCreacion = idUsuarioCreacion;
	}



	/**
	 * @return the nombreUsuarioCreacion
	 */
	public String getNombreUsuarioCreacion() {
		return nombreUsuarioCreacion;
	}



	/**
	 * @param nombreUsuarioCreacion the nombreUsuarioCreacion to set
	 */
	public void setNombreUsuarioCreacion(String nombreUsuarioCreacion) {
		this.nombreUsuarioCreacion = nombreUsuarioCreacion;
	}



	/**
	 * @return the mailUsuarioCreacion
	 */
	public String getMailUsuarioCreacion() {
		return mailUsuarioCreacion;
	}



	/**
	 * @param mailUsuarioCreacion the mailUsuarioCreacion to set
	 */
	public void setMailUsuarioCreacion(String mailUsuarioCreacion) {
		this.mailUsuarioCreacion = mailUsuarioCreacion;
	}



	/**
	 * @return the chatUsuarioCreacion
	 */
	public String getChatUsuarioCreacion() {
		return chatUsuarioCreacion;
	}



	/**
	 * @param chatUsuarioCreacion the chatUsuarioCreacion to set
	 */
	public void setChatUsuarioCreacion(String chatUsuarioCreacion) {
		this.chatUsuarioCreacion = chatUsuarioCreacion;
	}
	
	/**
	 * @return the permitirTomarTarea
	 */
	public boolean isPermitirTomarTarea() {
		return permitirTomarTarea;
	}

	/**
	 * @param permitirTomarTarea the permitirTomarTarea to set
	 */
	public void setPermitirTomarTarea(boolean permitirTomarTarea) {
		this.permitirTomarTarea = permitirTomarTarea;
	}

	/**
	 * @return the permitirLiberarTarea
	 */
	public boolean isPermitirLiberarTarea() {
		return permitirLiberarTarea;
	}

	/**
	 * @param permitirLiberarTarea the permitirLiberarTarea to set
	 */
	public void setPermitirLiberarTarea(boolean permitirLiberarTarea) {
		this.permitirLiberarTarea = permitirLiberarTarea;
	}



	public String getNameTarea() {
		if(!Validaciones.isObjectNull(this.tipoTarea)) {
			return this.tipoTarea.name();
		} else {
			return "";
		}
	}


	public void setNameTarea(String nameTarea) {
		this.nameTarea = nameTarea;
	}


	/**
	 * @return the idTarea
	 */
	public Long getIdTarea() {
		return idTarea;
	}


	/**
	 * @param idTarea the idTarea to set
	 */
	public void setIdTarea(Long idTarea) {
		this.idTarea = idTarea;
	}
	
}