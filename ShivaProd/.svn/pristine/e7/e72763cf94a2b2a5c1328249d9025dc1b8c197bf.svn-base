package ar.com.telecom.shiva.persistencia.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import ar.com.telecom.shiva.base.enumeradores.EstadoProcesamientoHilosEnum;
import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoCobroHiloEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoImputacionEnum;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;

@Entity
@Table(name = "SHV_COB_PROC_HILOS_COBROS")
public class ShvCobProcHilosCobros extends Modelo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_COB_PROC_HILOS")
    @SequenceGenerator(name="SEQ_SHV_COB_PROC_HILOS", sequenceName="SEQ_SHV_COB_PROC_HILOS", allocationSize = 1)
	@Column(name="ID_PROC_HILOS")
	private Long idProcHilos;
	
	
	@Column(name="ID_COBRO")	
	private Long idCobro;
	
	@Column(name="ID_OPERACION")
	private Long idOperacion;
	
	@Column(name = "FECHA_INICIO", nullable = false)
	private Date fechaInicio;
	
	@Column(name = "FECHA_FIN")
	private Date fechaFin;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ESTADO")
	private EstadoProcesamientoHilosEnum  estado;
	
	@Column(name="CANT_TRANSACCIONES")
	private Integer cantTransacciones;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_HILO_POR_CANT_TRANS")
	private TipoCobroHiloEnum  tipoHiloCantTransacciones;
	
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_COBRO_POR_CANT_TRANS")
	private TipoCobroHiloEnum  tipoCobroCantTransacciones;
	
	@Enumerated(EnumType.STRING)
	@Column(name="PROCESO_BATCH")
	private TipoImputacionEnum  tipoImputacion;
	
	@Transient
	private SiNoEnum contieneAplicacionManual;
	
	@Transient
	private Estado estadoCobro;
	
	
	

	public Estado getEstadoCobro() {
		return estadoCobro;
	}

	public void setEstadoCobro(Estado estadoCobro) {
		this.estadoCobro = estadoCobro;
	}

	public SiNoEnum getContieneAplicacionManual() {
		return contieneAplicacionManual;
	}

	public void setContieneAplicacionManual(SiNoEnum contieneAplicacionManual) {
		this.contieneAplicacionManual = contieneAplicacionManual;
	}

	public TipoImputacionEnum getTipoImputacion() {
		return tipoImputacion;
	}

	public void setTipoImputacion(TipoImputacionEnum tipoImputacion) {
		this.tipoImputacion = tipoImputacion;
	}

	public Long getIdProcHilos() {
		return idProcHilos;
	}

	public void setIdProcHilos(Long idProcHilos) {
		this.idProcHilos = idProcHilos;
	}

	public Long getIdCobro() {
		return idCobro;
	}

	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}

	public Long getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public EstadoProcesamientoHilosEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoProcesamientoHilosEnum estado) {
		this.estado = estado;
	}

	public Integer getCantTransacciones() {
		return cantTransacciones;
	}

	public void setCantTransacciones(Integer cantTransacciones) {
		this.cantTransacciones = cantTransacciones;
	}

	public TipoCobroHiloEnum getTipoHiloCantTransacciones() {
		return tipoHiloCantTransacciones;
	}

	public void setTipoHiloCantTransacciones(
			TipoCobroHiloEnum tipoHiloCantTransacciones) {
		this.tipoHiloCantTransacciones = tipoHiloCantTransacciones;
	}

	public TipoCobroHiloEnum getTipoCobroCantTransacciones() {
		return tipoCobroCantTransacciones;
	}

	public void setTipoCobroCantTransacciones(
			TipoCobroHiloEnum tipoCobroCantTransacciones) {
		this.tipoCobroCantTransacciones = tipoCobroCantTransacciones;
	}
}
