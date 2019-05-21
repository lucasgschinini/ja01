package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IretencionImpuesto;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoRetencionImpuesto;

public class RetencionImpuestoImpl extends Dao implements IretencionImpuesto{

	@Override
	@SuppressWarnings("unchecked")
	public ShvParamTipoRetencionImpuesto buscarRetencionImpuesto(String idRetencionImpuesto)
			throws PersistenciaExcepcion {
		try {
			String query = "from ShvParamTipoRetencionImpuesto where idTipoRetencionImpuesto=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(new Long(idRetencionImpuesto));
			List<ShvParamTipoRetencionImpuesto> lista = (List<ShvParamTipoRetencionImpuesto>) buscarUsandoQueryConParametros(qp);

			return lista.get(0);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}

	}

}
