package ar.com.telecom.shiva.persistencia.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ar.com.telecom.shiva.base.enumeradores.EnviarMailBoletaEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.ImprimirBoletaEstadoEnum;

@Entity
@Table(name = "SHV_BOL_BOLETA")
public class ShvBolBoleta extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_BOL_BOLETA")
	@SequenceGenerator(name = "SEQ_SHV_BOL_BOLETA", sequenceName = "SEQ_SHV_BOL_BOLETA", allocationSize = 1)
	@Column(name = "ID_BOLETA", nullable = false)
	private Long idBoleta;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_WORKFLOW", referencedColumnName = "ID_WORKFLOW", nullable = false)
	private ShvWfWorkflow workFlow;

	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "boleta")
	private ShvBolBoletaSinValor boletaSinValor;

	@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "boleta")
	private ShvBolBoletaConValor boletaConValor;

	@Column(name = "NUMERO_BOLETA", nullable = false)
	private Long numeroBoleta;

	@Column(name = "EMAIL")
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(name = "IMPRESION_ESTADO", nullable = false)
	private ImprimirBoletaEstadoEnum impresionEstado;

	@Column(name = "IMPRESION_USUARIO")
	private String impresionUsuario;

	@Column(name = "IMPRESION_FECHA")
	private Date impresionFecha;

	@Enumerated(EnumType.STRING)
	@Column(name = "EMAIL_ENVIO_ESTADO", nullable = false)
	private EnviarMailBoletaEstadoEnum emailEnvioEstado;

	@Column(name = "EMAIL_ENVIO_OBSERVACIONES")
	private String emailEnvioObservaciones;

	@Column(name = "EMAIL_ENVIO_FECHA")
	private Date emailEnvioFecha;

	@Column(name = "EMAIL_ENVIO_DESTINO")
	private String emailEnvioDestino;

	@Column(name = "EMAIL_ENVIO_USUARIO")
	private String emailEnvioUsuario;
	
	@Column(name="EMAIL_OBSERVACIONES")
	private String emailObservaciones;
	
	@Column(name="FECHA_ALTA")	 			
	private Date fechaAlta;
	
	public Long getIdBoleta() {
		super.setId(idBoleta);
		return idBoleta;
	}

	public void setIdBoleta(Long idBoleta) {
		this.idBoleta = idBoleta;
	}

	public ShvWfWorkflow getWorkFlow() {
		return workFlow;
	}

	public void setWorkFlow(ShvWfWorkflow workFlow) {
		this.workFlow = workFlow;
	}

	public ShvBolBoletaSinValor getBoletaSinValor() {
		return boletaSinValor;
	}

	public void setBoletaSinValor(ShvBolBoletaSinValor boletaSinValor) {
		this.boletaSinValor = boletaSinValor;
	}

	public ShvBolBoletaConValor getBoletaConValor() {
		return boletaConValor;
	}

	public void setBoletaConValor(ShvBolBoletaConValor boletaConValor) {
		this.boletaConValor = boletaConValor;
	}

	public Long getNumeroBoleta() {
		return numeroBoleta;
	}

	public void setNumeroBoleta(Long numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ImprimirBoletaEstadoEnum getImpresionEstado() {
		return impresionEstado;
	}

	public void setImpresionEstado(ImprimirBoletaEstadoEnum impresionEstado) {
		this.impresionEstado = impresionEstado;
	}

	public String getImpresionUsuario() {
		return impresionUsuario;
	}

	public void setImpresionUsuario(String impresionUsuario) {
		this.impresionUsuario = impresionUsuario;
	}

	public Date getImpresionFecha() {
		return impresionFecha;
	}

	public void setImpresionFecha(Date impresionFecha) {
		this.impresionFecha = impresionFecha;
	}

	public EnviarMailBoletaEstadoEnum getEmailEnvioEstado() {
		return emailEnvioEstado;
	}

	public void setEmailEnvioEstado(EnviarMailBoletaEstadoEnum emailEnvioEstado) {
		this.emailEnvioEstado = emailEnvioEstado;
	}

	public String getEmailEnvioObservaciones() {
		return emailEnvioObservaciones;
	}

	public void setEmailEnvioObservaciones(String emailEnvioObservaciones) {
		this.emailEnvioObservaciones = emailEnvioObservaciones;
	}

	public Date getEmailEnvioFecha() {
		return emailEnvioFecha;
	}

	public void setEmailEnvioFecha(Date emailEnvioFecha) {
		this.emailEnvioFecha = emailEnvioFecha;
	}

	public String getEmailEnvioDestino() {
		return emailEnvioDestino;
	}

	public void setEmailEnvioDestino(String emailEnvioDestino) {
		this.emailEnvioDestino = emailEnvioDestino;
	}

	public String getEmailEnvioUsuario() {
		return emailEnvioUsuario;
	}

	public void setEmailEnvioUsuario(String emailEnvioUsuario) {
		this.emailEnvioUsuario = emailEnvioUsuario;
	}

	public String getEmailObservaciones() {
		return emailObservaciones;
	}

	public void setEmailObservaciones(String emailObservaciones) {
		this.emailObservaciones = emailObservaciones;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
}
