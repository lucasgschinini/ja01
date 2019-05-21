package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.negocio.servicios.impl.ContabilidadServicioImpl;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IContabilidadDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvCntContabilidadDetalle;
import ar.com.telecom.shiva.persistencia.repository.ContabilidadRepositorio;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

public class ContabilidadDaoImpl extends Dao implements IContabilidadDao{

	@Autowired
	ContabilidadRepositorio contabilidadRepositorio;
	
	public ShvCntContabilidadDetalle insertarActualizarContabilidadDetalle(ShvCntContabilidadDetalle contabilidadModelo) throws PersistenciaExcepcion {
		try{
			ShvCntContabilidadDetalle contabilidadDB = contabilidadRepositorio.save(contabilidadModelo);
			contabilidadRepositorio.flush();
			return contabilidadDB;
		}catch(Exception e){
			throw new PersistenciaExcepcion(e.getMessage(),e);
		}
	}
	@SuppressWarnings("unchecked")
	public List<ShvCntContabilidadDetalle> listarPorEstado(String estado) throws PersistenciaExcepcion {
		String query = "FROM ShvCntContabilidadDetalle cont WHERE cont.estado=?";
		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addParametro(estado);
		
		List<ShvCntContabilidadDetalle> list = (List<ShvCntContabilidadDetalle>)buscarUsandoQueryConParametros(qp); 
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvCntContabilidadDetalle> listarPorEstadoFechaHasta(String estado, Filtro contabilidadFiltro) throws PersistenciaExcepcion {
		String query = "FROM ShvCntContabilidadDetalle cont WHERE cont.estado = ?"
				+ " and fechaCreacion <= TO_TIMESTAMP( ? , ? )";
		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addParametro(estado);
		qp.addParametro(new String(contabilidadFiltro.getFechaHasta() + " 23:59:59"));
		qp.addParametro(new String(Utilidad.ORACLE_DATE_TIME_FULL_FORMAT));
		
		List<ShvCntContabilidadDetalle> list = (List<ShvCntContabilidadDetalle>)buscarUsandoQueryConParametros(qp); 
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ShvCntContabilidadDetalle> buscarPorIdValor(Long idValor) throws PersistenciaExcepcion{
		String query = "FROM ShvCntContabilidadDetalle cont WHERE cont.idValor=?";
		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addParametro(idValor);
		
		List<ShvCntContabilidadDetalle> listaDetalles = (List<ShvCntContabilidadDetalle>)buscarUsandoQueryConParametros(qp); 
		return listaDetalles;
	}
	
	public List<ShvCntContabilidadDetalle> listarPendientes(Filtro contabilidadFiltro) throws PersistenciaExcepcion{
		return listarPorEstadoFechaHasta(ContabilidadServicioImpl.CONTABILIDAD_PENDIENTE, contabilidadFiltro);
	}
	
	public List<ShvCntContabilidadDetalle> listarEnProceso() throws PersistenciaExcepcion{
		return listarPorEstado(ContabilidadServicioImpl.CONTABILIDAD_PROCESO);
	}

	public List<ShvCntContabilidadDetalle> listarPendientesAcreditacion(Filtro contabilidadFiltro) throws PersistenciaExcepcion {
		return listarPorEstadoFechaHasta(ContabilidadServicioImpl.CONTABILIDAD_PENDIENTE_ACREDITACION, contabilidadFiltro);
	}
	public List<ShvCntContabilidadDetalle> listarPendientesSinAcreditacion(Filtro contabilidadFiltro) throws PersistenciaExcepcion {
		return listarPorEstadoFechaHasta(ContabilidadServicioImpl.CONTABILIDAD_PENDIENTE_SIN_ACREDITACION, contabilidadFiltro);
	}
	
}
