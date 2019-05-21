package ar.com.telecom.shiva.persistencia.modelo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "SHV_SEG_ACCION")
public class ShvSegAccion extends Modelo {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@Column(name="ID_SEG_ACCION")
	private Integer idSegAccion;
	
	@Column(name="FUNCIONALIDAD")
	private String funcionalidad;
	
	@Column(name="ID_ACCION")
	private Integer idAccion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MENU", nullable=false)
	private ShvMnuMenu menu;
	
	@Column(name="TIPO_ACCION")
	private String tipoAccion;
	
	@Column(name="DESCRIPCION_ACCION")
	private String descripcionAccion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERFIL_APLICATIVO", nullable=false)
	private ShvSegPerfilAplicativo perfilAplicativo;

	@Column(name="TIPO_TAREA")
	private String tipoTarea;

	@Column(name="ASIGNADO_ACCION")
	private String asignadoAccion;
	
	@Column(name="ID_SOCIEDAD")
	private String idSociedad;
	
	@Column(name="SISTEMA")
	private String sistema;
	

	
	/**
	 * @return the idSegAccion
	 */
	public Integer getIdSegAccion() {
		return idSegAccion;
	}

	/**
	 * @param idSegAccion the idSegAccion to set
	 */
	public void setIdSegAccion(Integer idSegAccion) {
		this.idSegAccion = idSegAccion;
	}

	/**
	 * @return the idAccion
	 */
	public Integer getIdAccion() {
		return idAccion;
	}

	/**
	 * @param idAccion the idAccion to set
	 */
	public void setIdAccion(Integer idAccion) {
		this.idAccion = idAccion;
	}

	/**
	 * @return the funcionalidad
	 */
	public String getFuncionalidad() {
		return funcionalidad;
	}

	/**
	 * @param funcionalidad the funcionalidad to set
	 */
	public void setFuncionalidad(String funcionalidad) {
		this.funcionalidad = funcionalidad;
	}

	/**
	 * @return the idMenu
	 */
	public ShvMnuMenu getMenu() {
		return menu;
	}

	/**
	 * @param idMenu the idMenu to set
	 */
	public void setMenu(ShvMnuMenu menu) {
		this.menu = menu;
	}

	/**
	 * @return the tipoAccion
	 */
	public String getTipoAccion() {
		return tipoAccion;
	}

	/**
	 * @param tipoAccion the tipoAccion to set
	 */
	public void setTipoAccion(String tipoAccion) {
		this.tipoAccion = tipoAccion;
	}

	/**
	 * @return the descripcionAccion
	 */
	public String getDescripcionAccion() {
		return descripcionAccion;
	}

	/**
	 * @param descripcionAccion the descripcionAccion to set
	 */
	public void setDescripcionAccion(String descripcionAccion) {
		this.descripcionAccion = descripcionAccion;
	}

	/**
	 * @return the tipoTarea
	 */
	public String getTipoTarea() {
		return tipoTarea;
	}

	/**
	 * @param tipoTarea the tipoTarea to set
	 */
	public void setTipoTarea(String tipoTarea) {
		this.tipoTarea = tipoTarea;
	}

	/**
	 * @return the asignadoAccion
	 */
	public String getAsignadoAccion() {
		return asignadoAccion;
	}

	/**
	 * @param asignadoAccion the asignadoAccion to set
	 */
	public void setAsignadoAccion(String asignadoAccion) {
		this.asignadoAccion = asignadoAccion;
	}

	/**
	 * @return the idSociedad
	 */
	public String getIdSociedad() {
		return idSociedad;
	}

	/**
	 * @param idSociedad the idSociedad to set
	 */
	public void setIdSociedad(String idSociedad) {
		this.idSociedad = idSociedad;
	}

	/**
	 * @return the sistema
	 */
	public String getSistema() {
		return sistema;
	}

	/**
	 * @param sistema the sistema to set
	 */
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	/**
	 * @return the idPerfilAplicativo
	 */
	public ShvSegPerfilAplicativo getPerfilAplicativo() {
		return perfilAplicativo;
	}

	/**
	 * @param idPerfilAplicativo the idPerfilAplicativo to set
	 */
	public void setPerfilAplicativo(ShvSegPerfilAplicativo perfilAplicativo) {
		this.perfilAplicativo = perfilAplicativo;
	}

	
	
}
