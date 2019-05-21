package ar.com.telecom.shiva.persistencia.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBanco;

@Entity
@Table(name = "SHV_VAL_VALOR_CHEQUE_PD")
public class ShvValValorChequePagoDiferido extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_VALOR")
	@GeneratedValue(generator = "SEQ_SHV_VAL_VALOR")
	@GenericGenerator(name = "SEQ_SHV_VAL_VALOR", strategy = "foreign", parameters = @Parameter(name = "property", value = "valor"))
	private Long idValor;

	@OneToOne
	@PrimaryKeyJoinColumn
	private ShvValValor valor;

	@Column(name = "NUMERO_CHEQUE")
	private Long numeroCheque;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumns({ @JoinColumn(name = "ID_BANCO_ORIGEN", referencedColumnName = "ID_BANCO") })
	private ShvParamBanco bancoOrigen;

	@Column(name = "FECHA_DEPOSITO")
	private Date fechaDeposito;

	@Column(name = "FECHA_VENCIMIENTO")
	private Date fechaVencimiento;

	@Column(name = "NUMERO_BOLETA")
	private Long numeroBoleta;

	@Enumerated(EnumType.STRING)
	@Column(name = "DOCUMENTACION_ORIG_RECIBIDA", nullable = false)
	private SiNoEnum documentacionOriginalRecibida;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "ID_RECIBO_PREIMPRESO", referencedColumnName = "ID_RECIBO_PREIMPRESO")
	private ShvTalReciboPreImpreso reciboPreImpreso;

	@Column(name = "FECHA_RECIBO")
	private Date fechaRecibo;
	
	@Column(name = "FECHA_EMISION")
	private Date fechaEmision;
	
	public Long getIdValor() {
		return idValor;
	}

	public void setIdValor(Long idValor) {
		this.idValor = idValor;
	}

	public ShvValValor getValor() {
		return valor;
	}

	public void setValor(ShvValValor valor) {
		this.valor = valor;
	}

	public Long getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(Long numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public ShvParamBanco getBancoOrigen() {
		return bancoOrigen;
	}

	public void setBancoOrigen(ShvParamBanco bancoOrigen) {
		this.bancoOrigen = bancoOrigen;
	}

	public Date getFechaDeposito() {
		return fechaDeposito;
	}

	public void setFechaDeposito(Date fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @return the numeroBoleta
	 */
	public Long getNumeroBoleta() {
		return numeroBoleta;
	}

	/**
	 * @param numeroBoleta the numeroBoleta to set
	 */
	public void setNumeroBoleta(Long numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}

	/**
	 * @return the documentacionOriginalRecibida
	 */
	public SiNoEnum getDocumentacionOriginalRecibida() {
		return documentacionOriginalRecibida;
	}

	/**
	 * @param documentacionOriginalRecibida the documentacionOriginalRecibida to set
	 */
	public void setDocumentacionOriginalRecibida(
			SiNoEnum documentacionOriginalRecibida) {
		this.documentacionOriginalRecibida = documentacionOriginalRecibida;
	}
	
	/**
	 * @return the reciboPreImpreso
	 */
	public ShvTalReciboPreImpreso getReciboPreImpreso() {
		return reciboPreImpreso;
	}
	
	/**
	 * @param reciboPreImpreso the reciboPreImpreso to set
	 */
	public void setReciboPreImpreso(ShvTalReciboPreImpreso reciboPreImpreso) {
		this.reciboPreImpreso = reciboPreImpreso;
	}

	/**
	 * @return the fechaRecibo
	 */
	public Date getFechaRecibo() {
		return fechaRecibo;
	}

	/**
	 * @param fechaRecibo the fechaRecibo to set
	 */
	public void setFechaRecibo(Date fechaRecibo) {
		this.fechaRecibo = fechaRecibo;
	}

	/**
	 * @return the fechaEmision
	 */
	public Date getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	
}
