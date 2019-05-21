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
@Table(name = "SHV_PARAM_ACUERDO")
public class ShvParamAcuerdo extends Modelo{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column (name="ID_ACUERDO")
	private Integer idAcuerdo;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumns({@JoinColumn(name="ID_BANCO_CUENTA", referencedColumnName="ID_BANCO_CUENTA")}) 
	private ShvParamBancoCuenta bancoCuenta;

	@Column (name="DESCRIPCION")
	private String descripcion;
	
	@Column (name="SECUENCIAL")
	private Integer secuencial;
	
	@Column (name="ES_CONCILIABLE")
	private char conciliable;

	
	/***************************************************************************
	 * GETTERS & SETTERS
	 * ************************************************************************/


	public Integer getIdAcuerdo() {
		return idAcuerdo;
	}

	public void setIdAcuerdo(Integer idAcuerdo) {
		this.idAcuerdo = idAcuerdo;
	}

	public ShvParamBancoCuenta getBancoCuenta() {
		return bancoCuenta;
	}

	public void setBancoCuenta(ShvParamBancoCuenta bancoCuenta) {
		this.bancoCuenta = bancoCuenta;
	}
	
	public Integer getSecuencial() {
		return secuencial;
	}

	public void setSecuencial(Integer secuencial) {
		this.secuencial = secuencial;
	}

	public char getConciliable() {
		return conciliable;
	}

	public void setConciliable(char conciliable) {
		this.conciliable = conciliable;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
