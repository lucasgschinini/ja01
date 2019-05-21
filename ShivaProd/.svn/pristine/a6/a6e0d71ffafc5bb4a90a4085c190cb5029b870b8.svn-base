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
@Table(name = "SHV_AVC_REG_AVC_TRANSFERENCIA")
@PrimaryKeyJoinColumn(name = "ID_REGISTRO_AVC")
public class AvcRegistroAvcTransferencia extends AvcRegistroAvc {
	
	private static final long serialVersionUID = 1L;
	
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
	 * @return the sucursal
	 */
	public Long getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(Long sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * @return the referencia
	 */
	public Long getReferencia() {
		return referencia;
	}

	/**
	 * @param referencia the referencia to set
	 */
	public void setReferencia(Long referencia) {
		this.referencia = referencia;
	}

	/**
	 * @return the codigo
	 */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the observacionEditarCuit
	 */
	public String getObservacionEditarCuit() {
		return observacionEditarCuit;
	}

	/**
	 * @param observacionEditarCuit the observacionEditarCuit to set
	 */
	public void setObservacionEditarCuit(String observacionEditarCuit) {
		this.observacionEditarCuit = observacionEditarCuit;
	}

	/**
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * @param cuit the cuit to set
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * @return the errorAlta
	 */
	public String getErrorAlta() {
		return errorAlta;
	}

	/**
	 * @param errorAlta the errorAlta to set
	 */
	public void setErrorAlta(String errorAlta) {
		this.errorAlta = errorAlta;
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
		return "AvcRegistroAvcTransferencia [fechaIngreso=" + fechaIngreso
				+ ", sucursal=" + sucursal + ", referencia=" + referencia
				+ ", codigo=" + codigo + ", observacion=" + observacion
				+ ", observacionEditarCuit=" + observacionEditarCuit
				+ ", cuit=" + cuit + ", errorAlta=" + errorAlta
				+ ", fechaError=" + fechaError + "]";
	}

	
	
	
	
}
