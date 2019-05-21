package ar.com.telecom.shiva.persistencia.bean;

import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.negocio.servicios.bean.Bean;

public class VistaSoporteResultadoBusquedaLegajoCobrosRelacionadoSimplificado extends VistaSoporteResultadoBusqueda implements Bean {
	
	private static final long serialVersionUID = 20170623L;
	private String 	sistema;
	private String	idOperacion;
	private String	idDescobro; //SOLO SHIVA
	private String 	idOperacionDescobro;
	private String	idChequeRechazadoCobro;
	private String	estadoReversionShiva;
	private SistemaEnum sistemaOrigen;

	public VistaSoporteResultadoBusquedaLegajoCobrosRelacionadoSimplificado() {
	}

	/**
	 * @return the sistema
	 */
	public String getSistema() {
		return sistema;
	}

	/**
	 * @param sistema the sistema to set
	 */
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	/**
	 * @return the idOperacion
	 */
	public String getIdOperacion() {
		return idOperacion;
	}

	/**
	 * @param idOperacion the idOperacion to set
	 */
	public void setIdOperacion(String idOperacion) {
		this.idOperacion = idOperacion;
	}

	/**
	 * @return the idDescobro
	 */
	public String getIdDescobro() {
		return idDescobro;
	}

	/**
	 * @param idDescobro the idDescobro to set
	 */
	public void setIdDescobro(String idDescobro) {
		this.idDescobro = idDescobro;
	}

	/**
	 * @return the idChequeRechazadoCobro
	 */
	public String getIdChequeRechazadoCobro() {
		return idChequeRechazadoCobro;
	}

	/**
	 * @param idChequeRechazadoCobro the idChequeRechazadoCobro to set
	 */
	public void setIdChequeRechazadoCobro(String idChequeRechazadoCobro) {
		this.idChequeRechazadoCobro = idChequeRechazadoCobro;
	}

	/**
	 * @return the estadoReversionShiva
	 */
	public String getEstadoReversionShiva() {
		return estadoReversionShiva;
	}

	/**
	 * @param estadoReversionShiva the estadoReversionShiva to set
	 */
	public void setEstadoReversionShiva(String estadoReversionShiva) {
		this.estadoReversionShiva = estadoReversionShiva;
	}

	/**
	 * @return the sistemaOrigen
	 */
	public SistemaEnum getSistemaOrigen() {
		return sistemaOrigen;
	}

	/**
	 * @param sistemaOrigen the sistemaOrigen to set
	 */
	public void setSistemaOrigen(SistemaEnum sistemaOrigen) {
		this.sistemaOrigen = sistemaOrigen;
	}

	/**
	 * @return the idOperacionDescobro
	 */
	public String getIdOperacionDescobro() {
		return idOperacionDescobro;
	}

	/**
	 * @param idOperacionDescobro the idOperacionDescobro to set
	 */
	public void setIdOperacionDescobro(String idOperacionDescobro) {
		this.idOperacionDescobro = idOperacionDescobro;
	}
	
}
