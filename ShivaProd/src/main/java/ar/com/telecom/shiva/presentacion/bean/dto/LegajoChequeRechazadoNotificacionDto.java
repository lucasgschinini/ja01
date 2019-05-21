package ar.com.telecom.shiva.presentacion.bean.dto;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EstadoNotificacionEnum;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
/**
 * @author u578936 M.A.Uehara
 *
 */
public class LegajoChequeRechazadoNotificacionDto extends DTO {
	private static final long serialVersionUID = 20170804;

	private Long idNotificacion;
	private long idLegajoChequeRechazado;

	private int tipoNotificacion;
	private String tipoNotificacionDescripicion;

	private String datosReceptor;

	private String fechaContacto;

	private int tipoContacto;
	private String tipoContactoDescripcion;

	private String datosContacto;

	private int acuseRecibo;
	private String acuseReciboDescripcion;

	private String fechaRecepcion;

	private String fechaCreacion;
//
//	private Date fechaModificacion;

	private String observaciones;
	private String usuarioAlta;
	private String usuarioDesc;

	private EstadoNotificacionEnum estado;
	private String estadoDescripion;

	private LegajoChequeRechazadoNotificacionCartaDto carta;
	
	private String mailAnalista;
	private String urlFotoAnalista;

	//Datos adicionales para la grilla
	private String usuarioDescripcion;

	
	// Solo de consulta para Guarda!!
	private Estado estadoLegajo;
	private int idWorkFlowLegajo;

	// Solo para la carta
	private String segmentoLegajo;

	

	public LegajoChequeRechazadoNotificacionDto() {}

	/**
	 * @return the idNotificacion
	 */
	public Long getIdNotificacion() {
		return idNotificacion;
	}

	/**
	 * @param idNotificacion the idNotificacion to set
	 */
	public void setIdNotificacion(Long idNotificacion) {
		this.idNotificacion = idNotificacion;
	}

	/**
	 * @return the idLegajoChequeRechazado
	 */
	public long getIdLegajoChequeRechazado() {
		return idLegajoChequeRechazado;
	}

	/**
	 * @param idLegajoChequeRechazado the idLegajoChequeRechazado to set
	 */
	public void setIdLegajoChequeRechazado(long idLegajoChequeRechazado) {
		this.idLegajoChequeRechazado = idLegajoChequeRechazado;
	}

	/**
	 * @return the tipoNotificacion
	 */
	public int getTipoNotificacion() {
		return tipoNotificacion;
	}

	/**
	 * @param tipoNotificacion the tipoNotificacion to set
	 */
	public void setTipoNotificacion(int tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
	}

	/**
	 * @return the tipoNotificacionDescripicion
	 */
	public String getTipoNotificacionDescripicion() {
		return tipoNotificacionDescripicion;
	}

	/**
	 * @param tipoNotificacionDescripicion the tipoNotificacionDescripicion to set
	 */
	public void setTipoNotificacionDescripicion(String tipoNotificacionDescripicion) {
		this.tipoNotificacionDescripicion = tipoNotificacionDescripicion;
	}

	/**
	 * @return the datosReceptor
	 */
	public String getDatosReceptor() {
		return datosReceptor;
	}

	/**
	 * @param datosReceptor the datosReceptor to set
	 */
	public void setDatosReceptor(String datosReceptor) {
		this.datosReceptor = datosReceptor;
	}

	/**
	 * @return the fechaContacto
	 */
	public String getFechaContacto() {
		return fechaContacto;
	}

	/**
	 * @param fechaContacto the fechaContacto to set
	 */
	public void setFechaContacto(String fechaContacto) {
		this.fechaContacto = fechaContacto;
	}

	/**
	 * @return the tipoContacto
	 */
	public int getTipoContacto() {
		return tipoContacto;
	}

	/**
	 * @param tipoContacto the tipoContacto to set
	 */
	public void setTipoContacto(int tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	/**
	 * @return the tipoContactoDescripcion
	 */
	public String getTipoContactoDescripcion() {
		return tipoContactoDescripcion;
	}

	/**
	 * @param tipoContactoDescripcion the tipoContactoDescripcion to set
	 */
	public void setTipoContactoDescripcion(String tipoContactoDescripcion) {
		this.tipoContactoDescripcion = tipoContactoDescripcion;
	}

	/**
	 * @return the datosContacto
	 */
	public String getDatosContacto() {
		return datosContacto;
	}

	/**
	 * @param datosContacto the datosContacto to set
	 */
	public void setDatosContacto(String datosContacto) {
		this.datosContacto = datosContacto;
	}

	/**
	 * @return the acuseRecibo
	 */
	public int getAcuseRecibo() {
		return acuseRecibo;
	}

	/**
	 * @param acuseRecibo the acuseRecibo to set
	 */
	public void setAcuseRecibo(int acuseRecibo) {
		this.acuseRecibo = acuseRecibo;
	}

	/**
	 * @return the acuseReciboDescripcion
	 */
	public String getAcuseReciboDescripcion() {
		return acuseReciboDescripcion;
	}

	/**
	 * @param acuseReciboDescripcion the acuseReciboDescripcion to set
	 */
	public void setAcuseReciboDescripcion(String acuseReciboDescripcion) {
		this.acuseReciboDescripcion = acuseReciboDescripcion;
	}

	/**
	 * @return the fechaRecepcion
	 */
	public String getFechaRecepcion() {
		return fechaRecepcion;
	}

	/**
	 * @param fechaRecepcion the fechaRecepcion to set
	 */
	public void setFechaRecepcion(String fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
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
	 * @return the usuarioAlta
	 */
	public String getUsuarioAlta() {
		return usuarioAlta;
	}

	/**
	 * @param usuarioAlta the usuarioAlta to set
	 */
	public void setUsuarioAlta(String usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

	/**
	 * @return the estado
	 */
	public EstadoNotificacionEnum getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoNotificacionEnum estado) {
		this.estado = estado;
	}

	/**
	 * @return the estadoDescripion
	 */
	public String getEstadoDescripion() {
		return estadoDescripion;
	}

	/**
	 * @param estadoDescripion the estadoDescripion to set
	 */
	public void setEstadoDescripion(String estadoDescripion) {
		this.estadoDescripion = estadoDescripion;
	}

	/**
	 * @return the usuarioDescripcion
	 */
	public String getUsuarioDescripcion() {
		return usuarioDescripcion;
	}

	/**
	 * @param usuarioDescripcion the usuarioDescripcion to set
	 */
	public void setUsuarioDescripcion(String usuarioDescripcion) {
		this.usuarioDescripcion = usuarioDescripcion;
	}

	/**
	 * @return the carta
	 */
	public LegajoChequeRechazadoNotificacionCartaDto getCarta() {
		return carta;
	}

	/**
	 * @param carta the carta to set
	 */
	public void setCarta(LegajoChequeRechazadoNotificacionCartaDto carta) {
		this.carta = carta;
	}

	/**
	 * @return the usuarioDesc
	 */
	public String getUsuarioDesc() {
		return usuarioDesc;
	}

	/**
	 * @param usuarioDesc the usuarioDesc to set
	 */
	public void setUsuarioDesc(String usuarioDesc) {
		this.usuarioDesc = usuarioDesc;
	}

	/**
	 * @return the fechaCreacion
	 */
	public String getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the estadoLEgajo
	 */
	public Estado getEstadoLegajo() {
		return estadoLegajo;
	}

	/**
	 * @param estadoLEgajo the estadoLEgajo to set
	 */
	public void setEstadoLegajo(Estado estadoLEgajo) {
		this.estadoLegajo = estadoLEgajo;
	}

	/**
	 * @return the idWorkFlowLegajo
	 */
	public int getIdWorkFlowLegajo() {
		return idWorkFlowLegajo;
	}

	/**
	 * @param idWorkFlowLegajo the idWorkFlowLegajo to set
	 */
	public void setIdWorkFlowLegajo(int idWorkFlowLegajo) {
		this.idWorkFlowLegajo = idWorkFlowLegajo;
	}

	/**
	 * @return the mailAnalista
	 */
	public String getMailAnalista() {
		return mailAnalista;
	}

	/**
	 * @param mailAnalista the mailAnalista to set
	 */
	public void setMailAnalista(String mailAnalista) {
		this.mailAnalista = mailAnalista;
	}

	/**
	 * @return the urlFotoAnalista
	 */
	public String getUrlFotoAnalista() {
		return urlFotoAnalista;
	}

	/**
	 * @param urlFotoAnalista the urlFotoAnalista to set
	 */
	public void setUrlFotoAnalista(String urlFotoAnalista) {
		this.urlFotoAnalista = urlFotoAnalista;
	}

	/**
	 * @return the segmentoLegajo
	 */
	public String getSegmentoLegajo() {
		return segmentoLegajo;
	}

	/**
	 * @param segmentoLegajo the segmentoLegajo to set
	 */
	public void setSegmentoLegajo(String segmentoLegajo) {
		this.segmentoLegajo = segmentoLegajo;
	}

	
}
