package ar.com.telecom.shiva.persistencia.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;

/**
 * @author u529234
 *
 */
@Entity
@Table(name = "SHV_AVC_ARCHIVO_AVC")
public class ShvAvcArchivoAvc extends Modelo{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_AVC_ARCHIVOS_AVC")
	@SequenceGenerator(name="SEQ_SHV_AVC_ARCHIVOS_AVC", sequenceName="SEQ_SHV_AVC_ARCHIVOS_AVC", allocationSize = 1)
	@Column(name="ID_ARCHIVO_AVC")
	private Long idArchivosAvc; 

	@Column(name="NOMBRE_ARCHIVO") 	
	private String nombreArchivo;
	
	@Column(name="LOG_PROCESAMIENTO")
	private String logProcesamiento;
	
	@Column(name="USUARIO_PROCESAMIENTO") 	
	private String usuarioProcesamiento;
	
	@Column(name="FECHA_PROCESAMIENTO") 	
	private Date fechaProcesamiento;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumns({ @JoinColumn(name = "ID_ADJUNTO", referencedColumnName = "ID_ADJUNTO", nullable = false, updatable = false) })
	private ShvDocDocumentoAdjunto documentoAdjunto;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_ACUERDO")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamAcuerdo acuerdo;
	
	public Long getIdArchivosAvc() {
		return idArchivosAvc;
	}
	public void setIdArchivosAvc(Long idArchivosAvc) {
		this.idArchivosAvc = idArchivosAvc;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getLogProcesamiento() {
		return logProcesamiento;
	}
	public void setLogProcesamiento(String logProcesamiento) {
		this.logProcesamiento = logProcesamiento;
	}
	public String getUsuarioProcesamiento() {
		return usuarioProcesamiento;
	}
	public void setUsuarioProcesamiento(String usuarioProcesamiento) {
		this.usuarioProcesamiento = usuarioProcesamiento;
	}
	public Date getFechaProcesamiento() {
		return fechaProcesamiento;
	}
	public void setFechaProcesamiento(Date fechaProcesamiento) {
		this.fechaProcesamiento = fechaProcesamiento;
	}
	public ShvParamAcuerdo getAcuerdo() {
		return acuerdo;
	}
	public void setAcuerdo(ShvParamAcuerdo acuerdo) {
		this.acuerdo = acuerdo;
	}
	public ShvDocDocumentoAdjunto getDocumentoAdjunto() {
		return documentoAdjunto;
	}
	public void setDocumentoAdjunto(ShvDocDocumentoAdjunto documentoAdjunto) {
		this.documentoAdjunto = documentoAdjunto;
	}
 
}
