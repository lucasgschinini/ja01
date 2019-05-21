package ar.com.telecom.shiva.persistencia.modelo.param;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.negocio.servicios.bean.ErrorBean;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;

/**
 * @author u578936 M.A.Uehara
 *
 */
@Entity
@Table(name = "SHV_PARAM_CONF_CAMPO")
public class ShvParamConfCampo extends Modelo {
	private static final long serialVersionUID = 20180519L;

	@Id
	@Column (name="ID_CONF_CAMPO")
	private Long idConfCampo;
	@Column (name="NOMBRE")
	private String nombre;
	@Column (name="TIPO_CAMPO")
	private String tipoCampo;
	@Enumerated(EnumType.STRING)
	@Column (name="CAMPO_IMPORTACION")
	private SiNoEnum campoImportacion;
	@Enumerated(EnumType.STRING)
	@Column (name="ACTIVO")
	private SiNoEnum activo;
	@Column (name="ORDEN_CAMPOS")
	private Long ordenCampos;
	
	public ShvParamConfCampo() {
	}

	@Transient
	public List<ErrorBean> listaErrores = new ArrayList<ErrorBean>();

	/**
	 * @return the idConfCampo
	 */
	public Long getIdConfCampo() {
		return idConfCampo;
	}
	/**
	 * @param idConfCampo the idConfCampo to set
	 */
	public void setIdConfCampo(Long idConfCampo) {
		this.idConfCampo = idConfCampo;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the tipoDeDato
	 */
	public String getTipoDeDato() {
		return tipoCampo;
	}
	/**
	 * @param tipoDeDato the tipoDeDato to set
	 */
	public void setTipoDeDato(String tipoDeDato) {
		this.tipoCampo = tipoDeDato;
	}
	/**
	 * @return the activo
	 */
	public SiNoEnum getActivo() {
		return activo;
	}
	/**
	 * @param activo the activo to set
	 */
	public void setActivo(SiNoEnum activo) {
		this.activo = activo;
	}

	/**
	 * @return the campoImportacion
	 */
	public SiNoEnum getCampoImportacion() {
		return campoImportacion;
	}

	/**
	 * @param campoImportacion the campoImportacion to set
	 */
	public void setCampoImportacion(SiNoEnum campoImportacion) {
		this.campoImportacion = campoImportacion;
	}

	/**
	 * @return the ordenCampos
	 */
	public Long getOrdenCampos() {
		return ordenCampos;
	}

	/**
	 * @param ordenCampos the ordenCampos to set
	 */
	public void setOrdenCampos(Long ordenCampos) {
		this.ordenCampos = ordenCampos;
	}
//	/**
//	 * @return the listaErrores
//	 */
//	public List<StringBuffer> getListaErrores() {
//		return listaErrores;
//	}
//	/**
//	 * @param listaErrores the listaErrores to set
//	 */
//	public void setListaErrores(List<StringBuffer> listaErrores) {
//		this.listaErrores = listaErrores;
//	}
//
//	public String obtenerError() {
//		StringBuffer stre = new StringBuffer();
//		for (StringBuffer str : listaErrores) {
//			if (str.length() > 0) {
//				stre.append(str.toString());
//			}
//		}
//		return stre.toString();
//	}
	/**
	 * @return the listaErrores
	 */
	public List<ErrorBean> getListaErrores() {
		return listaErrores;
	}
	
	/**
	 * @param listaErrores the listaErrores to set
	 */
	public void setListaErrores(List<ErrorBean> listaErrores) {
		this.listaErrores = listaErrores;
	}

	public String obtenerError() {
		StringBuffer stre = new StringBuffer();
		Collections.sort(listaErrores, new Comparator<ErrorBean>() {
			@Override
			public int compare(ErrorBean o1, ErrorBean o2) {
				int salid = o1.getPrioridad().compareTo(o2.getPrioridad());
				if (salid > 0) {
					salid = 1;
				} else if (salid < 0) {
					salid = -1;
				} 
				return salid;
			}
		});
		for (ErrorBean str : listaErrores) {
			if (str.getError().length() > 0) {
				stre.append(str.getError());
			}
		}
		return stre.toString();
	}
	
	public void addError(int prioridad, String error) {
		ErrorBean errorBean = new ErrorBean();
		errorBean.setError(error);
		errorBean.setPrioridad(prioridad);
		this.getListaErrores().add(errorBean);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShvParamConfCampo other = (ShvParamConfCampo) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ShvParamConfCampo [idConfCampo=" + idConfCampo + ", nombre="
				+ nombre + ", tipoCampo=" + tipoCampo + ", campoImportacion="
				+ campoImportacion + ", activo=" + activo + ", ordenCampos="
				+ ordenCampos + ", listaErrores=" + listaErrores + "]";
	}
	
	

}
