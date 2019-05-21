package ar.com.telecom.shiva.negocio.simulacionCoherencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import ar.com.telecom.shiva.base.comparador.ComparatorShvCobEdCreditoWrapper;
import ar.com.telecom.shiva.base.comparador.ComparatorShvCobEdDebitoWrapper;
import ar.com.telecom.shiva.base.comparador.ComparatorShvCobEdOtroDebitoWrapper;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobEdCobro;

/**
 * 
 * @author u578936 Max.Uehara
 *
 */
public class ShvCobEdCobroDatosSerializados implements Serializable {

	private static final long serialVersionUID = -6319303455970981706L;

	private String segmento = "";

	private Set<ShvCobEdDebitoWrapper> debitos = new TreeSet<ShvCobEdDebitoWrapper> (
		new ComparatorShvCobEdDebitoWrapper()
	);
	private Set<ShvCobEdOtroDebitoWrapper> otrosDebitos = new TreeSet<ShvCobEdOtroDebitoWrapper> (
			new ComparatorShvCobEdOtroDebitoWrapper());
	
	private Set<ShvCobEdCreditoWrapper> creditos = new TreeSet<ShvCobEdCreditoWrapper>(
		new ComparatorShvCobEdCreditoWrapper()
	);
	private List<ShvCobEdOtrosMedioPagoWrapper> medios = new ArrayList<ShvCobEdOtrosMedioPagoWrapper>();
	private ShvCobEdTratamientoDiferenciaWrapper diferencia = null;

	public void serializar(ShvCobEdCobro cobro) {
	}

	/**
	 * @return the otrosDebitos
	 */
	public Set<ShvCobEdOtroDebitoWrapper> getOtrosDebitos() {
		return otrosDebitos;
	}

	/**
	 * @param otrosDebitos the otrosDebitos to set
	 */
	public void setOtrosDebitos(Set<ShvCobEdOtroDebitoWrapper> otrosDebitos) {
		this.otrosDebitos = otrosDebitos;
	}

	/**
	 * @return the debitos
	 */
	public Set<ShvCobEdDebitoWrapper> getDebitos() {
		return debitos;
	}

	/**
	 * @param debitos the debitos to set
	 */
	public void setDebitos(Set<ShvCobEdDebitoWrapper> debitos) {
		this.debitos = debitos;
	}
	/**
	 * @return the creditos
	 */
	public Set<ShvCobEdCreditoWrapper> getCreditos() {
		return creditos;
	}

	/**
	 * @param creditos the creditos to set
	 */
	public void setCreditos(Set<ShvCobEdCreditoWrapper> creditos) {
		this.creditos = creditos;
	}
	

	/**
	 * @return the diferencia
	 */
	public ShvCobEdTratamientoDiferenciaWrapper getDiferencia() {
		return diferencia;
	}

	/**
	 * @param diferencia the diferencia to set
	 */
	public void setDiferencia(ShvCobEdTratamientoDiferenciaWrapper diferencia) {
		this.diferencia = diferencia;
	}

	/**
	 * @return the medios
	 */
	public List<ShvCobEdOtrosMedioPagoWrapper> getMedios() {
		return medios;
	}

	/**
	 * @param medios the medios to set
	 */
	public void setMedios(List<ShvCobEdOtrosMedioPagoWrapper> medios) {
		this.medios = medios;
	}

	/**
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}

	/**
	 * @param segmento the segmento to set
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}
}
