package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IEmpresaSegmentoDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;

public class EmpresaSegmentoDaoImpl extends Dao  implements IEmpresaSegmentoDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ShvParamAcuerdo> listarEmpresaSegmento(
			String idEmpresa, String idSegmento, String tipoValor) throws PersistenciaExcepcion {
		
		try {
			String query = "select tg.acuerdo"
					+ " from ShvParamTipoGestion as tg "
					+ " join tg.segmentoEmpresa as se "
					+ " join se.empresa as e "
					+ " join se.segmento as s "
					+ " where e.idEmpresa = ? "  
					+ " and s.idSegmento = ? "
					+ " and tg.idTipoValor = ? ";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idEmpresa);
			qp.addParametro(idSegmento);
			qp.addParametro(new Integer(tipoValor));
			
			List<ShvParamAcuerdo> listaAcuerdo = buscarUsandoQueryConParametros(qp); 
			return listaAcuerdo;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
		
		
	}

}
