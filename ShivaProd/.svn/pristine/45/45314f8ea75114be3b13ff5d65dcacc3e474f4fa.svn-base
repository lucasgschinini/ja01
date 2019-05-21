package ar.com.telecom.shiva.persistencia.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoValorEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IAcuerdoDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBancoCuenta;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoGestion;

public class AcuerdoDaoImpl extends Dao implements IAcuerdoDao{

	// U562163 - se deja de usar este metodo dado que se cambio en la base de datos los acuerdos 87 y 94 por no conciliables. 
	// Y esto provoca que falle el procesar archivo avc y graba en la base en la tabla SHV_AVC_ARCHIVO_AVC el ACUERDO con null.
//	@SuppressWarnings("unchecked")
//	public ShvParamAcuerdo buscarAcuerdoConciliable(String id) throws PersistenciaExcepcion {
//		try {
//			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamAcuerdo where conciliable='S' and idAcuerdo=?");
//			qp.addParametro(new Integer(id));
//			List<ShvParamAcuerdo> list = (List<ShvParamAcuerdo>) buscarUsandoQueryConParametros(qp);
//			
//			return (list.size()>0)?list.get(0):null;
//		} catch (Throwable e) {
//			throw new PersistenciaExcepcion(e);
//		}
//	}
	
	@SuppressWarnings("unchecked")
	public ShvParamAcuerdo buscarAcuerdo(String id) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamAcuerdo where idAcuerdo=?");
			qp.addParametro(new Integer(id));
			List<ShvParamAcuerdo> list = (List<ShvParamAcuerdo>) buscarUsandoQueryConParametros(qp);
			
			return (list.size() > 0) ? list.get(0): null;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ShvParamAcuerdo buscarAcuerdoConciliablePorIdCuenta(String idCuenta) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamAcuerdo where conciliable='S' and bancoCuenta.idBancoCuenta=?");
			qp.addParametro(new Integer(idCuenta));
			List<ShvParamAcuerdo> list = (List<ShvParamAcuerdo>) buscarUsandoQueryConParametros(qp);
			
			return list.get(0);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	@SuppressWarnings("unchecked")
	public ShvParamAcuerdo buscarAcuerdoPorIdCuenta(String idCuenta) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamAcuerdo where bancoCuenta.idBancoCuenta=?");
			qp.addParametro(new Integer(idCuenta));
			List<ShvParamAcuerdo> list = (List<ShvParamAcuerdo>) buscarUsandoQueryConParametros(qp);
			
			return list.get(0);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ShvParamAcuerdo buscarAcuerdoNoConciliablesYconciliablesPorIdCuenta(String idCuenta, String esConciliable) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamAcuerdo where conciliable=? and bancoCuenta.idBancoCuenta=? ");
			qp.addParametro(new Character(esConciliable.charAt(0)));
			qp.addParametro(new Integer(idCuenta));
			List<ShvParamAcuerdo> list = (List<ShvParamAcuerdo>) buscarUsandoQueryConParametros(qp);
			
			if(list.isEmpty()) {
				return null;
			} else {
				return list.get(0);
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShvParamAcuerdo> listarAcuerdoPorIdBanco(String idBanco) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamBancoCuenta where banco.idBanco = ? ");
			qp.addParametro(idBanco);
			
			List<ShvParamBancoCuenta> listaBancoCuenta = (List<ShvParamBancoCuenta>) buscarUsandoQueryConParametros(qp);
			List<ShvParamAcuerdo> listaAcuerdo = new ArrayList<ShvParamAcuerdo>(); 
			for (ShvParamBancoCuenta bancoCuenta : listaBancoCuenta) {
				ShvParamAcuerdo acuerdo = buscarAcuerdoPorIdCuenta(String.valueOf(bancoCuenta.getIdBancoCuenta()));
				if(acuerdo!=null){
					listaAcuerdo.add(acuerdo);
				}
			}
			
			return listaAcuerdo;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvParamAcuerdo> listarAcuerdoNoConciliableYconciliablePorIdBanco(String idBanco, String esConcialiable) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamBancoCuenta where banco.idBanco=? ");
			qp.addParametro(idBanco);
			List<ShvParamBancoCuenta> listaBancoCuenta = (List<ShvParamBancoCuenta>) buscarUsandoQueryConParametros(qp);
			
			List<ShvParamAcuerdo> listaAcuerdo = new ArrayList<ShvParamAcuerdo>(); 
			for (ShvParamBancoCuenta bancoCuenta : listaBancoCuenta) {
				ShvParamAcuerdo acu = buscarAcuerdoNoConciliablesYconciliablesPorIdCuenta(String.valueOf(bancoCuenta.getIdBancoCuenta()),esConcialiable);
				if(acu!=null){
					listaAcuerdo.add(acu);
				}
				
			}
			
			return listaAcuerdo;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShvParamAcuerdo> actualizarAcuerdoPorIdOrigen(String idEmpresa, String idSegmento, String idOrigen) throws PersistenciaExcepcion {
		try {
			
			String query = " select tg.acuerdo from ShvParamTipoGestion As tg " 
						+ " join tg.segmentoEmpresa as se "
						+ " join se.empresa as e "
						+ " join se.segmento as s "
						+ " where e.idEmpresa = ? "  
						+ " and s.idSegmento = ? "
						+ " and tg.origen.idOrigen = ? ";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idEmpresa);
			qp.addParametro(idSegmento);
			qp.addParametro(new Integer(idOrigen));
			List<ShvParamAcuerdo> listAcuerdosPorOrigen = buscarUsandoQueryConParametros(qp);
			
			List<ShvParamAcuerdo> listaAcuerdo = new ArrayList<ShvParamAcuerdo>();
			for (ShvParamAcuerdo acuerdo : listAcuerdosPorOrigen) {
				if (!listaAcuerdo.contains(acuerdo)){
					listaAcuerdo.add(acuerdo);
				}
			}
			return listaAcuerdo;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvParamAcuerdo> actualizarAcuerdoPorIdOrigenValor(String idEmpresa, String idSegmento, String idOrigen, String tipoValor) throws PersistenciaExcepcion {
		try {
			String query = " select tg.acuerdo from ShvParamTipoGestion As tg " 
					+ " join tg.segmentoEmpresa as se "
					+ " join se.empresa as e "
					+ " join se.segmento as s "
					+ " where e.idEmpresa = ? "  
					+ " and s.idSegmento = ? "
					+ " and tg.origen.idOrigen = ? "
					+ " and tg.idTipoValor = ? ";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idEmpresa);
			qp.addParametro(idSegmento);
			qp.addParametro(new Integer(idOrigen));
			qp.addParametro(new Integer(tipoValor));
			
			List<ShvParamAcuerdo> listaAcuerdo=null;
			
			List<ShvParamAcuerdo> listAcuerdosPorOrigenValor = buscarUsandoQueryConParametros(qp);
			
			if(Validaciones.isCollectionNotEmpty(listAcuerdosPorOrigenValor)){
				listaAcuerdo = new ArrayList<ShvParamAcuerdo>(); 
				for (ShvParamAcuerdo acuerdo : listAcuerdosPorOrigenValor) {
					listaAcuerdo.add(acuerdo);
				}
			}
			
			
			return listaAcuerdo;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvParamAcuerdo> actualizarAcuerdoPorIdSegmento(String idEmpresa, String idSegmento) throws PersistenciaExcepcion {
		try {
			
			String query = " select tg.acuerdo from ShvParamTipoGestion As tg " 
					+ " join tg.segmentoEmpresa as se "
					+ " join se.empresa as e "
					+ " join se.segmento as s "
					+ " where e.idEmpresa = ? "  
					+ " and s.idSegmento = ? ";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idEmpresa);
			qp.addParametro(idSegmento);
			List<ShvParamAcuerdo> listAcuerdosPorSegmentoEmpresa = buscarUsandoQueryConParametros(qp);
			
			List<ShvParamAcuerdo> listaAcuerdo = new ArrayList<ShvParamAcuerdo>();
			for (ShvParamAcuerdo acuerdo : listAcuerdosPorSegmentoEmpresa) {
				listaAcuerdo.add(acuerdo);
			}
			return listaAcuerdo;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Retorna los acuerdos NO conciliables y con origen Aviso de Pago.
	 */
	@SuppressWarnings("unchecked")
	public List<ShvParamAcuerdo> listarAcuerdoNoConciliables(String idEmpresa, String idSegmento, String tipoValor) throws PersistenciaExcepcion {		
		
		try {
			String query = " select tg.acuerdo from ShvParamTipoGestion As tg " 
					+ " join tg.segmentoEmpresa as se "
					+ " join se.empresa as e "
					+ " join se.segmento as s "
					+ " where e.idEmpresa = ? "  
					+ " and s.idSegmento = ? "
					+ " and tg.origen.idOrigen = 6 "
					+ " and tg.idTipoValor = ?"
					+ " and tg.acuerdo.conciliable = ?";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idEmpresa);
			qp.addParametro(idSegmento);
			qp.addParametro(new Integer(tipoValor));
			qp.addParametro('N');
			List<ShvParamAcuerdo> listAcuerdosNoConciliables = buscarUsandoQueryConParametros(qp);
			
			return listAcuerdosNoConciliables;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	@Override
	public List<ShvParamAcuerdo> listarAcuerdo(String idEmpresa)  throws PersistenciaExcepcion {
		return this. listarAcuerdo(idEmpresa, null);
	}
	@SuppressWarnings("unchecked")
	public List<ShvParamAcuerdo> listarAcuerdo(String idEmpresa, String idSegmento) throws PersistenciaExcepcion {
		try {
			StringBuffer str = new StringBuffer();
			if (Validaciones.isNullEmptyOrDash(idSegmento)) {
				str.append("from ShvParamAcuerdo acuerdo WHERE acuerdo.bancoCuenta.banco.empresa.idEmpresa =? ORDER BY acuerdo.idAcuerdo ASC");
			} else {
				str.append("select tg.acuerdo from ShvParamTipoGestion As tg where join tg.segmentoEmpresa as se join se.empresa as e ");
				str.append(" join se.segmento as s  where e.idEmpresa = ? and s.idSegmento = ? ORDER BY tg.acuerdo.idAcuerdo ASC");
			}
		
			QueryParametrosUtil qp = new QueryParametrosUtil(str.toString());

			qp.addParametro(idEmpresa);
			if (!Validaciones.isNullEmptyOrDash(idSegmento)) {
				qp.addParametro(idSegmento);
			}
			return (List<ShvParamAcuerdo>) buscarUsandoQueryConParametros(qp);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	@SuppressWarnings("unchecked")
	public List<ShvParamTipoGestion> listarParamTipoGestion(String idEmpresa, Integer idOrigen, List<TipoValorEnum> listaValores, SiNoEnum consiliable) throws PersistenciaExcepcion {
		try {
			StringBuilder str = new StringBuilder("FROM ShvParamTipoGestion ");
			str.append("WHERE segmentoEmpresa.empresa.idEmpresa = ? ");
			if (Validaciones.isCollectionNotEmpty(listaValores)) {
				str.append("AND idTipoValor in ( ");
				List<String> parametros = new ArrayList<String>();
				for (int i = 0; i < listaValores.size(); i++) {
					parametros.add("? ");
				}
				str.append(StringUtils.join(parametros.toArray(new String[parametros.size()]), ", "));
				str.append(") ");
			}
			if (!Validaciones.isObjectNull(idOrigen)) {
				str.append("AND origen.idOrigen = ? ");
			}
			if (!Validaciones.isObjectNull(consiliable)) {
				str.append("AND acuerdo.conciliable = ? ");
			}
			str.append(" ORDER BY segmentoEmpresa.segmento.idSegmento, origen.idOrigen, idTipoValor, acuerdo.idAcuerdo ");

			QueryParametrosUtil qp = new QueryParametrosUtil(str.toString());
			//qp.addParametro("OCJ");
			qp.addParametro(idEmpresa);

			if (Validaciones.isCollectionNotEmpty(listaValores)) {
				for (TipoValorEnum  tipoValor : listaValores) {
					qp.addParametro(tipoValor.getIdTipoValor());
				}
			}
			if (!Validaciones.isObjectNull(idOrigen)) {
				qp.addParametro(idOrigen);
			}
			if (!Validaciones.isObjectNull(consiliable)) {
				if (SiNoEnum.NO.equals(consiliable)) {
					qp.addParametro('N');	
				} else {
					qp.addParametro('S');
				}
				
			}
			
			System.out.println(qp.getSql());
			return (List<ShvParamTipoGestion>) buscarUsandoQueryConParametros(qp);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	@Override
	public List<Map<String, Object>> listarParamTipoGestionDb(String idEmpresa, Integer idOrigen, List<TipoValorEnum> listaValores, SiNoEnum consiliable) throws PersistenciaExcepcion {
		try {
			StringBuilder str = new StringBuilder();
			QueryParametrosUtil parametros = new QueryParametrosUtil();

			str.append("Select ");
			str.append("tipoGestion.id_tipo_valor as id_tipo_valor, tipoGestion.id_origen As id_origen, segEmp.id_empresa As id_empresa, segEmp.id_segmento as id_segmento ");
			str.append(", acuerdo.id_acuerdo as id_Acuerdo, acuerdo.descripcion as acuerdoDescripcion,  acuerdo.es_conciliable As es_conciliable ");
			str.append(",bancoCuenta.CUENTA_BANCARIA as cuenta_bancaria, bancoCuenta.id_banco_cuenta as id_banco_cuenta ");
			str.append(", banco.id_banco as id_banco, banco.descripcion as banco_descripcion, origen.descripcion as origen_descripcion ");
			str.append("FROm SHV_PARAM_TIPO_GESTION tipoGestion "); 
			str.append("left join shv_param_segmento_empresa segEmp On (tipoGestion.id_Segmento_empresa = segEmp.id_Segmento_empresa) ");
			str.append("left join SHV_PARAM_ACUERDO acuerdo on (acuerdo.id_acuerdo = tipoGestion.id_acuerdo) ");
			str.append("left join SHV_PARAM_BANCO_CUENTA bancoCuenta on (acuerdo.ID_BANCO_CUENTA = bancoCuenta.ID_BANCO_CUENTA) ");
			str.append("left join SHV_PARAM_BANCO banco On (bancoCuenta.id_banco = banco.id_banco) ");
			str.append("left join SHV_PARAM_Origen origen On (origen.id_origen = tipoGestion.id_origen) ");
			
			str.append("where segEmp.id_empresa = ? ");
			parametros.addCampoAlQuery(idEmpresa, Types.VARCHAR);
			if (!Validaciones.isObjectNull(idOrigen)) {
				str.append("And tipoGestion.id_origen = ? ");
				parametros.addCampoAlQuery(idOrigen, Types.INTEGER);
			}

			if (Validaciones.isCollectionNotEmpty(listaValores)) {
				str.append("and tipoGestion.id_tipo_valor in ( ");
				List<String> parametrosSql = new ArrayList<String>();
				for (TipoValorEnum tipoValorEnum : listaValores) {
					parametrosSql.add("? ");
					parametros.addCampoAlQuery(tipoValorEnum.getIdTipoValor(), Types.INTEGER);
				}
				str.append(StringUtils.join(parametrosSql.toArray(new String[parametrosSql.size()]), ", "));
				str.append(") ");
			}
			if (!Validaciones.isObjectNull(consiliable)) {
				str.append(" and acuerdo.es_conciliable = ? ");
				parametros.addCampoAlQuery(consiliable.getDescripcionCorta(), Types.CHAR);
				
			}
			str.append(" ORDER BY segEmp.id_segmento,tipoGestion.id_origen, tipoGestion.id_tipo_valor, acuerdo.id_acuerdo ");

			parametros.setSql(str.toString());
			
			System.out.println(parametros.getSql());
			return this.buscarUsandoSQL(parametros);
			} catch (Throwable e) {
				throw new PersistenciaExcepcion(e);
		}
	}
}




