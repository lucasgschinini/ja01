package ar.com.telecom.shiva.batch.springbatch.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcArchivoAvc;
import ar.com.telecom.shiva.persistencia.modelo.ShvWfWorkflow;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoValor;

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
@Table(name = "SHV_AVC_REGISTRO_AVC")
@Inheritance(strategy = InheritanceType.JOINED)
public class AvcRegistroAvc extends Modelo {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SHV_AVC_REGISTRO_AVC")
	@SequenceGenerator(name="SEQ_SHV_AVC_REGISTRO_AVC", sequenceName="SEQ_SHV_AVC_REGISTRO_AVC", allocationSize = 1)
	@Column(name="ID_REGISTRO_AVC")
	private Long idRegistroAvc;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_ARCHIVO_AVC", referencedColumnName="ID_ARCHIVO_AVC") 
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvAvcArchivoAvc archivoAvc;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_WORKFLOW", referencedColumnName="ID_WORKFLOW")
	private ShvWfWorkflow workFlow;
	
	@Column(name="IMPORTE") 
	private BigDecimal importe;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_ACUERDO")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamAcuerdo acuerdo;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_TIPO_VALOR")
	@NotFound(action=NotFoundAction.IGNORE)
	private ShvParamTipoValor tipoValor;
	
	@Column(name="CODIGO_CLIENTE")
	private String codigoCliente;
	
	@Column(name="OBSERVACION_ANULACION")
	private String observacionAnulacion;

	@Column(name="OBSERVACION_CONFIRMAR_ANUL")
	private String observacionConfirmarAnulacion;
	
	@Column(name="OBSERVACIONES")
	private String observaciones;
	
	@Column(name="RAZON_SOCIAL_CLIENTE_PERFIL")
	private String razonSocialClientePerfil;
	
	@Column(name="ID_CLIENTE_PERFIL")
	private Long idClientePerfil;
	
	@Column(name="FECHA_DERIVACION")
	private Date fechaDerivacion;
	
	@Column(name="ANALISTA_DERIVACION")
	private String analistaDerivacion;

	/**
	 * @return the idRegistroAvc
	 */
	public Long getIdRegistroAvc() {
		return idRegistroAvc;
	}

	/**
	 * @param idRegistroAvc the idRegistroAvc to set
	 */
	public void setIdRegistroAvc(Long idRegistroAvc) {
		this.idRegistroAvc = idRegistroAvc;
	}

	/**
	 * @return the archivoAvc
	 */
	public ShvAvcArchivoAvc getArchivoAvc() {
		return archivoAvc;
	}

	/**
	 * @param archivoAvc the archivoAvc to set
	 */
	public void setArchivoAvc(ShvAvcArchivoAvc archivoAvc) {
		this.archivoAvc = archivoAvc;
	}

	/**
	 * @return the workFlow
	 */
	public ShvWfWorkflow getWorkFlow() {
		return workFlow;
	}

	/**
	 * @param workFlow the workFlow to set
	 */
	public void setWorkFlow(ShvWfWorkflow workFlow) {
		this.workFlow = workFlow;
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
	 * @return the acuerdo
	 */
	public ShvParamAcuerdo getAcuerdo() {
		return acuerdo;
	}

	/**
	 * @param acuerdo the acuerdo to set
	 */
	public void setAcuerdo(ShvParamAcuerdo acuerdo) {
		this.acuerdo = acuerdo;
	}

	/**
	 * @return the tipoValor
	 */
	public ShvParamTipoValor getTipoValor() {
		return tipoValor;
	}

	/**
	 * @param tipoValor the tipoValor to set
	 */
	public void setTipoValor(ShvParamTipoValor tipoValor) {
		this.tipoValor = tipoValor;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the observacionAnulacion
	 */
	public String getObservacionAnulacion() {
		return observacionAnulacion;
	}

	/**
	 * @param observacionAnulacion the observacionAnulacion to set
	 */
	public void setObservacionAnulacion(String observacionAnulacion) {
		this.observacionAnulacion = observacionAnulacion;
	}

	/**
	 * @return the observacionConfirmarAnulacion
	 */
	public String getObservacionConfirmarAnulacion() {
		return observacionConfirmarAnulacion;
	}

	/**
	 * @param observacionConfirmarAnulacion the observacionConfirmarAnulacion to set
	 */
	public void setObservacionConfirmarAnulacion(
			String observacionConfirmarAnulacion) {
		this.observacionConfirmarAnulacion = observacionConfirmarAnulacion;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the razonSocialClientePerfil
	 */
	public String getRazonSocialClientePerfil() {
		return razonSocialClientePerfil;
	}

	/**
	 * @param razonSocialClientePerfil the razonSocialClientePerfil to set
	 */
	public void setRazonSocialClientePerfil(String razonSocialClientePerfil) {
		this.razonSocialClientePerfil = razonSocialClientePerfil;
	}

	/**
	 * @return the idClientePerfil
	 */
	public Long getIdClientePerfil() {
		return idClientePerfil;
	}

	/**
	 * @param idClientePerfil the idClientePerfil to set
	 */
	public void setIdClientePerfil(Long idClientePerfil) {
		this.idClientePerfil = idClientePerfil;
	}

	/**
	 * @return the fechaDerivacion
	 */
	public Date getFechaDerivacion() {
		return fechaDerivacion;
	}

	/**
	 * @param fechaDerivacion the fechaDerivacion to set
	 */
	public void setFechaDerivacion(Date fechaDerivacion) {
		this.fechaDerivacion = fechaDerivacion;
	}

	/**
	 * @return the analistaDerivacion
	 */
	public String getAnalistaDerivacion() {
		return analistaDerivacion;
	}

	/**
	 * @param analistaDerivacion the analistaDerivacion to set
	 */
	public void setAnalistaDerivacion(String analistaDerivacion) {
		this.analistaDerivacion = analistaDerivacion;
	}

	@Override
	public String toString() {
		return "AvcRegistroAvc [idRegistroAvc=" + idRegistroAvc
				+ ", archivoAvc=" + archivoAvc + ", workFlow=" + workFlow
				+ ", importe=" + importe + ", acuerdo=" + acuerdo
				+ ", tipoValor=" + tipoValor + ", codigoCliente="
				+ codigoCliente + ", observacionAnulacion="
				+ observacionAnulacion + ", observacionConfirmarAnulacion="
				+ observacionConfirmarAnulacion + ", observaciones="
				+ observaciones + ", razonSocialClientePerfil="
				+ razonSocialClientePerfil + ", idClientePerfil="
				+ idClientePerfil + ", fechaDerivacion=" + fechaDerivacion
				+ ", analistaDerivacion=" + analistaDerivacion + "]";
	}
	
	
	
	
}
