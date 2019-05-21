package ar.com.telecom.shiva.persistencia.modelo.simple;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobDescobro;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;

@Entity
@Table(name = "SHV_COB_COBRO")
public class ShvCobCobroSimple extends Modelo{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_COBRO")	
	private Long idCobro;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_WORKFLOW", referencedColumnName="ID_WORKFLOW")
	private ShvWfWorkflow workflow;

	@Column(name="ID_OPERACION") 	
	private Long idOperacion;
	
	@Transient
	private ShvCobDescobro descobro;
	
	@Column(name="FECHA_ULT_PROCESAMIENTO") 	
	private Date fechaUltProcesamiento;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CONTIENE_APLICACION_MANUAL")
	private SiNoEnum contieneAplicacionManual;
	
	public SiNoEnum getContieneAplicacionManual() {
		return contieneAplicacionManual;
	}
	public void setContieneAplicacionManual(SiNoEnum contieneAplicacionManual) {
		this.contieneAplicacionManual = contieneAplicacionManual;
	}
	public Long getIdCobro() {
		return idCobro;
	}
	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}
	
	public ShvWfWorkflow getWorkflow() {
		return workflow;
	}
	public void setWorkflow(ShvWfWorkflow workflow) {
		this.workflow = workflow;
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
	
	
}
