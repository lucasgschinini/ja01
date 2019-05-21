package ar.com.telecom.shiva.negocio.dto;

import ar.com.telecom.shiva.base.dto.DTO;
import ar.com.telecom.shiva.base.enumeradores.ConfFormObligatoriedadEnum;

/**
 * @author u578936 M.A.Uehara
 *
 */
public class ParamConfReglaCampo extends DTO{
	private static final long serialVersionUID = 20180519L;

	private String nombre;
	private String longitud;
	private String tipoDeDato;
	private String validacion;
	private String validacionMsg;
	private boolean selector;
	private ConfFormObligatoriedadEnum obligatoriedad;
	private String tipoCampo;

	public ParamConfReglaCampo() {
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
	 * @return the longitud
	 */
	public String getLongitud() {
		return longitud;
	}

	/**
	 * @param longitud the longitud to set
	 */
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	/**
	 * @return the tipoDeDato
	 */
	public String getTipoDeDato() {
		return tipoDeDato;
	}

	/**
	 * @param tipoDeDato the tipoDeDato to set
	 */
	public void setTipoDeDato(String tipoDeDato) {
		this.tipoDeDato = tipoDeDato;
	}

	/**
	 * @return the selector
	 */
	public boolean isSelector() {
		return selector;
	}

	/**
	 * @param selector the selector to set
	 */
	public void setSelector(boolean selector) {
		this.selector = selector;
	}

	/**
	 * @return the obligatoriedad
	 */
	public ConfFormObligatoriedadEnum getObligatoriedad() {
		return obligatoriedad;
	}

	/**
	 * @param obligatoriedad the obligatoriedad to set
	 */
	public void setObligatoriedad(ConfFormObligatoriedadEnum obligatoriedad) {
		this.obligatoriedad = obligatoriedad;
	}

	/**
	 * @return the validacion
	 */
	public String getValidacion() {
		return validacion;
	}

	/**
	 * @param validacion the validacion to set
	 */
	public void setValidacion(String validacion) {
		this.validacion = validacion;
	}

	/**
	 * @return the validacionMsg
	 */
	public String getValidacionMsg() {
		return validacionMsg;
	}

	/**
	 * @param validacionMsg the validacionMsg to set
	 */
	public void setValidacionMsg(String validacionMsg) {
		this.validacionMsg = validacionMsg;
	}

	/**
	 * @return the tipoCampo
	 */
	public String getTipoCampo() {
		return tipoCampo;
	}

	/**
	 * @param tipoCampo the tipoCampo to set
	 */
	public void setTipoCampo(String tipoCampo) {
		this.tipoCampo = tipoCampo;
	}
	
}
