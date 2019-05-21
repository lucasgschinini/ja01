package ar.com.telecom.shiva.persistencia.modelo.param;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_PARAMETRO")
public class ShvParamParametro extends Modelo{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name="CLAVE")
	private String clave;
	
	@Column (name="VALOR_TEXTO")
	private String valorTexto;
	
	@Column (name="VALOR_NUMERICO")
	private Long valorNumerico;

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getValorTexto() {
		return valorTexto;
	}

	public void setValorTexto(String valorTexto) {
		this.valorTexto = valorTexto;
	}

	public Long getValorNumerico() {
		return valorNumerico;
	}

	public void setValorNumerico(Long valorNumerico) {
		this.valorNumerico = valorNumerico;
	}

}
