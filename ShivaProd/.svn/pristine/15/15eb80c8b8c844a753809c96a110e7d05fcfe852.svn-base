package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SHV_MAS_OPER_MASIVA_ARCH")
public class ShvMasOperacionMasivaArchivo extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_MAS_OPERACION_ARCH")
	@SequenceGenerator(name = "SEQ_SHV_MAS_OPERACION_ARCH", sequenceName = "SEQ_SHV_MAS_OPERACION_ARCH", allocationSize = 1)
	@Column(name = "ID_OPERACION_MASIVA_ARCHIVO", nullable = false)
	private Long idOperacionMasivaArchivo;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name="ID_OPERACION_MASIVA", referencedColumnName="ID_OPERACION_MASIVA") 	
	private ShvMasOperacionMasiva operacionMasiva;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumns({ @JoinColumn(name = "ID_ADJUNTO", referencedColumnName = "ID_ADJUNTO", nullable = false, updatable = false) })
	private ShvDocDocumentoAdjunto documentoAdjunto;
	
	@Column(name="LOG_PROCESAMIENTO")
	private String logProcesamiento;
	
	@Column(name="NOMBRE_ARCHIVO") 	
	private String nombreArchivo;
	
	@Column(name="CANTIDAD_REGISTROS") 	
	private Long cantidadRegistros;
	
	@Column(name="IMPORTE_TOTAL") 	
	private BigDecimal importeTotal;
	
	@Column(name="FECHA_DERIVACION") 			
	private Date fechaDerivacion;
	
	@Column(name="FECHA_AUTORIZACION") 			
	private Date fechaAutorizacion;
	
	@Column(name="FECHA_PROCESO") 			
	private Date fechaProceso;

	public Long getIdOperacionMasivaArchivo() {
		return idOperacionMasivaArchivo;
	}

	public void setIdOperacionMasivaArchivo(Long idOperacionMasivaArchivo) {
		this.idOperacionMasivaArchivo = idOperacionMasivaArchivo;
	}

	public ShvMasOperacionMasiva getOperacionMasiva() {
		return operacionMasiva;
	}

	public void setOperacionMasiva(ShvMasOperacionMasiva operacionMasiva) {
		this.operacionMasiva = operacionMasiva;
	}

	public ShvDocDocumentoAdjunto getDocumentoAdjunto() {
		return documentoAdjunto;
	}

	public void setDocumentoAdjunto(ShvDocDocumentoAdjunto documentoAdjunto) {
		this.documentoAdjunto = documentoAdjunto;
	}

	public String getLogProcesamiento() {
		return logProcesamiento;
	}

	public void setLogProcesamiento(String logProcesamiento) {
		this.logProcesamiento = logProcesamiento;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public Long getCantidadRegistros() {
		return cantidadRegistros;
	}

	public void setCantidadRegistros(Long cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}

	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Date getFechaDerivacion() {
		return fechaDerivacion;
	}

	public void setFechaDerivacion(Date fechaDerivacion) {
		this.fechaDerivacion = fechaDerivacion;
	}

	public Date getFechaAutorizacion() {
		return fechaAutorizacion;
	}

	public void setFechaAutorizacion(Date fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}

	public Date getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	
}
