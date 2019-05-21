package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SHV_CNT_CONTABILIDAD_CABECERA")
public class ShvCntContabilidadCabecera extends Modelo {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_CNT_CONTABILIDAD_CABEC")
	@SequenceGenerator(name="SEQ_SHV_CNT_CONTABILIDAD_CABEC", sequenceName="SEQ_SHV_CNT_CONTABILIDAD_CABEC", allocationSize = 1)
	@Column (name="ID_CONTABILIDAD_CABECERA")	
	private Long idContabilidadCabecera;	
	@Column (name="ESTADO")
	private String estado;
	@Column (name="FECHA_CREACION")
	private Date fechaCreacion;
	@Column (name="CANTIDAD_REGISTROS_PROCESADOS")	
	private Long cantidadRegistrosProcesados;	
	@Column (name="IMPORTE_REGISTROS_PROCESADOS")	
	private BigDecimal importeRegistrosProcesados;	
	@Column (name="FECHA_PROCESAMIENTO")
	private Date fechaProcesamiento;
	
	
	/**
	 * @return the idContabilidadCabecera
	 */
	public Long getIdContabilidadCabecera() {
		return idContabilidadCabecera;
	}
	/**
	 * @param idContabilidadCabecera the idContabilidadCabecera to set
	 */
	public void setIdContabilidadCabecera(Long idContabilidadCabecera) {
		this.idContabilidadCabecera = idContabilidadCabecera;
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
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @return the cantidadRegistrosProcesados
	 */
	public Long getCantidadRegistrosProcesados() {
		return cantidadRegistrosProcesados;
	}
	/**
	 * @param cantidadRegistrosProcesados the cantidadRegistrosProcesados to set
	 */
	public void setCantidadRegistrosProcesados(Long cantidadRegistrosProcesados) {
		this.cantidadRegistrosProcesados = cantidadRegistrosProcesados;
	}
	/**
	 * @return the importeRegistrosProcesados
	 */
	public BigDecimal getImporteRegistrosProcesados() {
		return importeRegistrosProcesados;
	}
	/**
	 * @param importeRegistrosProcesados the importeRegistrosProcesados to set
	 */
	public void setImporteRegistrosProcesados(BigDecimal importeRegistrosProcesados) {
		this.importeRegistrosProcesados = importeRegistrosProcesados;
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

		
}
