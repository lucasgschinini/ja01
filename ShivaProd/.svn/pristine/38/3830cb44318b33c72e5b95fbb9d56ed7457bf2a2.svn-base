package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.EstadoGestorEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IGestorDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamGestor;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamGestorSegmentoEmpresa;

public class GestorDaoImpl extends Dao implements IGestorDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ShvParamGestor> listarGestoresActivos(String idEmpresa, String idSegmento)
			throws PersistenciaExcepcion {
		
		String query = "from ShvParamGestorSegmentoEmpresa as ges "
				+ "where ges.segmentoEmpresa.empresa.idEmpresa=? "
				+ " and ges.segmentoEmpresa.segmento.idSegmento=?"
				+ " and ges.gestor.estado=?";
		
		QueryParametrosUtil qp = new QueryParametrosUtil(query);
		qp.addParametro(idEmpresa);
		qp.addParametro(idSegmento);
		qp.addParametro(EstadoGestorEnum.ACTIVO);
		
		List<ShvParamGestorSegmentoEmpresa> lista = (List<ShvParamGestorSegmentoEmpresa>) buscarUsandoQueryConParametros(qp);
		List<ShvParamGestor> resultado = new ArrayList<ShvParamGestor>();
		for (ShvParamGestorSegmentoEmpresa gestorSegEmp : lista) {
			resultado.add(gestorSegEmp.getGestor());
		}
		 return resultado;
	}
}
