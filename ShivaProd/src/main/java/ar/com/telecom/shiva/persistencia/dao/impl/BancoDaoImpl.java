package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IBancoDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBanco;

public class BancoDaoImpl extends Dao implements IBancoDao{

	@SuppressWarnings("unchecked")
	public ShvParamBanco buscarBanco(String idBanco) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamBanco where idBanco=?");
			qp.addParametro(idBanco);
			List<ShvParamBanco> list = (List<ShvParamBanco>) buscarUsandoQueryConParametros(qp);
			
			if(Validaciones.isCollectionNotEmpty(list)){
				return list.get(0);
			}
			return null;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShvParamBanco> buscarBancoOrigen() throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamBanco");
			List<ShvParamBanco> listaBanco = (List<ShvParamBanco>) buscarUsandoQueryConParametros(qp);
			return listaBanco;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvParamBanco> buscarBancoOrigen(String idEmpresa) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamBanco where empresa.idEmpresa=?");
			qp.addParametro(idEmpresa);
			
			List<ShvParamBanco> listaBanco = (List<ShvParamBanco>) buscarUsandoQueryConParametros(qp);
			return listaBanco;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	@SuppressWarnings("unchecked")
	public List<ShvParamBanco> buscarBancoOrigenOrdenadoPorDescripcion(String idEmpresa) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamBanco where empresa.idEmpresa=? ORDER BY DESCRIPCION ");
			qp.addParametro(idEmpresa);
			
			List<ShvParamBanco> listaBanco = (List<ShvParamBanco>) buscarUsandoQueryConParametros(qp);
			return listaBanco;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
}
