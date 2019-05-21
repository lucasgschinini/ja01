package ar.com.telecom.shiva.base.dto;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;

import ar.com.telecom.shiva.base.constantes.Constantes;
import ar.com.telecom.shiva.base.constantes.Propiedades;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;

/**
 * DTO para la capa de presentacion (WEB) 
 */
public class DTO  extends Object implements Serializable {

	private static final long serialVersionUID = 4130001118020957462L;
	
	//Por defecto es un detalle
	private Integer operation = new Integer(Constantes.DETAIL); 
	private String accion;
	
	private String pantallaDestino="";
	
	private Serializable id;
	private String timeStamp;
	private String timeStampAux;
	private String url;

	protected Date fechaAlta;
	private Date fechaUltimaModificacion;
	private String usuarioModificacion;
	private String nombreUsuarioModificacion;
	
	private Boolean errorNingunaModificacion=false;
	private String descripcionNingunaModificacion="";
	
	
	public DTO(){
	}

	public DTO(Serializable key){
		this.setId(key);
	}
	
	/**
	 * Concurrencia
	 */
	@JsonIgnore
	public String getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	@JsonIgnore
	public String getTimeStampDTO() {
		if (this.fechaUltimaModificacion != null) {
			return String.valueOf(this.fechaUltimaModificacion.getTime());
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	@JsonIgnore
	public String getFechaAltaFormateado(){
		if(!Validaciones.isObjectNull(this.getFechaAlta())){
			return Utilidad.formatDateAndTimeFull(this.getFechaAlta());
		} else {
			return "-";
		}
	}
	
	/**
	 * 
	 * @return
	 */
	@JsonIgnore
	public String getFechaUtimaModificacionFormateado(){
		return Utilidad.formatDateAndTimeFull(this.getFechaUltimaModificacion());
	}
	
	public String urlFotoUsuario(String usuario){
		String numUsuario = Utilidad.eliminarCaracteresNoNumericos(usuario);
		String imagenUsuario = Utilidad.rellenarCerosIzquierda(numUsuario, 7);
		return Utilidad.reemplazarMensajes(Propiedades.SHIVA_PROPIEDADES.getString("signomobile.foto"), imagenUsuario);
	}
	
	/*********************************************************************************
	 * Setters & Getters
	 ********************************************************************************/
	@JsonIgnore
	public Integer getOperation() {
		return operation;
	}
	public void setOperation(Integer operation) {
		this.operation = operation;
	}

	@JsonIgnore
	public Serializable getId() {
		return id;
	}

	public void setId(Serializable id) {
		this.id = id;
	}
	
	@JsonIgnore
	public Boolean getErrorNingunaModificacion() {
		return errorNingunaModificacion;
	}

	public void setErrorNingunaModificacion(Boolean errorNingunaModificacion) {
		this.errorNingunaModificacion = errorNingunaModificacion;
	}

	@JsonIgnore
	public String getDescripcionNingunaModificacion() {
		return descripcionNingunaModificacion;
	}

	public void setDescripcionNingunaModificacion(String descripcionNingunaModificacion) {
		this.descripcionNingunaModificacion = descripcionNingunaModificacion;
	}
	
	@JsonIgnore
	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}
	
	@JsonIgnore
	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	@JsonIgnore
	public String getNombreUsuarioModificacion() {
		return nombreUsuarioModificacion;
	}

	public void setNombreUsuarioModificacion(String nombreUsuarioModificacion) {
		this.nombreUsuarioModificacion = nombreUsuarioModificacion;
	}

	@JsonIgnore
	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	@JsonIgnore
	public String getTimeStampAux() {
		return timeStampAux;
	}

	public void setTimeStampAux(String timeStampAux) {
		this.timeStampAux = timeStampAux;
	}
	
	/**
	 * 
	 * @return
	 */
	@JsonIgnore
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * 
	 * @param fechaAlta
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@JsonIgnore
	public String getPantallaDestino() {
		return pantallaDestino;
	}

	public void setPantallaDestino(String pantallaDestino) {
		this.pantallaDestino = pantallaDestino;
	}

	@JsonIgnore
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
