package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IProcesoTransaccionesDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvTransaccionesProceso;

public class ProcesoTransaccionesDaoImpl extends Dao implements IProcesoTransaccionesDao {

	@SuppressWarnings("unchecked")
	public ShvTransaccionesProceso buscarPorNombreDeProceso(String nombreProceso) throws PersistenciaExcepcion {
		try {
			String query = "From ShvTransaccionesProceso where proceso=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(nombreProceso);
			List<ShvTransaccionesProceso> list = (List<ShvTransaccionesProceso>) buscarUsandoQueryConParametros(qp);
			if (!list.isEmpty()){
				return list.get(0);
			} else {
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

}
