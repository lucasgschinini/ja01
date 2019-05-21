package ar.com.telecom.shiva.base.excepciones.otros;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;

public class ValidacionRegistroCotizacionSapExcepcion extends NegocioExcepcion {
	
	private static final long serialVersionUID = 1L;
	private List<String> listaErrores = new ArrayList<String>();
	/**
	 * @return the listaErrores
	 */
	public List<String> getListaErrores() {
		return listaErrores;
	}
	
	public ValidacionRegistroCotizacionSapExcepcion() {
		super();
	}

	public ValidacionRegistroCotizacionSapExcepcion(List<String> listaErrores) {
		super();
		this.listaErrores = listaErrores;
	}

	/**
	 * 
	 * @param message
	 */
	public ValidacionRegistroCotizacionSapExcepcion(String message) {
		super(message);
	}

	/**
	 * 
	 * @param message
	 */
	public ValidacionRegistroCotizacionSapExcepcion(List<String> listaErrores, String message) {
		super(message);
		this.listaErrores = listaErrores;
	}

}
