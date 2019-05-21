package ar.com.telecom.shiva.persistencia.modelo.param;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.telecom.shiva.persistencia.modelo.Modelo;

@Entity
@Table(name = "SHV_PARAM_BANCO_CUENTA")
public class ShvParamBancoCuenta extends Modelo {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_BANCO_CUENTA", nullable = false)
	private Integer idBancoCuenta;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumns({ @JoinColumn(name = "ID_BANCO", referencedColumnName = "ID_BANCO", nullable = false) })
	private ShvParamBanco banco;

	@Column(name = "CUENTA_BANCARIA", nullable = false)
	private String cuentaBancaria;

	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/
	public Integer getIdBancoCuenta() {
		return idBancoCuenta;
	}

	public void setIdBancoCuenta(Integer idBancoCuenta) {
		this.idBancoCuenta = idBancoCuenta;
	}

	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	public ShvParamBanco getBanco() {
		return banco;
	}

	public void setBanco(ShvParamBanco banco) {
		this.banco = banco;
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((idBancoCuenta == null) ? 0 : idBancoCuenta.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShvParamBancoCuenta other = (ShvParamBancoCuenta) obj;
		if (idBancoCuenta == null) {
			if (other.idBancoCuenta != null)
				return false;
		} else if (!idBancoCuenta.equals(other.idBancoCuenta))
			return false;
		return true;
	}
	
}
