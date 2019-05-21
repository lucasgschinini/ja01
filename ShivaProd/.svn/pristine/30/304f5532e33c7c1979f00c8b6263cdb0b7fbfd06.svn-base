package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.EstadoActivoInactivoEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IEmpresaDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamEmpresa;
import ar.com.telecom.shiva.presentacion.bean.filtro.EmpresaFiltro;

public class EmpresaDaoImpl extends Dao implements IEmpresaDao{
	
	@SuppressWarnings("unchecked")
	public List<ShvParamEmpresa> buscar(EmpresaFiltro filtro) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = null;
			
			if (!Validaciones.isObjectNull(filtro.getIdEmpresa())) {
				qp = new QueryParametrosUtil("from ShvParamEmpresa where estado=? and idEmpresa= ?");
				qp.addParametro(filtro.getEstado());
				qp.addParametro(filtro.getIdEmpresa());
			} else {
				qp = new QueryParametrosUtil("from ShvParamEmpresa where estado=? ");
				qp.addParametro(filtro.getEstado());
			}
			
			List<ShvParamEmpresa> listaEmpresa = (List<ShvParamEmpresa>) buscarUsandoQueryConParametros(qp);
			
			return listaEmpresa;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	public ShvParamEmpresa buscar(String idEmpresa) throws PersistenciaExcepcion {
		try {
			EmpresaFiltro filtro = new EmpresaFiltro();
			
			filtro.setIdEmpresa(idEmpresa);
			filtro.setEstado(EstadoActivoInactivoEnum.ACTIVO);
			List<ShvParamEmpresa> lista = this.buscar(filtro);
			return (lista.size()>0)?lista.get(0):null;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

}
