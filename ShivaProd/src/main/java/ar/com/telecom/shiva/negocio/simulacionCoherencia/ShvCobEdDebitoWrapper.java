package ar.com.telecom.shiva.negocio.simulacionCoherencia;

import java.io.Serializable;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdDebito;

/**
 * 
 * @author u578936 Max.Uehara
 *
 */
public class ShvCobEdDebitoWrapper implements Serializable{
	private static final long serialVersionUID = 5273310629358404695L;
	private ShvCobEdDebito debito = null;
	private String idPantalla = "";

	public ShvCobEdDebitoWrapper() {
	}

	public ShvCobEdDebitoWrapper(ShvCobEdDebito debito, String idPantalla) throws NegocioExcepcion {
		super();
		try {
			this.debito = (ShvCobEdDebito) Utilidad.clonarObjeto(debito);
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage());
		}
		this.debito.setPk(null);
		this.idPantalla = idPantalla;
	}

	/**
	 * @return the debito
	 */
	public ShvCobEdDebito getDebito() {
		return debito;
	}

	/**
	 * @param debito the debito to set
	 */
	public void setDebito(ShvCobEdDebito debito) {
		this.debito = debito;
	}

	/**
	 * @return the idPantalla
	 */
	public String getIdPantalla() {
		return idPantalla;
	}

	/**
	 * @param idPantalla the idPantalla to set
	 */
	public void setIdPantalla(String idPantalla) {
		this.idPantalla = idPantalla;
	}

}
