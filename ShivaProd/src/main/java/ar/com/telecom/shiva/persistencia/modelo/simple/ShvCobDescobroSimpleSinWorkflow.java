package ar.com.telecom.shiva.persistencia.modelo.simple;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

/**
 * @author u578936 M.A.Uehara
 *
 */
@Entity
@Table(name = "SHV_COB_DESCOBRO")
public class ShvCobDescobroSimpleSinWorkflow extends Modelo {
 	
	private static final long serialVersionUID = 20171024L;

	@Id
	@Column(name="ID_DESCOBRO")	
	private Long idDescobro;
	
	@Column(name="ID_WORKFLOW") 
	private Long idWorkflow;

	@Column(name="ID_OPERACION") 	
	private Long idOperacion;
	
	@Column(name="ID_COBRO")	
	private Long idCobro;

	/**
	 * @return the idDescobro
	 */
	public Long getIdDescobro() {
		return idDescobro;
	}

	/**
	 * @param idDescobro the idDescobro to set
	 */
	public void setIdDescobro(Long idDescobro) {
		this.idDescobro = idDescobro;
	}

	/**
	 * @return the idWorkflow
	 */
	public Long getIdWorkflow() {
		return idWorkflow;
	}

	/**
	 * @param idWorkflow the idWorkflow to set
	 */
	public void setIdWorkflow(Long idWorkflow) {
		this.idWorkflow = idWorkflow;
	}

	/**
	 * @return the idOperacion
	 */
	public Long getIdOperacion() {
		return idOperacion;
	}

	/**
	 * @param idOperacion the idOperacion to set
	 */
	public void setIdOperacion(Long idOperacion) {
		this.idOperacion = idOperacion;
	}

	/**
	 * @return the idCobro
	 */
	public Long getIdCobro() {
		return idCobro;
	}

	/**
	 * @param idCobro the idCobro to set
	 */
	public void setIdCobro(Long idCobro) {
		this.idCobro = idCobro;
	}
}
