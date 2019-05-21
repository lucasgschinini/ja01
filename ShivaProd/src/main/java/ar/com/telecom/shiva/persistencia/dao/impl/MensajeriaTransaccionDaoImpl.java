package ar.com.telecom.shiva.persistencia.dao.impl;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.com.telecom.shiva.base.enumeradores.MensajeEstadoEnum;
import ar.com.telecom.shiva.base.enumeradores.MensajeServicioEnum;
import ar.com.telecom.shiva.base.excepciones.PersistenciaExcepcion;
import ar.com.telecom.shiva.base.utils.Utilidad;
import ar.com.telecom.shiva.base.utils.Validaciones;
import ar.com.telecom.shiva.persistencia.dao.Dao;
import ar.com.telecom.shiva.persistencia.dao.IMensajeriaTransaccionDao;
import ar.com.telecom.shiva.persistencia.dao.util.QueryParametrosUtil;
import ar.com.telecom.shiva.persistencia.modelo.ShvCobTransaccionMensajeriaDetalle;
import ar.com.telecom.shiva.persistencia.repository.MensajeriaTransaccionRepositorio;

@SuppressWarnings("unchecked")
public class MensajeriaTransaccionDaoImpl extends Dao implements IMensajeriaTransaccionDao {

	@Autowired 
	MensajeriaTransaccionRepositorio mensajeriaTransaccionRepositorio;
	
	public ShvCobTransaccionMensajeriaDetalle guardarMensaje(ShvCobTransaccionMensajeriaDetalle mensajeria) throws PersistenciaExcepcion {
		try {
			ShvCobTransaccionMensajeriaDetalle mensajeriaBD = 
					mensajeriaTransaccionRepositorio.save(mensajeria);
			mensajeriaTransaccionRepositorio.flush();
			return mensajeriaBD;
		} catch(Exception e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public ShvCobTransaccionMensajeriaDetalle buscarMensajePorIdMensajeria(
			Integer idMensajeria) throws PersistenciaExcepcion {
		List<ShvCobTransaccionMensajeriaDetalle> lista;	
		try {
			String query = "from ShvCobTransaccionMensajeriaDetalle where idTransaccionMensajeria = ?";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(idMensajeria,Types.NUMERIC);
			
			lista = (List<ShvCobTransaccionMensajeriaDetalle>) buscarUsandoQueryConParametros(qp);
			if(Validaciones.isCollectionNotEmpty(lista)){
				return lista.get(0);
			}
			return null;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajePorIdOperacionTransaccion(Long idOperacion, Integer idTransaccion)
			throws PersistenciaExcepcion {
		
		List<ShvCobTransaccionMensajeriaDetalle> lista;	
		try {
			String query = "from ShvCobTransaccionMensajeriaDetalle where idTransaccion = ? and idOperacion= ? "
					+ " order by idTransaccionMensajeria desc ";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(idTransaccion,Types.NUMERIC);
			qp.addCampoAlQuery(idOperacion,Types.NUMERIC);
			
			lista = (List<ShvCobTransaccionMensajeriaDetalle>) buscarUsandoQueryConParametros(qp);
			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajesDesapropiacionConfirmacionEnviadosMIC(Long idOperacion)
			throws PersistenciaExcepcion {
		
		List<ShvCobTransaccionMensajeriaDetalle> lista;	
		try {
			String query = "from ShvCobTransaccionMensajeriaDetalle where idOperacion = ? "
					+ " and estado = '" + MensajeEstadoEnum.ENVIADO + "' "
					+ " and servicio in ( '" + MensajeServicioEnum.MIC_CONFIRMACION + "' , '" 
										+ MensajeServicioEnum.MIC_DESAPROPIACION + "' )"
					+ " order by idTransaccionMensajeria desc";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(idOperacion,Types.NUMERIC);
			
			lista = (List<ShvCobTransaccionMensajeriaDetalle>) buscarUsandoQueryConParametros(qp);
			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajesApropiacionCargoEnviadosMIC(Long idOperacion, Integer idTransaccion)
			throws PersistenciaExcepcion {
		
		List<ShvCobTransaccionMensajeriaDetalle> lista;	
		try {
			String query = "from ShvCobTransaccionMensajeriaDetalle where idTransaccion = ? "
					+ " and idOperacion = ? "
					+ " and estado = '" + MensajeEstadoEnum.ENVIADO+"' "
					+ " and servicio in ('"+MensajeServicioEnum.MIC_APROP_DEUDA + "','"
									+ MensajeServicioEnum.MIC_APROP_DEUDA_Y_MP +"', '" 
									+ MensajeServicioEnum.MIC_APROP_MP + "', '"
									+ MensajeServicioEnum.MIC_APROP_MP + "', '"
									+ MensajeServicioEnum.MIC_DESAPROPIACION + "', '"
									+ MensajeServicioEnum.MIC_GENERACION_CARGO_CREDITO + "', '"
									+ MensajeServicioEnum.MIC_GENERACION_CARGO_DEBITO + "', '"
									+ MensajeServicioEnum.MIC_GENERACION_CARGO_INTERES + "') "
					+ " order by idTransaccionMensajeria desc";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(idTransaccion,Types.NUMERIC);
			qp.addCampoAlQuery(idOperacion,Types.NUMERIC);
			
			lista = (List<ShvCobTransaccionMensajeriaDetalle>) buscarUsandoQueryConParametros(qp);
			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajesDescobroEnviadosMIC(Long idOperacionCobro, Integer idTransaccion)
			throws PersistenciaExcepcion {
		
		List<ShvCobTransaccionMensajeriaDetalle> lista;	
		try {
			String query = "from ShvCobTransaccionMensajeriaDetalle where idTransaccion = ? "
					+ " and idOperacion = ? "
					+ " and estado = '" + MensajeEstadoEnum.ENVIADO+"' "
					+ " and servicio in ('"+ MensajeServicioEnum.MIC_DESCOBRO + "','"
									+ MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_CREDITO +"', '" 
									+ MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_DEBITO +"', '"
									+ MensajeServicioEnum.MIC_DESCOBRO_GENERACION_CARGO_INTERES + "') "
									
					+ " order by idTransaccionMensajeria desc";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(idTransaccion, Types.NUMERIC);
			qp.addCampoAlQuery(idOperacionCobro, Types.NUMERIC);			
			
			lista = (List<ShvCobTransaccionMensajeriaDetalle>) buscarUsandoQueryConParametros(qp);
			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public List<ShvCobTransaccionMensajeriaDetalle> listarMensajesPendientesMIC(String fechaPermitidaParaReenvio) throws PersistenciaExcepcion {
		
		List<ShvCobTransaccionMensajeriaDetalle> lista;	
		try {
			String query = "from ShvCobTransaccionMensajeriaDetalle where (estado = '" + MensajeEstadoEnum.PENDIENTE + "'"
					+ " or (estado = '" + MensajeEstadoEnum.ENVIADO + "' "
					+ " and respuestaRecibida is null) "
					+ " or estado = '" + MensajeEstadoEnum.REC_ERROR + "') "
					+ " and servicio like '%MIC_%' " 
					//+ " and cantReintentos <" + cantMaxReintentos 
					+ " and (fechaEnvio <= to_timestamp( ? ,'" + Utilidad.ORACLE_DATE_TIME_FULL_FORMAT + "')"
					+ " or fechaEnvio is null)"
					+ " order by idTransaccionMensajeria";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(fechaPermitidaParaReenvio,Types.VARCHAR);
			
			lista = (List<ShvCobTransaccionMensajeriaDetalle>) buscarUsandoQueryConParametros(qp);
			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajesRespuestaConfirmacion(Long idOperacion) throws PersistenciaExcepcion {
		
		List<ShvCobTransaccionMensajeriaDetalle> lista;	
		try {
			String query = "from ShvCobTransaccionMensajeriaDetalle where idOperacion = ? "
					+ " and servicio in ('"+MensajeServicioEnum.CLP_CONFIRMACION + "','" + MensajeServicioEnum.MIC_RESPUESTA_CONFIRMACION +"') "
					+ " order by idTransaccionMensajeria desc";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(idOperacion,Types.NUMERIC);
			
			lista = (List<ShvCobTransaccionMensajeriaDetalle>) buscarUsandoQueryConParametros(qp);
			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajesRespuestaDesapropiacion(Long idOperacion) throws PersistenciaExcepcion {
		
		List<ShvCobTransaccionMensajeriaDetalle> lista;	
		try {
			String query = "from ShvCobTransaccionMensajeriaDetalle where idOperacion = ? "
					+ " and servicio in ('"+MensajeServicioEnum.CLP_DESAPROPIACION +"','" + MensajeServicioEnum.MIC_RESPUESTA_DESAPROPIACION +"') "
					+ " order by idTransaccionMensajeria desc";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(idOperacion,Types.NUMERIC);
			
			lista = (List<ShvCobTransaccionMensajeriaDetalle>) buscarUsandoQueryConParametros(qp);
			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajesRespuestaApropiacion(Long idOperacion, Integer idTransaccion) throws PersistenciaExcepcion {
		
		List<ShvCobTransaccionMensajeriaDetalle> lista;	
		try {
			String query = "from ShvCobTransaccionMensajeriaDetalle where idTransaccion = ? and idOperacion = ? "
					+ " and servicio in ('"+MensajeServicioEnum.CLP_APROP_DEUDA + "','"+ MensajeServicioEnum.CLP_APROP_DEUDA_Y_MP +"', "
				    + "'" + MensajeServicioEnum.CLP_APROP_MP + "','"+ MensajeServicioEnum.MIC_RESPUESTA_APROP_DEUDA + "', "
				    + "'" + MensajeServicioEnum.MIC_RESPUESTA_APROP_DEUDA_y_MP + "','" + MensajeServicioEnum.MIC_RESPUESTA_APROP_MP+"') "
					+ " order by idTransaccionMensajeria desc";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(idTransaccion,Types.NUMERIC);
			qp.addCampoAlQuery(idOperacion,Types.NUMERIC);
			
			lista = (List<ShvCobTransaccionMensajeriaDetalle>) buscarUsandoQueryConParametros(qp);
			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajesPorIdOperacion(
			Long idOperacion, MensajeServicioEnum servicio)
			throws PersistenciaExcepcion {
		List<ShvCobTransaccionMensajeriaDetalle> lista;	
		try {
			String query = "from ShvCobTransaccionMensajeriaDetalle where idOperacion = ? "
					+ " and idTransaccion is null and servicio = ? "
					+ " order by idTransaccionMensajeria desc";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(idOperacion,Types.NUMERIC);
			qp.addCampoAlQuery(servicio,Types.VARCHAR);
			
			lista = (List<ShvCobTransaccionMensajeriaDetalle>) buscarUsandoQueryConParametros(qp);
			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	
	@Override
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajesPorIdOperacionTransaccion(
			Long idOperacion, Integer idTransaccion,
			MensajeServicioEnum servicio) throws PersistenciaExcepcion {
		
		List<ShvCobTransaccionMensajeriaDetalle> lista;	
		try {
			String query = "from ShvCobTransaccionMensajeriaDetalle where idTransaccion = ? and idOperacion = ? "
					+ " and servicio = ? "
					+ " order by idTransaccionMensajeria desc";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(idTransaccion,Types.NUMERIC);
			qp.addCampoAlQuery(idOperacion,Types.NUMERIC);			
			qp.addCampoAlQuery(servicio,Types.VARCHAR);
			
			lista = (List<ShvCobTransaccionMensajeriaDetalle>) buscarUsandoQueryConParametros(qp);
			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajesPorIdOperacionTransaccionYEstadoMensaje(
			Long idOperacion, Integer idTransaccion,
			MensajeServicioEnum servicio, MensajeEstadoEnum estado) throws PersistenciaExcepcion {
		
		List<ShvCobTransaccionMensajeriaDetalle> lista;	
		try {
			String query = "from ShvCobTransaccionMensajeriaDetalle where idTransaccion = ? and idOperacion = ? "
					+ " and servicio = ? "
					+ " and estado = ? "
					+ " order by idTransaccionMensajeria desc";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(idTransaccion, Types.NUMERIC);
			qp.addCampoAlQuery(idOperacion, Types.NUMERIC);			
			qp.addCampoAlQuery(servicio, Types.VARCHAR);
			qp.addCampoAlQuery(estado, Types.VARCHAR);
			
			lista = (List<ShvCobTransaccionMensajeriaDetalle>) buscarUsandoQueryConParametros(qp);
			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajeCancelado(Long idOperacion) throws PersistenciaExcepcion {
		
		List<ShvCobTransaccionMensajeriaDetalle> lista;	
		try {
			String query = "from ShvCobTransaccionMensajeriaDetalle where idOperacion = ? "
					+ " and estado='"+ MensajeEstadoEnum.CANCELADO +"'"
					+ " order by idTransaccionMensajeria desc";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(idOperacion,Types.NUMERIC);
			
			lista = (List<ShvCobTransaccionMensajeriaDetalle>) buscarUsandoQueryConParametros(qp);
			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajeConfirmacionCompletadaMic(Long idOperacion) throws PersistenciaExcepcion {
		
		List<ShvCobTransaccionMensajeriaDetalle> lista;	
		try {
			String query = "from ShvCobTransaccionMensajeriaDetalle where idOperacion = ? "
					+ " and estado = '" + MensajeEstadoEnum.COMPLETADO+"' "
					+ " and servicio in ('"+MensajeServicioEnum.MIC_RESPUESTA_CONFIRMACION  + "') "	
					+ " order by idTransaccionMensajeria desc";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(idOperacion,Types.NUMERIC);
			
			lista = (List<ShvCobTransaccionMensajeriaDetalle>) buscarUsandoQueryConParametros(qp);
			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajeDesapropiacionCompletadaMic(Long idOperacion) throws PersistenciaExcepcion {
		
		List<ShvCobTransaccionMensajeriaDetalle> lista;	
		try {
			String query = "from ShvCobTransaccionMensajeriaDetalle where idOperacion = ? "
					+ " and estado = '" + MensajeEstadoEnum.COMPLETADO +"' "
					+ " and servicio in ('"+ MensajeServicioEnum.MIC_RESPUESTA_DESAPROPIACION + "') "
					+ " order by idTransaccionMensajeria desc";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(idOperacion,Types.NUMERIC);
			
			lista = (List<ShvCobTransaccionMensajeriaDetalle>) buscarUsandoQueryConParametros(qp);
			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
	
	@Override
	public List<ShvCobTransaccionMensajeriaDetalle> buscarMensajeDesapropiacionParcialCompletadaMic(Long idOperacion) throws PersistenciaExcepcion {
		
		List<ShvCobTransaccionMensajeriaDetalle> lista;	
		try {
			String query = "from ShvCobTransaccionMensajeriaDetalle where idOperacion = ? "
					+ " and estado = '" + MensajeEstadoEnum.COMPLETADO +"' "
					+ " and servicio in ('"+ MensajeServicioEnum.MIC_RESPUESTA_DESAPROPIACION + "') "
					+ " and idTransaccion is not null "
					+ " order by idTransaccionMensajeria desc";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(idOperacion,Types.NUMERIC);
			
			lista = (List<ShvCobTransaccionMensajeriaDetalle>) buscarUsandoQueryConParametros(qp);
			return lista;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
	public int borrarMensajesPorIdOperacion(
			Long idOperacion) throws PersistenciaExcepcion {
		
		try {
			int countDelete = mensajeriaTransaccionRepositorio.borrarMensajesPorIdOperacion(idOperacion);
			mensajeriaTransaccionRepositorio.flush();
			return countDelete;
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}	
		
	}

	/**
	 * Borroa los mensajes de transacciones segun el id de operacion y MesajeServicioEnum
	 * Los parametros servicio1, servicio2 y servicio3. forma un filtro del tipo "IN (servicio1 , servicio2 , servicio3)
	 */
	@SuppressWarnings("unused")
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
	public void borrarMensajesPorIdOperacionServicios(Long idOperacion, MensajeServicioEnum servicio1, MensajeServicioEnum servicio2, MensajeServicioEnum servicio3) throws PersistenciaExcepcion {
		
		try {
			int countDelete = mensajeriaTransaccionRepositorio.borrarMensajesPorIdOperacionServicios(idOperacion, servicio1, servicio2, servicio3);
			mensajeriaTransaccionRepositorio.flush();
			
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}	
	}
	
	/**
	 * Borroa los mensajes de transacciones segun el id de operacion y MesajeServicioEnum
	 * Los parametros servicio1, servicio2 y servicio3. forma un filtro del tipo "IN (servicio1 , servicio2 , servicio3, etc)
	 * 
	 * @author u572487 Guido.Settecerze
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
	public void borrarMensajesPorIdOperacionServiciosVarios(Long idOperacion, Integer idTransaccion, List<MensajeServicioEnum> listaMensajes) throws PersistenciaExcepcion {
		
		try {
			
			boolean primerMensaje = true;

			String query = "delete From SHV_COB_TRANSACCION_MSJ_DET where id_Operacion = "+idOperacion+"  and id_Transaccion = "+idTransaccion+" and servicio IN (";
			
			for (MensajeServicioEnum mensaje : listaMensajes) {
				
				if(!primerMensaje) {
					query += ",";
				}else {
					primerMensaje=false;
				}
				query += "'"+mensaje+"'";
			}
			
			query += ")";
			
			execute(query);
			
//			int countDelete = mensajeriaTransaccionRepositorio.borrarMensajesPorIdOperacionServiciosVarios(idOperacion, idTransaccion, servicios);
//			mensajeriaTransaccionRepositorio.flush();
			
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}	
	}

	@SuppressWarnings("unused")
	@Transactional(readOnly = false, rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
	public void borrarMensajesPorIdTransaccionMensajeria(Integer idTransaccionMensajeria) throws PersistenciaExcepcion {
		
		try {
			int countDelete = mensajeriaTransaccionRepositorio.borrarMensajesPorIdTransaccionMensajeria(idTransaccionMensajeria);
			mensajeriaTransaccionRepositorio.flush();
			
		}catch(Exception e){
			throw new PersistenciaExcepcion(e);
		}	
	}

	/**
	 * 
	 * @param strIdOperacion
	 * @param idTransaccion
	 * @param servicio
	 * @return
	 * @throws PersistenciaExcepcion
	 */
	public boolean mensajeAnteriormenteProcesado(Long idOperacion, Integer idTransaccion, MensajeServicioEnum servicio) throws PersistenciaExcepcion{
		
		String query = "select trans from ShvCobTransaccionMensajeriaDetalle as trans "
				+ " where trans.servicio=? and trans.idOperacion=? "
				+ " and trans.idTransaccion=? and trans.estado='COMPLETADO'";
		QueryParametrosUtil qp = new QueryParametrosUtil(query.toString());
		qp.addCampoAlQuery(servicio, Types.VARCHAR);
		
		qp.addCampoAlQuery(idOperacion, Types.NUMERIC);
		qp.addCampoAlQuery(idTransaccion, Types.NUMERIC);
		
		List<ShvCobTransaccionMensajeriaDetalle> lista = (List<ShvCobTransaccionMensajeriaDetalle>) buscarUsandoQueryConParametros(qp);
		
		if(Validaciones.isCollectionNotEmpty(lista)){
			return true;
		}
		return false;
	}
	
	@Override
	public ShvCobTransaccionMensajeriaDetalle buscarUltimoMensajeCompletado(Long idOperacion) throws PersistenciaExcepcion {
		
		List<ShvCobTransaccionMensajeriaDetalle> lista;	
		try {
			String query = "from ShvCobTransaccionMensajeriaDetalle where idOperacion = ? "
					+ " and estado = '" + MensajeEstadoEnum.COMPLETADO+"' "
					+ " and respuestaRecibida is not null "	
					+ " order by idTransaccionMensajeria desc";
			QueryParametrosUtil qp = new QueryParametrosUtil(query);
			qp.addCampoAlQuery(idOperacion,Types.NUMERIC);
			
			lista = (List<ShvCobTransaccionMensajeriaDetalle>) buscarUsandoQueryConParametros(qp);
			
			if(Validaciones.isCollectionNotEmpty(lista)){
				return lista.get(0);
			}
			return null;
		} catch (Throwable e) {
			throw new PersistenciaExcepcion(e);
		}
	}
}
