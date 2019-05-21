package ar.com.telecom.shiva.base.ws.cliente.datos.entrada.agrupador.deimos;

import java.util.List;

public class Transaccion {

	protected Integer idTransaccion;
	protected Integer idSecuencia;
	protected List<Documento> listaDocumentos;
	
	/*************************************************
	 * Getters & Setters
	 *************************************************/
	public Integer getIdTransaccion() {
		return idTransaccion;
	}
	public void setIdTransaccion(Integer idTransaccion) {
		this.idTransaccion = idTransaccion;
	}
	public Integer getIdSecuencia() {
		return idSecuencia;
	}
	public void setIdSecuencia(Integer idSecuencia) {
		this.idSecuencia = idSecuencia;
	}
	public List<Documento> getListaDocumentos() {
		return listaDocumentos;
	}
	public void setListaDocumentos(List<Documento> listaDocumentos) {
		this.listaDocumentos = listaDocumentos;
	}
	
}