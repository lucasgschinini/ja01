package ar.com.telecom.shiva.negocio.servicios.paginado;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.bean.ValidacionesAuxiliar;
import ar.com.telecom.shiva.negocio.servicios.bean.Bean;
import ar.com.telecom.shiva.presentacion.bean.paginacion.ChequeRechazadoControlPaginacion;
import ar.com.telecom.shiva.presentacion.bean.paginacion.ControlPaginacion;
import ar.com.telecom.shiva.presentacion.bean.paginacion.PaginadorAccion;

public interface IPaginadorServicio {
	/**
	 * 
	 * @param chequeRechazadoControlPaginacion
	 * @param validacionesAuxiliar
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<Bean> buscar(ChequeRechazadoControlPaginacion chequeRechazadoControlPaginacion, ValidacionesAuxiliar validacionesAuxiliar) throws NegocioExcepcion;
	/**
	 * 
	 * @param controlPaginacion
	 * @param accion
	 * @param ValidacionesAuxiliar
	 * @return
	 * @throws NegocioExcepcion
	 */
	public List<Bean> paginado(ControlPaginacion controlPaginacion, PaginadorAccion accion, ValidacionesAuxiliar validacionesAuxiliar) throws NegocioExcepcion;
}
