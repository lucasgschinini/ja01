package ar.com.telecom.shiva.base.mapeadores;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;

public interface IMapeadorUtil {
	
	/**
	 * Me permite almacenar atributos
	 * @param modelo
	 * @return
	 */
	public void setObjeto(Object cualquierAtributo) throws NegocioExcepcion;
	
}
