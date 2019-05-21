package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.CriterioBusquedaClienteEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.base.utils.logs.Traza;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IParamClienteDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamCliente;
import ar.com.telecom.shiva.presentacion.bean.filtro.ClienteFiltro;

public class ParamClienteDaoImpl extends Dao implements IParamClienteDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ShvParamCliente> consultarClientes(ClienteFiltro filtro) throws PersistenciaExcepcion {
		try {
			StringBuilder query = new StringBuilder("FROM ShvParamCliente shvParamCliente ");
			QueryParametrosUtil qp = null;

			if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_CLIENTE_LEGADO.name().equals(filtro.getCriterio())) {
				query.append("WHERE shvParamCliente.idClienteLegado =? ");
			} else if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_CUIT.name().equals(filtro.getCriterio())) {
				query.append("WHERE shvParamCliente.cuit =? ");
			} else if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_AGENCIA_NEGOCIO.name().equals(filtro.getCriterio())) {
				query.append("WHERE shvParamCliente.idAgenciaNegocio =? ");
			} else if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_HOLDING.name().equals(filtro.getCriterio())) {
				query.append("WHERE shvParamCliente.numHolding =? ");
			}

			if (CriterioBusquedaClienteEnum.BUSQUEDA_POR_CUIT.name().equals(filtro.getCriterio())) {
				qp = new QueryParametrosUtil(query.toString());
				qp.addParametro(filtro.getBusqueda());
			} else {
				qp = new QueryParametrosUtil(query.toString());
				qp.addParametro(Long.valueOf(filtro.getBusqueda()));
			}

			return (List<ShvParamCliente>) buscarUsandoQueryConParametros(qp);
		} catch (Exception e) {
			Traza.error(getClass(), e.getMessage());
			throw new PersistenciaExcepcion(e);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ShvParamCliente> consultarClientes(List<Long> listaIds) throws PersistenciaExcepcion {
		try {
			StringBuilder query = new StringBuilder("FROM ShvParamCliente shvParamCliente WHERE shvParamCliente.idClienteLegado In (");
			QueryParametrosUtil qp = new QueryParametrosUtil();

			//query.append(StringUtils.join(listaIds, ", "));
			for (Long id : listaIds) {
				query.append("?,");
				qp.addParametro(id);
			}
			if (Validaciones.isCollectionNotEmpty(listaIds)) {
				query.deleteCharAt(query.length() - 1);
			}
			query.append(")");

			qp.setSql(query.toString());
			return (List<ShvParamCliente>) buscarUsandoQueryConParametros(qp);
		} catch (Exception e) {
			Traza.error(getClass(), e.getMessage());
			throw new PersistenciaExcepcion(e);
		}
	}
}
