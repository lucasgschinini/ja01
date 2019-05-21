package ar.com.telecom.shiva.persistencia.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.negocio.workflow.definicion.Estado;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IConciliacionDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvc;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcBoleta;
import ar.com.telecom.shiva.persistencia.modelo.ShvAvcRegistroAvcBoletaPk;
import ar.com.telecom.shiva.persistencia.modelo.ShvBolBoleta;
import ar.com.telecom.shiva.persistencia.repository.RegistroAVCBoletaRepositorio;

public class ConciliacionDaoImpl extends Dao implements IConciliacionDao{

	@Autowired 
	RegistroAVCBoletaRepositorio registroAVCBoletaRepositorio;
	
	/**
	 * Retorna la lista de ShvAvcRegistroAvcBoleta  
	 */
	@SuppressWarnings("unchecked")
	public List<ShvAvcRegistroAvcBoleta> listarConciliacionesSugeridas() throws PersistenciaExcepcion {
		try {
			String query = "select ravc from ShvAvcRegistroAvcBoleta as ravc "
					+ "join ravc.shvAvcRegistroAvcBoletaPk.boleta as b join b.workFlow as w  "
					+ "join w.shvWfWorkflowEstado as weBoleta "
					+ "join ravc.shvAvcRegistroAvcBoletaPk.registroAvc as reg join reg.workFlow as wRegistro  "
					+ "join wRegistro.shvWfWorkflowEstado as weRegistro"
					+ " where weBoleta.estado=? and weRegistro.estado=?";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(Estado.BOL_SUGERIDA);
			qp.addParametro(Estado.AVC_CONCILIACION_SUGERIDA);
			List<ShvAvcRegistroAvcBoleta> resultado = (List<ShvAvcRegistroAvcBoleta>) buscarUsandoQueryConParametros(qp);
			
			return resultado;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Inserta el registro en ShvAvcRegistroAvcBoleta para guardar la relacion en una conciliacion.
	 * @param boleta
	 * @param registroAvc
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public ShvAvcRegistroAvcBoleta insertarConciliacionEnRegistroAvcBoleta(ShvBolBoleta boleta, ShvAvcRegistroAvc registroAvc) throws PersistenciaExcepcion {
		try {
			ShvAvcRegistroAvcBoleta registroBoleta = new ShvAvcRegistroAvcBoleta();
			ShvAvcRegistroAvcBoletaPk shvAvcRegistroAvcBoletaPk = new ShvAvcRegistroAvcBoletaPk();
			shvAvcRegistroAvcBoletaPk.setBoleta(boleta);
			shvAvcRegistroAvcBoletaPk.setRegistroAvc(registroAvc);
			registroBoleta.setShvAvcRegistroAvcBoletaPk(shvAvcRegistroAvcBoletaPk);
			
			
			ShvAvcRegistroAvcBoleta registroAvcBoleta = registroAVCBoletaRepositorio.save(registroBoleta);
			registroAVCBoletaRepositorio.flush();
			return registroAvcBoleta;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Deshace la conciliacion sugerida eliminando al registro de la tabla ShvAvcRegistroAvcBoleta.
	 * @param registroBoleta
	 * @throws PersistenciaExcepcion
	 */
	public void eliminarConciliacionSugerida(String idRegistro, String idBoleta) throws PersistenciaExcepcion {
		try {
			ShvAvcRegistroAvcBoleta registroBoleta = buscarConciliacionesSugeridas(idRegistro, idBoleta);
			if(!Validaciones.isObjectNull(registroBoleta)){
				registroAVCBoletaRepositorio.delete(registroBoleta);
				registroAVCBoletaRepositorio.flush();
			}
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	/**
	 * Retorna el registro ShvAvcRegistroAvcBoleta.
	 */
	@SuppressWarnings("unchecked")
	public ShvAvcRegistroAvcBoleta buscarConciliacionesSugeridas(String idRegistro, String idBoleta) throws PersistenciaExcepcion {
		try {
			String query = "select ravc from ShvAvcRegistroAvcBoleta as ravc "
					+ "join ravc.shvAvcRegistroAvcBoletaPk.boleta as b  "
					+ "join ravc.shvAvcRegistroAvcBoletaPk.registroAvc as r  "
					+ "where b.idBoleta=? and r.idRegistroAvc=?";
			
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addParametro(new Long(idBoleta));
			qp.addParametro(new Long(idRegistro));
			
			List<ShvAvcRegistroAvcBoleta> resultado = (List<ShvAvcRegistroAvcBoleta>) buscarUsandoQueryConParametros(qp);
			if(Validaciones.isCollectionNotEmpty(resultado)){
				return resultado.get(0);
			}
			return null;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

}
