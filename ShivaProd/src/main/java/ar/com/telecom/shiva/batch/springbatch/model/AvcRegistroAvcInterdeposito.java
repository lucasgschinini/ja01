package ar.com.telecom.shiva.batch.springbatch.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Se crea esta clase a fin de poder generar altas de registros AVC a partir del modelo, 
 * y no desde una clase DTO.
 * Adicionalmente, aprovecho el momento para armar estos modelos con subclases usando herencia, 
 * haciendo mas facil el manejo al momento de codificar.
 * La idea es que una vez que este modelo se utilice en todos lados, eliminar el viejo modelo y reemplazarlo con este.
 * 
 * @author u564030 Pablo M. Ibarrola
 */
@Entity
@Table(name = "SHV_AVC_REG_AVC_INTERDEPOSITO")
@PrimaryKeyJoinColumn(name = "ID_REGISTRO_AVC")
public class AvcRegistroAvcInterdeposito extends AvcRegistroAvc {

	private static final long serialVersionUID = 1L;
	
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

	/**
	 * @return the fechaValor
	 */
	public Date getFechaValor() {
		return fechaValor;
	}

	/**
	 * @param fechaValor the fechaValor to set
	 */
	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
	}

	/**
	 * @return the fechaIngreso
	 */
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param fechaIngreso the fechaIngreso to set
	 */
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**
	 * @return the codigoOperacion
	 */
	public String getCodigoOperacion() {
		return codigoOperacion;
	}

	/**
	 * @param codigoOperacion the codigoOperacion to set
	 */
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	/**
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}

	/**
	 * @param comprobante the comprobante to set
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	/**
	 * @return the codigoOrganismo
	 */
	public String getCodigoOrganismo() {
		return codigoOrganismo;
	}

	/**
	 * @param codigoOrganismo the codigoOrganismo to set
	 */
	public void setCodigoOrganismo(String codigoOrganismo) {
		this.codigoOrganismo = codigoOrganismo;
	}

	/**
	 * @return the codigoInterdeposito
	 */
	public Long getCodigoInterdeposito() {
		return codigoInterdeposito;
	}

	/**
	 * @param codigoInterdeposito the codigoInterdeposito to set
	 */
	public void setCodigoInterdeposito(Long codigoInterdeposito) {
		this.codigoInterdeposito = codigoInterdeposito;
	}

	/**
	 * @return the deposito
	 */
	public String getDeposito() {
		return deposito;
	}

	/**
	 * @param deposito the deposito to set
	 */
	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}

	/**
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * @return the codOpBanco
	 */
	public Long getCodOpBanco() {
		return codOpBanco;
	}

	/**
	 * @param codOpBanco the codOpBanco to set
	 */
	public void setCodOpBanco(Long codOpBanco) {
		this.codOpBanco = codOpBanco;
	}

	/**
	 * @return the pcc
	 */
	public String getPcc() {
		return pcc;
	}

	/**
	 * @param pcc the pcc to set
	 */
	public void setPcc(String pcc) {
		this.pcc = pcc;
	}

	/**
	 * @return the errorAltaInterdeposito
	 */
	public String getErrorAltaInterdeposito() {
		return errorAltaInterdeposito;
	}

	/**
	 * @param errorAltaInterdeposito the errorAltaInterdeposito to set
	 */
	public void setErrorAltaInterdeposito(String errorAltaInterdeposito) {
		this.errorAltaInterdeposito = errorAltaInterdeposito;
	}

	/**
	 * @return the fechaError
	 */
	public Date getFechaError() {
		return fechaError;
	}

	/**
	 * @param fechaError the fechaError to set
	 */
	public void setFechaError(Date fechaError) {
		this.fechaError = fechaError;
	}

	@Override
	public String toString() {
		return "AvcRegistroAvcInterdeposito [fechaValor=" + fechaValor
				+ ", fechaIngreso=" + fechaIngreso + ", concepto=" + concepto
				+ ", codigoOperacion=" + codigoOperacion + ", comprobante="
				+ comprobante + ", codigoOrganismo=" + codigoOrganismo
				+ ", codigoInterdeposito=" + codigoInterdeposito
				+ ", deposito=" + deposito + ", sucursal=" + sucursal
				+ ", codOpBanco=" + codOpBanco + ", pcc=" + pcc
				+ ", errorAltaInterdeposito=" + errorAltaInterdeposito
				+ ", fechaError=" + fechaError + "]";
	}
	
	
	
	
}
