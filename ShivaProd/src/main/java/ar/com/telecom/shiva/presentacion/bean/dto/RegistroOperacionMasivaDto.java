package ar.com.telecom.shiva.presentacion.bean.dto;

import java.util.Date;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.EstadoRegistroOperacionMasivaEnum;
import ar.com.telecom.shiva.persistencia.modelo.ShvMasOperacionMasiva;

public class RegistroOperacionMasivaDto extends DTO {
	
	private static final long serialVersionUID = 1L;

	private Long idRegistro;
	private ShvMasOperacionMasiva operacionMasiva;
	private Long idCobro;
	private EstadoRegistroOperacionMasivaEnum estado;
	private Date fechaCreacion;
	private String usuarioCreacion;
	private Date fechaModificacion;
	private String nombreArchivo;

	public Long getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}

	public ShvMasOperacionMasiva getOperacionMasiva() {
		return operacionMasiva;
	}

	public void setOperacionMasiva(ShvMasOperacionMasiva operacionMasiva) {
		this.operacionMasiva = operacionMasiva;
	}

	public Long getIdCobro() {
		return idCobro;
	}

	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}

	public EstadoRegistroOperacionMasivaEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoRegistroOperacionMasivaEnum estado) {
		this.estado = estado;
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

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * @param nombreArchivo the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
}
