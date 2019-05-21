package ar.com.telecom.shiva.persistencia.modelo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@Table(name = "SHV_VAL_BOLETA_DEP_EFECTIVO")
public class ShvValBoletaDepositoEfectivo extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_VALOR")
	@GeneratedValue(generator = "SEQ_SHV_VAL_VALOR")
	@GenericGenerator(name = "SEQ_SHV_VAL_VALOR", strategy = "foreign", parameters = @Parameter(name = "property", value = "valor"))
	private Long idValor;

	@OneToOne
	@PrimaryKeyJoinColumn
	private ShvValValor valor;

	@Column(name = "FECHA_DEPOSITO")
	private Date fechaDeposito;

	@Column(name = "FECHA_RECIBO")
	private Date fechaRecibo;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "ID_RECIBO_PREIMPRESO", referencedColumnName = "ID_RECIBO_PREIMPRESO")
	private ShvTalReciboPreImpreso reciboPreImpreso;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumns({ @JoinColumn(name = "ID_BOLETA", referencedColumnName = "ID_BOLETA", nullable = false) })
	private ShvBolBoleta boleta;

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

	public Date getFechaDeposito() {
		return fechaDeposito;
	}

	public void setFechaDeposito(Date fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}

	public Date getFechaRecibo() {
		return fechaRecibo;
	}

	public void setFechaRecibo(Date fechaRecibo) {
		this.fechaRecibo = fechaRecibo;
	}

	public ShvTalReciboPreImpreso getReciboPreImpreso() {
		return reciboPreImpreso;
	}

	public void setReciboPreImpreso(ShvTalReciboPreImpreso reciboPreImpreso) {
		this.reciboPreImpreso = reciboPreImpreso;
	}

	public ShvBolBoleta getBoleta() {
		return boleta;
	}

	public void setBoleta(ShvBolBoleta boleta) {
		this.boleta = boleta;
	}

}
