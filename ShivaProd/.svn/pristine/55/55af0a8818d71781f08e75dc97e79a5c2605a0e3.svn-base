package ar.com.telecom.shiva.base.excepciones.otros;

import java.io.Serializable;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;


/**
 * Excepcion de concurrencia
 */
public class ConcurrenciaExcepcion extends NegocioExcepcion {

	private static final long serialVersionUID = 1L;

	private Serializable idInconcurrente;
	private String listaIdsInconcurrentes;
	
	public ConcurrenciaExcepcion() {
		super();
	}

	public ConcurrenciaExcepcion(Serializable id) {
		super();
		this.idInconcurrente = id;
	}
	
	public ConcurrenciaExcepcion(String lista) {
		super();
		this.listaIdsInconcurrentes = lista;
	}
	
	public String getListaIdsInconcurrentes() {
		return listaIdsInconcurrentes;
	}

	public void setListaIdsInconcurrentes(String listaIdsInconcurrentes) {
		this.listaIdsInconcurrentes = listaIdsInconcurrentes;
	}

	public Serializable getIdInconcurrente() {
		return idInconcurrente;
	}

	public void setIdInconcurrente(Serializable idInconcurrente) {
		this.idInconcurrente = idInconcurrente;
	}
	
}
