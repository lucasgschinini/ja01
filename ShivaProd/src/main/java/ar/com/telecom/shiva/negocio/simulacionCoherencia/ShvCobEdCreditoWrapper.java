package ar.com.telecom.shiva.negocio.simulacionCoherencia;

import java.io.Serializable;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCredito;
/**
 * 
 * @author u578936 Max.Uehara
 */
public class ShvCobEdCreditoWrapper implements Serializable{
	private static final long serialVersionUID = 5273310629358404695L;
	private ShvCobEdCredito credito = null;
	private String idPantalla = "";
	private String tipoMedioPago = "";

	public ShvCobEdCreditoWrapper() {
	}

	public ShvCobEdCreditoWrapper(ShvCobEdCredito credito, String idPantalla) throws NegocioExcepcion {
		super();
		try {
			this.credito = credito;
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage());
		}
		this.credito.setPk(null);
		if (this.credito.getTipoMedioPago() != null) {
			this.tipoMedioPago = this.credito.getTipoMedioPago().getDescripcion();
		}
		this.credito.setTipoMedioPago(null);
		this.idPantalla = idPantalla;
	}

	/**
	 * @return the debito
	 */
	public ShvCobEdCredito getCredito() {
		return credito;
	}

	/**
	 * @param debito the debito to set
	 */
	public void setCredito(ShvCobEdCredito credito) {
		this.credito = credito;
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

	/**
	 * @return the tipoMedioPago
	 */
	public String getTipoMedioPago() {
		return tipoMedioPago;
	}

	/**
	 * @param tipoMedioPago the tipoMedioPago to set
	 */
	public void setTipoMedioPago(String tipoMedioPago) {
		this.tipoMedioPago = tipoMedioPago;
	}
}
