package ar.com.telecom.shiva.persistencia.modelo.param;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_TIPO_VALOR")
public class ShvParamTipoValor extends Modelo{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name="ID_TIPO_VALOR")
	private Long idTipoValor;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, mappedBy = "tipoValor")
	private Set<ShvParamValorMedioPago> valorMedioPago = new HashSet<ShvParamValorMedioPago>(0);
	
	@Column(name="DESCRIPCION") 
	private String descripcion;
	
	@Column (name="TIPO_CONCILIACION")
	private String tipoConciliacion;
	
	@Column (name="TIPO_TIPO_VALOR")
	private String tipoTipoValor;
	
	

	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	
	public Long getIdTipoValor() {
		return idTipoValor;
	}

	public void setIdTipoValor(Long idTipoValor) {
		this.idTipoValor = idTipoValor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipoConciliacion() {
		return tipoConciliacion;
	}

	public void setTipoConciliacion(String tipoConciliacion) {
		this.tipoConciliacion = tipoConciliacion;
	}

	public String getTipoTipoValor() {
		return tipoTipoValor;
	}

	public void setTipoTipoValor(String tipoTipoValor) {
		this.tipoTipoValor = tipoTipoValor;
	}

	public Set<ShvParamValorMedioPago> getValorMedioPago() {
		return valorMedioPago;
	}

	public void setValorMedioPago(Set<ShvParamValorMedioPago> valorMedioPago) {
		this.valorMedioPago = valorMedioPago;
	}
}
