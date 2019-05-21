package ar.com.telecom.shiva.presentacion.bean.paginacion.salida;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.ws.cliente.datos.salida.agrupador.ResultadoProcesamiento;
import ar.com.telecom.shiva.persistencia.bean.VistaSoporteResultadoBusquedaValor;

public class ShivaConsultaCreditoSalida {
	
	protected List<VistaSoporteResultadoBusquedaValor> listaCreditos = new ArrayList<VistaSoporteResultadoBusquedaValor>();
	
	protected ShivaControlPaginado controlPaginado 		= new ShivaControlPaginado();
	protected ShivaResultadoConsulta resultadoConsulta 	= new ShivaResultadoConsulta();
	protected ResultadoProcesamiento resultadoProcesamiento;
	
	public ShivaConsultaCreditoSalida(){
		super();
	}
	
	public ShivaConsultaCreditoSalida(int totalRegistros){
		super();
		this.controlPaginado = new ShivaControlPaginado();
		this.controlPaginado.setCantidadRegistrosTotales(new Long(String.valueOf(totalRegistros)));
		this.controlPaginado.setCantidadRegistrosRetornados(0L);
	}

	public ShivaControlPaginado getControlPaginado() {
		return controlPaginado;
	}

	public void setControlPaginado(ShivaControlPaginado controlPaginado) {
		this.controlPaginado = controlPaginado;
	}

	public ShivaResultadoConsulta getResultadoConsulta() {
		return resultadoConsulta;
	}

	public void setResultadoConsulta(ShivaResultadoConsulta resultadoConsulta) {
		this.resultadoConsulta = resultadoConsulta;
	}

	public List<VistaSoporteResultadoBusquedaValor> getListaCreditos() {
		return listaCreditos;
	}

	public void setListaCreditos(
			List<VistaSoporteResultadoBusquedaValor> listaCreditos) {
		this.listaCreditos = listaCreditos;
	}

	public ResultadoProcesamiento getResultadoProcesamiento() {
		return resultadoProcesamiento;
	}

	public void setResultadoProcesamiento(
			ResultadoProcesamiento resultadoProcesamiento) {
		this.resultadoProcesamiento = resultadoProcesamiento;
	}
	
	/*********************************************************************
	 * Getters & Setters
	 *********************************************************************/

}
