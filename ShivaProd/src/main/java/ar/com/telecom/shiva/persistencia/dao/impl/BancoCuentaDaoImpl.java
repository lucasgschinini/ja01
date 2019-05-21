package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.ArrayList;
import java.util.List;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IBancoCuentaDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamAcuerdo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBanco;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamBancoCuenta;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamSegmentoEmpresa;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoGestion;

public class BancoCuentaDaoImpl extends Dao implements IBancoCuentaDao{

	@SuppressWarnings("unchecked")
	public ShvParamBancoCuenta buscarBancoCuentaPorIdAcuerdo(String idAcuerdo) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamAcuerdo where idAcuerdo=?");
			qp.addParametro(new Integer(idAcuerdo));
			
			List<ShvParamAcuerdo> listaAcuerdo = (List<ShvParamAcuerdo>) buscarUsandoQueryConParametros(qp);
			
			ShvParamBancoCuenta bancoCuenta = listaAcuerdo.get(0).getBancoCuenta();
			return bancoCuenta;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ShvParamBancoCuenta buscarBancoCuentaPorIdCuenta(String idCuenta) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamBancoCuenta where idBancoCuenta=?");
			qp.addParametro(new Integer(idCuenta));
			
			List<ShvParamBancoCuenta> listaBancoCuenta = (List<ShvParamBancoCuenta>) buscarUsandoQueryConParametros(qp);
			return listaBancoCuenta.get(0);
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ShvParamBanco buscarBancoPorIdCuenta(String idCuenta) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamBancoCuenta where idBancoCuenta=?");
			qp.addParametro(new Integer(idCuenta));
			
			List<ShvParamBancoCuenta> listaBancoCuenta = (List<ShvParamBancoCuenta>) buscarUsandoQueryConParametros(qp);
			return listaBancoCuenta.get(0).getBanco();
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvParamBancoCuenta> listarCuentaPorIdBanco(String idBanco, String esConciliable) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("select bancoCuenta from ShvParamAcuerdo as acuerdo "
														+ "	join acuerdo.bancoCuenta as bancoCuenta where bancoCuenta.banco.idBanco=? and acuerdo.conciliable=?");
			qp.addParametro(idBanco);
			qp.addParametro(new Character(esConciliable.charAt(0)));
			List<ShvParamBancoCuenta> listaBancoCuenta = (List<ShvParamBancoCuenta>) buscarUsandoQueryConParametros(qp);
			
			return listaBancoCuenta;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShvParamBancoCuenta> actualizarCuentaPorIdOrigen( String idEmpresa, String idSegmento, String idOrigen) throws PersistenciaExcepcion {
		try {
			List<ShvParamBancoCuenta> listaCuenta = new ArrayList<ShvParamBancoCuenta>();
			
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamSegmentoEmpresa where segmento.idSegmento= ? and empresa.idEmpresa= ? ");
			qp.addParametro(idSegmento);
			qp.addParametro(idEmpresa);
			List<ShvParamSegmentoEmpresa> listaSegmentoEmpresa = (List<ShvParamSegmentoEmpresa>) buscarUsandoQueryConParametros(qp);
			
			qp = new QueryParametrosUtil("from ShvParamTipoGestion where segmentoEmpresa.idSegmentoEmpresa= ? and origen.idOrigen= ? ");
			qp.addParametro(listaSegmentoEmpresa.get(0).getIdSegmentoEmpresa());
			qp.addParametro(new Integer(idOrigen));
			List<ShvParamTipoGestion> listaTipoGestion =(List<ShvParamTipoGestion>) buscarUsandoQueryConParametros(qp);
			
			for (ShvParamTipoGestion tipoGestion : listaTipoGestion) {
				if (!listaCuenta.contains(tipoGestion.getAcuerdo().getBancoCuenta())){
					listaCuenta.add(tipoGestion.getAcuerdo().getBancoCuenta());
				}
			}
			return listaCuenta;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvParamBancoCuenta> actualizarCuentaPorIdOrigenValor( String idEmpresa, String idSegmento, String idOrigen, String tipoValor) throws PersistenciaExcepcion {
		try {
			List<ShvParamBancoCuenta> listaCuenta = new ArrayList<ShvParamBancoCuenta>();
			
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamSegmentoEmpresa where segmento.idSegmento= ? and empresa.idEmpresa= ? ");
			qp.addParametro(idSegmento);
			qp.addParametro(idEmpresa);
			List<ShvParamSegmentoEmpresa> listaSegmentoEmpresa = (List<ShvParamSegmentoEmpresa>) buscarUsandoQueryConParametros(qp);
			
			qp = new QueryParametrosUtil("from ShvParamTipoGestion where segmentoEmpresa.idSegmentoEmpresa= ? and origen.idOrigen= ? and idTipoValor= ? ");
			qp.addParametro(listaSegmentoEmpresa.get(0).getIdSegmentoEmpresa());
			qp.addParametro(new Integer(idOrigen));
			qp.addParametro(new Integer(tipoValor));
			List<ShvParamTipoGestion> listaTipoGestion =(List<ShvParamTipoGestion>) buscarUsandoQueryConParametros(qp);
			
			for (ShvParamTipoGestion tipoGestion : listaTipoGestion) {
				listaCuenta.add(tipoGestion.getAcuerdo().getBancoCuenta());
			}
			return listaCuenta;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvParamBancoCuenta> actualizarCuentaPorAcuerdo(String idAcuerdo) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamAcuerdo where idAcuerdo=?");
			qp.addParametro(new Integer(idAcuerdo));
			List<ShvParamAcuerdo> listaAcuerdo = (List<ShvParamAcuerdo>) buscarUsandoQueryConParametros(qp);
			
			qp = new QueryParametrosUtil("from ShvParamBancoCuenta where idBancoCuenta=?");
			qp.addParametro(listaAcuerdo.get(0).getBancoCuenta().getIdBancoCuenta());
			List<ShvParamBancoCuenta> listaCuenta = (List<ShvParamBancoCuenta>) buscarUsandoQueryConParametros(qp);
			
			return listaCuenta;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvParamBancoCuenta> actualizarCuentaPorIdSegmento( String idEmpresa, String idSegmento) throws PersistenciaExcepcion {
		try {
			List<ShvParamBancoCuenta> listaCuenta = new ArrayList<ShvParamBancoCuenta>();
			
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamSegmentoEmpresa where segmento.idSegmento= ? and empresa.idEmpresa= ? ");
			qp.addParametro(idSegmento);
			qp.addParametro(idEmpresa);
			List<ShvParamSegmentoEmpresa> listaSegmentoEmpresa = (List<ShvParamSegmentoEmpresa>) buscarUsandoQueryConParametros(qp);
			
			qp = new QueryParametrosUtil("from ShvParamTipoGestion where segmentoEmpresa.idSegmentoEmpresa= ? ");
			qp.addParametro(listaSegmentoEmpresa.get(0).getIdSegmentoEmpresa());
			List<ShvParamTipoGestion> listaTipoGestion =(List<ShvParamTipoGestion>) buscarUsandoQueryConParametros(qp);
			
			for (ShvParamTipoGestion tipoGestion : listaTipoGestion) {
				listaCuenta.add(tipoGestion.getAcuerdo().getBancoCuenta());
			}
			return listaCuenta;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShvParamBanco> actualizarBancoPorIdOrigen(String idEmpresa, String idSegmento, String idOrigen) throws PersistenciaExcepcion {
		try {
			List<ShvParamBanco> listaBanco = new ArrayList<ShvParamBanco>();
			
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamSegmentoEmpresa where segmento.idSegmento= ? and empresa.idEmpresa= ? ");
			qp.addParametro(idSegmento);
			qp.addParametro(idEmpresa);
			List<ShvParamSegmentoEmpresa> listaSegmentoEmpresa = (List<ShvParamSegmentoEmpresa>) buscarUsandoQueryConParametros(qp);
			
			qp = new QueryParametrosUtil("from ShvParamTipoGestion where segmentoEmpresa.idSegmentoEmpresa= ? and origen.idOrigen= ? ");
			qp.addParametro(listaSegmentoEmpresa.get(0).getIdSegmentoEmpresa());
			qp.addParametro(new Integer(idOrigen));
			List<ShvParamTipoGestion> listaTipoGestion =(List<ShvParamTipoGestion>) buscarUsandoQueryConParametros(qp);
			
			for (ShvParamTipoGestion tipoGestion : listaTipoGestion) {
				if(!listaBanco.contains(tipoGestion.getAcuerdo().getBancoCuenta().getBanco())){
					listaBanco.add(tipoGestion.getAcuerdo().getBancoCuenta().getBanco());
				}
			}
			return listaBanco;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvParamBanco> actualizarBancoPorIdOrigenValor(String idEmpresa, String idSegmento, String idOrigen, String tipoValor) throws PersistenciaExcepcion {
		try {
			List<ShvParamBanco> listaBanco = new ArrayList<ShvParamBanco>();
			
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamSegmentoEmpresa where segmento.idSegmento= ? and empresa.idEmpresa= ? ");
			qp.addParametro(idSegmento);
			qp.addParametro(idEmpresa);
			List<ShvParamSegmentoEmpresa> listaSegmentoEmpresa = (List<ShvParamSegmentoEmpresa>) buscarUsandoQueryConParametros(qp);
			
			qp = new QueryParametrosUtil("from ShvParamTipoGestion where segmentoEmpresa.idSegmentoEmpresa= ? and origen.idOrigen= ? and idTipoValor= ? ");
			qp.addParametro(listaSegmentoEmpresa.get(0).getIdSegmentoEmpresa());
			qp.addParametro(new Integer(idOrigen));
			qp.addParametro(new Integer(tipoValor));
			List<ShvParamTipoGestion> listaTipoGestion =(List<ShvParamTipoGestion>) buscarUsandoQueryConParametros(qp);
			
			for (ShvParamTipoGestion tipoGestion : listaTipoGestion) {
				if(!listaBanco.contains(tipoGestion.getAcuerdo().getBancoCuenta().getBanco())){
					listaBanco.add(tipoGestion.getAcuerdo().getBancoCuenta().getBanco());
				}
			}
			return listaBanco;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<ShvParamBanco> actualizarBancoPorIdSegmento(String idEmpresa, String idSegmento) throws PersistenciaExcepcion {
		try {
			List<ShvParamBanco> listaBanco = new ArrayList<ShvParamBanco>();
			
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamSegmentoEmpresa where segmento.idSegmento= ? and empresa.idEmpresa= ? ");
			qp.addParametro(idSegmento);
			qp.addParametro(idEmpresa);
			List<ShvParamSegmentoEmpresa> listaSegmentoEmpresa = (List<ShvParamSegmentoEmpresa>) buscarUsandoQueryConParametros(qp);
			
			qp = new QueryParametrosUtil("from ShvParamTipoGestion where segmentoEmpresa.idSegmentoEmpresa= ? ");
			qp.addParametro(listaSegmentoEmpresa.get(0).getIdSegmentoEmpresa());
			List<ShvParamTipoGestion> listaTipoGestion =(List<ShvParamTipoGestion>) buscarUsandoQueryConParametros(qp);
			
			for (ShvParamTipoGestion tipoGestion : listaTipoGestion) {
				if(!listaBanco.contains(tipoGestion.getAcuerdo().getBancoCuenta().getBanco())){
					listaBanco.add(tipoGestion.getAcuerdo().getBancoCuenta().getBanco());
				}
			}
			return listaBanco;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvParamBanco> actualizarBancoPorAcuerdo(String idAcuerdo) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil("from ShvParamAcuerdo where idAcuerdo=?");
			qp.addParametro(new Integer(idAcuerdo));
			List<ShvParamAcuerdo> listaAcuerdo = (List<ShvParamAcuerdo>) buscarUsandoQueryConParametros(qp);
			
			List<ShvParamBanco> listaBanco = new ArrayList<ShvParamBanco>();
			listaBanco.add(listaAcuerdo.get(0).getBancoCuenta().getBanco());
			
			return listaBanco;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvParamBanco> listarBancosNoConciliables(List<ShvParamAcuerdo> listaAcuerdo) throws PersistenciaExcepcion {
		try {			
			QueryParametrosUtil qp = new QueryParametrosUtil();
			
			int i=0;
			String coma="";
			StringBuilder concatenarId= new StringBuilder();
			for(ShvParamAcuerdo acu : listaAcuerdo) {
				coma=(i==0)?"":",";
				concatenarId.append(coma+"?");
				qp.addParametro(acu.getBancoCuenta().getIdBancoCuenta());
				i++;
			}
			
			String query = " select b from ShvParamBanco as b " + 
					" where b.idBanco in (" +
					" select distinct pbc.banco.idBanco " + 
					" from ShvParamBancoCuenta as pbc " + 
					" where pbc.idBancoCuenta in (" + concatenarId.toString() +"))";
			qp.setSql(query);
			
			List<ShvParamBanco> listaBancos = new ArrayList<ShvParamBanco>();
			if (concatenarId.length() > 0) {
				listaBancos =  (List<ShvParamBanco>) buscarUsandoQueryConParametros(qp);
			}
			
			return listaBancos;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ShvParamBancoCuenta> listarCuentaNoConciliables(List<ShvParamAcuerdo> listaAcuerdo) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil();
			
			int i=0;
			String coma="";
			StringBuilder concatenarId= new StringBuilder();
			for(ShvParamAcuerdo acu : listaAcuerdo) {
				coma=(i==0)?"":",";
				concatenarId.append(coma+"?");
				qp.addParametro(acu.getBancoCuenta().getIdBancoCuenta());
				i++;
			}
			
			String query = "from ShvParamBancoCuenta where idBancoCuenta in ("+concatenarId.toString()+")";
			qp.setSql(query);
			
			List<ShvParamBancoCuenta> listaCuenta = new ArrayList<ShvParamBancoCuenta>();
			if (concatenarId.length() > 0) {
					listaCuenta =  (List<ShvParamBancoCuenta>) buscarUsandoQueryConParametros(qp);
			}
			return listaCuenta;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}	
}
