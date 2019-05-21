package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import ar.com.telecom.shiva.base.enumeradores.MonedaEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoReglaCobroEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IReglaCobroDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamReglaCobro;
import ar.com.telecom.shiva.presentacion.bean.filtro.ReglaCobroFiltro;

public class ReglaCobroDaoImpl extends Dao implements IReglaCobroDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ShvParamReglaCobro> buscar(ReglaCobroFiltro reglaCobroFiltro)
			throws PersistenciaExcepcion {
		
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil();
			StringBuffer consulta = new StringBuffer();
					
			consulta.append(
					"from ShvParamReglaCobro as regla "
					+ "where regla.tipoRegla = ? "
					+ "and regla.empresa.idEmpresa = ? "
					+ "and regla.segmento.idSegmento = ? "
					+ "and regla.moneda = ? ");
			
			
			qp.addParametro(TipoReglaCobroEnum.getEnumByName(reglaCobroFiltro.getTipoRegla()));
			qp.addParametro(reglaCobroFiltro.getEmpresa());
			qp.addParametro(reglaCobroFiltro.getSegmento());
			qp.addParametro(MonedaEnum.getEnumByName(reglaCobroFiltro.getMoneda()));
					
			if(!Validaciones.isObjectNull(reglaCobroFiltro.getMonto())) {
				consulta.append("and ? >= regla.montoMinimo and ? <regla.montoMaximo ");
				qp.addParametro(reglaCobroFiltro.getMonto());
				qp.addParametro(reglaCobroFiltro.getMonto());
			}
			
			if(!Validaciones.isObjectNull(reglaCobroFiltro.getPorcentaje())) {
				consulta.append("and ? >= regla.porcentajeMinimo and ? < regla.porcentajeMaximo ");
				qp.addParametro(reglaCobroFiltro.getPorcentaje());
				qp.addParametro(reglaCobroFiltro.getPorcentaje());
			}
			
			qp.setSql(consulta.toString());
			
			List<ShvParamReglaCobro> list = (List<ShvParamReglaCobro>) buscarUsandoQueryConParametros(qp);
			return list;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
}
