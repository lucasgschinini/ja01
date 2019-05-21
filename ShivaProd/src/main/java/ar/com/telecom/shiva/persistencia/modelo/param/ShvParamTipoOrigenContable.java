package ar.com.telecom.shiva.persistencia.modelo.param;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SHV_PARAM_TIPO_ORIGEN_CONTABLE")
public class ShvParamTipoOrigenContable {
	
	@Id
	@Column (name="ID_TIPO_ORIGEN_CONTABLE")
	private String idTipoOrigenContable;
	@Column (name="DESCRIPCION")
	private String descripcion;
	
	
	/**
	 * @return the idTipoOrigenContable
	 */
	public String getIdTipoOrigenContable() {
		return idTipoOrigenContable;
	}
	/**
	 * @param idTipoOrigenContable the idTipoOrigenContable to set
	 */
	public void setIdTipoOrigenContable(String idTipoOrigenContable) {
		this.idTipoOrigenContable = idTipoOrigenContable;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
