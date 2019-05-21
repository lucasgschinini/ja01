package ar.com.telecom.shiva.base.ws.cliente.datos.salida;

import java.util.ArrayList;
import java.util.List;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.Partida;
import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.sap.ResultadoInvocacion;

/**
 * @author u586743
 *
 */
@SuppressWarnings("serial")
public class SalidaSapConsultaPartidasWS extends SalidaWS {
	
	protected List<Partida> listaPartidas = new ArrayList<Partida>();
	protected List<ResultadoInvocacion> listaErroresFuncionales = new ArrayList<ResultadoInvocacion>();
	protected ResultadoInvocacion resultadoInvocacion = new ResultadoInvocacion();

	/**
	 * @return the listaPartidas
	 */
	public List<Partida> getListaPartidas() {
		return listaPartidas;
	}

	/**
	 * @param listaPartidas the listaPartidas to set
	 */
	public void setListaPartidas(List<Partida> listaPartidas) {
		this.listaPartidas = listaPartidas;
	}

	/**
	 * @return the listaErroresFuncionales
	 */
	public List<ResultadoInvocacion> getListaErroresFuncionales() {
		return listaErroresFuncionales;
	}

	/**
	 * @param listaErroresFuncionales the listaErroresFuncionales to set
	 */
	public void setListaErroresFuncionales(
			List<ResultadoInvocacion> listaErroresFuncionales) {
		this.listaErroresFuncionales = listaErroresFuncionales;
	}

	/**
	 * @return the llamadaServicio
	 */
	public ResultadoInvocacion getResultadoInvocacion() {
		return resultadoInvocacion;
	}

	/**
	 * @param llamadaServicio the llamadaServicio to set
	 */
	public void setResultadoInvocacion(ResultadoInvocacion llamadaServicio) {
		this.resultadoInvocacion = llamadaServicio;
	}
	 

}
