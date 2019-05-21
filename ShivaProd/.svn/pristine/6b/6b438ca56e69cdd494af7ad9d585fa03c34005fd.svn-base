package ar.com.telecom.shiva.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.servicios.bean.TipoValorBean;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.ITipoValorDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoValor;
import ar.com.telecom.shiva.presentacion.bean.dto.ValorDto;

public class TipoValorDaoImpl extends Dao implements ITipoValorDao {

	@SuppressWarnings("unchecked")
	public ShvParamTipoValor buscarTipoValor(String idTipoValor)
			throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamTipoValor where idTipoValor=?");
			qp.addParametro(new Long(idTipoValor));
			List<ShvParamTipoValor> listaTipoValor = (List<ShvParamTipoValor>) buscarUsandoQueryConParametros(qp);

			return listaTipoValor.get(0);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShvParamTipoValor> buscarTipoValorBoletaCV(ValorDto boletaDto)
			throws PersistenciaExcepcion {
		try {
			String query = " select distinct tg.idTipoValor from ShvParamTipoGestionSimple As tg " 
					+ " join tg.segmentoEmpresa as se "
					+ " join se.empresa as e "
					+ " join se.segmento as s "
					+ " where e.idEmpresa = ? "  
					+ " and s.idSegmento = ? ";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(boletaDto.getIdEmpresa());
			qp.addParametro(boletaDto.getIdSegmento());
			List<Integer> listaIdTipoValor = buscarUsandoQueryConParametros(qp);
			
			QueryParametrosUtil qp1 = new QueryParametrosUtil();
			int i=0;
			String coma="";
			StringBuilder concatenarId= new StringBuilder();
			for (Integer idTipoValor : listaIdTipoValor) {
				coma=(i==0)?"":",";
				concatenarId.append(coma+"?");
				qp1.addParametro(new Long(idTipoValor.toString()));
				i++;
			}
			
			query = " from ShvParamTipoValor "
							+ " where tipoTipoValor = 'BCV' "
							+ " and idTipoValor in ("+concatenarId.toString()+")";
			qp1.setSql(query);
			
			List<ShvParamTipoValor> listaTipoValor = new ArrayList<ShvParamTipoValor>();
			if (concatenarId.length() > 0) {
				listaTipoValor = (List<ShvParamTipoValor>) buscarUsandoQueryConParametros(qp1);
			}
			return listaTipoValor;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShvParamTipoValor> buscarTipoValorAvisoPag(ValorDto boletaDto)
			throws PersistenciaExcepcion {
		try {
			String query = " select distinct tg.idTipoValor from ShvParamTipoGestionSimple As tg " 
					+ " join tg.segmentoEmpresa as se "
					+ " join se.empresa as e "
					+ " join se.segmento as s "
					+ " where e.idEmpresa = ? "  
					+ " and s.idSegmento = ? ";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(boletaDto.getIdEmpresa());
			qp.addParametro(boletaDto.getIdSegmento());
			List<Integer> listaIdTipoValor = buscarUsandoQueryConParametros(qp);
			
			QueryParametrosUtil qp1 = new QueryParametrosUtil();
			int i=0;
			String coma="";
			StringBuilder concatenarId= new StringBuilder();
			for (Integer idTipoValor : listaIdTipoValor) {
				coma=(i==0)?"":",";
				concatenarId.append(coma+"?");
				qp1.addParametro(new Long(idTipoValor.toString()));
				i++;
			}
			
			query = " from ShvParamTipoValor "
					+ " where tipoTipoValor = 'AVS' "
					+ " and idTipoValor in ("+concatenarId.toString()+")";
			qp1.setSql(query);
	
			List<ShvParamTipoValor> listaTipoValor = new ArrayList<ShvParamTipoValor>();
			if (concatenarId.length() > 0) {
				listaTipoValor = (List<ShvParamTipoValor>) buscarUsandoQueryConParametros(qp1);
			}
			
			return listaTipoValor;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public List<TipoValorBean> buscarTiposValor(String idEmpresa, String tipoTipoValor) throws PersistenciaExcepcion {
		try {
			StringBuilder query = new StringBuilder();
			QueryParametrosUtil parametros = new QueryParametrosUtil();
			List<TipoValorBean> beans = new ArrayList<TipoValorBean>();
			
			
			query.append("Select se.id_empresa, ptg.id_tipo_valor, se.id_segmento, tv.descripcion from SHV_PARAM_TIPO_GESTION ptg ");
			query.append("left join Shv_Param_Tipo_Valor tv ON (ptg.id_tipo_valor = tv.id_tipo_valor) left join shv_param_segmento_empresa se ");
			query.append("ON (ptg.id_segmento_empresa = se.id_segmento_empresa) where tv.TIPO_TIPO_VALOR = ? ");
			if (!Validaciones.isNullEmptyOrDash(idEmpresa)) {
				query.append("And se.id_empresa = ? ");
			}
			query.append("AND tv.id_tipo_valor != ? ");
			query.append("group by ptg.id_tipo_valor, se.id_segmento, tv.descripcion, se.id_empresa order by se.id_segmento ");

			parametros.addCampoAlQuery(tipoTipoValor, Types.VARCHAR);
			if (!Validaciones.isNullEmptyOrDash(idEmpresa)) {
				parametros.addCampoAlQuery(idEmpresa, Types.VARCHAR);
			}
			
			parametros.addCampoAlQuery(TipoValorEnum.INTERDEPÓSITO.getIdTipoValor(), Types.NUMERIC);
			parametros.setSql(query.toString());

			List<Map<String, Object>> result =  this.buscarUsandoSQL(parametros);

			if (Validaciones.isCollectionNotEmpty(result)) {
				for (Map<String, Object> reg : result) {
					TipoValorBean bean = new TipoValorBean();

					if (!Validaciones.isObjectNull(reg.get("ID_TIPO_VALOR"))) {
						bean.setIdTipoValor(((BigDecimal) reg.get("ID_TIPO_VALOR")).intValue());
					}
					bean.setDescripcion(reg.get("DESCRIPCION").toString());
					bean.setIdSegmento(reg.get("ID_SEGMENTO").toString());
					bean.setIdEmpresa(reg.get("ID_EMPRESA").toString());
					beans.add(bean);
				}
			}
			return beans;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
}
