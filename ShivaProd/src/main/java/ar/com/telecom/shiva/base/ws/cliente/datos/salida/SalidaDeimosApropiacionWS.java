package ar.com.telecom.shiva.base.ws.cliente.datos.salida;

import java.util.List;

import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ResultadoProcesamiento;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.deimos.Resultado;

/**
 * @author u573005
 * Sprint 5
 */
@SuppressWarnings("serial")
public class SalidaDeimosApropiacionWS extends SalidaWS {
	
	protected String idSecuencia;
	protected List<Resultado> listaResultados;
    protected ResultadoProcesamiento resultadoProcesamiento;
    
    /*************************************************
	 * Getters & Setters
	 *************************************************/
	public ResultadoProcesamiento getResultadoProcesamiento() {
		return resultadoProcesamiento;
	}
	public void setResultadoProcesamiento(
			ResultadoProcesamiento resultadoProcesamiento) {
		this.resultadoProcesamiento = resultadoProcesamiento;
	}
	public List<Resultado> getListaResultados() {
		return listaResultados;
	}
	public void setListaResultados(List<Resultado> listaResultados) {
		this.listaResultados = listaResultados;
	}
	
	/**
	 * equivale a Id de Operación de Shiva.Id de Transacción 
	 * @param idSecuencia
	 */
	public String getIdSecuencia() {
		return idSecuencia;
	}
	
	/**
	 * equivale a Id de Operación de Shiva.Id de Transacción 
	 * @param idSecuencia
	 */
	public void setIdSecuencia(String idSecuencia) {
		this.idSecuencia = idSecuencia;
	}
	
}