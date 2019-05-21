package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.enumeradores.EstadoValorConstRecepEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IValorConstanciaRecepcionDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvValConstanciaRecepcion;
import ar.com.telecom.shiva.persistencia.modelo.ShvValConstanciaRecepcionValor;
import ar.com.telecom.shiva.persistencia.modelo.ShvValValor;
import ar.com.telecom.shiva.persistencia.modelo.simple.ShvValConstanciaRecepcionValorSimplificado;
import ar.com.telecom.shiva.persistencia.repository.ConstanciaRecepcionRepositorio;
import ar.com.telecom.shiva.persistencia.repository.ConstanciaRecepcionValorRepositorio;

public class ValorConstanciaRecepcionDaoImpl extends Dao implements IValorConstanciaRecepcionDao {
	
	@Autowired ConstanciaRecepcionRepositorio constanciaRecepcionRepositorio;
	@Autowired ConstanciaRecepcionValorRepositorio constanciaRecepcionValorRepositorio;
	
	/**
	 * Graba la ShvValConstanciaRecepcion en la base.
	 */
	@Override
	public ShvValConstanciaRecepcion actualizarConstanciaRecepcion(ShvValConstanciaRecepcion constancia) throws PersistenciaExcepcion {
		try {
			ShvValConstanciaRecepcion constanciaBD = constanciaRecepcionRepositorio.save(constancia);
			constanciaRecepcionRepositorio.flush();
			return constanciaBD;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * 
	 */
	@Override
	public ShvValConstanciaRecepcionValor actualizarConstanciaRecepcionValor(ShvValConstanciaRecepcionValor nuevaConstancia)
			throws PersistenciaExcepcion {
		try {
			ShvValConstanciaRecepcionValor constanciaBD = constanciaRecepcionValorRepositorio.save(nuevaConstancia);
			constanciaRecepcionValorRepositorio.flush();
			return constanciaBD;
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ShvValConstanciaRecepcionValor buscarConstanciaRecepcionAsignadaAValor(Long idValor) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil(
					  "from ShvValConstanciaRecepcionValor cons where "
					+ " cons.shvValConstanciaRecepcionValorPk.valor.idValor=? and "
					+ " cons.shvValConstanciaRecepcionValorPk.constanciaRecepcion.estado=?");
			qp.addParametro(idValor);
			qp.addParametro(EstadoValorConstRecepEnum.ASIGNADA);
			List<ShvValConstanciaRecepcionValor> list = (List<ShvValConstanciaRecepcionValor>) buscarUsandoQueryConParametros(qp);
			
			return (list.size() > 0) ? list.get(0): null;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public ShvValConstanciaRecepcionValorSimplificado buscarConstanciaRecepcionAsignadaAValorSimplificado(Long idValor) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil(
					  "from ShvValConstanciaRecepcionValorSimplificado cons where cons.pk.idValor=? and cons.pk.constanciaRecepcion.estado=?");

			qp.addParametro(idValor);
			qp.addParametro(EstadoValorConstRecepEnum.ASIGNADA);
			List<ShvValConstanciaRecepcionValorSimplificado> list = (List<ShvValConstanciaRecepcionValorSimplificado>) buscarUsandoQueryConParametros(qp);
			
			return (list.size() > 0) ? list.get(0): null;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ShvValValor> buscarValoresAsociadosAConstancia(Integer idConstanciaRecepcion) throws PersistenciaExcepcion {
		try {
			QueryParametrosUtil qp = new QueryParametrosUtil(
					  "select cons.shvValConstanciaRecepcionValorPk.valor from ShvValConstanciaRecepcionValor cons "
					+ "where cons.shvValConstanciaRecepcionValorPk.constanciaRecepcion.idConstanciaRecepcion=?");
			qp.addParametro(idConstanciaRecepcion);
			List<ShvValValor> list = (List<ShvValValor>) buscarUsandoQueryConParametros(qp);
			
			
			return list;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	/**
	 * 
	 */
	public void eliminarRelacionConstanciaValor(ShvValConstanciaRecepcionValor constanciaRecepcionValorActual) throws PersistenciaExcepcion {
		try {
			if (!Validaciones.isObjectNull(constanciaRecepcionValorActual)) {
				constanciaRecepcionValorRepositorio.delete(constanciaRecepcionValorActual);
				constanciaRecepcionValorRepositorio.flush();
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	public ConstanciaRecepcionRepositorio getValorConstanciaRecepcionRepositorio() {
		return constanciaRecepcionRepositorio;
	}

	public void setValorConstanciaRecepcionRepositorio(
			ConstanciaRecepcionRepositorio valorConstanciaRecepcionRepositorio) {
		this.constanciaRecepcionRepositorio = valorConstanciaRecepcionRepositorio;
	}
}
