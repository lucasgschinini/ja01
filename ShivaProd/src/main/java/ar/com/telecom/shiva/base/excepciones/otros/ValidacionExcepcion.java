package ar.com.telecom.shiva.base.excepciones.otros;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;

public class ValidacionExcepcion extends NegocioExcepcion {
	
	private static final long serialVersionUID = 1L;
	
	private String campoError = "";
	private String codigoLeyenda = "";
	private String leyenda = "";
	private Object[] parametros;
	private List<String> camposError = new ArrayList<String>();
	private List<String> codigosLeyenda = new ArrayList<String>();
	
	public ValidacionExcepcion() {
		super();
	}
	
	public ValidacionExcepcion(String message) {
		super(message);
	}
	
	public ValidacionExcepcion(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ValidacionExcepcion(String codigoLeyenda, String leyenda) {
		this.codigoLeyenda = codigoLeyenda;
		this.leyenda = leyenda;
	}
	
	public ValidacionExcepcion(String codigoLeyenda, String leyenda, Object[] parametros) {
		this.codigoLeyenda = codigoLeyenda;
		this.leyenda = leyenda;
		this.parametros = parametros;
	}
	
	public ValidacionExcepcion(String codigoLeyenda, String leyenda, String campoError) {
		this.codigoLeyenda = codigoLeyenda;
		this.leyenda = leyenda;
		this.campoError = campoError;
	}
	
	public ValidacionExcepcion(List<String> camposError, List<String> codigosLeyenda){
		this.camposError = camposError;
		this.codigosLeyenda = codigosLeyenda;
	}
	
	public String getLeyenda() {
		return leyenda;
	}

	public String getCodigoLeyenda() {
		return codigoLeyenda;
	}
	
	/**
	 * @return the parametros
	 */
	public Object[] getParametros() {
		return parametros;
	}

	public String getCampoError() {
		return campoError;
	}

	public List<String> getCamposError() {
		return camposError;
	}

	public List<String> getCodigosLeyenda() {
		return codigosLeyenda;
	}
	
}
