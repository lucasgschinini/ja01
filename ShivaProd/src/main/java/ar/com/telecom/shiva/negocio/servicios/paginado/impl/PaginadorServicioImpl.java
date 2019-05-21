package ar.com.telecom.shiva.negocio.servicios.paginado.impl;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.NegocioExcepcion;
import ar.com.telecom.shiva.negocio.bean.ValidacionesAuxiliar;
import ar.com.telecom.shiva.negocio.servicios.bean.Bean;
import ar.com.telecom.shiva.negocio.servicios.paginado.IPaginadorServicio;
import ar.com.telecom.shiva.presentacion.bean.paginacion.ControlPaginacion;

public abstract class PaginadorServicioImpl implements IPaginadorServicio{

	public PaginadorServicioImpl() {}
	
	protected abstract ControlPaginacion actualizarOffset(ControlPaginacion controlPaginacion, List<Bean> lista);
	
	protected abstract List<Bean> listar(ControlPaginacion controlPaginacion, ValidacionesAuxiliar validacionesAuxiliar) throws NegocioExcepcion;
}
