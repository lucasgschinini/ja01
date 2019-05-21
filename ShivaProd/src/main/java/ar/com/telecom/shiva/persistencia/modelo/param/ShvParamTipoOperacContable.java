package ar.com.telecom.shiva.persistencia.modelo.param;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SHV_PARAM_TIPO_OPERAC_CONTABLE")
public class ShvParamTipoOperacContable {
	
	@Id
	@Column (name="ID_TIPO_OPERAC_CONTABLE")
	private String idTipoOperacContable;
	@Column (name="GRUPO_FUNCIONAL")
	private String grupoFuncional;
	@Column (name="DESCRIPCION")
	private String descripcion;
	/**
	 * @return the idTipoOperacContable
	 */
	public String getIdTipoOperacContable() {
		return idTipoOperacContable;
	}
	/**
	 * @param idTipoOperacContable the idTipoOperacContable to set
	 */
	public void setIdTipoOperacContable(String idTipoOperacContable) {
		this.idTipoOperacContable = idTipoOperacContable;
	}
	/**
	 * @return the grupoFuncional
	 */
	public String getGrupoFuncional() {
		return grupoFuncional;
	}
	/**
	 * @param grupoFuncional the grupoFuncional to set
	 */
	public void setGrupoFuncional(String grupoFuncional) {
		this.grupoFuncional = grupoFuncional;
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
