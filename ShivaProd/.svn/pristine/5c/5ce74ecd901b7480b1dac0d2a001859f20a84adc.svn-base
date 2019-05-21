package ar.com.telecom.shiva.persistencia.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SHV_MIG_REGISTRO_AVC_ARCHIVOS")
public class ShvMigRegAvcArchivos extends Modelo {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_MIG_AVC_ARCHIVOS")
    @SequenceGenerator(name="SEQ_SHV_MIG_AVC_ARCHIVOS", sequenceName="SEQ_SHV_MIG_AVC_ARCHIVOS",allocationSize=1)
	@Column(name="ID")
	private Long idRegAvcArchivos;
	@Column(name="ID_ARCHIVO")
	private Long idArchivo;
	@Column(name="NOMBRE_ARCHIVO")
	private String nombreArchivo;
	@Column(name="USUARIO_PROCESAMIENTO")
	private String usuarioProcesamiento;
	@Column(name="FECHA_PROCESAMIENTO")
	private Date fechaProcesamiento;
	@Column(name="NUMERO_ACUERDO")
	private Long numeroAcuerdo;
	
	
	/**
	 * @return the idRegAvcArchivos
	 */
	public Long getIdRegAvcArchivos() {
		return idRegAvcArchivos;
	}
	/**
	 * @param idRegAvcArchivos the idRegAvcArchivos to set
	 */
	public void setIdRegAvcArchivos(Long idRegAvcArchivos) {
		this.idRegAvcArchivos = idRegAvcArchivos;
	}
	/**
	 * @return the idArchivo
	 */
	public Long getIdArchivo() {
		return idArchivo;
	}
	/**
	 * @param idArchivo the idArchivo to set
	 */
	public void setIdArchivo(Long idArchivo) {
		this.idArchivo = idArchivo;
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
	/**
	 * @return the usuarioProcesamiento
	 */
	public String getUsuarioProcesamiento() {
		return usuarioProcesamiento;
	}
	/**
	 * @param usuarioProcesamiento the usuarioProcesamiento to set
	 */
	public void setUsuarioProcesamiento(String usuarioProcesamiento) {
		this.usuarioProcesamiento = usuarioProcesamiento;
	}
	/**
	 * @return the fechaProcesamiento
	 */
	public Date getFechaProcesamiento() {
		return fechaProcesamiento;
	}
	/**
	 * @param fechaProcesamiento the fechaProcesamiento to set
	 */
	public void setFechaProcesamiento(Date fechaProcesamiento) {
		this.fechaProcesamiento = fechaProcesamiento;
	}
	/**
	 * @return the numeroAcuerdo
	 */
	public Long getNumeroAcuerdo() {
		return numeroAcuerdo;
	}
	/**
	 * @param numeroAcuerdo the numeroAcuerdo to set
	 */
	public void setNumeroAcuerdo(Long numeroAcuerdo) {
		this.numeroAcuerdo = numeroAcuerdo;
	}
	
	
}
