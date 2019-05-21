package ar.com.telecom.shiva.negocio.simulacionCoherencia;

import java.io.Serializable;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdOtrosDebito;

public class ShvCobEdOtroDebitoWrapper implements Serializable {
	private static final long serialVersionUID = 5273310629358404695L;
	private ShvCobEdOtrosDebito debito = null;
	private String idPantalla = "";

	public ShvCobEdOtroDebitoWrapper() {
	}

	public ShvCobEdOtroDebitoWrapper(ShvCobEdOtrosDebito shvCobEdDebito,
			String idPantalla) throws NegocioExcepcion {
		super();
		try {
			this.debito = (ShvCobEdOtrosDebito) Utilidad.clonarObjeto(shvCobEdDebito);
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage());
		}
		this.debito.setPk(null);
		this.idPantalla = idPantalla;
	}

	/**
	 * @return the debito
	 */
	public ShvCobEdOtrosDebito getDebito() {
		return debito;
	}

	/**
	 * @param debito
	 *            the debito to set
	 */
	public void setDebito(ShvCobEdOtrosDebito debito) {
		this.debito = debito;
	}

	/**
	 * @return the idPantalla
	 */
	public String getIdPantalla() {
		return idPantalla;
	}

	/**
	 * @param idPantalla
	 *            the idPantalla to set
	 */
	public void setIdPantalla(String idPantalla) {
		this.idPantalla = idPantalla;
	}

}
