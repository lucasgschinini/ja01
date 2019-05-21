package ar.com.telecom.shiva.negocio.servicios;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.base.excepciones.ShivaExcepcion;
import ar.com.telecom.shiva.presentacion.bean.dto.json.CreditoJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.dto.json.DebitoJsonResponse;
import ar.com.telecom.shiva.presentacion.bean.filtro.ConfCobroCreditoFiltro;
import ar.com.telecom.shiva.presentacion.bean.filtro.ConfCobroDebitoFiltro;
import ar.com.telecom.shiva.presentacion.bean.paginacion.CreditosControlPaginacion;
import ar.com.telecom.shiva.presentacion.bean.paginacion.DebitosControlPaginacion;

public interface ICobroOnlineBusquedaYPaginadoServicio {
	
	public DebitoJsonResponse buscarDebitos(ConfCobroDebitoFiltro filtro) throws NegocioExcepcion, ShivaExcepcion;
	
	public DebitoJsonResponse paginarDebitos(DebitosControlPaginacion debPaginado, 
			String idsClientes, String idsDoc, String accion) throws NegocioExcepcion, ShivaExcepcion;
	
	public CreditoJsonResponse buscarCreditos(ConfCobroCreditoFiltro filtro) throws NegocioExcepcion, ShivaExcepcion;
	
	public CreditoJsonResponse paginarCreditos(CreditosControlPaginacion credPaginado, 
			String idsClientes, String idsDoc, String accion) throws NegocioExcepcion, ShivaExcepcion;
	
}
