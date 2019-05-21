package ar.com.telecom.shiva.presentacion.bean.filtro;

public class VistaSoporteOperacionMasivaFiltro extends Filtro {


	private static final long serialVersionUID = 1L;
	
	
	protected String estado;
	protected String motivo;
	protected String analista;
	protected String idCopropietario;
	protected String tipoOperacionMasiva;
	protected String idOperacionMasiva;
	protected String descripcionComprobante;
	protected String observaciones;
	protected String idMotivoEdicion;
	protected String idCopropietarioEdicion;
	
	public VistaSoporteOperacionMasivaFiltro(){}


	public String getidEstado() {
		return estado;
	}


	public void setidEstado(String estado) {
		this.estado = estado;
	}


	public String getidMotivo() {
		return motivo;
	}


	public void setidMotivo(String motivo) {
		this.motivo = motivo;
	}


	public String getidAnalista() {
		return analista;
	}


	public void setidAnalista(String analista) {
		this.analista = analista;
	}


	public String getidTipoOperacionMasiva() {
		return tipoOperacionMasiva;
	}


	public void setidTipoOperacionMasiva(String tipoOperacionMasiva) {
		this.tipoOperacionMasiva = tipoOperacionMasiva;
	}


	public String getIdOperacionMasiva() {
		return idOperacionMasiva;
	}


	public void setIdOperacionMasiva(String idOperacionMasiva) {
		this.idOperacionMasiva = idOperacionMasiva;
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
	 * @return the descripcionComprobante
	 */
	public String getDescripcionComprobante() {
		return descripcionComprobante;
	}


	/**
	 * @param descripcionComprobante the descripcionComprobante to set
	 */
	public void setDescripcionComprobante(String descripcionComprobante) {
		this.descripcionComprobante = descripcionComprobante;
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


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getMotivo() {
		return motivo;
	}


	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}


	public String getAnalista() {
		return analista;
	}


	public void setAnalista(String analista) {
		this.analista = analista;
	}


	public String getTipoOperacionMasiva() {
		return tipoOperacionMasiva;
	}


	public void setTipoOperacionMasiva(String tipoOperacionMasiva) {
		this.tipoOperacionMasiva = tipoOperacionMasiva;
	}


	public String getIdMotivoEdicion() {
		return idMotivoEdicion;
	}


	public void setIdMotivoEdicion(String idMotivoEdicion) {
		this.idMotivoEdicion = idMotivoEdicion;
	}


	public String getIdCopropietarioEdicion() {
		return idCopropietarioEdicion;
	}


	public void setIdCopropietarioEdicion(String idCopropietarioEdicion) {
		this.idCopropietarioEdicion = idCopropietarioEdicion;
	}

}

