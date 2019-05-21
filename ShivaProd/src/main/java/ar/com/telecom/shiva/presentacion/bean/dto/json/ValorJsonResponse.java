package ar.com.telecom.shiva.presentacion.bean.dto.json;

import java.util.ArrayList;
import java.util.List;



/**
 * @author u578936 M.A.Uehara
 *
 */
public class ValorJsonResponse extends JsonResponse {
	private boolean errorRecibo = false;
	private boolean errorListaVacia = false;
	private boolean errorValorDuplicado = false;

	private List<String> listaErrores = new ArrayList<String>();
	private boolean imprimible = false;

	private String mensajeAlta;
	private String mensajeAltaDuplicado;
	private String url;
	private String action;
	
	private String mensajesMostrarEnvioMail;

	public ValorJsonResponse() {
	}

	/**
	 * @return the errorRecibo
	 */
	public boolean isErrorRecibo() {
		return errorRecibo;
	}

	/**
	 * @param errorRecibo the errorRecibo to set
	 */
	public void setErrorRecibo(boolean errorRecibo) {
		this.errorRecibo = errorRecibo;
	}

	/**
	 * @return the errorListaVacia
	 */
	public boolean isErrorListaVacia() {
		return errorListaVacia;
	}

	/**
	 * @param errorListaVacia the errorListaVacia to set
	 */
	public void setErrorListaVacia(boolean errorListaVacia) {
		this.errorListaVacia = errorListaVacia;
	}

	/**
	 * @return the errorValorDuplicado
	 */
	public boolean isErrorValorDuplicado() {
		return errorValorDuplicado;
	}

	/**
	 * @param errorValorDuplicado the errorValorDuplicado to set
	 */
	public void setErrorValorDuplicado(boolean errorValorDuplicado) {
		this.errorValorDuplicado = errorValorDuplicado;
	}

	/**
	 * @return the mensajeAlta
	 */
	public String getMensajeAlta() {
		return mensajeAlta;
	}

	/**
	 * @param mensajeAlta the mensajeAlta to set
	 */
	public void setMensajeAlta(String mensajeAlta) {
		this.mensajeAlta = mensajeAlta;
	}

	/**
	 * @return the mensajeAltaDuplicado
	 */
	public String getMensajeAltaDuplicado() {
		return mensajeAltaDuplicado;
	}

	/**
	 * @param mensajeAltaDuplicado the mensajeAltaDuplicado to set
	 */
	public void setMensajeAltaDuplicado(String mensajeAltaDuplicado) {
		this.mensajeAltaDuplicado = mensajeAltaDuplicado;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the listaErrores
	 */
	public List<String> getListaErrores() {
		return listaErrores;
	}

	/**
	 * @param listaErrores the listaErrores to set
	 */
	public void setListaErrores(List<String> listaErrores) {
		this.listaErrores = listaErrores;
	}

	/**
	 * @return the imprimible
	 */
	public boolean isImprimible() {
		return imprimible;
	}

	/**
	 * @param imprimible the imprimible to set
	 */
	public void setImprimible(boolean imprimible) {
		this.imprimible = imprimible;
	}

	public String getMensajesMostrarEnvioMail() {
		return mensajesMostrarEnvioMail;
	}

	public void setMensajesMostrarEnvioMail(String mensajesMostrarEnvioMail) {
		this.mensajesMostrarEnvioMail = mensajesMostrarEnvioMail;
	}

	
}
