package ar.com.telecom.shiva.persistencia.modelo;

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

/**
 * @author u529234
 *
 */
@Entity
@Table(name = "SHV_VAL_VALOR_POR_REVERSION")
public class ShvValValorPorReversion extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SHV_VAL_VALOR_REVERSION")
	@SequenceGenerator(name = "SEQ_SHV_VAL_VALOR_REVERSION", sequenceName = "SEQ_SHV_VAL_VALOR_REVERSION", allocationSize = 1)
	@Column(name = "ID_VALOR_POR_REVERSION")
	private Long idValorPorReversion;

	@Column(name ="ID_TIPO_VALOR")
	private Long idTipoValor;

	@Column(name ="ID_ACUERDO")
	private Long idAcuerdo;

	@Column(name = "IMPORTE")
	private BigDecimal importe;

	@Column(name = "SALDO_DISPONIBLE")
	private BigDecimal saldoDisponible;

	@Column(name = "NUMERO_REFERENCIA")
	private Long numeroReferencia;

	@Column(name ="ID_BANCO_ORIGEN")
	private String idBancoOrigen;
	
	@Column(name = "CODIGO_ORGANISMO")
	private String codigoOrganismo;
	
	@Column(name = "FECHA_DEPOSITO")
	private Date fechaDeposito;

	@Column(name = "NUMERO_BOLETA")
	private Long numeroBoleta;
	
	@Column(name = "FECHA_VENCIMIENTO")
	private Date fechaVencimiento;
	
	@Column(name = "NUMERO_CHEQUE")
	private Long numeroCheque;

	@Column(name = "CODIGO_INTERDEPOSITO")
	private Long codigoInterdeposito;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_WORKFLOW", referencedColumnName="ID_WORKFLOW")
	private ShvWfWorkflow workflow;

	
	public Long getIdValorPorReversion() {
		return idValorPorReversion;
	}

	public void setIdValorPorReversion(Long idValorPorReversion) {
		this.idValorPorReversion = idValorPorReversion;
	}

	public Long getIdTipoValor() {
		return idTipoValor;
	}

	public void setIdTipoValor(Long idTipoValor) {
		this.idTipoValor = idTipoValor;
	}

	public Long getIdAcuerdo() {
		return idAcuerdo;
	}

	public void setIdAcuerdo(Long idAcuerdo) {
		this.idAcuerdo = idAcuerdo;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public BigDecimal getSaldoDisponible() {
		return saldoDisponible;
	}

	public void setSaldoDisponible(BigDecimal saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	public Long getNumeroReferencia() {
		return numeroReferencia;
	}

	public void setNumeroReferencia(Long numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}

	public String getIdBancoOrigen() {
		return idBancoOrigen;
	}

	public void setIdBancoOrigen(String bancoOrigen) {
		this.idBancoOrigen = bancoOrigen;
	}

	public String getCodigoOrganismo() {
		return codigoOrganismo;
	}

	public void setCodigoOrganismo(String codigoOrganismo) {
		this.codigoOrganismo = codigoOrganismo;
	}

	public Date getFechaDeposito() {
		return fechaDeposito;
	}

	public void setFechaDeposito(Date fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}

	public Long getNumeroBoleta() {
		return numeroBoleta;
	}

	public void setNumeroBoleta(Long numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Long getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(Long numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public Long getCodigoInterdeposito() {
		return codigoInterdeposito;
	}

	public void setCodigoInterdeposito(Long codigoInterdeposito) {
		this.codigoInterdeposito = codigoInterdeposito;
	}

	public ShvWfWorkflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(ShvWfWorkflow workflow) {
		this.workflow = workflow;
	}

	
	
}
