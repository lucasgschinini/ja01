package ar.com.telecom.shiva.persistencia.modelo.simple;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_MAS_OPER_MASIVA")
public class ShvMasOperacionMasivaSimplificado extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_OPERACION_MASIVA", nullable = false)
	private Long idOperacionMasiva;

	@Column(name="USUARIO_MODIFICACION")
	private String usuarioModificacion;
	
	@Column(name="FECHA_MODIFICACION")
	private Date fechaModificacion;

	@Column(name="CANT_REGISTROS_PROCESADOS_OK")
	private Long cantRegistrosProcesados;

	@Column(name="CANT_REGISTROS_ERROR")
	private Long cantRegistrosError;

	@Column(name="CANT_REGISTROS")
	private Long cantRegistros;

	@Column(name="CANT_REGISTROS_EN_PROCESO")
	private Long cantRegistrosEnProceso;



	public ShvMasOperacionMasivaSimplificado () {}
			
	public ShvMasOperacionMasivaSimplificado (
		Long idOperacionMasiva,
		Long cantRegistrosProcesados,
		Long cantRegistrosError,
		Long cantRegistros,
		Long cantRegistrosEnProceso
	) {
		this.idOperacionMasiva = idOperacionMasiva;
		this.cantRegistrosProcesados = cantRegistrosProcesados;
		this.cantRegistrosError = cantRegistrosError;
		this.cantRegistros = cantRegistros;
		this.cantRegistrosEnProceso = cantRegistrosEnProceso;
	}
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
	 * @return the cantRegistrosProcesados
	 */
	public Long getCantRegistrosProcesados() {
		return cantRegistrosProcesados;
	}
	/**
	 * @param cantRegistrosProcesados the cantRegistrosProcesados to set
	 */
	public void setCantRegistrosProcesados(Long cantRegistrosProcesados) {
		this.cantRegistrosProcesados = cantRegistrosProcesados;
	}
	/**
	 * @return the cantRegistrosError
	 */
	public Long getCantRegistrosError() {
		return cantRegistrosError;
	}
	/**
	 * @param cantRegistrosError the cantRegistrosError to set
	 */
	public void setCantRegistrosError(Long cantRegistrosError) {
		this.cantRegistrosError = cantRegistrosError;
	}
	/**
	 * @return the cantRegistros
	 */
	public Long getCantRegistros() {
		return cantRegistros;
	}
	/**
	 * @param cantRegistros the cantRegistros to set
	 */
	public void setCantRegistros(Long cantRegistros) {
		this.cantRegistros = cantRegistros;
	}
	/**
	 * @return the cantRegistrosEnProceso
	 */
	public Long getCantRegistrosEnProceso() {
		return cantRegistrosEnProceso;
	}
	/**
	 * @param cantRegistrosEnProceso the cantRegistrosEnProceso to set
	 */
	public void setCantRegistrosEnProceso(Long cantRegistrosEnProceso) {
		this.cantRegistrosEnProceso = cantRegistrosEnProceso;
	}


	/**
	 * @return the usuarioModificacion
	 */
	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	/**
	 * @param usuarioModificacion the usuarioModificacion to set
	 */
	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	/**
	 * @return the fechaModificacion
	 */
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @param fechaModificacion the fechaModificacion to set
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
}
