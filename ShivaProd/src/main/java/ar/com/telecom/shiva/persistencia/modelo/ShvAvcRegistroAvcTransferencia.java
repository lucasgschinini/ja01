package ar.com.telecom.shiva.persistencia.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "SHV_AVC_REG_AVC_TRANSFERENCIA")
public class ShvAvcRegistroAvcTransferencia extends Modelo{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_REGISTRO_AVC")
	@GeneratedValue(generator="SEQ_SHV_AVC_REGISTRO_AVC")
	@GenericGenerator(name = "SEQ_SHV_AVC_REGISTRO_AVC", strategy="foreign", parameters=@Parameter(name="property", value="registroAvc")) 
	private Long idRegistroAvc;
	
	@OneToOne  
	@PrimaryKeyJoinColumn  
	private ShvAvcRegistroAvc registroAvc;

	@Column(name="FECHA_INGRESO") 	
	private Date fechaIngreso;
	
	@Column(name="SUCURSAL") 		
	private Long sucursal;
	
	@Column(name="REFERENCIA") 		
	private Long referencia;
	
	@Column(name="CODIGO") 			
	private Long codigo;
	
	@Column(name="OBSERVACION") 	
	private String observacion;
	
	@Column(name="OBSERVACION_EDITAR_CUIT") 	
	private String observacionEditarCuit;
	
	@Column(name="CUIT") 			
	private String cuit;
	
	@Column(name="ERROR_ALTA_TRANSFERENCIA") 			
	private String errorAlta;
	
	@Column(name="FECHA_ERROR") 	
	private Date fechaError;
	
	
	public Long getIdRegistroAvc() {
		return idRegistroAvc;
	}
	public void setIdRegistroAvc(Long idRegistroAvc) {
		this.idRegistroAvc = idRegistroAvc;
	}
	public ShvAvcRegistroAvc getRegistroAvc() {
		return registroAvc;
	}
	public void setRegistroAvc(ShvAvcRegistroAvc registroAvc) {
		this.registroAvc = registroAvc;
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
	public String getObservacionEditarCuit() {
		return observacionEditarCuit;
	}
	public void setObservacionEditarCuit(String observacionEditarCuit) {
		this.observacionEditarCuit = observacionEditarCuit;
	}
	public String getErrorAlta() {
		return errorAlta;
	}
	public void setErrorAlta(String errorAlta) {
		this.errorAlta = errorAlta;
	}
	public Date getFechaError() {
		return fechaError;
	}
	public void setFechaError(Date fechaError) {
		this.fechaError = fechaError;
	}

	
}
