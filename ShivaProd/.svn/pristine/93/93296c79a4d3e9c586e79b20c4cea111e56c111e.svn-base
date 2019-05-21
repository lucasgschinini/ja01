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
@Table(name = "SHV_VAL_VALOR_TRANSFERENCIA")
public class ShvValValorTransferencia extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_VALOR")
	@GeneratedValue(generator = "SEQ_SHV_VAL_VALOR")
	@GenericGenerator(name = "SEQ_SHV_VAL_VALOR", strategy = "foreign", parameters = @Parameter(name = "property", value = "valor"))
	private Long idValor;

	@OneToOne
	@PrimaryKeyJoinColumn
	private ShvValValor valor;

	@Column(name = "NUMERO_REFERENCIA")
	private Long numeroReferencia;

	@Column(name = "CUIT")
	private String cuit;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumns({ @JoinColumn(name = "ID_BANCO_ORIGEN", referencedColumnName = "ID_BANCO") })
	private ShvParamBanco bancoOrigen;

	@Column(name = "FECHA_TRANSFERENCIA")
	private Date fechaTransferencia;

	@Enumerated(EnumType.STRING)
	@Column(name = "DOCUMENTACION_ORIG_RECIBIDA", nullable = false)
	private SiNoEnum documentacionOriginalRecibida;
	
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

	public Long getNumeroReferencia() {
		return numeroReferencia;
	}

	public void setNumeroReferencia(Long numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}

	public ShvParamBanco getBancoOrigen() {
		return bancoOrigen;
	}

	public void setBancoOrigen(ShvParamBanco bancoOrigen) {
		this.bancoOrigen = bancoOrigen;
	}

	public Date getFechaTransferencia() {
		return fechaTransferencia;
	}

	public void setFechaTransferencia(Date fechaTransferencia) {
		this.fechaTransferencia = fechaTransferencia;
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
	
	
}
