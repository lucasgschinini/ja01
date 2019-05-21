package ar.com.telecom.shiva.persistencia.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "SHV_TAL_RECIBO_PREIMPRESO")
public class ShvTalReciboPreImpreso extends Modelo{

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_TAL_RECIBO_PREIMPRESO")
	@SequenceGenerator(name="SEQ_SHV_TAL_RECIBO_PREIMPRESO", sequenceName="SEQ_SHV_TAL_RECIBO_PREIMPRESO", allocationSize = 1)
	@Column(name="ID_RECIBO_PREIMPRESO")
	private 	Integer idReciboPreimpreso;

	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="ID_TALONARIO", referencedColumnName="ID_TALONARIO")
	private ShvTalTalonario talonario;

	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_WORKFLOW", referencedColumnName="ID_WORKFLOW")
	private ShvWfWorkflow workflow;
	
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
	

	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy="reciboPreImpreso")
	private Set<ShvValBoletaDepositoCheque> shvValBoletaDepositoCheque;
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy="reciboPreImpreso")
	private Set<ShvValBoletaDepositoChequePagoDiferido> shvValBoletaDepositoChequePagoDiferido;
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy="reciboPreImpreso")
	private Set<ShvValBoletaDepositoEfectivo> shvValBoletaDepositoEfectivo;
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy="reciboPreImpreso")
	private Set<ShvValValorRetencion> shvValValorRetencion;
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy="reciboPreImpreso")
	private Set<ShvValValorEfectivo> valorEfectivo;

	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy="reciboPreImpreso")
	private Set<ShvValValorCheque> valorCheque;

	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy="reciboPreImpreso")
	private Set<ShvValValorChequePagoDiferido> valorChequePagoDiferido;
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, mappedBy="reciboPreImpreso")
	@NotFound(action=NotFoundAction.IGNORE)
	private Set<ShvTalCompensacion> shvTalCompensacion;
	
	/******************************************
	 *           Getters & Setters            *
	 *****************************************/
	
	
	public ShvWfWorkflow getWorkflow() {
		return workflow;
	}
	public void setWorkflow(ShvWfWorkflow workflow) {
		this.workflow = workflow;
	}
	

	public Set<ShvTalCompensacion> getShvTalCompensacion() {
		return shvTalCompensacion;
	}
	public void setShvTalCompensacion(Set<ShvTalCompensacion> shvTalCompensacion) {
		this.shvTalCompensacion = shvTalCompensacion;
	}
	public void setTalonario(ShvTalTalonario talonario) {
		this.talonario = talonario;
	}
	public Integer getIdReciboPreimpreso() {
		return idReciboPreimpreso;
	}
	public void setIdReciboPreimpreso(Integer idReciboPreimpreso) {
		this.idReciboPreimpreso = idReciboPreimpreso;
	}
	public ShvTalTalonario getTalonario() {
		return talonario;
	}
	public String getNumeroRecibo() {
		return numeroRecibo;
	}
	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}

	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public Date getFechaAnulacion() {
		return fechaAnulacion;
	}
	public void setFechaAnulacion(Date fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}
	public String getUsuarioAnulacion() {
		return usuarioAnulacion;
	}
	public void setUsuarioAnulacion(String usuarioAnulacion) {
		this.usuarioAnulacion = usuarioAnulacion;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
    public Object clone(){
        Object obj=null;
        try{
            obj=super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }
	/**
	 * @return the shvValBoletaDepositoCheque
	 */
	public Set<ShvValBoletaDepositoCheque> getShvValBoletaDepositoCheque() {
		return shvValBoletaDepositoCheque;
	}
	/**
	 * @param shvValBoletaDepositoCheque the shvValBoletaDepositoCheque to set
	 */
	public void setShvValBoletaDepositoCheque(
			Set<ShvValBoletaDepositoCheque> shvValBoletaDepositoCheque) {
		this.shvValBoletaDepositoCheque = shvValBoletaDepositoCheque;
	}
	/**
	 * @return the shvValBoletaDepositoChequePagoDiferido
	 */
	public Set<ShvValBoletaDepositoChequePagoDiferido> getShvValBoletaDepositoChequePagoDiferido() {
		return shvValBoletaDepositoChequePagoDiferido;
	}
	/**
	 * @param shvValBoletaDepositoChequePagoDiferido the shvValBoletaDepositoChequePagoDiferido to set
	 */
	public void setShvValBoletaDepositoChequePagoDiferido(
			Set<ShvValBoletaDepositoChequePagoDiferido> shvValBoletaDepositoChequePagoDiferido) {
		this.shvValBoletaDepositoChequePagoDiferido = shvValBoletaDepositoChequePagoDiferido;
	}
	/**
	 * @return the shvValBoletaDepositoEfectivo
	 */
	public Set<ShvValBoletaDepositoEfectivo> getShvValBoletaDepositoEfectivo() {
		return shvValBoletaDepositoEfectivo;
	}
	/**
	 * @param shvValBoletaDepositoEfectivo the shvValBoletaDepositoEfectivo to set
	 */
	public void setShvValBoletaDepositoEfectivo(
			Set<ShvValBoletaDepositoEfectivo> shvValBoletaDepositoEfectivo) {
		this.shvValBoletaDepositoEfectivo = shvValBoletaDepositoEfectivo;
	}
	/**
	 * @return the shvValValorRetencion
	 */
	public Set<ShvValValorRetencion> getShvValValorRetencion() {
		return shvValValorRetencion;
	}
	/**
	 * @param shvValValorRetencion the shvValValorRetencion to set
	 */
	public void setShvValValorRetencion(
			Set<ShvValValorRetencion> shvValValorRetencion) {
		this.shvValValorRetencion = shvValValorRetencion;
	}
	/**
	 * @return the valorEfectivo
	 */
	public Set<ShvValValorEfectivo> getValorEfectivo() {
		return valorEfectivo;
	}
	/**
	 * @param valorEfectivo the valorEfectivo to set
	 */
	public void setValorEfectivo(Set<ShvValValorEfectivo> valorEfectivo) {
		this.valorEfectivo = valorEfectivo;
	}
	/**
	 * @return the valorCheque
	 */
	public Set<ShvValValorCheque> getValorCheque() {
		return valorCheque;
	}
	/**
	 * @param valorCheque the valorCheque to set
	 */
	public void setValorCheque(Set<ShvValValorCheque> valValorCheque) {
		this.valorCheque = valValorCheque;
	}
	/**
	 * @return the valorChequePagoDiferido
	 */
	public Set<ShvValValorChequePagoDiferido> getValorChequePagoDiferido() {
		return valorChequePagoDiferido;
	}
	/**
	 * @param valorChequePagoDiferido the valorChequePagoDiferido to set
	 */
	public void setValorChequePagoDiferido(
			Set<ShvValValorChequePagoDiferido> valorChequePagoDiferido) {
		this.valorChequePagoDiferido = valorChequePagoDiferido;
	}
}
