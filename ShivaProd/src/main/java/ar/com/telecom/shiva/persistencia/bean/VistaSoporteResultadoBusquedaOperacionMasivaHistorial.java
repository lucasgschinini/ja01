package ar.com.telecom.shiva.persistencia.bean;

public class VistaSoporteResultadoBusquedaOperacionMasivaHistorial extends VistaSoporteResultadoBusqueda{
	
	private Long idOperacionMasiva;
	private String estado;
	private String idUsuarioModificacion;
	private String nombreCompletoUsuarioMod;
	private String mailUsuarioModificacion;
	private String fechaModificacion;	//fecha Actualizacion
	private String observaciones;
	
	
	
	
	/**
	 * @return the idOperacionMasiva
	 */
	public Long getIdOperacionMasiva() {
		return idOperacionMasiva;
	}
	/**
	 * @param idOperacionMasiva the idOperacionMasiva to set
	 */
	public void setIdOperacionMasiva(Long idOperacionMasiva) {
		this.idOperacionMasiva = idOperacionMasiva;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
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
}
