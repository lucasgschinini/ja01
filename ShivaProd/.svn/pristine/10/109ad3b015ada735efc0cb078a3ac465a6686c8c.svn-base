package ar.com.telecom.shiva.persistencia.dao.impl;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import ar.com.telecom.shiva.base.enumeradores.SiNoEnum;
import ar.com.telecom.shiva.base.enumeradores.SistemaEnum;
import ar.com.telecom.shiva.base.enumeradores.SociedadEnum;
import ar.com.telecom.shiva.base.enumeradores.TipoComprobanteEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.ITipoComprobanteDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoComprobante;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoComprobanteUso;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoLetraComprobante;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoRetencionImpuesto;

public class TipoComprobanteDaoImpl extends Dao implements ITipoComprobanteDao {
	
	@SuppressWarnings("unchecked")
	public ShvParamTipoComprobante buscarComprobante(TipoComprobanteEnum tipoComprobante) throws PersistenceException {
		List<ShvParamTipoComprobante> resultado;	
		try {
			String query = "select tipoComprobante from ShvParamTipoComprobante as tipoComprobante "
							+ "where tipoComprobante.idTipoComprobante= ? ";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(tipoComprobante.name(),Types.VARCHAR);
			resultado = (List<ShvParamTipoComprobante>) buscarUsandoQueryConParametros(qp);
			if(Validaciones.isCollectionNotEmpty(resultado)){
				return resultado.get(0);
			}else{
				return null;
			}
		} catch (PersistenciaExcepcion e) {
			throw new PersistenceException(e.getMessage(),e);
		}
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<ShvParamTipoComprobante> listarTipoComprobante()
			throws PersistenciaExcepcion {
		try {
			
			List<ShvParamTipoComprobante> listaTipoComprobante = 
					(List<ShvParamTipoComprobante>) buscarUsandoQueryConParametros(new QueryParametrosUtil("from ShvParamTipoComprobante"));
			return listaTipoComprobante;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	@Override
	@SuppressWarnings("unchecked")
	public ShvParamTipoComprobanteUso listarTipoComprobanteUso(SociedadEnum sociedad, SistemaEnum sistema, TipoComprobanteEnum tipoComprobante)
			throws PersistenciaExcepcion {
		try {
			String query = "from ShvParamTipoComprobanteUso where tipoComprobante.idTipoComprobante =? and sociedad =? and sistema =?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(tipoComprobante.name());
			qp.addParametro(sociedad);
			qp.addParametro(sistema);
			
			List<ShvParamTipoComprobanteUso> lista = (List<ShvParamTipoComprobanteUso>) buscarUsandoQueryConParametros(qp);
			
			if (!lista.isEmpty()){
				return lista.get(0);
			} else {
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ShvParamTipoLetraComprobante> listarTipoLetraComprobante()
			throws PersistenciaExcepcion {
		try {
			List<ShvParamTipoLetraComprobante> listaTipoLetraComprobante = 
					(List<ShvParamTipoLetraComprobante>) buscarUsandoQueryConParametros(new QueryParametrosUtil("from ShvParamTipoLetraComprobante"));
			return listaTipoLetraComprobante;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ShvParamTipoRetencionImpuesto> listarTipoImpuesto()
			throws PersistenciaExcepcion {
		try {
			List<ShvParamTipoRetencionImpuesto> listaTipoImpuesto = 
				(List<ShvParamTipoRetencionImpuesto>) buscarUsandoQueryConParametros(new QueryParametrosUtil("from ShvParamTipoRetencionImpuesto"));
			return listaTipoImpuesto;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public ShvParamTipoComprobante listarTipoComprobanteClase(String idTipoComprobante) throws PersistenciaExcepcion {

		try {
			String query = "from ShvParamTipoComprobante where idTipoComprobante=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(idTipoComprobante);
			
			List<ShvParamTipoComprobante> lista = (List<ShvParamTipoComprobante>) buscarUsandoQueryConParametros(qp);
			if (!lista.isEmpty()){
				return lista.get(0);
			} else {
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	public ShvParamTipoLetraComprobante listarTipoLetraComprobanteClase(String letraComprobante) throws PersistenciaExcepcion {

		try {
			String query = "from ShvParamTipoLetraComprobante where idTipoLetraComprobante=?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(letraComprobante);
			
			List<ShvParamTipoLetraComprobante> lista = (List<ShvParamTipoLetraComprobante>) buscarUsandoQueryConParametros(qp);
			if (!lista.isEmpty()){
				return lista.get(0);
			} else {
				return null;
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}

	}

}
