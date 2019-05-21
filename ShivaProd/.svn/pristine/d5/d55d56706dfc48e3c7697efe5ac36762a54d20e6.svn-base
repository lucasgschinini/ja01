package ar.com.telecom.shiva.persistencia.modelo.simple;

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

import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;

@Entity
@Table(name = "SHV_TAL_RECIBO_PREIMPRESO")
public class ShvTalReciboPreImpresoCompensacionSimplificado extends Modelo{

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_TAL_RECIBO_PREIMPRESO")
	@SequenceGenerator(name="SEQ_SHV_TAL_RECIBO_PREIMPRESO", sequenceName="SEQ_SHV_TAL_RECIBO_PREIMPRESO", allocationSize = 1)
	@Column(name="ID_RECIBO_PREIMPRESO")
	private 	Integer idReciboPreimpreso;

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
	
	@OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
	@JoinColumn(name="ID_RECIBO_PREIMPRESO", referencedColumnName="ID_RECIBO_PREIMPRESO")
	@NotFound(action=NotFoundAction.IGNORE)
	private Set<ShvTalCompensacionSimplificado> shvTalCompensacionSimplificado;
	
	/******************************************
	 *           Getters & Setters            *
	 *****************************************/
	
	
	public ShvWfWorkflow getWorkflow() {
		return workflow;
	}
	public void setWorkflow(ShvWfWorkflow workflow) {
		this.workflow = workflow;
	}
	

	public Set<ShvTalCompensacionSimplificado> getShvTalCompensacionSimplificado() {
		return shvTalCompensacionSimplificado;
	}
	public void setShvTalCompensacionSimplificado(Set<ShvTalCompensacionSimplificado> shvTalCompensacionSimplificado) {
		this.shvTalCompensacionSimplificado = shvTalCompensacionSimplificado;
	}

	public Integer getIdReciboPreimpreso() {
		return idReciboPreimpreso;
	}
	public void setIdReciboPreimpreso(Integer idReciboPreimpreso) {
		this.idReciboPreimpreso = idReciboPreimpreso;
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
}
