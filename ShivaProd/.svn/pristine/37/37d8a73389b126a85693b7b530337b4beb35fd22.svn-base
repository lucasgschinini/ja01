package ar.com.telecom.shiva.persistencia.modelo.simple;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

/**
 * @author u564030 Pablo M. Ibarrola
 * 
 * Req 70868 Mejora de Performance en Talonarios.
 * Se crean objetos mas livianos para hacer mas performance la gestion de recibos y talonarios 
 */
@Entity
@Table(name = "SHV_TAL_RECIBO_PREIMPRESO")
public class ShvTalReciboPreImpresoSimplificado extends Modelo{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_TAL_RECIBO_PREIMPRESO")
	@SequenceGenerator(name="SEQ_SHV_TAL_RECIBO_PREIMPRESO", sequenceName="SEQ_SHV_TAL_RECIBO_PREIMPRESO", allocationSize = 1)
	@Column(name="ID_RECIBO_PREIMPRESO")
	private Integer idReciboPreimpreso;

	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="ID_TALONARIO", referencedColumnName="ID_TALONARIO")
	private ShvTalTalonarioSimplificado talonario;

	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_WORKFLOW", referencedColumnName="ID_WORKFLOW")
	private ShvWfWorkflowSimplificado workflow;
	
	@Column(name="NUMERO_RECIBO")	 			
	private	String	numeroRecibo;
	
	@Column(name="IMPORTE")			 			
	private	BigDecimal 	importe;
	
	@Column(name="FECHA_ANULACION")	 			
	private	Date fechaAnulacion;
	
	@Column(name="USUARIO_ANULACION")	 		
	private	String usuarioAnulacion;
	
	@Column(name="FECHA_INGRESO")
	private	Date fechaIngreso;

	/**
	 * @return the idReciboPreimpreso
	 */
	public Integer getIdReciboPreimpreso() {
		return idReciboPreimpreso;
	}

	/**
	 * @param idReciboPreimpreso the idReciboPreimpreso to set
	 */
	public void setIdReciboPreimpreso(Integer idReciboPreimpreso) {
		this.idReciboPreimpreso = idReciboPreimpreso;
	}

	/**
	 * @return the talonario
	 */
	public ShvTalTalonarioSimplificado getTalonario() {
		return talonario;
	}

	/**
	 * @param talonario the talonario to set
	 */
	public void setTalonario(ShvTalTalonarioSimplificado talonario) {
		this.talonario = talonario;
	}

	/**
	 * @return the workflow
	 */
	public ShvWfWorkflowSimplificado getWorkflow() {
		return workflow;
	}

	/**
	 * @param workflow the workflow to set
	 */
	public void setWorkflow(ShvWfWorkflowSimplificado workflow) {
		this.workflow = workflow;
	}

	/**
	 * @return the numeroRecibo
	 */
	public String getNumeroRecibo() {
		return numeroRecibo;
	}

	/**
	 * @param numeroRecibo the numeroRecibo to set
	 */
	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}

	/**
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * @param importe the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * @return the fechaAnulacion
	 */
	public Date getFechaAnulacion() {
		return fechaAnulacion;
	}

	/**
	 * @param fechaAnulacion the fechaAnulacion to set
	 */
	public void setFechaAnulacion(Date fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}

	/**
	 * @return the usuarioAnulacion
	 */
	public String getUsuarioAnulacion() {
		return usuarioAnulacion;
	}

	/**
	 * @param usuarioAnulacion the usuarioAnulacion to set
	 */
	public void setUsuarioAnulacion(String usuarioAnulacion) {
		this.usuarioAnulacion = usuarioAnulacion;
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
}
