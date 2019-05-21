package ar.com.telecom.shiva.negocio.batch.bean;

import java.util.Date;

public class TransferenciaBatch extends RegistroAVCBatch {

	private static final long serialVersionUID = 1L;

	private Date fechaIngreso;
	private Long sucursal;
	private Long referencia;
	private Long codigo;
	private String observacion;
	private String observacionEditarCuit;
	private String cuit;
	private Long subtotal;
	
	public TransferenciaBatch(String acuerdo) {
		setIdAcuerdo(acuerdo);
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
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public Long getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Long subtotal) {
		this.subtotal = subtotal;
	}

	public String getObservacionEditarCuit() {
		return observacionEditarCuit;
	}

	public void setObservacionEditarCuit(String observacionEditarCuit) {
		this.observacionEditarCuit = observacionEditarCuit;
	}

}
