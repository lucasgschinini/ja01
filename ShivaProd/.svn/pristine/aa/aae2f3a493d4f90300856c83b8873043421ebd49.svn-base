package ar.com.telecom.shiva.negocio.servicios.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;

/**
 * @author u578936 M.A.Uehara
 *
 */
public class ConfReglasBean implements Serializable {
	private static final long serialVersionUID = 20180613L;

	private SiNoEnum validado;
	private String campoNombre;
	private Object valor;
	private List<ConfReglaBean> reglas = new ArrayList<ConfReglaBean>();
	private List<StringBuffer> listaErrores = new ArrayList<StringBuffer>();
	
	public ConfReglasBean() {
		validado = SiNoEnum.SI;
	}

	/**
	 * @return the validado
	 */
	public SiNoEnum getValidado() {
		return validado;
	}

	/**
	 * @param validado the validado to set
	 */
	public void setValidado(SiNoEnum validado) {
		this.validado = validado;
	}

	/**
	 * @return the campoNombre
	 */
	public String getCampoNombre() {
		return campoNombre;
	}

	/**
	 * @param campoNombre the campoNombre to set
	 */
	public void setCampoNombre(String campoNombre) {
		this.campoNombre = campoNombre;
	}

	/**
	 * @return the reglas
	 */
	public List<ConfReglaBean> getReglas() {
		return reglas;
	}

	/**
	 * @param reglas the reglas to set
	 */
	public void setReglas(List<ConfReglaBean> reglas) {
		this.reglas = reglas;
	}

	/**
	 * @return the listaErrores
	 */
	public List<StringBuffer> getListaErrores() {
		return listaErrores;
	}
	/**
	 * @param listaErrores the listaErrores to set
	 */
	public void setListaErrores(List<StringBuffer> listaErrores) {
		this.listaErrores = listaErrores;
	}
	
	public String getErrores() {
		StringBuffer str = new StringBuffer();
		for (StringBuffer error : this.listaErrores) {
			str.append(error.toString());
		}
		return str.toString();
	}

	/**
	 * @return the valor
	 */
	public Object getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(Object valor) {
		this.valor = valor;
	}
	
}
