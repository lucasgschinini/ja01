package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SHV_ARC_ARCHIVO")
public class ShvArcArchivo extends Modelo {
	 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_ARC_ARCHIVO")
    @SequenceGenerator(name="SEQ_SHV_ARC_ARCHIVO", sequenceName="SEQ_SHV_ARC_ARCHIVO",allocationSize=1)
	@Column(name="ID_ARCHIVO")	
	private Long idArchivo;
	
	@OneToMany(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER, mappedBy = "archivo")
	private Set<ShvCobAplicacionManualBatchDetalle> registros = new HashSet<ShvCobAplicacionManualBatchDetalle>();
	
	@Column(name="NOMBRE_ARCHIVO")	
	private String nombreArchivo;

	@Column(name="PROCESO")	
	private String proceso;
	
	@Column(name="CANTIDAD_REGISTROS")	
	private Long cantidadRegistros;
	
	@Column(name="IMPORTE_TOTAL")	
	private BigDecimal importeTotal;

	@Column(name="FECHA_ENVIO")	
	private Date fechaEnvio;
	
	@Column(name="FECHA_RECEPCION")	
	private Date fechaRecepcion;

	public Long getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(Long idArchivo) {
		this.idArchivo = idArchivo;
	}

	public Set<ShvCobAplicacionManualBatchDetalle> getRegistros() {
		return registros;
	}

	public void setRegistros(Set<ShvCobAplicacionManualBatchDetalle> registros) {
		this.registros = registros;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
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

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}
	
}
