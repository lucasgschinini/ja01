package ar.com.telecom.shiva.persistencia.modelo.param;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_VALOR_MEDIO_PAGO")
public class ShvParamValorMedioPago extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_VALOR_MEDIO_PAGO")
	private Long idValorMedioPago;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_TIPO_VALOR" , nullable=true) 
	private ShvParamTipoValor tipoValor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_TIPO_MEDIO_PAGO") 
	private ShvParamTipoMedioPago tipoMedioPago;
	
	@Column(name="ID_SUB_TIPO_VALOR", nullable=true)
	private Long idSubTipoValor;
	
	public ShvParamTipoValor getTipoValor() {
		return tipoValor;
	}

	public void setTipoValor(ShvParamTipoValor tipoValor) {
		this.tipoValor = tipoValor;
	}

	public ShvParamTipoMedioPago getTipoMedioPago() {
		return tipoMedioPago;
	}

	public void setTipoMedioPago(ShvParamTipoMedioPago tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}

	public Long getIdValorMedioPago() {
		return idValorMedioPago;
	}

	public void setIdValorMedioPago(Long idValorMedioPago) {
		this.idValorMedioPago = idValorMedioPago;
	}

	public Long getIdSubTipoValor() {
		return idSubTipoValor;
	}

	public void setIdSubTipoValor(Long idSubTipoValor) {
		this.idSubTipoValor = idSubTipoValor;
	}

	
	
}
