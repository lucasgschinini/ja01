package ar.com.telecom.shiva.presentacion.bean.dto;

import ar.com.telecom.shiva.base.dto.DTO;

public class CobroHistoricoDto extends DTO{
	
	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idOperacion;
	private String idOperacionFormateado;
	private String estadoModificacion;
	private String marcaModificacion;
	private String idUsuarioModificacion;
	private String nombreCompletoUsuarioMod;
	private String mailUsuarioModificacion;
	private String fechaModificacion;	//fecha Actualizacion
	private String observaciones;
	private String mensajeError;
	private String numeroTransaccion;
	private String numeroTransaccionFicticio;
	private String numeroDocumentoDebito;
	private String importe;
	private String fechaCobro;
	private String refMedioPago;
	private String acuerdoTrasladoCargo;

	
	/**
	 * @return the idOperacion
	 */
	public Long getIdOperacion() {
		return idOperacion;
	}
	/**
	 * @param idOperacion the idOperacion to set
	 */
	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}
	/**
	 * @return the idOperacionFormateado
	 */
	public String getIdOperacionFormateado() {
		return idOperacionFormateado;
	}
	/**
	 * @param idOperacionFormateado the idOperacionFormateado to set
	 */
	public void setIdOperacionFormateado(String idOperacionFormateado) {
		this.idOperacionFormateado = idOperacionFormateado;
	}
	/**
	 * @return the estadoModificacion
	 */
	public String getEstadoModificacion() {
		return estadoModificacion;
	}
	/**
	 * @param estadoModificacion the estadoModificacion to set
	 */
	public void setEstadoModificacion(String estadoModificacion) {
		this.estadoModificacion = estadoModificacion;
	}
	/**
	 * @return the marcaModificacion
	 */
	public String getMarcaModificacion() {
		return marcaModificacion;
	}
	/**
	 * @param marcaModificacion the marcaModificacion to set
	 */
	public void setMarcaModificacion(String marcaModificacion) {
		this.marcaModificacion = marcaModificacion;
	}
	/**
	 * @return the idUsuarioModificacion
	 */
	public String getIdUsuarioModificacion() {
		return idUsuarioModificacion;
	}
	/**
	 * @param idUsuarioModificacion the idUsuarioModificacion to set
	 */
	public void setIdUsuarioModificacion(String idUsuarioModificacion) {
		this.idUsuarioModificacion = idUsuarioModificacion;
	}
	/**
	 * @return the nombreCompletoUsuarioMod
	 */
	public String getNombreCompletoUsuarioMod() {
		return nombreCompletoUsuarioMod;
	}
	/**
	 * @param nombreCompletoUsuarioMod the nombreCompletoUsuarioMod to set
	 */
	public void setNombreCompletoUsuarioMod(String nombreCompletoUsuarioMod) {
		this.nombreCompletoUsuarioMod = nombreCompletoUsuarioMod;
	}
	/**
	 * @return the mailUsuarioModificacion
	 */
	public String getMailUsuarioModificacion() {
		return mailUsuarioModificacion;
	}
	/**
	 * @param mailUsuarioModificacion the mailUsuarioModificacion to set
	 */
	public void setMailUsuarioModificacion(String mailUsuarioModificacion) {
		this.mailUsuarioModificacion = mailUsuarioModificacion;
	}
	/**
	 * @return the fechaModificacion
	 */
	public String getFechaModificacion() {
		return fechaModificacion;
	}
	public String getFechaModificacionFormateada() {
		return fechaModificacion.substring(0, 19);
	}
	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
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
	 * @return the mensajeError
	 */
	public String getMensajeError() {
		return mensajeError;
	}
	/**
	 * @param mensajeError the mensajeError to set
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	/**
	 * @return the numeroTransaccion
	 */
	public String getNumeroTransaccion() {
		return numeroTransaccion;
	}
	/**
	 * @param numeroTransaccion the numeroTransaccion to set
	 */
	public void setNumeroTransaccion(String numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}
	/**
	 * @return the numeroDocumentoDebito
	 */
	public String getNumeroDocumentoDebito() {
		return numeroDocumentoDebito;
	}
	/**
	 * @param numeroDocumentoDebito the numeroDocumentoDebito to set
	 */
	public void setNumeroDocumentoDebito(String numeroDocumentoDebito) {
		this.numeroDocumentoDebito = numeroDocumentoDebito;
	}
	/**
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}
	/**
	 * @param importe the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}
	/**
	 * @return the fechaCobro
	 */
	public String getFechaCobro() {
		return fechaCobro;
	}
	/**
	 * @param fechaCobro the fechaCobro to set
	 */
	public void setFechaCobro(String fechaCobro) {
		this.fechaCobro = fechaCobro;
	}
	/**
	 * @return the refMedioPago
	 */
	public String getRefMedioPago() {
		return refMedioPago;
	}
	/**
	 * @param refMedioPago the refMedioPago to set
	 */
	public void setRefMedioPago(String refMedioPago) {
		this.refMedioPago = refMedioPago;
	}
	/**
	 * @return the acuerdoTrasladoCargo
	 */
	public String getAcuerdoTrasladoCargo() {
		return acuerdoTrasladoCargo;
	}
	/**
	 * @param acuerdoTrasladoCargo the acuerdoTrasladoCargo to set
	 */
	public void setAcuerdoTrasladoCargo(String acuerdoTrasladoCargo) {
		this.acuerdoTrasladoCargo = acuerdoTrasladoCargo;
	}
	/**
	 * @return the numeroTransaccionFicticio
	 */
	public String getNumeroTransaccionFicticio() {
		return numeroTransaccionFicticio;
	}
	/**
	 * @param numeroTransaccionFicticio the numeroTransaccionFicticio to set
	 */
	public void setNumeroTransaccionFicticio(String numeroTransaccionFicticio) {
		this.numeroTransaccionFicticio = numeroTransaccionFicticio;
	}
	
	
}