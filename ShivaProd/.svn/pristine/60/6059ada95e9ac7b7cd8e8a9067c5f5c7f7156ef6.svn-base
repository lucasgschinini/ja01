package ar.com.telecom.shiva.base.excepciones;

import java.util.List;

public class ReportePerfilesExcepcion extends Excepcion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected List<String> listaErrores;
	private boolean tieneErrorEnArchivo;
	
	
	public ReportePerfilesExcepcion() {
		super();
	}

	public ReportePerfilesExcepcion(String message) {
		super(message);
	}

	public ReportePerfilesExcepcion(Throwable cause) {
		super(cause);
	}

	public ReportePerfilesExcepcion(String message, Throwable cause) {
		super(message, cause);
	}

	public ReportePerfilesExcepcion(String message, Throwable cause, List<String> lista) {
		super(message, cause);
		this.listaErrores = lista;
	}
	
	public ReportePerfilesExcepcion(String message, List<String> lista, boolean error) {
		super(message);
		this.listaErrores = lista;
		this.tieneErrorEnArchivo = error;
	}
	
	public List<String> getListaErrores() {
		return listaErrores;
	}

	public void setListaErrores(List<String> listaErrores) {
		this.listaErrores = listaErrores;
	}
	
	/**
	 * @return the tieneErrorEnArchivo
	 */
	public boolean isTieneErrorEnArchivo() {
		return tieneErrorEnArchivo;
	}

	/**
	 * @param tieneErrorEnArchivo the tieneErrorEnArchivo to set
	 */
	public void setTieneErrorEnArchivo(boolean tieneErrorEnArchivo) {
		this.tieneErrorEnArchivo = tieneErrorEnArchivo;
	}

}
