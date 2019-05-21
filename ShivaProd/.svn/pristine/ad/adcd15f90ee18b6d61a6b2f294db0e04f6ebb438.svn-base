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
@Table(name = "SHV_AVC_REG_AVC_INTERDEPOSITO")
public class ShvAvcRegistroAvcInterdeposito extends Modelo{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_REGISTRO_AVC")
	@GeneratedValue(generator="SEQ_SHV_AVC_REGISTRO_AVC")
	@GenericGenerator(name = "SEQ_SHV_AVC_REGISTRO_AVC", strategy="foreign", parameters=@Parameter(name="property", value="registroAvc")) 
	private Long idRegistroAvc;
	
	@OneToOne
	@PrimaryKeyJoinColumn  
	private ShvAvcRegistroAvc registroAvc;
	
	@Column(name="FECHA_VALOR")
	private Date fechaValor;
	
	@Column(name="FECHA_INGRESO")
	private Date fechaIngreso;
	
	@Column(name="CONCEPTO")
	private String concepto;
	
	@Column(name="CODIGO_OPERACION")
	private String codigoOperacion;
	
	@Column(name="COMPROBANTE")
	private String comprobante;
	
	@Column(name="CODIGO_ORGANISMO")
	private String codigoOrganismo;
	
	@Column(name="CODIGO_INTERDEPOSITO")
	private Long codigoInterdeposito;
	
	@Column(name="DEPOSITO")
	private String deposito;
	
	@Column(name="SUCURSAL")
	private String sucursal;
	
	@Column(name="COD_OP_BANCO")
	private Long codOpBanco;
	
	@Column(name="PCC")
	private String pcc;
	
	@Column(name="ERROR_ALTA_INTERDEPOSITO")
	private String errorAltaInterdeposito;
	
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
	public Date getFechaValor() {
		return fechaValor;
	}
	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getCodigoOperacion() {
		return codigoOperacion;
	}
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}
 
	public String getComprobante() {
		return comprobante;
	}
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getCodigoOrganismo() {
		return codigoOrganismo;
	}
	public void setCodigoOrganismo(String codigoOrganismo) {
		this.codigoOrganismo = codigoOrganismo;
	}
	public String getSucursal() {
		return sucursal;
	}
	public Long getCodigoInterdeposito() {
		return codigoInterdeposito;
	}
	public void setCodigoInterdeposito(Long codigoInterdeposito) {
		this.codigoInterdeposito = codigoInterdeposito;
	}
	public String getDeposito() {
		return deposito;
	}
	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}
	public Long getCodOpBanco() {
		return codOpBanco;
	}
	public void setCodOpBanco(Long codOpBanco) {
		this.codOpBanco = codOpBanco;
	}
	public String getPcc() {
		return pcc;
	}
	public void setPcc(String pcc) {
		this.pcc = pcc;
	}
	public String getErrorAltaInterdeposito() {
		return errorAltaInterdeposito;
	}
	public void setErrorAltaInterdeposito(String errorAltaInterdeposito) {
		this.errorAltaInterdeposito = errorAltaInterdeposito;
	}
	public Date getFechaError() {
		return fechaError;
	}
	public void setFechaError(Date fechaError) {
		this.fechaError = fechaError;
	}
	
}
