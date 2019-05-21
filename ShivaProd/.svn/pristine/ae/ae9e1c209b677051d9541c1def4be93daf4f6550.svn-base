package ar.com.telecom.shiva.persistencia.dao.impl;

import java.sql.Types;
import java.util.Collection;
import java.util.List;

import javax.persistence.PersistenceException;

import ar.com.telecom.shiva.base.enumeradores.TipoMedioPagoEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.ITipoMedioPagoDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.Modelo;
import ar.com.telecom.shiva.persistencia.modelo.param.ShvParamTipoMedioPago;
import ar.com.telecom.shiva.presentacion.bean.filtro.Filtro;

@SuppressWarnings("unchecked")
public class TipoMedioPagoDaoImpl extends Dao implements ITipoMedioPagoDao {

	@Override
	public Collection<ShvParamTipoMedioPago> listarMediosPago() throws PersistenciaExcepcion {
		return buscarUsandoQueryConParametros(new QueryParametrosUtil("from ShvParamTipoMedioPago"));
	}

	@Override
	public ShvParamTipoMedioPago buscarTipo(String tipo) throws PersistenceException {
		List<ShvParamTipoMedioPago> resultado;	
		try {
			String query = "from ShvParamTipoMedioPago where idTipoMedioPago = ?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(tipo,Types.VARCHAR);

			resultado = (List<ShvParamTipoMedioPago>) buscarUsandoQueryConParametros(qp);
			if(Validaciones.isCollectionNotEmpty(resultado)){
				return resultado.get(0);
			}else{
				return null;
			}
		} catch (PersistenciaExcepcion e) {
			throw new PersistenceException(e.getMessage(),e);
		}
	}

	
	public ShvParamTipoMedioPago buscarMedioPago(TipoMedioPagoEnum tipoMedioPago) throws PersistenceException {
		List<ShvParamTipoMedioPago> resultado;	
		try {
			String query = "select tipoMedPago from ShvParamTipoMedioPago as tipoMedPago "
							+ "where tipoMedPago.descripcion= ? ";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(tipoMedioPago.getDescripcion(),Types.VARCHAR);
			resultado = (List<ShvParamTipoMedioPago>) buscarUsandoQueryConParametros(qp);
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
	public Modelo buscar(Object id) throws PersistenciaExcepcion {
		List<ShvParamTipoMedioPago> listaTipoMedioPago;	
		try {
			String query = "from ShvParamTipoMedioPago where idTipoMedioPago = ? ";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery((String)id,Types.VARCHAR);
			listaTipoMedioPago = (List<ShvParamTipoMedioPago>) buscarUsandoQueryConParametros(qp);
			
			return (listaTipoMedioPago.size() > 0) ? listaTipoMedioPago.get(0) : null;
			
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public Collection<Modelo> listar(Filtro filtro) throws PersistenciaExcepcion {
		return null;
	}

	@Override
	public Modelo crear(Modelo modelo) throws PersistenciaExcepcion {
		return null;
	}

	@Override
	public void modificar(Modelo modelo) throws PersistenciaExcepcion {

	}

	@Override
	public void eliminar(Modelo modelo) throws PersistenciaExcepcion {

	}
}
