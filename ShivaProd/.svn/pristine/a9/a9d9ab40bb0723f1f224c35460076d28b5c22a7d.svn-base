package ar.com.telecom.shiva.negocio.simulacionCoherencia;

import java.io.Serializable;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdTratamientoDiferencia;

/**
 * 
 * @author u578936 Max.Uehara
 */
public class ShvCobEdTratamientoDiferenciaWrapper implements Serializable{
	private static final long serialVersionUID = 5273310629358404695L;
	private ShvCobEdTratamientoDiferencia diferencia = null;
	private String tipoMedioPago = "";
	
	public ShvCobEdTratamientoDiferenciaWrapper() {
	}

	public ShvCobEdTratamientoDiferenciaWrapper(ShvCobEdTratamientoDiferencia tratamiento) throws NegocioExcepcion {
		super();
		try {
			this.diferencia = tratamiento;
		} catch (Exception e) {
			throw new NegocioExcepcion(e.getMessage());
		}
		this.diferencia.setIdCobro(0l);
		this.diferencia.setCobro(null);
		if (!Validaciones.isObjectNull(this.diferencia.getTipoMedioPago())) {
			this.tipoMedioPago = this.diferencia.getTipoMedioPago().getIdTipoMedioPago();
		}
		this.diferencia.setTipoMedioPago(null);
	}
	/**
	 * @return the diferencia
	 */
	public ShvCobEdTratamientoDiferencia getDiferencia() {
		return diferencia;
	}
	/**
	 * @param diferencia the diferencia to set
	 */
	public void setDiferencia(ShvCobEdTratamientoDiferencia diferencia) {
		this.diferencia = diferencia;
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
