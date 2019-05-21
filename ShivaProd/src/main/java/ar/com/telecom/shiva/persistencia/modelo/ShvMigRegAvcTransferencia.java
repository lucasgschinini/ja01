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
@Table(name = "SHV_MIG_REG_AVC_TRANSFERENCIA")
public class ShvMigRegAvcTransferencia extends Modelo {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_MIG_AVC_TRANSFERENCIA")
    @SequenceGenerator(name="SEQ_SHV_MIG_AVC_TRANSFERENCIA", sequenceName="SEQ_SHV_MIG_AVC_TRANSFERENCIA",allocationSize=1)
	@Column(name="ID")
	private Long idRegAvcTransferencia;
	@Column(name="ID_ARCHIVO")
	private Long idArchivo;
	@Column(name="IMPORTE")
	private BigDecimal importe;
	@Column(name="NUMERO_ACUERDO")
	private Long numeroAcuerdo;
	@Column(name="CODIGO_CLIENTE")
	private Long codigoCliente;
	@Column(name="FECHA_INGRESO")
	private Date fechaIngreso;
	@Column(name="SUCURSAL")
	private Long sucursal;
	@Column(name="REFERENCIA")
	private Long referencia;
	@Column(name="CODIGO")
	private Long codigo;
	@Column(name="OBSERVACIONES")
	private String observaciones;

	
	public Long getIdRegAvcTransferencia() {
		return idRegAvcTransferencia;
	}
	public void setIdRegAvcTransferencia(Long idRegAvcTransferencia) {
		this.idRegAvcTransferencia = idRegAvcTransferencia;
	}
	public Long getIdArchivo() {
		return idArchivo;
	}
	public void setIdArchivo(Long idArchivo) {
		this.idArchivo = idArchivo;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public Long getNumeroAcuerdo() {
		return numeroAcuerdo;
	}
	public void setNumeroAcuerdo(Long numeroAcuerdo) {
		this.numeroAcuerdo = numeroAcuerdo;
	}
	public Long getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(Long codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Long getSucursal() {
		return sucursal;
	}
	public void setSucursal(Long sucursal) {
		this.sucursal = sucursal;
	}
	public Long getReferencia() {
		return referencia;
	}
	public void setReferencia(Long referencia) {
		this.referencia = referencia;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	
}
