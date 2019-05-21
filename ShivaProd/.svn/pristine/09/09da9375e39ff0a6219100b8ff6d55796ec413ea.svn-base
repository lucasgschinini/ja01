package ar.com.telecom.shiva.persistencia.modelo.simple;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;

@Entity
@Table(name = "SHV_COB_COBRO")
public class ShvCobCobroSimpleSinWorkflow extends Modelo{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_COBRO")	
	private Long idCobro;
	
	@Column(name="ID_WORKFLOW") 
	private Long idWorkflow;

	@Column(name="ID_OPERACION") 	
	private Long idOperacion;
	
	@Transient
	private ShvCobDescobro descobro;
	
	@Column(name="FECHA_ULT_PROCESAMIENTO") 	
	private Date fechaUltProcesamiento;
	
	public Long getIdCobro() {
		return idCobro;
	}
	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}
	
	public ShvCobDescobro getDescobro() {
		return descobro;
	}
	public void setDescobro(ShvCobDescobro descobro) {
		this.descobro = descobro;
	}
	public Long getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}
	public Date getFechaUltProcesamiento() {
		return fechaUltProcesamiento;
	}
	public void setFechaUltProcesamiento(Date fechaUltProcesamiento) {
		this.fechaUltProcesamiento = fechaUltProcesamiento;
	}
	public Long getIdWorkflow() {
		return idWorkflow;
	}
	public void setIdWorkflow(Long idWorkflow) {
		this.idWorkflow = idWorkflow;
	}
	
	
}
