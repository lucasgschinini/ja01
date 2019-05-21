package ar.com.telecom.shiva.persistencia.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "SHV_VAL_VALOR_INTERDEPOSITO")
public class ShvValValorInterdeposito extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_VALOR")
	@GeneratedValue(generator = "SEQ_SHV_VAL_VALOR")
	@GenericGenerator(name = "SEQ_SHV_VAL_VALOR", strategy = "foreign", parameters = @Parameter(name = "property", value = "valor"))
	private Long idValor;

	@OneToOne
	@PrimaryKeyJoinColumn
	private ShvValValor valor;

	@Column(name = "NUMERO_INTERDEPOSITO")
	private Long numeroInterdeposito;

	@Column(name = "CODIGO_ORGANISMO")
	private String codigoOrganismo;

	@Column(name = "FECHA_INTERDEPOSITO")
	private Date fechaInterdeposito;

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

	public Long getNumeroInterdeposito() {
		return numeroInterdeposito;
	}

	public void setNumeroInterdeposito(Long numeroInterdeposito) {
		this.numeroInterdeposito = numeroInterdeposito;
	}

	public String getCodigoOrganismo() {
		return codigoOrganismo;
	}

	public void setCodigoOrganismo(String codigoOrganismo) {
		this.codigoOrganismo = codigoOrganismo;
	}

	public Date getFechaInterdeposito() {
		return fechaInterdeposito;
	}

	public void setFechaInterdeposito(Date fechaInterdeposito) {
		this.fechaInterdeposito = fechaInterdeposito;
	}
}
